import javax.swing.*;
import java.util.*;

public class Routing_Dijkstras_Algorithm {

    //road as edge in network graph
    static class Road{
        String targetNode;
        int baseTime;
        int riskScore;
        int blockagePenalty;
        String condition;

        public Road(String targetNode, int baseTime, int riskScore, int blockagePenalty, String condition){
            this.targetNode = targetNode;
            this.baseTime = baseTime;
            this.riskScore = riskScore;
            this.blockagePenalty = blockagePenalty;
            this.condition = condition;
        }

        //calculate cumulative weight or traversal cost
        public int getTotalCost(){
            if(this.condition.equals("FULLY_BLOCKED")){
                return Integer.MAX_VALUE; //return represent infinity cost
            }
            return this.baseTime + this.riskScore + this.blockagePenalty;
        }
    }

    //helper class to store states inside Min-Priority Queue
    static class NodePair implements Comparable<NodePair>{
        String nodeName;
        int accumulatedCost;

        public NodePair(String nodeName, int accumulatedCost){
            this.nodeName = nodeName;
            this.accumulatedCost = accumulatedCost;
        }

        @Override
        public int compareTo(NodePair other){
            return Integer.compare(this.accumulatedCost, other.accumulatedCost);
        }
    }

    //adjacency list mapping locations to adjacent roads
    private Map<String, List<Road>> graph = new HashMap<>();

    public void addLocation(String name){
        graph.putIfAbsent(name, new ArrayList<>());
    }

    public void addRoad(String source, String destination, int baseTime, int riskScore, int blockagePenalty, String condition){
        addLocation(source);
        addLocation(destination);
        //standard directed road edge addition
        graph.get(source).add(new Road(destination, baseTime, riskScore, blockagePenalty, condition));
    }

    //Dijkstra Execution step
    public void findOptionalRoute(String startNode, String targetNode){
        System.out.println("Running Rescue Optimization From: " + startNode + " to " + targetNode);

        Map<String, Integer> distance = new HashMap<>();
        Map<String, String> parents = new HashMap<>();
        Set<String> visited = new HashSet<>();

        //initialization
        for (String node: graph.keySet()){
            distance.put(node, Integer.MAX_VALUE);
            parents.put(node, null);
        }
        distance.put(startNode, 0);

        PriorityQueue<NodePair> pq = new PriorityQueue<>();
        pq.add(new NodePair(startNode, 0));

        boolean targetReached = false;

        //processing loop
        while (!pq.isEmpty()){
            NodePair current = pq.poll();
            String u = current.nodeName;

            if(visited.contains(u)) continue;
            visited.add(u);

            //early termination check if priority destination is reached
            if(u.equals(targetNode)){
                targetReached = true;
                break;
            }

            //explore neighbors
            for(Road road: graph.getOrDefault(u, new ArrayList<>())){
                //safely ignore paths completely obstructed by landslides
                if(road.condition.equals("FULLY_BLOCKED")){
                    System.out.println("-> [ALERT] Bypassing fully blocked road from " + u + " to " + road.targetNode);
                    continue;
                }
                String v = road.targetNode;
                int weight = road.getTotalCost();

                if(distance.get(u) != Integer.MAX_VALUE && distance.get(u) + weight < distance.get(v)){
                    distance.put(v, distance.get(u) + weight);
                    parents.put(v, u);
                    pq.add(new NodePair(v, distance.get(v)));
                }
            }
        }

        //path evaluation and output display
        if(targetReached && distance.get(targetNode) != Integer.MAX_VALUE){
            List<String> path = new ArrayList<>();
            String curr = targetNode;
            while(curr != null){
                path.add(curr);
                curr = parents.get(curr);
            }
            Collections.reverse(path);

            System.out.println("\n🟢 OPTIMAL MISSION PATH FOUND:");
            System.out.print("   " + String.join(" ➔ ", path));
            System.out.println("\n📊 TOTAL CALCULATED MISSION COST VALUE: " + distance.get(targetNode));
        } else {
            System.out.println("\n❌ [CRITICAL] NO SAFE ROUTE AVAILABLE! All matching paths are fully blocked.");
        }
    }

    //Program Demonstration with Scenario Environments
    public static void main(String[] args){
        Routing_Dijkstras_Algorithm rescueSystem = new Routing_Dijkstras_Algorithm();

        //establishing locations based on the problem design
        rescueSystem.addLocation("Civil Defence HQ");
        rescueSystem.addLocation("Junction A");
        rescueSystem.addLocation("Junction B");
        rescueSystem.addLocation("Hospital Zone");
        rescueSystem.addLocation("Trapped Village");

        System.out.println("Environment Scenario A: Initial Road Status (Clear & Partial Risks");
        //syntax parameters: addRoad(Source, destination, basetime, riskscore, blockagePenalty, condition)
        rescueSystem.addRoad("Civil Defence HQ", "Junction A", 10, 0, 0, "CLEAR");
        rescueSystem.addRoad("Civil Defence HQ", "Junction B", 15, 10, 10, "PARTIALLY_BLOCKED");

        rescueSystem.addRoad("Junction A", "Hospital Zone", 12, 0, 0, "CLEAR");
        rescueSystem.addRoad("Junction B", "Hospital Zone", 8, 0, 0, "CLEAR");

        rescueSystem.addRoad("Hospital Zone", "Trapped Village", 15, 0, 0, "CLEAR");

        //run analysis for normal conditions
        rescueSystem.findOptionalRoute("Civil Defence HQ", "Hospital Zone");

        System.out.println("\n\n---Environment Scenario B: Dynamic Disaster update (Landslide Event!)");
        //clear old map parameters and apply the updated crisis landscape
        rescueSystem.graph.clear();

        //junction A route completely wiped out by a fresh mudslide
        rescueSystem.addRoad("Civil Defence HQ", "Junction A", 10, 0, 0, "FULLY_BLOCKED");
        rescueSystem.addRoad("Civil Defence HQ", "Junction B", 15, 10, 10, "PARTIALLY_BLOCKED");

        rescueSystem.addRoad("Junction A", "Hospital Zone", 12, 0, 0, "CLEAR");
        rescueSystem.addRoad("Junction B", "Hospital Zone", 8, 0, 0, "CLEAR");

        rescueSystem.addRoad("Hospital Zone", "Trapped Village", 15, 0, 0, "CLEAR");

        //recalculating
        rescueSystem.findOptionalRoute("Civil Defence HQ", "Hospital Zone");
    }
}
