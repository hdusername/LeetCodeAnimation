import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 喊7是一个传统的聚会游戏，N个人围成一圈，按顺时针从1到N编号。
 * 编号为1的人从1开始喊数，下一个人喊的数字为上一个人的数字加1，但是当将要喊出来的数字是7的倍数或者数字本身含有7的话，不能把这个数字直接喊出来，而是要喊”过”。
 * 假定玩这个游戏的N个人都没有失误地在正确的时机喊了”过”，当喊到数字K时，可以统计每个人喊”过”的次数。
 * 现给定一个长度为N的数组，存储了打乱顺序的每个人喊”过”的次数，请把它还原成正确的顺序，即数组的第i个元素存储编号i的人喊”过”的次数。
 *
 * 输入描述：
 * 输入为一行，为空格分隔的喊”过”的次数，注意K并不提供，K不超过200，而数字的个数即为N。
 *
 * 输出描述：
 * 输出为一行，为顺序正确的喊”过”的次数，也由空格分隔。
 */
public class 喊7的次数重排 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] fResults = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int fResultsSum = Arrays.stream(fResults).sum();

        int[] ans = new int[fResults.length];

        //记录第几个人喊
        int person = 0;

        //这个值是乱写的，很大，题目中说k不超过200，指的是fResults中的值不超过200，这里其实可以改为while循环
        for(int i=1; i<=10000; i++){
            if((i+"").contains("7") || i%7==0){
                //只有满足条件才会进入这个判断，其余情况person会一直++的
                ans[person]++;
            }
            if(++person>=fResults.length){
                person=0;
            }

            if( Arrays.stream(ans).sum()==fResultsSum){
                break;
            }

        }

        StringJoiner stringJoiner = new StringJoiner(" ");
        for(int a : ans){
            stringJoiner.add(a+"");
        }

        System.out.println(stringJoiner);
    }


//    Scanner sc = new Scanner(System.in);
//
//    int n = 0; // n 记录一共多少个人
//    int totalGo = 0; // totalGo 记录一共喊了多少次过
//
//    while (sc.hasNextInt()) {
//        totalGo += sc.nextInt();
//        n++;
//    }
//
//    // p[j]表示每个人喊了几次过，初始为0
//    int[] p = new int[n];
//
//    int i = 1; // i 记录喊的数字
//    int j = 0; // j 记录第几个人喊
//
//    while (totalGo > 0) {
//        if (i % 7 == 0 || (i + "").contains("7")) {
//            totalGo--;
//            p[j]++;
//        }
//
//        i++; // 下一个人喊的数字+1
//
//        if (++j >= n) j = 0; // 回到第一个人继续喊
//    }
//
//    StringJoiner sj = new StringJoiner(" ");
//    for (int v : p) {
//        sj.add(v + "");
//    }
//
//    System.out.println(sj);



}
