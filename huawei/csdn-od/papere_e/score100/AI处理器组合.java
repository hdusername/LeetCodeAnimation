import java.util.*;

/**
 * 某公司研发了一款高性能AI 处理器。每台物理设备具备 8 颗 AI 处理器，编号分别为0、1、2、3、4、5、6、7。
 * 编号 0-3 的处理器处于同一个链路中，编号 4-7 的处理器处于另外一个链路中，不通链路中的处理器不能通信。
 * 现给定服务器可用的处理器编号数组 ，以及任务申请的处理器数量 num，找出符合下列亲和性调度原则的芯片组合。
 * 如果不存在符合要求的组合，则返回空列表
 *
 * 亲和性调度原则：
 * 如果申请处理器个数为 1，则选择同一链路，剩余可用的处理器数量为 1 个的最佳，其次是剩余 3 个的为次佳，然后是剩余 2 个，最后是剩余 4 个。
 * 如果申请处理器个数为 2，则选择同一链路剩余可用的处理器数量 2 个的为最佳，其次是剩余4 个，最后是剩余 3 个。
 * 如果申请处理器个数为 4，则必须选择同一链路剩余可用的处理器数量为 4 个。
 * 如果申请处理器个数为 8，则申请节点所有 8 个处理器。
 *
 * 提示：
 * 任务申请的处理器数量只能是1、2、4、8。
 * 编号 0-3 的处理器处于一个链路，编号 4-7 的处理器处于另外一个链路。
 * 处理器编号唯一，且不存在相同编号处理器。
 *
 * 输入描述：
 * 输入包含可用的处理器编号数组 array，以及任务申请的处理器数量 num 两个部分。
 * 第一行为 array，第二行为 num。例如：
 * [0, 1, 4, 5, 6, 7]
 * 1
 * 表示当前编号为 0、1、4、5、6、7 的处理器可用。任务申请 1 个处理器。
 * 0 ≤ array.length ≤ 8
 * 0 ≤ array[i] ≤ 7
 * num in [1, 2, 4, 8]
 *
 * 输出描述：
 * 输出为组合列表，当array=[0，1，4，5，6，7]，num=1 时，输出为[[0], [1]]。
 *
 * 用例输入：
 * [0, 1, 4, 5, 6, 7]
 * 4
 *
 * 输出：
 *[[4, 5, 6, 7]]
 *
 * 输入：
 * [0, 1, 4, 5, 6, 7]
 * 1
 *
 * 输出
 * [[0], [1]]
 */
public class AI处理器组合 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        if(s.equals("[]")){
            System.out.println("[]");
            return;
        }
        //获取可用剩余的处理器
        //输入是这种形式：
        //[0, 1, 4, 5, 6, 7]
        //1
        //字符之间是有空格的，所以需要替换空格
        int[] array = Arrays.stream(s.substring(1, s.length() - 1).replaceAll(" ", "").split(",")).mapToInt(Integer::parseInt).toArray();

        //结果为了从小到大排序，不然有的案例通不过
        Arrays.sort(array);
        //获取申请的处理器数量
        int num = Integer.parseInt(scanner.nextLine());

        List<List<Integer>> ansList = new ArrayList<>();

        //首先要判断使用哪个链路的
        //初始化两个链路的可用剩余处理器
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for(int i=0; i<array.length; i++){
            if(array[i]<4){
                list1.add(array[i]);
            }else {
                list2.add(array[i]);
            }
        }

        HashMap<Integer, int[]> map = new HashMap<>();
        map.put(1, new int[]{1, 3, 2, 4});
        map.put(2, new int[]{2, 4, 3});
        map.put(4, new int[]{4});

        if(num==8){
            if(list1.size()==4 && list2.size()==4) {
                //只能申请两个链路的8个处理器
                list1.addAll(list2);

                ansList.add(list1);
            }
        }else {

            int[] ints = map.get(num);

            for(int gVal : ints){
                //有可能会包含链路1、2都是最佳的情况，所以要这么写逻辑
                if(list1.size()== gVal || list2.size()==gVal){
                    if(list1.size() == gVal) {
                        //使用链路1，找出链路1包含的处理器每num一个组合的所有组合，因为如果申请1个处理器，链路1剩余3个为找到的最佳链路，就要将链路中的处理器一个为一组，找到所有组合
                        dfs(list1, 0, num, new ArrayList<>(), ansList);
                    }

                    if (list2.size() == gVal) {
                        //使用链路2
                        dfs(list2, 0, num, new ArrayList<>(), ansList);

                    }
                    //找到了就跳出即为最优
                    break;

                }

            }
        }

        System.out.println(ansList);
    }

    private static void dfs(List sourceList, int idx, int targerNum, List saveList, List ansList){
        if(saveList.size() == targerNum){
            ArrayList tmpList = new ArrayList();
            tmpList.addAll(saveList);
            ansList.add(tmpList);
            return;
        }

        for(int i=idx; i<sourceList.size(); i++){
            saveList.add(sourceList.get(i));
            //这里特别要注意是i+1,之前写成idx+1了是有问题的
            dfs(sourceList, i+1, targerNum, saveList, ansList);
            saveList.remove(saveList.size()-1);
        }

        //以下为给的标准答案
//        Scanner sc = new Scanner(System.in);
//
//        // 输入获取
//        int[] array = Arrays.stream(sc.nextLine().replaceAll("[\\[\\]]", "").split(", ")).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).toArray();
//        int num = sc.nextInt();
//
//        // 本题没有规定输出组合内部顺序要求，但是最好排序一下
//        Arrays.sort(array);
//
//        // 将处理器编号数组array分为链路1和链路2
//        ArrayList<Integer> link1 = new ArrayList<>();
//        ArrayList<Integer> link2 = new ArrayList<>();
//
//        for (int i : array) {
//            // 编号0-3的处理器处于同一个链路中，编号4-7的处理器处于另外一个链路中
//            if (i < 4) {
//                link1.add(i);
//            } else {
//                link2.add(i);
//            }
//        }
//
//        // 记录本题结果
//        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
//
//        if (num == 8) {
//            // 如果申请8个处理器，则链路1，链路2都要有4个
//            if (link1.size() == 4 && link2.size() == 4) {
//                link1.addAll(link2);
//                res.add(link1);
//            }
//        } else {
//            // 构建亲和性调度原则,构建映射关系, key=申请处理器数量，val=最佳数量选择数组
//            HashMap<Integer, int[]> map = new HashMap<>();
//            map.put(1, new int[]{1, 3, 2, 4});
//            map.put(2, new int[]{2, 4, 3});
//            map.put(4, new int[]{4});
//
//            for (int i : map.get(num)) {
//                if (link1.size() == i || link2.size() == i) {
//                    if (link1.size() == i) {
//                        dfs(link1, num, 0, new ArrayList<>(), res);
//                    }
//
//                    if (link2.size() == i) {
//                        dfs(link2, num, 0, new ArrayList<>(), res);
//                    }
//
//                    break; // 找到最优选择策略了，所以无需继续
//                }
//            }
//        }
//
//        // 输出打印
//        System.out.println(res);
//    }
//
//    // 在link中选取level个元素的组合
//    public static void dfs(ArrayList<Integer> link, int level, int index, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> res) {
//        if (path.size() == level) {
//            res.add(new ArrayList<>(path));
//            return;
//        }
//
//        for (int i = index; i < link.size(); i++) {
//            path.add(link.get(i));
//            dfs(link, level, i + 1, path, res);
//            path.remove(path.size() - 1);
//        }
//
    }
}
