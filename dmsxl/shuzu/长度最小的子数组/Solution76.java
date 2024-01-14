import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 *
 *
 * 注意：
 *
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 */
public class Solution76 {
    public static void main(String[] args) {
        String s = minWindow("cabwefgewcwaefgcf", "cae");
        System.out.println(s);
    }

    public static String minWindow(String s, String t) {
        int leftInx = 0;
        //int rightInx = 0;

        int minInx= 0;
        int maxInx = 0;
        int lenInx = 0;

        Map<Character,Integer> tMap = new HashMap();
        Map<Character,Integer> sMap = new HashMap();
       // int minAns = Integer.MAX_VALUE;

        for(int i =0; i<t.length(); i++){
            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0)+1);
        }

        for(int rightInx = 0; rightInx<s.length(); rightInx++){
            sMap.put(s.charAt(rightInx), sMap.getOrDefault(s.charAt(rightInx), 0)+1);

            while(checkKey(sMap, tMap)){
                if(rightInx-leftInx<(lenInx==0?Integer.MAX_VALUE:lenInx)){
                    minInx= leftInx;
                    lenInx = rightInx-leftInx+1;
                    maxInx = rightInx+1;
                }
                //minAns = Math.min(rightInx-leftInx+1, minAns);
                sMap.put(s.charAt(leftInx), sMap.getOrDefault(s.charAt(leftInx), 0)-1);
                leftInx++;
            }
        }
        return s.substring(minInx, maxInx);

    }

    /**
     * 功能：校验tMap中的key的值是否小于等于sMap中的值
     * @param sMap
     * @param tMap
     * @return 是：返回true 否返回false
     */
    public static boolean checkKey(Map<Character,Integer> sMap, Map<Character,Integer> tMap){
        Set<Character> characters = tMap.keySet();
        for(Character character: characters) {

            if(tMap.get(character) > (sMap.get(character)==null?0:sMap.get(character))){
                return false;
            }
        }

        return true;
    }
}
