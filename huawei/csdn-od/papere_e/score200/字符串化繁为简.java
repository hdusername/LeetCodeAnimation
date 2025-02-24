    import java.util.ArrayList;
    import java.util.HashSet;
    import java.util.LinkedList;
    import java.util.Scanner;

    /**
     * 给定一个输入字符串，字符串只可能由英文字母（ 'a' ~ 'z'、'A' ~ 'Z' ）和左右小括号（ '('、')' ）组成。
     *
     * 当字符里存在小括号时，小括号是成对的，可以有一个或多个小括号对，小括号对不会嵌套，小括号对内可以包含1个或多个英文字母，也可以不包含英文字母。
     *
     * 当小括号对内包含多个英文字母时，这些字母之间是相互等效的关系，而且等效关系可以在不同的小括号对之间传递，即当存在 'a' 和 'b' 等效和存在 'b' 和 'c' 等效时，
     * 'a' 和 'c' 也等效，另外，同一个英文字母的大写字母和小写字母也相互等效（即使它们分布在不同的括号对里）
     *
     * 需要对这个输入字符串做简化，输出一个新的字符串，输出字符串里只需保留输入字符串里的没有被小括号对包含的字符（按照输入字符串里的字符顺序），
     * 并将每个字符替换为在小括号对里包含的且字典序最小的等效字符。
     *
     * 如果简化后的字符串为空，请输出为"0"。
     *
     * 示例 :
     *  输入字符串为"never(dont)give(run)up(f)()"，初始等效字符集合为('d', 'o', 'n', 't')、('r', 'u', 'n')，由于等效关系可以传递，
     *  因此最终等效字符集合为('d', 'o', 'n', 't', 'r', 'u')，将输入字符串里的剩余部分按字典序最小的等效字符替换后得到"devedgivedp'
     *
     *  输入描述：
     *  input_string
     *  输入为1行，代表输入字符串
     *
     *  输出描述：
     *  output_string
     *  输出为1行，代表输出字符串
     *
     *  备注：
     *  输入字符串的长度在1~100000之间
     *
     *  输入示例：
     *  ()abd
     *
     *  输出：
     *  abd
     *
     *  解释：
     *  输入字符串里没有被小括号包含的子字符串为"abd"，其中每个字符没有等效字符，输出为"abd"
     */
    public class 字符串化繁为简 {
        //这个解法有通不过的案例，使用标准答案即可
//        static class UnionSet{
//            int[] unionArr ;
//
//
//            public UnionSet(int n) {
//                this.unionArr = new int[n];
//                for(int i=0; i<n; i++){
//                    unionArr[i] = i;
//                }
//            }
//
//            public int find(int i){
//                if(i!=unionArr[i]){
//                    return unionArr[i] = find(unionArr[i]);
//                }else {
//                    return i;
//                }
//            }
//
//            public void union(int rela_1, int rela_2){
//                int fa_1 = find(rela_1);
//                int fa_2 = find(rela_2);
//
//                //谁的值小谁当根，因为最后要找出来这个根去替换字符串中的字符
//                if(fa_1<fa_2){
//                    unionArr[fa_2] = fa_1;
//                }else if (fa_1>fa_2){
//                    unionArr[fa_1] = fa_2;
//                }
//                //值相同不用管，因为是同一个根
//
//            }
//        }
//
//        public static void main(String[] args) {
//
//            Scanner scanner = new Scanner(System.in);
//            String s = scanner.nextLine();
//
//            StringBuffer stringBuffer = new StringBuffer();
//            HashSet<Character> hashSet = new HashSet<>();
//            UnionSet unionSet = new UnionSet(128);
//
//            LinkedList<Character> linkedList = new LinkedList<>();
//            boolean isOpen = false;
//
//            for(int i=0; i<s.length(); i++){
//                char c = s.charAt(i);
//
//                if(!isOpen && c=='('){
//                    isOpen = true;
//                }else if(isOpen && c==')'){
//
//                    isOpen = false;
//                    linkedList.clear();
//                } else if (isOpen) {
//                    Character upperC = Character.toUpperCase(c);
//                    Character lowerC = Character.toLowerCase(c);
//
//                    //相同字符，不同大小写
//                    //多个括号间进行相互关联
//                    if(!hashSet.contains(c) && hashSet.contains(upperC)){
//                        unionSet.union(c, upperC);
//                    } else if (!hashSet.contains(c) && hashSet.contains(lowerC)) {
//                        unionSet.union(c, lowerC);
//                    } else if (linkedList.size()>0) {
//                        //不同字符
//                        //括号中的字符进行关联
//                        unionSet.union(linkedList.get(0), c);
//
//                    }
//                    linkedList.add(c);
//                    hashSet.add(c);
//                }else {
//                    stringBuffer.append(c);
//                }
//            }
//            char[] tmp_arrays = stringBuffer.toString().toCharArray();
//            for (int i=0; i<tmp_arrays.length; i++) {
//                int rootC = unionSet.find(tmp_arrays[i]);
//                if(rootC != tmp_arrays[i]){
//                    tmp_arrays[i] = (char) rootC;
//                }
//            }
//
//            System.out.println(new String(tmp_arrays));
//
//        }

        //标准答案
    //    import java.util.*;
    //
    //    public class Main {
            public static void main(String[] args) {
                Scanner sc = new Scanner(System.in);
                String s = sc.nextLine();
                System.out.println(getResult(s));
            }

            public static String getResult(String s) {
                // 主体字符容器
                StringBuilder sb = new StringBuilder();

                // 等效字符容器
                ArrayList<Character> list = new ArrayList<>();

                UnionFindSet ufs = new UnionFindSet(128);
                HashSet<Character> set = new HashSet<>();

                //  isOpen标志，表示有没有遇到 '(' 字符
                boolean isOpen = false;

                for (int i = 0; i < s.length(); i++) {
                    char c = s.charAt(i);

                    if (c == '(') {
                        isOpen = true; // 接下来将开始收集等效字符
                    } else if (c == ')') {
                        isOpen = false; // 某个()内的等效字符收集完成

                        if (list.isEmpty()) continue;

                        char base = list.get(0);

                        // 等效传递（利用并查集）
                        for (Character letter : list) {
                            // 不同括号间的大小写字母可以形成等效传递, 比如括号1中 (abc), 括号2中 (AXY), 则两个括号的a,A形成等效传递，最终:a,b,c,A,X,Y互相等效
                            char upper = Character.toUpperCase(letter);
                            char lower = Character.toLowerCase(letter);

                            // set记录的是之前括号里面出现过的字母
                            if (set.contains(lower)) {
                                ufs.union(letter, lower); // 括号间等效传递（利用并查集）
                            }

                            if (set.contains(upper)) {
                                ufs.union(letter, upper); // 括号间等效传递（利用并查集）
                            }

                            //todo: 如果letter的值等于之前括号中存在的字符，也就是说之前括号中的letter和本括号中的letter同时是大写或者同时是小写
                            //也会进行union，这时并查集不会改变，因为传到union方法中的两个值相同，值相同的话逻辑中子父关系不会改变，也就是说如果之前
                            //letter的根是a,union后根还是a不会变，相当于没有进行union操作
                            ufs.union(letter, base); // 括号内等效传递
                        }

                        // 将当前()内字符加入set
                        set.addAll(list);
                        // 清空list, 用于收集下个()内的字母
                        list.clear();
                    } else if (isOpen) {
                        list.add(c); // 等效字符容器
                    } else {
                        sb.append(c); // 主体字符容器
                    }
                }

                // 等效替换
                char[] cArr = sb.toString().toCharArray();
                for (int i = 0; i < 128; i++) {
                    char ch = ((char) i);
                    char fa = ((char) ufs.find(i)); // 找到ch字母的等效的字典序最小的字母fa

                    // 将ch替换为fa
                    for (int j = 0; j < cArr.length; j++) {
                        if (cArr[j] == ch) {
                            cArr[j] = fa;
                        }
                    }
                }

                String res = new String(cArr);
                return res.isEmpty() ? "0" : res; // 如果简化后的字符串为空，请输出为"0"。
            }
        }

        // 并查集实现
        class UnionFindSet {
            int[] fa;

            public UnionFindSet(int n) {
                this.fa = new int[n];
                for (int i = 0; i < n; i++) fa[i] = i;
            }

            public int find(int x) {
                if (x != this.fa[x]) {
                    this.fa[x] = this.find(this.fa[x]);
                    return this.fa[x];
                }
                return x;
            }

            public void union(int x, int y) {
                int x_fa = this.find(x);
                int y_fa = this.find(y);

                // 保证字典序小的字符优先为根
                if (x_fa < y_fa) {
                    this.fa[y_fa] = x_fa;
                } else {
                    this.fa[x_fa] = y_fa;
                }
            }
    //    }

    }
