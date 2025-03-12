import java.util.HashMap;
import java.util.Scanner;

/**
 * 在第一人称射击游戏中，玩家通过键盘的A、S、D、W四个按键控制游戏人物分别向左、向后、向右、向前进行移动，从而完成走位。
 * 假设玩家每按动一次键盘，游戏任务会向某个方向移动一步，如果玩家在操作一定次数的键盘并且各个方向的步数相同时，此时游戏任务必定会回到原点，则称此次走位为完美走位。
 * 现给定玩家的走位（例如：ASDA），请通过更换其中一段连续走位的方式使得原走位能够变成一个完美走位。其中待更换的连续走位可以是相同长度的任何走位。
 * 请返回待更换的连续走位的最小可能长度。
 * 如果原走位本身是一个完美走位，则返回0。
 *
 * 输入描述：
 * 输入为由键盘字母表示的走位s，例如：ASDA
 *
 * 输出描述：
 * 输出为待更换的连续走位的最小可能长度。
 *
 * 备注：
 * 走位长度 1 ≤ s.length ≤ 100000
 * s.length 是 4 的倍数
 * s中只含有'A', 'S', 'D', 'W' 四种字符
 *
 * 用例输入：
 * WASDAASD
 *
 * 输出：
 * 1
 *
 * 解释：
 * 将第二个A替换为W，即可得到完美走位
 */
public class 获得完美走位 {
    /**
     * 本题可采用最小覆盖子串的算法进行求解,本题稍有不同的是使用的map保存的字符的数量
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        HashMap<Character, Integer> map = new HashMap<>();
        //每步应该有的数量
        int standStep = s.length()/4;
        //每步多出的数量和
        int diff = 0;
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0)+1);
        }

        for (Character character : map.keySet()) {
            int num = map.get(character);
            if(num<=standStep){
                map.put(character, 0);
            }else {
                //意味着我要在下面的循环中找到num - standStep个character，和标准最小覆盖子串的解法原理一样
                map.put(character, num - standStep);
                diff+=(num-standStep);
            }

        }

        if(diff == 0){
            System.out.println(0);
            return;
        }
        int l = 0;
        int r = 0;
        int minLen = Integer.MAX_VALUE;
        while (r<s.length()){
            Character rc = s.charAt(r);
            if (map.get(rc)>0){
                diff--;
            }
            //多出来的key为rc的value会变为负数
            map.put(rc, map.get(rc)-1);
            //这个diff--一定要在判断中，例如：ASDASSSWWDSSWDASSWAAWSAWASDSSASA
            // A只多了一个，但是在判断时，当判断到第二个A时，也会再减去一个不同的数字，也就是diff--，这样逻辑就错了，所以diff--不能放在这里
            //diff--;
            while (diff==0){
                minLen = Math.min(minLen, r-l+1);
                //说明所有多出来的步都已经遍历到了，开始向右移动左指针
                Character lc = s.charAt(l);

                //说明从此刻开始遇到了改变diff的字符
                if(map.get(lc)>=0){
                    diff++;
                }
                map.put(lc, map.get(lc)+1);
                l++;
            }
            r++;
        }

        System.out.println(minLen);
    }
}

//标准答案
//import java.util.HashMap;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        System.out.println(solution(sc.next()));
//    }
//
//    public static int solution(String str) {
//        // count用于记录W,A,S,D字母的数量
//        HashMap<Character, Integer> count = new HashMap<>();
//
//        for (int i = 0; i < str.length(); i++) {
//            Character c = str.charAt(i);
//            count.put(c, count.getOrDefault(c, 0) + 1);
//        }
//
//        // 平衡状态时，W,A,S,D应该都是avg数量
//        int avg = str.length() / 4;
//
//        // total用于记录多余字母个数
//        int total = 0;
//
//        // flag表示当前是否为平衡状态，默认是
//        boolean flag = true;
//
//        for (Character c : count.keySet()) {
//            if (count.get(c) > avg) {
//                // 如果有一个字母数量超标，则平衡打破
//                flag = false;
//                // 此时count记录每个字母超过avg的数量
//                count.put(c, count.get(c) - avg);
//                total += count.get(c);
//            } else {
//                count.put(c, 0); // 此时count统计的其实是多余字母，如果没有超过avg,则表示没有多余字母
//            }
//        }
//
//        // 如果平衡，则输出0
//        if (flag) return 0;
//
//        int i = 0;
//        int j = 0;
//        int minLen = str.length() + 1;
//
//        while (j < str.length()) {
//            Character jc = str.charAt(j);
//
//            if (count.get(jc) > 0) {
//                total--;
//            }
//            count.put(jc, count.get(jc) - 1);
//
//            while (total == 0) {
//                minLen = Math.min(minLen, j - i + 1);
//
//                Character ic = str.charAt(i);
//                if (count.get(ic) >= 0) {
//                    total++;
//                }
//                count.put(ic, count.get(ic) + 1);
//
//                i++;
//            }
//            j++;
//        }
//        return minLen;
//    }
//}
