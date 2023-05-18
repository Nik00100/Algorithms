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
41. [Maximize Distance to Closest Person {849}](#maximize-distance-to-closest-person-849)
42. [Median of Two Sorted Arrays {4}](#median-of-two-sorted-arrays-4)
43. [Subarray Sums Divisible by K {974}](#subarray-sums-divisible-by-k-974)
44. [Search in Rotated Sorted Array {33}](#search-in-rotated-sorted-array-33)
45. [Serialize and Deserialize BST {449}](#serialize-and-deserialize-bst-449)
46. [Squares of sorted array {977}](#squares-of-sorted-array-977)
47. [Intersection of Two Arrays II {350}](#intersection-of-two-arrays-ii-350)
48. [Remove Nth Node From End of List {19}](#remove-nth-node-from-end-of-list-19)
49. [Maximal Rectangle {85}](#maximal-rectangle-85)
50. [Find First and Last Position of Element in Sorted Array {34}](#find-first-and-last-position-of-element-in-sorted-array-34)
51. [Evaluate Reverse Polish Notation {150}](#evaluate-reverse-polish-notation-150)
52. [Perfect Squares {279}](#perfect-squares-279)
53. [Is substring strStr() {28}](#is-substring-strstr-28)
54. [Product of Array Except Self {238}](#product-of-array-except-self-238)
55. [Simplify Path {71}](#simplify-path-71)
56. [Palindrome Linked List {234}](#palindrome-linked-list-234)

## Line Reflection {356}
Дано n точек на 2D плоскости, найти существует ли линия, параллельная y-оси отображающая данные точки.
https://leetcode.ca/all/356.html

- Сначала найти max и min значения абсцисс Х всех точек, таким образом среднее из этих двух является абсциссой искомой линиии.
- Затем в цикле перебрать каждую точку и если находим нессиметричную точку возвращаем false

Временная сложность->: O(N), простраственная: O(N)
```
class Solution {
    public boolean isReflected(int[][] points) {
        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
        Set<String> pointSet = new HashSet<>();
        for (int[] point : points) {
            minX = Math.min(minX, point[0]);
            maxX = Math.max(maxX, point[0]);
            pointSet.add(point[0] + "." + point[1]);
        }
        long s = minX + maxX;
        for (int[] point : points) {
            if (!pointSet.contains((s - point[0]) + "." + point[1])) {
                return false;
            }
        }
        return true;
    }
}
```

## Summary ranges {228}
В отсортированном массиве вернуть список диапазонов. К примеру: Input: nums = [0,1,2,4,5,7]; Output: ["0->2","4->5","7"]
https://leetcode.com/problems/summary-ranges/

В цикле пройтись по всем индексам массива:
1. Переменной `begin` присваивается `i-ый` элемент.
2. В цикле проверяется не достиг ли индекс `i` конца массива и `i-ый` элемент плюс 1 дает следующий элемент. 
Если да, продолжаем инкрементировать индекс `i` до того, как условие нарушится.
2. Переменной  `end` присваивается `i-ый` элемент
3. Выполняем проверку `begin == end`, если равны, то добавляем в результат `begin` (диапазон состоит из одного числа).
4. Иначе добавляем `begin+"->"+end`.

Временная сложность-> 0(N), простраственная: O(1)
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
Даны два списка целочисленных элементов. Реализовать итератор, возвращающий элементы поочередно. К примеру: v1 = [1, 2]; v2 = [3, 4, 5, 6]; output = [1, 3, 2, 4, 5, 6]
https://leetcode.ca/all/281.html

- Базовое решение проблемы - использовать очередь для загрузки всех итераторов. 
- При этом метод next() выбирает из очереди соответствующий итератор и выполняет метод iterator.next()
- Если `iterator.hasNext() == true` (итератор содержит элемент), возвращает итератор обратно в очередь.

Основной момент - итератор должен содержаться в очереди, если `hasNext() == true`.

Временная сложность->: O(N), простраственная: O(N)
```
public class ZigzagIterator {
    Queue<Iterator<Integer>> q;
    
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        q = new LinkedList<Iterator<Integer>>();
        Iterator<Integer> it1 = v1.iterator();
        Iterator<Integer> it2 = v2.iterator();
        
        // Ensure that the Iterator in the Queue hasNext().
        if (it1.hasNext())
            q.add(it1);
        if (it2.hasNext())
            q.add(it2);
    }
 
    public int next() {
        Iterator<Integer> it = q.remove();
        int res = it.next();
        if (it.hasNext()) {
            q.add(it);
        }
        return res;
    }
 
    public boolean hasNext() {
        return !q.isEmpty();
    }
}

```

## String Compression {443}
https://leetcode.com/problems/string-compression

- Используем два указателя, один `i` для итерации по индексам символов входного массива и второй `ans` для отслеживания индекса в сжатом массиве.
- Переменные `start` и `end` для подсчета идущих подряд символов. 
- Переменной `current` присваиваем `i-ый` символ массива.
- В цикле проходим по элементам массива пока не найдем отличающийся символ либо достигнем конца массива.
- На каждой итерации инкрементируем индекс `i`.

Временная сложность-> 0(N), простраственная: O(1)
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

- Используем три указателя prevHead, head, recordNext. 
- В каждой итерации присваиваем `head.next = prevHead` и сдвигаем указатели на один шаг. 
- При выходе из цикла while, `head` будет равен null, а `prevHead` указывает на конечный узел оригинального списка, поэтому возаращаем `prevHead`.

Временная сложность-> 0(N), простраственная: O(1)
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
        ListNode prevHead = null;
        while(head != null){
            ListNode recordNext = head.next;
            head.next = prevHead;
            prevHead = head;
            head = recordNext;
        }
        return prevHead;
    }
}
```

## Generate Parentheses {22}
https://leetcode.com/problems/generate-parentheses

Используем вспомогательный рекурсивный метод для генерации скобок.
Два указателя `left` и `right` нужны для контроля за количеством левых и правых скобок.

Временная сложность-> 0(2^N), простраственная: O(N)
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
	/* Для n = 2								   	
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

Временная сложность-> 0(1), простраственная: O(N)
```
class Node {
    int key;
    int val;
    Node prev;
    Node next;

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

class LRUCache {

    Map<Integer, Node> map;
    Node head;
    Node tail;
    int capacity;

    // capacity - это размер структуры; два узла head и tail указывают друг на друга
    public LRUCache(int capacity) {
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }

    // узел current становится head, O(1)
    public void moveToHead(Node curr) {
        curr.next = head.next;
        curr.prev = head;
        head.next = curr;
        curr.next.prev = curr;
    }

    // удаление узла, O(1)
    public void delete(Node curr) {
        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;
    }

    // при операции get нужно удалить и переместить узел в head, т.к. использовался недавно O(1)
    public int get(int key) {
        if(map.containsKey(key)) {
            Node curr = map.get(key);
            delete(curr);
            moveToHead(curr);
            return curr.val;
        }
        return -1;
    }

    // при операции put узел становится head
    // если емкость превышена, tail удаляется (наименее используемый) O(1)
    public void put(int key, int value) {
        if(map.containsKey(key)) {
            Node curr = map.get(key);
            curr.val = value;
            delete(curr);
            moveToHead(curr);
        }
        else if(map.size()<capacity) {
            map.put(key, new Node(key, value));
            moveToHead(map.get(key));
        }
        else {
            map.put(key, new Node(key, value));
            map.remove(tail.prev.key);
            delete(tail.prev);
            moveToHead(map.get(key));
        }
    }
}
```

## Move Zeroes {283}
https://leetcode.com/problems/move-zeroes

- Заполняем ненулевыми элементами ячейки массива, начиная сначала
- Оставшиеся ячейки заполняем нулями

Временная сложность-> 0(N), простраственная: O(1)
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

Используем два указателя.

Временная сложность-> 0(N), простраственная: O(1)
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

Перебираем все слова
- Каждое слово сортируем в лексикографическом порядке. 
- Отсортированное слово является ключом мапы.
- В значение мапы по этому ключу добавляем исходное слово.

Временная сложность -> O(N*MlogM) M - максимальная длина слова
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
Дан бинарный массив, найти максимальное количество последовательно идущих 1 в массиве, если есть возможность изменить один 0.
https://leetcode.ca/all/487.html

Временная сложность-> 0(N), простраственная: O(N) - 1 вариант; простраственная: O(1) - 2 вариант
```
class Solution {
    // первый вариант использовать очередь для хранения индексов нулей
    public int findMaxConsecutiveOnes(int[] nums) {
        final int maxZeros = 1;
        int ans = 0;
        
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

    // второй вариант (k - это максимальное количество нулей, которое можно изменить) использовать скользящее окно с индексами [j, i] и разница i - j ответ.
    // Если число равно нулю, уменьшаем k. Если k становится меньше нуля, увеличиваем j и обновляем k
    static int longestOnes(int[] nums, int k) {
        int i = 0, j = 0;
        while (i < nums.length) {
            k -= nums[i++] == 1 ? 0 : 1;
            if (k < 0)
                k += nums[j++] == 1 ? 0 : 1;
            //System.out.println(i+" "+j);
        }
        return i - j;
    }
}
```

## One Edit Distance {161}
Даны две строки s и t, определить является ли их редакционное расстояние 1.
Возможны 3 варианта для данного случая:
1. Вставка символа в s для получения t
2. Удаление символа в s для получения t
3. Замена символа в s для получения t

https://leetcode.ca/all/161.html

При определении является ли их редакционное расстояние 1 возможны три случая:

- Разница длин строк больше 1, возвращаем False.
- Разница длин строк равна 1. В большей строке удаляется один символ, а остальное остается как в меньшей строке.
- Разница длин строк равна 0, а символы на соответствующих позициях в двух строках могут различаться только в одном месте.

Временная сложность-> 0(N), простраственная: O(1) 
(N = min(lengthS, lengthT)
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

Считаем max сумму счетчиков prev и curr
prev: счетчик единиц "до появления 0 в массиве"
curr: счетчик единиц "после появления 0"

Временная сложность-> 0(N), простраственная: O(1) 
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

- Перебираем все символы последовательности.
- Помещаем в стек правые скобки ')', ']', или '}', если символом является соответствующая левая скобка. 
- Если символ - правая скобка, проверяем, что стек не пуст и верхний элемент стека равен символу.
- Если условие не выполняется, последовательность неверная. 
- В конце проверяем, что стек пуст.

Временная сложность-> 0(N), простраственная: O(1) 
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

- Переменная `sum` отслеживает накопительную сумму элементов в массиве
- Переменная `count` отслеживает количество подмассивов, сумма которых равна k. 
- Запись в `map` с ключом 0 и значением 1 представляет накопительную сумму пустого подмассива, равную 0.
- Итерируем по каждому элементу в массиве, добавляя текущий элемент к накопительной сумме и проверяя, есть ли ключ `sum - k` в `map`. 
- Если да, то увеличиваем `count` на значение этого ключа в `map`. Это значение представляет количество раз, когда данная накопительная сумма была встречена до этого момента, и, следовательно, количество подмассивов, оканчивающихся на текущем элементе и сумма которых равна k.

Временная сложность-> 0(N), простраственная: O(N) 
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

Используем ArrayList & HashMap

Временная сложность-> 0(1) в среднем, простраственная: O(N)
```
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
```

## Two Sum {1}
https://leetcode.com/problems/two-sum


Используем HashMap для хранения числа и индекса

Временная сложность-> 0(N), простраственная: O(N)
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

- Узел `dummy` и указатель `curr` отслеживают текущую позицию в объединенном списке.
- Куча `minHeap` хранит наименьший узел из каждого списка.
- Перебираются все списки в lists. Для каждого непустого списка добавляется первый узел в `minHeap`.
- Цикл выполняется до тех пор, пока `minHeap` не станет пустым. 
- На каждой итерации наименьший узел удаляется из `minHeap` и добавляется в объединенный список путем обновления указателя `next` узла `curr`. 
- Если удаленный узел имеет следующий узел, он добавляется в `minHeap`.
- После обработки и добавления всех узлов в объединенный список возвращается узел `next` узла `dummy`.

Временная сложность-> 0(NlogN), простраственная: O(N)
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

- Массивы `data` и `test` размером 26 содержат частоту символов строки `s1` и скользящего окна размером `s1.length()` строки `s2`, соответственно.
- Заполняется массив `data` частотой символов в `s1` и первым окном символов в `s2`.
- Затем выполняется итерация по каждому окну символов в `s2`. 
- Для каждого окна проверяется, совпадает ли частота символов в окне с частотой символов в `s1`, вызывая вспомогательный метод `equalsString`. 
- Если они совпадают, это означает, что перестановка `s1` существует в виде подстроки `s2`, и возвращается `true`.
- После проверки каждого окна код обновляет частоту символов в следующем окне, увеличивая счетчик следующего символа и уменьшая счетчик первого символа в текущем окне.
- Если после проверки всех окон не найдено ни одной перестановки, код возвращает `false`.

Временная сложность-> 0(N), простраственная: O(1)
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
Дан массив интервалов времени встреч, состоящий из времен начала и окончания [[s1, e1], [s2, e2], ...] (si < ei). Необходимо найти минимальное количество комнат для проведения всех встреч.

https://leetcode.ca/all/253.html

Временная сложность-> 0(NlogN), простраственная: O(N)
```
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        // сортируем массив по времени начала встреч
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));

        // хранение времени окончания встреч 
        Queue<Integer> minHeap = new PriorityQueue<>();

        for (int[] interval : intervals) {
            // нет перекрытий, мы можем использовать одну и ту же комнату повторно
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

- Идея заключается в сортировке интервалов по их начальным точкам. 
- Затем берем первый интервал и сравниваем его конец с началами следующих интервалов. 
- Пока они перекрываются, мы обновляем конец, чтобы он был максимальным из перекрывающихся интервалов. 
- Как только нашли неперекрывающийся интервал, добавляем предыдущий "расширенный" интервал и начинаем сначала.

Временная сложность-> 0(NlogN), простраственная: O(N)
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

Класс RecentCounter подсчитывает количество запросов в определенном временном интервале.
```
// TreeMap Time: O(logN), space: O(N)
class RecentCounter {
    TreeMap<Integer, Integer> tm;

    public RecentCounter() {
        tm = new TreeMap<>();
    }
    
    public int ping(int t) {
        tm.put(t, 1 + tm.size());
        return tm.tailMap(t - 3000).size();
    }
}
```
```
// Queue Time & space: O(Math.min(N, 3000))
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
```

## Validate Binary Search Tree {98}
https://leetcode.com/problems/validate-binary-search-tree

Применяем итеративный обход в порядке "inorder" с использованием стека. Рещаем три задачи однотипных.

Временная сложность-> 0(N), простраственная: O(N)
```
// Определение узла
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
```
1. Решение Validate Binary Search Tree
```
class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(pre != null && root.val <= pre.val) return false;
            pre = root;
            root = root.right;
        }
        return true;
    }
}
```
2. Решение Binary Tree Inorder Traversal
```
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        while(root != null || !stack.empty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);
            root = root.right;

        }
        return list;
    }
}
```
3. Решение Kth Smallest Element in a BST
```
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while(root != null || !stack.isEmpty()) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(--k == 0) break;
            root = root.right;
        }
        return root.val;
    }
}
```

## Implement Queue using Stacks {232}
https://leetcode.com/problems/implement-queue-using-stacks

Временная сложность-> 0(1)
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
```

## Spiral Matrix II {59}
https://leetcode.com/problems/spiral-matrix-ii

- Цикл выполняется до тех пор, пока все элементы в матрице не будут заполнены. 
- На каждой итерации заполняем верхнюю строку слева направо, правый столбец сверху вниз, нижнюю строку справа налево и левый столбец снизу вверх. 
- После заполнения каждой строки или столбца обновляем соответствующую переменную, отвечающую за начальную/конечную строку или столбец, чтобы двигаться внутрь матрицы.
- После заполнения всех элементов возвращаем полученную матрицу.

Временная сложность-> 0(N*N)
```
class Solution {
    public int[][] generateMatrix(int n) {
        
        // creating a matrix of n x n
        
        int[][] ans = new int[n][n];
        
        int count = 1;
        int total = n * n;
        
        // инициализируем переменные для отслеживания начальной и конечной позиций строк и колонок
        int startingRow = 0;
        int endingRow = n - 1;
        int startingCol = 0;
        int endingCol = n - 1;
        
        while (count <= total) {
            
            // заполним верхнюю строку слева направо
            for (int i = startingCol; i <= endingCol; i++) {
                ans[startingRow][i] = count;
                count++;
            }
            startingRow++;
            
            // заполним правый столбец сверху вниз
            for (int i = startingRow; i <= endingRow; i++) {
                ans[i][endingCol] = count;
                count++;
            }
            endingCol--;
            
            // заполним нижнюю строку справа налево
            for (int i = endingCol; i >= startingCol; i--) {
                ans[endingRow][i] = count;
                count++;
            }
            endingRow--;
            
            // заполним левый столбец снизу вверх
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

Оптимизированный подход с использованием скользящего окна за один проход:

- Если в диапазоне A[start] ~ A[end] количество нулей меньше или равно K, мы продолжаем увеличивать end.
(Пояснение: Окно все еще допустимо и размер окна может быть увеличен.)
- Если в диапазоне A[start] ~ A[end] количество нулей больше K, мы увеличиваем и start, и end.
(Пояснение: Окно содержит лишние нули, поэтому оно больше не допустимо, и мы увеличиваем и start, и end, чтобы сохранить размер окна неизменным.)

Временная сложность-> 0(N), простраственная: O(1)
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

**Подход**

Необходимо определить общий объем воды, который можно задержать. Разница в высотах между меньшей и большей планкой решает задачу, но меньшая планка может быть меньше, чем несколько планок, или одна высокая планка может быть выше нескольких меньших планок. Поэтому мы можем вычислить общий объем воды, задержанной каждой планкой, отслеживая меньшие планки в стеке, который содержит планки в монотонно убывающем порядке.

**Алгоритм**

Извлекаем элементы из стека, если высота текущей планки больше, чем минимальная высота планки в стеке, и вычисляем объем воды, задержанной в этой области, и добавляем его к ответу.

Временная сложность-> 0(N), простраственная: O(N)
```
class Solution {
    public int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for(int i = 0; i < height.length; i++) {
            while(!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int top = height[stack.peek()];
                stack.pop();
                // Последняя планка не может задерживать воду.
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

**Алгоритм**

- Используем скользящее окно с указателями для перемещения окна и hashset.
- Если набор не содержит символ, то сначала добавим в набор и одновременно вычислим maxLength.
- Если символ присутствует в наборе, нужно сдвинуть скользящее окно на 1, прежде чем удалить все символы, находящиеся перед символом, который уже присутствует в окне.
- Теперь нужно удалить этот символ, переместить указатель слева и также добавить новый символ в набор.

Временная сложность-> 0(N), простраственная: O(k), где k - кол-во различных символов в hashset.
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

Может возникнуть ситуация:
```
l1     1     3                              1     
+      +     +                              +
l2     1     9                              1 
-----------------------------------------------------------------------------------
cur    2    12(должна быть 2)        2 + carry(carry переносится с 12)
            12 %10 = 2
		    carry = 12//10 =1	   
```
Переменная carry позволяет справиться с этой ситуацией: выравниваем слагаемые по вертикали и складываем столбцы, начиная с самого левого столбца. Если сумма столбца превышает девять, "переносим" дополнительную цифру в следующий столбец.

Временная сложность-> 0(N), простраственная: O(k)
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
        // dummy list
        ListNode dummy = new ListNode(0);
        // указатель
        ListNode curr = dummy;
        // Инициализируем переменную carry значением 0
        int carry = 0;
        // Цикл будет выполняться, пока l1 ИЛИ l2 не достигнут значения null, ИЛИ если они оба достигли значения null.
        // Значение переменной carry также добавим в список.
        while (l1 != null || l2 != null || carry == 1) {
            // Инициализируем sum
            int sum = 0;
            // Добавляем значение l1 к sum и передвигаем l1
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            // Добавляем значение l2 к sum и передвигаем l2
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            // добавляем carry к sum
            sum += carry;
            // значение переноса
            carry = sum / 10;
            // значение добавляем в нлвый узел
            ListNode node = new ListNode(sum % 10);
            // curr указывает на новый узел
            curr.next = node;
            // обновляем
            curr = curr.next; 
        }
        return dummy.next; 
    }
}
```

## Merge Two Sorted Lists {21}
https://leetcode.com/problems/merge-two-sorted-lists

Создаем фиктивный узел, к которому присоединяем узлы из списков. Мы проходим по спискам с помощью двух указателей и постепенно строим результирующий список так, чтобы значения были монотонно возрастающими.

Временная сложность-> 0(N), простраственная: O(1)
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

Используем три указателя:
- i указывает на индекс (m-1) nums1 и хранит наибольший элемент nums1.
- j указывает на индекс (n-1) nums2 и хранит наибольший элемент nums2.
- k последняя позиция nums1, сюда вставляется наибольший элемент после сравнения обоих массивов.

В цикле по массиву nums2 проверяем условие:
- Если индекс i больше 0 и элемент по индексу i массива nums1 больше элемента по индексу j массива nums2.
Если это условие верно, значит элемент по индексу i массива nums1 является наибольшим из обоих массивов и будет помещен в последнюю позицию массива nums1. Затем мы уменьшим значение i, так как нам нужно сравнить другой элемент массива nums1 с элементом массива nums2.
Мы также уменьшим значение k, так как последняя позиция заполнена, и нам нужна новая позиция.
- Если условие не выполняется, это означает, что либо элемент по индексу j массива nums2 больше элемента по индексу i массива nums1, либо нет элементов для сравнения с массивом nums1, и нам просто нужно поместить элементы массива nums2 в массив nums1 (это оставшиеся наименьшие элементы).
Мы уменьшим значение j для других сравнений и также значение k для сохранения других элементов.

Временная сложность-> 0(M + N), простраственная: O(1)
```
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // указатели 
        int i=m-1; 
        int j=n-1; 
        int k=nums1.length-1; 

        // Цикл по nums2 
        while (j >= 0) {
            // Если nums1[i] > nums2[j], то будет сохранен в nums1[k]
            // также проверяем i >= 0 (наличие элементов в nums1)
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                k--; 
                i--; //обновляем указатель
            } else{
                // иначе nums2[j] будет сохранен в nums1[k]
                nums1[k] = nums2[j];
                k--; 
                j--; //обновляем указатель
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

## Maximize Distance to Closest Person {849}
https://leetcode.com/problems/maximize-distance-to-closest-person

**Explanation**

- `last` is the index of last seated seat.
- Loop on all seats, when we met a people, we count the distance from the last.
- The final result = max(distance at the beginning, distance in the middle / 2, distance at the end).

**Complexity**

Time O(N) Space O(1)
```
public class Solution {
    public int maxDistToClosest(int[] seats) {
        int res = 0, n = seats.length, last = -1;
        for (int i = 0; i < n; ++i) {
            if (seats[i] == 1) {
                res = last < 0 ? i : Math.max(res, (i - last) / 2);
                last = i;
            }
        }
        res = Math.max(res, n - last - 1);
        return res;
    }
}
```

## Median of Two Sorted Arrays {4}
https://leetcode.com/problems/median-of-two-sorted-arrays
```
class Solution {
    public double findMedianSortedArrays(int A[], int B[]) {
        int n = A.length;
        int m = B.length;
        // the following call is to make sure len(A) <= len(B).
        // yes, it calls itself, but at most once, shouldn't be
        // consider a recursive solution
        if (n > m)
            return findMedianSortedArrays(B, A);

        // now, do binary search
        int k = (n + m - 1) / 2;
        int l = 0, r = Math.min(k, n); // r is n, NOT n-1, this is important!!
        while (l < r) {
            int midA = (l + r) / 2;
            int midB = k - midA;
            if (A[midA] < B[midB])
                l = midA + 1;
            else
                r = midA;
        }

        // after binary search, we almost get the median because it must be between
        // these 4 numbers: A[l-1], A[l], B[k-l], and B[k-l+1]

        // if (n+m) is odd, the median is the larger one between A[l-1] and B[k-l].
        // and there are some corner cases we need to take care of.
        int a = Math.max(l > 0 ? A[l - 1] : Integer.MIN_VALUE, k - l >= 0 ? B[k - l] : Integer.MIN_VALUE);
        if (((n + m) & 1) == 1)
            return (double) a;

        // if (n+m) is even, the median can be calculated by
        // median = (max(A[l-1], B[k-l]) + min(A[l], B[k-l+1]) / 2.0
        // also, there are some corner cases to take care of.
        int b = Math.min(l < n ? A[l] : Integer.MAX_VALUE, k - l + 1 < m ? B[k - l + 1] : Integer.MAX_VALUE);
        return (a + b) / 2.0;
    }
}
```

## Subarray Sums Divisible by K {974}
https://leetcode.com/problems/subarray-sums-divisible-by-k

**Approach**

When it comes to Sum of Subarray, the use of Prefix Sum is especially important.

Prefix Sum is the sum of the current integer with the previous integer in the array (Prefix).

Example: nums = [1,2,3,4,5] has the prefix sum array of prefixSum = [1,3,6,10,15], where the nums[0] + 1 = 1, nums[1] + nums[0] = 2 + 1 = 3, nums[2] + nums[1] = 3 + 3 = 6, and so on.

Using the example above, we can determine the subarray sum of any subarray using prefix sum.

To get the subarray sum of nums[2] to nums[4] == 3 + 4 + 5 == 12, we can get from prefixSum[5] - prefixSum[1] == 15 - 3 == 12.

With Prefix Sum, we can evaluate if any subarray sum is divisible by 'k', if two prefix sums have the same remainder of 'k'.

For Example, nums = [4,2,3], k = 5, with two prefix sum, 4 [4] and 9 [4,2,3].

Both remainders are 4, with the subarray between the prefix sum 9 [4,2,3] - 4 [4] == 5 [2,3], which is divisible by 5.

**Complexity**

Time Complexity: O(n),
where 'n' is the length of 'nums'.
In fact, in actual is '2n' as we traverse 'nums' once and the HashMap once, with the worst time complexity of the HashMap being 'n'.

Space Complexity: O(n),
where 'n' is the length of 'nums'.
The worst case is when all the prefix sums in 'nums' have different remainders with 'k', resulting in the maximum size of the HashMap to be 'n'.
```
class Solution {
public int subarraysDivByK(int[] nums, int k) {

        // Use the HashMap to record the frequency of all the prefix sum remainders.
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0, remainder = 0; i < nums.length; i++) {
            // Note that the integer in 'nums' can be negative.
            // Thus, we need to adjust the negative remainder to positive remainder.
            // Below accounts for both negative and positive remainders.
            // We can also check if the remainder is negative, then add a 'k' to make the remainder positive.
            // For Example, nums = [-2,3,2], k = 5,
            // remainder for the prefix sum of [-2,1,3] are -2, 1 and 3 respectively.
            // We know that [3,2] sum to 5, which is divisible by 5.
            // After converting -2 to 3, by adding 5, it has the same remainder with prefix sum 3.
            remainder = ((remainder + nums[i]) % k + k) % k;
            map.put(remainder, map.getOrDefault(remainder, 0) + 1);
        }
        // The result contains all the prefix sum with remainder 0,
        // as all the prefix sum with remainder of 0 is itself divisible by 'k'.
        // However, do note that the prefix sum with remainder 0 also able to form subarray sums that is divisible by 'k'
        // with one another, which will be calculated next.
        // For Example: nums = [5,5,5,5], k = 5,
        // The prefix sum of [5,10,15,20] are themselves divisible by 5, while also forming subarray sums divisible by 5
        // with 10 [5,5] - 5 [5] == 5, 15 [5,5,5] - 5 [5] == 10, etc.
        int result = map.getOrDefault(0, 0);

        // The prefix sums with the same remainder can form subarray sums that is divisible by 'k' with each other.
        // For each remainder, the number of subarray that is divisible by 'k' is the number of combinations from the frequency.
        // Equation for the number of combinations of n items is n * "(n - 1) / 2".
        for (int frequency : map.values())
            result += frequency * (frequency - 1) / 2;

        return result;
    }
}
```

## Search in Rotated Sorted Array {33}
https://leetcode.com/problems/search-in-rotated-sorted-array

The Binary search approach is based on the fact that a rotated sorted array can be divided into two sorted arrays.

- The approach starts with finding the mid element and compares it with the target element.
- If they are equal, it returns the mid index. If the left half of the array is sorted, then it checks if the target lies between the start and the mid, and updates the end pointer accordingly.
- Otherwise, it checks if the target lies between mid and end, and updates the start pointer accordingly.
- If the right half of the array is sorted, then it checks if the target lies between mid and end, and updates the start pointer accordingly.
- Otherwise, it checks if the target lies between start and mid, and updates the end pointer accordingly.
- This process continues until the target element is found, or the start pointer becomes greater than the end pointer, in which case it returns -1.
- This approach has a time complexity of O(log n).
```
class Solution {
    public int search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        int mid = (start + end) / 2;
        while (start <= end) {
            mid = (start + end) / 2;
            if (target == nums[mid]) {
                return mid;
            }
            if (nums[start] <= nums[mid]) {
                if (nums[start] <= target && nums[mid] >= target) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (nums[end] >= target && nums[mid] <= target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }
}
```

## Serialize and Deserialize BST {449}
https://leetcode.com/problems/serialize-and-deserialize-bst

Pre order traversal of BST will output root node first, then left children, then right.

`root left1 left2 leftX right1 rightX`

Pre-order traversal is BST's serialized string. I am doing it iteratively.
To deserialized it, use a queue to recursively get root node, left subtree and right subtree.

Time complexity is O(N*N).
```
class Codec {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        dfs(root, sb);
        return sb.toString();
    }

    private void dfs(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        sb.append(root.val + ",");
        dfs(root.left, sb);
        dfs(root.right, sb);
        return;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] arr = data.split(",");
        TreeNode root = null;
        for (String s : arr) {
            if (s.length() > 0) {
                root = buildBST(root, Integer.parseInt(s));
            }
        }
        return root;
    }

    public TreeNode buildBST(TreeNode root, int v) {
        if (root == null) return new TreeNode(v);
        if (v < root.val) {
            root.left = buildBST(root.left, v);
        } else {
            root.right = buildBST(root.right, v);
        }
        return root;
    }
}
```

## Squares of sorted array {977}
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

## Intersection of Two Arrays II {350}
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

## Remove Nth Node From End of List {19}
https://leetcode.com/problems/remove-nth-node-from-end-of-list
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Two pointers - fast and slow
        ListNode slow = head;
        ListNode fast = head;
        // Move fast pointer n steps ahead
        for (int i = 0; i < n; i++) {
            if (fast.next == null) {
                // If n is equal to the number of nodes, delete the head node
                if (i == n - 1) {
                    head = head.next;
                }
                return head;
            }
            fast = fast.next;
        }
        // Loop until we reach to the end.
        // Now we will move both fast and slow pointers
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        // Delink the nth node from last
        if (slow.next != null) {
            slow.next = slow.next.next;
        }
        return head;
    }
}
```

## Maximal Rectangle {85}
https://leetcode.com/problems/maximal-rectangle/

Solutions to other similar question on LeetCode: 84. Largest Rectangle in Histogram
```
/**
 * This solution is converting the input matrix row by row (OR column by column)
 * to Largest Rectangle in a Histogram.
 *
 * For each row (OR column) cumulative height is calculated. Then use stack to
 * save the increasing height index.
 *
 * Time Complexity: O(R * C). Each element is added to stack once and popped
 * from stack once.
 *
 * Space Complexity: O(min(R,C)). We will either store a row or a column
 *
 * R = Number of rows in the matrix. C = Number of columns in the matrix.
 */
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Input matrix is null");
        }
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        if (cols < rows) {
            return maximalRectangleHelper(matrix, rows, cols, true);
        } else {
            return maximalRectangleHelper(matrix, cols, rows, false);
        }
    }

    private int maximalRectangleHelper(char[][] matrix, int big, int small, boolean isColsSmall) {
        int[] heights = new int[small];
        int largestRectangle = 0;
        for (int i = 0; i < big; i++) {
            Deque<Integer> stack = new ArrayDeque<>();
            for (int j = 0; j <= small; j++) {
                if (j < small) {
                    if (isColsSmall) {
                        heights[j] = matrix[i][j] == '0' ? 0 : heights[j] + 1;
                    } else {
                        heights[j] = matrix[j][i] == '0' ? 0 : heights[j] + 1;
                    }

                }
                while (!stack.isEmpty() && (j == small || heights[stack.peek()] >= heights[j])) {
                    int h = heights[stack.pop()];
                    int left = stack.isEmpty() ? -1 : stack.peek();
                    largestRectangle = Math.max(largestRectangle, (j - 1 - left) * h);
                }
                stack.push(j);
            }
        }
        return largestRectangle;
    }
}
```

## Find First and Last Position of Element in Sorted Array {34}
https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array

```
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] ans = {-1,-1};
        int s = search(nums,target,true);
        int e = search(nums,target,false);
        ans[0] = s;
        ans[1] = e;
        return ans;

    }
    public int search(int[] nums , int target , boolean FindFirst){
        int s = 0;
        int e = nums.length-1;
        int ans = -1;
        while(s<=e){
            int mid = s+(e-s)/2;
            if(nums[mid] < target) s = mid +1;
            else if(nums[mid] > target) e = mid - 1;
            else {
                ans = mid;
                if(FindFirst==true) e = mid -1;
                else s = mid+1;
            }
        }
        return ans;
    }
}
```

## Evaluate Reverse Polish Notation {150}
https://leetcode.com/problems/evaluate-reverse-polish-notation
```
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
    for (String s : tokens) {
        if (s.equals("+")) {
            stack.push(stack.pop() + stack.pop());
        } else if (s.equals("-")) {
            int b = stack.pop();
            int a = stack.pop();
            stack.push(a - b);
        } else if (s.equals("*")) {
            stack.push(stack.pop() * stack.pop());
        } else if (s.equals("/")) {
            int b = stack.pop();
            int a = stack.pop();
            stack.push(a / b);
        } else {
            stack.push(Integer.parseInt(s));
        }
    }
    return stack.pop();  
    }
}
```

## Perfect Squares {279}
https://leetcode.com/problems/perfect-squares
```
class Solution {
    public int numSquares(int n) {
        int dp[]=new int [n+1];
        dp[0]=0;
        dp[1]=1;
        
        for(int i=2;i<dp.length;i++){
            int min=Integer.MAX_VALUE;
            for(int j=1;j*j<=i;j++){
                min=Math.min(min,dp[i-j*j]);
            }
            dp[i]=min+1;
        }
        return dp[n];
    }
}
```

## Is substring strStr() {28}
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

## Product of Array Except Self {238}
https://leetcode.com/problems/product-of-array-except-self

Time complexity: O(n)
```
class Solution {
    public int[] productExceptSelf(int[] nums) {
        //Approach: Using prefix and postfix
        //Idea: Just find prefix and postfix product and keep them in the ans array

        int ans[] = new int[nums.length];
        int pre = 1, post = 1;
        
        //find pre product
        for(int i=0;i<nums.length;i++){
            ans[i] = pre;
            pre*=nums[i];
        }

        //find post product
        for(int i=nums.length-1;i>=0;i--){
            ans[i]*=post;
            post*=nums[i];
        }

        return ans;
    }
}
```

## Simplify Path {71}
https://leetcode.com/problems/simplify-path/

So, basically what are we doing: `Pushing and Popping` directory names based on rules

And what are the rules :
- /.. come out from the directory
- /nameOfDirectory going into directory

We Generically used in Stack 

Alright, back to the problem - So, what we can do is by looking at the rules, split the directrory by the slash/ given and that will give us in the form of array e.g :- [a, ., b, .., .., c]

But remeber when returning we have to go in the form of reverse order. Because Stack use LIFO order and the highest one will comes out. But we need the lowest once first. So, we need to append in the carefull manner.
```
class Solution {
    public String simplifyPath(String path) {
        Stack<String> s = new Stack<>();
        StringBuilder res = new StringBuilder();
        String[] p =path.split("/");
        
        for(int i=0;i<p.length;i++){
            if(!s.isEmpty()  && p[i].equals("..")) s.pop();
            else if(!p[i].equals("") && !p[i].equals(".") && !p[i].equals(".."))
                s.push(p[i]);
        }
        
        
        if(s.isEmpty()) return "/";
        while(!s.isEmpty()){
            res.insert(0,s.pop()).insert(0,"/");
        }
        
        return res.toString();
    }
}
```

## Palindrome Linked List {234}
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