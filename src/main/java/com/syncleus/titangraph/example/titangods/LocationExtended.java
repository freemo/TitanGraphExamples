package com.syncleus.titangraph.example.titangods;

import com.tinkerpop.frames.Property;

public interface LocationExtended extends Location {
    @Property("other")
    public String getOther();
}
