import java.util.*;

/**
 * 向一个空栈中依次存入正整数，假设入栈元素 n （1 ≤ n ≤ 2^31 - 1）按顺序依次为：nx ... n4、n3、n2、n1，
 * 每当元素入栈时，如果 n1 = n2 + ... + ny（y 的范围[2,x]， 1 ≤ x ≤ 1000），则 n1 ~ ny 全部元素出栈，重新入栈新元素 m（m = 2 * n1）
 * 如：依次向栈存入6、1、2、3
 * 当存入 6、1、2 时，栈底至栈顶依次为[6、1、2]；
 * 当存入 3 时，3 = 2 + 1，3、2、1全部出栈，重新入栈元素6（6=2*3），此时栈中有元素6；因为6=6，所以两个6全部出栈，存入12，最终栈中只剩一个元素12。
 *
 * 输入描述：
 * 使用单个空格隔开的正整数的字符串，如”5 6 7 8″， 左边的数字先入栈，输入的正整数个数为 x， 1 ≤ x ≤ 1000。
 *
 * 输出描述：
 * 最终栈中存留的元素值，元素值使用空格隔开，如"8 7 6 5"， 栈顶数字在左边。
 *
 */
public class 计算堆栈中的剩余数字 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] inputs = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        LinkedList<Integer> linkedList = new LinkedList();
        //先放入一个元素
        linkedList.add(inputs[0]);
        for(int i=1; i<inputs.length; i++){

            //因为涉及到放入元素就可能发生的重新弹栈入栈的情况，所以这是一个递归的过程，要新写一个方法
            push(inputs[i], linkedList);
        }

        StringJoiner stringJoiner = new StringJoiner(" ");
        while (!linkedList.isEmpty()){
            stringJoiner.add(linkedList.removeLast()+"");
        }
        System.out.println(stringJoiner);
    }

    private static void push(int num, LinkedList<Integer> linkedList) {
        //num在下面再次调用push方法时还有用，所以不能直接减去，赋值给另一个数字
        int sum = num;

        //相当于从栈顶开始向下遍历
        for(int i=linkedList.size()-1; i>=0; i--){
            //todo：这里用减法才是整个解法的精髓
            sum-=linkedList.get(i);

            if(sum ==0){
                linkedList.subList(i, linkedList.size()).clear();
                //删除后需要新增元素2*sum
                push(2*num, linkedList);
                return;
            }else if(sum <0){
                break;
            }
        }
        linkedList.add(num);

    }


}
