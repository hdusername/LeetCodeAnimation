import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * 单词接龙的规则是：
 * 可用于接龙的单词首字母必须要前一个单词的尾字母相同
 * 当存在多个首字母相同的单词时，取长度最长的单词，如果长度也相等，则取字典序最小的单词
 * 已经参与接龙的单词不能重复使用
 * 现给定一组全部由小写字母组成单词数组，并指定其中的一个单词作为起始单词，进行单词接龙，请输出最长的单词串，单词串是单词拼接而成，中间没有空格。
 *
 * 输入描述：
 * 输入的：
 * 第一行为一个非负整数，表示起始单词在数组中的索引 K（0 ≤ K < N）
 * 第二行为一个非负整数，表示单词的个数 N
 * 接下来的 N 行，分别表示单词数组中的单词
 * 备注：
 * 单词个数 N 的取值范围为[1, 20]；
 * 单个单词的长度的取值范围为[1, 30]；
 *
 * 输出描述：
 * 输出一个字符串，表示最终拼接的单词串。
 */
public class 单词接龙 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //索引
        int index = Integer.parseInt(scanner.nextLine());
        //单词数量
        int n = Integer.parseInt(scanner.nextLine());
        String[] words = new String[n];

        for(int i=0; i<n; i++){
            words[i] = scanner.nextLine();
        }
        StringBuffer stringBuffer = new StringBuffer();
        //最末尾的单词，先取第一个
        String lastWord = words[index];
        //这样做相当于设置为空，以后不再取这个值了
        words[index]="";
        stringBuffer.append(lastWord);
        //剩下的进行排序
        List<String> wordList = Arrays.stream(words).sorted((a, b) -> a.length() != b.length() ? b.length() - a.length() : a.compareTo(b)).collect(Collectors.toList());

        int count =0;
        while (count<n){
            if(wordList.get(count).startsWith(lastWord.substring(lastWord.length()-1))){
                stringBuffer.append(wordList.get(count));
                lastWord = wordList.get(count);
                wordList.set(count, "");
                count=0;
                continue;
            }
            count++;
        }

        System.out.println(stringBuffer);
    }
}
