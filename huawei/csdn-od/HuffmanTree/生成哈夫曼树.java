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
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        PriorityQueue<Tree> trees = new PriorityQueue<>((a,b)->{return a.val-b.val!=0?a.val-b.val:a.height-b.height;});
        for(int i=0;i<n;i++){
            trees.add(new Tree(null,null,scanner.nextInt(),0));
        }

        while (trees.size()>1){
            //获取左节点和右节点
            Tree leftTree = trees.poll();
            Tree rightTree = trees.poll();

            //这里虽然用height的高度表示的是从下向上的高度，而题意中是从上向下的高度，但是不影响，因最后也不是求高度，只是用这个height表示左子树和右子树的高度哪个高
            //从下向上高就是从上向下高
            //从而对左右两个子树节点进行排序，也就是trees.poll();时取出的小的（val小，val一样的话就是height小）为左子树
            Tree faTree = new Tree(leftTree, rightTree, leftTree.val + rightTree.val, Math.max(leftTree.height, rightTree.height) +1);

            trees.add(faTree);
        }
        StringJoiner stringJoiner = new StringJoiner(" ");

        middleOutput(trees.poll(),stringJoiner);

        System.out.println(stringJoiner);

    }

    private static void middleOutput(Tree tree,StringJoiner stringJoiner) {
        if(tree.leftTree !=null){
            middleOutput(tree.leftTree,stringJoiner);
        }
        stringJoiner.add(tree.val+"");
        if(tree.rightTree !=null){
            middleOutput(tree.rightTree,stringJoiner);
        }

    }

    static class Tree{
        private Tree leftTree;
        private Tree rightTree;
        private int val;
        private int height;

        public Tree(Tree leftTree, Tree rightTree, int val, int height) {
            this.leftTree = leftTree;
            this.rightTree = rightTree;
            this.val = val;
            this.height = height;
        }
    }
}
