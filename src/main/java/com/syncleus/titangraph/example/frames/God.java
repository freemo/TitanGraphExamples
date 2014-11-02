package com.syncleus.titangraph.example.frames;

import com.tinkerpop.frames.*;
import com.tinkerpop.frames.annotations.gremlin.GremlinGroovy;

public interface God {
    @Property("name")
    public String getName();

    @Property("age")
    public String getAge();

    @Property("type")
    public String getType();

    @Adjacency(label="father")
    public God getFather();

    @GremlinGroovy("it.in('father')")
    public God getSon();

    @Adjacency(label="lives")
    public Location getHome();
}
