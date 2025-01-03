import java.util.Scanner;

public class Tree {

    public static void main(String[] args) {
        int height = 5; // 你可以根据需要调整树的高度
        printTree(height);
    }

    public static void printTree(int height) {
        int rows = height; // 设置圣诞树的高度

        for (int i = 1; i <= rows; i++) {
            // 打印前导空格，使得星号居中
            for (int j = 1; j <= rows - i; j++) {
                System.out.print(" ");
            }

            // 打印星号
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }

            // 换行
            System.out.println();
        }
    }


}
