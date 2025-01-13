import java.util.LinkedList;
import java.util.Scanner;

/**
 * 有一个特异性的双端队列，该队列可以从头部或尾部添加数据，但是只能从头部移出数据。
 * 小A依次执行2n个指令往队列中添加数据和移出数据。其中n个指令是添加数据（可能从头部添加、也可能从尾部添加），依次添加1到n；n个指令是移出数据。
 * 现在要求移除数据的顺序为1到n。
 * 为了满足最后输出的要求，小A可以在任何时候调整队列中数据的顺序。
 * 请问 小A 最少需要调整几次才能够满足移除数据的顺序正好是1到n；
 *
 * 输入描述
 * 第一行一个数据n，表示数据的范围。
 * 接下来的2n行，其中有n行为添加数据，指令为：
 * head add x" 表示从头部添加数据 x，
 * tail add x" 表示从尾部添加数据x，
 * 另外 n 行为移出数据指令，指令为："remove" 的形式，表示移出1个数据；
 * 1 ≤ n ≤ 3 * 10^5。
 * 所有的数据均合法。
 *
 * 输出描述：
 * 一个整数，表示 小A 要调整的最小次数。
 */
public class 最小的调整次数 {

    /**
     * 在做本题时要理解，它说的是满足移除顺序是1到n，所以说不是遇到了一个不满足顺序的就要调整，要等到remove时顺序不对，才要调整，
     * 题目中说的时调整数据的顺序，而不是交换的顺序，所以在一次调整时不管换了数字几次都只算一次
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        LinkedList<String> ansList = new LinkedList<>();
        int changeCount = 0;
        int count = 0;
        for(int i=0; i<2*n; i++){

            String s = scanner.nextLine();
            //如果集合大小为0，可以插入head开头的，其他任何中情况插入head开头的都会造成顺序错误
            if(ansList.size()==0){
                //插入什么不重要，只是占个位置，插入输入的值会占用很多内存，所以不插入输入的值 最终的changeCount才重要
                ansList.add(i+"");
            } else if (s.startsWith("remove")) {
                if(changeCount>0){
                    //用例：
                    //5
                    //head add 1
                    //tail add 2
                    //remove
                    //head add 3
                    //tail add 4
                    //head add 5
                    //remove
                    //remove
                    //remove
                    //remove
                    //只是调整了一次
                    count++;
                    changeCount=0;
                }
                ansList.removeFirst();
            } else {
                if(s.startsWith("head")){
                    ansList.addFirst(i+"");
                    //ansList不为空，当前判断值为head开头，一定要调整一次
                    changeCount++;
                }else {
                    ansList.addLast(i+"");
                }
            }
        }

        System.out.println(count);
    }
}
