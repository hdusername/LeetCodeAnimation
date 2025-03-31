/**
 * 给定整数 n ，返回 所有小于非负整数 n 的质数的数量 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 * 示例 2：
 *
 * 输入：n = 0
 * 输出：0
 * 示例 3：
 *
 * 输入：n = 1
 * 输出：0
 */
public class 计数质数 {

    public static void main(String[] args) {
        countPrimes(10);
    }

    public static int countPrimes(int n) {
        int ans = 0;
        for(int i=1; i<n; i++){
            if(getResult(i)){
                ans++;
            }
        }
        return ans;
    }

    public static boolean getResult(int i){
        if(i==1){
            return false;
        }
        if(i%2==0){
            return i==2?true:false;
        }
        for(int j=3; j*j<=i;j+=2){
            if(i%j==0){
                return false;
            }
        }
        return true;
    }
}
