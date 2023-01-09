package Custom_Impl.customLinkedListPalindrome;

/*Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
Example 1:
Input: head = [1,2,2,1]
Output: true
Example 2:
Input: head = [1,2]
Output: false*/

public class Main {
    static class CustomLinkedList<E> {
        private ListNode<E> head;
        private ListNode<E> tail;

        public ListNode<E> getHead() {return head;}
        public ListNode<E> getTail() {return tail;}

        // добавить вначало
        public void addAtFirst(E val) {
            ListNode<E> temp = new ListNode<>(val);
            temp.next = head;
            head = temp;
            if (tail == null) {
                tail = temp;
            }
        }

        // добавить в конец
        public void addAtLast(E val) {
            if (tail == null) {
                addAtFirst(val);
                return;
            }
            ListNode<E> temp = new ListNode<>(val);
            tail.next = temp;
            tail = temp;
        }
    }

    public static void main(String[] args) {
        int[] vals = {1,2,2,3};
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        for (int i=0; i< vals.length; i++)
            list.addAtLast(vals[i]);
        PrintLinkedList.printLinkedList(list.getHead());
        Solution1 s = new Solution1();
        PrintLinkedList.printLinkedList(s.reverseList(list.getHead()));
    }

}
