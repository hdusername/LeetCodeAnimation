import java.util.Arrays;

/**
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 *
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [10,2]
 * 输出："210"
 * 示例 2：
 *
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 */
public class 最大数 {

    public static void main(String[] args) {
        String ss = "a::b:c";
        String[] split = ss.split(":");
        System.out.println(split.length);
    }

    public String largestNumber(int[] nums) {
        String[] largestStrArr = new String[nums.length];
        for(int i=0; i<nums.length; i++){
            largestStrArr[i] = nums[i]+"";
        }

        Arrays.sort(largestStrArr, (a, b)->{
            return (b+a).compareTo(a+b);
        });
        StringBuffer stringBuffer = new StringBuffer();
        Arrays.stream(largestStrArr).forEach(a->stringBuffer.append(a));
        while (stringBuffer.length()>1&&stringBuffer.toString().startsWith("0")){
            stringBuffer.deleteCharAt(0);
        }
        return stringBuffer.toString();
    }
}
