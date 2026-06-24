# 🍽️ FoodChain – Smart Food Delivery & Restaurant Logistics Optimization System
**Complete DSA-2 Case Study Documentation (All Course Outcomes)**

**Subject:** DSA-2 | **Department:** FED | **Academic Year:** I-III Semester (2025-26)  
**Institution:** Koneru Lakshmaiah Education Foundation, Hyderabad

---

## 📌 Project Overview

**FoodChain** is a comprehensive simulation of a smart food delivery and restaurant logistics management system. It models real-world scenarios using **6 distinct Course Outcomes (COs)**, each leveraging specific **Data Structures and Algorithms** to solve critical business problems.

### 🎯 Complete Module Map

| # | Module | Course Outcome | Problem Domain | Core Algorithm(s) | Time Complexity |
|---|--------|---|---|---|---|
| **1** | Restaurant Registration | CO1 | Restaurant indexing & search | AVL Tree | O(log n) |
| **2** | Menu Catalog & Analytics | CO2 | Price range queries & peak hours | B+ Tree + Segment Tree | O(log n + k) / O(log n) |
| **3** | Delivery Zone Mapping | CO3 | Route discovery & hub network | BFS + DFS + Prim's | O(V+E) / O(V²) |
| **4** | Route Optimization | CO4 | Shortest delivery paths | Dijkstra + Bellman-Ford + Floyd-Warshall | O(V²) / O(VE) / O(V³) |
| **5** | Order Processing | CO5 | Efficient sorting & analytics | Heap Sort + Counting Sort + Radix Sort | O(n log n) / O(n+k) / O(n×d) |
| **6** | Resource Optimization | CO6 | Revenue maximization & pattern matching | Knapsack + LCS + Matrix Chain | O(nW) / O(m×n) / O(n³) |

---

---

# 🌳 CO1 – Restaurant Registration & Search Index using AVL Tree

## 📋 Problem Statement

FoodChain onboards hundreds of restaurants daily. Each restaurant has a unique RestaurantID. The system must support:

- ✅ **Fast insertion** of new restaurants
- ✅ **Quick search** by RestaurantID
- ✅ **Efficient deletion** of inactive restaurants
- ✅ **Automatic sorting** for restaurant directory

A plain **Binary Search Tree (BST)** can degrade to **O(n)** in the worst case (skewed tree). An **AVL Tree** maintains perfect balance through rotations, guaranteeing **O(log n)** for all operations.

## 🔧 Data Structure Used

**AVL Tree** – Self-Balancing Binary Search Tree

### How It Works

1. **Insertion** – Insert like normal BST, then check balance factors
2. **Balance Factor** – For each node: `BF = height(left) - height(right)`
3. **Rebalancing** – If `|BF| > 1`, perform one of 4 rotations:
   - **LL Rotation** – Inserted into left subtree of left child
   - **RR Rotation** – Inserted into right subtree of right child
   - **LR Rotation** – Inserted into right subtree of left child
   - **RL Rotation** – Inserted into left subtree of right child
4. **Inorder Traversal** – Produces sorted restaurant directory automatically
5. **Search** – O(log n) guaranteed due to balanced height

## 📊 Sample Data

| RestaurantID | Name | City | Rating |
|---|---|---|---|
| 89 | WrapZone | Hyd | 4.2 |
| 95 | RiceBox | Bangalore | 4.5 |
| 101 | PizzaHub | Hyderabad | 4.8 |
| 150 | SpiceGarden | Chennai | 4.3 |
| 175 | SandwichCo | Pune | 4.1 |
| 205 | BurgerKing | Mumbai | 4.6 |
| 260 | GrillHouse | Hyderabad | 4.4 |
| 310 | TacoStop | Bangalore | 4.0 |
| 330 | CurryPoint | Delhi | 4.7 |
| 420 | NoodleHut | Kolkata | 4.2 |

### AVL Tree Visualization After All Insertions

```
           205
          /   \
        101   310
       /  \   /  \
      95  150 260 330
     /    /
    89  175
```

## ⏱️ Time Complexity

| Operation | Complexity | Explanation |
|-----------|---|---|
| **Insert** | O(log n) | Find + Insert + Rotate |
| **Search** | O(log n) | Balanced tree height = log n |
| **Delete** | O(log n) | Find + Delete + Rebalance |
| **Inorder Traversal** | O(n) | Visit each node once |

## 📈 Why AVL Over Plain BST?

| Scenario | BST | AVL |
|----------|-----|-----|
| Sequential insertion (1,2,3,4,5) | O(n) – Skewed | O(log n) – Balanced |
| Random insertion | O(log n) avg | O(log n) guaranteed |
| Worst-case search | O(n) | O(log n) |
| Memory overhead | None | Balance factors only |

## ▶️ How to Run

```bash
javac FoodChainAVL.java
java FoodChainAVL
```

---

# 🗂️ CO2 – Menu Catalog Indexing & Demand Analytics using B+ Tree & Segment Tree

## 📋 Problem Statement

FoodChain lists thousands of menu items across hundreds of restaurants. The system must:

1. **Range Queries** – "Show all dishes between Rs.100 and Rs.500"
2. **Peak Hour Analytics** – "What hour had the most orders?"
3. **Fast Insertion/Deletion** – Handle dynamic menu updates

**Two separate structures solve these problems:**
- **B+ Tree** → Efficient price-based range queries
- **Segment Tree** → Fast range sum queries on hourly order frequency

## 🔧 Data Structures Used

### B+ Tree (Order 3)
- Inner nodes store keys (routing only)
- Leaf nodes store actual data
- All leaves linked for sequential access
- Perfect for range queries

### Segment Tree
- Binary tree built over array
- Each node stores sum/max of range
- Fast O(log n) range queries

## ⚙️ How It Works

### B+ Tree – Menu Catalog

```
Tree Structure (Order 3):
         [200|400]
        /    |    \
    [60 75 85]  [120 175]  [250 300|320 390 450 499]
    
Data stored only in leaves
Leaves linked: 60→75→85→120→...→550
```

**Range Query [100, 500]:**
1. Search for first key ≥ 100 (find 120)
2. Follow linked leaf nodes collecting all values
3. Stop when key > 500
4. Return: [120, 175, 200, 300, 320, 390, 450, 499]

### Segment Tree – Peak Hours

```
Array: [8, 15, 22, 18, 25, 30, 20, ...]  (orders per hour)

Segment Tree:
           328 (sum of all)
          /    \
       63        265
      / \       /   \
    23  40    67   198
   / \ / \   / \   / \
  8 15 22 18 25 30 20 ...

Range Query [10:14]: sum of 25+30+20 = 75 orders
```

## 📊 Sample Data

### Menu Items (Prices in Rs.)

| Item | Price | Category |
|---|---|---|
| Samosa | 60 | Snack |
| Tea | 75 | Beverage |
| Veg Roll | 85 | Snack |
| Paneer Wrap | 120 | Main |
| Egg Fried Rice | 175 | Main |
| Dal Makhani | 200 | Main |
| Fish Curry | 300 | Main |
| Lamb Chops | 320 | Main |
| Prawn Masala | 390 | Premium |
| Chicken Biryani | 450 | Premium |
| Butter Chicken | 499 | Premium |
| Mutton Rogan | 550 | Premium |

**Range Query: Rs.100 – Rs.500**
```
Result: [120, 175, 200, 300, 320, 390, 450, 499]
Count: 8 items
```

### Hourly Orders (Segment Tree)

| Hour | Orders | Period |
|---|---|---|
| 6 AM | 8 | Morning |
| 7 AM | 15 | Morning |
| 12 PM | 22 | Lunch |
| 1 PM | 25 | Lunch |
| 2 PM | 30 | Lunch |
| 6 PM | 35 | Dinner |
| 7 PM | 40 | Dinner |
| 8 PM | 28 | Dinner |

**Range Query: Lunch Peak (12 PM - 2 PM) = 22 + 25 + 30 = 77 orders**

## ⏱️ Time Complexity

| Operation | B+ Tree | Segment Tree |
|-----------|---------|---|
| **Insert** | O(log n) | O(log n) |
| **Delete** | O(log n) | O(log n) |
| **Range Query** | O(log n + k) | O(log n) |
| **Build** | O(n log n) | O(n) |

## 📈 B+ Tree vs Hash Table for Range

| Scenario | B+ Tree | Hash Table |
|----------|---------|---|
| Range query [100-500] | ✅ O(log n + k) | ❌ O(n) – scan all |
| Point query (price=300) | ✅ O(log n) | ✅ O(1) |
| Sorted output | ✅ Native linked | ❌ Need sorting |
| Insert ordered data | ✅ Efficient | ❌ Resizing |

## ▶️ How to Run

```bash
javac FoodChainCatalog.java
java FoodChainCatalog
```

---

# 🚚 CO3 – Delivery Zone Mapping & Hub Network using BFS, DFS & Prim's MST

## 📋 Problem Statement

FoodChain operates across multiple delivery zones in a city. The system must:

1. **Find nearest delivery zone** from a restaurant (BFS)
2. **Verify all zones are reachable** from central hub (DFS)
3. **Build minimum cost road network** for all hubs/zones (Prim's MST)

## 🔧 Algorithms Used

- **BFS** – Breadth-First Search (nearest zone discovery)
- **DFS** – Depth-First Search (connectivity verification)
- **Prim's Algorithm** – Minimum Spanning Tree (hub network optimization)

## ⚙️ How It Works

### 1️⃣ BFS – Nearest Delivery Zone

```
Start from Restaurant_A
Level 0: [Restaurant_A]
Level 1: [Hub_H1, Hub_H2]
Level 2: [Zone_1, Zone_2, Zone_3, Zone_4, Zone_5]
         ↑ First zone found → NEAREST ZONE

Distance: 2 hops
```

**Algorithm:**
1. Queue = [Restaurant_A]
2. Visit each node level by level
3. Return first zone node encountered
4. Guarantees minimum hops (not distance)

### 2️⃣ DFS – Connectivity Check

```
Start from Hub_H1, mark visited nodes:
Hub_H1 → Hub_H2 → Zone_3 → Zone_4 → Zone_5
  ↓          ↓
Zone_2     Zone_6
  ↓
Zone_1

Visited: 10 nodes
Total nodes: 10
Status: ✅ FULLY CONNECTED
```

**Algorithm:**
1. DFS from central hub
2. Recursively visit all connected nodes
3. Count visited nodes
4. If visited == total → network is connected

### 3️⃣ Prim's MST – Minimum Hub Network

```
Edge Selection (weights in km):
1. (Restaurant_A, Hub_H1): 4 km ✅
2. (Hub_H1, Hub_H2): 2 km ✅
3. (Hub_H2, Zone_3): 4 km ✅
4. (Hub_H2, Zone_4): 3 km ✅
5. (Zone_4, Hub_H3): 2 km ✅
... (continue until all nodes connected)

Total cost: 29 km (minimum among all spanning trees)
Used: 9 edges (V-1 for 10 nodes)
```

**Algorithm:**
1. Start with arbitrary node
2. Greedily select minimum weight edge connecting a new node
3. Repeat until all V nodes included
4. Result: minimum cost spanning tree

## 📊 Network Graph

### Nodes (10 total)
```
Nodes:
  0: Restaurant_A (origin)
  1: Hub_H1
  2: Hub_H2
  3: Hub_H3
  4: Zone_1
  5: Zone_2
  6: Zone_3
  7: Zone_4
  8: Zone_5
  9: Zone_6
```

### Network Connectivity

```
Restaurant_A ----4km---- Hub_H1 ----2km---- Hub_H2
     |                       |                  |
    7km                     5km               4km
     |                       |                  |
  Zone_1                  Zone_2            Zone_3
     |                       |                  |
    5km                     4km               6km
     |                       |                  |
  Zone_5 -------3km------- Zone_6             Zone_4
                                               |
                                             2km
                                               |
                                             Hub_H3
```

### Adjacency Matrix (14 edges)

```
    0  1  2  3  4  5  6
0 [ 0  4  ∞  ∞  7  ∞  ∞ ]
1 [ 4  0  2  ∞  5  ∞  ∞ ]
2 [ ∞  2  0  4  ∞  ∞  4 ]
3 [ ∞  ∞  4  0  ∞  ∞  3 ]
4 [ 7  5  ∞  ∞  0  5  ∞ ]
5 [ ∞  ∞  ∞  ∞  5  0  3 ]
6 [ ∞  ∞  4  3  ∞  3  0 ]
```

## 📈 Expected Outputs

### BFS Output
```
Nearest Delivery Zone: Zone_1 (Level 2)
Distance: 2 hops
Path: Restaurant_A → Hub_H1 → Zone_1
```

### DFS Output
```
Connectivity Check: ✅ FULLY CONNECTED
Visited Nodes: 10/10
All delivery zones reachable from Hub_H1
```

### Prim's MST Output
```
Minimum Spanning Tree (9 edges):
  0-1: 4 km
  1-2: 2 km
  2-3: 4 km
  2-6: 4 km
  3-7: 3 km
  1-4: 5 km
  4-5: 5 km
  5-6: 3 km
  
Total Cost: 29 km
```

## ⏱️ Time Complexity

| Algorithm | Complexity | When to Use |
|-----------|---|---|
| **BFS** | O(V + E) | Shortest hop distance |
| **DFS** | O(V + E) | Connectivity check |
| **Prim's MST** | O(V²) | Dense graph, small V |

## 📈 Prim's vs Kruskal's

| Aspect | Prim's | Kruskal's |
|--------|--------|---|
| Edge selection | Minimum from connected nodes | Minimum from all edges |
| Data structure | Adjacency matrix/Priority queue | Union-Find |
| Time | O(V²) | O(E log E) |
| Space | O(V) | O(E) |
| Better for | Dense graphs | Sparse graphs |

## ▶️ How to Run

```bash
javac FoodChainDelivery.java
java FoodChainDelivery
```

---

# 🚗 CO4 – Delivery Route Optimization using Shortest Path Algorithms

## 📋 Problem Statement

FoodChain's delivery network must determine optimal routes under different constraints:

1. **Fastest route** with normal road distances (Dijkstra's)
2. **Optimal path** with dynamic pricing/discounts (Bellman-Ford)
3. **All shortest routes** for complete logistics planning (Floyd-Warshall)

Three algorithms for three different real-world scenarios.

## 🔧 Algorithms Used

### 1️⃣ Dijkstra's Algorithm
**Time Complexity:** O(V²) with adjacency matrix  
**Space Complexity:** O(V)  
**Best For:** Single-source shortest paths with non-negative weights

**How It Works:**
```
dist[v] = minimum distance from source to v
visited[v] = whether v has been finalized

1. Initialize dist[src]=0, all others=∞
2. Repeat V-1 times:
     a. Pick unvisited node u with minimum dist
     b. Mark u as visited
     c. For each neighbor v of u:
        dist[v] = min(dist[v], dist[u] + weight(u,v))
```

**Example:**
```
Shortest path from Node 0:
  Node 0: 0 km (source)
  Node 1: 4 km (via 0→1)
  Node 2: 6 km (via 0→1→2)
  Node 3: 9 km (via 0→1→2→3)
  Node 4: 10 km (via 0→1→2→3→4)
```

### 2️⃣ Bellman-Ford Algorithm
**Time Complexity:** O(VE)  
**Space Complexity:** O(V)  
**Best For:** Routes with discounts (negative weights) and cycle detection

**How It Works:**
```
1. Initialize dist[src]=0, all others=∞
2. Relax all edges V-1 times:
     For each edge (u,v,w):
       if dist[u] + w < dist[v]:
         dist[v] = dist[u] + w
3. Check for negative cycles:
     For each edge (u,v,w):
       if dist[u] + w < dist[v]: negative cycle!
```

**Advantage:** Handles negative weights (discounts)

### 3️⃣ Floyd-Warshall Algorithm
**Time Complexity:** O(V³)  
**Space Complexity:** O(V²)  
**Best For:** All-pairs shortest paths (complete preprocessing)

**How It Works:**
```
dist[i][j] = shortest distance from i to j

For k = 0 to V-1:
  For i = 0 to V-1:
    For j = 0 to V-1:
      dist[i][j] = min(dist[i][j], 
                       dist[i][k] + dist[k][j])
```

**Key Insight:** Check if path through intermediate node k is better

## 📊 Sample Data & Graph

```
Network (5 hubs):
    0 = Restaurant_A
    1 = Hub_H1
    2 = Hub_H2
    3 = Hub_H3
    4 = Hub_H4

Distances (km):
       0   1   2   3   4
   0 [ 0   4   ∞   ∞   7 ]
   1 [ 4   0   2   ∞   ∞ ]
   2 [ ∞   2   0   3   ∞ ]
   3 [ ∞   ∞   3   0   1 ]
   4 [ 7   ∞   ∞   1   0 ]
```

## 📈 Expected Outputs

### Dijkstra (Single-Source from Node 0)
```
Distance to Node 0 = 0
Distance to Node 1 = 4
Distance to Node 2 = 6
Distance to Node 3 = 9
Distance to Node 4 = 10

Shortest route: 0→1→2→3→4 = 10 km
```

### Bellman-Ford (Single-Source from Node 0)
```
Distance to Node 0 = 0
Distance to Node 1 = 4
Distance to Node 2 = 6
Distance to Node 3 = 9
Distance to Node 4 = 10

Same as Dijkstra (no negative weights)
```

### Floyd-Warshall (All-Pairs)
```
Distance Matrix:
    0   1   2   3   4
0 [ 0   4   6   9  10 ]
1 [ 4   0   2   5   6 ]
2 [ 6   2   0   3   4 ]
3 [ 9   5   3   0   1 ]
4 [10   6   4   1   0 ]

Example: Distance from Hub_H2 (2) to Hub_H4 (4) = 4 km
Path: 2→3→4
```

## ⏱️ Algorithm Comparison

| Aspect | Dijkstra | Bellman-Ford | Floyd-Warshall |
|--------|----------|---|---|
| **Single-source shortest** | ✅ O(V²) | ✅ O(VE) | ❌ Overkill |
| **Negative weights** | ❌ Fails | ✅ Works | ✅ Works |
| **All-pairs shortest** | ❌ Repeat V times | ❌ Repeat V times | ✅ O(V³) |
| **Detect negative cycle** | ❌ No | ✅ Yes | ❌ No |
| **Dense graphs** | ✅ O(V²) | ❌ O(VE) slow | ✅ O(V³) ok |
| **Sparse graphs** | ❌ O(V²) waste | ✅ O(VE) better | ❌ O(V³) waste |

## 🎯 When to Use Which

| Scenario | Algorithm | Reason |
|----------|-----------|--------|
| One restaurant to all hubs | Dijkstra | O(V²), fast, standard |
| Routes with express pricing (negative) | Bellman-Ford | Handles negative |
| Hub-to-hub pre-routing table | Floyd-Warshall | All-pairs preprocessing |
| Detect pricing loops/anomalies | Bellman-Ford | Negative cycle detection |

## ▶️ How to Run

```bash
javac FoodChainCO4.java
java FoodChainCO4
```

---

# 📦 CO5 – Efficient Order Processing using Sorting Algorithms

## 📋 Problem Statement

FoodChain processes thousands of orders hourly. The platform must:

1. **Sort delivery priorities** by importance level (Heap Sort)
2. **Group orders by rating** (1-5 stars) for analytics (Counting Sort)
3. **Sort order IDs** (5-digit numbers) for batch processing (Radix Sort)

## 🔧 Algorithms Used

### 1️⃣ Heap Sort
**Time Complexity:** O(n log n) all cases  
**Space Complexity:** O(1) – in-place  
**Best For:** Priority sorting, guaranteed performance

**How It Works:**
```
Step 1: Build max-heap from array
        [9, 5, 7, 2, 8, 3]
           9
         /   \
        8     7
       / \   /
      2   5 3

Step 2: Extract root, heapify remainder
        Extract 9 → [9, 5, 7, 2, 8, 3]
        Heap: 8, 5, 7, 2, 3
        ...
        
Step 3: Result in ascending order [2, 3, 5, 7, 8, 9]
```

**Heapify Operation:**
```
For parent at index i:
  Left child = 2*i + 1
  Right child = 2*i + 2
  Find largest among parent and children
  Swap and recurse if needed
```

### 2️⃣ Counting Sort
**Time Complexity:** O(n + k) where k = max value  
**Space Complexity:** O(k)  
**Best For:** Small integer ranges (1-5 stars)

**How It Works:**
```
Input: [5, 3, 4, 5, 2, 1, 5, 4]

Step 1: Create count array
        count[1]=1, count[2]=1, count[3]=1, count[4]=2, count[5]=3

Step 2: Reconstruct from counts
        Add 1 × count[1] → [1]
        Add 1 × count[2] → [1, 2]
        Add 1 × count[3] → [1, 2, 3]
        Add 2 × count[4] → [1, 2, 3, 4, 4]
        Add 3 × count[5] → [1, 2, 3, 4, 4, 5, 5, 5]

Output: [1, 2, 3, 4, 4, 5, 5, 5]
```

**Constraint:** Only non-negative integers

### 3️⃣ Radix Sort
**Time Complexity:** O(n × d) where d = digits  
**Space Complexity:** O(n + k)  
**Best For:** Multi-digit numbers (order IDs)

**How It Works:**
```
Input: [10234, 56890, 23145, 99876, 12345]

Digit-by-digit sorting:
Sort by 1s place:   [10234, 56890, 23145, 99876, 12345]
Sort by 10s place:  [23145, 99876, 10234, 56890, 12345]
Sort by 100s place: [10234, 12345, 23145, 56890, 99876]
Sort by 1000s:      [10234, 12345, 23145, 56890, 99876]
Sort by 10000s:     [10234, 12345, 23145, 56890, 99876]

Output: [10234, 12345, 23145, 56890, 99876]
```

**Key:** Uses counting sort for each digit position

## 📊 Sample Data

### Test Case 1: Delivery Priorities
```
Input:  [9, 5, 7, 2, 8, 3]
Output: [2, 3, 5, 7, 8, 9]
Context: Priority 9 = urgent, Priority 2 = standard
```

### Test Case 2: Order Ratings
```
Input:  [5, 3, 4, 5, 2, 1, 5, 4]
Output: [1, 2, 3, 4, 4, 5, 5, 5]
Context: 5-star items get featured placement (3 orders)
```

### Test Case 3: Order IDs
```
Input:  [10234, 56890, 23145, 99876, 12345]
Output: [10234, 12345, 23145, 56890, 99876]
Context: Sequential ID processing for batch operations
```

## ⏱️ Time Complexity Comparison

| Algorithm | Best | Average | Worst | Space | Stable | In-Place |
|-----------|------|---------|-------|-------|--------|----------|
| **Heap Sort** | O(n log n) | O(n log n) | O(n log n) | O(1) | ❌ | ✅ |
| **Counting Sort** | O(n+k) | O(n+k) | O(n+k) | O(k) | ✅ | ❌ |
| **Radix Sort** | O(n×d) | O(n×d) | O(n×d) | O(n) | ✅ | ❌ |

## 🎯 Algorithm Selection Guide

| Scenario | Best Choice | Why |
|----------|-------------|-----|
| Sort priorities (any range) | Heap Sort | O(n log n), in-place, guaranteed |
| Group by 1-5 star ratings | Counting Sort | O(n) on small range |
| Sort 5-digit order IDs | Radix Sort | O(n×5) < O(n log n) |
| Memory-constrained system | Heap Sort | O(1) extra space |
| Need stability | Radix Sort | Maintains equal order |
| Already partially sorted | Heap Sort | Still O(n log n) |

## ▶️ How to Run

```bash
javac FoodChainCO5.java
java FoodChainCO5
```

**Expected Output:**
```
Heap Sort:
[2, 3, 5, 7, 8, 9]

Counting Sort:
[1, 2, 3, 4, 4, 5, 5, 5]

Radix Sort:
[10234, 12345, 23145, 56890, 99876]
```

---

# 💰 CO6 – Resource Optimization using Dynamic Programming

## 📋 Problem Statement

FoodChain faces three resource optimization challenges:

1. **Menu Bundling** – Maximize revenue within delivery capacity (Knapsack)
2. **Customer Matching** – Find common preferences (Longest Common Subsequence)
3. **Batch Processing** – Minimize computations (Matrix Chain Multiplication)

All three are classic **dynamic programming** problems.

## 🔧 Algorithms Used

### 1️⃣ 0/1 Knapsack Problem
**Time Complexity:** O(n × W)  
**Space Complexity:** O(n × W)  
**Best For:** Bounded optimization

**Problem:**
```
Given:
  - n items with values and weights
  - Knapsack capacity W
  - Each item: 0 or 1 (can't split)

Find:
  Maximum value with total weight ≤ W
```

**Recurrence:**
```
dp[i][w] = maximum value using first i items, weight limit w

If we include item i:
  dp[i][w] = val[i] + dp[i-1][w-wt[i]]
  
If we exclude item i:
  dp[i][w] = dp[i-1][w]

dp[i][w] = max(include, exclude)
```

**Example:**
```
Items:
  Item 1: Value ₹300, Weight 4 kg
  Item 2: Value ₹250, Weight 3 kg
  Item 3: Value ₹500, Weight 6 kg
  Item 4: Value ₹150, Weight 2 kg

Capacity: 15 kg

DP Table:
        w=0  ...  w=9  ...  w=15
    i=0  0        0         0
    i=1  0       300       300
    i=2  0       300       550
    i=3  0       300       500
    i=4  0       150       650 ✓

Maximum Revenue: ₹650
Selected: Items 1, 2, 4 (weights: 4+3+2=9 kg)
```

### 2️⃣ Longest Common Subsequence (LCS)
**Time Complexity:** O(m × n)  
**Space Complexity:** O(m × n)  
**Best For:** Pattern matching

**Problem:**
```
Given: Two sequences X and Y
Find: Length of longest subsequence common to both

Note: Subsequence ≠ Substring (doesn't need to be contiguous)

Example:
  X = "PIZZAWRAPBIRYANI"
  Y = "PIZZAWRAP"
  LCS = "PIZZAWRAP" (length 9)
```

**Recurrence:**
```
If X[i] == Y[j]:
  dp[i][j] = dp[i-1][j-1] + 1   (include character)
  
Else:
  dp[i][j] = max(
    dp[i-1][j],   (skip X[i])
    dp[i][j-1]    (skip Y[j])
  )
```

**DP Table:**
```
        ""  P  I  Z  Z  A  W  R  A  P
    ""   0  0  0  0  0  0  0  0  0  0
    P    0  1  1  1  1  1  1  1  1  1
    I    0  1  2  2  2  2  2  2  2  2
    Z    0  1  2  3  3  3  3  3  3  3
    Z    0  1  2  3  4  4  4  4  4  4
    A    0  1  2  3  4  5  5  5  5  5
    W    0  1  2  3  4  5  6  6  6  6
    R    0  1  2  3  4  5  6  7  7  7
    A    0  1  2  3  4  5  6  7  8  8
    P    0  1  2  3  4  5  6  7  8  9 ✓

LCS Length = 9
```

### 3️⃣ Matrix Chain Multiplication
**Time Complexity:** O(n³)  
**Space Complexity:** O(n²)  
**Best For:** Preprocessing optimal evaluation order

**Problem:**
```
Given: Sequence of matrices A₁, A₂, ..., Aₙ
Find: Minimum scalar multiplications needed

Note: (A×B)×C ≠ A×(B×C) in operation count!
```

**Matrix Multiplication Cost:**
```
If A = (p × q) and B = (q × r):
  Scalar multiplications = p × q × r
  Result = (p × r) matrix
```

**Example:**
```
Matrices: A₁(10×20), A₂(20×30), A₃(30×40)

Option 1: (A₁ × A₂) × A₃
  A₁ × A₂ = 10×20×30 = 6,000 ops
  Result × A₃ = 10×30×40 = 12,000 ops
  Total = 18,000 ✓ OPTIMAL

Option 2: A₁ × (A₂ × A₃)
  A₂ × A₃ = 20×30×40 = 24,000 ops
  A₁ × Result = 10×20×40 = 8,000 ops
  Total = 32,000 (77% more!)
```

**Recurrence:**
```
dp[i][j] = minimum cost to multiply matrices i through j

For length L = 2 to n:
  For start position i:
    j = i + L - 1
    dp[i][j] = min(
      dp[i][k] + dp[k+1][j] + p[i-1]×p[k]×p[j]
    ) for all k ∈ [i, j-1]

p = [10, 20, 30, 40] (dimension array)
```

**DP Table:**
```
      1     2     3
  1   0   6000  18000 ✓
  2       0    24000
  3            0

Optimal: Split at position 1
(A₁×A₂)×A₃ with cost 18,000
```

## 📊 Sample Data & Results

### Test Case 1: Menu Bundling (Knapsack)
```
Restaurant Inventory:
  Dish 1: Revenue ₹300, Weight 4 kg
  Dish 2: Revenue ₹250, Weight 3 kg
  Dish 3: Revenue ₹500, Weight 6 kg
  Dish 4: Revenue ₹150, Weight 2 kg

Delivery Capacity: 15 kg

Result:
  Selected: Items 1, 2, 4
  Total Weight: 9 kg
  Maximum Revenue: ₹700 ✅
```

### Test Case 2: Customer Preference (LCS)
```
CustomerA Orders: "PIZZAWRAPBIRYANI"
CustomerB Orders: "PIZZAWRAP"

LCS Length: 9
Common Items: [P, I, Z, Z, A, W, R, A, P]

Recommendation: Bundle "PIZZAWRAP" combo
```

### Test Case 3: Batch Processing (Matrix Chain)
```
Matrix Dimensions: [10, 20, 30, 40]

Minimum Operations: 18,000
Optimal Order: (A₁ × A₂) × A₃
Savings vs naive: 77% reduction
```

## ⏱️ Time Complexity Comparison

| Algorithm | Time | Space | Approach | Best For |
|-----------|------|-------|----------|----------|
| **Knapsack** | O(nW) | O(nW) | Fill DP table | Capacity constraints |
| **LCS** | O(m×n) | O(m×n) | Fill DP table | String matching |
| **Matrix Chain** | O(n³) | O(n²) | Fill DP table | Chain optimization |

## 🎯 Dynamic Programming Pattern

All three problems follow the **DP framework:**

```
1. Define subproblems
   Knapsack: Max value with weight limit w using first i items
   LCS: Length of LCS of X[0..i] and Y[0..j]
   Matrix Chain: Min cost to multiply matrices i through j

2. Write recurrence relation
   Knapsack: include/exclude item
   LCS: characters match or skip
   Matrix Chain: split position k

3. Build DP table bottom-up
   Base cases: dp[0][...] = 0
   Fill in order: smaller subproblems first

4. Extract answer
   Knapsack: dp[n][W]
   LCS: dp[m][n]
   Matrix Chain: dp[1][n-1]

5. Reconstruct solution (optional)
   Backtrack through DP table
```

## ▶️ How to Run

```bash
javac FoodChainCO6.java
java FoodChainCO6
```

**Expected Output:**
```
Knapsack Revenue = ₹700
LCS Length = 9
Minimum Multiplications = 18000
```

---

---

# 📚 Complete Project Structure

```
FoodChain/
│
├── CO1_Restaurant_Registration/
│   ├── FoodChainAVL.java          # AVL Tree implementation
│   └── README_CO1.md
│
├── CO2_Menu_Catalog/
│   ├── FoodChainCatalog.java      # B+ Tree + Segment Tree
│   └── README_CO2.md
│
├── CO3_Delivery_Zones/
│   ├── FoodChainDelivery.java     # BFS + DFS + Prim's
│   └── README_CO3.md
│
├── CO4_Route_Optimization/
│   ├── FoodChainCO4.java          # Dijkstra + Bellman-Ford + Floyd-Warshall
│   └── README_CO4.md
│
├── CO5_Order_Processing/
│   ├── FoodChainCO5.java          # Heap Sort + Counting Sort + Radix Sort
│   └── README_CO5.md
│
├── CO6_Resource_Optimization/
│   ├── FoodChainCO6.java          # Knapsack + LCS + Matrix Chain
│   └── README_CO6.md
│
└── README.md (this file)
```

---

# 🛠️ How to Compile & Run All Modules

## Prerequisites
- Java JDK 8 or higher
- Any IDE or command line

## Compilation

```bash
# Compile all modules
javac FoodChainAVL.java
javac FoodChainCatalog.java
javac FoodChainDelivery.java
javac FoodChainCO4.java
javac FoodChainCO5.java
javac FoodChainCO6.java
```

## Execution

```bash
# Run CO1 - Restaurant Registration
java FoodChainAVL

# Run CO2 - Menu Catalog
java FoodChainCatalog

# Run CO3 - Delivery Zones
java FoodChainDelivery

# Run CO4 - Route Optimization
java FoodChainCO4

# Run CO5 - Order Processing
java FoodChainCO5

# Run CO6 - Resource Optimization
java FoodChainCO6
```

## Master Compilation Script

```bash
#!/bin/bash
echo "Compiling FoodChain project..."
javac FoodChainAVL.java FoodChainCatalog.java FoodChainDelivery.java \
      FoodChainCO4.java FoodChainCO5.java FoodChainCO6.java

echo "Compilation complete!"
echo "Run modules with: java FoodChainAVL, java FoodChainCO4, etc."
```

---

# 📊 Complete Complexity Summary

| # | CO | Algorithm(s) | Main Operation | Time | Space |
|---|----|----|---|---|---|
| **1** | Restaurant Index | AVL Tree | Insert/Search/Delete | O(log n) | O(n) |
| **2** | Menu Catalog | B+ Tree | Range Query | O(log n + k) | O(n) |
| **2** | Peak Analytics | Segment Tree | Range Sum | O(log n) | O(n) |
| **3** | Nearest Zone | BFS | Graph Traversal | O(V+E) | O(V) |
| **3** | Connectivity | DFS | Graph Traversal | O(V+E) | O(V) |
| **3** | Hub Network | Prim's MST | Spanning Tree | O(V²) | O(V) |
| **4** | Single-Source Path | Dijkstra | Shortest Path | O(V²) | O(V) |
| **4** | Negative Weights | Bellman-Ford | Shortest Path | O(VE) | O(V) |
| **4** | All-Pairs Path | Floyd-Warshall | Shortest Path | O(V³) | O(V²) |
| **5** | Priority Sort | Heap Sort | Sorting | O(n log n) | O(1) |
| **5** | Rating Group | Counting Sort | Sorting | O(n+k) | O(k) |
| **5** | ID Sort | Radix Sort | Sorting | O(n×d) | O(n) |
| **6** | Revenue Max | 0/1 Knapsack | DP | O(nW) | O(nW) |
| **6** | Preference Match | LCS | DP | O(m×n) | O(m×n) |
| **6** | Batch Compute | Matrix Chain | DP | O(n³) | O(n²) |

---

# 🎯 Key Insights by Course Outcome

## **CO1 – AVL Tree**
- ✅ Self-balancing: no degeneration to O(n)
- ✅ Perfect for maintaining sorted order
- ✅ Rotations keep height ≤ 1.44 × log₂(n)

## **CO2 – B+ Tree & Segment Tree**
- ✅ B+ Tree: all data in leaves, linked for sequential access
- ✅ Perfect for range queries in O(log n + k)
- ✅ Segment Tree: preprocessing buys O(log n) queries

## **CO3 – BFS, DFS, Prim's**
- ✅ BFS: shortest hops (not distance)
- ✅ DFS: connectivity & reachability checks
- ✅ Prim's: minimum cost network via greedy selection

## **CO4 – Dijkstra, Bellman-Ford, Floyd-Warshall**
- ✅ Dijkstra: non-negative weights, single-source
- ✅ Bellman-Ford: handles negative, detects cycles
- ✅ Floyd-Warshall: all-pairs with O(V³) preprocessing

## **CO5 – Heap, Counting, Radix Sort**
- ✅ Heap: O(n log n) guaranteed, in-place
- ✅ Counting: O(n) for small range, stable
- ✅ Radix: O(n×d) for multi-digit numbers

## **CO6 – Knapsack, LCS, Matrix Chain**
- ✅ Dynamic Programming: trade space for time
- ✅ Knapsack: optimal selection with constraints
- ✅ LCS: pattern matching in strings
- ✅ Matrix Chain: optimization of evaluation order

---

# 📈 Algorithm Selection Decision Tree

```
Need to find something in sorted data?
├─ Yes → AVL Tree (CO1)
└─ No
   ├─ Range query?
   │  ├─ Sorted range → B+ Tree (CO2)
   │  └─ Aggregate range → Segment Tree (CO2)
   │
   ├─ Graph problem?
   │  ├─ Shortest path?
   │  │  ├─ Non-negative → Dijkstra (CO4)
   │  │  ├─ Negative weights → Bellman-Ford (CO4)
   │  │  └─ All-pairs → Floyd-Warshall (CO4)
   │  │
   │  ├─ Spanning tree? → Prim's (CO3)
   │  │
   │  ├─ Reachable nodes? → DFS (CO3)
   │  │
   │  └─ Nearest node? → BFS (CO3)
   │
   ├─ Sorting problem?
   │  ├─ Guaranteed O(n log n)? → Heap Sort (CO5)
   │  ├─ Small integer range? → Counting Sort (CO5)
   │  └─ Multi-digit numbers? → Radix Sort (CO5)
   │
   └─ Optimization problem?
      ├─ Capacity constraint? → Knapsack (CO6)
      ├─ Pattern matching? → LCS (CO6)
      └─ Chain computation? → Matrix Chain (CO6)
```

---

# 🎓 Learning Path

### **Week 1-2: Trees (CO1, CO2)**
- AVL Tree basics and rotations
- B+ Tree structure and range queries
- Segment Tree construction and queries

### **Week 3: Graph Fundamentals (CO3)**
- BFS for nearest neighbor
- DFS for connectivity
- Prim's Algorithm for MST

### **Week 4: Shortest Paths (CO4)**
- Dijkstra's Algorithm
- Bellman-Ford for negative weights
- Floyd-Warshall for all-pairs

### **Week 5: Sorting Optimization (CO5)**
- Heap Sort internals
- Counting Sort for small ranges
- Radix Sort for large numbers

### **Week 6: Dynamic Programming (CO6)**
- 0/1 Knapsack optimization
- Longest Common Subsequence
- Matrix Chain Multiplication

---

# 🏆 Optimization Statistics

| Optimization | Without Algorithm | With Algorithm | Improvement |
|---|---|---|---|
| Restaurant Search | O(n) linear scan | O(log n) AVL | **100× faster** (1M items) |
| Menu Range Query | O(n) full scan | O(log n + k) B+ Tree | **1000× faster** (1M items) |
| Delivery Route | Manual trial-error | Dijkstra O(V²) | **Polynomial time** |
| Route with Discounts | Fails completely | Bellman-Ford | **Works** ✅ |
| Hub Network Cost | Brute force O(2^V) | Prim's O(V²) | **Exponential → Quadratic** |
| Order Priority Sort | O(n log n) comparison | Heap Sort O(n log n) | **In-place, no extra space** |
| Star Rating Sort | O(n log n) | Counting O(n) | **5× faster** (small range) |
| Order ID Sort | O(n log n) | Radix O(5n) | **2× faster** (5-digit) |
| Menu Bundle Revenue | O(2^n) brute | Knapsack O(nW) | **Exponential → Polynomial** |
| Customer Matching | O(2^n) subseq | LCS O(m×n) | **Exponential → Quadratic** |
| Batch Computation | O(4^n) orders | Matrix Chain O(n³) | **Exponential → Cubic** |

---

# 📚 Data Structures at a Glance

| DS | Insert | Search | Delete | Space | Use Case |
|---|---|---|---|---|---|
| **AVL Tree** | O(log n) | O(log n) | O(log n) | O(n) | Sorted index |
| **B+ Tree** | O(log n) | O(log n) | O(log n) | O(n) | Range queries |
| **Segment Tree** | O(log n) | O(log n) | O(log n) | O(n) | Range aggregates |
| **Adjacency Matrix** | O(1) | O(1) | O(1) | O(V²) | Dense graphs |
| **Heap** | O(log n) | O(1) | O(log n) | O(n) | Priority queue |

---

# 🔗 Inter-CO Applications

**Real-world FoodChain scenarios combining multiple COs:**

```
1. Restaurant Registration + Route Finding
   CO1 (AVL) → Find restaurant
   CO4 (Dijkstra) → Find fastest route
   Time: O(log n) + O(V²)

2. Menu Browsing + Sorting Orders
   CO2 (B+ Tree) → Filter by price range
   CO5 (Radix Sort) → Sort results by ID
   Time: O(log n + k) + O(n×d)

3. Delivery Network Planning
   CO3 (BFS/DFS) → Check connectivity
   CO3 (Prim's) → Build optimal network
   CO4 (Floyd-Warshall) → Compute all routes
   Time: O(V+E) + O(V²) + O(V³)

4. Peak Hour Optimization
   CO2 (Segment Tree) → Find peak hours
   CO5 (Counting Sort) → Sort by demand
   CO6 (Knapsack) → Allocate resources
   Time: O(log n) + O(n+k) + O(nW)
```

---

# 🎯 Final Checklist

- ✅ **CO1** – AVL Tree for restaurant indexing
- ✅ **CO2** – B+ Tree for menu range queries
- ✅ **CO2** – Segment Tree for peak hour analytics
- ✅ **CO3** – BFS for nearest zone
- ✅ **CO3** – DFS for network connectivity
- ✅ **CO3** – Prim's for minimum hub network
- ✅ **CO4** – Dijkstra for standard shortest paths
- ✅ **CO4** – Bellman-Ford for negative weights
- ✅ **CO4** – Floyd-Warshall for all-pairs
- ✅ **CO5** – Heap Sort for priorities
- ✅ **CO5** – Counting Sort for ratings
- ✅ **CO5** – Radix Sort for order IDs
- ✅ **CO6** – 0/1 Knapsack for bundles
- ✅ **CO6** – LCS for customer matching
- ✅ **CO6** – Matrix Chain for batch processing

---

# 📖 References

**Data Structures:**
- Introduction to Algorithms (CLRS)
- The Art of Computer Programming (Knuth)
- Algorithms in C (Sedgewick)

**Online Resources:**
- GeeksforGeeks DSA tutorials
- Visualgo.net (algorithm animations)
- LeetCode (practice problems)

---

**Submitted as part of DSA-2 Case Study – I-III Semester (2025-26)**  
**FoodChain Smart Delivery & Restaurant Logistics System**  
**Koneru Lakshmaiah Education Foundation, Hyderabad**

---

# 📞 Quick Reference

| Question | Answer | CO |
|----------|--------|-----|
| How to search restaurants fast? | AVL Tree | 1 |
| How to find dishes in price range? | B+ Tree | 2 |
| When are peak orders? | Segment Tree | 2 |
| Is delivery zone reachable? | DFS | 3 |
| What's nearest zone? | BFS | 3 |
| Build optimal road network? | Prim's MST | 3 |
| Fastest route A→B? | Dijkstra | 4 |
| Route with discounts? | Bellman-Ford | 4 |
| All routes precomputed? | Floyd-Warshall | 4 |
| Sort priorities? | Heap Sort | 5 |
| Group by rating? | Counting Sort | 5 |
| Sort order IDs? | Radix Sort | 5 |
| Maximize revenue? | Knapsack | 6 |
| Common preferences? | LCS | 6 |
| Optimize computation? | Matrix Chain | 6 |

---

**Happy Learning! 🚀**
