public class LinkedList {

    private Node head;

    private class Node {
        int data;
        Node next;
        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public boolean replaceData(int data, int index) {
        boolean res = true;
        Node curr = head;
        int in = 0;
        while (curr != null && in != index) {
            curr = curr.next;
        }
        if (curr == null) res = false;
        else curr.data = data;
        return res;
    }
}
