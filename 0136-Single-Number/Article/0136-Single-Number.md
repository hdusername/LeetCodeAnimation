# LeetCode 第 136 号问题：只出现一次的数字

> 本文首发于公众号「图解面试算法」，是 [图解 LeetCode ](<https://github.com/MisterBooo/LeetCodeAnimation>) 系列文章之一。
>
> 同步博客：https://www.algomooc.com

题目来源于 LeetCode 上第 136 号问题：只出现一次的数字。题目难度为 Easy，目前通过率为 66.8% 。

### 题目描述

给定一个**非空**整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

**说明：**

你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

**示例 1:**

```
输入: [2,2,1]
输出: 1
```

**示例 2:**

```
输入: [4,1,2,1,2]
输出: 4
```

### 题目解析

根据题目描述，由于加上了时间复杂度必须是 O(n) ，并且空间复杂度为 O(1) 的条件，因此不能用排序方法，也不能使用 map 数据结构。

程序员小吴想了一下午没想出来，答案是使用 **位操作Bit Operation** 来解此题。

将所有元素做异或运算，即a[1] ⊕  a[2] ⊕  a[3] ⊕ …⊕  a[n]，所得的结果就是那个只出现一次的数字，时间复杂度为O(n)。

### 异或

异或运算A ⊕  B的真值表如下：

| A    |  B   |    ⊕ |
| :--- | :--: | ---: |
| F    |  F   |    F |
| F    |  T   |    T |
| T    |  F   |    T |
| T    |  T   |    F |

### 动画演示

![](../Animation/136.gif)

### 代码实现
#### C
````c
int singleNumber(int* nums, int numsSize){
    int res=0;
    for(int i=0;i<numsSize;i++)
    {
        res ^= nums[i];
    }
    
    return res;
}
````

#### C++
````c++
class Solution35 {
public:
    int singleNumber(vector<int>& nums) {
        int res=0;
        for(auto n:nums)
        {
            // 异或
            res ^= n;
        }
        return res;
    }
};
````

#### Java
````java
class Solution35 {
    public int singleNumber(int[] nums) {
        int res = 0;
        for(int n:nums)
        {
            // 异或
            res ^= n;
        }
        return res;
    }
}
````

#### pyton
````python
class Solution35(object):
    def singleNumber(self, nums):
        return reduce(lambda x,y:x^y, nums)
# reduce用法举例
# 计算列表和：1+2+3+4+5
# 使用 lambda 匿名函数
# reduce(lambda x, y: x+y, [1,2,3,4,5])  
````

### 进阶版

有一个 n 个元素的数组，除了两个数只出现一次外，其余元素都出现两次，让你找出这两个只出现一次的数分别是几，要求时间复杂度为 O(n) 且再开辟的内存空间固定(与 n 无关)。

#### 示例 :

输入: [1,2,2,1,3,4]     
输出: [3,4]

### 题目再解析

根据前面找一个不同数的思路算法，在这里把所有元素都异或，那么得到的结果就是那两个只出现一次的元素异或的结果。

然后，因为这两个只出现一次的元素一定是不相同的，所以这两个元素的二进制形式肯定至少有某一位是不同的，即一个为 0 ，另一个为 1 ，现在需要找到这一位。

根据异或的性质 `任何一个数字异或它自己都等于 0 `，得到这个数字二进制形式中任意一个为 1 的位都是我们要找的那一位。

再然后，以这一位是 1 还是 0 为标准，将数组的 n 个元素分成两部分。

- 将这一位为 0 的所有元素做异或，得出的数就是只出现一次的数中的一个
- 将这一位为 1 的所有元素做异或，得出的数就是只出现一次的数中的另一个。

这样就解出题目。忽略寻找不同位的过程，总共遍历数组两次，时间复杂度为O(n)。

### 动画再演示

![](https://blog-1257126549.cos.ap-guangzhou.myqcloud.com/blog/5uz1n.gif)





![](../../Pictures/qrcode.jpg)
