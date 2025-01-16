import java.util.Scanner;

/**
 * 3020年，空间通信集团的员工人数突破20亿人，即将遇到现有工号不够用的窘境。
 * 现在，请你负责调研新工号系统。继承历史传统，新的工号系统由小写英文字母（a-z）和数字（0-9）两部分构成。
 * 新工号由一段英文字母开头，之后跟随一段数字，比如”aaahw0001″,”a12345″,”abcd1″,”a00″。
 * 注意新工号不能全为字母或者数字,允许数字部分有前导0或者全为0。
 * 但是过长的工号会增加同事们的记忆成本，现在给出新工号至少需要分配的人数X和新工号中字母的长度Y，求新工号中数字的最短长度Z。
 *
 * 输入描述：
 * 一行两个非负整数 X Y，用数字用单个空格分隔。
 * 0 < X ≤ 2^50 – 1
 * 0 < Y ≤ 5
 *
 * 输出描述：
 * 输出新工号中数字的最短长度Z
 *
 */
public class 工号不够用了怎么办 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //工号需要分配的个数
        long totalNum = scanner.nextLong();
        //字母的个数
        int charNum = scanner.nextInt();

        //最少要有1位数字,如果输入为1 1，那么其实1位字母就够了，但是因为最少要一位数字，所以这里要和1取最大值
        int number = Math.max(1,(int)Math.ceil(Math.log10(totalNum/(Math.pow(26, charNum)))));

        System.out.println(number);
    }
}
