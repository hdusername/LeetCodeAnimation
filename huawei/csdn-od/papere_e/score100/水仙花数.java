import java.util.Scanner;

/**
 * 所谓水仙花数，是指一个n位的正整数，其各位数字的n次方和等于该数本身。
 * 例如153是水仙花数，153是一个3位数，并且153 = 1^3 + 5^3 + 3^3。
 *
 * 输入描述：
 * 第一行输入一个整数n，表示一个n位的正整数。n在3到7之间，包含3和7。、
 * 第二行输入一个整数m，表示需要返回第m个水仙花数。
 *
 * 输出描述：
 * 返回长度是n的第m个水仙花数。个数从0开始编号。
 * 若m大于水仙花数的个数，返回最后一个水仙花数和m的乘积。
 *若输入不合法，返回-1。
 */
public class 水仙花数 {
    public static void main(String[] args) {
        //水仙花数最小值为10^(n-1),最大值不到10^n
        //比如三位数，最小值为10^2,最大值不到10^3

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int index = scanner.nextInt();
        //水仙花的个数
        int count = 0;
        //水仙花数
        long powNum = 0;

        if(!(n>=3 && n<=7 && index>=0)){
            System.out.println(-1);
            return;
        }

        int min = (int)Math.pow(10,n-1);
        int max = (int)Math.pow(10,n);

        for(long i=min; i<max; i++){
            long pow = getPow(i, n);
            if(pow == i){
                //为了记录到最后一个水仙花数
                powNum = i;
                count++;
            }

            if(count-1==index){

                break;
            }

        }
        //index>=count表示角标超过就要输出powNum*index
        System.out.println(index>=count?powNum*index:powNum);

    }

    private static long getPow(long i, int n) {

        //可以将0-9的n次方保存到缓存中，可以避免重复计算，这里没有那样做
        long result = 0;
        while (i>0){
            result+=Math.pow(i%10,n);
            i/=10;
        }
        return result;
    }
}
