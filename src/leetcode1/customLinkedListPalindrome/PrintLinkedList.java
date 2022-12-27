package leetcode1.customLinkedListPalindrome;

import leetcode1.customLinkedList.Main;

public class PrintLinkedList {
    public static void printLinkedList(ListNode head) {
        while (head!=null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
    }
}
