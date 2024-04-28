package com.premsan.endless.base;

import java.io.Serializable;
import java.net.URL;
import java.util.UUID;

public class Cluster implements Serializable {

    private static final long serialVersionUID = 1L;

    private final long creationTimeMillis;
    private final UUID id;
    private final URL url;

    public Cluster(final long creationTimeMillis, final UUID id, final URL url) {
        this.creationTimeMillis = creationTimeMillis;
        this.id = id;
        this.url = url;
    }

    public long getCreationTimeMillis() {
        return creationTimeMillis;
    }

    public UUID getId() {
        return id;
    }

    public URL getUrl() {
        return url;
    }
}
