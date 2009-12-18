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

    public String toString() {
        String result = "";

        if (canTakeTheCourse) {
            result += "Register success!";
        } else {
            result += "Can't Register this course, because: " + reason;
        }

        return result;
    }
}