import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 给定一段英文文章片段，由若干单词组成，单词间以空格间隔，单词下标从0开始。
 *  请翻转片段中指定区间的单词顺序并返回翻转后的内容。
 *  例如给定的英文文章片段为"I am a developer"，翻转区间为[0,3]，则输出“developer a am I”。
 *  reverseWords(String s, int start, int end)
 *
 *  输入描述：
 *  使用换行隔开三个参数
 *  第一个参数为英文文章内容即英文字符串
 *  第二个参数为待翻转内容起始单词下标
 *  第三个参数为待翻转内容最后一个单词下标
 *
 *  输出描述：
 *  翻转后的英文文章片段所有单词之间以一个半角空格分隔进行输出。
 *
 *  备注：
 *  英文文章内容首尾无空格
 *
 *  用例输入：
 *  I am a developer.
 *  1
 *  2
 *
 *  输出：
 *  I a am developer.
 *
 *  输入：
 *  hello world
 *  -1
 *  1
 *
 *  输出：
 *  world hello
 *
 *  解释：
 *  下标小于0时，从第一个单词开始
 *
 *  输入：
 *  I am a developer
 *  0
 *  5
 *
 * 输出：
 * developer a am I
 *
 * 解释：
 * 下标大于实际单词个数，则按最大下标算
 *
 * 输入：
 * I am a developer
 * -2
 * -1
 *
 * 输出：
 * I am a developer
 *
 * 解释：
 * 翻转区间无效时，不做翻转
 */
public class 按单词下标区间翻转文章内容 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String wordStr = scanner.nextLine();
        String[] words = wordStr.split(" ");

        int startIdx = Integer.parseInt(scanner.nextLine());
        int endIdx = Integer.parseInt(scanner.nextLine());

        startIdx = Math.max(0, startIdx);
        endIdx = Math.min(words.length-1, endIdx);

        if(startIdx>=endIdx){
            System.out.println(wordStr);
        }else {
            while (startIdx<endIdx){
                String tmpStr = words[startIdx];
                words[startIdx] = words[endIdx];
                words[endIdx] = tmpStr;
                startIdx++;
                endIdx--;
            }
            StringJoiner stringJoiner = new StringJoiner(" ");
            Arrays.stream(words).forEach(a->stringJoiner.add(a));
            System.out.println(stringJoiner);
            String.join(",", words);
        }
    }
}
