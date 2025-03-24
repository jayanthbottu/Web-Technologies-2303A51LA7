public class Student {
    private String name, email, password, course;

    public Student(String name, String email, String password, String course) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.course = course;
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public String getCourse() { return course; }
}