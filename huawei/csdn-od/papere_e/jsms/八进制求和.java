import java.util.LinkedList;

/**
 *
 */
public class 八进制求和 {

    public static void main(String[] args) {

        String a = "123";
        String b = "2387";
        System.out.println( add8Binary(a, b));
        System.out.println( addBinary(a, b));
    }

    public static String add8Binary(String a, String b) {
    int i = a.length() - 1;
    int j = b.length() - 1;
    int carry = 0;
    StringBuilder builder = new StringBuilder();
    //循环相加两个字符串相同长度的低位数部分
        while(i >=0&&j >=0)

    {
        int sum = carry;
        sum += a.charAt(i--) - '0';
        sum += b.charAt(j--) - '0';
        carry = sum / 8;
        builder.append(sum % 8);
    }
    // 如果 a 还没遍历完成（a串比b串长），则继续遍历添加 a 的剩余部分
        while(i >=0)

    {
        int sum = carry + a.charAt(i--) - '0';
        carry = sum / 8;
        builder.append(sum % 8);
    }
    // 如果 b 还没遍历完成（b串比a串长），则继续遍历添加 b 的剩余部分
        while(j >=0)

    {
        int sum = carry + b.charAt(j--) - '0';
        carry = sum / 8;
        builder.append(sum % 8);
    }
    //如果 carry 不等于0 还有个进位数没加进去，需要补充
        if(carry ==1)

    {
        builder.append(carry);
    }
    //反转字符串获得正常结果
        return builder.reverse().toString();

}

        public static String addBinary(String a, String b) {
            StringBuffer ans = new StringBuffer();

            int n = Math.max(a.length(), b.length()), carry = 0;
            for (int i = 0; i < n; ++i) {
                carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
                carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
                ans.append((char) (carry % 8 + '0'));
                carry /= 8;
            }

            if (carry > 0) {
                ans.append('1');
            }
            ans.reverse();

            return ans.toString();
        }

    public String removeKdigits(String num, int k) {

        LinkedList<Character> linkedList = new LinkedList<>();
        linkedList.add(num.charAt(0));

        int remain = num.length() - k;

        for(int i=1; i<num.length(); i++){
            Character param = num.charAt(i);
            while (linkedList.size()>0 && param<linkedList.getLast() && k>0){
                linkedList.removeLast();
                k--;
            }
            linkedList.addLast(param);
        }

        while (linkedList.size()>remain){
            linkedList.removeLast();
        }
        while (linkedList.getFirst() == '0' && linkedList.size() > 1) {
            linkedList.removeFirst();
        }
        StringBuffer stringBuffer = new StringBuffer();
        linkedList.forEach(a->stringBuffer.append(a));
        return stringBuffer.toString();
    }
}
