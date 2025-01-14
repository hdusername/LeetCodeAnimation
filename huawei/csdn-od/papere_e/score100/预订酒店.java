import java.util.*;

/**
 * 放暑假了，小明决定到某旅游景点游玩，他在网上搜索到了各种价位的酒店（长度为 n 的数组 A），他的心理价位是 x 元，
 * 请帮他筛选出 k 个最接近 x 元的酒店（n ≥ k > 0），并由低到高打印酒店的价格。
 *
 * 备注
 * 酒店价格数组 A 和小明的心理价位 x 均为整型数据（0 < n, k, x < 10000）
 * 优先选择价格最接近心理价位的酒店，若两家酒店距离心理价位差价相同，则选择价格较低的酒店。（比如 100 元和 300 元距离心理价位 200 元同样接近，此时选择 100 元）
 * 酒店价格可能相同重复
 *
 * 输入描述：
 * 第一行：n, k, x
 * 第二行：A[0] A[1] A[2] … A[n-1]
 *
 * 输出描述：
 * 从低到高打印筛选出的酒店价格
 *
 */
public class 预订酒店 {
    public static class Hotel{
        //节点价格
        int price;
        //与x的差额，排序用
        int diff;

        public Hotel(int price, int diff) {
            this.price = price;
            this.diff = diff;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int x = scanner.nextInt();

        //todo: 不能用TreeSet，因为如果判断条件判断出来两个元素相同的话会进行覆盖的，导致集合中包含的不是所有元素
       // TreeSet<Hotel> hotelTreeSet = new TreeSet<>((a,b)->a.diff-b.diff!=0?a.diff-b.diff:a.price-b.price);
        List<Hotel> sortList = new ArrayList<>();
        for(int i=0; i<n; i++){
            int price = scanner.nextInt();
            sortList.add(new Hotel(price, Math.abs(price-x)));
        }
        sortList.sort((a, b)->a.diff-b.diff!=0?a.diff-b.diff:a.price-b.price);

        //比如说n是：-2，0，2，x=2,那么treeSet中的顺序是0，2，2，但是输出要按照-2，0，2这种顺序，所以还要排序
        int[] res = new int[k];
        for(int j=0; j<k; j++){
            res[j] = sortList.get(j).price;
        }

        Arrays.sort(res);

        StringJoiner stringJoiner = new StringJoiner(" ");
        for(int num : res){
            stringJoiner.add(num+"");
        }
        System.out.println(stringJoiner);
    }


    //这个是答案中的，应该更好些，占用内存更小些
//        public static void main(String[] args) {
//            Scanner sc = new Scanner(System.in);
//
//            int n = sc.nextInt();
//            int k = sc.nextInt();
//            int x = sc.nextInt();
//
//            int[][] A = new int[n][2];
//
//            for (int i = 0; i < n; i++) {
//                A[i][0] = sc.nextInt(); // 酒店价格
//                A[i][1] = Math.abs(A[i][0] - x); // 酒店价格和 x 的差距，差距越小，则越接近x
//            }
//
//            // 优先选择价格最接近心理价位的酒店，若两家酒店距离心理价位差价相同，则选择价格较低的酒店
//            Arrays.sort(A, (a, b) -> a[1] != b[1] ? a[1] - b[1] : a[0] - b[0]);
//
//            // 选择前k个
//            int[] res = new int[k];
//            for (int i = 0; i < k; i++) {
//                res[i] = A[i][0];
//            }
//
//            // 由低到高打印酒店的价格
//            Arrays.sort(res);
//
//            StringJoiner sj = new StringJoiner(" ");
//            for (int re : res) {
//                sj.add(re + "");
//            }
//
//            System.out.println(sj);
//        }

}
