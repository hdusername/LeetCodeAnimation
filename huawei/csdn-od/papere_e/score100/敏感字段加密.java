import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 给定一个由多个命令字组成的命令字符串：
 * 字符串长度小于等于127字节，只包含大小写字母，数字，下划线和偶数个双引号；
 * 命令字之间以一个或多个下划线 _ 进行分割；
 * 可以通过两个双引号 "" 来标识包含下划线 _ 的命令字或空命令字（仅包含两个双引号的命令字），双引号不会在命令字内部出现；
 *
 * 请对指定索引的敏感字段进行加密，替换为******（6个*），并删除命令字前后多余的下划线_。
 * 如果无法找到指定索引的命令字，输出字符串ERROR
 *
 * 输入描述:
 * 输入为两行，第一行为命令字索引 K（从 0 开始），第二行为命令字符串 S。
 *
 * 输出描述：
 * 输出处理后的命令字符串，如果无法找到指定索引的命令字，输出字符串ERROR
 */
public class 敏感字段加密 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int k = scanner.nextInt();
        String S = scanner.next();

        //判断双引号是否结束
        boolean isStart = false;

        List<String> stringList = new ArrayList<>();
        StringBuffer stringBuffer = new StringBuffer();
        //加这一步是为了输出最后一个命令字，比如字符串最后是aaa，下面这个程序，如果不在最后加上_,那么就不会输出aaa
        S+="_";
        for(int i=0;i<S.length();i++){
            char charAt = S.charAt(i);

            if(charAt=='"'){
                isStart = !isStart;
            }

            if(isStart || charAt!='_'){
                stringBuffer.append(charAt);

            } else if (stringBuffer.length()>0) {
                stringList.add(stringBuffer.toString());
                stringBuffer.setLength(0);
            }

        }


        String answer = "ERROR";

        if(stringList.size()>k){
            stringList.set(k, "******");
            answer = String.join("_", stringList);
        }

        System.out.println(answer);

    }
}
