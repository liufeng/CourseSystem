import model.*;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class SystemDatabase {
    private HashMap courses;
    private HashMap students;

    public SystemDatabase() {
        courses = new HashMap();
        students = new HashMap();

        readCourseData();
        readStudentData();
    }

    private void readCourseData() {
        try {
            BufferedReader fileIn = new BufferedReader(new FileReader("courses.txt"));
            String line = fileIn.readLine();

            while (line != null) {
                String[] token = line.split(",");

                String cID = token[0];
                String secID = token[1];
                String prerequisite = token[2];
                String days = token[3];
                int start = Integer.parseInt(token[4]);
                int end = Integer.parseInt(token[5]);

                Course aCourse = new Course(cID, secID, prerequisite, days, start, end);
                courses.put(cID+secID, aCourse);

                line = fileIn.readLine();
            }

            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readStudentData() {
        try {
            BufferedReader fileIn = new BufferedReader(new FileReader("students.txt"));
            String line = fileIn.readLine();

            while (line != null) {
                String[] token = line.split(",");
                int sID = Integer.parseInt(token[0]);
                String name = token[1];
                String courseTaken = token[2];
                Student aStudent = new Student(sID, name, courseTaken);
                students.put(new Integer(sID), aStudent);

                line = fileIn.readLine();
            }

            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return courses.size() + "\n\n" + students.size();
    }
}
