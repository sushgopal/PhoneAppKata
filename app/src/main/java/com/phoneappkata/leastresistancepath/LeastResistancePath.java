package com.phoneappkata.leastresistancepath;

import org.apache.commons.lang3.StringUtils;

public class LeastResistancePath {

    private static String YES = "YES";

    private static String NO = "NO";

    private char DELIMITER = ' ';

    private String resistance;

    private String path;

    private String canFlow;


    public LeastResistancePath(ResistancePath path) {
        this.resistance = getResistance(path);
        this.path = getPath(path);
        this.canFlow = canFlow(path);

    }

    public String resistance() {
        return resistance;
    }

    public String path() {
        return path;
    }

    public String canFlow() {
        return canFlow;
    }

    private String canFlow(ResistancePath path) {
        return path.canFlow() ? YES: NO;
    }

    private String getPath(ResistancePath path) {
        return  StringUtils.join(path.getPath(), DELIMITER);
    }

    private String getResistance(ResistancePath path) {
        return Integer.toString(path.getResistance());
    }


}
