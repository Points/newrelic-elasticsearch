package me.snov.newrelic.elasticsearch.parsers;

import me.snov.newrelic.elasticsearch.responses.ClusterHealth;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ClusterHealthParserTest {

    private ClusterHealthParser clusterHealthParser;

    @Before
    public void setUp() {
        clusterHealthParser = new ClusterHealthParser();
    }

    private ClusterHealth parseJson(String path) throws IOException {
        return clusterHealthParser.parse(getClass().getResourceAsStream(path));
    }

    @Test
    public void testV522() throws Exception {
        ClusterHealth clusterHealth = parseJson("/resources/cluster_health_5.2.2.json");
        assertEquals("green", clusterHealth.status);
        assertFalse(clusterHealth.timed_out);
        assertEquals(8, clusterHealth.number_of_nodes.longValue());
        assertEquals(5, clusterHealth.number_of_data_nodes.longValue());
        assertEquals(106, clusterHealth.active_primary_shards.longValue());
        assertEquals(170, clusterHealth.active_shards.longValue());
        assertEquals(0, clusterHealth.relocating_shards.longValue());
        assertEquals(0, clusterHealth.initializing_shards.longValue());
        assertEquals(0, clusterHealth.unassigned_shards.longValue());
        assertEquals(0, clusterHealth.delayed_unassigned_shards.longValue());
        assertEquals(0, clusterHealth.number_of_pending_tasks.longValue());
        assertEquals(0, clusterHealth.number_of_in_flight_fetch.longValue());
        assertEquals(0, clusterHealth.task_max_waiting_in_queue_millis.longValue());
        assertEquals(100.0, clusterHealth.active_shards_percent_as_number.floatValue(), 0.0);
        
    }

}
