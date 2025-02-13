import java.util.Arrays;
import java.util.Scanner;

/**
 * 给航天器一侧加装长方形或正方形的太阳能板（图中的红色斜线区域），需要先安装两个支柱（图中的黑色竖条），再在支柱的中间部分固定太阳能板。
 * 但航天器不同位置的支柱长度不同，太阳能板的安装面积受限于最短一侧的那根支柱长度。
 * 现提供一组整形数组的支柱高度数据，假设每根支柱间距离相等为 1 个单位长度，计算如何选择两根支柱可以使太阳能板的面积最大。
 *
 * 输入描述：
 * 10,9,8,7,6,5,4,3,2,1
 * 注：支柱至少有2根，最多10000根，能支持的高度范围1~10^9的整数。柱子的高度是无序的，例子中递减只是巧合。
 *
 * 输出描述：
 * 可以支持的最大太阳能板面积：（10米高支柱和5米高支柱之间）
 * 25
 *
 * 用例输入：
 * 10,9,8,7,6,5,4,3,2,1
 *
 * 输出：
 * 25
 *
 * 解释：
 * 10米高支柱和5米高支柱之间宽度为5，高度取小的支柱高也是5，面积为25。
 * 任取其他两根支柱所能获得的面积都小于25。
 * 所以最大的太阳能板面积为25。
 *
 *
 */
public class 太阳能板最大面积 {

    /**
     * 本题认为给出的高度都不相同
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long[] heights = Arrays.stream(scanner.nextLine().split(",")).mapToLong(Long::parseLong).toArray();

        int l = 0;
        int r = heights.length -1;

        long maxArea = 0;

        while (l<r){
            long lHeight = heights[l];
            long rHeight = heights[r];

            if(lHeight > rHeight){
                maxArea = Math.max(maxArea, (r-l)*rHeight);
                r--;
            }else {
                maxArea = Math.max(maxArea, (r-l)*lHeight);
                l++;
            }

        }
        System.out.println(maxArea);
    }
}
