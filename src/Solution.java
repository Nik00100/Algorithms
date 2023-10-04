import java.util.*;

class Solution {


    static List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        int i = 0;
        while (i < nums.length) {
            int begin = i;
            while (i + 1 < nums.length && nums[i] == nums[i + 1] - 1) {
                i++;
            }
            int end = i;
            if (end == begin) {
                result.add(nums[begin] + "");
            } else {
                result.add(nums[begin] + "->" + nums[end]);
            }
            i++;
        }
        return result;
    }

    public int compress(char[] chars) {
        int ans = 0;
        int i = 0;
        while (i < chars.length) {
            int begin = i;
            char current = chars[i];
            while (i < chars.length && chars[i] == current) {
                i++;
            }
            int end = i;
            chars[ans++] = current;
            if (end != begin + 1) {
                for (char x : Integer.toString(end - begin).toCharArray()) {
                    chars[ans++] = x;
                }
            }
        }
        return ans;
    }

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        recurse(res, 0, 0, "", n);
        return res;
    }

    private void recurse (List<String> list, int left, int right, String s, int n) {
        if (s.length() == 2 * n) {
            list.add(s);
            return;
        }

        if (left < n) {
            recurse (list, left + 1, right, s + "(", n);
        }

        if (right < left) {
            recurse (list, left, right + 1, s + ")", n);
        }
    }

    public boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;

        while (l < r) {
            while (l < r && !Character.isLetterOrDigit(s.charAt(l))) {
                l++;
            }

            while (l < r && !Character.isLetterOrDigit(s.charAt(r))) {
                r--;
            }

            if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r)))
                return false;

            l++;
            r--;
        }
        return true;
    }
    class ZigzagIterator {

        private Queue<Iterator<Integer>> q;

        public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
            this.q = new LinkedList<>();

            Iterator<Integer> it1 = v1.iterator();
            Iterator<Integer> it2 = v2.iterator();

            if (it1.hasNext()) {
                q.offer(it1);
            }

            if (it2.hasNext()) {
                q.offer(it2);
            }
        }

        public int next() {
            Iterator<Integer> it = q.poll();
            int next = it.next();
            if (it.hasNext()) {
                q.offer(it);
            }
            return next;
        }

        public boolean hasNext() {
            return !q.isEmpty();
        }

    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 4, 5, 7};
        System.out.println(summaryRanges(nums));
    }
}