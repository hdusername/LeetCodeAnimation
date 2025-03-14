import java.util.Arrays;
import java.util.Scanner;

/**
 * 疫情期间需要大家保证一定的社交距离，公司组织开交流会议。
 * 座位一排共 N 个座位，编号分别为 [0, N - 1] 。
 * 要求员工一个接着一个进入会议室，并且可以在任何时候离开会议室。
 * 满足：
 * 每当一个员工进入时，需要坐到最大社交距离（最大化自己和其他人的距离的座位）；
 * 如果有多个这样的座位，则坐到索引最小的那个座位。
 *
 * 输入描述：
 * 会议室座位总数 seatNum
 *  1 ≤ seatNum ≤ 500
 * 员工的进出顺序 seatOrLeave 数组
 *  元素值为 1，表示进场
 *  元素值为负数，表示出场（特殊：位置 0 的员工不会离开）
 *  例如 -4 表示坐在位置 4 的员工离开（保证有员工坐在该座位上）
 *
 * 输出描述：
 * 最后进来员工，他会坐在第几个位置，如果位置已满，则输出-1。
 */
public class 最大社交距离 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int seatNum = Integer.parseInt(scanner.nextLine());

        int[] seatOrLeave = Arrays.stream(scanner.nextLine().replace("[", "").replace("]", "")
                .replace(" ", "").split(",")).mapToInt(Integer::parseInt).toArray();

        //初始化座位
        int[] seats = new int[seatNum];
        //索引为0的位置是否有人坐
       // boolean isFirstIdxSeat = false;
        for(int i=0; i<seatOrLeave.length; i++){


            if(seatOrLeave[i]==1){

                if(!(seats[0]==1)){
                    //第一个位置没人坐直接坐在第一个位置
                    seats[0]=1;
                    continue;
                }

                int j = 0;
                int startIdx = 0;
                boolean isStart = false;
                //当前座位情况中距离最大的从第一个没坐人到当前可能坐人位置的长度,也就是最大社交距离
                //初始化为-1是因为有可能距离为0，如果座位如下所示
                //1 0 1
                //此时再进来一个人，最大社交距离就是0，设置为-1，这个人才会进入判断做到这个位置上
                int maxSeatDis = -1;
                //当前判断的人需要坐在哪个位置
                int seatIdx = -1;
                while (j<seats.length){
                    if(seats[j]==0 && !isStart){
                        //找到第一个还没被占的座位
                        isStart = true;
                        startIdx = j;

                    }else if((seats[j]==1 && isStart)){
                        //结束位置取从开始位置一直延续没坐人的位置中，第一个坐人的位置的索引
                        int endIdx = j;


                        //连续的没坐人的长度
                        int len = endIdx-startIdx;
//1 0 0 0 1
                        //当前坐人位置与两侧的最大社交距离，这个社交距离其实取得是离人最近的距离是多少
                        //比如现在1代表坐人了，0代表未坐人，坐人的情况为1 0 0 0 1 0 0 0 0 1，再坐人会做到位置2上，因为其距离左侧右侧都是1个位置，如果坐在6位置
                        //上距离左侧也是1，坐在7位置上距离右侧是1，其实在偶数个位置中就是取得是最小间隔距离
                        //既然间隔都是1，那么选择索引小的，当然是2位置
                        int curSeatDis = len/2-(len%2==0?1:0);
//                        if(len%2==0){
//
//                        }
                        if(maxSeatDis<curSeatDis){

                            seatIdx = curSeatDis+startIdx;
                            maxSeatDis = curSeatDis;

                        }
                        isStart = false;
                    }
                    if (seats[j]==0 && j == seats.length - 1){
                        int endIdx = seats.length;
                        if(maxSeatDis<endIdx-startIdx){

                            seatIdx = seats.length - 1;
                            //走到这里说明已经走到当前判断座位的最后了，不用再给maxSeatDis赋值了

                        }
                        isStart = false;
                    }
                    j++;
                }

                if(seatIdx==-1){
                    //说明位置满了
                    System.out.println(-1);
                    return;
                }else {
                    seats[seatIdx] = 1;
                }

                if(i==seatOrLeave.length-1){
                    System.out.println(seatIdx);
                    return;
                }

            }else {
                //员工离开此位置
                int delIdx = Math.abs(seatOrLeave[i]);
                seats[delIdx] = 0;
            }
        }
    }

//标准答案
//    public class Main {
//        public static void main(String[] args) {
//            Scanner sc = new Scanner(System.in);
//
//            int seatNum = Integer.parseInt(sc.nextLine());
//
//            String tmp = sc.nextLine();
//            int[] searOrLeave =
//                    Arrays.stream(tmp.substring(1, tmp.length() - 1).split(", "))
//                            .mapToInt(Integer::parseInt)
//                            .toArray();
//
//            System.out.println(getResult(seatNum, searOrLeave));
//        }
//
//        public static int getResult(int seatNum, int[] seatOrLeave) {
//            // 记录已经坐人位置的序号
//            ArrayList<Integer> seatIdx = new ArrayList<>();
//
//            // 记录题解
//            int lastSeatIdx = -1;
//
//            // 遍历员工的进出顺序
//            for (int info : seatOrLeave) {
//                // 如果当前元素值为负数，表示出场（特殊：位置 0 的员工不会离开）
//                // 例如 -4 表示坐在位置 4 的员工离开（保证有员工坐在该座位上）
//                if (info < 0) {
//                    int leaveIdx = -info;
//                    seatIdx.remove(new Integer(leaveIdx));
//                    continue;
//                }
//
//                // 如果当前元素值为 1，表示进场
//                // 如果没有空闲位置，则坐不下
//                if (seatIdx.size() == seatNum) {
//                    // 假设当前人就是最后一个人
//                    lastSeatIdx = -1;
//                    continue;
//                }
//
//                if (seatIdx.size() == 0) {
//                    // 当前人员进场前，座位上没有人，则当前人员是第一个进场的，直接坐第0个位置
//                    seatIdx.add(0);
//                    lastSeatIdx = 0;
//                } else if (seatIdx.size() == 1) {
//                    // 当前人员进场前，座位上只有一个人，那么这个人肯定坐在第0个位置，则当前进场的人坐在 seatNum - 1 位置才能离 0 位置最远
//                    seatIdx.add(seatNum - 1);
//                    lastSeatIdx = seatNum - 1;
//                } else {
//                    // 记录具有最大社交距离的座位号
//                    int bestSeatIdx = -1;
//                    // 记录最大社交距离
//                    int bestSeatDis = -1;
//
//                    // 找到连续空闲座位区域（该区域左、右边界是坐了人的座位）
//
//                    int left = seatIdx.get(0); // 左边界
//                    for (int i = 1; i < seatIdx.size(); i++) {
//                        int right = seatIdx.get(i); // 右边界
//
//                        // 连续空闲座位区域的长度
//                        int dis = right - left - 1;
//
//                        // 如果连续空闲座位区域长度为0，则无法坐人，此时遍历下一个连续空闲座位区域
//                        // 如果连续空闲座位区域长度大于0，则可以坐人
//                        if (dis > 0) {
//                            // 当前空闲区域能产生的最大社交距离
//                            int curSeatDis = dis / 2 - (dis % 2 == 0 ? 1 : 0);
//                            // 当前空闲区域中具备最大社交距离的位置
//                            int curSeatIdx = left + curSeatDis + 1;
//
//                            // 保留最优解
//                            if (curSeatDis > bestSeatDis) {
//                                bestSeatDis = curSeatDis;
//                                bestSeatIdx = curSeatIdx;
//                            }
//                        }
//
//                        left = right;
//                    }
//
//                    // 如果最后一个座位，即第 seatNum - 1 号座位没有坐人的话，比如 1 0 0 0 1 0 0 0 0，此时最后一段空闲区域是没有右边界的，需要特殊处理
//                    if (seatIdx.get(seatIdx.size() - 1) < seatNum - 1) {
//                        // 此时可以直接坐到第 seatNum - 1 号座位，最大社交距离为 curSeatDis
//                        int curSeatDis = seatNum - 1 - seatIdx.get(seatIdx.size() - 1) - 1;
//                        int curSeatIdx = seatNum - 1;
//
//                        // 保留最优解
//                        if (curSeatDis > bestSeatDis) {
//                            bestSeatIdx = curSeatIdx;
//                        }
//                    }
//
//                    // 如果能坐人，则将坐的位置加入seatIdx中
//                    if (bestSeatIdx > 0) {
//                        seatIdx.add(bestSeatIdx);
//                        seatIdx.sort((a, b) -> a - b);
//                    }
//
//                    // 假设当前人就是最后一个人，那么无论当前人是否能坐进去，都更新lastSeatIdx = bestSeatIdx
//                    lastSeatIdx = bestSeatIdx;
//                }
//            }
//
//            return lastSeatIdx;
//        }
//    }
}
