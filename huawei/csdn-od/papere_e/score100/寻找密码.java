import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * 小王在进行游戏大闯关，有一个关卡需要输入一个密码才能通过，密码获得的条件如下：
 * 在一个密码本中，每一页都有一个由 26 个小写字母组成的若干位密码，每一页的密码不同，需要从这个密码本中寻找这样一个最长的密码，从它的末尾开始依次去掉一位得到的新密码也在密码本中存在。
 * 请输出符合要求的密码，如果有多个符合要求的密码，则返回字典序最大的密码。
 * 若没有符合要求的密码，则返回空字符串
 *
 * 输入描述：
 * 密码本由一个字符串数组组成，不同元素之间使用空格隔开，每一个元素代表密码本每一页的密码。
 *
 * 输出描述
 * 一个字符串
 *
 * 备注：
 * 1 ≤ 密码本的页数 ≤ 10^5
 * 1 ≤ 每页密码的长度 ≤ 10^5
 *
 * 用例输入：
 * h he hel hell hello
 *
 * 输出：
 * hello
 *
 * 解释：
 * "hello"从末尾依次去掉一位得到的 "hell"，"hel"，"he"和"h"在密码本中都存在。
 */
public class 寻找密码 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inputs = scanner.nextLine().split(" ");

        //做比较用
        TreeSet<String> treeSet = new TreeSet<>(Arrays.asList(inputs));

        Arrays.sort(inputs, (a, b)->{
            if(a.length()!=b.length()){
                return b.length()-a.length();
            }else {
                return b.compareTo(a);
            }
        });

        String ans = "";
        out: for(int i=0; i<inputs.length; i++){
            String s = inputs[i];
            //本题的含义是如果输入是a b c d e f g，输出为g，所以要取到j=s.length，才可以取到这一位
            for(int j=s.length(); j>0; j--){
                String subS = s.substring(0, j);
                if(!treeSet.contains(subS)){
                    continue out;
                }
                if(j==1){
                    ans=s;
                    break out;
                }
            }

        }
        System.out.println(ans);
    }
}
