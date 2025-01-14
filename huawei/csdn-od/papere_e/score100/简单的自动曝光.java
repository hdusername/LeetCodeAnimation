import java.util.Arrays;
import java.util.Scanner;

/**
 * 一个图像有 n 个像素点，存储在一个长度为 n 的数组 img 里，每个像素点的取值范围 [0,255] 的正整数。
 * 请你给图像每个像素点值加上一个整数 k（可以是负数），得到新图 newImg，使得新图 newImg 的所有像素平均值最接近中位值128。
 * 请输出这个整数 k。
 *
 * 输入描述：
 * n 个整数，中间用空格分开
 *
 * 输出描述：
 * 一个整数 k
 *
 * 备注：
 * 1 ≤ n ≤ 100
 * 如有多个整数 k 都满足，输出小的那个 k
 * 新图的像素值会自动截取到 [0,255] 范围。当新像素值 < 0，其值会更改为 0，当新像素值 > 255，其值会更改为 255。例如 newImg = "-1 -2 256"，会自动更改为 "0 0 255"
 *
 */
public class 简单的自动曝光 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] s = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int minK = Integer.MAX_VALUE;
        double minTotalK = Integer.MAX_VALUE;
        //因为新图的所有像素平均值都要接近中位值128，所以每一个数字加上这个k之后都应该接近128
        //因为每一个数字都是[0,255]之间的数字，所以k的范围是-127~128
        for(int k=-127; k<=128; k++){
            double tempTotalK = 0;
            for(int i=0; i<s.length; i++){
                double tmpk = 0;
                if (s[i] + k > 255) {
                    tmpk = 255;
                } else if (s[i] + k < 0 ) {
                    tmpk = 0;
                }else {
                    tmpk =s[i] + k;
                }
                tempTotalK+=tmpk;
            }
            tempTotalK/=s.length;

            double abs = Math.abs(tempTotalK - 128);
            if(abs<minTotalK){
                minTotalK = abs;
                minK = k;
            }
        }
        System.out.println(minK);
    }
}
