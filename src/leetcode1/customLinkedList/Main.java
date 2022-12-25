package leetcode1.customLinkedList;

/*Given the head of a singly linked list, reverse the list, and return the reversed list.
Example 1:
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]*/

public class Main {

    static class ListNode<E> {
        E val;
        ListNode<E> next;
        ListNode() {}
        ListNode(E val) {this.val = val;}
        ListNode(E val, ListNode<E> next) {
            this.val = val;
            this.next = next;
        }
    }

    static class CustomLinkedList<E> {
        private ListNode<E> head;
        private ListNode<E> tail;

        // добавить вначало
        public void addAtFirst(E val) {
            ListNode<E> temp = new ListNode<>(val);
            temp.next = head;
            head = temp;
            if(tail == null){tail = temp;}
        }

        // добавить в конец
        public void addAtLast(E val){
            if(tail==null){
                addAtFirst(val);
                return;
            }
            ListNode<E> temp = new ListNode<>(val);
            tail.next = temp;
            tail = temp;
        }

        // развернуть в обратную сторону
        public ListNode<E> reverseList(ListNode<E> headNode) {
            if(headNode==null) return headNode;
            ListNode temp = headNode.next;
            headNode.next = null;
            while(temp!=null){
                ListNode t = temp.next;
                temp.next = headNode;
                headNode = temp;
                temp = t;
            }
            return headNode;
        }
    }

    static void printLinkedList(ListNode head) {
        while (head!=null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
    }

    public static void main(String[] args) {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        for (int i=1; i<=5; i++) {
            list.addAtLast(i);
        }
        ListNode<Integer> head = list.head;
        printLinkedList(head);
        System.out.println();
        printLinkedList(list.reverseList(head));
    }

}
