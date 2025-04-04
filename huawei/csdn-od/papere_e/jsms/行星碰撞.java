import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个整数数组 asteroids，表示在同一行的小行星。数组中小行星的索引表示它们在空间中的相对位置。
 *
 * 对于数组中的每一个元素，其绝对值表示小行星的大小，正负表示小行星的移动方向（正表示向右移动，负表示向左移动）。每一颗小行星以相同的速度移动。
 *
 * 找出碰撞后剩下的所有小行星。碰撞规则：两个小行星相互碰撞，较小的小行星会爆炸。如果两颗小行星大小相同，则两颗小行星都会爆炸。两颗移动方向相同的小行星，永远不会发生碰撞。
 *
 *
 *
 * 示例 1：
 *
 * 输入：asteroids = [5,10,-5]
 * 输出：[5,10]
 * 解释：10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞。
 * 示例 2：
 *
 * 输入：asteroids = [8,-8]
 * 输出：[]
 * 解释：8 和 -8 碰撞后，两者都发生爆炸。
 * 示例 3：
 *
 * 输入：asteroids = [10,2,-5]
 * 输出：[10]
 * 解释：2 和 -5 发生碰撞后剩下 -5 。10 和 -5 发生碰撞后剩下 10 。
 */
public class 行星碰撞 {



    //自己写的
    public int[] asteroidCollisionTest(int[] asteroids) {

        LinkedList<Integer> linkedList = new LinkedList<>();
        out: for(int i=0; i<asteroids.length; i++){
            if(asteroids[i]<0){
                //int tmp_ast = asteroids[i];
                while (linkedList.size()>0 && linkedList.getLast()>0 ){
                    int pop_param = linkedList.pollLast();

                    if(asteroids[i]+pop_param>=0){
                        if(asteroids[i]+pop_param>0) {
                            linkedList.add(pop_param);
                        }
                        continue out;
                    }

                }
                linkedList.add(asteroids[i]);
            }else {
                linkedList.add(asteroids[i]);
            }

        }

        int[] ans = new int[linkedList.size()];

        for(int i=0; i<linkedList.size(); i++){
            ans[i] = linkedList.get(i);
        }
        return ans;
    }

        public int[] asteroidCollision(int[] asteroids) {

            Deque<Integer> stack = new ArrayDeque<Integer>();
            for (int aster : asteroids) {
                //不管正负，开始都是存活状态
                boolean alive = true;
                while (alive && aster < 0 && !stack.isEmpty() && stack.peek() > 0) {
                    alive = stack.peek() < -aster; // aster 是否存在
                    if (stack.peek() <= -aster) {  // 栈顶行星爆炸
                        stack.pop();
                    }
                }
                if (alive) {
                    stack.push(aster);
                }
            }
            int size = stack.size();
            int[] ans = new int[size];
            for (int i = size - 1; i >= 0; i--) {
                ans[i] = stack.pop();
            }
            return ans;


        }

}
