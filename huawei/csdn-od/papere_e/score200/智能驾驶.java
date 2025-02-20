import java.util.LinkedList;
import java.util.Scanner;

/**
 * 有一辆汽车需要从 m * n 的地图左上角（起点）开往地图的右下角（终点），去往每一个地区都需要消耗一定的油量，加油站可进行加油。
 * 请你计算汽车确保从从起点到达终点时所需的最少初始油量。
 *
 * 说明：
 * 智能汽车可以上下左右四个方向移动
 * 地图上的数字取值是 0 或 -1 或 正整数：
 *          -1 ：表示加油站，可以加满油，汽车的油箱容量最大为100；
 *           0 ：表示这个地区是障碍物，汽车不能通过
 *        正整数：表示汽车走过这个地区的耗油量
 * 如果汽车无论如何都无法到达终点，则返回 -1
 *
 * 输入描述：
 * 第一行为两个数字，M，N，表示地图的大小为 M * N
 *  0 < M,N ≤ 200
 * 后面一个 M * N 的矩阵，其中的值是 0 或 -1 或正整数，加油站的总数不超过 200 个
 *
 * 输出描述：
 * 如果汽车无论如何都无法到达终点，则返回 -1
 * 如果汽车可以到达终点，则返回最少的初始油量
 */
public class 智能驾驶 {

    static class Car{
        int x;
        int y;
        //到达当前位置还剩余油量
        int remainOil;
        //初始油量（记录的是从0,0点出发时的油量）
        int initOil;

        boolean isAddOil;

        public Car(int x, int y, int remainOil, int initOil, boolean isAddOil) {
            this.x = x;
            this.y = y;
            this.remainOil = remainOil;
            this.initOil = initOil;
            this.isAddOil = isAddOil;
        }
    }

    static int[][] offsets = {{0,1},{0,-1},{1,0},{-1,0}};
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in).useDelimiter("[\n,]");
        int m = scanner.nextInt();//行
        int n = scanner.nextInt();//列

        int[][] matrix = new int[m][n];

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                matrix[i][j] = scanner.nextInt();
            }
        }

        LinkedList<Car> queue = new LinkedList<>();

        if(matrix[0][0]==-1){
            //起始位置就是加油站
            queue.add(new Car(0, 0, 100, 0, true));

        }else if(matrix[0][0]==0 || matrix[m-1][n-1]==0){
            //左上角和右下角不可达直接返回
            System.out.println(-1);
            return;
        }else {
            //将初始位置放入队列中,到达初始位置需要耗费油量matrix[0][0]，还剩余油量0
            queue.add(new Car(0, 0, 0, matrix[0][0], false));
        }

        // dist_init[x][y] 用于记录起点 (0, 0) 到达 (x, y) 的所有可达路径中最优路径（即初始油量需求最少的路径）的初始油量
        int[][] dist_init = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 由于需要记录每个位置的最少需要的初始油量，因此每个位置所需的初始油量初始化为一个较大值
                dist_init[i][j] = Integer.MAX_VALUE;
            }
        }

        // dist_remain 用于记录起点 (0,0) 到达 (x,y) 的所有可达路径中最优路径（即初始油量需求最少的路径）的最大剩余可用油量
        // 即如果存在多条最优路径，我们应该选这些路径中到达此位置剩余油量最多的
        int[][] dist_remain = new int[m][n];

        // 起点（0,0）到达自身位置（0,0）所需的最少初始油量和最多剩余油量
        dist_init[0][0] = queue.peekFirst().initOil;
        dist_remain[0][0] = queue.peekFirst().remainOil;

        //本题目不设置无法访问之前已经访问过的路径这一限制，等到油量耗尽自然就会停下来，这样做的原因是可能会发生要走的下一地点是加油站，从加油站回来使用的初始油量可能更小
//        boolean[][] isUsed = new boolean[m][n];
//        isUsed[0][0] = true;

        while (queue.size()>0){

            Car curCar = queue.pollFirst();
            int curX = curCar.x;
            int curY = curCar.y;
            int initOil = curCar.initOil;
            boolean isAddOil = curCar.isAddOil;
            int remainOil = curCar.remainOil;

            for(int[] offset : offsets){
                int newX = offset[0] + curX;
                int newY = offset[1] + curY;

                if(newX<0 || newX>=m || newY<0 || newY>=n ){
                    continue;
                }

                //标记已经走过了
                //isUsed[newX][newY]=true;
                int newInitOil = curCar.initOil;
                int newRemainOil = curCar.remainOil;
                boolean newIsAddOil = curCar.isAddOil;

                int newOil = matrix[newX][newY];

                if(newOil == -1){
                    //当前是加油站
                    //queue.add(new Car(newX, newY, 100, initOil, true));
                    newRemainOil = 100;
                    newIsAddOil = true;

                } else if (newOil==0) {
                    //障碍物，汽车不能通过
                    continue;
                }else {
                    if(remainOil >= newOil){
                        //剩余的油足够
                        //queue.add(new Car(newX, newY, remainOil-newOil, initOil, isAddOil));
                        newRemainOil = remainOil-newOil;
                    }else {
                        //剩余的油不够

                        if(isAddOil){
                            //如果经过了加油站，就不能通过增加初始油量的方式，因为汽车油箱容量只有100，到加油站肯定会加满，初始油量有多少都没用，都无法到达
                            //此条线路无法到达
                            continue;
                        }else {
                            int addiOil = newOil-remainOil;

                            if(addiOil+initOil>100){
                                //如果初始油量加上缺少的油量比最大容量100大，说明无法到达
                                continue;
                            }else {
                                //queue.add(new Car(newX, newY, 0, initOil+addiOil, isAddOil));
                                newRemainOil = 0;
                                newInitOil = initOil+addiOil;
                            }

                        }
                    }
                }

                if (newInitOil < dist_init[newX][newY] || newRemainOil > dist_remain[newX][newY]) {
                    // 则当前路径策略更优，记录更优路径的状态
                    dist_init[newX][newY] = newInitOil;
                    dist_remain[newX][newY] = newRemainOil;
                    queue.add(new Car(newX, newY, newRemainOil, newInitOil, newIsAddOil));
                }
//                if(newX==m-1 && newY==n-1){
//                    minOil = Math.min(minOil, queue.peekLast().initOil);
//                }
            }
        }

        // dist_init[m - 1][n - 1] 记录的是到达右下角终点位置所需的最少初始油量
        System.out.println( dist_init[m - 1][n - 1] == Integer.MAX_VALUE ? -1 : dist_init[m - 1][n - 1]);
    }
}
