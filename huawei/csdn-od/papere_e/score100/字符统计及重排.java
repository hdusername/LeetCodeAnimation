import java.util.HashMap;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 给出一个仅包含字母的字符串，不包含空格，统计字符串中各个字母（区分大小写）出现的次数，并按照字母出现次数从大到小的顺序。
 * 输出各个字母及其出现次数。如果次数相同，按照自然顺序进行排序，且小写字母在大写字母之前。
 *
 * 输入描述：
 * 输入一行，为一个仅包含字母的字符串。
 *
 * 输出描述：
 * 按照字母出现次数从大到小的顺序输出各个字母和字母次数，用英文分号分隔，注意末尾的分号；
 */
public class 字符统计及重排 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        HashMap<Character, Integer> map = new HashMap<>();
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            map.putIfAbsent(c,0);
            //统计出各个字符的数量
            map.put(c, map.get(c)+1);
        }

        StringJoiner stringJoiner = new StringJoiner(";");
        map.entrySet().stream().sorted((a, b)-> {

            if(a.getValue()!=b.getValue()){
                return b.getValue()-a.getValue();
            }else {
                //题目中的意思是如果字符数量相同，小写字母要在前，然后在满足这个的前提下，再进行自然排序
                if (Character.isUpperCase(a.getKey()) && Character.isLowerCase(b.getKey())) {
                    //b这个后面的数要排在前面，因为它是小写的，那么也就意味着a和b要换位置，所以要返回大于0的数，因为b.getKey是小写字母，所以b.getKey一定大于a.getKey
                    //所以返回b.getKey() - a.getKey()即可
                    return b.getKey() - a.getKey();
                } else if ( Character.isLowerCase(a.getKey()) && Character.isUpperCase(b.getKey())) {
                    //这里a.getKey()是小写的，所以不用换位置，返回小于0的数即可，其实这里返回b.getKey() - a.getKey()也可以，但是怕与上面的混淆，就直接写-1了
                    return -1;
                } else {
                    //走到这里说明a，b都是大写或者都是小写，所以按照自然顺序排序即可
                    return a.getKey() - b.getKey();

                }

            }
        }).forEach((a)->stringJoiner.add(a.getKey()+":"+a.getValue()));

        System.out.println(stringJoiner+";");
    }
}
