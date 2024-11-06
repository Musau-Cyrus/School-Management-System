package Database;

public class Student {
    private String name;
    private String id;
    private String age;
    private String studentClass;
    private String contactInfo;

    public Student(String name, String id, String age, String studentClass, String contactInfo) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.studentClass = studentClass;
        this.contactInfo = contactInfo;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getAge() {
        return age;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getClassName() {
        return studentClass;
    }

}