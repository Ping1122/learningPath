class Solution {
    private ListNode tail;
    private ListNode head;
    public ListNode reverseList(ListNode head) {
        if (head == null) return head;
        if (head.next == null) {
            this.head = head;
            this.tail = head;
            return head;
        }
        reverseList(head.next);
        tail.next = head;
        head.next = null;
        tail = head;
        return this.head;
    }
}