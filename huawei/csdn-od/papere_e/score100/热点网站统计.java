import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 企业路由器的统计页面，有一个功能需要动态统计公司访问最多的网页 URL top N。
 * 请设计一个，可以高效动态统计 Top N 的页面。
 *
 * 输入描述：
 * 每一行都是一个URL或一个数字
 * 如果是 URL，代表一段时间内的网页访问
 * 如果是一个数字 N，代表本次需要输出的 Top N 个 URL
 *
 * 输入约束：
 * 总访问网页数量小于 5000 个，单网页访问次数小于 65535 次
 * 网页 URL 仅由字母，数字和点分隔符组成，且长度小于等于 127 字节
 * 数字是正整数，小于等于 10 且小于当前总访问网页数
 *
 * 输出描述：
 * 每行输入要对应一行输出，输出按访问次数排序的前 N 个 URL，用逗号分隔。
 *
 * 输出要求：
 * 每次输出要统计之前所有输入，不仅是本次输入
 * 如果有访问次数相等的 URL，按 URL 的字符串字典序升序排列，输出排序靠前的 URL
 */
public class 热点网站统计 {

    public static void main(String[] args) {

        Map<String, Integer> map = new HashMap<>();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            try {
                int n = Integer.parseInt(s);
                StringJoiner stringJoiner = new StringJoiner(",");
                map.entrySet().stream().sorted((a, b) -> {
                    return a.getValue() - b.getValue() == 0 ? a.getKey().compareTo(b.getKey()) : b.getValue() - a.getValue();
                }).limit(n).forEach(a -> stringJoiner.add(a.getKey()));

                System.out.println(stringJoiner);
            } catch (Exception e) {
                String url = s;
                map.put(url, map.getOrDefault(url, 0) + 1);
            }
        }
    }
}
