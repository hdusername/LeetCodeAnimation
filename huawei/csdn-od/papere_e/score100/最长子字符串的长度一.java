import java.util.Scanner;

/**
 * 给你一个字符串 s，首尾相连成一个环形，请你在环中找出 'o' 字符出现了偶数次最长子字符串的长度
 *
 * 输入描述：
 * 输入是一个小写字母组成的字符串
 *
 * 输出描述：
 * 输出是一个整数
 *
 * 备注：
 * 1 ≤ s.length ≤ 500000
 * s 只包含小写英文字母
 */
public class 最长子字符串的长度一 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        int oNum = 0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i)=='o'){
                oNum++;
            }
        }

        if(s.length() % oNum==0){
            System.out.println(s.length());
        }else {
            System.out.println(s.length()-1);
        }
    }
}
