import java.util.*;

/**
 * 一个XX产品行销总公司，只有一个boss,，其有若干一级分销，一级分销又有若干二级分销，每个分销只有唯一的上级分销。
 * 规定，每个月，下级分销需要将自己的总收入（自己的+下级上交的）每满100元上交15元给自己的上级。
 * 现给出一组分销的关系，和每个分销的收入，请找出boss并计算出这个boss的收入。
 * 比如：
 * 收入100元，上交15元；
 * 收入199元（99元不够100），上交15元；
 * 收入200元，上交30元。
 *
 * 输入：
 * 分销关系和收入：[[分销id 上级分销id 收入], [分销id 上级分销id 收入], [分销id 上级分销id 收入]]
 * 分销ID范围： 0..65535
 * 收入范围：0..65535，单位元
 *
 * 提示：
 * 输入的数据只存在1个boss，不存在环路
 *
 * 输出：
 * [boss的ID, 总收入]
 *
 * 输入描述:
 * 第一行输入关系的总数量 N
 *  第二行开始，输入关系信息，格式：
 *  分销ID 上级分销ID 收入
 *   比如：
 *   5
 *   1 0 100
 *   2 0 199
 *   3 0 200
 *   4 0 200
 *   5 0 200
 *
 *   输出描述：
 *   boss的ID 总收入
 *   比如
 *   0 120
 *
 *   备注：
 *   给定的输入数据都是合法的，不存在环路，
 */
public class boss的收入 {

    static Map<Integer, List<Integer>> fa_ch;
    static Map<Integer, Integer> ele_money;
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Set<Integer> allEle = new HashSet<>();
        Map<Integer, Integer> ch_fa = new HashMap<>();
        fa_ch = new HashMap<>();
        ele_money = new HashMap<>();

        Integer rootFa = null;
        for(int i=0;i<n;i++){

            int[] eles = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int ch = eles[0];
            int fa = eles[1];
            int money = eles[2];

            //如果没有父key，value就放入空的list
            fa_ch.putIfAbsent(fa, new ArrayList<>());
            ch_fa.put(ch, fa);
            //这里一定有父key了，加入子值
            fa_ch.get(fa).add(ch);
            //放入子元素及对应的钱
            ele_money.put(ch, money);
            //将所有元素放入set中，为了后续找出根节点
            allEle.add(ch);
            allEle.add(fa);
        }
        for (Integer o : allEle.toArray(new Integer[0])) {
            if(ch_fa.get(o)==null){
                //找出的根节点
                rootFa=o;
                break;
            }
        }

       dfs(rootFa, fa_ch.get(rootFa));

        System.out.println(rootFa + " " + ele_money.get(rootFa));

    }

    public static void dfs(Integer faEle, List<Integer> chList) {
        if(!chList .isEmpty()) {
            for (Integer ch_ele : chList) {
                dfs(ch_ele, fa_ch.get(ch_ele));
                //因为root父节点没有钱数，所以ele_money.getOrDefault(faEle, 0)来防止找不出root父节点的钱数
                ele_money.put(faEle, ele_money.getOrDefault(faEle, 0) + ele_money.get(ch_ele) / 100 * 15);
            }
        }
    }
}
