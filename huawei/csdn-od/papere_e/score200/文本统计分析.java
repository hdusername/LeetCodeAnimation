import java.util.ArrayList;
import java.util.Scanner;

/**
 * 有一个文件，包含以一定规则写作的文本，请统计文件中包含的文本数量。
 * 规则如下：
 * 文本以 ";" 分隔，最后一条可以没有 ";" ，但空文本不能算语句，比如
 * COMMAND A; ;
 * 只能算一条语句。
 *
 * 文本可以跨行，比如下面，是一条文本，而不是三条
 * COMMAND A
 * AND
 * COMMAND B;
 *
 * 文本支持字符串，字符串为成对的单引号(')或者成对的双引号(")，字符串可能出现用转义字符(\)处理的单双引号("your input is\"")和转义字符本身，比如
 * COMMAND A "Say \"hello\"";
 *
 * 支持注释，可以出现在字符串之外的任意位置注释以”--“开头，到换行结束，比如
 * COMMAND A; -- this is comment
 * COMMAND -- comment
 * A AND COMMAND B;
 * 注意字符串内的”--“，不是注释。
 *
 * 输入描述：
 * 文本文件
 *
 * 输出描述：
 * 包含的文本数量
 *
 * 用例输入：
 * COMMAND TABLE IF EXISTS "UNITED STATE";
 *  COMMAND A GREAT (
 *  ID ADSAB,
 *  download_length INTE-GER,  -- test
 *  file_name TEXT,
 *  guid TEXT,
 *  mime_type TEXT,
 *  notifica-tionid INTEGER,
 *  original_file_name TEXT,
 *  pause_reason_type INTEGER,
 *  resumable_flag INTEGER,
 *  start_time INTEGER,
 *  state INTEGER,
 *  folder TEXT,
 *  path TEXT,
 *  total_length INTE-GER,
 *  url TEXT
 *
 * 输出：
 * 2
 */
public class 文本统计分析 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<String> ansList = new ArrayList<>();
        //文本开始后，将文本先临时存入buffer中，找到文本结束位置后存入ansList中
        StringBuffer stringBuffer = new StringBuffer();

        //是否双引号开始了
        boolean isDubboOpen = false;
        //是否单引号开始了
        boolean isSingleOpen = false;
        out: while (scanner.hasNextLine()){
            String curLineStr = scanner.nextLine();

            //不影响最终结果，因为题目中描述说有可能最后一个文本不带;这个字符
            //curLineStr += ";";
            char[] charArray = curLineStr.toCharArray();

            for(int i=0; i<charArray.length; i++){

                if(!isDubboOpen && charArray[i]=='"'){
                    //双引号开始
                    isDubboOpen = true;
                } else if (!isSingleOpen && charArray[i] == '\'') {
                    //单引号开始
                    isSingleOpen = true;
                } else if (isDubboOpen) {
                    //是双引号
                    if(charArray[i] != '"' || (i>0 && charArray[i]=='"' && charArray[i-1]=='\\')){
                        //没到双引号的结尾或者是双引号中的双引号
                        stringBuffer.append(charArray[i]);
                    } else {
                        //单双引号中的空字符、制表符不算空，所以要加进去这个单双引号当作这个空字符制表符等以便最终放到anslist中
                        //例如用例'abc''abc';'';'	';' 	'
                        //其实是有四个文本的
                        stringBuffer.append(charArray[i]);
                        isDubboOpen = false;
                    }

                }else if (isSingleOpen) {
                    //是单引号
                    if(charArray[i] != '\'' || (i>0 && charArray[i]=='\'' && charArray[i-1]=='\\')){
                        stringBuffer.append(charArray[i]);
                    }else {
                        stringBuffer.append(charArray[i]);
                        isSingleOpen = false;
                    }

                }else if(charArray[i]==';'){
                    //这个判断放在这里是因为如果有这种输入：say "hello;\n world";
                    //其实这是一个文本，如果这个判断放在最前面就会导致判断出来有两个文本
                    if(!stringBuffer.toString().isEmpty()){
                        ansList.add(stringBuffer.toString());
                        stringBuffer.setLength(0);

                    }
                }else if(charArray[i] == ' ' || charArray[i] == '\t'){

                }else if(charArray[i] == '-' && i+1<curLineStr.length() && charArray[i+1] == '-'){
                    continue out;
                }
                else {
                    stringBuffer.append(charArray[i]);
                }
            }


        }

        if(!stringBuffer.toString().isEmpty()){
            ansList.add(stringBuffer.toString());
        }

        System.out.println(ansList.size());
    }



//        public static void main(String[] args) {
//            Scanner sc = new Scanner(System.in);
//
//            StringBuilder sb = new StringBuilder(); // 记录文件内容
//
//            // 注意：字符串的成对引号不一定限制在一行内
//            boolean isSingleOpen = false; // 单引号是否处于闭合状态
//            boolean isDoubleOpen = false; // 双引号是否处于闭合状态
//
//            while (sc.hasNextLine()) {
//                char[] s = sc.nextLine().toCharArray(); // 获取一行
//
//                for (int i = 0; i < s.length; i++) { // 遍历一行中每个字符
//                    char c = s[i];
//
//                    if (i == 0 || s[i - 1] != '\\') { // 如果当前字符是单引号或双引号，且前面一个字符不是斜杠，那么对应引号就是字符串标识，对应引号状态取反
//                        switch (c) {
//                            case '\'':
//                                isSingleOpen = !isSingleOpen;
//                                break;
//                            case '"':
//                                isDoubleOpen = !isDoubleOpen;
//                                break;
//                        }
//                    }
//
//                    // 如果当前处于引号开启状态，那么当前字符属于字符串内容，为了避免字符串内容影响逻辑，我们可以忽略字符串内容
//                    if (isSingleOpen || isDoubleOpen) {
//                        continue;
//                    }
//
//                    // 如果当前字符不是字符串内容，且当前字符和前一个字符都是 - 则为注释，后续所有内容都可以忽略
//                    if (c == '-' && i + 1 < s.length && s[i + 1] == '-') {
//                        break;
//                    }
//
//                    // 空白字符和制表符忽略
//                    if (c != ' ' && c != '\t') {
//                        sb.append(c);
//                    }
//                }
//            }
//
//            sb.append(";"); // 最后一条可以没有 ";" , 这里追加一个
//
//            int ans = 0;
//
//            int last = -1; // 上一个分号位置
//            for (int i = 0; i < sb.length(); i++) {
//                if (sb.charAt(i) == ';') { // 当前位置是分号
//                    if (i - last > 1) {
//                        ans++; // 如果两个分号不相邻，则有效文本数量+1
//                    }
//                    last = i;
//                }
//            }
//
//            System.out.println(ans);
//        }

}
