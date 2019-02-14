import java.util.Collection;
import java.util.List;

/**
 * Your implementation of a binary search tree.
 *
 * @author Bingyao Wang
 * @userid bwang404
 * @GTID 903406074
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no-argument constructor that should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Initializes the BST with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular for loop
     * will not work here. However, all Collections are Iterable, so what type
     * of loop would work?
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Empty set!");
        }
        for (T element: data) {
            if (data == null) {
                throw new IllegalArgumentException("Some data is empty!");
            }
            add(element);
        }
    }

    /**
     * Add the data as a leaf in the BST. Should traverse the tree to find the
     * appropriate location. If the data is already in the tree, then nothing
     * should be done (the duplicate shouldn't get added, and size should not be
     * incremented).
     * 
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @param data the data to be added
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data");
        }
        root = add(data, root);
        size++;
    }

    /**
     * add helper
     *
     * @param data data to be added
     * @param node node passed in
     * @return the right position to add the node
     */
    private BSTNode<T> add(T data, BSTNode<T> node) {
        if (node == null) {
            return new BSTNode<>(data);
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(add(data, node.getRight()));
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(add(data, node.getLeft()));
        } else {
            size--;
        }
        return node;
    }

    /**
     * Removes the data from the tree. There are 3 cases to consider:
     *
     * 1: the data is a leaf (no children). In this case, simply remove it.
     * 2: the data has one child. In this case, simply replace it with its
     * child.
     * 3: the data has 2 children. Use the predecessor to replace the data.
     * You MUST use recursion to find and remove the predecessor (you will
     * likely need an additional helper method to handle this case efficiently).
     *
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data the data to remove from the tree.
     * @return the data removed from the tree. Do not return the same data
     * that was passed in. Return the data that was stored in the tree.
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot remove null data");
        }
        BSTNode<T> dummy = new BSTNode<>(null);
        root = remove(data, root, dummy);
        size--;
        return dummy.getData();
    }

    /**
     * remove helper
     *
     * @param data data to be removed
     * @param node node pointer
     * @param dummy node to get data
     * @return the node
     */
    private BSTNode<T> remove(T data, BSTNode<T> node, BSTNode<T> dummy) {
        if (node == null) {
            throw new java.util.NoSuchElementException("Not in the tree");
        }
        if (node.getData().compareTo(data) == 0) {
            dummy.setData(node.getData());
            if (node.getLeft() != null && node.getRight() != null) {
                BSTNode<T> temp = new BSTNode<>(null);
                node.setLeft(pred(node.getLeft(), temp));
                node.setData(temp.getData());
            } else if (node.getLeft() != null) {
                return node.getLeft();
            } else if (node.getRight() != null) {
                return node.getRight();
            } else {
                return null;
            }
        } else if (node.getData().compareTo(data) > 0) {
            node.setLeft(remove(data, node.getLeft(), dummy));
        } else {
            node.setRight(remove(data, node.getRight(), dummy));
        }
        return node;
    }

    /**
     * predecessor helper
     *
     * @param node node pointer
     * @param temp dummy node to get data
     * @return the node passed in
     */
    private BSTNode<T> pred(BSTNode<T> node, BSTNode<T> temp) {
        if (node.getRight() == null) {
            temp.setData(node.getData());
            return node.getLeft();
        }
        node.setRight(pred(node.getRight(), temp));
        return node;
    }

    /**
     * Returns the data in the tree matching the parameter passed in (think
     * carefully: should you use value equality or reference equality?).
     *
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data the data to search for in the tree.
     * @return the data in the tree equal to the parameter. Do not return the
     * same data that was passed in.  Return the data that was stored in the
     * tree.
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot get null data");
        }
        return get(data, root);
    }

    /**
     * get helper
     *
     * @param data data
     * @param node node
     * @return the wanted node
     */
    private T get(T data, BSTNode<T> node) {
        if (node == null) {
            throw new java.util.NoSuchElementException("Data isn't found");
        } else if (data.compareTo(node.getData()) == 0) {
            return node.getData();
        } else if (data.compareTo(node.getData()) > 0) {
            return get(data, node.getRight());
        } else {
            return get(data, node.getLeft());
        }
    }

    /**
     * Returns whether or not data equivalent to the given parameter is
     * contained within the tree. The same type of equality should be used as
     * in the get method.
     *
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @param data the data to search for in the tree.
     * @return whether or not the parameter is contained within the tree.
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot contain null");
        }
        return contains(data, root);
    }

    /**
     * contains helper
     *
     * @param data data
     * @param node node
     * @return result whether it contains the node
     */
    private boolean contains(T data, BSTNode<T> node) {
        if (node == null) {
            return false;
        } else if (data.compareTo(node.getData()) == 0) {
            return true;
        } else if (data.compareTo(node.getData()) > 0) {
            return contains(data, node.getRight());
        } else {
            return contains(data, node.getLeft());
        }
    }

    /**
     * Should run in O(n).
     *
     * @return a preorder traversal of the tree
     */
    public List<T> preorder() {
        List<T> res = new java.util.ArrayList<T>();
        preorder(root, res);
        return res;
    }

    /**
     * preorder helper
     *
     * @param node node to start
     * @param list result
     */
    private void preorder(BSTNode<T> node, List<T> list) {
        if (node == null) {
            return;
        } else {
            list.add(node.getData());
            preorder(node.getLeft(), list);
            preorder(node.getRight(), list);
        }
    }

    /**
     * Should run in O(n).
     *
     * @return an inorder traversal of the tree
     */
    public List<T> inorder() {
        List<T> res = new java.util.ArrayList<>();
        inorder(root, res);
        return res;
    }

    /**
     * inorder helper
     *
     * @param node node to start
     * @param list result
     */
    private void inorder(BSTNode<T> node, List<T> list) {
        if (node == null) {
            return;
        } else {
            inorder(node.getLeft(), list);
            list.add(node.getData());
            inorder(node.getRight(), list);
        }
    }


    /**
     * Should run in O(n).
     *
     * @return a postorder traversal of the tree
     */
    public List<T> postorder() {
        List<T> res = new java.util.ArrayList<>();
        postorder(root, res);
        return res;
    }

    /**
     * postorder helper
     *
     * @param node node to start
     * @param list result
     */
    private void postorder(BSTNode<T> node, List<T> list) {
        if (node == null) {
            return;
        } else {
            postorder(node.getLeft(), list);
            postorder(node.getRight(), list);
            list.add(node.getData());
        }
    }

    /**
     * Generate a level-order traversal of the tree.
     *
     * To do this, add the root node to a queue. Then, while the queue isn't
     * empty, remove one node, add its data to the list being returned, and add
     * its left and right child nodes to the queue. If what you just removed is
     * {@code null}, ignore it and continue with the rest of the nodes.
     *
     * Should run in O(n). This does not need to be done recursively.
     *
     * @return a level order traversal of the tree
     */
    public List<T> levelorder() {
        return levelorderHelper(root);
    }

    /**
     * levelorder helper
     *
     * @param node input node
     * @return a list containing nodes
     */
    private List<T> levelorderHelper(BSTNode<T> node) {
        if (node == null) {
            return new java.util.ArrayList<>(0);
        }
        List<T> res = new java.util.ArrayList<>();
        java.util.Queue<BSTNode<T>> q = new java.util.LinkedList<>();
        q.add(node);
        while (!q.isEmpty()) {
            BSTNode<T> temp = q.poll();
            res.add(temp.getData());
            if (temp.getLeft() != null) {
                q.add(temp.getLeft());
            }
            if (temp.getRight() != null) {
                q.add(temp.getRight());
            }
        }
        return res;
    }

    /**
     * This method checks whether a binary tree meets the criteria for being
     * a binary search tree.
     *
     * This method is a static method that takes in a BSTNode called
     * {@code treeRoot}, which is the root of the tree that you should check.
     *
     * You may assume that the tree passed in is a proper binary tree; that is,
     * there are no loops in the tree, the parent-child relationship is
     * correct, that there are no duplicates, and that every parent has at
     * most 2 children. So, what you will have to check is that the order
     * property of a BST is still satisfied.
     *
     * Should run in O(n). However, you should stop the check as soon as you
     * find evidence that the tree is not a BST rather than checking the rest
     * of the tree.
     *
     * @param <T> the generic typing
     * @param treeRoot the root of the binary tree to check
     * @return true if the binary tree is a BST, false otherwise
     */
    public static <T extends Comparable<? super T>> boolean isBST(
            BSTNode<T> treeRoot) {
        return isBST(treeRoot, null, null);
    }

    /**
     * isBST helper
     *
     * @param node node
     * @param max the maximum value of the node
     * @param min the minimum value of the node
     * @param <T> generic typing
     * @return if the tree is BST
     */
    private static <T extends Comparable<? super T>> boolean isBST(
            BSTNode<T> node, T max, T min) {
        if (node == null) {
            return true;
        }
        boolean maxbool = true;
        boolean minbool = true;
        if (max == null) {
            maxbool = false;
        } else {
            maxbool = node.getData().compareTo(max) > 0;
        }
        if (min == null) {
            minbool = false;
        } else {
            minbool = node.getData().compareTo(min) < 0;
        }
        if (maxbool || minbool) {
            return false;
        }
        return isBST(node.getLeft(), node.getData(), min)
                && isBST(node.getRight(), max, node.getData());

    }


    /**
     * Clears the tree.
     *
     * Should run in O(1).
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Calculate and return the height of the root of the tree. A node's
     * height is defined as {@code max(left.height, right.height) + 1}. A leaf
     * node has a height of 0 and a null child should be -1.
     *
     * Should be calculated in O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return heightHelper(root);
    }

    /**
     * height helper
     *
     * @param node node
     * @return the height of the root
     */
    private int heightHelper(BSTNode<T> node) {
        if (node == null) {
            return -1;
        } else {
            int left = heightHelper(node.getLeft());
            int right = heightHelper(node.getRight());
            return 1 + (left > right ? left : right);
        }
    }

    /**
     * Returns the size of the BST.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the number of elements in the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Returns the root of the BST.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}