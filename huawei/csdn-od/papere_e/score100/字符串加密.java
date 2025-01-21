import java.util.Scanner;

/**
 * 给你一串未加密的字符串 str，通过对字符串的每一个字母进行改变来实现加密，加密方式是在每一个字母 str[i] 偏移特定数组元素 a[i] 的量
 * 数组 a 前三位已经赋值：a[0]=1，a[1]=2，a[2]=4，当 i ≥ 3 时，数组元素 a[i]=a[i-1]+a[i-2]+a[i-3]
 * 例如：原文 abcde 加密后 bdgkr，其中偏移量分别是1,2,4,7,13。
 *
 * 输入描述：
 * 第一行为一个整数 n（1 ≤ n ≤ 1000），表示有 n 组测试数据
 * 每组数据包含一行，原文str（只含有小写字母，0 < 长度 ≤ 50）。
 *
 * 输出描述：
 * 每组测试数据输出一行，表示字符串的密文。
 */
public class 字符串加密 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());


        int[] a = new int[51];
        a[0]=1;
        a[1]=2;
        a[2]=4;
        for(int i=3; i<a.length; i++){
            //如果一个字母移动了26位，相当于它只移动了1位，所以在这里就将偏移量计算后存入数组中
            a[i] = (a[i-1] + a[i-2] + a[i-3])%26;
        }

        for(int i=0; i<n; i++){
            String s = scanner.nextLine();
            StringBuilder stringBuilder = new StringBuilder();
            for(int j=0; j<s.length(); j++){
                char c = s.charAt(j);
                stringBuilder.append(Character.toChars('a'+((c-'a'+a[j])%26)));
            }

            System.out.println(stringBuilder);
        }

    }
}
