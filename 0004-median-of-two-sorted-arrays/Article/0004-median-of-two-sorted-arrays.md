# LeetCode 第 4 号问题：寻找两个正序数组的中位数

题目来源于 LeetCode 上第 4 号问题：寻找两个正序数组的中位数。题目难度为 Hard，目前通过率为 29.0% 。

## 题目描述

> 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
你可以假设 nums1 和 nums2 不会同时为空。

```
示例1：
nums1 = [1, 3]
nums2 = [2]
    
则中位数是 2.0
    
示例2：
nums1 = [1, 2]
nums2 = [3, 4]
    
则中位数是 (2 + 3)/2 = 2.5
```

## 题目解析

## 官方解法
假设数组1的长度m，假设数组2的长度为n，用线把两个数组的元素分成两部分。
分组原则是：1.线左边和右边元素个数相等，或者左边比右边多一个元素；2.线左边
所有元素的值都比都小于等于线右边所有元素的值；
- 当m+n为偶数的时候，sizeleft=(m+n)/2=(m+n+1)/2因为会向下取整。
- 当m+n为奇数的时候，假设会把多出的数分到左边，sizeleft=(m+n+1)/2
- 因此把以上两种写法合并，sizeleft=(m+n+1)/2

## 代码实现

```java
/**
 * 本题中要求算法的时间复杂度为 O(log(m + n))
 * 说明要使用二分查找法
 */
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length>nums2.length){
            //这一步是为了让第一个数组的长度短，因为找分割线时是找的长度短的数组之间做分割线，以此避免分割线划到数组短的数组之外
            int[] tmpArr = nums1;
            nums1 = nums2;
            nums2 = tmpArr;
        }
        int m = nums1.length;
        int n = nums2.length;
        //两个数组分割线左面元素个数
        int totalSize = (m+n+1)/2;

        int left = 0;
        int right = m;

        //这个循环是为了用二分法查找第一个数组中的元素，并且与第二个数组进行关联，找到符合条件的i,j值
        while(left<right){
            //i表示第一个数组分割线右边第一个元素的下标
            //正常的二分查找法这里是不会+1的，这里是为了防止发生死循环，因为在下面的else的逻辑中没有对i值进行更改，导致发生死循环
            int i = left + (right - left + 1)/2;
            //j表示第二个数组分割线右边第一个元素的下标
            int j = totalSize - i;

            if(nums1[i-1]>nums2[j]){
                //下一轮搜索区间[left,i-1]
                right = i-1;
            }else {
                //下一轮搜索区间[i,right]
                left = i;
            }
        }
        int i = left;
        int j = totalSize - i;

        int nums1LeftMax = i==0? Integer.MIN_VALUE:nums1[i-1];
        //当i=第一个数组的长度时，说明第一个数组分割线右边没有值，所以肯定不会选到此数组的num1[i]的值也就是分割线右边的值，分割线右边取得都是最小值，所以将它设置为int的最大值，保证不会取到它
        int nums1RightMin = i==m? Integer.MAX_VALUE:nums1[i];
        int nums2LeftMax = j==0? Integer.MIN_VALUE:nums2[j-1];
        int nums2RightMin = j==n? Integer.MAX_VALUE:nums2[j];

        if((m + n) % 2 == 1){
            //数组元素个数相加起来是奇数
            return Math.max(nums1LeftMax,nums2LeftMax );
        }else{
            return (double) ((Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin))) / 2;
        }
    }
}
```

### 复杂度分析

+ 时间复杂度：对数组进行二分查找，因此为O(logN)
+ 空间复杂度：O(1)
