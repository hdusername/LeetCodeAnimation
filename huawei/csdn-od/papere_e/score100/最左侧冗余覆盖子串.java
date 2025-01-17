import java.util.Arrays;
import java.util.Scanner;

/**
 *
 */
public class 最左侧冗余覆盖子串 {

    /**
     * 此方法在某些情况下容易造成内存移出或者超时错误
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String targetStr = scanner.nextLine();
        String sourceStr = scanner.nextLine();

        int k = Integer.parseInt(scanner.nextLine());

        //小写字母范围97-122，定义一个128位长度的数组存储各个字母的数量
        int[] targetNums = new int[128];
        for(int i=0; i<targetStr.length(); i++){
            targetNums[targetStr.charAt(i)]++;
        }

        //这里用等于号比较准确，具体原因可参考《补种未成活胡杨》
        out: for(int j=0; j<=sourceStr.length()-(k+targetStr.length()); j++){
            int[] tempTargetNums = Arrays.copyOf(targetNums, targetNums.length);

            String substring = sourceStr.substring(j, j + k+targetStr.length());
            for(int x=0; x<substring.length(); x++){
                tempTargetNums[substring.charAt(x)]--;
            }

            for(int y=0; y<tempTargetNums.length; y++){
                if(tempTargetNums[y]>0){
                    continue out;
                };
            }
            System.out.println(j);
            return;
        }
        System.out.println(-1);
    }


//todo: 以下是摘抄的标准答案
//        public static void main(String[] args) {
//            Scanner sc = new Scanner(System.in);
//
//            String s1 = sc.next();
//            String s2 = sc.next();
//            int k = sc.nextInt();
//
//            System.out.println(solution(s1, s2, k));
//        }
//
//        public static int solution(String s1, String s2, int k) {
//            // 在s2中选一个子串，满足:该子串长度为 n1+k
//            int n1 = s1.length();
//            int n2 = s2.length();
//            if (n2 < n1 + k) return -1;
//
//            // 由于字符串s1中都是小写字母，因此每个字母的ASCII码范围是97~122，因此这里初始化128长度数组来作为统计容器
//            int[] count = new int[128];
//
//            // 统计s1中所有每个字符出现的次数到count中
//            for (int i = 0; i < n1; i++) {
//                char c = s1.charAt(i);
//                count[c]++;
//            }
//
//            // s1字符总数
//            int total = n1;
//
//            // 滑动窗口的左边界从0开始，最大maxI；滑动窗口长度len
//            int maxI = n2 - n1 - k;
//            // s2子串长度
//            int len = n1 + k;
//
//            // 统计s2的0~len范围内出现的s1中字符的次数
//            for (int j = 0; j < len; j++) {
//                char c = s2.charAt(j);
//
//                // 如果s2的0~len范围内的字符c，在count[c]存在，则说明c是s1内有的字符，
//                // 此时我们需要count[c]--，如果自减之前，count[c] > 0，则自减时，total也应该--,否则total不--
//                //todo：只有c字符存在，count[c]才可能为正数或者0，如果不存在count[c]肯定是负数
//                if (count[c]-- > 0) {
//                    total--;
//                }
//
//                // 如果total为0了，则说明在s2的0~len范围内找到了所有s1中字符
//                if (total == 0) {
//                    // 此时可以直接返回起始索引0
//                    return 0;
//                }
//            }
//
//            // 下面是从左边界1开始的滑动窗口，利用差异思想，避免重复部分求解
//            for (int i = 1; i <= maxI; i++) {
//                // 滑动窗口右移一格后，失去了s2[i - 1]，得到了s2[i - 1 + len]，其余部分不变
//                char remove = s2.charAt(i - 1);
//                char add = s2.charAt(i - 1 + len);
//
//                //因为上面的逻辑中一直在执行count[c]--，所以如果这里count[remove]==0，说明count[remove]一定是之前有值且大于0的
//                //count[remove]>0说明在s2中没有找到足够数目的remove字符与s1进行对应
//                if (count[remove]++ >= 0) {
//                    total++;
//                }
//
//                if (count[add]-- > 0) {
//                    total--;
//                }
//
//                if (total == 0) {
//                    return i;
//                }
//            }
//            return -1;
//        }

}
