package com.eurotech;

import org.junit.platform.suite.api.*;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("com.eurotech")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.eurotech")
public class RunCucumberTest {
}
