import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * 有一个简易内存池，，内存按照大小粒度分类，每个粒度有若干个可用内存资源，用户会进行一系列内存申请，需要按需分配内存池中的资源返回申请结果成功失败列表。
 * 分配规则如下：
 * 分配的内存要大于等于内存的申请量，存在满足需求的内存就必须分配，优先分配粒度小的，但内存不能拆分使用；
 * 需要按申请顺序分配，先申请的先分配。
 * 有可用内存分配则申请结果为true，没有可用则返回false。
 * 注意：不考虑内存释放
 *
 * 输入描述：
 * 输入为两行字符串：
 * 第一行为内存池资源列表，包含内存粒度数据信息，粒度数据间用逗号分割，一个粒度信息内用冒号分割，冒号前为内存粒度大小，冒号后为数量
 * 资源列表不大于1024
 * 每个粒度的数量不大于4096
 * 第二行为申请列表，申请的内存大小间用逗号分割
 * 申请列表不大于100000
 *
 * 如
 * 64:2,128:1,32:4,1:128
 * 50,36,64,128,127
 *
 * 输出描述：
 * 输出为内存池分配结果
 * 如：
 * true,true,true,false,false
 */
public class 内存资源分配 {
    public static class Memory{
        int size;
        int count;

        public Memory(int size, int count) {
            this.size = size;
            this.count = count;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //初始化内存，将大小和数量存到list中
        ArrayList<Memory> memoryList = new ArrayList<>();
        Arrays.stream(scanner.nextLine().split(",")).forEach(a->{
            int[] split = Arrays.stream(a.split(":")).mapToInt(Integer::parseInt).toArray();
            memoryList.add(new Memory(split[0], split[1]));
        });

        //因为优先分配粒度小的内存，所以将内存从小到大排序
        memoryList.sort((a, b)->a.size-b.size);

        //获取申请内存的列表,申请内存列表有这样的数据：50,36,64,,128,127，所以要过滤下
        int[] askMemorys = Arrays.stream(scanner.nextLine().split(",")).filter(a->!a.isEmpty()).mapToInt(Integer::parseInt).toArray();

        StringJoiner stringJoiner = new StringJoiner(",");
        out :for(int i=0; i<askMemorys.length; i++){
            for(int j=0; j<memoryList.size(); j++){
                if(askMemorys[i]<=memoryList.get(j).size && memoryList.get(j).count>0){
                    memoryList.get(j).count--;
                    stringJoiner.add("true");
                    continue out;
                }
            }
            stringJoiner.add("false");
        }
       System.out.println(stringJoiner);
    }



//todo: 这个是二分查找的方法，防止大数据量报错
//    public static class Memory {
//        int size; // 内存粒度大小
//        int count; // 数量
//
//        public Memory(String[] info) {
//            this.size = Integer.parseInt(info[0]);
//            this.count = Integer.parseInt(info[1]);
//        }
//    }
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        ArrayList<Memory> memories = Arrays.stream(sc.nextLine().split(",")).map(s -> new Memory(s.split(":"))).collect(Collectors.toCollection(ArrayList::new));
//        int[] requests = Arrays.stream(sc.nextLine().split(",")).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).toArray();
//
//        // 按照内存粒度大小升序
//        memories.sort((a, b) -> a.size - b.size);
//
//        // 记录结果
//        StringJoiner sj = new StringJoiner(",");
//
//        // 遍历申请列表
//        for (int request : requests) {
//            // 二分查找（找到大于等于request的内存资源）
//            int i = binarySearch(memories, request);
//
//            if (i < memories.size()) {
//                // 如果找到>=申请大小的内存池资源
//                sj.add("true");
//
//                // 移除数量为0的内存池资源
//                if (--memories.get(i).count == 0) {
//                    memories.remove(i);
//                }
//            } else {
//                // 如果找不到
//                sj.add("false");
//            }
//        }
//
//        System.out.println(sj);
//    }
//
//    public static int binarySearch(ArrayList<Memory> arr, int target) {
//        int l = 0;
//        int r = arr.size() - 1;
//
//        while (l <= r) {
//            int mid = (l + r) >> 1;
//
//            int midVal = arr.get(mid).size;
//
//            if (midVal > target) {
//                r = mid - 1;
//            } else if (midVal < target) {
//                //找不到符合条件的target值一定会走这个分支，因为如果midVal > target就说明肯定有符合条件的值，就不会走这个分支
//                //最终没有找到的话mid+1后就会越界，就可以判断i < memories.size()
//                l = mid + 1;
//            } else {
//                return mid;
//            }
//        }
//
//        return l;
//    }

}
