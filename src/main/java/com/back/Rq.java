package com.back;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Rq {
    private final String actionName;
    private final Map<String, String> paramsMap;
    public Rq(String cmd) {
        String[] bits = cmd.split("\\?", 2);

        actionName = bits[0];

        String queryString = bits.length == 2 ? bits[1] : "";
            paramsMap = Arrays.stream(queryString.split("&"))
                    .map(p -> p.split("=", 2))
                    .filter(p -> p.length == 2 && !p[0].trim().isEmpty() && !p[1].trim().isEmpty())
                    .collect(Collectors.toMap(
                            p -> p[0].trim(),
                            p -> p[1].trim()
                    ));
    }

    public String getActionName() {
        return actionName;
    }

    public String getParam(String paramName, String defaultValue) {
        return paramsMap.getOrDefault(paramName, defaultValue);
    }

    public int getParamAsInt(String paramName, int defaultValue) {
        String value = getParam(paramName, null);

        if (value == null || value.isBlank()) return defaultValue;

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }

    }
}
