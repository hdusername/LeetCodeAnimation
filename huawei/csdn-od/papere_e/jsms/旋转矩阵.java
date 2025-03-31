/**
 * 有一个NxN整数矩阵，请编写一个算法，将矩阵顺时针旋转90度。给定一个NxN的矩阵，和矩阵的阶数N,请返回旋转后的NxN矩阵。
 * 要求：空间复杂度 O(1)
 *
 * https://blog.csdn.net/weixin_43919932/article/details/88769608
 */

public class 旋转矩阵 {

    public static void main(String[] args) {
        int a[][]={{1,2,3},{2,3,4},{4,5,6}};  //定义3*3数组演示
        int b[][] = new int[3][3];
        int c[][] = new int[3][3];
        int d[][] = new int[3][3];
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                b[i][j]=a[2-j][i];  //顺时针旋转90 度
                c[i][j]=a[2-i][2-j]; //顺时针旋转180度
                d[i][j]=a[j][2-i];   //顺时针旋转270度
            }

        }
    }
}
