package com.syncleus.titangraph.example.titangods;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.frames.*;
import com.tinkerpop.frames.annotations.gremlin.GremlinGroovy;
import com.tinkerpop.frames.modules.javahandler.*;

public interface God {
    @Property("name")
    public String getName();

    @Property("age")
    public Integer getAge();

    @Property("type")
    public String getType();

    @Adjacency(label="father")
    public God getFather();

    @GremlinGroovy("it.in('father')")
    public God getSon();

    @Adjacency(label="lives")
    public Location getHome();

    @JavaHandler
    public boolean isAgeEven();

    public abstract class Impl implements JavaHandlerContext<Vertex>, God {

        public boolean isAgeEven() {
            return this.getAge() % 2 == 0;
        }
    }
}
