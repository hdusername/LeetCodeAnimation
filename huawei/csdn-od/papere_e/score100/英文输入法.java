import java.util.Collections;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.TreeSet;

/**
 * 主管期望你来实现英文输入法单词联想功能。
 *
 * 需求如下：
 * 依据用户输入的单词前缀，从已输入的英文语句中联想出用户想输入的单词，按字典序输出联想到的单词序列，
 * 如果联想不到，请输出用户输入的单词前缀。
 *
 *注意：
 * 英文单词联想时，区分大小写
 * 缩略形式如”don’t”，判定为两个单词，”don”和”t”
 * 输出的单词序列，不能有重复单词，且只能是英文单词，不能有标点符号
 *
 * 输入描述：
 * 输入为两行。
 * 输入为两行。首行输入一段由英文单词word和标点符号组成的语句str；
 * 接下来一行为一个英文单词前缀pre。
 * 0 < word.length() <= 20
 * 0 < str.length <= 10000
 * 0 < pre <= 20
 *
 * 输出描述：
 * 输出符合要求的单词序列或单词前缀，存在多个时，单词之间以单个空格分割
 */
public class 英文输入法 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String preStr = scanner.nextLine();

        StringJoiner stringJoiner = new StringJoiner(" ");

        TreeSet<String> treeSet = new TreeSet<>();
        String[] splits = str.split("[^a-zA-Z]");

        for(int i=0;i<splits.length;i++){
            if(splits[i].startsWith(preStr)){
                treeSet.add(splits[i]);

            }
        }
        treeSet.stream().forEach(a->stringJoiner.add(a));
        if(stringJoiner.length()==0){
            stringJoiner.add(preStr);
        }
        System.out.println(stringJoiner);
    }
}
