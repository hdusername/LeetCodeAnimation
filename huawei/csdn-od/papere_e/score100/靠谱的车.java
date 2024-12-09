import java.util.Arrays;
import java.util.Scanner;

/**
 * 程序员小明打了一辆出租车去上班。出于职业敏感，他注意到这辆出租车的计费表有点问题，总是偏大。
 * 出租车司机解释说他不喜欢数字4，所以改装了计费表，任何数字位置遇到数字4就直接跳过，其余功能都正常。
 * 比如：
 * 23再多一块钱就变为25；
 * 39再多一块钱变为50；
 * 399再多一块钱变为500；
 * 小明识破了司机的伎俩，准备利用自己的学识打败司机的阴谋。
 * 给出计费表的表面读数，返回实际产生的费用。
 *
 * 输入描述：
 * 只有一行，数字N，表示里程表的读数。
 * (1<=N<=888888888)。
 *
 * 输出描述：
 * 一个数字，表示实际产生的费用。以回车结束。
 */
public class 靠谱的车 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] fNum = Arrays.stream(scanner.nextLine().split("")).mapToInt(Integer::parseInt).toArray();

        int answer = 0;

        for(int i =0;i<fNum.length;i++){
            int curNum = fNum[i];

            if(curNum>3){
                curNum--;
            }

            //注意这里的条件，外面的for循环先遍历到的是高位的数字，也就是9是从多往少了乘
            for(int j=0;j<fNum.length-i-1;j++){
                curNum*=9;
            }

            answer+=curNum;

        }
        System.out.println(answer);

    }
}
