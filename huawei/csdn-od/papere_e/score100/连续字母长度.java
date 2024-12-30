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
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int k = Integer.parseInt(scanner.nextLine());
        HashMap<Character, Integer> resultMap = new HashMap<>();

        char beforeC = s.charAt(0);
        resultMap.put(beforeC,1);

        for(int i=1;i<s.length();i++){
            char curC = s.charAt(i);
            if(curC==beforeC){
                resultMap.put(curC,resultMap.getOrDefault(curC,0)+1);
            }else {
                resultMap.put(curC,1);
                beforeC=curC;
            }
        }

        Integer[] array = resultMap.values().toArray(new Integer[0]);
        Arrays.sort(array,(a,b)->b-a);
        if(k>array.length||k<=0){
            System.out.println(-1);
        }else {
            System.out.println(array[k-1]);
        }


    }
}
