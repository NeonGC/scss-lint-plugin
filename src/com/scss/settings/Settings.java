package com.scss.settings;

import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;

@State(name = "ScssLintProjectComponent",
        storages = {
                @Storage(id = "default", file = StoragePathMacros.PROJECT_FILE),
                @Storage(id = "dir", file = StoragePathMacros.PROJECT_CONFIG_DIR + "/scssLintPlugin.xml", scheme = StorageScheme.DIRECTORY_BASED)})
public class Settings implements PersistentStateComponent<Settings> {
//    public static final String DEFAULT_ESLINT_RC = ScssLintFinder.ESLINTRC;
    public static final String DEFAULT_RULES_DIR = "";
    // public static final String DEFAULT_ESLINT_EXE = ""; // TODO use default
    public static final Boolean DEFAULT_PLUGIN_ENABLED = false;

    public String scssLintConfigFile = "";
    public String rulesPath = DEFAULT_RULES_DIR;
    public String eslintExecutable = "";
    public String nodeInterpreter;
    public boolean treatAllIssuesAsWarnings;
    public boolean pluginEnabled = DEFAULT_PLUGIN_ENABLED;

    protected Project project;

    public static Settings getInstance(Project project) {
        Settings settings = ServiceManager.getService(project, Settings.class);
        settings.project = project;
        return settings;
    }

    @Nullable
    @Override
    public Settings getState() {
        return this;
    }

    @Override
    public void loadState(Settings state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public String getVersion() {
        return nodeInterpreter + eslintExecutable + scssLintConfigFile + rulesPath;
    }
}
