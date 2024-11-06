package Database;

public class ClassDetails {
    private String classId;
    private String className;
    private String teacherIncharge;

    public ClassDetails(String classId, String className, String teacherIncharge) {
        this.classId = classId;
        this.className = className;
        this.teacherIncharge = teacherIncharge;
    }

    public String getClassId() {
        return classId;
    }

    public String getClassName() {
        return className;
    }

    public String getTeacherIncharge() {
        return teacherIncharge;
    }
}