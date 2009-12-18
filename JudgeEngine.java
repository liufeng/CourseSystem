import model.*;
import jess.*;
import java.util.Iterator;

public class JudgeEngine {
    private Rete engine;
    private WorkingMemoryMarker marker;
    private SystemDatabase database;

    public JudgeEngine(SystemDatabase aDatabase) throws JessException {
        engine = new Rete();
        engine.reset();

        engine.batch("judge.clp");

        database = aDatabase;
        engine.addAll(database.getCourses());
        // engine.addAll(database.getStudents());

        // loadCoursesData();

        marker = engine.mark();
    }
    /*
    private void loadCoursesData() throws JessException {
        Courses courses = database.getCourses();

        for (Iterator iter = courses.iterator(); iter.hasNext();) {
            Course course = (Course) iter.next();
            engine.add(course);
        }
    }
    */

    private void loadStudentsData(int sID) throws JessException {
        Student student = database.getStudent(sID);

        if (student != null) {
            engine.add(student);
            // engine.addAll(student.getCourseTaken());
        }
    }

    private Iterator run(int sID, String cID, String secID) throws JessException {
        engine.resetToMark(marker);
        
        loadStudentsData(sID);
        engine.add(new Register(sID, cID, secID));

        engine.run();

        return engine.getObjects(new Filter.ByClass(Decision.class));
    }
}