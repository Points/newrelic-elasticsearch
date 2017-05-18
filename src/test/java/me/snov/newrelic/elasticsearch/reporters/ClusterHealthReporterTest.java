package me.snov.newrelic.elasticsearch.reporters;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import me.snov.newrelic.elasticsearch.interfaces.AgentInterface;
import me.snov.newrelic.elasticsearch.parsers.ClusterHealthParser;
import me.snov.newrelic.elasticsearch.responses.ClusterHealth;

@RunWith(MockitoJUnitRunner.class)
public class ClusterHealthReporterTest {

    private ClusterHealthReporter clusterHealthReporter;
    private ClusterHealth clusterHealth;
    @Mock private AgentInterface agent;

    @Before
    public void setUp() throws Exception {
        clusterHealthReporter = new ClusterHealthReporter(agent);
        clusterHealth = new ClusterHealthParser().parse(getClass().getResourceAsStream("/resources/cluster_health_5.2.2.json"));
    }

    @Test
    public void shouldReportGreenStatusCorrectly() throws Exception {
        clusterHealth.status = "green";

        clusterHealthReporter.reportClusterHealth(clusterHealth);

        verify(agent).reportMetric("V1/ClusterHealth/Status/IsYellow", "bool", 0);
        verify(agent).reportMetric("V1/ClusterHealth/Status/IsRed", "bool", 0);
    }

    @Test
    public void shouldReportRedStatusCorrectly() throws Exception {
        clusterHealth.status = "red";

        clusterHealthReporter.reportClusterHealth(clusterHealth);

        verify(agent).reportMetric("V1/ClusterHealth/Status/IsYellow", "bool", 0);
        verify(agent).reportMetric("V1/ClusterHealth/Status/IsRed", "bool", 1);
    }

    @Test
    public void shouldReportYellowStatusCorrectly() throws Exception {
        clusterHealth.status = "yellow";

        clusterHealthReporter.reportClusterHealth(clusterHealth);

        verify(agent).reportMetric("V1/ClusterHealth/Status/IsYellow", "bool", 1);
        verify(agent).reportMetric("V1/ClusterHealth/Status/IsRed", "bool", 0);
    }
}
