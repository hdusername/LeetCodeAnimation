/**
 * 某地有N个广播站，站点之间有些有连接，有些没有。有连接的站点在接受到广播后会互相发送。
 * 给定一个N*N的二维数组matrix,数组的元素都是字符’0’或者’1’。
 * matrix[i][j] = ‘1’, 代表i和j站点之间有连接，
 * matrix[i][j] = ‘0’, 代表没连接，
 * 现在要发一条广播，问初始最少给几个广播站发送，才能保证所有的广播站都收到消息。
 *
 * 输入描述：
 * 从stdin输入，共一行数据，表示二维数组的各行，用逗号分隔行。保证每行字符串所含的字符数一样的。
 * 比如：110,110,001。
 *
 *
 * 110101,110000,001100,101100,000011,100011
 *
 * 110101,111000,011100,101100,000011,100011
 *
 * 110101,110000,001100,101110,000111,100011
 *
 * 输出描述：
 * 返回初始最少需要发送广播站个数
 */
import java.util.Scanner;

public class 发广播 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] matrix = sc.nextLine().split(",");

        System.out.println(getResult(matrix));
    }

    public static int getResult(String[] matrix) {
        int n = matrix.length;

        UnionFindSet ufs = new UnionFindSet(n);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (matrix[i].charAt(j) == '1') {
                    ufs.union(i, j);
                }
            }
        }

        return ufs.count;
    }
}

// 并查集实现
class UnionFindSet {
    int[] fa;
    int count;

    public UnionFindSet(int n) {
        this.fa = new int[n];
        for (int i = 0; i < n; i++) fa[i] = i;
        this.count = n;
    }

    public int find(int x) {
        if (x != this.fa[x]) {
            this.fa[x] = this.find(this.fa[x]);
            return this.fa[x];
        }
        return x;
    }

    public void union(int x, int y) {
        int x_fa = this.find(x);
        int y_fa = this.find(y);

        if (x_fa != y_fa) {
            //这里设置进去的x_fa是满足x == this.fa[x]条件的，所以有获取fa[y_fa]的父的是一定可以获取到的
            this.fa[y_fa] = x_fa;
            this.count--;
        }
    }
}