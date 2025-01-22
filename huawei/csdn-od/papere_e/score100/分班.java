import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 幼儿园两个班的小朋友在排队时混在了一起，每位小朋友都知道自己是否与前面一位小朋友同班，请你帮忙把同班的小朋友找出来。
 * 小朋友的编号是整数，与前一位小朋友同班用Y表示，不同班用N表示。
 *
 * 输入描述：
 * 输入为空格分开的小朋友编号和是否同班标志。
 * 比如：6/N 2/Y 3/N 4/Y，表示4位小朋友，2和6同班，3和2不同班，4和3同班。
 * 其中，小朋友总数不超过999，每个小朋友编号大于0，小于等于999。
 * 不考虑输入格式错误问题。
 *
 * 输出描述：
 * 输出为两行，每一行记录一个班小朋友的编号，编号用空格分开，且：
 * 编号需按照大小升序排列，分班记录中第一个编号小的排在第一行。
 * 若只有一个班的小朋友，第二行为空行。
 * 若输入不符合要求，则直接输出字符串ERROR。
 *
 * 用例：
 * 输入：
 * 1/N 2/Y 3/N 4/Y
 * 输出：
 * 1 2
 * 3 4
 */
public class 分班 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        try{
            String[][] students = Arrays.stream(s.split(" ")).map(a -> a.split("/")).toArray(String[][]::new);

            ArrayList<String> class1List = new ArrayList<>();
            ArrayList<String> class2List = new ArrayList<>();
            //当前比较学生的前一个学生是否是1班的，默认不是1班的
            boolean isClass1 = false;

            if(students.length>999){
                throw new Exception();
            }

            for(int i=0; i<students.length; i++){

                if(Integer.parseInt(students[i][0])<=0 || Integer.parseInt(students[i][0])>999){
                    throw new Exception();
                }

                if (i == 0 && "Y".equals(students[i][1])) {
                    throw new Exception();
                }
                if("Y".equals(students[i][1]) && isClass1 || "N".equals(students[i][1]) && !isClass1){
                    class1List.add(students[i][0]);
                    //此学生是1班的，设为true，便于在下个学生比较时可以找到此学生是1班的
                    isClass1=true;
                }else {
                    class2List.add(students[i][0]);
                    isClass1=false;
                }
            }
            class1List.sort((a,b)->Integer.parseInt(a)-Integer.parseInt(b));
            class2List.sort((a,b)->Integer.parseInt(a)-Integer.parseInt(b));

            //有两种情况需要换输出顺序 1：
           if(class1List.size()==0 || class2List.size() !=0 && Integer.parseInt(class1List.get(0))>Integer.parseInt(class2List.get(0))) {

               ArrayList<String> tmp = class1List;
               class1List = class2List;
               class2List = tmp;
           }
            System.out.println(join(class1List, " "));
            System.out.println(join(class2List, " "));

        }catch (Exception e){
            System.out.println("ERROR");
        }
    }
    public static String join(ArrayList<String> list, String delimiter) {
        StringJoiner sj = new StringJoiner(delimiter);

        for (String i : list) {
            sj.add(i);
        }

        return sj.toString();
    }
}
