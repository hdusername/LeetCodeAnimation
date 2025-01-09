import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 给定长度为 n 的无序的数字数组，每个数字代表二叉树的叶子节点的权值，数字数组的值均大于等于1。
 * 请完成一个函数，根据输入的数字数组，生成哈夫曼树，并将哈夫曼树按照中序遍历输出。
 * 为了保证输出的二叉树中序遍历结果统一，增加以下限制：
 * 二叉树节点中，左节点权值小于右节点权值，根节点权值为左右节点权值之和。当左右节点权值相同时，左子树高度小于等于右子树高度。
 * 注意：
 * 所有用例保证有效，并能生成哈夫曼树。
 * 提醒：
 * 哈夫曼树又称为最优二叉树，是一种带权路径长度最短的二叉树。
 * 所谓树的带权路径长度，就是树中所有的叶节点的权值乘上其到根节点的路径长度（若根节点为 0 层，叶节点到根节点的路径长度为叶节点的层数）
 *
 * 输入描述：
 * 例如：由叶子节点：5 15 40 30 10，生成的最优二叉树如下图所示，该树的最短带权路径长度为：40 * 1 + 30 * 2 + 15 * 3 + 5 * 4 + 10 * 4 = 205。
 *
 * 输出描述：
 * 输出一个哈夫曼树的中序遍历数组，数值间以空格分隔
 */
public class 生成哈夫曼树 {

    public static class Tree{
        Tree leftNode;
        Tree rightNode;
        //权值
        int weight;
        //高度，在权值相同时为了方便比较子节点树高度，而判断是作为左子节点，还是右子节点
        int height;

        public Tree(Tree leftNode, Tree rightNode, int weight, int height) {
            this.leftNode = leftNode;
            this.rightNode = rightNode;
            this.weight = weight;
            this.height = height;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //取出共有几个节点
        int n = scanner.nextInt();
        //题目中说明了当左右节点权值相同时，左子树高度小于等于右子树高度，这样就可以保证先取出来的height小的作为左节点
        PriorityQueue<Tree> queue = new PriorityQueue<>((a,b)->a.weight== b.weight?a.height-b.height:a.weight-b.weight);

        for(int i=0; i<n; i++){
            //将所有节点权值放到队列中

            queue.add(new Tree(null, null, scanner.nextInt(), 0));
        }

        //当queue.size等于1的时候，说明根节点已经关联上了，也就是已经生成好了哈夫曼树
        while (queue.size()>1){
            //因为已经按照权值排序了，所以先取出来的一定是左子树
            Tree leftNode = queue.poll();
            Tree rightNode = queue.poll();

            Tree faTree = new Tree(leftNode, rightNode, leftNode.weight+ rightNode.weight, leftNode.height+1);

            queue.add(faTree);

        }

        StringJoiner stringJoiner = new StringJoiner(" ");
        middleOutput(queue.poll(), stringJoiner);
        System.out.println(stringJoiner);
    }
    public static void middleOutput(Tree node, StringJoiner stringJoiner){
        if(node.leftNode != null){
            middleOutput(node.leftNode, stringJoiner);
        }
        stringJoiner.add(node.weight+"");
        if(node.rightNode != null){
            middleOutput(node.rightNode, stringJoiner);
        }
    }
}
