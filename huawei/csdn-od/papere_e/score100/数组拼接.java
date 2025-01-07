import java.util.*;
import java.util.stream.Collectors;

/**
 * 现在有多组整数数组，需要将它们合并成一个新的数组。
 * 合并规则，从每个数组里按顺序取出固定长度的内容合并到新的数组中，取完的内容会删除掉，如果该行不足固定长度或者已经为空，则直接取出剩余部分的内容放到新的数组中，继续下一行。
 *
 * 输入描述：
 * 第一行是每次读取的固定长度，0 < 长度 < 10
 * 第二行是整数数组的数目，0 < 数目 < 1000
 * 第3-n行是需要合并的数组，不同的数组用回车换行分隔，数组内部用逗号分隔，最大不超过100个元素。
 *
 * 输出描述：
 * 输出一个新的数组，用逗号分隔。
 */
public class 数组拼接 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer subLen = Integer.parseInt(scanner.nextLine());
        Integer subNum = Integer.parseInt(scanner.nextLine());

        LinkedList<LinkedList<Integer>> linkedList = new LinkedList<>();
        for(int i=0; i<subNum; i++) {
            //因为存在输入为空字符串的情况，所以在这里排除下
            linkedList.add(new LinkedList<>(Arrays.stream(scanner.nextLine().split(",")).filter(a -> !"".equals(a)).map(Integer::parseInt).collect(Collectors.toList())));

        }

        StringJoiner stringJoiner = new StringJoiner(",");
        while (linkedList.size() != 0){
            LinkedList<Integer> first = linkedList.getFirst();

            if(first.size() == 0){
                linkedList.removeFirst();
            }else {
                List<Integer> integers = first.subList(0, Math.min(subLen, first.size()));
                integers.stream().forEach(a -> stringJoiner.add(a + ""));
                integers.clear();
                linkedList.addLast(first);
                linkedList.removeFirst();
            }
        }

        System.out.println(stringJoiner);
    }
}
