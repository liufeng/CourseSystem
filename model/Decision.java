package model;

/**
 * The result return by the Jess program.
 */

public class Decision {
    private final boolean canTakeTheCourse;
    private final String reason;

    public Decision(boolean canTakeTheCourse, String aReason) {
        this.canTakeTheCourse = canTakeTheCourse;
        this.reason = aReason;
    }

    public boolean getResult() {
        return canTakeTheCourse;
    }

    public String getReason() {
        return reason;
    }
}