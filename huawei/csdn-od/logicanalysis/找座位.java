import java.io.Serializable;
import java.util.Scanner;

/**
 * https://fcqian.blog.csdn.net/article/details/127914382
 *
 * 在一个大型体育场内举办了一场大型活动，由于疫情防控的需要，要求每位观众的必须间隔至少一个空位才允许落座。
 * 现在给出一排观众座位分布图，座位中存在已落座的观众，请计算出，在不移动现有观众座位的情况下，最多还能坐下多少名观众。
 * 输入描述
 * 一个数组，用来标识某一排座位中，每个座位是否已经坐人。0表示该座位没有坐人，1表示该座位已经坐人。
 * 1 ≤ 数组长度 ≤ 10000
 *
 * 输出描述
 * 整数，在不移动现有观众座位的情况下，最多还能坐下多少名观众。
 */
public class 找座位 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String arrStr = scanner.nextLine();

        char[] charArray = arrStr.toCharArray();

        int extraSit = 0;

        for(int i=0;i<charArray.length;i++){
            if(charArray[i]=='0') {
                boolean left = i==0||charArray[i-1]=='0';
                boolean right = i==charArray.length-1||charArray[i+1]=='0';

                if(left && right){
                    extraSit++;
                    charArray[i]='1';
                    i++;
                }
            }
        }
        System.out.println(extraSit);
    }
}
