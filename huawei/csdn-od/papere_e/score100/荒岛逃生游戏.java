import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 一个荒岛上有若干人，岛上只有一条路通往岛屿两端的港口，大家需要逃往两端的港口才可逃生。
 * 假定每个人移动的速度一样，且只可选择向左或向右逃生。
 * 若两个人相遇，则进行决斗，战斗力强的能够活下来，并损失掉与对方相同的战斗力；若战斗力相同，则两人同归于尽。
 *
 * 输入描述
 * 给定一行非 0 整数数组，元素个数不超过30000；
 * 正负表示逃生方向（正表示向右逃生，负表示向左逃生），绝对值表示战斗力，越左边的数字表示里左边港口越近，逃生方向相同的人永远不会发生决斗。
 *
 * 输出描述：
 * 能够逃生的人总数，没有人逃生输出0，输入异常时输出-1。
 */
public class 荒岛逃生游戏 {
    /**
     * 从左到右遍历，遇到正数放到栈结构中，遇到负数就从栈中弹处元素与其比较，负数较大且栈中没有元素了，说明负数逃脱成功，比较到最后，栈中还有元素，则正数逃脱成功
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] inputs = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        if(inputs.length>=30000){
            System.out.println(-1);
            return;
        }
        int count =0;

        LinkedList<Integer> linkedList = new LinkedList<>();

        for(int i=0; i<inputs.length; i++){
            //检验，元素不能为0
            if(inputs[i]==0){
                System.out.println(-1);
                return;
            }

            if(inputs[i]<0){

                int tmpNum = inputs[i];
                while (!linkedList.isEmpty()){
                    //弹出一个正数
                    Integer addNum = linkedList.removeLast();
                    int resultNum = addNum + tmpNum;
                    if(resultNum==0){
                        tmpNum = resultNum;
                        //战斗值相同相互抵消
                        break;
                    } else if (resultNum>0) {
                        tmpNum = resultNum;
                        //正数大，重新入栈
                        linkedList.add(resultNum);
                        break;
                    } else if (resultNum<0) {
                        //负数大，继续与栈中元素比较
                        tmpNum = resultNum;
                    }
                }
                if(tmpNum<0){
                    count++;
                }

            } else if (inputs[i]>0) {
                linkedList.add(inputs[i]);

            }

        }

        count+=linkedList.size();

        System.out.println(count);


    }
}
