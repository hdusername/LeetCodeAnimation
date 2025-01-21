import java.util.HashMap;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 公司某部门软件教导团正在组织新员工每日打卡学习活动，他们开展这项学习活动已经一个月了，所以想统计下这个月优秀的打卡员工。每个员工会对应一个id，每天的打卡记录记录当天打卡员工的id集合，一共30天。
 * 请你实现代码帮助统计出打卡次数top5的员工。加入打卡次数相同，将较早参与打卡的员工排在前面，如果开始参与打卡的时间还是一样，将id较小的员工排在前面。
 * 注：不考虑并列的情况，按规则返回前5名员工的id即可，如果当月打卡的员工少于5个，按规则排序返回所有有打卡记录的员工id。
 *
 * 输入描述：
 * 第一行输入为新员工数量N，表示新员工编号id为0到N-1，N的范围为[1,100]
 * 第二行输入为30个整数，表示每天打卡的员工数量，每天至少有1名员工打卡。
 * 之后30行为每天打卡的员工id集合，id不会重复。
 *
 * 输出描述：
 * 按顺序输出打卡top5员工的id，用空格隔开。
 */
public class 优秀学员统计 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        //key为编号id，value为打卡次数
        HashMap<Integer, Integer> map = new HashMap<>();
        //key为编号id，value为第一次打卡的时刻，存的出现的索引
        HashMap<Integer, Integer> firstMap = new HashMap<>();


        int[] days = new int[30];

        for(int i=0; i<30; i++){
            days[i] = scanner.nextInt();
        }
        for(int i=0; i<days.length; i++){
            for(int j=0; j<days[i]; j++){
                int id = scanner.nextInt();
                map.put(id, map.getOrDefault(id, 0)+1);
                //放入第一次打卡的时机（索引）
                firstMap.putIfAbsent(id, i);
            }
        }

        StringJoiner stringJoiner = new StringJoiner(" ");
        map.entrySet().stream().sorted((a, b)->{

            //这里用+-号也可以，只是Long类型的不能直接用+-，要用Long.compare方法
            if(a.getValue()==b.getValue()){
                if(firstMap.get(a.getKey())==firstMap.get(b.getKey())){
                    return Integer.compare(a.getKey(), b.getKey());
                }else {
                    return Integer.compare(firstMap.get(a.getKey()), firstMap.get(b.getKey()));
                }
            }else {
                return Integer.compare(b.getValue(), a.getValue());
            }

        }).limit(5).forEach(a->stringJoiner.add(a.getKey()+""));

        System.out.println(stringJoiner);
    }
}
