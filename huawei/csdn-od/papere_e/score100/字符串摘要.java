import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 给定一个字符串的摘要算法，请输出给定字符串的摘要值
 * 去除字符串中非字母的符号。
 * 如果出现连续字符(不区分大小写) ，则输出：该字符 (小写) + 连续出现的次数。
 * 如果是非连续的字符(不区分大小写)，则输出：该字符(小写) + 该字母之后字符串中出现的该字符的次数
 * 对按照以上方式表示后的字符串进行排序：字母和紧随的数字作为一组进行排序，数字大的在前，数字相同的，则按字母进行排序，字母小的在前。
 *
 * 输入描述：
 * 一行字符串，长度为[1,200]
 *
 * 输出描述：
 * 摘要字符串
 */
public class 字符串摘要 {
    static class Result{
        public Character letter;
        public int num;

        @Override
        public String toString() {
            return letter+""+num;
        }
    }

    /**
     * 因为这个题目字母的输出可能会重复，比如说aaba，这种输出为a2a0b0这种输出，所以不能使用map来保存字符和数量这种结果，因为会键冲突，所以要使用对象来保存
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String lowerCase = s.toLowerCase();
        String replace = lowerCase.replaceAll("[^a-z]", "");

        int[] nums = new int[128];
        for(int i=0; i<replace.length(); i++){
            //为了字符不连续时的数量统计
            nums[replace.charAt(i)]++;
        }
        //这个很重要，为了最终输出，加这个不影响最终结果，因为下面的遍历是以beforeChar为准放入list中的，加上这个符号可以保证真正的最后一个字符可以被遍历到，当然这里加上
        //空格也可以
        replace = replace+"_";
        //字符连续时的数量统计
        int repeat = 1;
        Character beforeChar =replace.charAt(0);
        //字符的数量减去一个
        nums[beforeChar]--;

        ArrayList<Result> list = new ArrayList<>();

        for(int i=1; i<replace.length(); i++){
            char currC = replace.charAt(i);

            //字符的数量减去一个
            nums[currC]--;
            if(currC==beforeChar){

                repeat++;

            }else {
                Result result = new Result();
                result.letter=beforeChar;
                //如果repeat>1说明是重复字符，则输出重复数量
                result.num=repeat>1?repeat:nums[beforeChar];
                list.add(result);
                repeat=1;
                beforeChar=currC;

            }
        }


        list.stream().sorted((a, b)->{
            if(a.num== b.num){
                return a.letter-b.letter;
            }else {
                return b.num-a.num;
            }

        }).forEach(a->System.out.print(a));

    }

}
