package Custom_Impl.linkedListPalindrome;

public class PrintLinkedList {
    public static void printLinkedList(ListNode head) {
        while (head!=null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
    }
}
