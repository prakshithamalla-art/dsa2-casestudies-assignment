import java.util.*;

// ===== B+ Tree Section =====
class BPlusNode {
    int[] keys;
    int t;
    BPlusNode[] children;
    int n;
    boolean isLeaf;
    BPlusNode next;

    BPlusNode(int t, boolean isLeaf) {
        this.t = t;
        this.isLeaf = isLeaf;
        keys = new int[2 * t - 1];
        children = new BPlusNode[2 * t];
        n = 0;
    }
}

class BPlusTree {
    BPlusNode root;
    int t;

    BPlusTree(int t) {
        this.t = t;
        root = new BPlusNode(t, true);
    }

    void insert(int key) {
        BPlusNode r = root;
        if (r.n == 2 * t - 1) {
            BPlusNode s = new BPlusNode(t, false);
            s.children[0] = r;
            splitChild(s, 0, r);
            root = s;
            System.out.println("  Node split occurred at key " + key);
        }
        insertNonFull(root, key);
    }

    void insertNonFull(BPlusNode node, int key) {
        int i = node.n - 1;
        if (node.isLeaf) {
            while (i >= 0 && key < node.keys[i]) {
                node.keys[i + 1] = node.keys[i];
                i--;
            }
            node.keys[i + 1] = key;
            node.n++;
        } else {
            while (i >= 0 && key < node.keys[i]) i--;
            i++;
            if (node.children[i].n == 2 * t - 1) {
                splitChild(node, i, node.children[i]);
                if (key > node.keys[i]) i++;
            }
            insertNonFull(node.children[i], key);
        }
    }

    void splitChild(BPlusNode parent, int i, BPlusNode y) {
        BPlusNode z = new BPlusNode(t, y.isLeaf);
        z.n = t - 1;
        for (int j = 0; j < t - 1; j++) z.keys[j] = y.keys[j + t];
        if (!y.isLeaf)
            for (int j = 0; j < t; j++) z.children[j] = y.children[j + t];
        y.n = t - 1;
        for (int j = parent.n; j >= i + 1; j--) parent.children[j + 1] = parent.children[j];
        parent.children[i + 1] = z;
        for (int j = parent.n - 1; j >= i; j--) parent.keys[j + 1] = parent.keys[j];
        parent.keys[i] = y.keys[t - 1];
        parent.n++;
    }

    void rangeQuery(BPlusNode node, int low, int high) {
        if (node == null) return;
        if (node.isLeaf) {
            for (int i = 0; i < node.n; i++)
                if (node.keys[i] >= low && node.keys[i] <= high)
                    System.out.print("Rs." + node.keys[i] + "  ");
        } else {
            for (int i = 0; i < node.n; i++) {
                rangeQuery(node.children[i], low, high);
                if (node.keys[i] >= low && node.keys[i] <= high)
                    System.out.print("Rs." + node.keys[i] + "  ");
            }
            rangeQuery(node.children[node.n], low, high);
        }
    }
}

// ===== Segment Tree Section =====
class SegmentTree {
    int[] tree;
    int n;

    SegmentTree(int[] arr) {
        n = arr.length;
        tree = new int[4 * n];
        build(arr, 0, 0, n - 1);
    }

    void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        build(arr, 2 * node + 1, start, mid);
        build(arr, 2 * node + 2, mid + 1, end);
        tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
    }

    int query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) return 0;
        if (l <= start && end <= r) return tree[node];
        int mid = (start + end) / 2;
        return query(2 * node + 1, start, mid, l, r)
             + query(2 * node + 2, mid + 1, end, l, r);
    }
}

public class FoodChainCatalog {
    public static void main(String[] args) {

        // ---- B+ Tree: Menu Indexing by Price ----
        System.out.println("===== B+ TREE: MENU CATALOG INDEXING =====");
        BPlusTree bpt = new BPlusTree(3);
        int[] prices = {120, 85, 450, 300, 60, 550, 200, 175, 390, 499, 75, 320};
        String[] items = {"Paneer Wrap", "Veg Roll", "Chicken Biryani", "Fish Curry",
                          "Samosa", "Mutton Rogan", "Dal Makhani", "Egg Fried Rice",
                          "Prawn Masala", "Butter Chicken", "Tea", "Lamb Chops"};

        System.out.println("Inserting menu items by price:");
        for (int i = 0; i < prices.length; i++) {
            System.out.println("  Inserting: Rs." + prices[i] + " (" + items[i] + ")");
            bpt.insert(prices[i]);
        }

        System.out.println();
        System.out.println("Range Query - Items priced Rs.100 to Rs.500:");
        System.out.print("  Result: ");
        bpt.rangeQuery(bpt.root, 100, 500);
        System.out.println();

        // ---- Segment Tree: Peak Order Hours ----
        System.out.println();
        System.out.println("===== SEGMENT TREE: PEAK ORDER HOURS =====");
        // index 0=6AM, 1=7AM, ..., 6=12PM, 9=3PM, 12=6PM, 16=10PM
        int[] hourlyOrders = {5, 8, 15, 30, 45, 60, 80, 95, 70, 55, 40, 35,
                              50, 75, 90, 100, 85, 60, 30, 15, 10, 5, 3, 2};

        SegmentTree st = new SegmentTree(hourlyOrders);

        System.out.println("Query: Total orders from 12PM to 3PM (index 6-9):");
        int result1 = st.query(0, 0, hourlyOrders.length - 1, 6, 9);
        System.out.println("  Orders in 12PM-3PM window: " + result1);

        System.out.println("Query: Total orders from 6PM to 10PM (index 12-16):");
        int result2 = st.query(0, 0, hourlyOrders.length - 1, 12, 16);
        System.out.println("  Orders in 6PM-10PM window: " + result2);

        System.out.println("  Peak window: " + (result2 > result1 ? "6PM-10PM" : "12PM-3PM"));
        System.out.println();
        System.out.println("Time Complexity: B+ Tree O(log n), Range Query O(log n + k), Segment Tree O(log n)");
    }
}
