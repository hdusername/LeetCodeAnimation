/**
 * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的 排列。如果是，返回 true ；否则，返回 false 。
 *
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s1 = "ab" s2 = "eidbaooo"
 * 输出：true
 * 解释：s2 包含 s1 的排列之一 ("ba").
 * 示例 2：
 *
 * 输入：s1= "ab" s2 = "eidboaoo"
 * 输出：false
 */
public class 字符串的排列 {

    public boolean checkInclusion(String s1, String s2) {

        if(s1.length()>s2.length()){
            return false;
        }

        int total = s1.length();
        int s1Length = s1.length();
        int[] s1Nums = new int[128];
        for(int i=0; i<s1Length; i++){
            s1Nums[s1.charAt(i)]++;
        }

        for(int i=0; i<s1Length; i++){
            char s2_char = s2.charAt(i);
            if(s1Nums[s2_char]>0){
                total--;
            }
            s1Nums[s2_char]--;
        }

        if(total==0){
            return true;
        }

        for(int i=1; i<s2.length()-s1Length+1; i++){
            char add = s2.charAt(i+s1Length-1);
            char remove = s2.charAt(i-1);
            //char s2_char = ;

            if(s1Nums[remove]++>=0){
                total++;
            }

            if(s1Nums[add]-->0){
                total--;
            }


//            s1Nums[s2.charAt(i+s1Length-1)]--;
//            s1Nums[s2.charAt(i-1)]++;

            if(total==0){
                return true;
            }
        }
        return false;
    }
}
