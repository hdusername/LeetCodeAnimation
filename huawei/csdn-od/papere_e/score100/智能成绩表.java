import java.util.*;

/**
 * 小明来到某学校当老师，需要将学生按考试总分或单科分数进行排名，你能帮帮他吗？
 *
 * 输入描述：
 * 第 1 行输入两个整数，学生人数 n 和科目数量 m。
 * 0 < n < 100
 * 0 < m < 10
 *
 * 第 2 行输入 m 个科目名称，彼此之间用空格隔开。
 * 科目名称只由英文字母构成，单个长度不超过10个字符。
 * 科目的出现顺序和后续输入的学生成绩一一对应。
 * 不会出现重复的科目名称。
 *
 * 第 3 行开始的 n 行，每行包含一个学生的姓名和该生 m 个科目的成绩（空格隔开）
 * 学生不会重名。
 * 学生姓名只由英文字母构成，长度不超过10个字符。
 * 成绩是0~100的整数，依次对应第2行种输入的科目。
 *
 * 第n+2行，输入用作排名的科目名称。若科目不存在，则按总分进行排序。
 *
 * 输出描述：
 * 输出一行，按成绩排序后的学生名字，空格隔开。成绩相同的按照学生姓名字典顺序排序。
 */
public class 智能成绩表 {
    public static class Student{
        String name;
        //与输入的科目顺序相对应
        int[] rank;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int[] getRank() {
            return rank;
        }

        public void setRank(int[] rank) {
            this.rank = rank;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //String line1 = scanner.nextLine();

       // int[] line1s = Arrays.stream(line1.split(" ")).mapToInt(Integer::parseInt).toArray();
        //获取人数和科目数
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        List<Student> studentList = new ArrayList<>();

        //String[] subjects = new String[m];
        Map<String, Integer> subjectMap = new HashMap<>();
        for(int i=0;i<m;i++) {
            //保存科目及索引信息
            subjectMap.put(scanner.next(), i);
        }

        for(int i=0;i<n;i++){
            Student student = new Student();
            //设置姓名
            student.setName(scanner.next());
            int[] ranks = new int[m+1];
            //总成绩
            int allScore = 0;
            for(int j=0;j<m;j++){
                int subjectScore = scanner.nextInt();
                ranks[j]=subjectScore;
                allScore+=subjectScore;
            }
            //总成绩放到m的位置
            ranks[m]=allScore;
            student.setRank(ranks);
            studentList.add(student);
        }

        //排名的科目
        String rankSubject = scanner.next();

        //找到排名科目,如果没有此科目则按总分排名
        //rankIndex在匿名内部类中使用了，只能有这个初始化值，不能再次赋值了，否则会报编译错误
        int rankIndex = subjectMap.getOrDefault(rankSubject, m);

        studentList.sort((a,b)->b.getRank()[rankIndex]-a.getRank()[rankIndex]==0?a.getName().compareTo(b.getName()):b.getRank()[rankIndex]-a.getRank()[rankIndex]);

        StringJoiner stringJoiner= new StringJoiner(" ");
        studentList.stream().forEach((a)->stringJoiner.add(a.getName()));

        System.out.println(stringJoiner);
    }
}
