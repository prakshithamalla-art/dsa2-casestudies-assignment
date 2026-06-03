import java.util.*;

class AVLNode {
    int restaurantID;
    String restaurantName;
    int height;
    AVLNode left, right;

    AVLNode(int id, String name) {
        restaurantID = id;
        restaurantName = name;
        height = 1;
    }
}

class AVLTree {
    AVLNode root;

    int height(AVLNode node) {
        return (node == null) ? 0 : node.height;
    }

    int getBalance(AVLNode node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        System.out.println("  RR Rotation at pivot " + y.restaurantID);
        return x;
    }

    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        System.out.println("  LL Rotation at pivot " + x.restaurantID);
        return y;
    }

    AVLNode insert(AVLNode node, int id, String name) {
        if (node == null) return new AVLNode(id, name);
        if (id < node.restaurantID) node.left = insert(node.left, id, name);
        else if (id > node.restaurantID) node.right = insert(node.right, id, name);
        else return node;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        int balance = getBalance(node);

        // LL Case
        if (balance > 1 && id < node.left.restaurantID) return rightRotate(node);
        // RR Case
        if (balance < -1 && id > node.right.restaurantID) return leftRotate(node);
        // LR Case
        if (balance > 1 && id > node.left.restaurantID) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RL Case
        if (balance < -1 && id < node.right.restaurantID) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    void inorder(AVLNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.println("  ID: " + node.restaurantID + " -> " + node.restaurantName);
            inorder(node.right);
        }
    }

    boolean search(AVLNode node, int id) {
        if (node == null) return false;
        if (id == node.restaurantID) return true;
        if (id < node.restaurantID) return search(node.left, id);
        return search(node.right, id);
    }
}

public class FoodChainAVL {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        int[] ids = {101, 205, 150, 310, 89, 420, 175, 260, 330, 95};
        String[] names = {"PizzaHub", "BurgerKing", "SpiceGarden", "TacoStop",
                          "RiceBox", "NoodleHut", "SandwichCo", "GrillHouse",
                          "CurryPoint", "WrapZone"};

        System.out.println("AVL INSERTION (Registration Order)");
        System.out.println("Inserting Restaurant IDs: 101, 205, 150, 310, 89, 420, 175, 260, 330, 95");
        System.out.println("Rotations that occurred:");
        for (int i = 0; i < ids.length; i++) {
            tree.root = tree.insert(tree.root, ids[i], names[i]);
            System.out.println("  After inserting " + ids[i] +
                " -> Balance factor at root: " + tree.getBalance(tree.root));
        }

        System.out.println();
        System.out.println("SORTED RESTAURANT DIRECTORY (Inorder Traversal):");
        tree.inorder(tree.root);

        System.out.println();
        System.out.println("SEARCH RESULTS:");
        System.out.println("  Search R-150: " + (tree.search(tree.root, 150) ? "FOUND" : "NOT FOUND"));
        System.out.println("  Search R-999: " + (tree.search(tree.root, 999) ? "FOUND" : "NOT FOUND"));

        System.out.println();
        System.out.println("Time Complexity: Insert/Search/Delete = O(log n), Traversal = O(n)");
    }
}
