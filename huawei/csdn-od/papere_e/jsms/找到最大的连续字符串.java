import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目:
 * 一串 字符串只 由 "1-10""A-Z"组成，各字符之间由""隔开，找到最大连续的子串。(10后面的字符是A)
 * 示例1:
 * 输入:
 * 1,2 3 4 5,7,8
 * 输出:
 * 12345
 */
public class 找到最大的连续字符串 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        StringBuffer stringBuffer = new StringBuffer();
        //Arrays.stream(s.split(",")).forEach(a->stringBuffer.append(a));
        String[] splits = s.split(",");
        //char[] splits = stringBuffer.toString().toCharArray();

        int left =0;
        int[] ans = new int[]{0, splits.length};
        int minAns = 0;

        for(int i=1; i<splits.length; i++){
            //if(Character.isDigit(splits[i])){
               // if('A'==splits[i]){
//                    if((splits[i-1]+"").equals("10")){
//                        continue;
//                    }else {
//                       // ans = new int[]{left, i};
//                        //left = i;
//                    }
                //}else {
                    if(!("A".equals(splits[i]) && "10".equals(splits[i-1])) && !("10".equals(splits[i]) && "9".equals(splits[i-1])) && splits[i].charAt(0)-splits[i-1].charAt(0) != 1){

//                        if(i==splits.length-1){
//                            i=splits.length;
//                        }
                        if(i-left>minAns){
                            ans = new int[]{left, i};
                            left = i;
                        }

                    }
                //}
            //}
        }

        StringBuffer stringBuffer1 = new StringBuffer();
        for(int i=ans[0]; i<ans[1]; i++){
            stringBuffer1.append(splits[i]);
        }
        System.out.println(stringBuffer1);
    }
}
//1,2,3,4,5,6,7,9
//1,2,3,4,5,6,7,8,9,10,A