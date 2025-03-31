import java.util.Scanner;

/**
 * 有一名科学家想要从一台古董电脑中拷贝文件到自己的电脑中加以研究。但此电脑除了有一个3.5寸软盘驱动器以外，没有任何手段可以将文件持贝出来，而且只有一张软盘可以使用。
 * 因此这一张软盘是唯一可以用来拷贝文件的载体。
 * 科学家想要尽可能多地将计算机中的信息拷贝到软盘中，做到软盘中文件内容总大小最大。
 * 已知该软盘容量为1474560字节。文件占用的软盘空间都是按块分配的，每个块大小为512个字节。一个块只能被一个文件使用。拷贝到
 * 软盘中的文件必须是完整的，且不能采取任何压缩技术。
 *
 * 输入描述：
 * 第1行为一个整数N，表示计算机中的文件数量。1 ≤ N ≤ 1000.
 * 接下来的第2行到第N+1行(共N行)，每行为一个整数，表示每个文件的大小Si，单位为字节。
 * 0 ≤ i < N,0 ≤ Si
 *
 * 输出描述：
 * 科学家最多能拷贝的文件总大小
 *
 * 备注：
 * 为了充分利用软盘空间，将每个文件在软盘上占用的块记录到本子上。即真正占用软盘空间的只有文件内容本身。
 *
 * 用例输入：
 * 3
 * 737270
 * 737272
 * 737288
 *
 * 输出：
 * 1474542
 *
 * 解释：
 * 3个文件中，每个文件实际占用的大小分别为737280字节、737280字节、737792字节。
 */
public class 通过软盘拷贝文件 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        //软盘容量，单位byte
        int disk_b = 1474560;
        //每个块的字节数，单位byte
        int base_block = 512;
        //软盘总块数
        int total_block = disk_b/base_block;

        //各个文件大小，单位byte
        int[] size_b = new int[n];
        //各个文件所占块数
        int[] size_block = new int[n];
        for(int i=0; i<n; i++){

            size_b[i] = scanner.nextInt();
            size_block[i] = size_b[i]/base_block + (size_b[i]%base_block==0?0:1);
        }
        int[][] dp = new int[n+1][total_block+1];

        for(int i=1; i<n+1; i++){
            int dp_size_b = size_b[i-1];
            int dp_size_block = size_block[i-1];

            //可以看一下《工作安排》对比下i、j的取值
            for(int j=1; j<total_block+1; j++){
                if(dp_size_block>j){
                    dp[i][j] = dp[i-1][j];
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], dp_size_b+dp[i-1][j-dp_size_block]);
                }
            }
        }

        System.out.println(dp[n][total_block]);
    }
}
