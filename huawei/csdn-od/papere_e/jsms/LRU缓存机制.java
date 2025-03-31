import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 */
public class LRU缓存机制 {

    public static class LRUCache extends LinkedHashMap<Integer, Integer> {
        private int capacity;

        public LRUCache(int capacity) {
            //这里的第三个参数传true，迭代顺序将根据访问顺序进行调整，即最近访问的项会被移动到链表末尾，从而实现 LRU 缓存等功能
            //第三个参数如果设为 false，则 LinkedHashMap 保持插入顺序（默认）
            super(capacity, 0.75F, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }

}
