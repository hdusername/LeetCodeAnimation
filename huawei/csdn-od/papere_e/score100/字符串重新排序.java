import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 给定一个字符串 s，s 包括以空格分隔的若干个单词，请对 s 进行如下处理后输出：
 * 单词内部调整：对每个单词字母重新按字典序排序
 * 单词间顺序调整：
 * 统计每个单词出现的次数，并按次数降序排列
 * 次数相同，按单词长度升序排列
 * 次数和单词长度均相同，按字典升序排列
 *
 * 请输出处理后的字符串，每个单词以一个空格分隔。
 *
 * 输入描述：
 * 一行字符串，每个字符取值范围：[a-zA-z0-9] 以及空格，字符串长度范围：[1，1000]
 *
 * 输出描述：
 * 重新排序后的字符串，每个单词间隔1个空格，且首尾无空格
 *
 * 输入：
 * This is an apple
 * 输出：
 * an is This aelpp
 *
 * 输入：
 * My sister is in the house not in the yard
 * 输出：
 * in in eht eht My is not adry ehosu eirsst
 */
public class 字符串重新排序 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words = scanner.nextLine().split(" ");

        StringJoiner joiner = new StringJoiner(" ");
        //得到每个单词都按顺序排列好的数组
        String[] sortedWords = new String[words.length];
        HashMap<String, Integer> map = new HashMap<>();
        for(int i=0; i<words.length; i++){
            sortedWords[i] = sortWord(words[i]);
            //这里要注意要放排好序的单词，不然如果放words中的单词的话，后续排序时是找不到对应单词的
            map.put(sortedWords[i], map.getOrDefault(sortedWords[i], 0)+1);
        }

        Arrays.stream(sortedWords).sorted((a,b)->{
            if(map.get(a)!=map.get(b)){
                return map.get(b)-map.get(a);
            }else {
                if(a.length() != b.length()){
                    return a.length() - b.length();
                }else {
                    return a.compareTo(b);
                }
            }
        }).forEach(a->joiner.add(a));

        System.out.println(joiner);


    }

    /**
     * 将传入字符串按照字母顺序排序
     * @param word
     * @return
     */
    private static String sortWord(String word) {

        char[] charArray = word.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }
}
