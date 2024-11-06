package Functionality;


public class Data {
    private String name;
    private String phoneNumber;
    private String className;

    public Data(String name, String phoneNumber, String className) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.className = className;

    }

    public String getName () {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone Number: " + phoneNumber + ", Class: " + className;
    }
}