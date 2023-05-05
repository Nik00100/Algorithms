# TOP 56

+ [Summary ranges](#summary-ranges)
+ [Reverse Linked List](#reverse-linked-list)
+ [Move Zeroes](#move-zeroes)
+ [Valid Palindrome](#valid-palindrome)
+ [Valid Parentheses](#valid-parentheses)
+ [Two Sum](#two-sum)
+ [Is substring strStr()](#is-substring-strStr())


## Summary ranges
https://leetcode.com/problems/summary-ranges/
```
class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<>();
        for (int i=0; i< nums.length; i++) {
            int begin = nums[i];
            while (i+1<nums.length && nums[i]==nums[i+1]-1) {
                i++;
            }
            int end = nums[i];
            if (begin==end) {
                ans.add(""+begin);
            } else {
                ans.add(""+begin+"->"+end);
            }
        }
        return ans;
    }
}
```

## Reverse Linked List
https://leetcode.com/problems/reverse-linked-list
```
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head==null) return head;
        ListNode temp = head.next;
        head.next = null;
        while(temp!=null){
            ListNode t = temp.next;
            temp.next = head;
            head = temp;
            temp = t;
        }
        return head;
    }
}
```

## Move Zeroes
https://leetcode.com/problems/move-zeroes
```
class Solution {
    public void moveZeroes(int[] nums) {
        int i = 0;
        for (final int num : nums)
          if (num != 0)
            nums[i++] = num;
        while (i < nums.length)
          nums[i++] = 0;
    }
}
```

## Valid Palindrome
https://leetcode.com/problems/valid-palindrome
```
class Solution {
    public boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;

        while (l < r) {
          while (l < r && !Character.isLetterOrDigit(s.charAt(l)))
            ++l;
          while (l < r && !Character.isLetterOrDigit(s.charAt(r)))
            --r;
          if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r)))
            return false;
          ++l;
          --r;
        }
        return true;
    }
}
```

## Valid Parentheses
https://leetcode.com/problems/valid-parentheses
```
class Solution {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) return true;
    Stack<Character> stack = new Stack<>();
    for (Character ch : s.toCharArray()) {
        if (ch == '(') {
            stack.push(')');
        } else if (ch == '[') {
            stack.push(']');
        } else if (ch == '{') {
            stack.push('}');
        } else {
            if (stack.empty() || stack.pop() != ch) {
                return false;
            }
        }
    }
    return stack.isEmpty();
    }
}
```

## Two Sum
https://leetcode.com/problems/two-sum
```
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (numMap.containsKey(complement)) {
                return new int[] { numMap.get(complement), i };
            } else {
                numMap.put(nums[i], i);
            }
        }
        return new int[] {};
    }
}
```

## Is substring strStr()
https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string
```
class Solution {
    public int strStr(String haystack, String needle) {
        if (haystack.length()<needle.length()) return -1;
        int i=0;
        while(i<haystack.length()) {
            int j=0;
            while (j<needle.length()) {
                int index = j+i;
                if (index >= haystack.length()) break;
                if (haystack.charAt(index)!=needle.charAt(j)) break;
                if (j==needle.length()-1) return i;
                j++;
            }
            i++;
        }
        return -1;
    }
}
```

