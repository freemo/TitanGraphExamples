package com.syncleus.titangraph.example;

import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.gremlin.java.GremlinPipeline;
import com.tinkerpop.pipes.PipeFunction;
import org.junit.*;

import java.util.List;

public class GremlinTest {
    @Test
    public void testGremlinFindChild() {
        final TitanGraph godGraph = TitanGods.create("./target/TitanTestDB");
        final GremlinPipeline<Vertex,Vertex> pipe = new GremlinPipeline<Vertex,Vertex>();
        final Vertex saturnVertex = godGraph.getVertices("name", "saturn").iterator().next();
        final GremlinPipeline<Vertex,?> childsNamePipe = pipe.start(saturnVertex).in("father").property("name");
        final String childsName = childsNamePipe.next().toString();
        Assert.assertEquals(childsName, "jupiter");
    }

    @Test
    public void testGremlinFindBrothers() {
        final TitanGraph godGraph = TitanGods.create("./target/TitanTestDB");
        final GremlinPipeline<Vertex,Vertex> pipe = new GremlinPipeline<Vertex,Vertex>();
        final Vertex jupiterVertex = godGraph.getVertices("name", "jupiter").iterator().next();
        final GremlinPipeline<Vertex,?> brotherNamesPipe = pipe.start(jupiterVertex).out("brother").property("name");
        final List brotherNames = brotherNamesPipe.next(100);

        //we know jupiter only has two brothers
        Assert.assertEquals(brotherNames.size(), 2);

        //check each brothers name to make sure both of them matchone of the known brothers names
        Assert.assertTrue(("pluto".equals(brotherNames.get(0).toString())) || ("neptune".equals(brotherNames.get(0).toString())));
        Assert.assertTrue( ("pluto".equals(brotherNames.get(1).toString())) || ("neptune".equals(brotherNames.get(1).toString())) );
    }

    @Test
    public void testGremlinFindBrothersFirstLetterP() {
        final TitanGraph godGraph = TitanGods.create("./target/TitanTestDB");
        final GremlinPipeline<Vertex,Vertex> pipe = new GremlinPipeline<Vertex,Vertex>();
        final Vertex jupiterVertex = godGraph.getVertices("name", "jupiter").iterator().next();
        final GremlinPipeline brotherNamesPipe = new GremlinPipeline(jupiterVertex).out("brother").property("name").filter(new PipeFunction<String,Boolean>() {
            public Boolean compute(String argument) {
                return argument.startsWith("p");
            }
        });
        final List brotherNames = brotherNamesPipe.next(100);

        //we know jupiter only has two brothers
        Assert.assertEquals(brotherNames.size(), 1);

        //check each brothers name to make sure both of them matchone of the known brothers names
        Assert.assertEquals( "pluto", brotherNames.get(0).toString() );
    }
}
