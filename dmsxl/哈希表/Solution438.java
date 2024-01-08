import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 *
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 */
public class Solution438 {

    public static void main(String[] args) {
        findAnagrams("aaab","bbba");
    }

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<Integer>();
        int sLen = s.length();
        int pLen = p.length();

        if(sLen < pLen) {
            return res;
        }
        //表示字母出现次数差距
        //count[x] = 0  表示 s与p中字母x出现次数相同 都出现了n次(n>=0)
        //count[x] = n  表示 在s中字母x出现次数比p多 多出现了n次(n>0)
        //count[x] = -n 表示 在s中字母x出现次数比p少 少出现了n次(n>0)
        int[] count = new int[26];

        for(int i = 0; i < pLen; i++){
            ++count[s.charAt(i)-'a'];
            --count[p.charAt(i)-'a'];
        }
        //表示字母差异个数
        int differ = 0;
        for(int j = 0; j < 26; j++){
            if(count[j]!=0)++differ;
        }

        if(differ==0){
            res.add(0);
        }
        //向右滑动
        for(int i = 0; i < sLen - pLen; i++){
            //缩减时只考虑count[x]==1与count[x]==0的情况
            //因为缩减时字母x减少，count[x]会减去1
            //(1)count[x]==1时(次数差距1次，不相同)
            //count[x]==0 -> 次数相同 -> 不相同变相同，字母差异个数减少1 -> differ--

            //(2)count[x]==0时(次数相同)
            //count[x]==-1 -> 次数差距变为1次->相同变不相同 ，字母差异个数增加1 -> differ++

            //(3)count[x]==-1时(次数不相同) -> count[x]==-2 次数还是不相同-> 字母差异数不变

            //(4)count[x]==2时(次数不相同)  -> count[x]==1 次数还是不相同-> 字母差异数不变


            //左缩减一位，i
            if (count[s.charAt(i) - 'a'] == 1) {
                //窗口中s子串左边减少一个s[i]的数量(把原来多出来的1个s[i]去掉，变得相同)
                //两个字符串字母差距缩小
                --differ;
            } else if (count[s.charAt(i) - 'a'] == 0) {
                //窗口中s子串左边减少一个s[i]的数量(把原来相同数量的s[i]的减少了1个，数量变得不相同)
                //两个字符串字母差距增大
                ++differ;
            }
            //窗口中s子串左边减少一个字母s[i]
            --count[s.charAt(i) - 'a'];

            //添加时只考虑count[x]==-1与count[x]==0的情况，原因分析与缩减时类似
            //右添加一位，i+pLen
            if (count[s.charAt(i + pLen) - 'a'] == -1) {
                //窗口中s子串右边增加一个s[i+pLen]的数量(把原来缺少的1个s[i]加上，数量变得相同)
                //两个字符串字母差距缩小
                --differ;
            } else if (count[s.charAt(i + pLen) - 'a'] == 0) {
                //窗口中s子串右边增加一个s[i+pLen]的数量(把原来相同数量的s[i]多加了1个，变得不相同)
                //两个字符串字母差距增大
                ++differ;
            }
            //窗口中s子串右边增加一个字母s[i+pLen]
            ++count[s.charAt(i + pLen) - 'a'];
            //两个字符串字母差距为0
            if (differ == 0) {
                res.add(i + 1);
            }
        }
        return res;
    }
}
