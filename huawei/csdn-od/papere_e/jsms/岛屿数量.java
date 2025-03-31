import java.util.LinkedList;

/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 示例 1：
 *
 * 输入：grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * 输出：1
 * 示例 2：
 *
 * 输入：grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * 输出：3
 */
public class 岛屿数量 {

    int[][] offsets = {{0, 1},{0, -1},{1, 0},{-1, 0}};

    public int numIslands(char[][] grid) {

        int ans = 0;
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j]=='1'){
                    dfs(new int[]{i, j}, grid);
                    ans++;
                }
            }
        }
        return ans;
    }

    public void dfs(int[] points, char[][] grid){
        LinkedList<int[]> linkedList = new LinkedList<>();
        linkedList.addLast(points);

        while (linkedList.size()>0) {
            int[] cur_poi = linkedList.removeLast();
            int old_x = cur_poi[0];
            int old_y = cur_poi[1];

            grid[old_x][old_y] = '0';


            for (int[] offset : offsets) {
                int new_x = old_x + offset[0];
                int new_y = old_y + offset[1];

                if (new_x >= 0 && new_x < grid.length && new_y >= 0 && new_y < grid[0].length && grid[new_x][new_y] == '1') {
                    linkedList.addLast(new int[]{new_x, new_y});
                    //grid[new_x][new_y] = '0';
                }
            }
        }
    }
}
