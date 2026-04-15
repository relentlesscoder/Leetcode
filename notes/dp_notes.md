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

---

## 划分型 DP 的通用模板

### 核心公式

```
dfs(i) = min/max over j { dfs(j-1) + cost(j, i) }
```

枚举最后一段的起点 `j`, 最后一段为 `arr[j..i]`, 递归处理 `arr[0..j-1]`.

### j 的选择决定了复杂度

| j 的范围 | 写法 | 示例 |
|---|---|---|
| 固定 1-2 个选择 | 直接 `Math.max/min` | #0091, #0639, #2369, #3196 |
| for 循环枚举 | `for j = i downto 0` | #0132, #0139, #1043, #1105, #1416, #2547, #2707, #2767, #3144 |
| for + 数据结构加速 | 线段树/BIT 优化 | #2547 (线段树) |

### 内循环的常见剪枝/限制

- **Trie 匹配**: 路径不存在直接 break (#0139, #2707, #2767)
- **宽度/长度限制**: 总宽度超限 break (#1043, #1105), 最长单词长度限制 (#0139, #2707)
- **数值超限**: 数值 > k 时 break (#1416)
- **贪心**: 找到最短合法段就 break (#2472)

---

## 线段树优化划分型 DP (#2547 详解)

### 问题

将数组划分成若干段, 每段 cost = k + trimmedLength (去掉只出现一次的元素后的长度).

### 第一步: 原始 O(n²) DP

```
dp[i] = min over j in [1..i] { dp[j-1] + k + (i-j+1) - h(j,i) }
```

h(j,i) = nums[j-1..i-1] 中只出现一次的元素个数.

### 第二步: 提取常数项, 定义线段树存储内容

```
dp[i] = k + i + min over j { dp[j-1] - (j-1) - h(j,i) }
                              ^^^^^^^^^^^^^^^^^^^^^^^^^^^
                              tree[j] 存的就是这个值
```

定义 `tree[j] = dp[j-1] - (j-1) - h(j, 当前i)`, 则:

```
dp[i] = k + i + min(tree[1..i])    ← 线段树区间最小值查询 O(log n)
```

代码中用 `res = dp[i] - i` 省掉偏移, 最后 `return res + n`.

### 第三步: 增量更新 h 的变化

当加入 `nums[i-1] = v` 时, h(j, i) 相比 h(j, i-1) 的变化:

```
段起点 j 的位置:

 0    prevToLast    last              i
 |--------|----------|----...---------|
    区域A      区域B         区域C
```

| 区域 | j 的范围 | v 的 freq 变化 | h 变化 | tree 更新 |
|---|---|---|---|---|
| C | [last+1, i] | 0→1 (首次出现) | h+1 | tree-1 |
| B | [prevToLast+1, last] | 1→2 (不再 distinct) | h-1 | tree+1 |
| A | [1, prevToLast] | >=2→>=3 (仍不 distinct) | 不变 | 不更新 |

```java
st.update(last[num] + 1, i, -1);              // 区域C: h+1 → tree-1
st.update(prevToLast[num] + 1, last[num], 1);  // 区域B: h-1 → tree+1
```

每步只做 O(1) 次区间更新, 每次 O(log n), 总时间 O(n log n).

---

## Lazy Propagation 线段树: spread 为什么不越界

`spread(node)` 访问 `node*2` 和 `node*2+1`, 两个条件保证不越界:

### 1. spread 只在内部节点调用

```java
void update(int node, int left, int right, int start, int end, int val) {
    if (left >= start && right <= end) {
        apply(node, val);   // 可能是叶子, 但不调 spread
        return;
    }
    spread(node);           // 只有 left < right (内部节点) 才走到这里
    // ...
}
```

`left == right` (叶子) 时, 第一个 `if` 必命中, 直接 return, 不会调 `spread`.

### 2. 数组开得够大

```java
int size = 2 << (32 - Integer.numberOfLeadingZeros(n));
//       ≈ 4 * nextPowerOf2(n) >= 4n
```

线段树最深叶子节点编号 <= 4n, 所以 `node*2+1` 永远在数组范围内.

两个条件缺一不可: 叶子节点调 spread → 子节点无意义; 数组太小 → 有效子节点越界.

---

## LCP (Longest Common Prefix) 数组预计算

### 定义

`lcp[i][j]` = 从位置 `i` 开始的子串和从位置 `j` 开始的子串的最长公共前缀长度.

### 预计算代码

```java
int[][] lcp = new int[n + 1][n + 1];
for (int i = n - 1; i >= 0; i--) {
    for (int j = n - 1; j > i; j--) {
        if (s[i] == s[j]) {
            lcp[i][j] = lcp[i + 1][j + 1] + 1;
        }
    }
}
```

从右下角往左上角填表, 因为 `lcp[i][j]` 依赖 `lcp[i+1][j+1]` (右下方的格子).

### 典型用法: 判断两个子串是否相等

判断 `s[i..i+len-1] == s[j..j+len-1]`:

```java
if (lcp[i][j] >= len) {
    // s[i..i+len-1] 和 s[j..j+len-1] 完全相同
}
```

**注意用 `>=` 而不是 `==`**: `lcp[i][j]` 是**最长**公共前缀, 可能比 `len` 更长. 我们只需要前 `len` 个字符匹配, 后面多匹配的不关心. 用 `==` 会错误地要求第 `len+1` 个字符必须不同.

```
示例: s = "aaaa", i = 0, j = 1, len = 1

s[0..] = "aaaa"
s[1..] = "aaa"
lcp[0][1] = 3 (公共前缀 "aaa")

>= 1 → 3 >= 1 ✓ 前 1 个字符相同, 可以操作
== 1 → 3 == 1 ✗ 错误地认为不匹配
```

### 与 O(n²) 子串比较的对比

| 方法 | 预处理 | 每次比较 |
|---|---|---|
| 直接比较 `s.substring` | 无 | O(n) |
| LCP 数组 | O(n²) | O(1) |

当需要多次判断子串相等时, LCP 预处理后每次 O(1) 查表, 避免重复比较.

相关题目: #2430

---

## Rolling Hash (滚动哈希)

### 原理

把字符串当成 BASE 进制的数, 用取模避免溢出.

```
字符串 "abc" 的哈希值 = a * BASE² + b * BASE¹ + c * BASE⁰
类似十进制: 123 = 1*100 + 2*10 + 3*1
```

### 前缀哈希预计算

```java
long MOD = (1L << 61) - 1, BASE = 131;
long[] hash = new long[n + 1], pow = new long[n + 1];
pow[0] = 1;
for (int i = 0; i < n; i++) {
    hash[i + 1] = (hash[i] * BASE + s[i]) % MOD;
    pow[i + 1] = pow[i] * BASE % MOD;
}
```

### O(1) 提取子串哈希

类似前缀和相减, 但要乘以 BASE 的幂来对齐位数:

```
hash("abc") = a*BASE² + b*BASE + c
hash("a")   = a

hash("bc") = hash("abc") - hash("a") * BASE²
           = (a*BASE² + b*BASE + c) - a*BASE²
           = b*BASE + c  ✓
```

```java
// 获取 s[l..r] 的哈希值
private long getHash(long[] hash, long[] pow, int l, int r, long MOD) {
    return (hash[r + 1] - hash[l] * pow[r - l + 1] % MOD + MOD) % MOD;
    //      ^^^^^^^^^^    ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    //      前缀hash[0..r]  减去前缀hash[0..l-1] 左移对齐
    //                                                ^^^
    //                                          +MOD 防止减法变负数
}
```

### 判断子串相等

```java
// O(1) 判断 s[i..i+len-1] == s[j..j+len-1]
if (getHash(hash, pow, i, i + len - 1, MOD)
    == getHash(hash, pow, j, j + len - 1, MOD)) {
    // 两个子串相同 (极小概率哈希冲突)
}
```

### 为什么选 MOD = 2⁶¹ - 1?

这是一个梅森素数, 两个好处:
1. **足够大** — 冲突概率 ≈ 1/2⁶¹, 极小
2. **取模快** — 梅森素数的取模可以用位运算优化

### Rolling Hash vs LCP 数组

| | LCP 数组 | Rolling Hash |
|---|---|---|
| 预处理 | O(n²) 时间, O(n²) 空间 | O(n) 时间, O(n) 空间 |
| 判断子串相等 | O(1) 查表 | O(1) 哈希比较 |
| 正确性 | 100% 正确 | 极小概率哈希冲突 |
| 消除冲突 | - | 用双哈希 (两组 BASE/MOD) |

当空间敏感时优先用 Rolling Hash; 当正确性要求严格时用 LCP 数组.

相关题目: #2430

---

## 状态机 DP

### 核心思想

状态机 DP 用于处理"当前选择受上一步状态约束"的问题. 每个时间步有若干状态, 状态之间的转移规则用状态机描述. DP 值定义在"第 i 步处于状态 j"上.

### 状态机图 (#3259 为例)

两个状态: 喝 A 或喝 B. 连续喝同一饮料直接转移, 切换饮料需跳过一步.

#         切换 (跳过 1 小时)
               ┌────────────────────┐
               │                    ▼
           ┌───┐ ┌───┐        ┌───┐ ┌───┐
           │   └▶│ A │        │ B │◀┘   │
           └────◀┘   │        │   └▶────┘
           继续  └───┘         └───┘  继续
               ▲                    │
               └────────────────────┘
                 切换 (跳过 1 小时)

### 转移公式

```
dp[i][0] = max(dp[i-1][0], dp[i-2][1]) + energyA[i]
                ^^^^^^^^    ^^^^^^^^
                连续喝A     从B切换, 跳过i-1

dp[i][1] = max(dp[i-1][1], dp[i-2][0]) + energyB[i]
                ^^^^^^^^    ^^^^^^^^
                连续喝B     从A切换, 跳过i-1
```

### 通用模板

```java
// 滚动变量优化, 只保留前两步的状态
long[] d0 = new long[numStates];  // i-2 步
long[] d1 = initValues;           // i-1 步
for (int i = 1; i < n; i++) {
    long[] c = new long[numStates];
    for (int s = 0; s < numStates; s++) {
        // 从各个合法的前驱状态转移
        c[s] = bestTransition(d0, d1, s) + value[s][i];
    }
    d0 = d1;
    d1 = c;
}
return max(d1);
```

### 常见变体

| 模式 | 转移跨度 | 示例 |
|---|---|---|
| 相邻约束(买卖股票) | i-1 步转移 | #0121, #0122, #0123, #0188, #0309, #0714 |
| 切换有冷却期 | i-2 步转移 | #0309, #3259 |
| 多状态选择 | 每步从多个状态中选 | #0188 (k 次交易) |

相关题目: #0121, #0122, #0123, #0188, #0309, #0714, #3259

---

## 子数组 vs 子序列 DP: 答案在哪里取?

### 核心区别

| | 子数组(连续) | 子序列(不连续) |
|---|---|---|
| 约束 | 元素必须连续 | 元素可跳过 |
| dp 含义 | 以第 i 个元素**结尾**的最优值 | 前 i 个元素中的最优值 |
| 最优解位置 | 任何位置都可能是最优终点 | 一定在最后一个位置取到 |
| 答案 | 遍历过程中取 `max(res, dp[i])` | 直接取 `dp[n-1]` |

### 为什么子数组需要每个位置取 max?

连续性约束导致子数组可能在中间被截断(比如遇到 0、负数翻转等), 后面的 dp 值可能比前面小. 最优子数组的终点可以在任何位置.

```java
// #1567: 遇到0截断, 最长正积子数组可能在中间结束
for (int i = 0; i < n; i++) {
    // ... 更新 dp[i]
    res = Math.max(res, dp[i]); // 每个位置都可能是答案
}
```

### 为什么子序列只需看最后一个位置?

子序列可以跳过任意元素, dp 值单调不减 — 后面的状态总能继承前面的最优解(`dp[i] = max(dp[i-1], ...)`，不选时直接继承). 所以 `dp[n-1]` 一定 >= 之前所有位置的值.

```java
// #2786: 子序列可跳过元素, dp值只增不减
for (int i = 1; i < n; i++) {
    if (nums[i] % 2 == j) {
        dp[i][j] = Math.max(dp[i-1][j], ... + nums[i]); // 选: 可能更大
    } else {
        dp[i][j] = dp[i-1][j]; // 不选: 直接继承, 不会变小
    }
}
return dp[n-1]; // 最后一定是最优
```

### 快速判断方法

- **有截断/重置条件**(遇0归零、连续性中断) → 子数组模式 → 每步取 max
- **不选时直接继承前一步** → 子序列模式 → 只取最后

相关题目: 子数组 #0053, #0152, #1567; 子序列 #2786, #2708
