import java.util.Scanner;

/**
 * 小明正在规划一个大型数据中心机房，为了使得机柜上的机器都能正常满负荷工作，需要确保在每个机柜边上至少要有一个电箱。
 * 为了简化题目，假设这个机房是一整排，M表示机柜，I表示间隔，请你返回这整排机柜，至少需要多少个电箱。 如果无解请返回 -1 。
 *
 * 输入描述：
 * cabinets = "MIIM"
 * 其中 M 表示机柜，I 表示间隔
 *
 * 输出描述：
 * 2
 * 表示至少需要2个电箱
 *
 * 备注：
 * 1 ≤ strlen(cabinets) ≤ 10000
 * 其中 cabinets[i] = 'M' 或者 'I‘
 *
 * 用例：
 * 输入
 * MIIM
 * 输出
 * 2
 */
public class 机房布局 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int len = s.length();
        char[] charArray = s.toCharArray();
        int ans = 0;
        for (int i=0; i<len; i++) {
            char c = charArray[i];

            if(c=='M'){
                if(i+1<len && charArray[i+1]=='I'){
                    //说明机柜右侧有电箱
                    ans++;
                    //那么再向右挨着右侧电箱的不管是什么都不用判断了，因为如果是机柜，那么其左侧有电箱了，如果是电箱也不用判断了
                    i+=2;
                } else if (i-1>=0 && charArray[i-1]=='I') {
                    //走到这个逻辑说明右侧没有电箱，就要看一下左侧有没有电箱
                    ans++;
                }else{
                    //走到这个逻辑说明机柜附近没有电箱
                    ans=-1;
                    break;
                }
            }
        }

        System.out.print(ans);
    }
}
