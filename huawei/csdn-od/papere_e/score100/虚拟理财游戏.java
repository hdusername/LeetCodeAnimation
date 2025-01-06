import java.util.*;

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
        //产品个数
        int m = scanner.nextInt();
        //总投资额
        int N = scanner.nextInt();
        //可接受的风险
        int X = scanner.nextInt();

        //投资回报率
        int[] investBackRates = new int[m];
        //风险
        int[] risks = new int[m];
        //最大投资额度
        int[] invests = new int[m];

        for(int i=0;i<m;i++){
            investBackRates[i]=scanner.nextInt();
        }
        for(int i=0;i<m;i++){
            risks[i]=scanner.nextInt();
        }
        for(int i=0;i<m;i++){
            invests[i]=scanner.nextInt();
        }

        int[] ans = new int[m];

        int maxInvest = 0;
        //暴力破解，每一个都遍历一遍
        for(int i=0;i<m;i++){


//            //不能这样遍历，这样会导致只有一个满足条件的投资，但是金额不足N时，不会被统计到
//            if(invests[i]>=N){
//                //如果在可接受的风险内
//                int tempBack1 = N*investBackRates[i];
//                if(risks[i]<=X && tempBack1>maxInvest){
//                    ans = new int[m];
//                    //投资额放到答案数组中
//                    ans[i]=N;
//                }
//               // maxInvest= Math.max(maxInvest, tempBack);
//            }
//            else if(invests[i]<N) {
//                for (int j = i + 1; j < m; j++) {
//
//                    int actInvest1 = 0;
//                    int actInvest2 = 0;
//
//                    //比较两种投资方式那种投资回报率高
//                    if(investBackRates[i]>investBackRates[j]){
//                        actInvest1 = invests[i];
//                        actInvest2 = Math.min(invests[j], N - invests[i]);
//
//                    }else{
//                        actInvest1 = N - invests[j]<0?0:Math.min(invests[i], N - invests[j]);
//                        actInvest2 = Math.min(invests[j], N );
//                    }
//
//
//                    if (risks[i] + risks[j] <= X) {
//                        int tempBack1 = actInvest1 * investBackRates[i];
//                        int tempBack2 = actInvest2 * investBackRates[j];
//                        if (tempBack1 + tempBack2 > maxInvest) {
//                            ans = new int[m];
//                            //投资额放到答案数组中
//                            ans[i] = actInvest1;
//                            ans[j] = actInvest2;
//                        }
//                    }
//                }
//            }

            if(risks[i]<=N){
                //单笔投资在可接受的风险内
                int actInvest1 = Math.min(invests[i], N);
                int tempBack1 = actInvest1*investBackRates[i];
                if(tempBack1>maxInvest){
                    ans = new int[m];
                    maxInvest = tempBack1;
                    //投资额放到答案数组中
                    ans[i]=actInvest1;
                }
            }else {
                continue;
            }

            for (int j = i + 1; j < m; j++) {
                if (risks[i] + risks[j] <= X) {
                    int actInvest11 = 0;
                    int actInvest22 = 0;

                    //比较两种投资方式那种投资回报率高
                    if(investBackRates[i]>investBackRates[j]){
                        actInvest11 = N-invests[i]<0?N:invests[i];
                        actInvest22 = Math.min(invests[j], N - actInvest11);

                    }else{
                        actInvest22 = N - invests[j]<0?N:invests[j];
                        actInvest11 = Math.min(invests[i], N-actInvest22 );
                    }

                    int tempBack11 = actInvest11 * investBackRates[i];
                    int tempBack22 = actInvest22 * investBackRates[j];
                    if (tempBack11 + tempBack22 > maxInvest) {
                        ans = new int[m];
                        maxInvest=tempBack11 + tempBack22;
                        //投资额放到答案数组中
                        ans[i] = actInvest11;
                        ans[j] = actInvest22;
                    }
                }
            }
        }

        StringJoiner stringJoiner = new StringJoiner(" ");
        for (int s : ans){
            stringJoiner.add(s+"");
        }
        System.out.println(stringJoiner);
    }
}
