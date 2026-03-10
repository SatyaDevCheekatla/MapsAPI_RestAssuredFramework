package org.satya.utils;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private final Map<String, String> variables;
    private static ScenarioContext instance;

    private ScenarioContext() {
        this.variables = new HashMap<>();
    }

    // Singleton instance getter
    public static synchronized ScenarioContext getInstance() {
        if (instance == null) {
            instance = new ScenarioContext();
        }
        return instance;
    }

    // Reset for new test run (call from Hooks)
    public static synchronized void reset() {
        instance = null;
    }

    public void setValue(String key, String value) {
        variables.put(key, value);
    }

    public String getValue(String key) {
        return variables.get(key);
    }
}
