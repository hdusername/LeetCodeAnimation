
import java.util.*;

/**
 * 小明来到某学校当老师，需要将学生按考试总分或单科分数进行排名，你能帮帮他吗？
 *
 * 第 1 行输入两个整数，学生人数 n 和科目数量 m。
 * 0 < n < 100
 * 0 < m < 10
 * 第 2 行输入 m 个科目名称，彼此之间用空格隔开。
 * 科目名称只由英文字母构成，单个长度不超过10个字符。
 * 科目的出现顺序和后续输入的学生成绩一一对应。
 * 不会出现重复的科目名称。
 * 第 3 行开始的 n 行，每行包含一个学生的姓名和该生 m 个科目的成绩（空格隔开）
 * 学生不会重名。
 * 学生姓名只由英文字母构成，长度不超过10个字符。
 * 成绩是0~100的整数，依次对应第2行种输入的科目。
 *
 * 第n+2行，输入用作排名的科目名称。若科目不存在，则按总分进行排序。
 *
 * 输出一行，按成绩排序后的学生名字，空格隔开。成绩相同的按照学生姓名字典顺序排序。
 */
public class 智能成绩表 {
    static class Student{
        private String name;
        private int[] score;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //学生总数
        int n = scanner.nextInt();
        //科目总数
        int m = scanner.nextInt();

        //每个人的总分数
        int totalRecord = 0;

        List<Student> studentList = new ArrayList<>();

        HashMap<String, Integer> map = new HashMap<>();
        //获取所有科目
       // String subjectStr = scanner.nextLine();
        //String[] subjects = subjectStr.split(" ");
        for(int i =0;i<m;i++){
            map.put(scanner.next(),i);
        }

        for(int i=0;i<n;i++){
            Student student = new Student();

            //不仅放科目成绩，为了把总成绩也放到数组中，定义长度为m+1
            int[] sujectInt = new int[m + 1];

            student.name = scanner.next();

            for(int j=0;j<m;j++){
                sujectInt[j]=scanner.nextInt();
                totalRecord+=sujectInt[j];
            }
            sujectInt[m]=totalRecord;
            student.score=sujectInt;
            studentList.add(student);
            totalRecord=0;

        }

        String outSubject = scanner.next();

        Integer index = map.getOrDefault(outSubject, m);

        studentList.sort((a,b)->
            a.score[index]==b.score[index]?a.name.compareTo(b.name):b.score[index]-a.score[index]
        );

        StringJoiner stringJoiner = new StringJoiner(" ");
        for(Student student : studentList){
            stringJoiner.add(student.name+"");
        }

        System.out.println(stringJoiner);
    }
}
