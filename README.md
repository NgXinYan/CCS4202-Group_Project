# 🚑 Emergency Rescue Route Optimization Using Dijkstra's Algorithm

## 📖 Overview

This project implements an **Emergency Rescue Route Optimization System** using **Dijkstra's Algorithm** to determine the safest and lowest-cost route for rescue teams during disaster situations. The case study is based on the **2015 Ranau Earthquake** in Sabah, Malaysia, where landslides destroyed road infrastructure and isolated communities.

Unlike conventional shortest-path routing, this project considers multiple real-world factors including travel time, road safety, and blockage conditions. Whenever a road becomes blocked, the system dynamically recalculates a new optimal route to support emergency response operations.

---

## 🌍 Background

On **5 June 2015**, a **magnitude 6.0 earthquake** struck Ranau, Sabah, triggering widespread landslides around Mount Kinabalu. The disaster damaged major roads, isolated mountain villages, and severely disrupted rescue operations.

Since road conditions changed continuously, conventional static maps quickly became outdated. Rescue agencies such as the Civil Defence Force required an intelligent routing system capable of:

- identifying the safest route,
- avoiding blocked roads,
- minimizing travel time,
- supporting rapid emergency response.

This project demonstrates how graph algorithms can be applied to solve this real-world optimization problem.

---

# 🎯 Project Objectives

1. **Model the Road Network**

   Represent Ranau's road network as a weighted graph where:

   - Vertices represent locations
   - Edges represent roads
   - Edge weights represent travel cost

2. **Implement Dynamic Route Optimization**

   Apply Dijkstra's Algorithm to compute the optimal rescue route while dynamically bypassing blocked roads.

3. **Evaluate Algorithm Performance**

   Analyze the correctness, efficiency, and time complexity of the implemented routing algorithm.

---

# 🛠 Algorithm Used

## Dijkstra's Algorithm (Greedy Algorithm)

This project applies **Dijkstra's Algorithm**, a greedy shortest-path algorithm that always expands the node with the lowest accumulated travel cost.

Unlike the traditional implementation that only considers physical distance, this project computes a **dynamic edge weight** based on multiple disaster-related factors.

### Edge Weight Formula

```
Total Cost = Base Time + Risk Score + Blockage Penalty
```

Where

| Component | Description |
|------------|-------------|
| Base Time | Estimated travel time (minutes) |
| Risk Score | Additional cost due to hazardous roads |
| Blockage Penalty | Delay caused by partially blocked roads |

Roads labelled as

```
FULLY_BLOCKED
```

are excluded completely from the routing process.

---

# 📊 Graph Representation

The road network is stored using an adjacency list.

```java
Map<String, List<Road>>
```

Example

```
Civil Defence HQ
│
├── Junction A
│      │
│      └── Hospital
│
└── Junction B
       │
       └── Hospital
```

Each **Road** object stores

- Destination
- Base travel time
- Risk score
- Blockage penalty
- Road condition

---

# 📂 Project Structure

```
Routing_Dijkstras_Algorithm.java

│
├── Road
│     ├── targetNode
│     ├── baseTime
│     ├── riskScore
│     ├── blockagePenalty
│     ├── condition
│     └── getTotalCost()
│
├── NodePair
│     ├── nodeName
│     ├── accumulatedCost
│     └── compareTo()
│
└── Routing_Dijkstras_Algorithm
      ├── addLocation()
      ├── addRoad()
      ├── findOptimalRoute()
      └── main()
```

---

# ⚙ Features

- Graph-based road network
- Java Adjacency List implementation
- Dijkstra's Greedy Algorithm
- Priority Queue (Min Heap)
- Dynamic edge weight calculation
- Automatic avoidance of blocked roads
- Parent tracking for path reconstruction
- Early termination after destination is reached
- Dynamic graph update demonstration
- Two disaster scenarios included

---

# 🚦 Road Conditions

Each road belongs to one of three conditions.

| Condition | Description |
|------------|-------------|
| CLEAR | Road is fully accessible |
| PARTIALLY_BLOCKED | Road is passable but incurs additional penalty |
| FULLY_BLOCKED | Road is completely inaccessible and ignored by the algorithm |

---

# 🧮 Algorithm Workflow

1. Input the road network.
2. Select the source location.
3. Select the destination.
4. Input road conditions.
5. Initialize all distances to infinity.
6. Set the source distance to zero.
7. Insert the source into the Priority Queue.
8. Extract the node with the minimum accumulated cost.
9. Skip visited nodes.
10. Ignore fully blocked roads.
11. Compute the edge weight.
12. Relax neighbouring nodes.
13. Update distances and parents.
14. Repeat until the destination is reached.
15. Reconstruct and display the optimal route.

---

# 📈 Time Complexity

Using Java's **PriorityQueue (Min Heap)**

```
Time Complexity

O((V + E) log V)
```

Where

- **V** = Number of vertices
- **E** = Number of edges

Space Complexity

```
O(V + E)
```

---

# 💻 Sample Demonstration

## Scenario A

Initial road conditions

```
HQ
│
├── Junction A (CLEAR)
│
│── Hospital
│
└── Junction B (PARTIALLY_BLOCKED)
      │
      └── Hospital
```

Output

```
Optimal Route

Civil Defence HQ
→ Junction A
→ Hospital Zone

Total Cost = 22
```

---

## Scenario B

Landslide occurs

```
HQ
│
├── Junction A (FULLY_BLOCKED)
│
└── Junction B (PARTIALLY_BLOCKED)
      │
      └── Hospital
```

Output

```
Blocked road detected.

Recalculating...

Optimal Route

Civil Defence HQ
→ Junction B
→ Hospital Zone

Total Cost = 43
```

---

# ▶ How to Run

### Clone Repository

```bash
git clone https://github.com/NgXinYan/CCS4202-Group_Project.git
```

### Compile

```bash
javac Routing_Dijkstras_Algorithm.java
```

### Run

```bash
java Routing_Dijkstras_Algorithm
```

---

# 📚 References

1. Dijkstra, E. W. (1959). *A Note on Two Problems in Connexion with Graphs*. Numerische Mathematik, 1, 269–271.

2. Cormen, T. H., Leiserson, C. E., Rivest, R. L., & Stein, C. (2022). *Introduction to Algorithms* (4th ed.). MIT Press.

3. Oyola, A., Romero, D., & Vintimilla, B. X. (2017). *A Dijkstra-Based Algorithm for Selecting the Shortest-Safe Evacuation Routes in Dynamic Environments (SSER).* Springer.

4. Milin, V. (2025). *Dijkstra and A* Algorithms for Algorithmic Optimization of Route Planning.* Algorithms.

5. Wilt, C., & Ruml, W. (2016). *Effective Heuristics for Suboptimal Best-First Search.* Journal of Artificial Intelligence Research.

6. United States Geological Survey. (2015). *M 6.0 - 12 km WNW of Ranau, Malaysia.*

7. Vulcan Post. (2015). *The Local Sabahan Earthquake Aftermath You're Probably Unaware Of.*

---

# 👨‍💻 Course Information

**Course:** Design and Analysis of Algorithms

**Project Title:** Emergency Rescue Route Optimization Using Dijkstra's Algorithm

**Language:** Java

**Paradigm:** Greedy Algorithm

**Data Structure:** Graph (Adjacency List)

**Institution:** Universiti Putra Malaysia (UPM)

---

## ⭐ Key Learning Outcomes

- Graph Modelling
- Greedy Algorithms
- Dijkstra's Algorithm
- Priority Queue (Min Heap)
- Dynamic Graph Updates
- Shortest Path Optimization
- Time Complexity Analysis
- Real-world Disaster Route Planning
