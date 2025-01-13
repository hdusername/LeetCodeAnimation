import java.util.Scanner;

/**
 * 有一种简易压缩算法：针对全部由小写英文字母组成的字符串，将其中连续超过两个相同字母的部分压缩为连续个数加该字母，其他部分保持原样不变。
 * 例如：字符串“aaabbccccd”经过压缩成为字符串“3abb4cd”。
 *
 * 请您编写解压函数，根据输入的字符串，判断其是否为合法压缩过的字符串，
 * 若输入合法则输出解压缩后的字符串，否则输出字符串!error”来报告错误。
 *
 * 输入描述：
 * 输入一行，为一个ASCII字符串，长度不会超过100字符，用例保证输出的字符串长度也不会超过100字符。
 *
 * 输出描述：
 * 若判断输入为合法的经过压缩后的字符串，则输出压缩前的字符串；
 * 若输入不合法，则输出字符串“
 */
public class 一种字符串压缩表示的解压 {

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        String zipStr = sc.nextLine(); // 输入的压缩串
        String unZipStr = unZip(zipStr); // 解压后字符串

        System.out.println(unZipStr);
    }

    // 解压
    public static String unZip(String zipStr) {
        char[] s = zipStr.toCharArray(); // charAt写出来的代码太臃肿了

        StringBuilder unZipStr = new StringBuilder(); // 记录解压结果串

        int i = 0;
        while (i < s.length) {
            char c = s[i];

            if (Character.isDigit(c)) { // 如果遍历的字符是数字
                // 则探索出之后连续的所有数字
                StringBuilder num = new StringBuilder();
                //有100y这种的输入，要汇总下数字输入
                while (i < s.length && Character.isDigit(s[i])) {
                    num.append(s[i]);
                    i++;
                }

                // 以及跟着的一个小写字母
                if (i < s.length && Character.isLowerCase(s[i])) {
                    //对于输入为：04d这种的判断为不合法
                    if(num.toString().startsWith("0")){
                        return "!error";
                    }
                    int repeatCount = Integer.parseInt(num.toString());
                    char repeatLetter = s[i];

                    // 必须超过连续两个相同小写字母才能进行压缩
                    if (repeatCount <= 2) return "!error";

                    //当前判断字符前面不能有超过或等于两个与当前判断字符相同的字符，否则就是重复的，压缩字符就是不合法的，比如这种输入：3b2b
                    if(unZipStr.length()>=2 &&unZipStr.substring(unZipStr.length()-2).replace(repeatLetter+"","").isEmpty()){
                        return "!error";
                    }

                    for (int k = 0; k < repeatCount; k++) {
                        unZipStr.append(repeatLetter);
                    }

                } else {
                    // 数字后面没有跟着小写字母, 则不合法
                    return "!error";
                }

            } else if (Character.isLowerCase(c)) {
                //这里也要有不能重复的判断，比如3bb这种，如果没有下面这个判断就会判断错误
                if(unZipStr.length()>=2 &&unZipStr.substring(unZipStr.length()-2).replace(c+"","").isEmpty()){
                    return "!error";
                }
                // 单独的小写字母直接并入解压串
                unZipStr.append(c);
            } else {
                // 压缩串中含有数字,小写字母以外的字符, 则不合法
                return "!error";
            }

            i++;
        }

        return unZipStr.toString();
    }

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String s = scanner.nextLine();
//
//        //String ans = "!error";
//        StringBuffer stringBuffer = new StringBuffer();
//
//        char beforeChar=' ';
//
//        StringBuffer digitBuffer = new StringBuffer();
//
//        for(int i=0; i<s.length(); i++){
//            char c = s.charAt(i);
//            if(('a'<=c && c<='z') || ('0'<=c && c<='9')){
//
//                while(i<s.length() && ('0'<=c && c<='9')){
//                    digitBuffer.append(c);
//                    beforeChar = c;
//                    i++;
//                    continue;
//                }
//
//                //进入到这个分支说明是符合条件的字符
//                //是十进制数字
////                if(Character.isDigit(c)){
////                    //数字要大于2
////                    if(Integer.parseInt(c+"")>2){
////
////                    }else {
////                        stringBuffer.setLength(0);
////                        stringBuffer.append("!error");
////                        break;
////                    }
////                }else {
//                int i1 = Integer.parseInt(String.valueOf(digitBuffer));
//                if(i1<=2){
//                    stringBuffer.setLength(0);
//                    stringBuffer.append("!error");
//                    break;
//                }
//
//
//
//                if(stringBuffer.length()>=2 &&stringBuffer.substring(stringBuffer.length()-2).replace(c+"","").isEmpty()) {
//                        stringBuffer.setLength(0);
//                        stringBuffer.append("!error");
//                        break;
//                    }
//
//                    if (Character.isDigit(beforeChar)) {
//                        //为了适配这种情况：3b4b
//
//                        //添加足够数量的字母
//                        for (int j = 0; j < Integer.parseInt(beforeChar + ""); j++) {
//                            stringBuffer.append(c);
//                        }
//                    } else {
////                        if(c!=beforeChar){
//                        stringBuffer.append(c);
//
////                        }else {
////                            stringBuffer.setLength(0);
////                            stringBuffer.append("!error");
////                            break;
////                        }
//                    }
////                }
//
//
//            }else {
//                stringBuffer.setLength(0);
//                stringBuffer.append("!error");
//                //遇到不符合条件的字符就跳出
//                break;
//            }
//
//            beforeChar = c;
//            digitBuffer .setLength(0);
//        }
//        System.out.println(stringBuffer);
//    }
//

        // 压缩
//        public static String zip(String unZipStr) {
//            StringBuilder zipStr = new StringBuilder();
//
//            // 加一个空格不影响压缩结果, 同时可以避免收尾操作
//            unZipStr += " ";
//
//            // 记录上一个字母, 以及它的出现次数
//            char last = unZipStr.charAt(0);
//            int count = 1;
//
//            for (int i = 1; i < unZipStr.length(); i++) {
//                char curt = unZipStr.charAt(i);
//
//                if (curt == last) {
//                    // 当前字母和上一个字母相同, 则连续相同该字母数量++
//                    count++;
//                } else {
//                    // 当前字母和上一个字母不相同, 则连续相同字母被打断
//                    if (count > 2) {
//                        // 超过两个连续相同字母, 则进行压缩
//                        zipStr.append(count);
//                        zipStr.append(last);
//                    } else if (count == 2) {
//                        // 两个连续相同字母
//                        zipStr.append(last);
//                        zipStr.append(last);
//                    } else {
//                        // 一个字母
//                        zipStr.append(last);
//                    }
//
//                    // 更新last和count
//                    last = curt;
//                    count = 1;
//                }
//            }
//
//            return zipStr.toString();
//        }
}
