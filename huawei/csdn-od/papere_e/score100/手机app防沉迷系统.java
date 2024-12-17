import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 智能手机方便了我们生活的同时，也侵占了我们不少的时间。
 * “手机App防沉迷系统”能够让我们每天合理地规划手机App使用时间，在正确的时间做正确的事。
 * 它的大概原理是这样的：
 * 在一天24小时内，可以注册每个App的允许使用时段
 * 一个时间段只能使用一个App，举例说明：不能同时在9:00-10:00注册App2和App3
 * App有优先级，数值越高，优先级越高。注册使用时段时，如果高优先级的App时间和低优先级的时段有冲突，则系统会自动注销低优先级的时段，如果App的优先级相同，则后添加的App不能注册。
 *请编程实现，根据输入数据注册App，并根据输入的时间点，返回时间点使用的App名称，如果该时间点没有注册任何App，请返回
 *
 * 输入描述
 * 第一行表示注册的App数量 N（N ≤ 100）
 * 第二部分包括 N 行，每行表示一条App注册数据
 * 最后一行输入一个时间点，程序即返回该时间点使用的App
 * 数据说明如下：
 * N行注册数据以空格分隔，四项数依次表示：App名称、优先级、起始时间、结束时间
 * 优先级1~5，数字越大，优先级越高
 * 时间格式 HH:MM，小时和分钟都是两位，不足两位前面补0
 * 起始时间需小于结束时间，否则注册不上
 * 注册信息中的时间段包含起始时间点，不包含结束时间点
 *
 * 输出描述
 * 输出一个字符串，表示App名称，或NA表示空闲时间
 *
 * 备注
 * 用例保证时间都介于 00:00 - 24:00 之间；
 * 用例保证数据格式都是正确的，不用考虑数据输入行数不够、注册信息不完整、字符串非法、优先级超限、时间格式不正确的问题；
 */
public class 手机app防沉迷系统 {

    public static class App{
        public String appName;
        public Integer priority;
        public Integer startTime;
        public Integer endTime;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer n = Integer.parseInt(scanner.nextLine());

       // List<App> apps = new ArrayList<>();
        List<App> registApps = new ArrayList<>();


        out: for(int i=0;i<n;i++){
            String[] s = scanner.nextLine().split(" ");
            App app = new App();
            app.appName = s[0];
            app.priority = Integer.parseInt(s[1]);
            app.startTime = convert(s[2]);
            app.endTime = convert(s[3]);

            //记录删除已登记的app的角标
            List<Integer> dels = new ArrayList<>();
            //apps.add(app);
            if(app.startTime>=app.endTime){
                //起始时间要小于结束时间
                continue;
            }
            for(int j=0;j<registApps.size();j++){
                //如果没有交集
                if(app.startTime>=registApps.get(j).endTime || registApps.get(j).startTime>=app.endTime){
                    //registApps.add(app);
                }
                //如果有冲突先比较优先级
                else if(app.priority>registApps.get(j).priority){
                    //这里先记录已经注册的app优先级低需要删除的角标，因为与第一个比较满足这个条件，如果与已注册的第二个比不满足这个条件了，是不需要删除的，比较第二个时下面的continue out会直接跳出
                    dels.add(j);
                }else {
                    //不满足优先级条件的，此app无需再进行判断，直接判断下一个app
                    continue out;
                }
            }

            //与已注册的app进行比较后加入到已注册清单中
            registApps.add(app);
            //从前向后删除角标会变化，所以要从后向前删除
            for(int z=dels.size()-1;z>=0;z--){
                //注意：这里一定要用int，不然remove方法会认为是按元素删除，而不是按角标删除
                int j = dels.get(z);
                registApps.remove(j);
            }


        }
        Integer targetTime = convert(scanner.nextLine());
        for(int i = 0;i<registApps.size();i++){
            if(registApps.get(i).startTime<=targetTime && registApps.get(i).endTime>targetTime){
                System.out.println(registApps.get(i).appName);
                return ;
            }
        }
        System.out.println("NA");

    }

    public static Integer convert(String time){
        String[] s = time.split(":");
        int hour = Integer.parseInt(s[0]);
        int minus = Integer.parseInt(s[1]);

        return hour*60+minus;
    }
}
