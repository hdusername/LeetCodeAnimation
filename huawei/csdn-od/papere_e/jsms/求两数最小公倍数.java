import java.util.HashMap;
import java.util.HashSet;

/**
 * 利用辗转相除法，我们可以求得两个数的最大公因数(greatest common divisor，gcd)将两个数相乘，再除以最大公因数只，即可得到最小公倍数(least common multiple,lcm)。
 */
public class 求两数最小公倍数 {

    public static void main(String[] args) {

//        HashMap<Integer, Integer> hashMap = new HashMap<>();
//        hashMap.put(1, hashMap.getOrDefault(1, 0)+1);
//        hashMap.entrySet().stream().sorted((a,b)->{
//            if(a.getValue()==b.getValue()){
//                return b.getKey() - a.getKey();
//            }else {
//                return b.getValue() - a.getValue();
//            }
//        }).limit(1).forEach(a->System.out.println(a.getKey()));
        //辗转相除法可以参考勾股数元组，不过勾股数元组使求是否互质，写法基本一样
    }
    //求最大公因数
//    int a = 8;
//    int b = 4;
//    //等于0的话说明已经找到最大公约数了
//    //如果mod=a%b中b等于1时，才得到余数mod为0，说明没有最大公约数，两个数互质
//    //如果b=1时余数才等于0，在后面的逻辑中1赋值给了a，所以后面判断a是否等于1返回两个数是否互质
//        while (b>0){
//        int mod=a%b;
//        a=b;
//        b=mod;
//
//    }
//       System.out.println(a);

}
