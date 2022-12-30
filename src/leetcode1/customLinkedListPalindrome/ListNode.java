package leetcode1.customLinkedListPalindrome;

public class ListNode<E> {
    E val;
    ListNode<E> next;
    ListNode() {}
    ListNode(E val) {this.val = val;}
    ListNode(E val, ListNode<E> next) {
        this.val = val;
        this.next = next;
    }
}