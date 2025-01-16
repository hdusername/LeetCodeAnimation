import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/**
 *一个人设定一组四码的数字作为谜底，另一方猜。
 * 每猜一个数，出数者就要根据这个数字给出提示，提示以XAYB形式呈现，直到猜中位置。
 * 其中X表示位置正确的数的个数（数字正确且位置正确），而Y表示数字正确而位置不对的数的个数。
 * 例如，当谜底为8123，而猜谜者猜1052时，出题者必须提示0A2B。
 * 例如，当谜底为5637，而猜谜者才4931时，出题者必须提示1A0B。
 * 当前已知N组猜谜者猜的数字与提示，如果答案确定，请输出答案，不确定则输出NA。
 *
 * 输入描述：
 * 第一行输入一个正整数，0＜N ＜ 100。
 * 接下来N行，每一行包含一个猜测的数字与提示结果。
 *
 * 输出描述：
 * 输出最后的答案，答案不确定则输出NA。
 */
public class 猜数字 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String[][] guessArrays = new String[n][2];
        for(int i=0; i<n; i++){
            String[] inputs = scanner.nextLine().split(" ");
            guessArrays[i][0]=inputs[0];
            guessArrays[i][1]=inputs[1];
        }

        int ansCount = 0;
        String answer = "NA";


        out: for(int i=0; i<=9999; i++){
            String formatChar4 = String.format("%04d", i);
            for(int j=0; j<guessArrays.length; j++){
                //将遍历的答案与猜谜者猜测的答案进行对比，看是否能与给出的提示对上
                String promt = getPromt(formatChar4, guessArrays[j][0]);
                if(!promt.equals(guessArrays[j][1])){
                    continue out;
                }
            }
            ansCount++;
            if(ansCount>1){
                answer = "NA";
                break ;
            }

            answer = formatChar4;
        }
        System.out.println(answer);
    }

    private static String getPromt(String formatStr, String guessStr) {
        int sameNum = 0;
        int diffNum = 0;

        List<Character> formatList = new ArrayList<>();
        List<Character> guessList = new ArrayList<>();
        for(int i=0; i<formatStr.length(); i++){

            if(formatStr.charAt(i)==guessStr.charAt(i)){
                sameNum++;
            }else {
                //记录下对应位置字符对不上的字符
                guessList.add(guessStr.charAt(i));
                //把这个添加逻辑也放在这里就说明位置对上的字符不能在与位置对不上的字符进行比较了，不如说用例：
                //formatStr=3585  guessStr=8585， 最终判断的结果应该是是3A0B，也就是说这两个字符第一位对不上，
                //即使guessStr第一位8在formatStr中存在，但是因为formatStr中的8已经有位置对应上了，所以不能算
                //所以最终的判断结果就是3A0B
                formatList.add(formatStr.charAt(i));
            }
        }
        for(int i=0; i<guessList.size(); i++){
            //位置对不上的字符在正确答案中可以找到对应的字符，说明有这个字符但是位置不对
            if(formatList.remove(guessList.get(i))){
                diffNum++;
            }
        }
        return sameNum+"A"+diffNum+"B";
    }
}
