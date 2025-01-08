import java.util.Scanner;

/**
 * 给出3组点坐标(x, y, w, h)，-1000<x,y<1000，w,h为正整数。
 * (x, y, w, h)表示平面直角坐标系中的一个矩形：
 * x, y为矩形左上角坐标点，w, h,向右w,向下h
 * (x, y, w, h)表示x轴(x, x+w)和y轴(y, y-h)围成的矩形区域；
 * (0, 0, 2, 2)表示 x轴(0, 2)和y 轴(0, -2)围成的矩形区域；
 * (3, 5, 4, 6)表示x轴(3, 7)和y轴(5, -1)围成的矩形区域；
 * 求3组坐标构成的矩形区域重合部分的面积。
 *
 * 输入描述：
 * 3行输入分别为3个矩形的位置，分别代表“左上角x坐标”，“左上角y坐标”，“矩形宽”，“矩形高” -1000 <= x,y < 1000
 *
 * 输出描述：
 * 输出3个矩形相交的面积，不相交的输出0。
 */
public class 矩形相交的面积 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x1=scanner.nextInt();
        int y1=scanner.nextInt();
        int x2=scanner.nextInt();
        int y2=scanner.nextInt();

        int x3=scanner.nextInt();
        int y3=scanner.nextInt();
        int x4=scanner.nextInt();
        int y4=scanner.nextInt();

        int x5=scanner.nextInt();
        int y5=scanner.nextInt();
        int x6=scanner.nextInt();
        int y6=scanner.nextInt();


        int length =Math.min(Math.min(x1+x2, x3+x4), x5+x6) - Math.max(Math.max(x1, x3), x5);
        int width = Math.min(Math.min(y1, y3),y5) - Math.max(Math.max(y1-y2, y3-y4),y5-y6);

        if(length<=0||width<=0){
            System.out.println("0");
        }else {
            System.out.println(length*width);
        }
    }
}
