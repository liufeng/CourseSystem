package model;

/**
 * The Course template.
 */

import java.util.Vector;

public class Course {
    private String id;
    private String section;
    private Vector prerequisite;
    private String days;
    private int start;
    private int end;

    public Course(String id, String section, String pre, String days, int start, int end) {
        this.id = id;
        this.section = section;

        if (pre.equals("0")) {  // no prerequisite
            prerequisite = null;
        } else {
            String[] token = pre.split(":");
            prerequisite = new Vector();

            for (int i = 0; i < token.length; i++) {
                prerequisite.add(token[i]);
            }
        }

        this.days = days;
        this.start = start;
        this.end = end;
    }

    public Vector getPrerequisite() {
        return prerequisite;
    }
}