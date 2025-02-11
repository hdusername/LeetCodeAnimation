import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 静态扫描可以快速识别源代码的缺陷，静态扫描的结果以扫描报告作为输出：
 * 文件扫描的成本和文件大小相关，如果文件大小为 N，则扫描成本为 N 个金币
 * 扫描报告的缓存成本和文件大小无关，每缓存一个报告需要 M 个金币
 * 扫描报告缓存后，后继再碰到该文件则不需要扫描成本，直接获取缓存结果
 *
 * 给出源代码文件标识序列和文件大小序列，求解采用合理的缓存策略，最少需要的金币数。
 *
 * 输入描述：
 * 第一行为缓存一个报告金币数：M
 * 第二行为文件标识序列：F1,F2,F3,....,Fn
 * 第三行为文件大小序列：S1,S2,S3,....,Sn
 *
 * 备注：
 * L ≤ M ≤ 100
 * 1 ≤ N ≤ 10000
 * 1 ≤ Fi ≤ 1000
 * 1 ≤ Si ≤ 10
 *
 */
public class 静态代码扫描服务 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = Integer.parseInt(sc.nextLine());
        int[] f = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] s = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // count用于保存每个文件出现的次数
        HashMap<Integer, Integer> count = new HashMap<>();
        // size用于保存文件的大小，即扫描成本
        HashMap<Integer, Integer> size = new HashMap<>();

        for(int i=0; i<f.length; i++){
            count.put(f[i], count.getOrDefault(f[i], 0)+1);
            size.putIfAbsent(f[i], s[i]);
        }

        int cost = 0;
        for(Integer file : count.keySet()){
            cost += Math.min(count.get(file)*size.get(file), m+size.get(file));
        }

        System.out.println(cost);
    }
}
