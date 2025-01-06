import java.util.Scanner;

/**
 * 定义构造三叉搜索树规则如下：
 * 每个节点都存有一个数，当插入一个新的数时，从根节点向下寻找，直到找到一个合适的空节点插入。查找的规则是：
 * 如果数小于节点的数减去500，则将数插入节点的左子树
 * 如果数大于节点的数加上500，则将数插入节点的右子树
 * 否则，将数插入节点的中子树
 * 给你一系列数，请按以上规则，按顺序将数插入树中，构建出一棵三叉搜索树，最后输出树的高度
 *
 * 输入描述：
 * 第一行为一个数 N，表示有 N 个数，1 ≤ N ≤ 10000
 * 第二行为 N 个空格分隔的整数，每个数的范围为[1,10000]
 *
 * 输出描述：
 * 输出树的高度（根节点的高度为1）
 */
public class 计算三叉搜索树的高度 {

    static class Tree{
        Tree leftNode;
        Tree middelNode;
        Tree rightNode;
        int value;
        int height;

        public Tree(Tree leftNode, Tree middelNode, Tree rightNode, int value, int height) {
            this.leftNode = leftNode;
            this.middelNode = middelNode;
            this.rightNode = rightNode;
            this.value = value;
            this.height = height;
        }
    }

    //根节点高度为1
    static int maxHeight = 1;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        Tree rootTree = new Tree(null, null, null, scanner.nextInt(), 1);

        //上面已经获取了根节点的数了，所以这里少获取一个
        for(int i=1; i<N; i++){
            putNode(rootTree, scanner.nextInt());
        }

        System.out.println(maxHeight);
    }

    private static void putNode(Tree rootTree, int value) {

        Tree curTree = rootTree;
        while (curTree !=null){
            if(value < curTree.value-500){
                if(curTree.leftNode ==null){
                    Tree nodeTree = new Tree(null, null, null, value, curTree.height+1 );
                    maxHeight = Math.max(maxHeight, nodeTree.height);
                    curTree.leftNode=nodeTree;
                    break;
                }else {
                    curTree = curTree.leftNode;
                }
            } else if (value > curTree.value+500) {
                if(curTree.rightNode == null){
                    Tree nodeTree = new Tree(null, null, null, value, curTree.height+1 );
                    maxHeight = Math.max(maxHeight, nodeTree.height);
                    curTree.rightNode=nodeTree;
                    break;
                }else {
                    curTree = curTree.rightNode;
                }
            }else {
                if(curTree.middelNode == null){
                    Tree nodeTree = new Tree(null, null, null, value, curTree.height+1 );
                    maxHeight = Math.max(maxHeight, nodeTree.height);
                    curTree.middelNode=nodeTree;
                    break;
                }else {
                    curTree = curTree.middelNode;
                }
            }
        }

    }
}
