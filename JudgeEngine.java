import model.*;
import jess.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

public class JudgeEngine {
    private Rete engine;
    private WorkingMemoryMarker marker;
    private SystemDatabase database;

    public JudgeEngine(SystemDatabase aDatabase) throws JessException {
        engine = new Rete();
        engine.reset();

        engine.batch("judge.clp");

        database = aDatabase;

        Collection courses = database.getCourses();

        for (Iterator iter = courses.iterator(); iter.hasNext(); ) {
            Course course = (Course) iter.next();
            Fact f = new Fact("Course", engine);
            f.setSlotValue("id", new Value(course.getID(), RU.SYMBOL));
            f.setSlotValue("section", new Value(course.getSection(), RU.SYMBOL));
            f.setSlotValue("days", new Value(course.getDays(), RU.SYMBOL));
            f.setSlotValue("start", new Value(course.getStart(), RU.INTEGER));
            f.setSlotValue("end", new Value(course.getEnd(), RU.INTEGER));

            ValueVector prerequisite = new ValueVector();
            Vector pre = course.getPrerequisite();
            for (Iterator iter2 = pre.iterator(); iter2.hasNext(); ) {
                String cour = (String) iter2.next();
                prerequisite.add(new Value(cour, RU.SYMBOL));
            }
            f.setSlotValue("prerequisite", new Value(prerequisite, RU.LIST));

            engine.assertFact(f);
        }

        // marker = engine.mark();
        // engine.executeCommand("(facts)");
    }

    private void loadStudentsData(int sID) throws JessException {
        Student student = database.getStudent(sID);

        if (student != null) {
            Fact f = new Fact("Student", engine);
            f.setSlotValue("id", new Value(sID, RU.INTEGER));
            f.setSlotValue("name", new Value(student.getName(), RU.SYMBOL));

            ValueVector courseTaken = new ValueVector();
            for (Iterator iter = student.getCoursesTaken().iterator(); iter.hasNext(); ) {
                String course = (String) iter.next();
                courseTaken.add(new Value(course, RU.SYMBOL));
            }
            f.setSlotValue("courseTaken", new Value(courseTaken, RU.LIST));

            engine.assertFact(f);
        }
    }

    public Iterator run(int sID, String cID, String secID) throws JessException {
        //engine.resetToMark(marker);
        
        loadStudentsData(sID);

        Fact f = new Fact("Register", engine);
        f.setSlotValue("sID", new Value(sID, RU.INTEGER));
        f.setSlotValue("cID", new Value(cID, RU.SYMBOL));
        f.setSlotValue("secID", new Value(secID, RU.SYMBOL));

        engine.assertFact(f);

        engine.run();
        engine.executeCommand("(facts)");
        return engine.getObjects(new Filter.ByClass(Decision.class));
    }
}