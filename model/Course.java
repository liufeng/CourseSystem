package model;

/**
 * The Course template.
 */

import java.util.Vector;

public class Course {
    private String id;
    private String section;
    private Vector prequisite;
    private String days;
    private int start;
    private int end;

    public Course(String id, String section, String pre, String days, int start, int end) {
        this.id = id;
        this.section = section;

        if (pre.equals("0")) {  // no prerequisite
            prequisite = null;
        } else {
            String[] token = pre.split(":");
            prequisite = new Vector();

            for (int i = 0; i < token.length; i++)
                prequisite.add(token[i]);
        }

        this.days = days;
        this.start = start;
        this.end = end;

        /****************
        try {
            BufferedReader fileIn = new BufferedReader(new FileReader("courses.txt"));
            String line = fileIn.readLine();
            while (line != null) {

                line = fileIn.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ****************/
    }

    public Vector getPrequisite() {
        return prequisite;
    }
}