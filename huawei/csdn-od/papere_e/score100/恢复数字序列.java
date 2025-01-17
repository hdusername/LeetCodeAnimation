import java.util.Scanner;

/**
 * 对于一个连续正整数组成的序列，可以将其拼接成一个字符串，再将字符串里的部分字符打乱顺序。如序列8 9 10 11 12，拼接成的字符串为89101112，
 * 打乱一部分字符后得到90811211，原来的正整数10就被拆成了0和1。
 * 现给定一个按如上规则得到的打乱字符的字符串，请将其还原成连续正整数序列，并输出序列中最小的数字。
 *
 * 输入描述：
 * 输入一行，为打乱字符的字符串和正整数序列的长度，两者间用空格分隔，字符串长度不超过200，正整数不超过1000，保证输入可以还原成唯一序列。
 *
 * 输出描述：
 * 输出一个数字，为序列中最小的数字。
 *
 */
public class 恢复数字序列 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] split = scanner.nextLine().split(" ");
        String wrongOrderStr = split[0];
        int k = Integer.parseInt(split[1]);

        //存储乱序字符串字符数量
        int[] wrongOrders = new int[10];
        //存储欢动窗口内字符串的字符数量
        int[] judgeOrders = new int[10];


        for(int i=0; i<wrongOrderStr.length(); i++){
            wrongOrders[wrongOrderStr.charAt(i)-'0']++;
        }

        for(int n=1; n<=k; n++) {
            //统计滑动窗口内数字个数，先统计前五个数字
            increaseCharNum(judgeOrders, n);

        }
        //数组的equals方法比较的是引用而不是内容，所以要自己实现一个比较数组内容的方法
        if(equals(wrongOrders,judgeOrders)){
            System.out.println(1);
            return;
        }

        for (int i=k+1; i<=100000; i++){
            increaseCharNum(judgeOrders, i);
            decreaseCharNum(judgeOrders, i-k);

            if(equals(wrongOrders,judgeOrders)){
                System.out.println(i-k+1);
                return;
            }
        }
        System.out.println(-1);

    }

    private static void decreaseCharNum(int[] judgeOrders, int n) {
        while (n>0){
            judgeOrders[n%10]--;
            n/=10;
        }
    }

    private static void increaseCharNum(int[] judgeOrders, int n) {
        while (n>0){
            judgeOrders[n%10]++;
            n/=10;
        }

    }

    private static boolean equals(int a[], int b[]){
        for(int i=0; i<a.length; i++){
            if(a[i]!=b[i]){
                return false;
            }
        }
        return true;
    }
}
