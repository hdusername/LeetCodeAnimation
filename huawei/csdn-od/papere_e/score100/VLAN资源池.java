import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * VLAN 是一种对局域网设备进行逻辑划分的技术，为了标识不同的VLAN，引入 VLAN ID（1-4094之间的整数）的概念。
 * 定义一个 VLAN ID 的资源池（下称VLAN资源池），资源池中连续的VLAN用开始VLAN-结束VLAN
 * 表示，不连续的用单个整数表示，所有的VLAN用英文逗号连接起来。
 * 现在有一个VLAN资源池，业务需要从资源池中申请一个VLAN，需要你输出从VLAN资源池中移除申请的VLAN后的资源池。
 *
 * 输入描述：
 * 第一行为字符串格式的VLAN资源池
 * 第二行为业务要申请的VLAN，VLAN 的取值范围为 [1，4094] 之间的整数。
 *
 * 输出描述：
 * 从输入VLAN资源池中移除申请的VLAN后字符串格式的VLAN资源池，输出要求满足题目描述中的格式，并且按照VLAN从小到大升序输出。
 * 如果申请的VLAN不在原VLAN资源池内，输出原VLAN资源池升序排序后的字符串即可。
 *
 * 备注：
 * 输入VLAN资源池中VLAN的数量取值范围为 [2-4094] 间的整数，资源池中VLAN不重复且合法（[1,4094]之间的整数），输入是乱序的。
 */
public class VLAN资源池 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] array = Arrays.stream(scanner.nextLine().split(",")).map(a -> Arrays.stream(a.split("-")).mapToInt(Integer::parseInt).toArray()).toArray(int[][]::new);

        int outNum = Integer.parseInt(scanner.nextLine());
        boolean[] dics = new boolean[4095];

        for(int i=0; i<array.length; i++){
            int startIndex = array[i][0];
            int endIndex =array[i].length==1?startIndex: array[i][1];
            for(int j=startIndex; j<=endIndex; j++){
                dics[j]=true;
            }
        }
        //拿出的数字置为false
        dics[outNum]=false;

        StringJoiner stringJoiner = new StringJoiner(",");
        for(int l=1; l<dics.length; l++){
            //找到第一个存在的数
            if(!dics[l]){
                continue;
            }
            for(int r=l+1; r<dics.length; r++){
                //找到第一个不存在的数
                if(dics[r]){
                    continue;
                }
                if(l==r-1){
                    //说明是只有一个数，没有挨着的
                    stringJoiner.add(l+"");
                }else {
                    stringJoiner.add(l+"-"+(r-1));
                }
                l=r;
                break;
            }
        }
        System.out.println(stringJoiner);
    }
}
