package org.example.runners;

import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
//@SelectClasspathResource("features_old/login.feature")
@SelectClasspathResource("features/settings.feature")
//@SelectClasspathResource("features/form_pengajuan.feature")
//@SelectClasspathResource("features_old/logout.feature")
public class RunTest {
}