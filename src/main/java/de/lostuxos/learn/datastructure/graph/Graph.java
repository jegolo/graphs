package de.lostuxos.learn.datastructure.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private Map<Vertex, List<Edge>> adjVertices = new HashMap<>();

    public void addVertex(String label) {
        adjVertices.putIfAbsent(new Vertex(label), new ArrayList<>());
    }

    public void removeVertex(String label) {
        adjVertices.remove(new Vertex(label));
    }

    public void addEdge(String label1, String label2, long distance) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        adjVertices.get(v1).add(new Edge(distance, v2));
    }

    public boolean hasVertex(String label) {
        Vertex vertex = new Vertex(label);
        return adjVertices.containsKey(vertex);
    }

    public List<Vertex> getVertexes() {
        return adjVertices.keySet().stream().toList();
    }

    public List<Edge> getEdges(Vertex label) {
        return adjVertices.get(label);
    }


}
