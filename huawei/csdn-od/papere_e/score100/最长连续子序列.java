import java.util.Arrays;
import java.util.Scanner;

/**
 * 有N个正整数组成的一个序列。给定整数sum，求长度最长的连续子序列，使他们的和等于sum，返回此子序列的长度，
 * 如果没有满足要求的序列，返回-1。
 *
 * 输入描述：
 * 第一行输入是：N个正整数组成的一个序列
 * 第二行输入是：给定整数sum
 *
 * 输出描述：
 * 最长的连续子序列的长度
 *
 * 备注
 * 输入序列仅由数字和英文逗号构成，数字之间采用英文逗号分隔
 * 序列长度：1 <= N <= 200
 * 输入序列不考虑异常情况
 *
 * 输入
 * 1,2,3,4,2
 * 6
 * 输出
 * 3
 * 说明
 * 1,2,3和4,2两个序列均能满足要求，所以最长的连续序列为1，2，3，因此结果为3
 */
public class 最长连续子序列 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arrays = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();

        int target = Integer.parseInt(scanner.nextLine());

        int[] preArrays = new int[arrays.length+1];

        int maxLen = 0;
        //获取前缀和,角标对应位置的值是当前值与之前值的集合
        for (int i=1; i<arrays.length+1; i++){
            preArrays[i] = preArrays[i-1]+arrays[i-1];
        }

        for(int i=1; i<preArrays.length; i++){
            for(int j=i; j<preArrays.length; j++){
                //todo: j=i这样写可以满足：找到数组中有一位与target相同的情况，也就是支持长度为1的情况
                //比如说用例：
                //1,2,3,4,2
                //1
                //最终会输出1
                if(preArrays[j]-preArrays[i-1]==target){
                    maxLen = Math.max(maxLen, j-i+1);
                }
            }
        }
       System.out.println(maxLen==0?-1:maxLen);
    }
}
