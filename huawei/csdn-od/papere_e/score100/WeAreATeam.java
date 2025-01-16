import java.util.ArrayList;
import java.util.Scanner;

/**
 *总共有 n 个人在机房，每个人有一个标号（1<=标号<=n），他们分成了多个团队，需要你根据收到的 m 条消息判定指定的两个人是否在一个团队中，具体的：
 * 消息构成为 a b c，整数 a、b 分别代表两个人的标号，整数 c 代表指令
 * c == 0 代表 a 和 b 在一个团队内
 * c == 1 代表需要判定 a 和 b 的关系，如果 a 和 b 是一个团队，输出一行’we are a team’,如果不是，输出一行’we are not a team’
 * c 为其他值，或当前行 a 或 b 超出 1~n 的范围，输出‘da pian zi’
 *
 * 输入描述：
 * 第一行包含两个整数 n，m(1<=n,m<100000),分别表示有 n 个人和 m 条消息
 * 随后的 m 行，每行一条消息，消息格式为：a b c(1<=a,b<=n,0<=c<=1)
 *
 * 输出描述：
 * c ==1,根据 a 和 b 是否在一个团队中输出一行字符串，在一个团队中输出‘we are a team‘,不在一个团队中输出’we are not a team’
 * c 为其他值，或当前行 a 或 b 的标号小于 1 或者大于 n 时，输出字符串‘da pian zi‘
 * 如果第一行 n 和 m 的值超出约定的范围时，输出字符串”NULL“。
 *
 *
 */
public class WeAreATeam {
    //使用并查集的方式，因为如果1，2在也给团队中，2，3在一个团队中，那么1，3也是在一个团队中的，并查集正好符合这样的特性
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        //因为人员编号是从1开始的，而并查集的索引是从0开始的，所以为了让索引和人员编号对上，初始化为n+1的长度
        UnionSet unionSet = new UnionSet(n+1);

        int[][] messages = new int[m][3];
        for(int i=0; i<m; i++){
            messages[i][0] = scanner.nextInt();
            messages[i][1] = scanner.nextInt();
            messages[i][2] = scanner.nextInt();
        }

        //这个判断条件从题目中这里来：1<=n,m<100000
        if(!(1<=n && m<100000 && 1<=m && n<100000)){
            System.out.println("NULL");
            return;
        }

        ArrayList<String> ans = new ArrayList<>();

        for(int[] message : messages){
            int a = message[0];
            int b = message[1];
            int c = message[2];
            if((a<1 || b<1) || (a>n || b>n) || !(c==0 || c==1)){
                ans.add("da pian zi");
                continue;
            }
            if(c==0) {
                unionSet.union(a, b);
            }else {
                //c==1的情况
                if(unionSet.find(a)== unionSet.find(b)){
                    ans.add("we are a team");
                }else {
                    ans.add("we are not a team");
                }
            }

        }
        for(String ansStr : ans){
            System.out.println(ansStr);
        }
    }

    /**
     * 并查集类
     */
    public static class UnionSet{
        public  int[] teamRelas ;

        //构造并查集
        public UnionSet(int n) {
            teamRelas = new int[n];
            for(int i=0; i<n; i++){
                teamRelas[i]=i;
            }
        }

        /**
         * 建立起a和b的关系,如果他们的根不是一个值，就要将他们的根变为一个
         * @param a
         * @param b
         */
        public  void union(int a, int b){
            int root_a = find(a);
            int root_b = find(b);
            if(root_a != root_b){
                teamRelas[root_a]=root_b;
            }

        }

        /**
         * 功能：找到传入节点的根节点
         * @param node 传入的节点
         * @return 传入节点的根节点
         */
        public  int find(int node){
            if(teamRelas[node] != node) {
                //说明有根节点
                return teamRelas[node] = find(teamRelas[node]);
            }else {
                return node;
            }
        }


    }

}
