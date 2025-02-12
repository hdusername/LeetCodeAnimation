import java.util.Arrays;
import java.util.Scanner;

/**
 * 小强正在参加《密室逃生》游戏，当前关卡要求找到符合给定 密码K（升序的不重复小写字母组成） 的箱子，并给出箱子编号，箱子编号为 1 ~ N 。
 * 每个箱子中都有一个 字符串s 字符串由大写字母、小写字母、数字、标点符号空格组成，需要在这些字符串中找到所有的字母，忽略大小写后排列出对应的密码串，并返回匹配密码的箱子序号。
 * 提示：满足条件的箱子不超过1个。
 *
 * 输入描述：
 * 第一行为 key 的字符串，第二行为箱子 boxes，为数组样式，以空格分隔
 * 箱子 N 数量满足 1 ≤ N ≤ 10000
 * s 长度满足 0 ≤ s.length ≤ 50
 * 密码为仅包含小写字母的升序字符串，且不存在重复字母
 * 密码 K 长度1 ≤ K.length ≤ 26
 *
 * 输出描述：
 * 返回对应箱子编号
 * 如不存在符合要求的密码箱，则返回 -1
 *
 * 备注：
 * 箱子中字符拼出的字符串与密码的匹配忽略大小写，且要求与密码完全匹配，如：
 * 密码 abc 匹配 aBc，但是密码 abc 不匹配 abcd
 *
 * 用例输入：
 * abc
 * s,sdf134 A2c4b
 *
 * 输出：
 * 2
 *
 * 解释：
 * 第 2 个箱子中的 Abc ，符合密码 abc。
 */
public class 寻找关键钥匙 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String key = scanner.nextLine();

        String[] boxs = scanner.nextLine().split(" ");

        int ans = -1;
        for(int i=0; i<boxs.length; i++){
            String box = boxs[i];
            //如果字符中有[]这种字符的话，replaceAll("[^a-zA-z]会失效，所以要先去掉这种字符
            String s = box.replace("[","").replace("]","").replaceAll("[^a-zA-z]", "").toLowerCase();
            if(!s.isEmpty()){
                char[] charArray = s.toCharArray();
                Arrays.sort(charArray);
                String s1 = new String(charArray);
                if(s1.equals(key)){
                    ans=i+1;
                }
            }
        }
        System.out.println(ans);
    }
}
