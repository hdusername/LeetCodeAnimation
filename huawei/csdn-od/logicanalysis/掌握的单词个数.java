import java.util.Scanner;

/**
 * 有一个字符串数组 words 和一个字符串 chars。
 * 假如可以用 chars 中的字母拼写出 words 中的某个“单词”（字符串），那么我们就认为你掌握了这个单词。
 * words 的字符仅由 a-z 英文小写字母组成，例如 "abc"
 * chars 由 a-z 英文小写字母和 "?" 组成。其中英文 "?" 表示万能字符，能够在拼写时当作任意一个英文字母。例如："?" 可以当作 "a" 等字母。
 * 注意：每次拼写时，chars 中的每个字母和万能字符都只能使用一次。
 * 输出词汇表 words 中你掌握的所有单词的个数。没有掌握任何单词，则输出0。
 *
 * 输入描述：
 * 第一行：输入数组 words 的个数，记作N。
 * 第二行 ~ 第N+1行：依次输入数组words的每个字符串元素
 * 第N+2行：输入字符串chars
 *
 * 输出描述：
 * 输出一个整数，表示词汇表 words 中你掌握的单词个数
 */
public class 掌握的单词个数 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int wordsNums = Integer.parseInt(scanner.nextLine());

        String[] words = new String[wordsNums];

        int ans = 0;

        //将word放入数组中
        for(int i = 0; i<wordsNums; i++){
            words[i]= scanner.nextLine();
        }

        String chars = scanner.nextLine();

        //统计chars各字符的数量
        int[] charInts = getCharNum(chars);

        for(int i = 0;i<words.length;i++){
            //统计各word中各字符的数量
            int[] wordInts = getCharNum(words[i]);
            int diff = 0;
            for(int x = 0;x<wordInts.length;x++){

                //word比给出的字符数量多，就取0，否则就取差值
                diff += Math.max(wordInts[x]-charInts[x],0);
//                if(wordInts[x]!=0){
//                    if(wordInts[x]<charInts[x]+charInts['?']){
//                        ans++;
//                    }
//                }
            }

            if(diff <= charInts['?']){
                ans++;
            }

        }
        System.out.println(ans);
    }

    private static int[] getCharNum(String s) {

        //存储chars每个字符的个数
        int[] cnts = new int[128];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            cnts[c] += 1;
        }

        return cnts;
    }
}
