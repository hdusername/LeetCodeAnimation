import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 给定一个连续不包含空格的字符串，该字符串仅包含英文小写字母及英文标点符号（逗号、分号、句号），同时给定词库，对该字符串进行精确分词。
 * 说明：
 * 精确分词：字符串分词后，不会出现重叠。即"ilovechina"，不同词库可分割为"i,love,china"，"ilove,china"，不能分割出现重叠的"i,ilove,china"，i 出现重叠
 * 标点符号不成词，仅用于断句
 * 词库：根据外部知识库统计出来的常用词汇例：dictionary = ["i", "love", "china", "lovechina", "ilove"]
 *
 * 分词原则：采用分词顺序优先且最长匹配原则
 * "ilovechina"，假设分词结果 [i,ilove,lo,love,ch,china,lovechina]，则输出 [ilove,china]
 * 错误输出：[i,lovechina]，原因："ilove" > 优先于 "lovechina" 成词
 * 错误输出：[i,love,china]，原因："ilove" > "i"遵循最长匹配原则
 *
 * 输入描述：
 * 第一行输入待分词语句 "ilovechina"
 *  字符串长度限制：0 < length < 256
 * 第二行输入中文词库 "i,love,china,ch,na,ve,lo,this,is,this,word"
 *  词库长度限制：1 < length < 100000
 *
 *  输出描述：
 *  按顺序输出分词结果 "i,love,china"
 */
public class 中文分词模拟器 {
    /**此解法虽然不能通过案例：
     * havegoodtimehave
     * ave,goodtime
     *
     * 标准答案输出：
     * h,ave,goodtime,h,a,v,e
     *
     * 此解法输出；
     * h,ave,goodtime,h,ave
     *
     * 但是仍然认为此题解法没问题，题目描述有误，因为标准答案中认为字典中的单词只能用一次，但是题目中没有提到
     * @param args
     */

    public static void main(String[] args) {
        Scanner scanner =  new Scanner(System.in);
        String[] srcWords = scanner.nextLine().split("[,;.]");
        String[] wordsDic = scanner.nextLine().split(",");
        HashSet<String> dicSet = new HashSet<>(Arrays.asList(wordsDic));
        StringJoiner stringJoiner = new StringJoiner(",");

        for(String s : srcWords) {

            out:
            while (s.length() != 0) {
                int endIdx = s.length();
                while (endIdx >= 1) {
                    String subStr = s.substring(0, endIdx);
                    if (dicSet.contains(subStr) || endIdx == 1) {
                        //如果字典中包含或者最终也没有找到，就添加单个字符
                        stringJoiner.add(subStr);
                        if (s.length() == endIdx) {
                            break out;
                        } else {
                            s = s.substring(endIdx);
                            //在字典中找到了对应的字符串就退出
                            break;
                        }
                    } else {
                        endIdx--;
                    }
                }
            }
        }
        System.out.println(stringJoiner);
    }

    //标准答案
//
//        public static void main(String[] args) {
//            Scanner sc = new Scanner(System.in);
//
//            String[] sentences = sc.nextLine().split("[,.;]");
//            String[] words = sc.nextLine().split("[,.;]");
//
//            System.out.println(getResult(sentences, words));
//        }
//
//        public static String getResult(String[] sentences, String[] words) {
//            // wordSet 记录词库词汇
//            HashSet<String> wordSet = new HashSet<>(Arrays.asList(words));
//
//            // queue记录待分词语句
//            LinkedList<String> queue = new LinkedList<>(Arrays.asList(sentences));
//
//            // ans记录最终分词结果
//            LinkedList<String> ans = new LinkedList<>();
//
//            while (queue.size() > 0) {
//                // 待分词的句子
//                String sentence = queue.removeFirst();
//
//                int r = sentence.length();
//                for (; r > 0; r--) {
//                    // 截取句子 [0,r) 范围子串词汇, 这样的就能实现优先最长匹配，并且由于是必须从0索引开始截取，因此满足了分词顺序优先
//                    String fragment = sentence.substring(0, r);
//
//                    // 若词库中是否存在该子串词汇
//                    if (wordSet.contains(fragment)) {
//                        // 则将对应子串词汇纳入结果
//                        ans.addLast(fragment);
//                        wordSet.remove(fragment); // 我理解词库中每个词汇只能使用一次，因此这里将词库中使用过的词汇移除
//
//                        // 若子串词汇只是句子部分，则句子剩余部分还要继续去词库中查找
//                        if (r < sentence.length()) {
//                            queue.addFirst(sentence.substring(r));
//                        }
//
//                        break;
//                    }
//                }
//
//                // 没有在词库中找到对应子串词汇，则输出单个字母
//                if (r == 0) {
//                    // 注意，这里只放一个字母到结果中，句子剩余部分继续去词库中查找
//                    ans.add(sentence.charAt(0) + "");
//
//                    if (sentence.length() > 1) {
//                        queue.addFirst(sentence.substring(1));
//                    }
//                }
//            }
//
//            StringJoiner sj = new StringJoiner(",");
//            ans.forEach(sj::add);
//
//            return sj.toString();
//        }

}
