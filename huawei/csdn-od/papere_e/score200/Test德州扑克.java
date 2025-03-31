import java.util.*;
import java.text.DecimalFormat;
import java.util.stream.Collectors;
public class Test德州扑克 {

        public static void main(String[] args) {
            // 输入处理
            Scanner in = new Scanner(System.in);
            List<List<String>> input_cards = new ArrayList<>();

            for (int j=0;j<5;j++){
                String line = in.nextLine();
                List<String> nums = new ArrayList<>();
                String num = "";
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == ' ') {
                        nums.add(num);
                        num = "";
                    } else {
                        num += line.charAt(i);
                    }
                }
                nums.add(num);
                input_cards.add(nums);
            }

            Map<String, Integer> cards = new HashMap<>();
            cards.put("2", 2);
            cards.put("3", 3);
            cards.put("4", 4);
            cards.put("5", 5);
            cards.put("6", 6);
            cards.put("7", 7);
            cards.put("8", 8);
            cards.put("9", 9);
            cards.put("10", 10);
            cards.put("J", 11);
            cards.put("Q", 12);
            cards.put("K", 13);
            cards.put("A", 14);

            List<String> nums = new ArrayList<>();
            List<String> colors = new ArrayList<>();

            for (List<String> card : input_cards) {
                nums.add(card.get(0));
                colors.add(card.get(1));
            }

            Collections.sort(nums, (a, b) -> cards.get(a) - cards.get(b));

            if (check_shunzi(cards, nums) && check_tonghua(colors)) {
                System.out.println(1);
            } else if (check_sitiao(nums)) {
                System.out.println(2);
            } else if (check_hulu(nums)) {
                System.out.println(3);
            } else if (check_tonghua(colors)) {
                System.out.println(4);
            } else if (check_shunzi(cards, nums)) {
                System.out.println(5);
            } else if (check_sanzhang(nums)) {
                System.out.println(6);
            } else {
                System.out.println(0);
            }
            return;

        }

        public static boolean check_shunzi(Map<String, Integer> cards, List<String> nums) {
            if (nums.get(0).equals("2") && nums.get(1).equals("3") && nums.get(2).equals("4")
                    && nums.get(3).equals("5") && nums.get(4).equals("A")) {
                return true;
            }

            for (int i = 1; i < nums.size(); i++) {
                int num1 = cards.get(nums.get(i - 1));
                int num2 = cards.get(nums.get(i));

                if (num1 + 1 != num2) {
                    return false;
                }
            }
            return true;
        }

        public static boolean check_tonghua(List<String> colors) {
            return new HashSet<>(colors).size() == 1;
        }

        public static boolean check_sitiao(List<String> nums) {
            Map<String, Integer> count = new HashMap<>();
            for (String num : nums) {
                count.put(num, count.getOrDefault(num, 0) + 1);
            }
            if (count.size() == 2) {
                for (Map.Entry<String, Integer> entry : count.entrySet()) {
                    if (entry.getValue() == 4 || entry.getValue() == 1) {
                        return true;
                    }
                }
            }
            return false;
        }
        public static boolean check_hulu(List<String> nums) {
            Map<String, Integer> count = new HashMap<>();

            for (String num : nums) {
                count.put(num, count.getOrDefault(num, 0) + 1);
            }

            if (count.size() == 2) {
                for (Map.Entry<String, Integer> entry : count.entrySet()) {
                    if (entry.getValue() == 3 || entry.getValue() == 2) {
                        return true;
                    }
                }
            }
            return false;
        }


        public static boolean check_sanzhang(List<String> nums) {
            Map<String, Integer> count = new HashMap<>();
            for (String num : nums) {
                count.put(num, count.getOrDefault(num, 0) + 1);
            }

            if (count.size() == 3) {
                for (Map.Entry<String, Integer> entry : count.entrySet()) {
                    if (entry.getValue() == 3) {
                        return true;
                    }
                }
            }
            return false;
        }

}
