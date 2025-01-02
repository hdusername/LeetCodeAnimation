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
