package de.lostuxos.learn.algorithm.dijkstra;

import de.lostuxos.learn.datastructure.graph.Vertex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DijkstraNode {
    Vertex vertex;
    DijkstraNode predecessor;
    long distance;
    boolean visited = false;
}
