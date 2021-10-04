package de.lostuxos.learn.datastructure.graph;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Edge {
    private long weight;
    private Vertex vertex;
}
