import java.util.*;

/**
 * 一个局域网内有很多台电脑，分别标注为 1 ~ N 的数字（题目中给的是0~N-1应该是不对的，所以改成了1~N）。相连接的电脑距离不一样，所以感染时间不一样，感染时间用 t 表示。
 * 其中网络内一台电脑被病毒感染，求其感染网络内所有的电脑最少需要多长时间。如果最后有电脑不会感染，则返回-1。
 * 给定一个数组 times 表示一台电脑把相邻电脑感染所用的时间。
 * 如图：path[i] = {i, j, t} 表示：电脑 i->j，电脑 i 上的病毒感染 j，需要时间 t。
 *
 * 输入描述
 * 4
 * 3
 * 2 1 1
 * 2 3 1
 * 3 4 1
 * 2
 *
 * 输出描述：
 * 2
 *
 * 解释：
 * 第一个参数：局域网内电脑个数N，1 ≤ N ≤ 200；
 * 第二个参数：总共多少条网络连接
 * 第三个 2 1 1 表示2->1时间为1
 * 第六行：表示病毒最开始所在电脑号2
 */
public class 电脑病毒感染 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //电脑数量
        int n = sc.nextInt();

        // 邻接表，key为起点，value中每一个数组0位置为终点，1位置为距离
        HashMap<Integer, ArrayList<int[]>> graph = new HashMap<>();

        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt(); // 出发点
            int v = sc.nextInt(); // 目标点
            int w = sc.nextInt(); // 出发点到达目标点的耗时

            graph.putIfAbsent(u, new ArrayList<>());
            graph.get(u).add(new int[]{v, w});
        }

        //标识对应电脑是否已经得到了最近距离，定义长度为n+1的原因是电脑编号是1-N，为了和电脑编号对应上
        boolean[] visited = new boolean[n+1];

        //标识对应编号的电脑到源点的最近距离
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        int initSrc = sc.nextInt();
        //源点到源点的距离为0
        dist[initSrc]=0;
        //数组中保存[电脑编号, 到源点的最短距离]，这个是临时存储的，后续还会进行弹栈，最终的结果在dist中保存
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b)->a[1]-b[1]);
        queue.add(new int[]{initSrc, 0});

        while (queue.size()>0){
            int[] srcArr = queue.poll();
            int src = srcArr[0];
            int len = srcArr[1];

            if(visited[src]){
                //如果已经作为起始节点计算过与其相连的其他节点的最短距离，就不用继续判断了
                continue;
            }
            visited[src]=true;

            if(graph.containsKey(src)){
                //与此起始点相关的终点
                ArrayList<int[]> endList = graph.get(src);
                for(int[] ends : endList){
                    int end = ends[0];
                    int end_len = ends[1];
                    //如果源点到终点的距离比之前其他路线从源点到终点的距离小，就记录这个最短距离
                    if(len+end_len<dist[end]){
                        dist[end] = len+end_len;
                        //如果说不是最小距离不用add，因为queue中保存的就是在计算过程中各个节点到源点的最短距离，最终不一定是这个
                        //比如说a->b距离是1, b->c距离是1,a->c距离是3
                        //初始化是queue中保存的是[a, 0];
                        //第一次循环到这里时，for循环执行后queue中保存的是[b, 1], [c, 3]
                        //第二次循环到这里时，for循环执行后queue中保存的是[c, 2]
                        //在循环就从c出发了
                        queue.add(new int[]{end, dist[end]});
                    }
                }

            }
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dist[i]);
        }

        // 如果存在某个点无法到达，则源点到该点的耗时为Integer.MAX_VALUE
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);

        //给出的标准答案
//        Scanner sc = new Scanner(System.in);
//
//        int n = sc.nextInt();
//
//        // 邻接表
//        HashMap<Integer, ArrayList<int[]>> graph = new HashMap<>();
//
//        int m = sc.nextInt();
//        for (int i = 0; i < m; i++) {
//            int u = sc.nextInt(); // 出发点
//            int v = sc.nextInt(); // 目标点
//            int w = sc.nextInt(); // 出发点到达目标点的耗时
//
//            graph.putIfAbsent(u, new ArrayList<>());
//            graph.get(u).add(new int[]{v, w});
//        }
//
//        // 源点
//        int src = sc.nextInt();
//
//        // visited[i]:记录节点i是否找到最短路
//        boolean[] visited = new boolean[n + 1];
//
//        // dist[i]:记录源点到节点i的最短路
//        int[] dist = new int[n + 1];
//        // 初始时，假设源点不可达其他剩余点，即源点到达其他点的耗时无限大
//        Arrays.fill(dist, Integer.MAX_VALUE);
//        // 源点到自身的耗时为0
//        dist[src] = 0;
//
//        // pq用于记录[节点编号，节点到源点的距离]，排序规则是，节点到源点的距离越短，优先级越高
//        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
//        // 从源点src开始发起探索
//        pq.add(new int[]{src, dist[src]});
//
//        // bfs
//        while (!pq.isEmpty()) {
//            // 取出局部最优（最短）路径的终点编号 u
//            int u = pq.poll()[0];
//
//            // 如果节点编号u已经找到了最短路，说明当前u是重复入队的点，因此无需继续探索
//            if (visited[u]) continue;
//            // 否则记录当前u找到了最短路
//            visited[u] = true;
//
//            // 如果u有可达的其他点
//            if (graph.containsKey(u)) {
//                for (int[] next : graph.get(u)) {
//                    // v是可达的其他店
//                    // w是u->v的耗时
//                    int v = next[0], w = next[1];
//
//                    // 那么如果从源点到u点的耗时是dist[u]，那么源点经过u点到v点的耗时就是dist[u] + w
//                    int newDist = dist[u] + w;
//                    // 而源点到v的耗时之前是dist[v]，因此如果newDist < dist[v]，则找到更少耗时的路径
//                    if (dist[v] > newDist) {
//                        // 更新源点到v的路径，即更新v点权重
//                        dist[v] = newDist;
//                        // 将v点加入优先队列中参与下一轮局部最优路径比较
//                        pq.add(new int[]{v, dist[v]});
//                    }
//                }
//            }
//        }
//
//        // dist记录的是源点到达其他各点的最短耗时，我们取出其中最大的就是源点走完所有点的最短耗时
//        int ans = 0;
//        for (int i = 1; i <= n; i++) {
//            ans = Math.max(ans, dist[i]);
//        }
//
//        // 如果存在某个点无法到达，则源点到该点的耗时为Integer.MAX_VALUE
//        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
//




        //代码随想录版本，测试后发现不适合本题目
        //https://programmercarl.com/kamacoder/0047.%E5%8F%82%E4%BC%9Adijkstra%E6%9C%B4%E7%B4%A0.html#%E5%85%B6%E4%BB%96%E8%AF%AD%E8%A8%80%E7%89%88%E6%9C%AC
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int m = scanner.nextInt();
//
//        int[][] grid = new int[n + 1][n + 1];
//        for (int i = 0; i <= n; i++) {
//            Arrays.fill(grid[i], Integer.MAX_VALUE);
//        }
//
//        for (int i = 0; i < m; i++) {
//            int p1 = scanner.nextInt();
//            int p2 = scanner.nextInt();
//            int val = scanner.nextInt();
//            grid[p1][p2] = val;
//        }
//
//        int start = 1;
//        int end = n;
//
//        // 存储从源点到每个节点的最短距离
//        int[] minDist = new int[n + 1];
//        Arrays.fill(minDist, Integer.MAX_VALUE);
//
//        // 记录顶点是否被访问过
//        boolean[] visited = new boolean[n + 1];
//
//        minDist[start] = 0;  // 起始点到自身的距离为0
//
//        for (int i = 1; i <= n; i++) { // 遍历所有节点
//
//            int minVal = Integer.MAX_VALUE;
//            int cur = 1;
//
//            // 1、选距离源点最近且未访问过的节点
//            for (int v = 1; v <= n; ++v) {
//                if (!visited[v] && minDist[v] < minVal) {
//                    minVal = minDist[v];
//                    cur = v;
//                }
//            }
//
//            visited[cur] = true;  // 2、标记该节点已被访问
//
//            // 3、第三步，更新非访问节点到源点的距离（即更新minDist数组）
//            for (int v = 1; v <= n; v++) {
//                if (!visited[v] && grid[cur][v] != Integer.MAX_VALUE && minDist[cur] + grid[cur][v] < minDist[v]) {
//                    minDist[v] = minDist[cur] + grid[cur][v];
//                }
//            }
//        }
//
//        if (minDist[end] == Integer.MAX_VALUE) {
//            System.out.println(-1); // 不能到达终点
//        } else {
//            System.out.println(minDist[end]); // 到达终点最短路径
//        }
    }
}

