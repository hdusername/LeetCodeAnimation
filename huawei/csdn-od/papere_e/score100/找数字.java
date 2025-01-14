import java.util.*;

/**
 * 给一个二维数组 nums，对于每一个元素 nums[i]，找出距离最近的且值相等的元素，
 * 输出横纵坐标差值的绝对值之和，如果没有等值元素，则输出-1。
 * 例如：输入数组 nums 为
 * 0 3 5 4 2
 * 2 5 7 8 3
 * 2 5 4 2 4
 *
 * 输出为：
 * -1 4 2 3 3
 * 1 1 -1 -1 4
 * 1 1 2 3 2
 *
 * 对于 nums[0][0] = 0，不存在相等的值。
 * 对于 nums[0][1] = 3，存在一个相等的值，最近的坐标为 nums[1][4]，最小距离为 4。
 * 对于 nums[0][2] = 5，存在两个相等的值，最近的坐标为 nums[1][1]，故最小距离为 2。
 * ...
 * 对于 nums[1][1] = 5，存在两个相等的值，最近的坐标为 nums[2][1]，故最小距离为1。
 * ...
 *
 * 输入描述：
 * 输入第一行为二维数组的行
 * 输入第二行为二维数组的列
 * 输入的数字以空格隔开。
 *
 * 输出描述：
 * 数组形式返回所有坐标值
 *
 * 备注：
 * 针对数组 nums[i][j]，满足 0 < i ≤ 100，0 < j ≤ 100
 * 对于每个数字，最多存在 100 个与其相等的数字
 */
public class 找数字 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int[][] inputs = new int[x][y];

//        List<Map<Integer, int[][]>> queryList = new ArrayList<>();
        Map<Integer, List<int[][]>> queryMap = new HashMap<>();

        for(int i=0; i<x; i++){
            for(int j=0; j<y; j++){
                inputs[i][j] = scanner.nextInt();
                //把值相同的坐标记录上
                queryMap.putIfAbsent(inputs[i][j],new ArrayList<>());
                int[][] point = new int[1][2];
                point[0][0]=i;
                point[0][1]=j;
                queryMap.get(inputs[i][j]).add(point);
            }
        }


        for(int i=0; i<x; i++){
            for(int j=0; j<y; j++){
                if(queryMap.get(inputs[i][j]).size()>1){
                    List<int[][]> ints = queryMap.get(inputs[i][j]);
                    //这里不能排序，因为比较的不是元素的顺序，而是其他元素与当前判断元素x,y距离的大小顺序
                    //ints.sort((a,b)->Math.abs(a[0][0]-b[0][0])+Math.abs(a[0][1]-b[0][1]));
                    int minNum = Integer.MAX_VALUE;
                    for(int z=0; z<ints.size(); z++){
                        //todo: 这个判断条件很重要，之前写错了写成&&连接了
                        if(ints.get(z)[0][0]!=i || ints.get(z)[0][1]!=j){
                            //x、y坐标有一个不相同，就可以进行比较
                            minNum = Math.min(minNum, Math.abs(ints.get(z)[0][0]-i)+Math.abs(ints.get(z)[0][1]-j));

                        }
                    }

                    inputs[i][j] = minNum;
                }else {
                    inputs[i][j]=-1;
                }

            }
        }


        System.out.println(Arrays.toString(Arrays.stream(inputs).map(Arrays::toString).toArray(String[]::new)));
    }


}