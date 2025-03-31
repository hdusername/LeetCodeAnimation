/**
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文串。
 *
 * 返回符合要求的 最少分割次数 。
 *
 * 示例 1：
 *
 * 输入：s = "aab"
 * 输出：1
 * 解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 */
public class 分割回文串II {

    public static void main(String[] args) {
        String s = "leet";
        //System.out.println(i);
        int i = getResult(s, 0);
        System.out.println(i);
    }

    public static int getResult(String s, int num) {

        if(isHWStr(s)){
            return num;
        }

        for(int i=s.length()-1; i>=1;  i--){
            String subBeforeStr = s.substring(0, i);
            String subAfterStr = s.substring(i);

            if(isHWStr(subBeforeStr)){
                if(isHWStr(subAfterStr)){
                    return num+1;
                }else {
                    return getResult(subAfterStr, num+1);
                }
            }

        }
        return 0;
    }

    public static boolean isHWStr(String s){
        int left =0;
        int right = s.length()-1;

        while (left<right){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
