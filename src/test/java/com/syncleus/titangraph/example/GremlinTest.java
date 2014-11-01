package com.syncleus.titangraph.example;

import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.gremlin.java.GremlinPipeline;
import org.junit.*;

public class GremlinTest {
    @Test
    public void testGremlin() {
        final TitanGraph godGraph = TitanGods.create("./target/TitanTestDB");
        final GremlinPipeline pipe = new GremlinPipeline();
        final Vertex saturnVertex = godGraph.getVertices("name", "saturn").iterator().next();
        final GremlinPipeline childsNamePipe = pipe.start(saturnVertex).in("father").property("name");
        final String childsName = childsNamePipe.next().toString();
        Assert.assertEquals(childsName, "jupiter");
    }
}
