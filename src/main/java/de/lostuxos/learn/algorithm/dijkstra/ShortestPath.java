package de.lostuxos.learn.algorithm.dijkstra;

import de.lostuxos.learn.datastructure.graph.Edge;
import de.lostuxos.learn.datastructure.graph.Graph;
import de.lostuxos.learn.datastructure.graph.Vertex;

import java.util.*;

public class ShortestPath {

    public List<DijkstraNode> shortestPath(Graph graph, String startNode) {
        var adjMap = new HashMap<Vertex, DijkstraNode>();
        adjMap.put(new Vertex(startNode), new DijkstraNode(new Vertex(startNode), null , 0 , false));
        var unvisitedNodes = createPriorityQueue(adjMap.get(new Vertex(startNode)));

        while (!unvisitedNodes.isEmpty()) {
            var currentNode = unvisitedNodes.poll();
            var edges = graph.getEdges(currentNode.getVertex());
            currentNode.setVisited(true);

            summarize(adjMap, currentNode, edges, unvisitedNodes);
        }
        return adjMap.values().stream().toList();
    }

    private PriorityQueue<DijkstraNode> createPriorityQueue(DijkstraNode dijkstraNode) {
        Comparator<DijkstraNode> byDistance = Comparator.comparingLong(DijkstraNode::getDistance);
        var unvisitedNodes = new PriorityQueue<>(byDistance);
        unvisitedNodes.add(dijkstraNode);
        return unvisitedNodes;
    }

    private void summarize(Map<Vertex, DijkstraNode> adjMap, DijkstraNode currentNode, List<Edge> edges, PriorityQueue<DijkstraNode> unvisitedNodes) {
        for (var edge : edges) {

            adjMap.putIfAbsent(edge.getVertex(), new DijkstraNode(edge.getVertex(), null, Long.MAX_VALUE, false));
            var successor = adjMap.get(edge.getVertex());
            var distance = currentNode.getDistance() + edge.getWeight();
            if (successor.getDistance() > distance) {
                successor.setDistance(currentNode.getDistance() + edge.getWeight());
                successor.setPredecessor(currentNode);
                unvisitedNodes.remove(successor);
            }
            if (!successor.isVisited()) {
                unvisitedNodes.add(successor);
            }
        }
    }

}
