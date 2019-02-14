public class BST {
    private BSTNode root;

    public int recursiveSum() {
        if (root == null) return 0;
        else return helper(root, root.data);
    }

    private int helper(BSTNode node, int sum) {
        if (node == null) return  sum;
        else return helper(node.left, sum + node.left.data) + helper(node.right,
                sum + node.right.data);
    }

    private class BSTNode {
        private int data;
        private BSTNode left;
        private BSTNode right;
    }

    public static void main(String[] args) {
        BST tree = new BST();
        
    }
}
