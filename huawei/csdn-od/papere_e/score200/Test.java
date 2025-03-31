import java.util.Scanner;
public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 输入充电设备个数
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = sc.nextInt(); // 输入每个充电设备的输出功率
        }
        int m = sc.nextInt(); // 输入充电站最大输出功率

        int[] f = new int[m + 1]; // 初始化动态规划数组f

        // 遍历每个充电设备
        for (int i = 0; i < n; ++i) {
            // 从最大功率m开始，向下遍历到当前设备的功率a[i]
            for (int j = m; j >= a[i]; --j) {
                f[j] = Math.max(f[j], f[j - a[i]] + a[i]); // 更新f[j]
            }
        }

        int max_f = 0;
        for (int i = 0; i <= m; ++i) {
            if (f[i] > max_f) {
                max_f = f[i];
            }
        }

        System.out.println(max_f); // 输出最优功率和

        sc.close();
    }
}
