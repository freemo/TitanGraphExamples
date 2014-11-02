package com.syncleus.titangraph.example.titangods;

import com.tinkerpop.frames.*;
import com.tinkerpop.frames.annotations.gremlin.GremlinGroovy;

public interface Location {
    @Property("name")
    public String getName();

    @Property("type")
    public String getType();

    @GremlinGroovy("it.in('lives')")
    public Iterable<God> getResidents();
}
