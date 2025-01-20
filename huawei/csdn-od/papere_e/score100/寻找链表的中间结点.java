import java.util.*;

/**
 * 给定一个单链表 L，请编写程序输出 L 中间结点保存的数据。
 * 如果有两个中间结点，则输出第二个中间结点保存的数据。例如：
 * 给定 L 为 1→7→5，则输出应该为7；
 * 给定 L 为 1→2→3→4，则输出应该为3；
 *
 * 每个输入包含1个测试用例。每个测试用例：
 * 第一行给出链表首结点的地址、结点总个数正整数 N （≤ 10^5）。
 * 结点的地址是 5 位非负整数，NULL地址用 -1 表示。
 * 接下来有N行，每行格式位：
 * Address Data Next
 * 其中Address是结点地址，Data是该结点保存的整数数据（0 ≤ Data ≤ 10^8），Next是下一结点的地址。
 *
 * 输出描述：
 * 对每个测试用例，在一行中输出 L 中间结点保存的数据。
 * 如果有两个中间结点，则输出第二个中间结点保存的数据。
 *
 * 备注：
 * 已确保输入的结点所构成的链表 L 不会成环，但会存在部分输入结点不属于链表 L 情况 。
 */
public class 寻找链表的中间结点 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] ns = scanner.nextLine().split(" ");
        Map<String, String[]> map = new HashMap<>();
        for(int i=0; i<Integer.parseInt(ns[1]); i++){
            String[] nodes = scanner.nextLine().split(" ");
            //将Address作为key，Data和Next作为value存入map，为了方便建立链表关系，因为可以随时查询出来Address是什么
            map.putIfAbsent(nodes[0], new String[2]);
            map.get(nodes[0])[0]=nodes[1];
            map.get(nodes[0])[1]=nodes[2];
        }

        //使用快慢指针，快指针一次向后移动两格，慢指针一次向后移动一格，最后慢指针位置就是所求位置
        String[] slowPoi = map.get(ns[0]);
        String[] fastPoi = map.get(ns[0]);
       // linkedList.add(curVal);

        while (Integer.parseInt(fastPoi[1]) != -1) {

            slowPoi = map.get(slowPoi[1]);
            fastPoi = map.get(fastPoi[1]);

            if (Integer.parseInt(fastPoi[1]) != -1) {
               // linkedList.add(map.get(curVal[1]));
                fastPoi = map.get(fastPoi[1]);
            }else {
                break;
            }

        }
        System.out.println(slowPoi[0]);

    }
}
