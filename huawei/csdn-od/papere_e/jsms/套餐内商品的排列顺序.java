import java.util.ArrayList;
import java.util.Arrays;

/**
 * 某店铺将用于组成套餐的商品记作字符串 goods，其中 goods[i] 表示对应商品。请返回该套餐内所含商品的 全部排列方式 。
 *
 * 返回结果 无顺序要求，但不能含有重复的元素。
 *
 *
 *
 * 示例 1：
 *
 * 输入：goods = "agew"
 * 输出：["aegw","aewg","agew","agwe","aweg","awge","eagw","eawg","egaw","egwa","ewag","ewga","gaew","gawe","geaw","gewa","gwae","gwea","waeg","wage","weag","wega","wgae","wgea"]
 */
public class 套餐内商品的排列顺序 {


    public String[] goodsOrder(String goods) {

        char[] charArray = goods.toCharArray();
        //todo: 这里一定要记住要排序，不然树层无法去重，下面有去重逻辑
        Arrays.sort(charArray);
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList<String> ansList = new ArrayList<>();
        boolean[] used = new boolean[charArray.length];
        dfs(charArray, goods.length(), stringBuffer, ansList, used);

        String[] array = ansList.toArray(new String[0]);

        return array;
     //   System.out.println(ansList);
    }

    private void dfs(char[] charArray, int k, StringBuffer stringBuffer, ArrayList<String> ansList, boolean[] used) {
        if(stringBuffer.length() == k){
            ansList.add(stringBuffer.toString());
            return;
        }

        for(int i=0; i<charArray.length; i++){
            if(used[i]){
                continue;
            }
            //树层去重
            if(i>0 && charArray[i]==charArray[i-1] && !used[i-1]){
                continue;
            }

            used[i] = true;
            stringBuffer.append(charArray[i]);
            dfs(charArray, k, stringBuffer, ansList, used);
            used[i]=false;
            stringBuffer.deleteCharAt(stringBuffer.length()-1);
        }
    }

}
