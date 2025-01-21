import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 磁盘的容量单位常用的有 M，G，T 这三个等级，它们之间的换算关系为：
 * 1T = 1024G
 * 1G = 1024M
 * 现在给定 n 块磁盘的容量，请对它们按从小到大的顺序进行稳定排序
 * 例如给定5块盘的容量：
 * 1T，20M，3G，10G6T，3M12G9M
 *
 * 排序后的结果为：
 * 20M，3G，3M12G9M，1T，10G6T
 *
 * 注意单位可以重复出现，上述 3M12G9M 表示的容量即为：3M+12G+9M，和 12M12G 相等。
 *
 * 输入描述：
 * 输入第一行包含一个整数 n，表示磁盘的个数
 * 2 ≤ n ≤ 100
 * 接下的 n 行，每行一个字符串（长度大于2，小于30），表示磁盘的容量，由一个或多个格式为mv的子串组成，其中 m 表示容量大小，v 表示容量单位，例如：20M，1T，30G，10G6T，3M12G9M。
 * 磁盘容量 m 的范围为 1 到 1024 的正整数
 * 容量单位 v 的范围只包含题目中提到的 M，G，T 三种，换算关系如题目描述
 *
 * 输出描述：
 * 输出 n 行，表示 n 块磁盘容量排序后的结果。
 */
public class 磁盘容量排序 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        //保存每个对应位置处磁盘容量是多少，不能直接保存原始字符串，因为要稳定排序，需要记住先后顺序，同时也有可能会发生磁盘大小的字符串一致的情况
        HashMap<Integer, Long> map = new HashMap<>();
        HashMap<Integer, String> sourcemap = new HashMap<>();
        for(int i=0; i<n; i++){
            String diskStr = scanner.nextLine();
            Long content = getContent(diskStr);
            map.put(i, content);
            sourcemap.put(i, diskStr);
        }
        map.entrySet().stream().sorted((a, b)->{
           return a.getValue()==b.getValue()?a.getKey()-b.getKey(): Long.compare(a.getValue() , b.getValue());
        }).forEach(a->System.out.println(sourcemap.get(a.getKey())));

    }

    private static Long getContent(String diskStr) {

        Pattern compile = Pattern.compile("(\\d+)([MGT])");
        Matcher matcher = compile.matcher(diskStr);
        Long sum = 0l;
        while (matcher.find()){

            long num = Long.parseLong(matcher.group(1));
            String sign = matcher.group(2);
            switch (sign){
                case "M":
                    sum+=num;
                    break;
                case "G":
                    sum+=num*1024;
                    break;
                case "T":
                    sum+=num*1024*1024;
                    break;
            }
        }
        return sum;
    }
}
