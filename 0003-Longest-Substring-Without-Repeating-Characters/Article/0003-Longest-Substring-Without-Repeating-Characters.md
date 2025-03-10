# LeetCode 第 3 号问题：无重复字符的最长子串

题目来源于 LeetCode 上第 3 号问题：无重复字符的最长子串。题目难度为 Medium，目前通过率为 29.0% 。

## 题目描述

给定一个字符串，请你找出其中不含有重复字符的 **最长子串** 的长度。

**示例：**

```
输入: "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
```

## 题目解析

建立一个256位大小的整型数组 freg ，用来建立字符和其出现位置之间的映射。

维护一个滑动窗口，窗口内的都是没有重复的字符，去尽可能的扩大窗口的大小，窗口不停的向右滑动。

- （1）如果当前遍历到的字符从未出现过，那么直接扩大右边界；
- （2）如果当前遍历到的字符出现过，则缩小窗口（左边索引向右移动），然后继续观察当前遍历到的字符；
- （3）重复（1）（2），直到左边索引无法再移动；
- （4）维护一个结果res，每次用出现过的窗口大小来更新结果 res，最后返回 res 获取结果。

## 动画描述

![动画描述](https://blog-1257126549.cos.ap-guangzhou.myqcloud.com/blog/20ahe.gif)

## 代码实现

```java
/**
 * 使用滑动窗口来解决这个问题
 */
class Solution35 {
    public int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }
}
```

### 复杂度分析
- 时间复杂度： `O(N)`，其中N是字符串的长度。左指针和右指针分别会遍历整个字符串一次。
- 空间复杂度： `O(∣Σ∣)`，其中Σ表示字符集（即字符串中可以出现的字符），∣Σ∣ 表示字符集的大小。在本题中没有明确说明字符集，因此可以默认为所有 ASCII 码在[0 , 128)
