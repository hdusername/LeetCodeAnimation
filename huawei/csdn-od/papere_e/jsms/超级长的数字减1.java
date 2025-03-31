import java.util.Scanner;
import java.util.*;
import java.util.stream.Stream;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
public class 超级长的数字减1 {




//这个解法只能实现减一的功能
//    public class SuperLongNumberMinusOne {
//        public static String superLongMinusOne(String number) {
//            // 转换为字符数组，便于操作
//            char[] digits = number.toCharArray();
//            int n = digits.length;
//
//            // 从右往左逐位处理减法
//            for (int i = n - 1; i >= 0; i--) {
//                if (digits[i] > '0') {
//                    // 当前位直接减1
//                    digits[i]--;
//                    break;
//                } else {
//                    // 当前位为0，需要借位，变成9
//                    digits[i] = '9';
//                }
//            }
//
//            // 特殊情况：如果首位是'0'，需要去掉首位，例如100 -> 99
//            if (digits[0] == '0') {
//                return new String(digits, 1, n - 1);
//            }
//
//            return new String(digits);
//        }
//
//        public static void main(String[] args) {
//            // 测试用例
//            String number = "127364646465645455455454545545455455450";
//            String result = superLongMinusOne(number);
//            System.out.println("结果: " + result); // 输出：127364646465645455455454545545455455449
//        }
//    }


    //这个解法可以实现负数减一
    //public class SuperLongNumberMinusOne {
    //    public static String superLongMinusOne(String number) {
    //        boolean isNegative = number.startsWith("-"); // 检查是否是负数
    //
    //        // 如果是负数，取绝对值部分进行加 1 操作
    //        if (isNegative) {
    //            String absNumber = number.substring(1); // 去掉负号
    //            String result = addOne(absNumber); // 负数减1 = 绝对值加1
    //            return "-" + result; // 添加负号
    //        } else {
    //            return subtractOne(number); // 正数逻辑
    //        }
    //    }
    //
    //    // 针对正数执行减 1 操作
    //    private static String subtractOne(String number) {
    //        char[] digits = number.toCharArray();
    //        int n = digits.length;
    //
    //        // 从右往左逐位处理减法
    //        for (int i = n - 1; i >= 0; i--) {
    //            if (digits[i] > '0') {
    //                // 当前位直接减1
    //                digits[i]--;
    //                break;
    //            } else {
    //                // 当前位为0，需要借位，变成9
    //                digits[i] = '9';
    //            }
    //        }
    //
    //        // 特殊情况：如果首位是'0'，需要去掉首位，例如100 -> 99
    //        if (digits[0] == '0') {
    //            return new String(digits, 1, n - 1);
    //        }
    //
    //        return new String(digits);
    //    }
    //
    //    // 针对正数执行加 1 操作（用于负数场景下的加法）
    //    private static String addOne(String number) {
    //        char[] digits = number.toCharArray();
    //        int n = digits.length;
    //        int carry = 1; // 加1的进位
    //
    //        // 从右往左逐位处理加法
    //        for (int i = n - 1; i >= 0 && carry > 0; i--) {
    //            int sum = digits[i] - '0' + carry;
    //            digits[i] = (char) ('0' + (sum % 10)); // 更新当前位
    //            carry = sum / 10; // 更新进位
    //        }
    //
    //        // 如果还有进位，需在最高位前加1
    //        if (carry > 0) {
    //            return "1" + new String(digits);
    //        }
    //
    //        return new String(digits);
    //    }
    //
    //    public static void main(String[] args) {
    //        // 测试用例
    //        String positiveNumber = "127364646465645455455454545545455455450";
    //        String negativeNumber = "-127364646465645455455454545545455455450";
    //
    //        System.out.println("正数减1结果: " + superLongMinusOne(positiveNumber)); // 127364646465645455455454545545455455449
    //        System.out.println("负数减1结果: " + superLongMinusOne(negativeNumber)); // -127364646465645455455454545545455455451
    //    }
    //}


    //这个解法太复杂了
    public static void main(String[] args) {

        System.out.println(sub("127364646465645455455454545545455455450", "1"));
    }

    public static String sub(String n1, String n2) {
        String symbol = "";
        if (n1.startsWith("-") && !n2.startsWith("-")) {
            return "-" + sum(n1.substring(1), n2);
        } else if (!n1.startsWith("-") && n2.startsWith("-")) {
            return sum(n1, n2.substring(1));
        } else if (n1.startsWith("-") && n2.startsWith("-")) {
            return sub(n2.substring(1), n1.substring(1));
        }

        if (n1.equals(n2)) return "0";

        //n1Moren2标记n1是否大于n2
        boolean n1Moren2 = n1.length() > n2.length();
        if (n1.length() == n2.length() && !n1.equals(n2)) {
            for (int i = 0; i < n2.length(); i++) {
                int temN1 = n1.charAt(i) - '0';
                int temN2 = n2.charAt(i) - '0';
                if (temN1 != temN2) {
                    n1Moren2 = temN1 > temN2;
                    break;
                }
            }
        }
        //n1小于n2则交换n1和n2,使n1大于n2
        if (!n1Moren2) {
            symbol = "-";
            String temp = n1;
            n1 = n2;
            n2 = temp;
        }
        //交换后如果长度不等，则对n2前面补0，使n1和n2的长度相等
        if (n1.length() != n2.length()) {
            int puchZero = n1.length() - n2.length();
            StringBuilder newStr = new StringBuilder();
            for (int i = 0; i < puchZero; i++) {
                newStr.append(0);
            }
            n2 = newStr + n2;
        }
        char[] ch1 = n1.toCharArray();
        char[] ch2 = n2.toCharArray();
        StringBuilder result = new StringBuilder();
        boolean nextExcuse = false;
        List<Integer> list = new ArrayList<>();
        for (int i = ch1.length - 1; i >= 0; i--) {
            int num1 = ch1[i] - '0';
            int num2 = ch2[i] - '0';
            if (!nextExcuse) {//该位没被借位过
                if (num1 >= num2) {
                    list.add(num1 - num2);
                } else {
                    nextExcuse = true;
                    list.add(10 + num1 - num2);
                }
            } else {//该位被借位过
                if (num1 > num2) {
                    list.add(num1 - 1 - num2);
                    nextExcuse = false;
                } else {
                    list.add(9 + num1 - num2);
                }
            }
        }
        //去除最开始的连续0，输出结果
        int zeroPostion = -1;
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) != 0) {
                break;
            }
            if (i >= 1 && list.get(i - 1) != 0) {
                zeroPostion = i;
                break;
            }
        }
        int startPostion = zeroPostion == -1 ? list.size() - 1 : zeroPostion - 1;
        for (int i = startPostion; i >= 0; i--) {
            result.append(list.get(i));
        }
        return symbol + result;
    }

    public static String sum(String n1, String n2) {
        String symbol = "";
        if (n1.startsWith("-") && !n2.startsWith("-")) {
            return sub(n2, n1.substring(1));
        } else if (!n1.startsWith("-") && n2.startsWith("-")) {
            return sub(n1, n2.substring(1));
        } else if (n1.startsWith("-") && n2.startsWith("-")) {
            symbol = "-";
            n1 = n1.substring(1);
            n2 = n2.substring(1);
        }

        //若n1和n2位数不一样，则将少的数补0
        int puchZero = Math.abs(n1.length() - n2.length());
        StringBuilder newStr = new StringBuilder();
        for (int i = 0; i < puchZero; i++) {
            newStr.append(0);
        }
        if (n1.length() > n2.length()) {
            n2 = newStr + n2;
        } else {
            n1 = newStr + n1;
        }
        //从个位开始将n1和n2相加，结果放入list中
        char[] ch1 = n1.toCharArray();
        char[] ch2 = n2.toCharArray();
        //下一位进位要加的数
        int nextAdd = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = ch1.length - 1; i >= 0; i--) {
            int num1 = ch1[i] - '0';
            int num2 = ch2[i] - '0';
            int sum = num1 + num2 + nextAdd;
            if (sum > 9) {
                if (i != 0) {
                    nextAdd = 1;
                    list.add(sum % 10);
                } else {
                    list.add(sum);
                }
            } else {
                nextAdd = 0;
                list.add(sum);
            }
        }
        //翻转list,输出结果
        StringBuilder result = new StringBuilder();
        for (int i = list.size() - 1; i >= 0; i--) {
            result.append(list.get(i));
        }
        return symbol + result;
    }
}
