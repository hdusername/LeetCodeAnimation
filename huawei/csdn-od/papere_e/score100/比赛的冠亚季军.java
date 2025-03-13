import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 有N（3 ≤ N < 10000）个运动员，他们的id为0到N-1,他们的实力由一组整数表示。他们之间进行比赛，需要决出冠亚军。
 * 比赛的规则是0号和1号比赛，2号和3号比赛，以此类推，每一轮，相邻的运动员进行比赛，获胜的进入下一轮；实力值大的获胜，
 * 实力值相等的情况，id小的情况下获胜；轮空的直接进入下一轮。
 *
 * 输入描述：
 * 输入一行N个数字代表N的运动员的实力值(0<=实力值<=10000000000)。
 *
 * 输出描述：
 * 输出冠亚季军的id，用空格隔开。
 *
 * 用例输入：
 * 2 3 4 5
 *
 * 输出：
 * 3 1 2
 *
 * 解释：
 * 第一轮比赛，id为0实力值为2的运动员和id为1实力值为3的运动员比赛，1号胜出进入下一轮争夺冠亚军，
 * id为2的运动员和id为3的运动员比赛，3号胜出进入下一轮争夺冠亚军，
 * 冠亚军比赛，3号胜1号，
 * 故冠军为3号，亚军为1号，2号与0号，比赛进行季军的争夺，2号实力值为4，0号实力值2，故2号胜出，得季军。冠亚季军为3 1 2。
 */
public class 比赛的冠亚季军 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long[] powers = Arrays.stream(scanner.nextLine().split(" ")).mapToLong(Long::parseLong).toArray();

        //将获胜的放到一个list中置于此链表的头节点
        LinkedList<ArrayList<Integer>> linkedList = new LinkedList<>();
        ArrayList<Integer> firstList = new ArrayList<>();
        for(int i=0; i<powers.length; i++){
            firstList.add(i);
        }
        linkedList.add(firstList);
        //因为冠军肯定最后剩一个，亚军剩一个，季军可能剩一个、可能剩两个
        while (linkedList.getFirst().size()>1) {
            //这里需要poll的原因是可以认为linkedList.getFirst()取出的都是获胜的人，下一次放入linkedList中的数据是在这些获胜的人中再次分出来获胜的和失败的，
            //所以原来的获胜集合就没用了可以弹出
            ArrayList<Integer> currList = linkedList.pollFirst();

            ArrayList<Integer> winList = new ArrayList<>();
            ArrayList<Integer> failList = new ArrayList<>();
            int i = 1;
            for (; i < currList.size(); i += 2) {
                long power1 = powers[currList.get(i - 1)];
                long power2 = powers[currList.get(i)];

                if (power1 >= power2) {
                    winList.add(currList.get(i - 1));
                    failList.add(currList.get(i));
                } else {
                    winList.add(currList.get(i));
                    failList.add(currList.get(i - 1));
                }
            }
            if (i == currList.size()) {
                //说明最后剩了一位
                winList.add(currList.get(i-1));
            }

            linkedList.addFirst(failList);
            linkedList.addFirst(winList);
        }



        int rank1 = linkedList.pollFirst().get(0);
        int rank2 = linkedList.pollFirst().get(0);
        ArrayList<Integer> rank3List = linkedList.pollFirst();
        int rank3;
        if(rank3List.size()==2){
            long power1 = powers[rank3List.get(0)];
            long power2 = powers[rank3List.get(1)];
            rank3 = power1>=power2?rank3List.get(0):rank3List.get(1);
        }else {
            rank3 = rank3List.get(0);
        }

        System.out.println( rank1+ " " + rank2 + " " + rank3);
    }
}

//标注答案
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        long[] strengths = Arrays.stream(sc.nextLine().split(" ")).mapToLong(Long::parseLong).toArray();
//
//        LinkedList<ArrayList<Integer>> link = new LinkedList<>();
//
//        ArrayList<Integer> sports = new ArrayList<>();
//        for (int i = 0; i < strengths.length; i++) sports.add(i);
//        link.add(sports);
//
//        // 冠军组如果不是一个人，那么还需要取出冠军组继续进行晋级赛
//        while (link.getFirst().size() > 1) {
//            ArrayList<Integer> ids = link.removeFirst();
//
//            // 记录获胜组
//            ArrayList<Integer> win = new ArrayList<>();
//            // 记录失败组
//            ArrayList<Integer> fail = new ArrayList<>();
//
//            for (int i = 1; i < ids.size(); i += 2) {
//                // 序号大的运动员
//                int majorId = ids.get(i);
//                // 序号小的运动员
//                int minorId = ids.get(i - 1);
//
//                if (strengths[majorId] > strengths[minorId]) {
//                    win.add(majorId);
//                    fail.add(minorId);
//                } else {
//                    // 如果序号大的运动员的实力 <= 序号小的运动员，则序号小的运动员获胜
//                    win.add(minorId);
//                    fail.add(majorId);
//                }
//            }
//
//            // 如果晋级赛中运动员个数是奇数个，那么最后一个运动员直接晋级
//            if (ids.size() % 2 != 0) {
//                win.add(ids.get(ids.size() - 1));
//            }
//
//            // 依次头部压入失败组，获胜组，保证头部是获胜组
//            link.addFirst(fail);
//            link.addFirst(win);
//        }
//
//        // 冠军组只有一个人
//        int first = link.get(0).get(0);
//
//        // 亚军组只有一个人
//        int second = link.get(1).get(0);
//
//        // 季军组可能有一个人，也可能有两个人
//        link.get(2).sort((a, b) -> strengths[a] != strengths[b] ? Long.compare(strengths[b], strengths[a]) : a - b);
//        int third = link.get(2).get(0);
//
//        System.out.println(first + " " + second + " " + third);
//    }
//}
