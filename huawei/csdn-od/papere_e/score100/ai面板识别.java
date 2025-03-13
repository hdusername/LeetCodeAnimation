import java.util.*;

/**
 * AI识别到面板上有 N（1 ≤ N ≤ 100）个指示灯，灯大小一样，任意两个之间无重叠。由于 AI 识别误差，每次别到的指示灯位置可能有差异，以4个坐标值
 * 描述AI识别的指示灯的大小和位置(左上角x1,y1，右下角x2,y2)，
 * 请输出先行后列排序的指示灯的编号，排序规则：
 * 每次在尚未排序的灯中挑选最高的灯作为的基准灯，
 * 找出和基准灯属于同一行所有的灯进行排序。两个灯高低偏差不超过灯半径算同一行（即两个灯坐标的差 ≤ 灯高度的一半）。
 *
 * 输入描述：
 * 第一行为N，表示灯的个数
 *  接下来N行，每行为1个灯的坐标信息，格式为：
 *  编号 x1 y1 x2 y2
 *  编号全局唯一
 *  1 ≤ 编号 ≤ 100
 *  0 ≤ x1 < x2 ≤ 1000
 *  0  ≤  y1 < y2 ≤ 1000
 *
 *  输出描述：
 *  排序后的编号列表，编号之间以空格分隔
 *  用例输入：
 *   5
 *   1 0 0 2 2
 *   2 6 1 8 3
 *   3 3 2 5 4
 *   5 5 4 7 6
 *   4 0 4 2 6
 *
 *   输出：
 *   1 2 3 4 5
 */
public class ai面板识别 {

    static class Light {
        int id; // 编号
        int x; // 圆心横坐标
        int y; // 圆心纵坐标
        int r; // 圆半径

        public Light(int id, int x, int y, int r) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Light> lightList = new ArrayList<>();

        int n = scanner.nextInt();
        for(int i=0; i<n; i++){
           int id = scanner.nextInt();
           int lx = scanner.nextInt();
           int ly = scanner.nextInt();
           int rx = scanner.nextInt();
           int ry = scanner.nextInt();
           Light light = new Light(id, lx+((rx-lx)/2), ly+((ry-ly)/2), (rx-lx)/2);
           lightList.add(light);

        }

        //按照高度排序，高度小的排在前面
        lightList.sort((a, b)->a.y-b.y);

        //保存同一行的灯
        ArrayList<Light> sameLineList = new ArrayList<>();

        //首先取出一个基准灯
        Light baseLight = lightList.get(0);
        sameLineList.add(baseLight);
        StringJoiner joiner = new StringJoiner(" ");
        for(int i=1; i<lightList.size(); i++){
            if(Math.abs(baseLight.y-lightList.get(i).y)<= baseLight.r){
                //同一行
                sameLineList.add(lightList.get(i));

            }else {

                //同一行的还要按照从左到右的顺序进行排序，因为已经按照高度排序了，所以只要走到这一步，就说明后续元素肯定没有和基准灯一行的了
                sameLineList.sort((a,b)->(a.x-b.x));
                sameLineList.forEach(a->joiner.add(a.id+""));
                sameLineList.clear();
                baseLight = lightList.get(i);
                sameLineList.add(baseLight);
            }
        }

        //说明最后还有元素没有输出
        if(sameLineList.size() !=0){
            sameLineList.sort((a,b)->(a.x-b.x));
            sameLineList.forEach(a->joiner.add(a.id+""));
        }

        System.out.println(joiner);
    }
}

//标准答案
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Scanner;
//import java.util.StringJoiner;
//
//public class Main {
//  public static void main(String[] args) {
//    Scanner sc = new Scanner(System.in);
//
//    int n = sc.nextInt();
//
//    Light[] lights = new Light[n];
//    for (int i = 0; i < n; i++) {
//      int id = sc.nextInt();
//      int x1 = sc.nextInt();
//      int y1 = sc.nextInt();
//      int x2 = sc.nextInt();
//      int y2 = sc.nextInt();
//      lights[i] = new Light(id, (x1 + x2) / 2, (y1 + y2) / 2, (x2 - x1) / 2);
//    }
//
//    System.out.println(getResult(lights));
//  }
//
//  public static String getResult(Light[] lights) {
//    // 按照圆心y坐标升序
//    Arrays.sort(lights, (a, b) -> a.y - b.y);
//
//    // ans记录题解
//    StringJoiner ans = new StringJoiner(" ");
//
//    // sameRowLights记录同一行的灯
//    ArrayList<Light> sameRowLights = new ArrayList<>();
//    Light base = lights[0];
//    sameRowLights.add(base);
//
//    for (int i = 1; i < lights.length; i++) {
//      Light light = lights[i];
//
//      // 如果lights[i]的纵坐标和base的纵坐标相差不超过半径，则视为同一行
//      if (light.y - base.y <= base.r) {
//        sameRowLights.add(light);
//      } else {
//        // 否则，不是同一行
//        // 针对同一行的灯，再按照横坐标升序
//        sameRowLights.sort((a, b) -> a.x - b.x);
//        sameRowLights.forEach(a -> ans.add(a.id + ""));
//        sameRowLights.clear();
//
//        // 开始新的一行记录
//        base = light;
//        sameRowLights.add(base);
//      }
//    }
//
//    // 注意不要漏了最后一行
//    if (sameRowLights.size() > 0) {
//      sameRowLights.sort((a, b) -> a.x - b.x);
//      sameRowLights.forEach(a -> ans.add(a.id + ""));
//    }
//
//    return ans.toString();
//  }
//}
//
//class Light {
//  int id; // 编号
//  int x; // 圆心横坐标
//  int y; // 圆心纵坐标
//  int r; // 圆半径
//
//  public Light(int id, int x, int y, int r) {
//    this.id = id;
//    this.x = x;
//    this.y = y;
//    this.r = r;
//  }
//}
