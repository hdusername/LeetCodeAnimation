import java.util.*;

/**
 * 如果3个正整数(a,b,c)满足a^2 + b^2 = c^2的关系，则称(a,b,c)为勾股数（著名的勾三股四弦五），
 * 为了探索勾股数的规律，我们定义如果勾股数(a,b,c)之间两两互质（即a与b，a与c，b与c之间均互质，没有公约数），则其为勾股数元组（例如(3,4,5)是勾股数元组，(6,8,10)则不是勾股数元组）。
 * 请求出给定范围[N,M]内，所有的勾股数元组。
 *
 * 输入描述：
 * 起始范围N，1 <= N <= 10000
 * 结束范围M，N < M <= 10000
 *
 * 输出描述：
 * 1. a,b,c请保证a < b < c,输出格式：a b c；
 * 2. 多组勾股数元组请按照a升序，b升序，最后c升序的方式排序输出；
 * 3. 给定范围中如果找不到勾股数元组时，输出”NA“。
 *
 *
 */
public class 勾股数元组 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //开始数字
        int n = scanner.nextInt();
        //结束数字
        int m = scanner.nextInt();

        ArrayList<String> ans = new ArrayList<>();
        int range = m-n+1;
        int[] ranges = new int[range];
        HashSet<Integer> hashSet = new HashSet<>();

        int index =0;
        for(int i=n; i<=m; i++){
            int result = (int)Math.pow(i, 2);
            ranges[index++] = result;
            hashSet.add(result);
        }

        for(int i=0; i<ranges.length; i++){
            for (int j=i+1; j< ranges.length; j++){
                int c_pow2 = ranges[i]+ranges[j];
                if(hashSet.contains(c_pow2)){
                    int a = (int)Math.sqrt(ranges[i]);
                    int b = (int)Math.sqrt(ranges[j]);
                    int c = (int)Math.sqrt(c_pow2);

                    //找到了一组勾股数，接下来要判断是否两两互质
                    boolean ifa = getResult(a,b);
                    boolean ifb = getResult(a,c);
                    boolean ifc = getResult(b,c);

                    if(ifa && ifb && ifc){
                        StringJoiner stringJoiner = new StringJoiner(" ");
                        stringJoiner.add(a+"");
                        stringJoiner.add(b+"");
                        stringJoiner.add(c+"");
                        ans.add(stringJoiner.toString());
                    }
                }
            }
        }
        if(ans.size()==0){
            System.out.println("NA");
        }else {
            for (String s : ans) {
                System.out.println(s);
            }
        }
    }

    /**
     * 判断两个数是否互质，使用辗转相除法，也就是说a/b=c余d，a与b的最大公因数，就是b与d的最大公因数，这是一个定理
     * @param a
     * @param b
     * @return
     */
    private static boolean getResult(int a, int b){

        //等于0的话说明已经找到最大公约数了
        //如果mod=a%b中b等于1时，才得到余数mod为0，说明没有最大公约数，两个数互质
        //如果b=1时余数才等于0，在后面的逻辑中1赋值给了a，所以后面判断a是否等于1返回两个数是否互质
        while (b>0){
            int mod=a%b;
            a=b;
            b=mod;

        }
        return a==1;
    }
}
//标准答案
//import java.util.*;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int m = sc.nextInt();
//
//        Integer[] arr = new Integer[m - n + 1];
//        for (int i = n; i <= m; i++) {
//            arr[i - n] = i * i;
//        }
//
//        HashSet<Integer> set = new HashSet<>();
//        Collections.addAll(set, arr);
//
//        ArrayList<String> res = new ArrayList<>();
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = i + 1; j < arr.length; j++) {
//                int sum = arr[i] + arr[j];
//
//                if (set.contains(sum)) {
//                    int a = (int) Math.sqrt(arr[i]);
//                    int b = (int) Math.sqrt(arr[j]);
//                    int c = (int) Math.sqrt(sum);
//
//                    if (isRelativePrime(a, b) && isRelativePrime(a, c) && isRelativePrime(b, c)) {
//                        res.add(a + " " + b + " " + c);
//                    }
//                }
//            }
//        }
//
//        if (res.isEmpty()) {
//            System.out.println("NA");
//        } else {
//            res.forEach(System.out::println);
//        }
//    }
//
//    // 判断两个数是否互质，辗转相除
//    public static boolean isRelativePrime(int x, int y) {
//        while (y > 0) {
//            int mod = x % y;
//            x = y;
//            y = mod;
//        }
//
//        return x == 1;
//    }
//}
