import java.util.*;

/**
 *
 */
public class 完美答案 {

//deepseek给的答案不对，不能用
//    static class Interval {
//        int start;
//        int end;
//        Interval(int s, int e) {
//            start = s;
//            end = e;
//        }
//    }
//
//
//        public static void main(String[] args) {
//            Scanner sc = new Scanner(System.in);
//            int questionCount = sc.nextInt();
//            int peopleCount = sc.nextInt();
//            List<Interval> intervals = new ArrayList<>();
//
//            for (int i = 0; i < peopleCount; i++) {
//                int s = sc.nextInt();
//                int e = sc.nextInt();
//                intervals.add(new Interval(s, e));
//            }
//
//            // 按end升序排序
//            Collections.sort(intervals, (a, b) -> a.end - b.end);
//
//            int[] maxCover = new int[questionCount + 1];
//            Arrays.fill(maxCover, -1);
//            maxCover[0] = 0; // 初始状态覆盖0的位置（题目编号从1开始）
//
//            for (Interval interval : intervals) {
//                int s = interval.start;
//                int e = interval.end;
//
//                // 处理区间越界或无效的情况
//                if (s > questionCount) continue;
//                s = Math.max(s, 1); // 题目编号最小为1
//
//                int startPrev = s - 1;
//                if (startPrev < 0) startPrev = 0;
//                if (maxCover[startPrev] == -1) continue; // 无法覆盖到s-1，跳过
//
//                int currentMax = maxCover[startPrev] + 1;
//                int endLimit = Math.min(e, questionCount);
//
//                // 从endLimit到s，反向更新maxCover
//                for (int i = endLimit; i >= s; i--) {
//                    if (maxCover[i] < currentMax) {
//                        maxCover[i] = currentMax;
//                    } else {
//                        break; // 后续位置已无法更优，提前退出
//                    }
//                }
//            }
//
//            System.out.println(maxCover[questionCount] == -1 ? -1 : maxCover[questionCount]);
//        }


//chatgpt答案
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            // 读取目标覆盖边界 end
            int end = sc.nextInt();
            // 读取区间数量 count
            int count = sc.nextInt();

            // 读取所有区间 [start, right]
            List<int[]> intervals = new ArrayList<>(count);
            for (int i = 0; i < count; i++) {
                int start = sc.nextInt();
                int right = sc.nextInt();
                intervals.add(new int[]{start, right});
            }

            // 第一步：先对区间按起点排序，若起点相同则按终点排序
            intervals.sort(new Comparator<int[]>() {
                @Override
                public int compare(int[] a, int[] b) {
                    if (a[0] == b[0]) {
                        return Integer.compare(a[1], b[1]);
                    }
                    return Integer.compare(a[0], b[0]);
                }
            });

            // minCount 记录使用了多少区间来覆盖 [1, end]
            int minCount = 0;

            // currentCoverage 表示当前已覆盖到的右边界，初始为 1
            int currentCoverage = 1;

            // i 为主循环指针，遍历 intervals
            int i = 0;

            // covered 表示是否已成功覆盖到 end
            boolean covered = false;

            while (i < count) {
                int j = i;
                // maxRight 用来记录在当前起点条件下可达到的最远右边界
                int maxRight = 0;

                // 在所有区间中，找出“起点 <= currentCoverage”的那些区间，
                // 取它们的最大终点来尽可能延伸覆盖范围
                while (j < count && intervals.get(j)[0] <= currentCoverage) {
                    maxRight = Math.max(maxRight, intervals.get(j)[1]);
                    j++;
                }

                // 如果无法前进，说明没有任何区间能扩展当前覆盖范围，直接跳出
                if (i == j) {
                    break;
                }

                // 使用了一个新的区间组合来延伸覆盖
                minCount++;

                // 如果此时已经能覆盖到 end，说明任务完成
                if (maxRight >= end) {
                    covered = true;
                    break;
                }

                // 否则更新 currentCoverage，继续尝试覆盖后面的区域
                currentCoverage = maxRight;
                // 因为 j 已经移动到下一个区间，下一次循环从 j-1 开始
                i = j - 1;
            }

            // 如果 covered 为 true，输出 minCount，否则输出 -1
            if (covered) {
                System.out.println(minCount);
            } else {
                System.out.println(-1);
            }

            sc.close();

//            Scanner sc = new Scanner(System.in);
//
//            // 读取 end、count
//            int end = sc.nextInt();
//            int count = sc.nextInt();
//
//            // 读取区间
//            List<int[]> ranges = new ArrayList<>(count);
//            for (int i = 0; i < count; i++) {
//                int start = sc.nextInt();
//                int right = sc.nextInt();
//                ranges.add(new int[]{start, right});
//            }
//
//            // 第一步：先对所有区间排序 (先按起点升序, 若相同再按终点升序)
//            ranges.sort((a, b) -> {
//                if (a[0] == b[0]) {
//                    return a[1] - b[1];
//                }
//                return a[0] - b[0];
//            });
//
//            int minCount = 0;
//            int left = 1;   // 题中示例逻辑，起始边界为 1
//            int i = 0;
//            int flag = 0;
//
//            while (i < count) {
//                int j = i;
//                int right = 0;
//
//                // 在所有 [start, end) 中，若 start <= left，则尝试合并并找最大终点
//                while (j < count && ranges.get(j)[0] <= left) {
//                    right = Math.max(right, ranges.get(j)[1]);
//                    j++;
//                }
//
//                // 如果没有找到能延伸的区间，说明无法继续覆盖
//                if (i == j) {
//                    break;
//                }
//
//                minCount++;
//
//                // 如果此时已经可以覆盖到题目给出的 end，直接退出
//                if (right >= end) {
//                    flag = 1;
//                    break;
//                }
//
//                // 更新下一次要覆盖的 left，并移动 i 指针
//                left = right;
//                i = j - 1;  // j-1, 因为 while 结束后 j 已经多加了 1
//            }
//
//            if (flag == 1) {
//                System.out.println(minCount);
//            } else {
//                System.out.println(-1);
//            }
        }


}
