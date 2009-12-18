package model;

/**
 * The Student template.
 */

import java.util.Vector;

public class Student {
    private int id;
    private String name;
    private Vector courseTaken;
    private Vector courseToTake;

    public Student(int id, String name, String ctaken) {
        this.id = id;
        this.name = name;
        courseTaken = new Vector();

        if (!ctaken.equals("0")) {
            String[] token = ctaken.split(":");

            for (int i = 0; i < token.length; i++) {
                courseTaken.add(token[i]);
            }
        }

        courseToTake = new Vector();
    }
}