package com.syncleus.titangraph.example;

import com.syncleus.titangraph.example.frames.God;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.frames.*;
import com.tinkerpop.frames.modules.gremlingroovy.GremlinGroovyModule;
import org.junit.*;

public class FramesTest {
    @Test
    public void testFrames() {
        final TitanGraph godGraph = TitanGods.create("./target/TitanTestDB");
        FramedGraphFactory factory = new FramedGraphFactory(new GremlinGroovyModule());

        FramedGraph framedGraph = factory.create(godGraph);

        Iterable<God> gods = (Iterable<God>) framedGraph.getVertices("name", "saturn", God.class);
        Assert.assertEquals(gods.iterator().next().getName(), "saturn");
    }
}
