package de.lostuxos.learn.datastructure.graph;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @org.junit.jupiter.api.Test
    void addVertex() {
        //Given an Enpty Graph
        var g = new Graph();
        //When
        g.addVertex("Hallo");
        //Then
        assertTrue(g.hasVertex("Hallo"));
    }

    @org.junit.jupiter.api.Test
    void removeVertex() {
        //Given an Enpty Graph
        var g = new Graph();
        //When
        g.addVertex("Hallo");
        g.removeVertex("Hallo");
        //Then
        assertFalse(g.hasVertex("Hallo"));
    }

}