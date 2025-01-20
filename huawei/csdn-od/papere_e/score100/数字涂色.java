import java.util.Arrays;
import java.util.Scanner;

/**
 * 疫情过后，希望小学终于又重新开学了，三年二班开学第一天的任务是将后面的黑板报重新制作。
 * 黑板上已经写上了N个正整数，同学们需要给这每个数分别上一种颜色。
 * 为了让黑板报既美观又有学习意义，老师要求同种颜色的所有数都可以被这种颜色中最小的那个数整除。
 * 现在请你帮帮小朋友们，算算最少需要多少种颜色才能给这N个数进行上色。
 *
 * 输入描述：
 * 第一行有一个正整数N，其中。
 * 第二行有N个int型数(保证输入数据在[1,100]范围中)，表示黑板上各个正整数的值。
 *
 * 输出描述：
 * 输出只有一个整数，为最少需要的颜色种数。
 */
public class 数字涂色 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arrays = new int[n];
        for(int i=0; i<n; i++){
            arrays[i] = scanner.nextInt();
        }

        //从小到达排序
        int[] newArrays = Arrays.stream(arrays).sorted().toArray();
        boolean[] colors = new boolean[n];
        int colorCount = 0;

        //这样遍历就会一直取到最小的值，然后向右遍历找到其倍数的数字进行染色
        for(int i=0; i<n; i++){
            if(!colors[i]) {
                //只要发现没有被染色，就要进行染色，然后在本次染色也就是遍历过程中再将其倍数的数字进行染色即可
                colorCount++;

                colors[i]=true;//加不加这个逻辑不影响最后结果
                for (int j = i + 1; j < n; j++) {
                    if (!colors[j]) {
                        //如果还没有染色就判断是否需要染色
                        if (newArrays[j] % newArrays[i] == 0) {
                            //需要染色
                            colors[j] = true;
                        }
                    }
                }

            }
        }
        System.out.println(colorCount);
    }
}
