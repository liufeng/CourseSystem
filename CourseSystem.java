import model.*;

import java.util.Iterator;

import jess.JessException;

public class CourseSystem {
    public static void main(String[] args) {
         try {
             SystemDatabase database = new SystemDatabase();
             JudgeEngine engine = new JudgeEngine(database);

             register(database, engine, 6800001, "comp4200", "a01");
        } catch (JessException e) {
             e.printStackTrace();
        }
    }

    private static void register(SystemDatabase database, JudgeEngine engine,
                                 int sID, String cID, String secID) throws JessException {
        Iterator decision;

        decision = engine.run(sID, cID, secID);

        while (decision.hasNext()) {
            System.out.println("   " + decision.next());
        }
        System.out.println();
    }
}