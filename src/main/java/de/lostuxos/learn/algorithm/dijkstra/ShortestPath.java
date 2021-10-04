package de.lostuxos.learn.algorithm.dijkstra;

import de.lostuxos.learn.datastructure.graph.Edge;
import de.lostuxos.learn.datastructure.graph.Graph;
import de.lostuxos.learn.datastructure.graph.Vertex;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class ShortestPath {

    public List<DijkstraNode> shortestPath(Graph graph, String startNode) {
        var adjMap = initialize(graph, startNode);
        var unvisitedNodes = createPriorityQueue(startNode, adjMap);

        while (!unvisitedNodes.isEmpty()) {
            var currentNode = unvisitedNodes.poll();
            var edges = graph.getEdges(currentNode.getVertex());
            currentNode.setVisited(true);

            summarize(adjMap, currentNode, edges);
            appendUnvisitedSuccessors(adjMap, edges, unvisitedNodes);
        }
        return adjMap.values().stream().toList();
    }

    private Map<Vertex, DijkstraNode> initialize(Graph graph, String startNode) {
        return graph.getVertexes().stream().map(v -> {
                    if (startNode.equals(v.getLabel())) {
                        return new DijkstraNode(v, null, 0, false);
                    } else {
                        return new DijkstraNode(v, null, Long.MAX_VALUE, false);
                    }
                }
        ).collect(Collectors.toMap(n -> n.getVertex(), n -> n));
    }

    private PriorityQueue<DijkstraNode> createPriorityQueue(String startNode, Map<Vertex, DijkstraNode> adjMap) {
        Comparator<DijkstraNode> byDistance = Comparator.comparingLong(DijkstraNode::getDistance);
        var unvisitedNodes = new PriorityQueue<>(byDistance);
        unvisitedNodes.add(adjMap.get(new Vertex(startNode)));
        return unvisitedNodes;
    }

    private void summarize(Map<Vertex, DijkstraNode> adjMap, DijkstraNode currentNode, List<Edge> edges) {
        for (var edge : edges) {
            var successor = adjMap.get(edge.getVertex());
            var distance = currentNode.getDistance() + edge.getWeight();
            if (successor.getDistance() > distance) {
                successor.setDistance(currentNode.getDistance() + edge.getWeight());
                successor.setPredecessor(currentNode);
            }
        }
    }

    private void appendUnvisitedSuccessors(Map<Vertex, DijkstraNode> adjMap, List<Edge> edges, PriorityQueue<DijkstraNode> unvisitedNodes) {
        unvisitedNodes.addAll(edges.stream()
                .map(successor -> adjMap.get(successor.getVertex()))
                .filter(n -> !n.isVisited())
                .toList()
        );
    }

}
