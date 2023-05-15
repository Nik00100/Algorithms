# TOP 56

1. [Line Reflection](#line-reflection)
2. [Summary ranges (RLE)](#summary-ranges)
3. [Zigzag Iterator](#zigzag-iterator)
4. [String Compression](#string-compression)
5. [Reverse Linked List](#reverse-linked-list)
6. [Generate Parentheses](#generate-parentheses)
7. [LRU Cache](#lru-cache)
8. [Move Zeroes To End](#move-zeroes)
9. [Valid Palindrome](#valid-palindrome)
10. [Group Anagrams](#group-anagrams)
11. [Max Consecutive Ones II](#max-consecutive-ones-ii)
12. [One Edit Distance](#one-edit-distance)
13. [Longest Subarray of 1's After Deleting One Element](#longest-subarray-of-1s-after-deleting-one-element)
14. [Valid Parentheses](#valid-parentheses)
15. [Subarray Sum Equals K](#subarray-sum-equals-k)
16. [Insert Delete GetRandom O(1)](#insert-delete-getrandom-o1)
17. [Two Sum](#two-sum)
18. [Merge k Sorted Lists](#merge-k-sorted-lists)
19. [Permutation in String](#permutation-in-string)
20. [Meeting Rooms II](#meeting-rooms-ii)
21. [Merge Intervals](#merge-intervals)
22. [Number of Recent Calls](#number-of-recent-calls)
23. [Validate Binary Search Tree](#validate-binary-search-tree)
24. [Implement Queue using Stacks](#implement-queue-using-stacks)
25. [Spiral Matrix II](#spiral-matrix-ii)

+ [Merge Two Sorted Lists](#merge-two-sorted-lists)
+ [Merge Sorted Array](#merge-sorted-array)
+ [Symmetric Tree](#symmetric-tree)
+ [Missing Number](#missing-number)
+ [Is Subsequence](#is-subsequence)
+ [Squares of sorted array](#squares-of-sorted-array)
+ [Intersection of Two Arrays II](#intersection-of-two-arrays-ii)
+ [Is substring strStr()](#is-substring-strstr)
+ [Palindrome Linked List](#palindrome-linked-list)

## Line Reflection
Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.
https://leetcode.ca/all/356.html
```
class Solution {
    public boolean isReflected(int[][] points) {
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        Set<String> seen = new HashSet<>();

        for (int[] p : points) {
            final int x = p[0];
            final int y = p[1];
            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
            seen.add(x + "," + y);
        }

        final int sum = minX + maxX;
        // (leftX + rightX) / 2 = (minX + maxX) / 2
        //  leftX = minX + maxX - rightX
        // RightX = minX + maxX - leftX

        for (int[] p : points)
            if (!seen.contains(sum - p[0] + "," + p[1]))
                return false;

        return true;
    }
}
```

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

## Zigzag Iterator
Given two 1d vectors, implement an iterator to return their elements alternately.
https://leetcode.ca/all/281.html
```
public class ZigzagIterator {
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        if (!v1.isEmpty())
            q.offer(v1.iterator());
        if (!v2.isEmpty())
            q.offer(v2.iterator());
    }

    public int next() {
        final Iterator it = q.poll();
        final int next = (int) it.next();
        if (it.hasNext())
            q.offer(it);
        return next;
    }

    public boolean hasNext() {
        return !q.isEmpty();
    }

    private Queue<Iterator> q = new ArrayDeque<>();
}

```

## String Compression
https://leetcode.com/problems/string-compression
```
class Solution {
    public int compress(char[] chars) {
        int ans = 0;
        int i = 0;
        while (i < chars.length) {
            int start = i;
            char current = chars[i];
            while (i < chars.length && chars[i] == current) {
                i++;
            }
            chars[ans++] = current;
            int end = i;
            if (start + 1 != end) {
               for(char c : Integer.toString(end - start).toCharArray()) 
                    chars[ans++] = c;
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

## Generate Parentheses
https://leetcode.com/problems/generate-parentheses
```
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        recurse(res, 0, 0, "", n);
        return res;
    }
    
    public void recurse(List<String> res, int left, int right, String s, int n) {
        if (s.length() == n * 2) {
            res.add(s);
            return;
        }
        
        if (left < n) {
            recurse(res, left + 1, right, s + "(", n);
        }
        
        if (right < left) {
            recurse(res, left, right + 1, s + ")", n);
        }
    }
	/* For n = 2								   	
                                    (0, 0, '')
								 	    |	
									(1, 0, '(')  
								   /           \
							(2, 0, '((')      (1, 1, '()')
							   /                 \
						(2, 1, '(()')           (2, 1, '()(')
						   /                       \
					(2, 2, '(())')                (2, 2, '()()')
						      |	                             |
					res.append('(())')             res.append('()()')
   */

}
```

## LRU Cache
https://leetcode.com/problems/lru-cache
```
class Node {
    int key;
    int value;
    Node prev;
    Node next;
 
    public Node(int key, int value){
        this.key=key;
        this.value=value;
    }
}

class LRUCache {

    Node head;
    Node tail;
    HashMap<Integer, Node> map = null;
    int cap = 0;
 
    public LRUCache(int capacity) {
        this.cap = capacity;
        this.map = new HashMap<>();
    }
 
    public int get(int key) {
        if(map.get(key)==null){
            return -1;
        }
 
        //move to tail
        Node t = map.get(key);
 
        removeNode(t);
        offerNode(t);
 
        return t.value;
    }
 
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node t = map.get(key);
            t.value = value;
 
            //move to tail
            removeNode(t);
            offerNode(t);
        }else{
            if(map.size()>=cap){
                //delete head
                map.remove(head.key);
                removeNode(head);
            }
 
            //add to tail
            Node node = new Node(key, value);
            offerNode(node);
            map.put(key, node);
        }
    }
 
    private void removeNode(Node n){
        if(n.prev!=null){
            n.prev.next = n.next;
        }else{
            head = n.next;
        }
 
        if(n.next!=null){
            n.next.prev = n.prev;
        }else{
            tail = n.prev;
        }
    }
 
    private void offerNode(Node n){
        if(tail!=null){
            tail.next = n;
        }
 
        n.prev = tail;
        n.next = null;
        tail = n;
 
        if(head == null){
            head = tail;   
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
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
## Group Anagrams
https://leetcode.com/problems/group-anagrams
```
import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> keyToAnagrams = new HashMap<>();

        for (final String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            keyToAnagrams.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(keyToAnagrams.values());
    }
}
```

## Max Consecutive Ones II
Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.
https://leetcode.ca/all/487.html
```
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        final int maxZeros = 1;
        int ans = 0;
        // Store indices of zero
        Queue<Integer> q = new ArrayDeque<>();

        for (int l = 0, r = 0; r < nums.length; ++r) {
            if (nums[r] == 0)
                q.offer(r);
            if (q.size() > maxZeros)
                l = q.poll() + 1;
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}
```

## One Edit Distance
Given two strings s and t, determine if they are both one edit distance apart.
There are 3 possiblities to satisify one edit distance apart:
1. Insert a character into s to get t
2. Delete a character from s to get t
3. Replace a character of s to get t

```
public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        if (s.length() < t.length()) {
            String temp = s;
            s = t;
            t = temp;
        }
        int m = s.length(), n = t.length(), diff = m - n;
        if (diff >= 2) return false;
        else if (diff == 1) {
            for (int i = 0; i < n; ++i) {
                if (s.charAt(i) != t.charAt(i)) {
                    return s.substring(i + 1).equals(t.substring(i));
                }
            }
            return true;
        } else {
            int cnt = 0;
            for (int i = 0; i < m; ++i) {
                if (s.charAt(i) != t.charAt(i)) ++cnt;
            }
            return cnt == 1;
        }
    }
}
```

## Longest Subarray of 1's After Deleting One Element
https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/
```
public int longestSubarray(int[] nums) {
	int longest = 0;
	int prev = 0, curr = 0;
	for (int bit : nums) {
		if (bit == 0) {
			prev = curr;
			curr = 0;
		} else {
			curr++;
            longest = Math.max(longest, prev + curr);
		}
	}
	return longest == nums.length ? longest - 1 : longest;
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

## Subarray Sum Equals K
https://leetcode.com/problems/subarray-sum-equals-k
```
class Solution {    
    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap <Integer, Integer> map = new HashMap <>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
```

## Insert Delete GetRandom O(1)
https://leetcode.com/problems/insert-delete-getrandom-o1
```
/**
 * Using ArrayList & HashMap
 *
 * Time Complexity: All function have average O(1)
 *
 * Space Complexity: O(N)
 *
 * N = Number of values currently stored in the data structure.
 */
class RandomizedSet {

    List<Integer> nums;
    Map<Integer, Integer> idxMap;
    Random random;

    public RandomizedSet() {
        nums = new ArrayList<>();
        idxMap = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if (idxMap.containsKey(val)) {
            return false;
        }

        idxMap.put(val, nums.size());
        nums.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!idxMap.containsKey(val)) {
            return false;
        }

        int idx = idxMap.get(val);
        int lastIdx = nums.size() - 1;
        if (idx != lastIdx) {
            int lastVal = nums.get(lastIdx);
            nums.set(idx, lastVal);
            idxMap.put(lastVal, idx);
        }
        nums.remove(lastIdx);
        idxMap.remove(val);
        return true;
    }

    public int getRandom() {
        return nums.get(random.nextInt(nums.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
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

## Merge k Sorted Lists
https://leetcode.com/problems/merge-k-sorted-lists
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
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        Queue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        for (final ListNode list : lists)
            if (list != null)
                minHeap.offer(list);

        while (!minHeap.isEmpty()) {
            ListNode minNode = minHeap.poll();
            if (minNode.next != null)
                minHeap.offer(minNode.next);
            curr.next = minNode;
            curr = curr.next;
        }

        return dummy.next;
    }
}
```

## Permutation in String
https://leetcode.com/problems/permutation-in-string
```
class Solution {
     public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;
        int[] s1map = new int[26];
        for (int i = 0; i < s1.length(); i++)
            s1map[s1.charAt(i) - 'a']++;
        for (int i = 0; i <= s2.length() - s1.length(); i++) {
            int[] s2map = new int[26];
            for (int j = 0; j < s1.length(); j++) {
                s2map[s2.charAt(i + j) - 'a']++;
            }
            if (matches(s1map, s2map))
                return true;
        }
        return false;
    }
    
    public boolean matches(int[] s1map, int[] s2map) {
        for (int i = 0; i < 26; i++) {
            if (s1map[i] != s2map[i])
                return false;
        }
        return true;
    }
}
```

## Meeting Rooms II
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
https://leetcode.ca/all/253.html
```
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));

        // Store end times of each room.
        Queue<Integer> minHeap = new PriorityQueue<>();

        for (int[] interval : intervals) {
            // No overlap, we can reuse the same room.
            if (!minHeap.isEmpty() && interval[0] >= minHeap.peek())
                minHeap.poll();
            minHeap.offer(interval[1]);
        }

        return minHeap.size();
    }
}
```

## Merge Intervals
https://leetcode.com/problems/merge-intervals
```
class Solution {
    public int[][] merge(int[][] intervals) {
        List<int[]> ans = new ArrayList<>();

        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));

        for (int[] interval : intervals)
          if (ans.isEmpty() || ans.get(ans.size() - 1)[1] < interval[0])
            ans.add(interval);
          else
            ans.get(ans.size() - 1)[1] = Math.max(ans.get(ans.size() - 1)[1], interval[1]);

        return ans.toArray(new int[ans.size()][]);
    }
}
```

## Number of Recent Calls
https://leetcode.com/problems/number-of-recent-calls
```
class RecentCounter {
    Queue<Integer> q;

    public RecentCounter() {
        q = new LinkedList<>();
    }

    public int ping(int t) {
        q.add(t);
        while (q.peek() < t - 3000) {
            q.poll();
        }
        return q.size();
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */
```

## Validate Binary Search Tree
https://leetcode.com/problems/validate-binary-search-tree
```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);    
    }
 
    public boolean isValidBST(TreeNode p, double min, double max){
        if(p==null) 
            return true;

        if(p.val <= min || p.val >= max)
            return false;

        return isValidBST(p.left, min, p.val) && isValidBST(p.right, p.val, max);
    }
}
```

## Implement Queue using Stacks
https://leetcode.com/problems/implement-queue-using-stacks
```
class MyQueue {

    private Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();

    /**
     * Для операции push () очереди можно напрямую выполнить операцию push () стека на stack1
     */
    public void push(int x) {
        stack1.push(x);
    }

    /**
     * Операция pop () очереди фактически предназначена для получения нижнего элемента в стеке 1. 
     * Как это получить? Сначала поместите указанные выше элементы один за другим в другой стек stack2.
     * Когда stack1 пуст, верхний элемент stack2 - это элемент, который нужно получить, 
     * который извлекается операцией pop () стека.
     * Перед возвратом элемента верните элемент из stack2 в stack1, а затем верните элемент
     */
    public int pop() {
        // если stack2 пуст
        if (stack2.isEmpty()) {
            // и stack1 не пуст
            while (!stack1.isEmpty()) {
                // Элементы в stack1 непрерывно выталкиваются и помещаются в stack2 для временного хранения
                stack2.push(stack1.pop());
            }
        }
        // Верхний элемент стека 2 на самом деле является нижним элементом стека 1, 
        // а верхний элемент очереди pop, который мы хотим вытолкнуть, на самом деле является нижним элементом стека pop
        return stack2.pop();
    }

    public int peek() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }

    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
```

## Spiral Matrix II
https://leetcode.com/problems/spiral-matrix-ii
```
class Solution {
  public int[][] generateMatrix(int n) {
    int[][] ans = new int[n][n];
    int count = 1;

    for (int min = 0; min < n / 2; ++min) {
      final int max = n - min - 1;
      for (int i = min; i < max; ++i)
        ans[min][i] = count++;
      for (int i = min; i < max; ++i)
        ans[i][max] = count++;
      for (int i = max; i > min; --i)
        ans[max][i] = count++;
      for (int i = max; i > min; --i)
        ans[i][min] = count++;
    }

    if (n % 2 == 1)
      ans[n / 2][n / 2] = count;

    return ans;
  }
}
```

## Merge Two Sorted Lists
https://leetcode.com/problems/merge-two-sorted-lists
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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tail.next = list1;
                list1 = list1.next;
                tail = tail.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
                tail = tail.next;
            }
        }
        tail.next = (list1 != null) ? list1 : list2;
        return dummyHead.next;
    }
}
```

## Merge Sorted Array
https://leetcode.com/problems/merge-sorted-array
```
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //variables to work as pointers
        int i=m-1; // will point at m-1 index of nums1 array
        int j=n-1; // will point at n-1 index of nums2 array
        int k=nums1.length-1; //will point at the last position of the nums1 array

        // Now traversing the nums2 array
        while (j >= 0) {
            // If element at i index of nums1 > element at j index of nums2
            // then it is largest among two arrays and will be stored at k position of nums1
            // using i >= 0 to make sure we have elements to compare in nums1 array
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                k--; 
                i--; //updating pointer for further comparisons
            } else{
                // element at j index of nums2 array is greater than the element at i index of nums1 array 
                // or there is no element left to compare with the nums1 array 
                // and we just have to push the elements of nums2 array in the nums1 array.
                nums1[k] = nums2[j];
                k--; 
                j--; //updating pointer for further comparisons
            }
        }
    }
}
```

## Symmetric Tree
https://leetcode.com/problems/symmetric-tree
```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root, root);
      }

      private boolean isSymmetric(TreeNode p, TreeNode q) {
        if (p == null || q == null)
          return p == q;

        return p.val == q.val && isSymmetric(p.left, q.right) && isSymmetric(p.right, q.left);
      }
}
```

## Missing Number
https://leetcode.com/problems/missing-number
```
class Solution {
    public int missingNumber(int[] nums) {
            int sum = 0;
	        int length = nums.length;
	        for(int i = 0; i < length; i++){
		        sum += nums[i];
            }
            return length * (length + 1) / 2 - sum;
    }
}
```

## Is Subsequence
https://leetcode.com/problems/is-subsequence
```
class Solution {
    public boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        while(i < s.length() && j < t.length()){
            if(s.charAt(i) == t.charAt(j)) i++;
            j++;
        }
        return i == s.length();
    }
}
```

## Squares of sorted array
https://leetcode.com/problems/squares-of-a-sorted-array
```
class Solution {
    public int[] sortedSquares(int[] nums) {
        int left = 0;
        int right = nums.length -1;
        int index = nums.length- 1;
        int result[] = new int [nums.length];
        while(left<=right)
        {
            if(Math.abs(nums[left])>Math.abs(nums[right]))
            {
                result[index] = nums[left] * nums[left];
                left++;
            }
            else
            {
                result[index] = nums[right] * nums[right];
                right--;
            }
            index--;
        }
        return result;
    }
}
```

## Intersection of Two Arrays II
https://leetcode.com/problems/intersection-of-two-arrays-ii
```
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int top = 0;
        int bottom = 0;
        List<Integer> h = new ArrayList<>();

        while (true) {
            if (top >= nums1.length || bottom >= nums2.length) {
                break;
            }
            if (nums1[top] == nums2[bottom]) {
                h.add(nums1[top]);
                top++;
                bottom++;
            } else if (nums1[top] < nums2[bottom]) {
                top++;
            } else if (nums1[top] > nums2[bottom]) {
                bottom++;
            }
        }

        int[] g = new int[h.size()];
        for (int i = 0; i < h.size(); i++) {
            g[i] = h.get(i);
        }
        return g;
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

## Palindrome Linked List
https://leetcode.com/problems/palindrome-linked-list
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
    public boolean isPalindrome(ListNode head) {
        List<Integer> vals = new ArrayList<>();

        // Convert LinkedList into ArrayList.
        ListNode currentNode = head;
        while (currentNode != null) {
            vals.add(currentNode.val);
            currentNode = currentNode.next;
        }

        // Use two-pointer technique to check for palindrome.
        int front = 0;
        int back = vals.size() - 1;
        while (front < back) {
            // Note that we must use ! .equals instead of !=
            // because we are comparing Integer, not int.
            if (!vals.get(front).equals(vals.get(back))) {
                return false;
            }
            front++;
            back--;
        }
        return true;
    }
}
```