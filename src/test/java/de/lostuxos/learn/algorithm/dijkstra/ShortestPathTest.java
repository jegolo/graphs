package de.lostuxos.learn.algorithm.dijkstra;

import de.lostuxos.learn.datastructure.graph.Graph;
import de.lostuxos.learn.datastructure.graph.Vertex;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ShortestPathTest {

    @Test
    void test_shortestPath() {
        //Given
        var graph = new Graph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "C", 1);
        //When
        var shortestPath = new ShortestPath();
        var result = shortestPath.shortestPath(graph, "A");

        //Then
        assertNotNull(result);
        assertEquals(3, result.size());

        var nodes = result.stream().collect(Collectors.toMap(dijkstraNode -> dijkstraNode.getVertex(), dijkstraNode -> dijkstraNode.distance));
        //A -> 0
        assertEquals(0, nodes.get(new Vertex("A")));
        //B -> 1
        assertEquals(1, nodes.get(new Vertex("B")));
        //C -> 2
        assertEquals(2, nodes.get(new Vertex("C")));

    }

    @Test
    void test_shortestPath_1() {
        //Given
        var graph = new Graph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 4);
        graph.addEdge("B", "C", 1);
        graph.addEdge("C", "A", 1);

        //When
        var shortestPath = new ShortestPath();
        var result = shortestPath.shortestPath(graph, "A");

        //Then
        assertNotNull(result);
        assertEquals(3, result.size());

        var nodes = result.stream().collect(Collectors.toMap(dijkstraNode -> dijkstraNode.getVertex(), dijkstraNode -> dijkstraNode.distance));
        //A -> 0
        assertEquals(0, nodes.get(new Vertex("A")));
        assertEquals(1, nodes.get(new Vertex("B")));
        assertEquals(2, nodes.get(new Vertex("C")));

    }
    @Test
    void test_shortestPath_CormanExample() {
        //Given
        var graph = new Graph();
        graph.addVertex("s");
        graph.addVertex("y");
        graph.addVertex("t");
        graph.addVertex( "z");
        graph.addVertex("x");

        graph.addEdge("s", "y", 5);
        graph.addEdge("s", "t", 10);
        graph.addEdge("t", "y", 2);
        graph.addEdge("y", "t", 3);
        graph.addEdge("y", "z", 2);
        graph.addEdge("y", "x", 9);
        graph.addEdge("z", "x", 6);
        graph.addEdge("x", "z", 4);
        graph.addEdge("t", "x", 1);


        //When
        var shortestPath = new ShortestPath();
        var result = shortestPath.shortestPath(graph, "s");

        //Then
        assertNotNull(result);
        assertEquals(5, result.size());

        var nodes = result.stream().collect(Collectors.toMap(dijkstraNode -> dijkstraNode.getVertex(), dijkstraNode -> dijkstraNode.distance));
        //A -> 0
        assertEquals(0, nodes.get(new Vertex("s")));
        assertEquals(5, nodes.get(new Vertex("y")));
        assertEquals(8, nodes.get(new Vertex("t")));
        assertEquals(9, nodes.get(new Vertex("x")));
        assertEquals(7, nodes.get(new Vertex("z")));

    }
}