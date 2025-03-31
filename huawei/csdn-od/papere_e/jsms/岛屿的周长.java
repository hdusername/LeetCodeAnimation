/**
 *给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。
 *
 * 网格中的格子 水平和垂直 方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 *
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 *
 *
 */
public class 岛屿的周长 {


    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    //广度搜索
    public int islandPerimeter(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == 1) {
                    int cnt = 0;
                    for (int k = 0; k < 4; ++k) {
                        int tx = i + dx[k];
                        int ty = j + dy[k];
                        if (tx < 0 || tx >= n || ty < 0 || ty >= m || grid[tx][ty] == 0) {
                            cnt += 1;
                        }
                    }
                    ans += cnt;
                }
            }
        }
        return ans;
    }

    //const islandPerimeter = (grid) => {
    //  let land = 0; // 土地个数
    //  let border = 0; // 接壤边界的条数
    //
    //  for (let i = 0; i < grid.length; i++) {
    //    for (let j = 0; j < grid[0].length; j++) {
    //      if (grid[i][j] == 1) {
    //        land++;
    //        if (i < grid.length - 1 && grid[i + 1][j] == 1) {
    //          border++;
    //        }
    //        if (j < grid[0].length - 1 && grid[i][j + 1] == 1) {
    //          border++;
    //        }
    //      }
    //    }
    //  }
    //  return 4 * land - 2 * border;
    //};
}
