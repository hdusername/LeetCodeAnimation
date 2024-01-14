import java.util.ArrayList;
import java.util.List;

/**
 *
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 */
public class Solution54 {

    public static void main(String[] args) {
        spiralOrder(new int[][]{{1,2,3}, {4,5,6},{7,8,9}});
    }

    public static List<Integer> spiralOrder(int[][] matrix) {


        int left = 0;
        int right = matrix[0].length-1;
        int top = 0;
        int bottom = matrix.length-1;

        ArrayList<Integer> arr = new ArrayList<>();

        //这个while表示第几层，依次遍历最外层、次外层和最内层
        while(left <= right && top <= bottom){
            //从左到右
            for(int column = left; column<=right; column++){
                arr.add(matrix[top][column]);
            }
            //从上到下
            for(int row = top+1; row<=bottom; row++){
                arr.add(matrix[row][right]);
            }

            //如果只有三行，那么上面就已经将中间那行输出了，下面这个输出是右下到左下、左下到左上，比如说有四行数据，第二层循环的时候还会走到这里
            if(left < right && top < bottom){
                //从右到左
                for(int column = right-1; column>=left; column--){
                    arr.add(matrix[bottom][column]);
                }
                //从下到上
                //这里row>top没有等于，因为从左上到右上的时候已经处理过row=top的情况了
                for(int row = bottom-1; row>top; row--){
                    arr.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return arr;
    }
}
