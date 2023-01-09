package Custom_Impl.customLinkedListPalindrome;

public class PrintLinkedList {
    public static void printLinkedList(ListNode head) {
        while (head!=null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
    }
}
