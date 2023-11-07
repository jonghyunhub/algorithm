package algo_middle_2.datastructure2;

class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class BST {
    Node root;

    BST() {
        this.root = null;
    }

    Node insert(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }
        if (data < node.data) {
            node.left = this.insert(node.left, data);
        } else {
            node.right = this.insert(node.right, data);
        }
        return node;
    }

    void insert(int data) {
        this.root = this.insert(this.root, data);
    }

    void inorder(Node node) {
        if (node == null) {
            return;
        }
        this.inorder(node.left);
        System.out.print(node.data + " ");
        this.inorder(node.right);
    }

    void inorder() {
        this.inorder(this.root);
        System.out.println();
    }

    Node inorder_successor(Node node) {
        Node p = node;
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    Node remove(Node node, int data) {
        if (node == null) {
            return node;
        }

        if (data < node.data) {
            node.left = this.remove(node.left, data);
        } else if (data > node.data) {
            node.right = this.remove(node.right, data);
        } else {
            if (node.left == null) {
                Node temp = node.right;
                node = null;
                return temp;
            } else if (node.right == null) {
                Node temp = node.left;
                node = null;
                return temp;
            }

            Node temp = this.inorder_successor(node.right);
            node.data = temp.data;
            node.right = this.remove(node.right, temp.data);
        }
        return node;
    }

    void remove(int data) {
        this.root = this.remove(this.root, data);
    }
}

public class BinarySearchTree {

    public static void main(String args[]) {
        BST bst = new BST();
        bst.insert(5);
        bst.insert(2);
        bst.insert(8);
        bst.insert(4);
        bst.insert(1);
        bst.insert(6);
        bst.insert(9);

        bst.inorder();

        bst.remove(2);
        bst.inorder();

        bst.remove(5);
        bst.inorder();
    }
}
