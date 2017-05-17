package me.snov.newrelic.elasticsearch.services;

import me.snov.newrelic.elasticsearch.responses.ClusterHealth;

public class ClusterHealthService {

    public boolean isYellow(ClusterHealth clusterHealth) {
        return "yellow".equals(clusterHealth.status);
    }

    public boolean isRed(ClusterHealth clusterHealth) {
        return "red".equals(clusterHealth.status);
    }
}
