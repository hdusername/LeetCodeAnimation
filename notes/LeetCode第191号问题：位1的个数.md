# LeetCode 第 191 号问题：位 1 的个数

> 本文首发于公众号「五分钟学算法」，是[图解 LeetCode ](<https://github.com/MisterBooo/LeetCodeAnimation>)系列文章之一。
>
> 个人网站：[https://www.cxyxiaowu.com](https://www.cxyxiaowu.com)

题目来源于 LeetCode 上第 191 号问题： 位 1 的个数。题目难度为 Easy，目前通过率为 52.3% 。

### 题目描述

编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为[汉明重量](https://baike.baidu.com/item/%E6%B1%89%E6%98%8E%E9%87%8D%E9%87%8F)）。

 

**示例 1：**

```
输入：00000000000000000000000000001011
输出：3
解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
```

**示例 2：**

```
输入：00000000000000000000000010000000
输出：1
解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
```

**示例 3：**

```
输入：11111111111111111111111111111101
输出：31
解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
```

 

**提示：**

- 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
- 在 Java 中，编译器使用[二进制补码](https://baike.baidu.com/item/%E4%BA%8C%E8%BF%9B%E5%88%B6%E8%A1%A5%E7%A0%81/5295284)记法来表示有符号整数。因此，在上面的 **示例 3** 中，输入表示有符号整数 `-3`。

 

**进阶**:
如果多次调用这个函数，你将如何优化你的算法？

### 题目解析

该题比较简单，解法有挺多，有位移法、位操作法、查表法、二次查表法等方法。

观察一下 n 与 n-1 这两个数的二进制表示：对于 n-1 这个数的二进制来说，相对于 n 的二进制，它的最末位的一个 1 会变成 0，最末位一个 1 之后的 0 会全部变成 1，其它位相同不变。

比如 n = 8888，其二进制为 **10001010111000**

则 n - 1 = 8887 ，其二进制为 **10001010110111**

通过按位与操作后：n & (n-1) = **10001010110000**

也就是说：通过 **n&(n-1)这个操作**，可以起到**消除最后一个1**的作用。

所以可以通过执行 n&(n-1) 操作来消除 n 末尾的 1 ，消除了多少次，就说明有多少个 1 。 



### 动画描述

暂无~

### 代码实现

```c++
class Solution35 {
public:
    int hammingWeight(uint32_t n) {
        int cnt = 0;
        while(n > 0){
            cnt++;
            n = n & (n - 1);
        }
        return cnt;
    }
};
```



![](https://blog-1257126549.cos.ap-guangzhou.myqcloud.com/blog/se6v6.png)