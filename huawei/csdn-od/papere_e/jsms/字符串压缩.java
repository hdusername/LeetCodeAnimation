import java.util.Scanner;
/**
 * 利用字符重复出现的次数，实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。输入只存在小写字符。
 *
 */
public class 字符串压缩 {
    //自己练习写的
    public static String compressString1(String S) {
        char front_char = S.charAt(0);
        int againNum = 1;

        StringBuffer stringBuffer = new StringBuffer();
        boolean changeFlag = false;

        for(int i=1; i<S.length(); i++){
            char cur_char = S.charAt(i);
            if(cur_char==front_char){
                changeFlag = true;
                againNum++;
            }else {
                stringBuffer.append(front_char+""+againNum);
                front_char = cur_char;
                againNum=1;
            }
        }
        stringBuffer.append(front_char+""+againNum);
        return changeFlag?stringBuffer.toString():S;

    }

    public static void main(String[] args) {
        // 处理输入
        Scanner in = new Scanner(System.in);
        String input_str = in.nextLine();

        compress(input_str.toCharArray());
        System.out.println("");
        System.out.println(compressString(input_str));
        System.out.println("");
        System.out.println(compressString1(input_str));
        return;
    }

    //O(n)空间复杂度
    public static String compressString(String S) {
        if (S.length() == 0) { // 空串处理
            return S;
        }
        StringBuffer ans = new StringBuffer();
        int cnt = 1;
        char ch = S.charAt(0);
        boolean flag = true;
        for (int i = 1; i < S.length(); ++i) {
            if (ch == S.charAt(i)) {
                cnt++;
            } else {
                ans.append(ch);
                if (cnt != 1){
                    flag = false;
                }
                ans.append(cnt);
                ch = S.charAt(i);
                cnt = 1;
            }
        }
        ans.append(ch);
        ans.append(cnt);
        return flag ? S : ans.toString();
    }

    //O(1)空间复杂度，只需要原地修改
    public static void compress(char[] chars) {
        int left = 0;
        int size = 0;

        //是否进行了替换修改？
        boolean flag = true;
        // 由于最后一个字符也需要判断，所以将右指针终点放到数组之外一格
        for (int right = 0; right <= chars.length; right++) {
            // 当遍历完成，或右指针元素不等于左指针元素时，更新数组
            if (right == chars.length || chars[right] != chars[left]) {
                chars[size++] = chars[left];    // 更新字符
                if (right - left > 1) {         // 更新计数，当个数大于 1 时才更新
                    for(char c : String.valueOf(right - left).toCharArray()) {
                        chars[size++] = c;
                        flag = false;
                    }
                }

                left = right;
            }
        }

        if (flag){
            System.out.print(chars);
            return;
        }
        for (int i=0;i<size;i++){
            if(i!=0 && !Character.isDigit(chars[i-1]) && !Character.isDigit(chars[i]) ){
                System.out.print(1);
            }
            System.out.print(chars[i]);
            if(i==size-1 && !Character.isDigit(chars[i]) ){
                System.out.print(1);
            }
        }
    }
}
