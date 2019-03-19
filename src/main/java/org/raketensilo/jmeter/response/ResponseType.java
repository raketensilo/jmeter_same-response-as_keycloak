package org.raketensilo.jmeter.response;

public enum ResponseType {

    ACTUAL, EXPECTED;

    public String asSuffix() {
        switch (this) {
            case ACTUAL:
                return "_response_actual.json";
            case EXPECTED:
                return "_response_expected.json";
            default:
                return ".json";
        }
    }

}