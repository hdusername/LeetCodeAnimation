import java.util.Scanner;

/**
 * 小明从糖果盒中随意抓一把糖果，每次小明会取出一半的糖果分给同学们。
 * 当糖果不能平均分配时，小明可以选择从糖果盒中（假设盒中糖果足够）取出一个糖果或放回一个糖果。
 * 小明最少需要多少次（取出、放回和平均分配均记一次），能将手中糖果分至只剩一颗。
 *
 * 输入描述：
 * 抓取的糖果数（<10000000000）：15
 *
 * 输出描述；
 * 最少分至一颗糖果的次数：5
 */
public class 分糖果 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long cnadyNum = scanner.nextLong();

        long num = getNum(cnadyNum, 0);
        System.out.println(num);

    }
    public static long getNum(long candyNum, long divideNum){
        if(candyNum==1){
            return divideNum;
        }
       if(candyNum%2==0){
           //平均分配次数加1
          return getNum(candyNum/2, divideNum+1);
       }else {
         return   Math.min(getNum((candyNum+1), divideNum+1),getNum((candyNum-1), divideNum+1));
       }
    }
}
