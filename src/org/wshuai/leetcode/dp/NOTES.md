# DP Notes

## 0/1 背包内循环为什么从大到小？

**核心目的：保证每件物品只被选一次。**

### 正向遍历（从小到大）会出现什么问题？

假设 `present[i] = 2`, `profit = 3`, `budget = 6`：

```
初始: dp = [0, 0, 0, 0, 0, 0, 0]
               ↑b=2: dp[2] = max(dp[2], dp[0]+3) = 3
dp = [0, 0, 3, 0, 0, 0, 0]
               ↑b=4: dp[4] = max(dp[4], dp[2]+3) = 6  ← dp[2] 已被本轮更新！
dp = [0, 0, 3, 0, 6, 0, 0]
                        ↑b=6: dp[6] = max(dp[6], dp[4]+3) = 9  ← 物品 i 被选了 3 次！
```

**同一轮内，`dp[b - present[i]]` 用到了本轮已更新的值，相当于物品 i 被重复选取。**

### 逆向遍历（从大到小）为什么正确？

```
初始: dp = [0, 0, 0, 0, 0, 0, 0]
                        ↑b=6: dp[6] = max(dp[6], dp[4]+3) = 3  ← dp[4] 是上轮的值 0
               ↑b=4: dp[4] = max(dp[4], dp[2]+3) = 3  ← dp[2] 是上轮的值 0
               ↑b=2: dp[2] = max(dp[2], dp[0]+3) = 3  ← dp[0] 是上轮的值 0
dp = [0, 0, 3, 0, 3, 0, 3]  ✓ 每个预算最多选一次物品 i
```

**逆序保证 `dp[b - present[i]]` 一定读取的是上一轮（未加入物品 i）的状态。**

### 一句话总结

| 遍历方向 | 读取的 `dp[b - w]` | 适用场景 |
|---|---|---|
| 从小到大 | 本轮已更新的值 | 完全背包（物品可重复选） |
| **从大到小** | **上一轮的旧值** | **0/1 背包（物品只选一次）** |

---

## 埃拉托斯特尼筛法（Sieve of Eratosthenes）判断质数

```java
static {
    NON_PRIME[0] = NON_PRIME[1] = true;
    for (int i = 2; i <= MAX; i++) {
        if (!NON_PRIME[i]) {
            for (int j = i; j <= MAX / i; j++) {
                NON_PRIME[i * j] = true;
            }
        }
    }
}
```

**核心思想：一个合数必然有一个不超过其平方根的质因子。** 所以只要把每个质数的倍数标记为非质数，剩下没被标记的就都是质数。

### 逐行解析

```java
NON_PRIME[0] = NON_PRIME[1] = true;  // 0 和 1 不是质数，提前标记
```

```java
for (int i = 2; i <= MAX; i++) {
    if (!NON_PRIME[i]) {              // i 还没被标记 → i 是质数
        for (int j = i; j <= MAX / i; j++) {
            NON_PRIME[i * j] = true;  // 把 i 的所有倍数标记为非质数
        }
    }
}
```

### 关键细节：为什么内循环从 `j = i` 开始而不是 `j = 2`？

因为 `i * 2, i * 3, ... i * (i-1)` 这些倍数**已经在前面的轮次被标记过了**：
- `i * 2` 在处理质数 `2` 时已被标记
- `i * 3` 在处理质数 `3` 时已被标记
- ...

所以从 `j = i` 开始，从 `i * i` 起标记，避免重复工作。

### 举例（MAX = 10）

```
初始:  [T, T, F, F, F, F, F, F, F, F, F]
        0  1  2  3  4  5  6  7  8  9  10

i=2 (质数): 标记 4, 6, 8, 10
       [T, T, F, F, T, F, T, F, T, F, T]

i=3 (质数): 标记 9
       [T, T, F, F, T, F, T, F, T, T, T]

i=4 (NON_PRIME=true, 跳过)
i=5 (质数): 5*5=25 > 10, 内循环不执行

结果: 2, 3, 5, 7 是质数 ✓
```

### 使用时

```java
if (!NON_PRIME[n])  // n 是质数
```

---

## 埃拉托斯特尼筛法变体：计算不同质因数个数

```java
static {
    for (int i = 2; i < MAX; i++) {
        if (PRIME_SCORE[i] == 0) {       // i 是质数
            for (int j = i; j < MAX; j += i) {
                PRIME_SCORE[j]++;        // i 是 j 的一个质因数，计数 +1
            }
        }
    }
}
```

### 为什么 `PRIME_SCORE[i] == 0` 能判断 i 是质数？

所有合数 `i` 必然有一个比它小的质因数 `p`，在处理 `p` 时已执行了 `PRIME_SCORE[i]++`，所以到达 `i` 时 `PRIME_SCORE[i] >= 1`。只有质数从未被更小的数整除过，`PRIME_SCORE[i]` 仍为 0。

### 举例

```
i=2 (质数): 给 2,4,6,8,10,12 的 PRIME_SCORE +1
i=3 (质数): 给 3,6,9,12 的 PRIME_SCORE +1
i=4: PRIME_SCORE[4]=1 ≠ 0 → 跳过（4 是合数）

最终:
  PRIME_SCORE[6]  = 2  ← 6 = 2 × 3 ✓
  PRIME_SCORE[12] = 2  ← 12 = 2² × 3（只算不同质因数）✓
  PRIME_SCORE[30] = 3  ← 30 = 2 × 3 × 5 ✓
```

### 与质数筛的关键区别：为什么内循环不从 `i*i` 开始？

| | 质数筛 | 质因数计数筛 |
|---|---|---|
| 内循环步长 | `j++`（逐个） | `j += i`（跳 i 步） |
| 内循环起点 | 可优化到 `i*i` | **必须从 `i` 开始** |
| 能从 `i*i` 起？ | ✓ 不会漏（已被标记） | ✗ 会漏掉 `i*2`~`i*(i-1)` 的质因数 `i` |

---

## Java 求二进制最高位 1 的方法

```java
int n = 12; // 二进制: 1100
```

| 方法 | 结果 | 含义 |
|---|---|---|
| `Integer.highestOneBit(n)` | `8` | 最高位 1 对应的值（2 的幂） |
| `31 - Integer.numberOfLeadingZeros(n)` | `3` | 最高位 1 的位置（从第 0 位开始） |
| `32 - Integer.numberOfLeadingZeros(n)` | `4` | 二进制长度 |

### 应用场景

枚举所有可能的 bit 位（如 #3825）：

```java
int max = 0;
for (int x : nums) max = Math.max(max, x);
int m = 32 - Integer.numberOfLeadingZeros(max); // 二进制长度
for (int i = 0; i < m; i++) {
    // 枚举第 i 位为 1 的元素
    if (((1 << i) & nums[j]) != 0) { ... }
}
```

---

## 贪心 + 二分求最长非递减/严格递增子序列（LIS 变体）

### 核心思想

维护一个有序数组 `arr`，`arr[i]` 表示长度为 `i+1` 的子序列的**最小可能结尾值**。对每个新元素用二分查找确定插入位置，要么追加（延长子序列）要么替换（优化尾部值）。

### 严格递增 vs 非递减的区别：二分查找用 lower bound 还是 upper bound？

| 子序列类型 | 二分查找 | 判断条件 | 原因 |
|---|---|---|---|
| **严格递增** | lower bound | `arr[mid] < target → low = mid + 1` | 相等不行，必须找到 `>=` 的位置替换 |
| **非递减** | upper bound | `arr[mid] <= target → low = mid + 1` | 相等可以，找到 `>` 的位置替换 |

### 严格递增示例（#0300, #1964, #3825）

```java
// lower bound: 找第一个 >= target 的位置
private int binarySearch(int[] nums, int high, int target) {
    int low = 0;
    while (low < high) {
        int mid = low + (high - low) / 2;
        if (nums[mid] < target) {
            low = mid + 1;
        } else {
            high = mid;
        }
    }
    return low;
}
```

### 非递减示例（#2826, #2111）

```java
// upper bound: 找第一个 > target 的位置
private int binarySearch(int[] nums, int high, int target) {
    int low = 0;
    while (low < high) {
        int mid = low + (high - low) / 2;
        if (nums[mid] <= target) {
            low = mid + 1;
        } else {
            high = mid;
        }
    }
    return low;
}
```

### 最少修改次数 = n - LIS 长度

当题目问"最少操作使数组有序"时，答案就是 `n - 最长非递减/递增子序列长度`（保留最多已有序的元素，剩下的才需要改）。

相关题目：#0300, #1671, #1964, #2111, #2826, #3825

---

## Java 反转数组的方法

Java 没有内置的原始数组反转方法，最常用的是双指针交换：

```java
// 双指针原地反转（最快，O(n) 时间，O(1) 空间）
private void reverse(int[] nums) {
    for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

| 方法 | 原地 | 速度 | 适用类型 |
|---|---|---|---|
| 双指针交换 | 是 | 最快 | `int[]`, `char[]` 等原始数组 |
| `Collections.reverse` | 是 | 快 | `List<T>` |
| `IntStream` | 否 | 较慢 | `int[]`（生成新数组） |

### 应用场景

反转数组后求 LIS = 求原数组的最长严格递减子序列（如 #1671 山脉数组）：

```java
int[] left = lisAtEachPosition(nums);   // 正向 LIS
reverse(nums);
int[] right = lisAtEachPosition(nums);  // 反向 LIS = 原数组的最长递减子序列
```

---

## O(n²) 预计算所有回文子串

### 代码

```java
boolean[][] isPalin = new boolean[n][n];
for (int i = n - 1; i >= 0; i--) {
    for (int j = i; j < n; j++) {
        isPalin[i][j] = s.charAt(i) == s.charAt(j)
                && (j - i <= 2 || isPalin[i + 1][j - 1]);
    }
}
```

### 转移条件

`s[i..j]` 是回文 ⟺ **首尾相等** 且 **中间部分也是回文**：

- `s[i] == s[j]`：首尾字符相等
- `j - i <= 2`：长度 ≤ 3 时（单字符 / 两字符 / 三字符），首尾相等即可
- `isPalin[i+1][j-1]`：长度 > 3 时，需要中间子串 `s[i+1..j-1]` 也是回文

### 填表顺序

`i` 从下往上（`n-1 → 0`），`j` 从左往右（`i → n-1`）。因为 `isPalin[i][j]` 依赖 `isPalin[i+1][j-1]`（左下方的格子），必须先算下面的行。

### 可视化示例（s = "aabac"）

```
        j=0  j=1  j=2  j=3  j=4
        'a'  'a'  'b'  'a'  'c'
i=0      T    T    F    F    F     "a" "aa" "aab" "aaba" "aabac"
i=1           T    F    T    F     "a" "ab" "aba" "abac"
i=2                T    F    F     "b" "ba" "bac"
i=3                     T    F     "a" "ac"
i=4                          T     "c"
```

填表过程：

```
i=4: [4][4] 'c'=='c'                          → T（单字符）

i=3: [3][3] 'a'=='a'                          → T
     [3][4] 'a'=='c'                          → F

i=2: [2][2] 'b'=='b'                          → T
     [2][3] 'b'=='a'                          → F
     [2][4] 'b'=='c'                          → F

i=1: [1][1] 'a'=='a'                          → T
     [1][2] 'a'=='b'                          → F
     [1][3] 'a'=='a' && j-i=2 ≤ 2            → T（"aba"，长度 3 首尾相等即可）
     [1][4] 'a'=='c'                          → F

i=0: [0][0] 'a'=='a'                          → T
     [0][1] 'a'=='a' && j-i=1 ≤ 2            → T（"aa"）
     [0][2] 'a'=='b'                          → F
     [0][3] 'a'=='a' && isPalin[1][2]=F       → F（"aaba"，中间"ab"非回文）
     [0][4] 'a'=='c'                          → F
```

依赖关系图：

```
        j →
   i  [ T  T  F  F  F ]
   ↓  [    T  F  T  F ]
      [       T  F  F ]
      [          T  F ]
      [             T ]
                ↗
      isPalin[i+1][j-1] 在左下方，已经算过
```

### 使用场景

预计算后，任意子串的回文判断都是 O(1) 查表：

```java
if (isPalin[j][i]) { ... }  // s[j..i] 是回文？O(1)
```

相关题目：#0005, #0131, #0132, #0516, #0647
