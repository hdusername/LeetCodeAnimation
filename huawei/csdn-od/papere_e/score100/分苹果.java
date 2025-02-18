import java.util.Arrays;
import java.util.Scanner;

/**
 * A、B两个人把苹果分为两堆
 * A希望按照他的计算规则等分苹果，他的计算规则是按照二进制加法计算，并且不计算进位 12+5=9（1100 + 0101 = 9），
 * B的计算规则是十进制加法，包括正常进位，B希望在满足A的情况下获取苹果重量最多。
 * 输入苹果的数量和每个苹果重量，输出满足A的情况下B获取的苹果总重量。
 * 如果无法满足A的要求，输出-1。
 *
 * 输入描述：
 * 输入第一行是苹果数量：3
 * 输入第二行是每个苹果重量：3 5 6
 *
 * 输出描述：
 * 输出第一行是B获取的苹果总重量：11
 *
 * 备注：
 * 数据范围
 * 1 ≤ 总苹果数量 ≤ 20000
 * 1 ≤ 每个苹果重量 ≤ 10000
 *
 */
public class 分苹果 {
    /**
     * 功能：这个题目中如果按位异或平分的话，说明分成的两堆值是一样的才是平分，这两个分配出来的相同的值进行异或的话值一定为0，
     * 因为异或的定义就是不同为1，相同为0。这道题的解法就是所有值异或如果等于0，说明存在两堆按位异或等于0的值，然后减去
     * 这些值中的最小值，就可以得出最大堆的值了
     * @param args
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int appleNums = scanner.nextInt();
        int[] apples = new int[appleNums];

        //插入苹果总重量
        for(int i=0; i<appleNums; i++){
            apples[i] = scanner.nextInt();
        }

        int[] sortedApples = Arrays.stream(apples).sorted().toArray();

        //临时存储按位异或的数
        int tempWeight = apples[0];
        //总重量
        int allWeight = 0;
        allWeight+=tempWeight;

        for(int i=1; i<appleNums; i++){
            tempWeight^=  apples[i] ;
            allWeight+=apples[i];
        }

        if(tempWeight ==0){
            //满足了按位异或的条件，也就是说可以按照A的要求分成相同的两堆，减去最小的，B得到的就是最大的
            System.out.println(allWeight-sortedApples[0]);
        }else {
            System.out.println(-1);
        }
    }
}
