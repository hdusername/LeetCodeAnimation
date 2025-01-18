import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 记账本上记录了若干条多国货币金额，需要转换成人民币分（fen），汇总后输出。
 * 每行记录一条金额，金额带有货币单位，格式为："数字+单位"，可能是单独元，或者单独分，或者元与分的组合。
 * 要求将这些货币全部换算成人民币分（fen）后进行汇总，汇总结果仅保留整数，小数部分舍弃。
 * 元和分的换算关系都是 1:100，如下：
 * 1CNY=100fen（1元=100分）
 * 1HKD=100cents（1港元=100港分）
 * 1JPY=100sen（1日元=100仙）
 * 1EUR=100eurocents（1欧元=100欧分）
 * 1GBP=100pence（1英镑=100便士）
 *
 * 汇率表如下：
 * 100CNY = 1825JPY = 123HKD = 14EUR = 12GBP
 *
 * 输入描述：
 * 第一行输入为 N，N 表示记录数。0 < N < 100
 * 之后 N 行，每行表示一条货币记录，且该行只会是一种货币。
 *
 * 输出描述：
 * 将每行货币转换成人民币分（fen）后汇总求和，只保留整数部分。
 * 输出格式只有整数数字，不带小数，不带单位。
 */
public class 货币单位换算 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        HashMap<String, Double> map = new HashMap<>();
        map.put("CNY", 100.0);
        map.put("fen", 1.0);
        map.put("JPY", 100.0/1825*100);
        map.put("sen", 100.0/1825);
        map.put("HKD", 100.0/123*100);
        map.put("cents", 100.0/123);
        map.put("EUR", 100.0/14*100);
        map.put("eurocents", 100.0/14);
        map.put("GBP", 100.0/12*100);
        map.put("pence", 100.0/12);

        double ans =0;

        Pattern compile = Pattern.compile("(\\d+)(CNY|fen|JPY|sen|HKD|cents|EUR|eurocents|GBP|pence)");

        for(int i=0; i<n; i++){
            Matcher matcher = compile.matcher(scanner.nextLine());
            while (matcher.find()){
                String money = matcher.group(1);
                String unit = matcher.group(2);
                ans+= Integer.parseInt(money) * map.get(unit);
            }
        }

        System.out.println((int)ans);
    }
}
