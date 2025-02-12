import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 斗地主起源于湖北十堰房县，据说是一位叫吴修全的年轻人根据当地流行的扑克玩法“跑得快”改编的，如今已风靡整个中国，并流行于互联网上。
 * 牌型：单顺，又称顺子，最少5张牌，最多12张牌(3…A)不能有2，也不能有大小王，不计花色。
 * 例如： 3-4-5-6-7-8，7-8-9-10-J-Q，3-4-5-6-7-8-9-10-J-Q-K-A
 * 可用的牌 3<4<5<6<7<8<9<10<J<Q<K<A<2<B(小王)<C(大王)，每种牌除大小王外有四种花色
 * (共有13×4+2张牌)
 *
 * 输入：
 * 手上有的牌
 * 已经出过的牌(包括对手出的和自己出的牌)
 *
 * 输出：
 * 对手可能构成的最长的顺子(如果有相同长度的顺子，输出牌面最大的那一个)，
 * 如果无法构成顺子，则输出 NO-CHAIN。
 *
 * 输入描述：
 * 输入的第一行为当前手中的牌
 * 输入的第二行为已经出过的牌
 *
 * 输出描述：
 * 最长的顺子
 *
 * 用例输入：
 * 3-3-3-3-4-4-5-5-6-7-8-9-10-J-Q-K-A
 * 4-5-6-7-8-8-8
 *
 * 输出：
 * 9-10-J-Q-K-A
 *
 */
public class 最长的顺子 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] myCards = scanner.nextLine().split("-");
        String[] outCards = scanner.nextLine().split("-");

        int[] cards = new int[15];
        for(int i=3; i<15; i++){
            //表示每张牌都有四张
            cards[i]=4;
        }
        //去除手里的
        for(String card : myCards){
            if(card.equals("2")||card.equals("B")||card.equals("C")){
                continue;
            }
            cards[trans(card)]--;
        }
        //去除已经打出去的
        for(String card : outCards){
            if(card.equals("2")||card.equals("B")||card.equals("C")){
                continue;
            }
            cards[trans(card)]--;
        }

        //是否顺子开始了
        boolean isStart = false;
        int startIdx = 0;
        int maxLen = 0;
        int[] ans = new int[2];
        for(int i=0; i< cards.length; i++){
            if(!isStart && cards[i]>0){
                startIdx = i;
                //顺子开始了
                isStart = true;
            } else if (isStart && (cards[i]==0 || i==cards.length-1)) {//找到不能组成顺子的牌或者找到最后一张牌
                //只要是找到的不存在的牌，i-1就是结束位置
                int endIdx = cards[i]==0?i-1:i;
                if(endIdx-startIdx>=maxLen){
                    ans[0]=startIdx;
                    ans[1]=endIdx;
                    maxLen = endIdx-startIdx;
                }
                isStart = false;
            }
        }

        if(ans[1]-ans[0]+1<5){
            System.out.println("NO-CHAIN");
            return;
        }
        StringJoiner stringJoiner = new StringJoiner("-");
        for(int i=ans[0]; i<=ans[1]; i++){
            stringJoiner.add(transCard(i));
        }
        System.out.println(stringJoiner);
    }

    private static int trans(String num){
        int ans;
        switch (num){
            case "J":
                ans=11;
                break;
            case "Q":
                ans=12;
                break;
            case "K":
                ans=13;
                break;
            case "A":
                ans=14;
                break;
            case "2":
                ans=14;
                break;
            default:
                ans = Integer.parseInt(num);
                break;
        }
        return ans;
    }

    private static String transCard(int num){
        String ans;
        switch (num){
            case 11:
                ans="J";
                break;
            case 12:
                ans="Q";
                break;
            case 13:
                ans="K";
                break;
            case 14:
                ans="A";
                break;
            default:
                ans = num+"";
                break;
        }
        return ans;
    }
}
