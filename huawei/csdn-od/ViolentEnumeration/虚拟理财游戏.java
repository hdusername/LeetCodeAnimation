import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 在一款虚拟游戏中生活，你必须进行投资以增强在虚拟游戏中的资产以免被淘汰出局。
 * 现有一家Bank，它提供有若干理财产品 m 个，风险及投资回报不同，你有 N（元）进行投资，能接收的总风险值为X。
 * 你要在可接受范围内选择最优的投资方式获得最大回报。
 *
 * 备注：
 * 在虚拟游戏中，每项投资风险值相加为总风险值；
 * 在虚拟游戏中，最多只能投资2个理财产品；
 * 在虚拟游戏中，最小单位为整数，不能拆分为小数；
 * 投资额*回报率=投资回报
 *
 * 输入描述：
 * 第一行：产品数（取值范围[1,20]）
 * 总投资额（整数，取值范围[1, 10000]）
 * 可接受的总风险（整数，取值范围[1,200]）
 * 第二行：产品投资回报率序列，输入为整数，取值范围[1,60]
 * 第三行：产品风险值序列，输入为整数，取值范围[1, 100]
 * 第四行：最大投资额度序列，输入为整数，取值范围[1, 10000]
 *
 * 输出描述：
 * 每个产品的投资额序列
 */
public class 虚拟理财游戏 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int[] input1s = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //获取产品数
        int proNum =input1s[0];
        //获取总投资额
        int totalAmount =input1s[1];
        //获取可接受的总风险
        int totalRisk = input1s[2];
        //产品投资回报率数组
        int[] rePays = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //产品风险数组
        int[] risks = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //投资额度数组
        int[] amounts = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int maxRePay = 0;
        int[] outputs = new int[proNum];

        for(int i=0;i<proNum;i++){
            if(risks[i]<=totalRisk){
                //因为总投资可能比本产品最大投资小，所以两者取最小值
                int minAmount = Math.min(totalAmount, amounts[i]);
                int maxRePayI = minAmount * rePays[i];
                if(maxRePayI>maxRePay) {
                    Arrays.fill(outputs,0);
                    outputs[i] = minAmount;
                    maxRePay = maxRePayI;
                }
            }else {
                continue;
            }

            for(int j=i+1;j<proNum;j++){
                if(risks[i]+risks[j]<=totalRisk){

                    //回报率高的要投满
                    if(rePays[i]<rePays[j]){

                        int minAmountj = Math.min(totalAmount, amounts[j]);
                        int maxRePayJ = minAmountj * rePays[j];

                        int minAmounti = Math.min(totalAmount-minAmountj, amounts[i]);
                        int maxRePayi = minAmounti * rePays[i];
                        //maxRePay = Math.max(maxRePayJ + maxRePayi, maxRePay);
                        if(maxRePayJ + maxRePayi>maxRePay) {
                            Arrays.fill(outputs, 0);
                            outputs[j] = minAmountj;
                            outputs[i] = minAmounti;
                            maxRePay = maxRePayJ+maxRePayi;
                        }

                    }else {
                        //第一个for循环中很可能没有给outputs[i]赋到值 ，因为如果i=1，j=2时求得了一个最大回报值，当i=2循环时，很可能走不到outputs[i] = minAmount;
                        //因为可能i的回报值比i=1，j=2时的回报值小
                        int minAmounti = Math.min(totalAmount, amounts[i]);
                        int maxRePayi = minAmounti * rePays[i];
                        int minAmountj = Math.min(totalAmount-minAmounti, amounts[j]);
                        int maxRePayJ = minAmountj * rePays[j];
                        //maxRePay = Math.max(maxRePayJ + maxRePayi, maxRePay);
                        if(maxRePayJ + maxRePayi>maxRePay) {
                            Arrays.fill(outputs, 0);
                            outputs[j] = minAmountj;
                            outputs[i] = minAmounti;
                            maxRePay = maxRePayJ+maxRePayi;
                        }
                    }
                }
            }


        }
        StringJoiner stringJoiner = new StringJoiner(" ");
        for(int output: outputs){
            stringJoiner.add(output+"");
        }
        System.out.println(stringJoiner);


    }
}
