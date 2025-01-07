import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 给定一个字符串，，只包含大写字母，求在包含同一字母的子串中，长度第 k 长的子串的长度，相同字母只取最长的那个子串。
 *
 * 输入描述:
 * 第一行有一个子串(1<长度<=100)，只包含大写字母。
 * 第二行为 k的值
 *
 * 输出描述：
 * 输出连续出现次数第k多的字母的次数。
 */
public class 连续字母长度 {

    public static void main(String[] args) {
        //下面这段被注释的代码是有问题的，因为for循环中的逻辑当出现与之前重复字母的时候，会把之前的数量覆盖掉，例如AAAABBA取
        //k取2，也就是取第二多出现的应该是B字母，输出2，但是下面这段逻辑会输出1，因为后面的一个A覆盖了前面的4个A，导致后面的A
        //出现的次数是1作为了第k次出现的数字
//        Scanner scanner = new Scanner(System.in);
//        String s = scanner.nextLine();
//        int k = Integer.parseInt(scanner.nextLine());
//        HashMap<Character, Integer> resultMap = new HashMap<>();
//
//        char beforeC = s.charAt(0);
//        resultMap.put(beforeC,1);
//
//        for(int i=1;i<s.length();i++){
//            char curC = s.charAt(i);
//            if(curC==beforeC){
//                resultMap.put(curC,resultMap.getOrDefault(curC,0)+1);
//            }else {
//                resultMap.put(curC,1);
//                beforeC=curC;
//            }
//        }
//
//        Integer[] array = resultMap.values().toArray(new Integer[0]);
//        Arrays.sort(array,(a,b)->b-a);
//        if(k>array.length||k<=0){
//            System.out.println(-1);
//        }else {
//            System.out.println(array[k-1]);
//        }

        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int k = sc.nextInt();
        System.out.println(getResult(s, k));
    }
    public static int getResult(String s, int k) {
        if (k <= 0) return -1;

        s += "0";

        HashMap<Character, Integer> count = new HashMap<>();

        char b = s.charAt(0);
        int len = 1;

        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);

            if (b == c) {
                len++;
            } else {
                if (!count.containsKey(b) || count.get(b) < len) {
                    count.put(b, len);
                }
                len = 1;
                b = c;
            }
        }

        Integer[] arr = count.values().toArray(new Integer[0]);

        if (k > arr.length) return -1;
        else {
            Arrays.sort(arr, (x, y) -> y - x);
            return arr[k - 1];
        }
    }

}
