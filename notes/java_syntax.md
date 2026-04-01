# Java Syntax Notes

## 反转字符串

### 双指针交换 (O(1) 额外空间, 原地反转 char[])

```java
char[] cs = s.toCharArray();
for (int i = 0, j = cs.length - 1; i < j; i++, j--) {
    char tmp = cs[i];
    cs[i] = cs[j];
    cs[j] = tmp;
}
String reversed = new String(cs);
```

### StringBuilder (O(n) 额外空间)

```java
String reversed = new StringBuilder(s).reverse().toString();
```

| 方法 | 时间 | 额外空间 | 适用 |
|---|---|---|---|
| 双指针交换 | O(n) | O(1) | char[] 原地反转 |
| StringBuilder.reverse() | O(n) | O(n) | String (不可变, 必须新建) |

双指针的优势体现在已经有 `char[]` 的场景下. Java 的 String 不可变, 如果输入是 String, 无论哪种方法都需要 O(n) 空间存结果.

---

## 反转数组

### 双指针交换 (最快, O(1) 空间)

```java
for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
}
```

| 方法 | 原地 | 速度 | 适用类型 |
|---|---|---|---|
| 双指针交换 | 是 | 最快 | int[], char[] 等原始数组 |
| Collections.reverse | 是 | 快 | List<T> |
| IntStream | 否 | 较慢 | int[] (生成新数组) |

---

## 创建 Map 数组

Java 不支持泛型数组直接创建, 需要用裸类型:

```java
// 正确写法: 创建裸类型数组, 会有 unchecked warning
Map<Integer, Integer>[] memo = new HashMap[n];
Arrays.setAll(memo, i -> new HashMap<>());

// 错误写法: 编译不过
// Map<Integer, Integer>[] memo = new HashMap<Integer, Integer>[n];
```

---

## 求二进制最高位 1

```java
int n = 12; // 二进制: 1100
```

| 方法 | 结果 | 含义 |
|---|---|---|
| Integer.highestOneBit(n) | 8 | 最高位 1 对应的值 (2 的幂) |
| 31 - Integer.numberOfLeadingZeros(n) | 3 | 最高位 1 的位置 (从第 0 位开始) |
| 32 - Integer.numberOfLeadingZeros(n) | 4 | 二进制长度 |

### 应用: 枚举所有 bit 位

```java
int max = 0;
for (int x : nums) max = Math.max(max, x);
int m = 32 - Integer.numberOfLeadingZeros(max);
for (int i = 0; i < m; i++) {
    if (((1 << i) & nums[j]) != 0) { ... }
}
```
