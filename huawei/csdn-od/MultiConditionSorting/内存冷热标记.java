import java.util.*;

/**
 * 现代计算机系统中通常存在多级的存储设备，针对海量 workload 的优化的一种思路是将热点内存页优先放到快速存储层级，这就需要对内存页进行冷热标记。
 * 一种典型的方案是基于内存页的访问频次进行标记，如果统计窗口内访问次数大于等于设定阈值，则认为是热内存页，否则是冷内存页。
 * 对于统计窗口内跟踪到的访存序列和阈值，现在需要实现基于频次的冷热标记。内存页使用页框号作为标识。
 *
 * 输入描述:
 * 第一行输入为 N，表示访存序列的记录条数，0 < N ≤ 10000。
 * 第二行为访存序列，空格分隔的 N 个内存页框号，页面号范围 0 ~ 65535，同一个页框号可能重复出现，出现的次数即为对应框号的频次。
 * 第三行为热内存的频次阈值 T，正整数范围 1 ≤ T ≤ 10000。
 *
 * 输出描述：
 * 第一行输出标记为热内存的内存页个数，如果没有被标记的热内存页，则输出 0 。
 * 如果第一行 > 0，则接下来按照访问频次降序输出内存页框号，一行一个，频次一样的页框号，页框号小的排前面。
 */
public class 内存冷热标记 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //一种解法思路
        //solution1(sc);
        //官方解法
        int n = sc.nextInt();

        HashMap<Integer, Integer> cnts = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            cnts.put(num, cnts.getOrDefault(num, 0) + 1);
        }

        int threshold = sc.nextInt();

        cnts.keySet().removeIf(num -> cnts.get(num) < threshold);

        System.out.println(cnts.size());

        cnts.entrySet().stream()
                .sorted(
                        (a, b) ->
                                a.getValue() - b.getValue() != 0
                                        ? b.getValue() - a.getValue()
                                        : a.getKey() - b.getKey())
                .forEach(a -> System.out.println(a.getKey()));


    }

    static class ElementWithIndex {
        int value;
        int index;

        ElementWithIndex(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }


    /**
     * 功能：此题想要用数组来实现，就是用数组下标表示页框号，数组值表示出现次数,但是根据出现次数排序那里感觉太麻烦了，不如正确答案，但是这种解法思路留下了没删除,而且这个算法没有完全通过测试用例，
     *      还是推荐用官方的，这个只是一种思路
     * @param scanner
     */
    private static void solution1(Scanner scanner) {
        int recordNum = scanner.nextInt();

        int[] ints = new int[1001];
        List<ElementWithIndex> list = new ArrayList<>();
        int ans = -1;
        StringBuilder stringBuilder = new StringBuilder();
        for(int i =0;i<recordNum;i++){
            int one = scanner.nextInt();
            ints[one]++;
        }

        int criticalVal = scanner.nextInt();

        for(int i =0;i<ints.length;i++){
            if(ints[i]>=criticalVal){
                list.add(new ElementWithIndex(ints[i],i));
            }
        }
        Collections.sort(list, new Comparator<ElementWithIndex>() {
            @Override
            public int compare(ElementWithIndex e1, ElementWithIndex e2) {
                if (e1.value != e2.value) {
                    return Integer.compare(e2.value, e1.value);
                }
                return Integer.compare(e1.index, e2.index);
            }
        });

        System.out.println(list.size()==0?-1:list.size());
        for (ElementWithIndex element : list) {
            System.out.println(element.index);
        }
    }
}
