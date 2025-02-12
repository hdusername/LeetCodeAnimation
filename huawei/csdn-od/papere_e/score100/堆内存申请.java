import java.util.Arrays;
import java.util.Scanner;

/**
 * 有一个总空间为100字节的堆，现要从中新申请一块内存，内存分配原则为：优先紧接着前一块已使用内存，分配空间足够且最接近申请大小的空闲内存。
 *
 * 输入描述：
 * 第1行是1个整数，表示期望申请的内存字节数
 * 第2到第N行是用空格分割的两个整数，表示当前已分配的内存的情况，每一行表示一块已分配的连续内存空间，每行的第1和第2个整数分别表示偏移地址和内存块大小，如：
 * 0 1
 * 3 2
 * 表示 0 偏移地址开始的 1 个字节和 3 偏移地址开始的 2 个字节已被分配，其余内存空闲。
 *
 * 输出描述：
 * 若申请成功，输出申请到内存的偏移；
 * 若申请失败，输出 -1。
 *
 * 输出描述：
 * 若输入信息不合法或无效，则申请失败
 * 若没有足够的空间供分配，则申请失败
 * 堆内存信息有区域重叠或有非法值等都是无效输入
 *
 * 用例输入：
 * 1
 * 0 1
 * 3 2
 *
 * 输出：
 * 1
 *
 * 解释：
 * 堆中已使用的两块内存是偏移从0开始的1字节和偏移从3开始的2字节，空闲的两块内存是偏移从1开始2个字节和偏移从5开始95字节，根据分配原则，新申请的内存应从1开始分配1个字节，所以输出偏移为1。
 */
public class 堆内存申请 {

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        // 读取整数
//        int malloc_size = sc.nextInt();
//        sc.nextLine();  // 消耗掉换行符
//
//        System.out.println("输入的内存大小: " + malloc_size);
//
//        // 然后再读取整行
//        System.out.println("请输入其他信息:");
//        String line = sc.nextLine();
//        System.out.println("你输入的是: " + line);

        Scanner scanner = new Scanner(System.in);
        int target = Integer.parseInt(scanner.nextLine());

        int ans = -1;
        int[] useds = new int[100];

        try {
            //todo:填充内存使用列表,这里的hasNextLine如果没有ctrl+z或者ctrl+d结束输入，就会一直卡着
            //但是在考试时应该是没关系的，系统可以自动加上输入结束标志，只是在idea中运行需要手动输入ctrl+d
            while (scanner.hasNextLine()) {
//                String line = ;
//                if (line.isEmpty()) {
//                    break;  // 空行作为结束条件
//                }
                int[] s = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                int start = s[0];
                int len = s[1];
                int end = start + len;
                for (int i = start; i < end; i++) {
                    if (useds[i] == 0) {
                        //标识已经占用了
                        useds[i] = 1;
                    } else {
                        //说明区域重叠了
                        System.out.println(-1);
                        return;
                    }
                }
            }
            //标识是否找到了空闲开始位置
            boolean isStart = false;
            int startIdx =0;
            int endIdx =0;
            int minLen = Integer.MAX_VALUE;

            for(int i=0; i<useds.length; i++){
                if(useds[i]==0 && !isStart){
                    startIdx = i;
                    isStart = true;

                }else if(useds[i]==1 || i==useds.length-1) {//找结束位置
                    if(isStart){
                        endIdx = i==useds.length-1?i+1:i;

                        if(endIdx-startIdx>=target && endIdx-startIdx<minLen){
                            minLen = endIdx-startIdx;
                            ans = startIdx;


                        }
                        isStart=false;
                    }
                }
            }

            System.out.println(ans);
        }catch (Exception e){
            //输入不合法
            System.out.println(ans);
        }
    }

    //以下为给出的标准答案
//    static int malloc_size;
//    static ArrayList<int[]> used = new ArrayList<>();
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        malloc_size = sc.nextInt();
//
//        while (sc.hasNextInt()) {
//            int start = sc.nextInt();
//            int count = sc.nextInt();
//            int end = start + count - 1;
//
//            used.add(new int[]{start, end});
//        }
//
//        System.out.println(solution());
//    }
//
//    public static int solution() {
//        // 申请内存大小非法
//        if (malloc_size <= 0 || malloc_size > 100) {
//            return -1;
//        }
//
//        // 总空间, 对应位置元素值标记为0表示未被分配，标记为1表示已分配
//        int[] memory = new int[100];
//
//        // 已分配的内存情况
//        for (int[] range : used) {
//            int start = range[0];
//            int end = range[1];
//
//            // 起始分配位置非法
//            if (start < 0 || start >= 100) {
//                return -1;
//            }
//
//            // 截止分配位置非法
//            if (end < 0 || end >= 100) {
//                return -1;
//            }
//
//            for (int i = start; i <= end; i++) {
//                if (memory[i] != 0) {
//                    // 存在内存占用重叠，则非法
//                    return -1;
//                } else {
//                    // 标记已分配的内存
//                    memory[i] = 1;
//                }
//            }
//        }
//
//        return getAns(memory);
//    }
//
//    private static int getAns(int[] memory) {
//        // 记录最佳申请起始位置
//        int ans = -1;
//        // 记录最接近的申请内存的空闲内存块的大小
//        int min_size = Integer.MAX_VALUE;
//
//        int l = 0;
//        while (l < 100) {
//            // 找到空闲内存左边界 l（包含）
//            while (l < 100 && memory[l] != 0) {
//                l++;
//            }
//
//            // 2024.04.04 补充此逻辑，否则下面用例会有问题
//            /*
//             * 1
//             * 0 100
//             */
//            if (l >= 100) {
//                break;
//            }
//
//            // 找到空闲内存右边界 r（不包含）
//            int r = l + 1;
//            while (r < 100 && memory[r] == 0) {
//                r++;
//            }
//
//            // 空闲内存大小
//            int free_size = r - l;
//
//            // 空闲内存 >= 申请内存 && 更接近申请内存大小
//            if (free_size >= malloc_size && free_size < min_size) {
//                min_size = free_size;
//                ans = l;
//            }
//
//            // 找下一个空闲内存
//            l = r + 1;
//        }
//        return ans;
//    }
}
