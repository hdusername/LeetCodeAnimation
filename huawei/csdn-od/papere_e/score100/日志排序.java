import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *运维工程师采集到某产品线网运行一天产生的日志n条，现需根据日志时间先后顺序对日志进行排序，日志时间格式为H:M:S.N。
 * H表示小时(0~23)
 * M表示分钟(0~59)
 * S表示秒(0~59)
 * N表示毫秒(0~999)
 * 时间可能并没有补全，也就是说，01:01:01.001也可能表示为1:1:1.1。
 *
 * 输入描述：
 * 第一行输入一个整数n表示日志条数，1<=n<=100000，接下来n行输入n个时间。
 *
 * 输出描述：
 * 按时间升序排列之后的时间，如果有两个时间表示的时间相同，则保持输入顺序。
 */
public class 日志排序 {

    /**
     * todo：本解决代码还是会出现超时异常，待后续修复
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
//        Scanner scanner = new Scanner(System.in);
//        int n = Integer.parseInt(scanner.nextLine());
        //这样写是为了避免超时异常的
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        //key：输入的时间，value：
        HashMap<String, Long> map = new HashMap<>();
        //HashMap<String, Integer> firstMap = new HashMap<>();
        String[] times = new String[n];
        for(int i=0; i<n; i++){
            String time = br.readLine();
            times[i] = time;
            map.put(time, transTime(time));
        }
        Arrays.sort(times, (a, b)->Long.compare(map.get(a), map.get(b)));


        Arrays.stream(times).forEach(a->System.out.println(a));
    }

    private static Long transTime(String time) {

        Long ms = 0l;
       // String[] split = time.split(":");

        Pattern compile = Pattern.compile("(\\d+):(\\d+):(\\d+).(\\d+)");
        Matcher matcher = compile.matcher(time);
        matcher.find();

        ms+=Long.parseLong(matcher.group(1))*60*60*1000;
        ms+=Long.parseLong(matcher.group(2))*60*1000;
        ms+=Long.parseLong(matcher.group(3))*1000;
        ms+=Long.parseLong(matcher.group(4));

        return ms;
    }
}

//标准答案
//import java.io.*;
//import java.util.Arrays;
//import java.util.StringJoiner;
//
//public class Main {
//    static class Log {
//        String time;
//        long timestamp;
//
//        public Log(String time) {
//            this.time = time;
//            this.timestamp = convert(time);
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        int n = Integer.parseInt(br.readLine());
//
//        Log[] logs = new Log[n];
//        for (int i = 0; i < n; i++) {
//            logs[i] = new Log(br.readLine());
//        }
//
//        Arrays.sort(logs, (a, b) -> Long.compare(a.timestamp, b.timestamp));
//
//        StringJoiner sj = new StringJoiner("\n");
//        for (Log log : logs) {
//            sj.add(log.time);
//        }
//        System.out.println(sj);
//    }
//
//    public static long convert(String log) {
//        String[] tmp = log.split("[:.]");
//        long H = Long.parseLong(tmp[0]) * 60 * 60 * 1000;
//        long M = Long.parseLong(tmp[1]) * 60 * 1000;
//        long S = Long.parseLong(tmp[2]) * 1000;
//        long N = Long.parseLong(tmp[3]);
//        return H + M + S + N;
//    }
//}