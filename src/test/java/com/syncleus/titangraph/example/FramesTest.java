package com.syncleus.titangraph.example;

import com.syncleus.titangraph.example.frames.God;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.frames.*;
import com.tinkerpop.frames.modules.gremlingroovy.GremlinGroovyModule;
import com.tinkerpop.frames.modules.javahandler.JavaHandlerModule;
import org.junit.*;

public class FramesTest {
    @Test
    public void testFramesGetGod() {
        final TitanGraph godGraph = TitanGods.create("./target/TitanTestDB");
        final FramedGraphFactory factory = new FramedGraphFactory(new GremlinGroovyModule());

        final FramedGraph framedGraph = factory.create(godGraph);

        final Iterable<God> gods = (Iterable<God>) framedGraph.getVertices("name", "saturn", God.class);
        Assert.assertEquals(gods.iterator().next().getName(), "saturn");
        Assert.assertTrue(gods.iterator().next().getAge().equals(10000));
    }

    @Test
    public void testFramesHandler() {
        final TitanGraph godGraph = TitanGods.create("./target/TitanTestDB");
        final FramedGraphFactory factory = new FramedGraphFactory(new GremlinGroovyModule(), new JavaHandlerModule());

        final FramedGraph framedGraph = factory.create(godGraph);

        final Iterable<God> gods = (Iterable<God>) framedGraph.getVertices("name", "saturn", God.class);
        final God god = gods.iterator().next();
        Assert.assertEquals(god.getName(), "saturn");
        Assert.assertTrue(god.isAgeEven());
    }
}
