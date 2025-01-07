import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定一组不等式，判断是否成立并输出不等式的最大差(输出浮点数的整数部分)
 * 要求:
 * 不等式系数为 double类型，是一个二维数组
 * 不等式的变量为 int类型，是一维数组;
 * 不等式的目标值为 double类型，是一维数组
 * 不等式约束为字符串数组，只能是:“>”,“>=”,“<”,“<=”,“=”，
 *
 * 例如，不等式组:
 * a11x1+a12x2+a13x3+a14x4+a15x5<=b1;
 * a21x1+a22x2+a23x3+a24x4+a25x5<=b2;
 * a31x1+a32x2+a33x3+a34x4+a35x5<=b3;
 * 最大差 = max{(a11x1+a12x2+a13x3+a14x4+a15x5-b1),(a21x1+a22x2+a23x3+a24x4+ a25x5-b2),(a31x1+a32x2+a33x3+a34x4+a35x5-b3)}
 * 类型为整数(输出浮点数的整数部分)
 *
 * 输入描述：
 * a11,a12,a13,a14,a15,a21,a22,a23,a24,a25, a31,a32,a33,a34,a35,x1,x2,x3,x4,x5,b1,b2,b3,<=,<=,<=
 * 1)不等式组系数(double类型):
 * a11,a12,a13,a14,a15
 * a21,a22,a23,a24,a25
 * a31,a32,a33,a34,a35
 * 2)不等式变量(int类型):x1,x2,x3,x4,x5
 * 3)不等式目标值(double类型):b1,b2,b3
 * 4)不等式约束(字符串类型):<=,<=,<=
 *
 * 输出描述：
 * true或者 false，最大差
 */
public class 判断一组不等式是否满足约束并输出最大差 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //系数
        double[][] as = new double[3][5];
        //变量
        int[] vs = new int[5];
        //目标值
        double[] ts = new double[3];
        //符号
        String[] ss = new String[3];

        String[] split = scanner.nextLine().split(";");


        for(int i=0;i<3; i++){
            as[i]  = Arrays.stream(split[i].split(",")).mapToDouble(Double::parseDouble).toArray();

        }

        vs = Arrays.stream(split[3].split(",")).mapToInt(Integer::parseInt).toArray();

        ts = Arrays.stream(split[4].split(",")).mapToDouble(Double::parseDouble).toArray();

        ss = split[5].split(",");

        double v1= as[0][0]*vs[0]+as[0][1]*vs[1]+as[0][2]*vs[2]+as[0][3]*vs[3]+as[0][4]*vs[4]-ts[0];
        double v2= as[1][0]*vs[0]+as[1][1]*vs[1]+as[1][2]*vs[2]+as[1][3]*vs[3]+as[1][4]*vs[4]-ts[1];
        double v3= as[2][0]*vs[0]+as[2][1]*vs[1]+as[2][2]*vs[2]+as[2][3]*vs[3]+as[2][4]*vs[4]-ts[2];

        int max = (int)Math.max(Math.max(v1, v2), v3);

        boolean flag = getFlag(v1, ss[0]) && getFlag(v2, ss[1]) && getFlag(v3, ss[2]);

        System.out.println(flag + " " + max);

    }

    private static boolean getFlag(double value, String ss) {

        boolean flag = false;
        switch (ss){
            case ">":
                flag=value>0;
                break;
            case ">=":
                flag=value>=0;
                break;
            case "<":
                flag=value<0;
                break;
            case "<=":
                flag=value<=0;
                break;
            case "=":
                flag=value==0;
                break;
            default:
                flag = false;
                break;
        }

        return flag;

    }
}