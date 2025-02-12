import java.util.ArrayList;
import java.util.Scanner;

/**
 * 有一批箱子（形式为字符串，设为str），要求将这批箱子按从上到下以之字形的顺序摆放在宽度为 n 的空地，请输出箱子的摆放位置。
 * 例如：箱子ABCDEFG，空地宽度为3，摆放结果如图：
 * A F G
 * B E
 * C D
 * 则输出结果为：
 * AFG
 * BE
 * CD
 *
 * 输入描述：
 * 输入一行字符串，通过空格分隔
 * 前面部分为字母或数字组成的字符串str，表示箱子
 * 后面部分为数字n，表示空地的宽度
 * 例如：
 * ABCDEFG 3
 *
 * 输出描述：
 * 箱子摆放结果，如题目示例所示
 * AFG
 * BE
 * CD
 *
 * 备注：
 * 请不要在最后一行输出额外的空行
 * str 只包含字母和数字，1 ≤ len(str) ≤ 1000
 * 1 ≤ n ≤ 1000
 */
public class 箱子之字形摆放 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] split = scanner.nextLine().split(" ");
        String s = split[0];
        int n = Integer.parseInt(split[1]);

        //int[][] ans = new int[n][n];
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        for(int i=0; i<n; i++){
            list.add(new ArrayList<>());
        }

        //标识是否按照正常从上到下的顺序遍历的,因为第一次遍历i=0时会改变这个值为true，所以先定义为false
        boolean transFlag = false;
        for(int i=0; i<s.length(); i++){
            int k = i%n;
            if(k==0){
                transFlag = !transFlag;
            }
            //如果不是从上到下遍历的，需要改变下索引取值
            if(!transFlag){
                k = n-1-k;
            }
            list.get(k).add(s.charAt(i)+"");
        }

        for (ArrayList sonList : list){
            System.out.println(String.join("", sonList));
        }
    }
    
}
