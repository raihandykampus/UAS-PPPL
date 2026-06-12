package org.example.runners;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasspathResource("features/dosen.feature")
@ConfigurationParameter(
        key = Constants.GLUE_PROPERTY_NAME,
        value = "org.example.steps, org.example.steps.dosen"
)
@ConfigurationParameter(
        key = Constants.PLUGIN_PROPERTY_NAME,
        value = "pretty, html:target/dosen-report.html"
)
public class DosenRunTest {
}