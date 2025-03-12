import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 公司用一个字符串来表示员工的出勤信息
 * absent：缺勤
 * late：迟到
 * leaveearly：早退
 * present：正常上班
 * 现需根据员工出勤信息，判断本次是否能获得出勤奖，能获得出勤奖的条件如下：
 * 缺勤不超过一次；
 * 没有连续的迟到/早退；
 * 任意连续7次考勤，缺勤/迟到/早退不超过3次。
 *
 * 输入描述
 * 用户的考勤数据字符串
 * 记录条数 >= 1；
 * 输入字符串长度 < 10000；
 * 不存在非法输入；
 *如：
 *  2
 *  present
 *  present absent present present leaveearly present absent
 *
 *  输出描述：
 *  根据考勤数据字符串，如果能得到考勤奖，输出”true”；否则输出”false”， 对于输入示例的结果应为：
 *  true false
 */
public class 考勤信息 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int recordCount = Integer.parseInt(scanner.nextLine());

        StringJoiner stringJoiner = new StringJoiner(" ");
        // String[] recordStr = new String[recordCount];

        for(int i=0;i<recordCount;i++) {
            boolean succ = getSucc(scanner.nextLine());
            stringJoiner.add(succ+"");
        }
        System.out.println(stringJoiner);
    }

    private static boolean getSucc(String str) {
        String[] records = str.split(" ");
        int absentCount = 0;
        String beforeState = "";
        int presentCount = 0;
        for(int i=0;i<records.length;i++){


            switch (records[i]){
                case "absent":
                    absentCount++;
                    if(absentCount>1){
                        return false;
                    }
                    beforeState = "absent";
                    break;
                case "late":
                    if(beforeState.equals("late") || beforeState.equals("leaveearly")){
                        return false;
                    }
                    beforeState = "late";
                    break;
                case "leaveearly":
                    if(beforeState.equals("late") || beforeState.equals("leaveearly")){
                        return false;
                    }
                    beforeState = "leaveearly";
                    break;
                case "present":
                    presentCount++;
                    beforeState = "present";
                    break;
            }
            if(i>6&&records[i-7].equals("present")){
                presentCount--;

                if(presentCount<=3){
                    return false;
                }
            }


        }

        return true;
    }
}
// 给出的标准答案
//import java.util.Scanner;
//import java.util.StringJoiner;
//
//public class Main {
//  public static void main(String[] args) {
//    Scanner sc = new Scanner(System.in);
//
//    int n = Integer.parseInt(sc.nextLine());
//
//    String[][] records = new String[n][];
//    for (int i = 0; i < n; i++) {
//      records[i] = sc.nextLine().split(" ");
//    }
//
//    getResult(n, records);
//  }
//
//  public static void getResult(int n, String[][] records) {
//    StringJoiner sj = new StringJoiner(" ");
//
//    for (int i = 0; i < n; i++) {
//      sj.add(isAward(records[i]) + "");
//    }
//
//    System.out.println(sj);
//  }
//
//  public static boolean isAward(String[] record) {
//    // 总缺勤次数
//    int absent = 0;
//
//    // 滑窗内正常上班的次数
//    int present = 0;
//
//    // 记录前一次的考勤记录
//    String preRecord = "";
//
//    for (int i = 0; i < record.length; i++) {
//      if (i >= 7) {
//        // 滑窗长度最大为7，如果超过7，则滑窗的左边界需要右移, 滑窗失去的部分record[i - 7]
//        // 如果失去部分是present，则更新滑窗内present次数
//        if ("present".equals(record[i - 7])) present--;
//      }
//
//      // 当前的考勤记录
//      String curRecord = record[i];
//
//      switch (curRecord) {
//        case "absent":
//          // 缺勤不超过一次
//          if (++absent > 1) return false;
//          break;
//        case "late":
//        case "leaveearly":
//          // 没有连续的迟到/早退
//          if ("late".equals(preRecord) || "leaveearly".equals(preRecord)) return false;
//          break;
//        case "present":
//          present++;
//          break;
//      }
//
//      preRecord = curRecord;
//
//      // 任意连续7次考勤，缺勤/迟到/早退不超过3次, 相当于判断： 滑窗长度 - present次数 <= 3
//      int window_len = Math.min(i + 1, 7); // 滑窗长度
//      if (window_len - present > 3) return false;
//    }
//
//    return true;
//  }
//}