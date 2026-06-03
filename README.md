# 🍽️ FoodChain – Smart Food Delivery & Restaurant Logistics Optimization System

> **Subject:** DSA-2 | **Department:** FED | **Academic Year:** I-III Semester (2025-26)  
> **Institution:** Koneru Lakshmaiah Education Foundation, Hyderabad

---

## 📌 Project Overview

**FoodChain** is a simulation of a smart food delivery and restaurant logistics management system. It models real-world scenarios like restaurant registration, menu cataloging, delivery zone mapping, and hub network optimization using core Data Structures and Algorithms.

The project is divided into **3 modules**, each mapped to a specific Course Outcome (CO):

| Module | Course Outcome | DSA Used |
|--------|---------------|----------|
| CO1 | Restaurant Registration & Search Index | AVL Tree (BST) |
| CO2 | Menu Catalog Indexing & Demand Analytics | B+ Tree + Segment Tree |
| CO3 | Delivery Zone Mapping & Hub Optimization | BFS + DFS + Prim's MST |

---

## 📂 Project Structure

```
FoodChain/
│
├── FoodChainAVL.java          # CO1 - AVL Tree: Restaurant Registration & Search
├── FoodChainCatalog.java      # CO2 - B+ Tree + Segment Tree: Menu & Analytics
├── FoodChainDelivery.java     # CO3 - BFS + DFS + Prim's MST: Delivery Network
└── README.md
```

---

## 🌳 CO1 – Restaurant Registration & Search Index using AVL Tree

### 📋 Problem Statement
FoodChain onboards hundreds of restaurants daily. Each restaurant has a unique `RestaurantID`. The system must support fast insertion, search, and deletion of restaurants while always being able to generate a sorted directory. A plain BST can degrade to O(n) in the worst case — so we use an **AVL Tree** to guarantee O(log n) at all times.

### 🔧 Data Structure Used
- **AVL Tree** (Self-Balancing Binary Search Tree)

### ⚙️ How It Works
1. Each restaurant is inserted into the AVL Tree using its `RestaurantID` as the key.
2. After every insertion, the **balance factor** of each ancestor node is checked.
3. If `|Balance Factor| > 1`, one of 4 rotations is triggered:
   - **LL Rotation** – inserted into left subtree of left child
   - **RR Rotation** – inserted into right subtree of right child
   - **LR Rotation** – inserted into right subtree of left child
   - **RL Rotation** – inserted into left subtree of right child
4. **Inorder traversal** produces a sorted restaurant directory automatically.
5. **Search** runs in O(log n) at all times due to balanced height.

### 📊 Sample Data
| RestaurantID | Name |
|---|---|
| 101 | PizzaHub |
| 205 | BurgerKing |
| 150 | SpiceGarden |
| 310 | TacoStop |
| 89 | RiceBox |
| 420 | NoodleHut |
| 175 | SandwichCo |
| 260 | GrillHouse |
| 330 | CurryPoint |
| 95 | WrapZone |

### ⏱️ Time Complexity
| Operation | Complexity |
|---|---|
| Insert | O(log n) |
| Search | O(log n) |
| Delete | O(log n) |
| Inorder Traversal | O(n) |

### ▶️ How to Run
```bash
javac FoodChainAVL.java
java FoodChainAVL
```

---

## 🗂️ CO2 – Menu Catalog Indexing & Demand Analytics using B+ Tree & Segment Tree

### 📋 Problem Statement
FoodChain lists thousands of menu items across hundreds of restaurants. Customers frequently filter items by price range (e.g., "show me all dishes between Rs.100 and Rs.500"). At the same time, the platform needs to identify **peak order hours** to allocate more delivery partners during high-demand windows.

Two separate structures solve these two problems:
- **B+ Tree** → efficient price-based range queries on menu items
- **Segment Tree** → fast range sum queries on hourly order frequency data

### 🔧 Data Structures Used
- **B+ Tree** (order 3) for menu indexing
- **Segment Tree** for order analytics

### ⚙️ How It Works

#### B+ Tree – Menu Catalog
1. Each menu item's **price** is used as the key in the B+ Tree of order 3.
2. As items are inserted, **node splits** occur when a node exceeds capacity.
3. All data is stored in **leaf nodes**, which are linked like a linked list for sequential access.
4. A **range query** traverses from the appropriate leaf node and collects all prices within `[low, high]`.

#### Segment Tree – Peak Hours
1. An array of **24 values** stores order counts for each hour of the day (index 0 = 6AM, index 6 = 12PM, etc.).
2. The Segment Tree is built over this array storing **sum** at each internal node.
3. A **range sum query** answers: "How many total orders came in between hour X and hour Y?"
4. This identifies the **peak demand window** so FoodChain can assign more partners.

### 📊 Sample Data

**Menu Items:**
| Price (Rs.) | Item |
|---|---|
| 60 | Samosa |
| 75 | Tea |
| 85 | Veg Roll |
| 120 | Paneer Wrap |
| 175 | Egg Fried Rice |
| 200 | Dal Makhani |
| 300 | Fish Curry |
| 320 | Lamb Chops |
| 390 | Prawn Masala |
| 450 | Chicken Biryani |
| 499 | Butter Chicken |
| 550 | Mutton Rogan |

**Range Query:** Rs.100 – Rs.500 → returns: 120, 175, 200, 300, 320, 390, 450, 499

### ⏱️ Time Complexity
| Operation | Complexity |
|---|---|
| B+ Tree Insert | O(log n) |
| B+ Tree Range Query | O(log n + k) |
| Segment Tree Build | O(n) |
| Segment Tree Range Query | O(log n) |

### ▶️ How to Run
```bash
javac FoodChainCatalog.java
java FoodChainCatalog
```

---

## 🚚 CO3 – Delivery Zone Mapping & Partner Assignment using BFS & Prim's Algorithm

### 📋 Problem Statement
FoodChain operates across multiple delivery zones in a city. The platform needs to:
1. **Find the nearest delivery zone** from a restaurant when a new order arrives (BFS).
2. **Verify that all delivery zones are reachable** from the central hub — no zone should be cut off (DFS).
3. **Build the minimum cost road network** connecting all hubs and zones to minimize infrastructure investment (Prim's MST).

### 🔧 Algorithms Used
- **BFS** (Breadth First Search) – nearest zone discovery
- **DFS** (Depth First Search) – connectivity verification
- **Prim's Algorithm** – Minimum Spanning Tree for hub network

### ⚙️ How It Works

#### Graph Representation
The city network is modelled as a **weighted undirected graph** using an **adjacency matrix**:
- **Nodes (10):** Restaurant_A, Restaurant_B, Hub_H1, Hub_H2, Zone_1 to Zone_6
- **Edges (14):** Road connections with distances in km

#### BFS – Nearest Delivery Zone
1. Start BFS from a restaurant node.
2. Explore nodes **level by level**.
3. The first Zone node found at **Level 1** is the nearest delivery zone.
4. Guarantees minimum hops (not minimum distance).

#### DFS – Connectivity Check
1. Start DFS from Hub_H1.
2. Recursively visit all connected nodes.
3. If all 10 nodes are visited → network is **FULLY CONNECTED**.
4. Ensures no delivery zone is isolated.

#### Prim's MST – Minimum Hub Network
1. Start from node 0 (Restaurant_A).
2. Greedily pick the **minimum weight edge** that connects a new node to the growing MST.
3. Repeat until all 10 nodes are included.
4. Output: minimum cost set of roads to connect the entire delivery network.

### 📊 Network Graph
```
Restaurant_A ---4km--- Hub_H1 ---2km--- Hub_H2
     |                  |                  |  \
    7km                5km               4km  3km
     |                  |                  |    \
  Zone_1            Zone_2            Zone_3  Zone_4
     |                  |                  |    |
    5km                4km               6km  2km
     |                  |                  |    |
  Zone_5 ----3km---- Zone_6 <-----------+ Zone_5
```

### ⏱️ Time Complexity
| Algorithm | Complexity |
|---|---|
| BFS | O(V + E) |
| DFS | O(V + E) |
| Prim's MST (Adjacency Matrix) | O(V²) |

### ▶️ How to Run
```bash
javac FoodChainDelivery.java
java FoodChainDelivery
```

---

## 🛠️ How to Run the Full Project

### Prerequisites
- Java JDK 8 or above installed
- Any IDE (IntelliJ IDEA / Eclipse / VS Code with Java extension) **or** command line

### Steps
```bash
# Clone the repository
git clone https://github.com/YOUR_USERNAME/FoodChain-DSA.git
cd FoodChain-DSA

# Compile all files
javac FoodChainAVL.java
javac FoodChainCatalog.java
javac FoodChainDelivery.java

# Run each module
java FoodChainAVL
java FoodChainCatalog
java FoodChainDelivery
```

---

## 📈 Overall Complexity Summary

| Module | CO | Algorithm | Time Complexity | Space Complexity |
|---|---|---|---|---|
| Restaurant Index | CO1 | AVL Tree | O(log n) per op | O(n) |
| Menu Range Query | CO2 | B+ Tree | O(log n + k) | O(n) |
| Peak Hour Analytics | CO2 | Segment Tree | O(log n) | O(n) |
| Nearest Zone | CO3 | BFS | O(V + E) | O(V) |
| Zone Connectivity | CO3 | DFS | O(V + E) | O(V) |
| Hub Network | CO3 | Prim's MST | O(V²) | O(V) |

---

## 🎯 Key Takeaways

- **AVL Tree** guarantees balanced height at all times — no degradation to O(n) unlike plain BST.
- **B+ Tree** stores all data in leaves and links them, making range queries extremely efficient.
- **Segment Tree** answers any range sum/max query in O(log n) after O(n) build time.
- **BFS** finds shortest path in terms of hops — perfect for nearest-zone discovery.
- **DFS** is ideal for connectivity checks — visits every reachable node exactly once.
- **Prim's MST** minimized the hub network cost by selecting only the cheapest 9 edges out of 14, saving ~37% compared to using all roads.

---


*Submitted as part of DSA-2 Case Study – I-III Semester (2025-26)*
