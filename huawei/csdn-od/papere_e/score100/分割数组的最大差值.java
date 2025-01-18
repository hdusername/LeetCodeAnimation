import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定一个由若干整数组成的数组nums ，可以在数组内的任意位置进行分割，将该数组分割
 * 成两个非空子数组（即左数组和右数组），分别对子数组求和得到两个值，计算这两个值的差值，请输出所有分割方案中，差值最大的值。
 *
 * 输入描述：
 * 第一行输入数组中元素个数n，1 < n ≤ 100000
 * 第二行输入数字序列，以空格进行分隔，数字取值为4字节整数
 *
 *  输出描述：
 *  输出差值的最大取值
 *
 */
public class 分割数组的最大差值 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for(int i=0; i<n; i++){
            nums[i] = scanner.nextInt();
        }

        int maxDiff = 0;
        //左侧数组元素的和初始化为0，右侧初始化为整个数组元素的和，从左向右遍历的过程中左边加，右边减，用这种方式可以方便的在循环中得到左右两侧数组元素的和
        int leftSum = 0;
        int rightSum = Arrays.stream(nums).sum();
        //左侧数组最多到倒数第二个元素，因为左右侧数组不能为空，这里保证了右侧数组不能为空
        for(int i=0; i<n-1; i++){
            leftSum=leftSum+nums[i];
            rightSum = rightSum-nums[i];
            maxDiff = Math.max(maxDiff,Math.abs(leftSum-rightSum));
        }

        System.out.println(maxDiff);
    }
}
