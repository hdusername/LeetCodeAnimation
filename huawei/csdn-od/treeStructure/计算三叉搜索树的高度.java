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

    static class TreeNode{
        int value;
        int height;
        TreeNode leftTree;
        TreeNode middleTree;
        TreeNode rightTree;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    static class Tree{
        int treeHight = 0;

        TreeNode rootNode = null;
        public void add(int val){
            TreeNode curTreeNode = null;
            TreeNode newTreeNode = new TreeNode(val);
            if(rootNode == null){
                newTreeNode.height = 1;
                rootNode = newTreeNode;
                treeHight=1;
            }else {
                //从根节点向下遍历
                curTreeNode = rootNode;
                while(true) {

                    newTreeNode.height = curTreeNode.height+1;
                    treeHight = Math.max(treeHight, newTreeNode.height);
                    if (val < curTreeNode.value - 500) {
                        if (curTreeNode.leftTree == null) {
                            curTreeNode.leftTree = newTreeNode;
                            break;
                        } else {
                            curTreeNode = curTreeNode.leftTree;
                        }
                    }else if(val > curTreeNode.value + 500){
                        if (curTreeNode.rightTree == null) {
                            curTreeNode.rightTree = newTreeNode;
                            break;
                        } else {
                            curTreeNode = curTreeNode.rightTree;
                        }
                    }else{
                        if (curTreeNode.middleTree == null) {
                            curTreeNode.middleTree = newTreeNode;
                            break;
                        } else {
                            curTreeNode = curTreeNode.middleTree;
                        }
                    }
                }
            }

        }
    }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        Tree tree = new Tree();
        for(int i=0;i<n;i++){
            int value = scanner.nextInt();

            tree.add(value);
        }
        System.out.println(tree.treeHight);

    }
}
