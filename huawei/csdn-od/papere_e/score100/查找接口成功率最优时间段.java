import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 服务之间交换的接口成功率作为服务调用关键质量特性，某个时间段内的接口失败率使用一个数组表示，
 * 数组中每个元素都是单位时间内失败率数值，数组中的数值为0~100的整数，
 * 给定一个数值(minAverageLost)表示某个时间段内平均失败率容忍值，即平均失败率小于等于minAverageLost，
 * 找出数组中最长时间段，如果未找到则直接返回NULL。
 *
 * 输入描述：
 * 输入有两行内容，第一行为{minAverageLost}，第二行为{数组}，数组元素通过空格(” “)分隔，
 * minAverageLost及数组中元素取值范围为0~100的整数，数组元素的个数不会超过100个。
 *
 * 输出描述：
 * 找出平均值小于等于minAverageLost的最长时间段，输出数组下标对，格式{beginIndex}-{endIndx}(下标从0开始)，
 * 如果同时存在多个最长时间段，则输出多个下标对且下标对之间使用空格(” “)拼接，多个下标对按下标从小到大排序。
 *
 */
public class 查找接口成功率最优时间段 {

    /**
     * 此问题也可以使用前缀和的方式求解
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int avgNum = Integer.parseInt(scanner.nextLine());
        int[] arrays = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] preSum = new int[arrays.length + 1];

        ArrayList<int[]> ansList = new ArrayList<>();

        int maxLen = 0;

        for(int i=1; i<preSum.length; i++){
            //preSum的角标和arrays的角标含义不同， preSum[i-1]表示在arrays数组中i-1位置的前一个元素及之前元素之和
            preSum[i] = preSum[i-1]+arrays[i-1];
        }

        //最长连续子序列中用的i=1这种方式，也可以，用下面这种方式也可以
        for(int i=0; i<preSum.length; i++){
            for(int j=i+1; j<preSum.length; j++){
                if((j-i)*avgNum>=preSum[j]-preSum[i]){
                    if(j-i>maxLen){
                        ansList.clear();
                        ansList.add(new int[]{i,j-1});
                        maxLen = j-i;
                    }else if(j-i==maxLen){
                        ansList.add(new int[]{i,j-1});
                    }
                }
            }
        }

        if(ansList.size()==0){
            System.out.println("NULL");
            return;
        }
        ansList.sort((a,b)->a[0]-b[0]);

        StringJoiner stringJoiner = new StringJoiner(" ");
        ansList.forEach(a->stringJoiner.add(a[0]+"-"+a[1]));
        System.out.println(stringJoiner);
    }
}
