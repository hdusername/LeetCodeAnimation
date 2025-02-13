import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * 为了达到新冠疫情精准防控的需要，为了避免全员核酸检测带来的浪费，需要精准圈定可能被感染的人群。
 * 现在根据传染病流调以及大数据分析，得到了每个人之间在时间、空间上是否存在轨迹交叉。
 * 现在给定一组确诊人员编号（X1,X2,X3,...,Xn），在所有人当中，找出哪些人需要进行核酸检测，输出需要进行核酸检测的人数。（注意：确诊病例自身不需要再做核酸检测）
 * 需要进行核酸检测的人，是病毒传播链条上的所有人员，即有可能通过确诊病例所能传播到的所有人。
 * 例如：A是确诊病例，A和B有接触、B和C有接触、C和D有接触、D和E有接触，那么B\C\D\E都是需要进行核酸检测的人。
 *
 * 输入描述：
 * 第一行为总人数 N
 * 第二行为确认病例人员编号（确诊病例人员数量 < N），用逗号分割
 * 第三行开始，为一个 N * N 的矩阵，表示每个人员之间是否有接触，0表示没有接触，1表示有接触。
 *
 * 输出描述：
 * 整数：需要做核酸检测的人数
 *
 * 备注：
 * 人员编号从0开始
 * 0 < N < 100
 *
 * 用例输入：
 * 5
 * 1 2
 * 1,1,0,1,0
 * 1,1,0,0,0
 * 0,0,1,0,1
 * 1,0,0,1,0
 * 0,0,1,0,1
 *
 * 输出：
 * 3
 *
 * 解释；
 * 编号为1、2号的人员，为确诊病例。
 * 1号和0号有接触，0号和3号有接触。
 * 2号和4号有接触。
 * 所以，需要做核酸检测的人是0号、3号、4号，总计3人需要进行核酸检测。
 */
public class 精准核酸检测 {

    /**
     * 创建并查集
     */
    static class UnionSet{
        int[] unionSet;
        //初始化并查集
        public UnionSet(int n) {
            unionSet= new int[n];
            for(int i=0; i<n; i++){
                unionSet[i]=i;
            }
        }

        public int find(int id){
            int fa_id = unionSet[id];
            if(fa_id == id){
                //没有关联
                return id;
            }else {
                return unionSet[id]=find(fa_id);
            }
        }

        public void union(int id1, int id2){
            int id1_fa = find(id1);
            int id2_fa = find(id2);
            if(id1_fa != id2_fa){
                unionSet[id1_fa] = id2_fa;
            }
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        //获取总人数
        int n = Integer.parseInt(scanner.nextLine());

        UnionSet unionSet = new UnionSet(n);
        //获取确诊人员
        int[] confirms = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();

        for(int i=0; i<n; i++){
            int[] relations = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
            for(int j=0; j<relations.length; j++){
                //构建人员之间的关系
                if(relations[j]==1){
                    unionSet.union(i, j);
                }
            }
        }

        //把确诊的根父类保存到set中，便于后续比较
        HashSet<Integer> rootRelation = new HashSet();
        for(int i=0; i<confirms.length; i++){
            rootRelation.add(unionSet.find(confirms[i]));
        }
        //取出所有人的根父类，比较是否与确诊的根父类相同，相同的话说明与确诊有关联，需要检测
        int ans =0;
        for(int i=0; i<n; i++){
            if(rootRelation.contains(unionSet.find(i))){
                ans++;
            }
        }

        //结果需要减去已经确诊的人，已经确诊的不用检测
        System.out.println(ans-confirms.length);
    }
}
