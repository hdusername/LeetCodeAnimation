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

//标准答案
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Scanner;
//
//public class Main {
//  public static void main(String[] args) {
//    Scanner sc = new Scanner(System.in);
//
//    int n = sc.nextInt();
//    int m = sc.nextInt();
//
//    System.out.println(getResult(n, m));
//  }
//
//  public static long getResult(int n, int m) {
//	// 若输入不合法，返回-1
//    if (n < 3 || n > 7 || m < 0) return -1;
//
//    // 提前计算好0~9的N次方, 避免后续进行重复计算
//    HashMap<Character, Integer> powN = new HashMap<>();
//    for (int i = 0; i <= 9; i++) {
//		// 将整型0~9转化字符'0'~'9'，即让i+'0'即可
//		powN.put((char) (i + '0'), (int) Math.pow(i, n));
//	}
//
//    // 最小的N位数
//    int min = (int) Math.pow(10, n - 1);
//	// 最大的N位数
//    int max = (int) Math.pow(10, n);
//
//    // 记录当前水仙花数
//    long ans = 0;
//
//	// 记录当前水仙花数是第几个
//    int idx = 0;
//
//    for (int num = min; num < max; num++) {
//	  // 记录num各位数字的N次方之和
//	  int sum = 0;
//
//	  // 遍历num的每一位数字
//	  String str = num + "";
//	  for(int i=0; i<n; i++) {
//		  sum += powN.get(str.charAt(i));
//	  }
//
//      // 判断num是否为水仙花数
//      if (sum == num) {
//        ans = num;
//		// 如果num刚好是N位数的第m个水仙花数，则直接返回，否则继续查找
//        if (idx++ == m) return ans;
//      }
//    }
//
//    // 若m大于水仙花数的个数，返回最后一个水仙花数和m的乘积
//    return ans * m;
//  }
//}


//标准答案2
//import java.util.HashMap;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        int n = sc.nextInt();
//        int m = sc.nextInt();
//
//        if (n < 3 || n > 7 || m < 0) {
//            System.out.println(-1);
//            return;
//        }
//
//        HashMap<Integer, int[]> dic = new HashMap<>();
//        dic.put(3, new int[]{153, 370, 371, 407});
//        dic.put(4, new int[]{1634, 8208, 9474});
//        dic.put(5, new int[]{54748, 92727, 93084});
//        dic.put(6, new int[]{548834});
//        dic.put(7, new int[]{1741725, 4210818, 9800817, 9926315});
//
//        if (m < dic.get(n).length) {
//            System.out.println(dic.get(n)[m]);
//        } else {
//            System.out.println((long) dic.get(n)[dic.get(n).length - 1] * m);
//        }
//    }
//}
