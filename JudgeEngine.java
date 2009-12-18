import model.*;
import jess.*;

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
        engine.addAll(database.getStudents());

        marker = engine.mark();
    }
}