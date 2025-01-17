import java.util.*;

/**
 * 现有两门选修课，每门选修课都有一部分学生选修，每个学生都有选修课的成绩，需要你找出同时选修了两门选修课的学生，先按照班级进行划分，班级编号小的先输出，
 * 每个班级按照两门选修课成绩和的降序排序，成绩相同时按照学生的学号升序排序。
 *
 * 输入描述：
 * 第一行为第一门选修课学生的成绩，
 * 第二行为第二门选修课学生的成绩，
 * 每行数据中学生之间以英文分号分隔，每个学生的学号和成绩以英文逗号分隔，
 * 学生学号的格式为8位数字
 *
 * 2位院系编号+入学年份后2位+院系内部1位专业编号+所在班级3位学号
 *
 * 学生成绩的取值范围为[0,100]之间的整数，
 * 两门选修课选修学生数的取值范围为[1-2000]之间的整数。
 *
 * 输出描述：
 * 同时选修了两门选修课的学生的学号，如果没有同时选修两门选修课的学生输出NULL，
 * 否则，先按照班级划分，班级编号小的先输出，每个班级先输出班级编号(学号前五位)，
 * 然后另起一行输出这个班级同时选修两门选修课的学生学号，学号按照要求排序(按照两门选修课成绩和的降序，成绩和相同时按照学号升序学生之间以英文分号分隔。
 *
 * 用例：
 * 输入：
 * 01202021,75;01201033,95;01202008,80;01203006,90;01203088,100
 * 01202008,70;01203088,85;01202111,80;01202021,75;01201100,88
 *
 * 输出：
 * 01202
 * 01202008;01202021
 * 01203
 * 01203088
 */
public class 选修课 {
    /**
     * 这个题最终输出的是班级编号和学号，所以可以优先考虑使用map，key为班级编号，先为key排序，value为学号，然后再为value排序输出即可，下面的逻辑没有这样做，但是也可以实现
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[][] subject1 = Arrays.stream(scanner.nextLine().split(";")).map(a-> Arrays.stream(a.split(",")).toArray(String[]::new)).toArray(String[][]::new);

        String[][] subject2 = Arrays.stream(scanner.nextLine().split(";")).map(a-> Arrays.stream(a.split(",")).toArray(String[]::new)).toArray(String[][]::new);

        HashMap<String, ArrayList<String>> map = new HashMap<>();

        HashMap<String, ArrayList<String>> resultMap = new HashMap<>();

        LinkedHashSet<String> sortClassList = new LinkedHashSet<>();

        for(int i=0; i<subject1.length; i++){
            map.putIfAbsent(subject1[i][0],new ArrayList());
            map.get(subject1[i][0]).add(subject1[i][1]);
        }
        for(int j=0; j<subject2.length; j++){
            map.putIfAbsent(subject2[j][0],new ArrayList<>());
            map.get(subject2[j][0]).add(subject2[j][1]);
        }

        //将只选了一门选修课的学生编号删除，剩下的都是选了两门的
        map.entrySet().removeIf(a->a.getValue().size()==1);

        if(map.isEmpty()){
            System.out.println("NULL");
            return;
        }

        map.entrySet().stream().sorted((a,b)->{
            //这里注意字符串的比较一定要用equals方法，不要用==、!=这种进行比较
            if(!a.getKey().substring(0,5) .equals( b.getKey().substring(0,5))){
                //班级号小的先输出
                return a.getKey().substring(0,5).compareTo(b.getKey().substring(0,5));
            }else {
                //todo：要注意这里如果在HashMap<String, ArrayList<String>> map = new HashMap<>();创建时ArrayList<String>不新建成Strign类型的集合在这里使用Integer::parseInt会导致编译报错的
                int suma = a.getValue().stream().mapToInt(Integer::parseInt).sum();
                int sumb = b.getValue().stream().mapToInt(Integer::parseInt).sum();

                return suma==sumb?a.getKey().compareTo(b.getKey()):sumb-suma;

            }
        }).forEach(a->{
            //为了让班级的输出有序，所以用List保存一下,而且班级是不能重复的，这里遍历的是map中所有元素，所以肯定会有重复班级所以这里要用linkedhashset去重
            sortClassList.add(a.getKey().substring(0,5));
            resultMap.putIfAbsent(a.getKey().substring(0,5), new ArrayList<>());
            resultMap.get(a.getKey().substring(0,5)).add(a.getKey());
        });

        sortClassList.stream().forEach(a-> {
                    System.out.println(a);
                    StringJoiner stringJoiner = new StringJoiner(";");
                    resultMap.get(a).stream().forEach(b->stringJoiner.add(b));
                    System.out.println(stringJoiner);
                }
        );
    }
}

