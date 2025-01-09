import java.util.*;

/**
 * 在斗地主扑克牌游戏中， 扑克牌由小到大的顺序为：3,4,5,6,7,8,9,10,J,Q,K,A,2，玩家可以出的扑克牌阵型有：单张、对子、顺子、飞机、炸弹等。
 * 其中顺子的出牌规则为：由至少5张由小到大连续递增的扑克牌组成，且不能包含2。
 * 例如：{3,4,5,6,7}、{3,4,5,6,7,8,9,10,J,Q,K,A}都是有效的顺子；而{J,Q,K,A,2}、 {2,3,4,5,6}、{3,4,5,6}、{3,4,5,6,8}等都不是顺子。
 * 给定一个包含 13 张牌的数组，如果有满足出牌规则的顺子，请输出顺子。
 * 如果存在多个顺子，请每行输出一个顺子，且需要按顺子的第一张牌的大小（必须从小到大）依次输出。
 * 如果没有满足出牌规则的顺子，请输出No。
 *
 * 输入描述：
 * 13张任意顺序的扑克牌，每张扑克牌数字用空格隔开，每张扑克牌的数字都是合法的，并且不包括大小王：2 9 J 2 3 4 K A 7 9 A 5 6
 * 不需要考虑输入为异常字符的情况
 *
 * 输出描述：
 * 组成的顺子，每张扑克牌数字用空格隔开：3 4 5 6 7
 */
public class 斗地主之顺子 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] pokers = Arrays.stream(scanner.nextLine().split(" ")).filter(a -> !a.equals("2")).sorted((a, b) -> getNum(a) - getNum(b)).toArray(String[]::new);

        ArrayList<LinkedList<String>> goingList = new ArrayList<>();

        //遍历排序后的每一张扑克牌
        out: for(int i=0; i<pokers.length; i++){

            //遍历顺子集合，确定每一张扑克牌要放到哪个顺子中
            for(int j=0; j<goingList.size(); j++){
                if(getNum(pokers[i])-getNum(goingList.get(j).getLast())==1){
                    goingList.get(j).addLast(pokers[i]);
                    continue out;
                }
            }

            LinkedList<String> insertList = new LinkedList<>();
            insertList.addLast(pokers[i]);
            goingList.add(insertList);
        }

        int outCount = 0;
        for(int i=0; i<goingList.size(); i++){
            if(goingList.get(i).size()>=5){
                outCount++;
                System.out.println(String.join(" ", goingList.get(i)));
            }
        }
        if(outCount==0){
            System.out.println("No");
        }

    }
    public static int getNum(String s){
        int result;
        switch (s) {
            case "J":
                result=11;
                break;
            case "Q":
                result=12;
                break;
            case "K":
                result=13;
                break;
            case "A":
                result = 14;
                break;
            default:
                result = Integer.parseInt(s);
                break;

        }
        return result;
    }
}
