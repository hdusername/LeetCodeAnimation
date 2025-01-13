import java.util.Scanner;

/**
 * 商人经营一家店铺，有 number 种商品，由于仓库限制每件商品的最大持有数量是 item[index]，每种商品的价格是 item_price[item_index][day]。
 * 通过对商品的买进和卖出获取利润，请给出商人在 days 天内能获取的最大的利润。
 * 注：同一件商品可以反复买进和卖出
 *
 * 输入描述：
 * 3 // 输入商品的数量 number
 * 3 // 输入商人售货天数 days
 * 4 5 6 // 输入仓库限制每件商品的最大持有数量是 item[index]
 * 1 2 3 // 输入第一件商品每天的价格
 * 4 3 2 // 输入第二件商品每天的价格
 * 1 5 3 // 输入第三件商品每天的价格
 *
 * 输出描述：
 * 32 // 输出商人在这段时间内的最大利润
 *
 * 备注：
 * 根据输入的信息：
 * number = 3
 * days = 3
 * item[3] = {4, 5, 6}
 * item_price[3][4] = {{1, 2, 3}, {4, 3, 2}, {1, 5, 3}}
 *
 * 针对第一件商品，商人在第一天的价格是 item_price[0][0] = 1 时买入 item[0] 件，在第三天 item_price[0][2] = 3 的时候卖出，获利最大是 8；
 * 针对第二件商品，不进行交易，获利最大时 0；
 * 针对第三件商品，商人在第一天价格是 item_price[2][0] = 1 时买入 item[2] 件，在第二天 item_price[2][0] = 5 的时候卖出，获利最大是24；
 * 因此这段时间商人能获取的最大利润是 8 + 24 = 32；
 */
public class 贪心的商人 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //商品数量
        int number = scanner.nextInt();
        //售货天数
        int days = scanner.nextInt();
        //商品最大持有量
        int[] item = new int[number];
        //商品每天价格
        int[][] item_price = new int[number][days];

        //最大利润
        int ans = 0;

        for(int i=0; i<number; i++){
            item[i] = scanner.nextInt();
        }
        for(int x=0; x<number; x++){
            for(int y=0; y<days; y++){
                item_price[x][y] = scanner.nextInt();
            }
        }

        for(int x=0; x<number; x++){
            for(int y=1; y<days; y++){
                //只要第二天比第一天单价增长，就要买入再卖出
                ans += item_price[x][y] - item_price[x][y-1]>0?item[x]*(item_price[x][y] - item_price[x][y-1]) : 0;
            }
        }

        System.out.println(ans);
    }
}
