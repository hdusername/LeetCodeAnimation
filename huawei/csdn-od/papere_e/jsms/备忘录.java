import java.util.*;
/**
 * 用java设计一个备忘录系统，要求有以下功能:
 *
 * addEvent(int startDate, sting content,int num, int perod)-自日期 startDate 开始 (startDate 包含在内)，添加 num 个以 period 为间隔周期待办事项 content,
 * 例如若命令返回本次操作共计新增待办事项的数量。若部分日期上已存在该事项，无论该事项是否设置为已完成，这些日期不需添加该事项。
 *
 * finishEvent(int date,String content)-将日期 date 上的 content 事项设置为已完成。
 * 若该日期上不存在此事项，或该日期上此事项已完成，返回false;
 * 若设置操作完成，则返回 true.
 *
 * removeEvent(int date, String content)-移除日期 date 上的 content 事项.c
 * 若该日期上不存在此事项，返回 false; 若移除操作完成，则返回 true。
 *
 * queryTodo(int statDate,int endDate)-查询从 startDate到 endDate(起止日期均包含在内)这段日期内所有的未完成的 content事项，并将查询到的事项以字符串形式按日期;T
 *
 * 示例1:
 * 俞入:["MemoSystem","addEvent","queryTodo","finishEvent""removeEvent"][{],[2,eat a burger,2,3],[0,5],[2,eat a burger],[2,eat a burger]]输出:[null,2,["eat a burger","eat a burger"],true,true]
 * 解:
 * MemoSystemms=MemoSystem();
 * ms.addEvent(2,"eat a burger”,2.3);//自日期2开始，添加2个间隔周期为3的待办事项"eataburger"，即在日期2、5 添加该待办事项，本次操作共新添加了2个待办事项，返回ms.queryTodo(0,5); //查询自日期 0至日期5 的所有未完成的事项，返回数组['eat a burger","eat a burger"]
 * ms.finishEvent(2,"eat aburger”);//将系统中日期为2的待办事项"eataburger”设置为已完成，返回 truems.removeEvent(2,"eat aburger”);//移除系统中日期为2的事项"eat a burger"，返回 true注:输出中的 null表示此对应函数无输出(其中:C的构造的数有返回值，但是也是无需输出)请设计一个备忘最系统，要求有以下功能:addEvent(int startDate, string content, int num, int penod)-自日期 startDate 开始 (stanDate 包含在内)，添加num 个以 period 为间隔周期待办事项 content,例如若命令返回本次操作共计新增待办事项的数量。若部分日期上已存在该事项，无论该事项是否设置为已完成，这些日期不需添加该事项。finishEvent(int date,String content)-将日期 date 上的 content 事项设置为已完成。若该日期上不存在此事项，或该日期上此事项已完成，返回false:若设置操作完成，则返回 true.
 * O
 * C
 * 0
 * removeEvent(int date, String content)-移除日期 date 上的 content 事顶,若该日期上不存在此事项，返回 false;若移除操作完成，则返回 true.quenyTodo(int startDate, int endDate)-查询从 starDate到endDate(起止日期均包含在内)这段日期内所有的未完成的content 事项，并将查询到的事项以字符串形式按日期,
 * C
 * 0
 * 4
 */
public class 备忘录 {



        private TreeSet<Integer> dates; // 维护有事件的日期，用于快速范围查询
        private Map<Integer, LinkedHashMap<String, Boolean>> eventMap; // 日期 -> (内容 -> 完成状态)

        public 备忘录() {
            dates = new TreeSet<>();
            eventMap = new HashMap<>();
        }

        /**
         * 添加周期性事件
         * @return 实际新增事件数量
         */
        public int addEvent(int startDate, String content, int num, int period) {
            int count = 0;
            for (int i = 0; i < num; i++) {
                int currentDate = startDate + i * period;
                LinkedHashMap<String, Boolean> contentMap = eventMap.get(currentDate);

                // 初始化日期对应的事件表
                if (contentMap == null) {
                    contentMap = new LinkedHashMap<>();
                    contentMap.put(content, false);
                    eventMap.put(currentDate, contentMap);
                    dates.add(currentDate);
                    count++;
                }
                // 仅当内容不存在时添加
                else if (!contentMap.containsKey(content)) {
                    contentMap.put(content, false);
                    count++;
                }
            }
            return count;
        }

        /**
         * 标记事件完成
         * @return 操作是否成功
         */
        public boolean finishEvent(int date, String content) {
            LinkedHashMap<String, Boolean> contentMap = eventMap.get(date);
            if (contentMap == null) return false;

            Boolean status = contentMap.get(content);
            if (status == null || status) return false; // 不存在或已完成

            contentMap.put(content, true);
            return true;
        }

        /**
         * 删除事件
         * @return 操作是否成功
         */
        public boolean removeEvent(int date, String content) {
            LinkedHashMap<String, Boolean> contentMap = eventMap.get(date);
            if (contentMap == null) return false;

            if (contentMap.containsKey(content)) {
                contentMap.remove(content);
                // 清理空日期
                if (contentMap.isEmpty()) {
                    eventMap.remove(date);
                    dates.remove(date);
                }
                return true;
            }
            return false;
        }

        /**
         * 查询待办事项
         * @return 未完成事项列表（按日期升序，同日期按添加顺序）
         */
        public List<String> queryTodo(int startDate, int endDate) {
            List<String> result = new ArrayList<>();
            // 获取范围内的有序日期
            SortedSet<Integer> subDates = dates.subSet(startDate, true, endDate, true);

            for (int date : subDates) {
                LinkedHashMap<String, Boolean> contentMap = eventMap.get(date);
                // 按添加顺序遍历并筛选未完成事项
                for (Map.Entry<String, Boolean> entry : contentMap.entrySet()) {
                    if (!entry.getValue()) {
                        result.add(entry.getKey());
                    }
                }
            }
            return result;
        }

}
