# TOP 56

1. [Line Reflection {356}](#line-reflection-356)
2. [Summary ranges (RLE) {228}](#summary-ranges-228)
3. [Zigzag Iterator {281}](#zigzag-iterator-281)
4. [String Compression {443}](#string-compression-443)
5. [Reverse Linked List {206}](#reverse-linked-list-206)
6. [Generate Parentheses {22}](#generate-parentheses-22)
7. [LRU Cache {146}](#lru-cache-146)
8. [Move Zeroes To End {283}](#move-zeroes-283)
9. [Valid Palindrome {125}](#valid-palindrome-125)
10. [Group Anagrams {49}](#group-anagrams-49)
11. [Max Consecutive Ones II {487}](#max-consecutive-ones-ii-487)
12. [One Edit Distance {161}](#one-edit-distance-161)
13. [Longest Subarray of 1's After Deleting One Element {1493}](#longest-subarray-of-1s-after-deleting-one-element-1493)
14. [Valid Parentheses {20}](#valid-parentheses-20)
15. [Subarray Sum Equals K {560}](#subarray-sum-equals-k-560)
16. [Insert Delete GetRandom O(1) {380}](#insert-delete-getrandom-o1-380)
17. [Two Sum {1}](#two-sum-1)
18. [Merge k Sorted Lists {23}](#merge-k-sorted-lists-23)
19. [Permutation in String {567}](#permutation-in-string-567)
20. [Meeting Rooms II {253}](#meeting-rooms-ii-253)
21. [Merge Intervals {56}](#merge-intervals-56)
22. [Number of Recent Calls {933}](#number-of-recent-calls-933)
23. [Validate Binary Search Tree {98}](#validate-binary-search-tree-98)
24. [Implement Queue using Stacks {232}](#implement-queue-using-stacks-232)
25. [Spiral Matrix II {59}](#spiral-matrix-ii-59)
26. [Max Consecutive Ones III {1004}](#max-consecutive-ones-iii-1004)
27. [Trapping Rain Water {42}](#trapping-rain-water-42)
28. [Longest Substring Without Repeating Characters {3}](#longest-substring-without-repeating-characters-3)
29. [Add Two Numbers {2}](#add-two-numbers-2)
30. [Merge Two Sorted Lists {21}](#merge-two-sorted-lists-21)
31. [Merge Sorted Array {88}](#merge-sorted-array-88)
32. [Symmetric Tree {101}](#symmetric-tree-101)
33. [Missing Number {268}](#missing-number-268)
34. [Design Hit Counter {362}](#design-hit-counter-362)
35. [Rotate Image {48}](#rotate-image-48)
36. [Longest Palindromic Substring {5}](#longest-palindromic-substring-5)
37. [Reconstruct Itinerary {332}](#reconstruct-itinerary-332)
38. [Find K Closest Elements {658}](#find-k-closest-elements-658)
39. [Number of Islands {200}](#number-of-islands-200)
40. [Is Subsequence {392}](#is-subsequence-392)
+ [Squares of sorted array](#squares-of-sorted-array)
+ [Intersection of Two Arrays II](#intersection-of-two-arrays-ii)
+ [Is substring strStr()](#is-substring-strstr)
+ [Palindrome Linked List](#palindrome-linked-list)

## Line Reflection {356}
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

## Summary ranges {228}
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

## Zigzag Iterator {281}
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

## String Compression {443}
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

## Reverse Linked List {206}
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

## Generate Parentheses {22}
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

## LRU Cache {146}
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

## Move Zeroes {283}
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

## Valid Palindrome {125}
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
## Group Anagrams {49}
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

## Max Consecutive Ones II {487}
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

## One Edit Distance {161}
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

## Longest Subarray of 1's After Deleting One Element {1493}
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

## Valid Parentheses {20}
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

## Subarray Sum Equals K {560}
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

## Insert Delete GetRandom O(1) {380}
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

## Two Sum {1}
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

## Merge k Sorted Lists {23}
https://leetcode.com/problems/merge-k-sorted-lists

`k` sorted linked lists merges into one sorted linked list. It does this by using a min heap (implemented as a priority queue) to keep track of the smallest node from each list.

Dummy node `dummy` and pointer `curr` keeps track of the current position in the merged list. Also create `minHeap` to store the smallest node from each list.

Iterate over each list in `lists`. For each non-null list add first node to `minHeap`.

Loop until `minHeap` is empty. In each iteration smallest node removed from `minHeap` and added it to merged list by updating the `next` pointer of `curr`. If removed node has next node, it adds to `minHeap`.

After all nodes have been processed and added to the merged list, it returns the `next` node of the dummy node as the head of the merged list.
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

## Permutation in String {567}
https://leetcode.com/problems/permutation-in-string

Arrays `data` and `test` of size 26 represent the frequency of characters in s1 and a sliding window of size s1.length() in s2, respectively.

Then populates `data` with frequency of characters in s1 and the first window of characters in s2.

Then iterates over each window of characters in s2. For each window, it checks if the frequency of characters in the window matches the frequency of characters in s1 by calling the helper method `equalsString`. If they match, it means that a permutation of s1 exists as a substring of s2, and the code returns `true`.

After checking each window, the code updates the frequency of characters in the next window by incrementing the count of the next character and decrementing the count of the first character in the current window.

If no permutation is found after checking all windows, the code returns `false`.
```
import java.util.*;

public class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        int[] data = new int[26];
        int[] test = new int[26];

        for (char c : s1.toCharArray()) data[c - 'a']++;

        for (int i = 0; i < s1.length(); i++)
            test[s2.charAt(i) - 'a']++;
        int n = s1.length();

        for (int i = 0; i < s2.length() - n; i++) {
            if (equalsString(test, data)) return true;
            test[s2.charAt(i + n) - 'a']++;
            test[s2.charAt(i) - 'a']--;
        }
        return equalsString(test, data);
    }

    private boolean equalsString(int[] s1, int[] s2) {
        for (int i = 0; i < 26; i++)
            if (s1[i] != s2[i]) return false;
        return true;
    }
}
```

## Meeting Rooms II {253}
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

## Merge Intervals {56}
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

## Number of Recent Calls {933}
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

## Validate Binary Search Tree {98}
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

## Implement Queue using Stacks {232}
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

## Spiral Matrix II {59}
https://leetcode.com/problems/spiral-matrix-ii

It loops until all elements in the matrix have been filled. In each iteration, it fills in the top row from left to right, the right column from top to bottom, the bottom row from right to left, and the left column from bottom to top. After filling in each row or column, it updates the corresponding starting or ending row or column variable to move inward.

After all elements have been filled, the code returns the generated matrix.
```
class Solution {
    public int[][] generateMatrix(int n) {
        
        // creating a matrix of n x n
        
        int[][] ans = new int[n][n];
        
        int count = 1;
        int total = n * n;
        
        // initialize variables for keeping track of starting and ending rows and columns
        int startingRow = 0;
        int endingRow = n - 1;
        int startingCol = 0;
        int endingCol = n - 1;
        
        while (count <= total) {
            
            // fill in the top row from left to right
            for (int i = startingCol; i <= endingCol; i++) {
                ans[startingRow][i] = count;
                count++;
            }
            startingRow++;
            
            // fill in the right column from top to bottom
            for (int i = startingRow; i <= endingRow; i++) {
                ans[i][endingCol] = count;
                count++;
            }
            endingCol--;
            
            // fill in the bottom row from right to left
            for (int i = endingCol; i >= startingCol; i--) {
                ans[endingRow][i] = count;
                count++;
            }
            endingRow--;
            
            // fill in the left column from bottom to top
            for (int i = endingRow; i >= startingRow; i--) {
                ans[i][startingCol] = count;
                count++;
            }
            startingCol++;
        }
        
        return ans;
    }
}
```

## Max Consecutive Ones III {1004}
https://leetcode.com/problems/max-consecutive-ones-iii

One-Pass Optimized Sliding Window

If A[start] ~ A[end] has zeros <= K, we continue to increment end.
(Reason: The window is still valid and window size can be increased.)

If A[start] ~ A[end] has zeros > K, we increment both start & end.
(Reason: The window contains extra zeros thus it is not valid any more, and we will increment both start & end to keep the window size same.)

Time Complexity: O(N); 
Space Complexity: O(1);
N = Length of the input array
```
class Solution {
    public int longestOnes(int[] nums, int k) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int start = 0;
        int end = 0;
        int zeros = 0;

        while (end < nums.length) {
            if (nums[end] == 0) {
                zeros++;
            }
            end++;
            if (zeros > k) {
                if (nums[start] == 0) {
                    zeros--;
                }
                start++;
            }
        }

        return end - start;
    }
}
```

## Trapping Rain Water {42}
https://leetcode.com/problems/trapping-rain-water

**Intuition**

We need to figure out the total water we can trap, A difference of heights, maybe a smaller and a greater one would do the trick but a smaller bar can be smaller than multiple bars or a single tall bar can be taller than multiple smaller bars. So we can calculate total water trapped by each bar by tracking the smaller bars in a stack which contains bars in monotonically decreasing order.

**Approach**

Pop the elements out of stack if current bar's height is more than the minimum height bar in stack and calculate the water stored in that area and add it to answer.

**Complexity**

Time complexity: O(n);
Space complexity: O(n)
```
class Solution {
    public int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for(int i = 0; i < height.length; i++) {
            while(!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int top = height[stack.peek()];
                stack.pop();
                // Last bar as base cannot store any water.
                if(stack.isEmpty()) break; 
                int width = i - stack.peek() - 1;
                int length = Math.min(height[i], height[stack.peek()]) - top;
                ans += length * width;
                
            }
            stack.push(i);
        }
        return ans;
    }
}
```

## Longest Substring Without Repeating Characters {3}
https://leetcode.com/problems/longest-substring-without-repeating-characters

**Approach**

- Use sliding window with hashset, use left and right pointers to move the window .
- If the set doesn't contains character then first add into the set and calculate the maxLength hand-in-hand...
- If character already present in the set that means you have to move your sliding window by 1 , before that you have to remove all the characters that are infront of the character that is present already in window before.
- Now you have to remove that character also and move the left pointer and also add the new character into the set.

**Complexity**

Time complexity: O(n);
Space complexity: O(k), where k is the number of distinctive characters prsent in the hashset.
```
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character>set = new HashSet<>();
        int maxLength = 0;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {           
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                maxLength=Math.max(maxLength,right-left+1);                
            } else {
                while(s.charAt(left) != s.charAt(right)) {
                    set.remove(s.charAt(left));
                    left++;
                }
                set.remove(s.charAt(left));
                left++;
                set.add(s.charAt(right));
            }            
        }
        return maxLength;
    }
}
```

## Add Two Numbers {2}
https://leetcode.com/problems/add-two-numbers
```
/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Creating an dummy list
        ListNode dummy = new ListNode(0);
        // Intialising an pointer
        ListNode curr = dummy;
        // Intialising our carry with 0 intiall
        int carry = 0;
        // While loop will run, until l1 OR l2 not reaches null OR if they both reaches null.
        // But our carry has some value in it.
        // We will add that as well into our list
        while (l1 != null || l2 != null || carry == 1) {
            // Intialising our sum
            int sum = 0;
            // Adding l1 to our sum & moving l1
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            // Adding l2 to our sum & moving l2
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            // if we have carry then add it into our sum
            sum += carry;
            // if we get carry, then divide it by 10 to get the carry
            carry = sum / 10;
            // the value we'll get by it, will become as new node so. add it to our list
            ListNode node = new ListNode(sum % 10);
            // curr will point to that new node if we get
            curr.next = node;
            // update the current every time
            curr = curr.next; 
        }
        // return dummy.next, we don't want the value we have consider in it intially!!
        return dummy.next; 
    }
}
```

## Merge Two Sorted Lists {21}
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

## Merge Sorted Array {88}
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

## Symmetric Tree {101}
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

## Missing Number {268}
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

## Design Hit Counter {362}
Design a hit counter which counts the number of hits received in the past 5 minutes.

Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.

It is possible that several hits arrive roughly at the same time.

https://leetcode.ca/all/362.html

```
class HitCounter {
    public void hit(int timestamp) {
        final int i = timestamp % 300;

        if (timestamps[i] == timestamp) {
            ++hits[i];
        } else {
            timestamps[i] = timestamp;
            hits[i] = 1; // Reset hit count to 1
        }
    }

    public int getHits(int timestamp) {
        int countHits = 0;

        for (int i = 0; i < 300; ++i)
            if (timestamp - timestamps[i] < 300)
                countHits += hits[i];

        return countHits;
    }

    private int[] timestamps = new int[300];
    private int[] hits = new int[300];
}

```

## Rotate Image {48}
https://leetcode.com/problems/rotate-image

Step 1: Transpose the matrix. (transposing means changing columns to rows and rows to columns)

Step 2: Reverse each row of the matrix.
```
class Solution {
    /* Clockwise Rotate */
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int first = 0, last = rows - 1; first < last; first++, last--) {
            int[] tmp = matrix[first];
            matrix[first] = matrix[last];
            matrix[last] = tmp;
        }
        for (int i = 0; i < rows; i++) {
            for (int j = i + 1; j < cols; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }

    /* Counter-clockwise Rotate */
    public void antiRotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int first = 0, last = cols - 1; first < last; first++, last--) {
            for (int i = 0; i < matrix.length; i++) {
                int tmp = matrix[i][first];
                matrix[i][first] = matrix[i][last];
                matrix[i][last] = tmp;
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = i + 1; j < cols; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }
}
```

## Longest Palindromic Substring {5}
https://leetcode.com/problems/longest-palindromic-substring

**Approach:**
- Initialize variables minstart and maxlen as 0 to keep track of the longest palindrome found so far.
- Iterate over each character in the string using a variable i.
- Check if the remaining length from i to the end of the string is less than half of the maxlen. If it is, there is no possibility of finding a longer palindrome, so we break out of the loop.
- Set l and r as i, representing the left and right pointers of the potential palindrome.
- While r is within the string bounds and the character at r is equal to the next character, increment r.
- Update i to be r + 1 since all the characters from l to r have been accounted for.
- While l is greater than 0 and r is within the string bounds, and the characters at l-1 and r+1 are equal, decrement l and increment r.
- Calculate the length of the potential palindrome using newlen = r - l + 1.
- If newlen is greater than maxlen, update maxlen with newlen and minstart with l.
- After the loop finishes, return the substring of s starting from minstart with length maxlen, which represents the longest palindrome found in the string.

**Complexity:**
- Time Complexity: The code uses a two-pointer approach to expand around each center, resulting in a linear time complexity of O(n), where n is the length of the string.
- Space Complexity: The code uses a constant amount of extra space, resulting in a space complexity of O(1).
```
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n == 0)
            return "";
        if (n == 1)
            return s;
        
        int minstart = 0, maxlen = 0;
        
        for (int i = 0; i < n; ) {
            if (n - i < maxlen / 2)
                break;
            
            int l = i, r = i;
            
            // Find the center of the palindrome
            while (r < n - 1 && s.charAt(r) == s.charAt(r + 1))
                r++;
            
            // Update the next starting point
            i = r + 1;
            
            // Expand around the center to find the longest palindrome
            while (l > 0 && r < n - 1 && s.charAt(l - 1) == s.charAt(r + 1)) {
                l--;
                r++;
            }
            
            int newlen = r - l + 1;
            if (newlen > maxlen) {
                maxlen = newlen;
                minstart = l;
            }
        }
        
        return s.substring(minstart, minstart + maxlen);
    }
}
```

## Reconstruct Itinerary {332}
https://leetcode.com/problems/reconstruct-itinerary

All the airports are vertices and tickets are directed edges. Then all these tickets form a directed graph.

The graph must be Eulerian since we know that a Eulerian path exists.

Thus, start from "JFK", we can apply the Hierholzer's algorithm to find a Eulerian path in the graph which is a valid reconstruction.

Since the problem asks for lexical order smallest solution, we can put the neighbors in a min-heap. In this way, we always visit the smallest possible neighbor first in our trip.
```
import java.util.*;

public class Solution {

    Map<String, PriorityQueue<String>> flights;
    LinkedList<String> path;

    public List<String> findItinerary(List<List<String>> tickets) {
        flights = new HashMap<>();
        path = new LinkedList<>();
        for (List<String> ticket : tickets) {
            flights.putIfAbsent(ticket.get(0), new PriorityQueue<>());
            flights.get(ticket.get(0)).add(ticket.get(1));
        }
        dfs("JFK");
        return path;
    }

    public void dfs(String departure) {
        PriorityQueue<String> arrivals = flights.get(departure);
        while (arrivals != null && !arrivals.isEmpty())
            dfs(arrivals.poll());
        path.addFirst(departure);
    }
}
```

Iterative version:
```
import java.util.*;

class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> targets = new HashMap<>();
        for (List<String> ticket : tickets)
            targets.computeIfAbsent(ticket.get(0), k -> new PriorityQueue()).add(ticket.get(1));
        List<String> route = new LinkedList();
        Stack<String> stack = new Stack<>();
        stack.push("JFK");
        while (!stack.empty()) {
            while (targets.containsKey(stack.peek()) && !targets.get(stack.peek()).isEmpty())
                stack.push(targets.get(stack.peek()).poll());
            route.add(0, stack.pop());
        }
        return route;
    }
}
```

## Find K Closest Elements {658}
https://leetcode.com/problems/find-k-closest-elements

**Intuition**

The array is sorted. If we want find the one number closest to x,
we don't have to check one by one. it's straightforward to use binary search. Now we want the k closest, the logic should be similar.

**Explanation**

Assume we are taking A[i] ~ A[i + k -1]. We can binary search i.
We compare the distance between x - A[mid] and A[mid + k] - x

See following cases:
Assume A[mid] ~ A[mid + k] is sliding window

case 1: x - A[mid] < A[mid + k] - x, need to move window go left
-------x----A[mid]-----------------A[mid + k]----------

case 2: x - A[mid] < A[mid + k] - x, need to move window go left again
-------A[mid]----x-----------------A[mid + k]----------

case 3: x - A[mid] > A[mid + k] - x, need to move window go right
-------A[mid]------------------x---A[mid + k]----------

case 4: x - A[mid] > A[mid + k] - x, need to move window go right
-------A[mid]---------------------A[mid + k]----x------

If x - A[mid] > A[mid + k] - x, it means A[mid + 1] ~ A[mid + k] is better than A[mid] ~ A[mid + k - 1], and we have mid smaller than the right i.
So assign left = mid + 1.

**Important**

Note that, you SHOULD NOT compare the absolute value of abs(x - A[mid]) and abs(A[mid + k] - x).
It fails at cases like A = [1,1,2,2,2,2,2,3,3], x = 3, k = 3

**Complexity**

Time O(log(N - K)) to binary research and find result.
Space O(K) to create the returned list.
```
import java.util.*;

class Solution {
    public List<Integer> findClosestElements(int[] A, int k, int x) {
        int left = 0, right = A.length - k;
        while (left < right) {
            int mid = (left + right) / 2;
            if (x - A[mid] > A[mid + k] - x)
                left = mid + 1;
            else
                right = mid;
        }
        return Arrays.stream(A, left, left + k).boxed().collect(Collectors.toList());
    }
}
```

## Number of Islands {200}
https://leetcode.com/problems/number-of-islands

Use DFS
```
class Solution {
    public static int numIslands(char[][] grid) {
        int result = 0;
        int n = grid.length;
        int m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                   clearDFS(grid,i,j);
                   result++;
                }
            }
        }
        return result;
    }

    public static void clearDFS (char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i > grid.length - 1 || j > grid[0].length - 1 || grid[i][j] != '1') return;
        grid[i][j] = '0';
        clearDFS(grid,i+1,j);
        clearDFS(grid,i-1,j);
        clearDFS(grid,i,j+1);
        clearDFS(grid,i,j-1);
    }
}
```

## Is Subsequence {392}
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