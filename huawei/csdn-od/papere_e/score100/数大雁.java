import java.util.HashMap;
import java.util.Scanner;

/**
 * 一群大雁往南飞，给定一个字符串记录地面上的游客听到的大雁叫声，请给出叫声最少由几只大雁发出。
 * 具体的：
 * 大雁发出的完整叫声为”quack“，因为有多只大雁同一时间嘎嘎作响，所以字符串中可能会混合多个”quack”。
 * 大雁会依次完整发出”quack”，即字符串中’q’ ,‘u’, ‘a’, ‘c’, ‘k’ 这5个字母按顺序完整存在才能计数为一只大雁。如果不完整或者没有按顺序则不予计数。
 * 如果字符串不是由’q’, ‘u’, ‘a’, ‘c’, ‘k’ 字符组合而成，或者没有找到一只大雁，请返回-1。
 *
 * 输入描述：
 * 一个字符串，包含大雁quack的叫声。1 <= 字符串长度 <= 1000，字符串中的字符只有’q’, ‘u’, ‘a’, ‘c’, ‘k’。
 *
 * 输出描述：
 * 大雁的数量
 */
//todo: 这个方法并不能通过所有用例
public class 数大雁 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        char[] charArray = s.toCharArray();
        int count=0;
        while (find(charArray)){
            count++;
        }

        //count = find2(s);
        System.out.println(count==0?-1:count);

    }

    //不能用这种方法，因为本题不是要求quack有几个，而是求的有几个鸭子
//    public static int find2(String birdStr) {
//
//        HashMap<Character, Integer> indexes = new HashMap<>();
//        for (int i = 0; i < "quack".length(); i++) {
//            indexes.put(birdStr.charAt(i), i); // 不会存在重复字母
//        }
//
//
//        int[] count = new int[5];
//        for (int i = 0; i < birdStr.length(); i++) {
//            char c = birdStr.charAt(i);
//
//            if (indexes.containsKey(c)) {
//                int idx = indexes.get(c);
//                // 下面判断逻辑请看图解
//                if (idx == 0 || count[idx] < count[idx - 1]) {
//                    count[idx]++;
//                }
//            }
//        }
//
//        return count[count.length - 1];
//    }
    /**
     * 功能：一只大雁叫完一次，紧接着会叫下一次
     * @param charArray
     * @return
     */
    public static boolean find(char[] charArray){
        boolean flag = false;
        //此角标记录叫声quack的角标值
        int index = 0;
        //保存在字符串种找到的叫声值的角标
        int[] ans = new int[5];
        for(int i=0; i<charArray.length; i++){
            if(charArray[i] == "quack".charAt(index)){
                ans[index++] = i;
            }

            if(index == 5){
                //说明找到了quack这几个值了
                for(int j =0; j<ans.length; j++){
                    //为了再次调用find方法时，不再能与quack进行对应
                    charArray[ans[j]]=' ';
                }
                index = 0;
                //不用新建数组了，因为index已经置为0了
                //ans = new int[5];
                flag=true;
            }

        }
        return flag;
    }
}

//标准答案
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.Scanner;
//
//public class Main {
//  public static void main(String[] args) {
//    Scanner sc = new Scanner(System.in);
//    String quack = sc.next();
//    System.out.println(getResult(quack));
//  }
//
//  public static int getResult(String quack) {
//    LinkedList<Integer> q = new LinkedList<>();
//
//    int u = 0, a = 0, c = 0;
//
//    ArrayList<Integer[]> ranges = new ArrayList<>();
//
//    for (int i = 0; i < quack.length(); i++) {
//      switch (quack.charAt(i)) {
//        case 'q':
//          q.add(i);
//          break;
//        case 'u':
//          if (u + 1 <= q.size()) u++;
//          break;
//        case 'a':
//          if (a + 1 <= u) a++;
//          break;
//        case 'c':
//          if (c + 1 <= a) c++;
//          break;
//        case 'k':
//          if (c >= 1) {
//求出所有完整叫声索引范围，，比如quacqkuack，第二只鸭子的第一个叫声q在第一个鸭子叫声中，ranges记录的范围是0-5、4-9，那么在后续的循环比较中，就会比较5和4的值,5>=4
//就会加一只鸭子count++
//            ranges.add(new Integer[] {q.removeFirst(), i});
//            u--;
//            a--;
//            c--;
//          }
//          break;
//        default:
//          return -1;
//      }
//    }
//
//    if (ranges.size() == 0) return -1;
//
//    int ans = 1;
//    for (int i = 0; i < ranges.size(); i++) {
//      int count = 1;
//      for (int j = i + 1; j < ranges.size(); j++) {
//        if (ranges.get(i)[1] >= ranges.get(j)[0]) {
//          count++;
//        }
//      }
//      ans = Math.max(ans, count);
//    }
//
//    return ans;
//  }
//}
