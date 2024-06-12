import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class 开源项目热度榜单 {

    static class Project{
        String name;
        int watch;
        int star;
        int fork;
        int issue;
        int mr;

        public Project(String name, int watch, int star, int fork, int issue, int mr) {
            this.name = name;
            this.watch = watch;
            this.star = star;
            this.fork = fork;
            this.issue = issue;
            this.mr = mr;
        }
    }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int proNums = scanner.nextInt();

        List<Project> projectList = new ArrayList<>(proNums);

        int[] weights = new int[5];

        for(int i=0;i<5;i++){
            weights[i]=scanner.nextInt();
        }

        for(int i=0;i<proNums;i++){
            projectList.add(new Project(scanner.next(),scanner.nextInt(),scanner.nextInt(),scanner.nextInt(),scanner.nextInt(),scanner.nextInt()));
        }

        projectList.sort((a,b)->a.watch*weights[0]+a.star*weights[1]+a.fork*weights[2]+a.issue*weights[3]+a.mr*weights[4]==
                b.watch*weights[0]+b.star*weights[1]+b.fork*weights[2]+b.issue*weights[3]+b.mr*weights[4]?
                a.name.toLowerCase().compareTo(b.name.toLowerCase()):
                (b.watch*weights[0]+b.star*weights[1]+b.fork*weights[2]+b.issue*weights[3]+b.mr*weights[4])-
                        (a.watch*weights[0]+a.star*weights[1]+a.fork*weights[2]+a.issue*weights[3]+a.mr*weights[4]));

        for(Project project:projectList){
            System.out.println(project.name);
        }
    }


}
