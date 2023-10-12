import java.util.ArrayList;

public class Course {
    private String courseId;
    private String courseName;
    private int maxStudentsRegister;
    private int currentStudentsRegister;
    private ArrayList<String> studentRegisterList = new ArrayList<>();
    private String instructor;
    private int courseSection;
    private String courseLocation;

    public Course() {
    }

    public Course(String courseId, String courseName, int courseSection, String courseLocation, int maxStudentsRegister) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.maxStudentsRegister = maxStudentsRegister;
        this.courseSection = courseSection;
        this.courseLocation = courseLocation;
    }
    
    public Course(Course course,String facultyName) {
        this(course.getCourseId(), course.getCourseName(), course.getCourseSection(), course.getCourseLocation(), course.getMaxStudentsRegister());
        this.instructor = facultyName;
    } 

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getMaxStudentsRegister() {
        return maxStudentsRegister;
    }

    public void setMaxStudentsRegister(int maxStudentsRegister) {
        this.maxStudentsRegister = maxStudentsRegister;
    }

    public int getCurrentStudentsRegister() {
        updateCurrentStudentsRegister();
        return currentStudentsRegister;
    }
    
    public void updateCurrentStudentsRegister() {
        this.currentStudentsRegister = this.studentRegisterList.size();
    }


    public int getCourseSection() {
        return courseSection;
    }

    public void setCourseSection(int courseSection) {
        this.courseSection = courseSection;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String facultyName) {
        this.instructor = facultyName;
    }

    public String getCourseLocation() {
        return courseLocation;
    }

    public void setCourseLocation(String courseLocation) {
        this.courseLocation = courseLocation;
    }

    public ArrayList<String> getStudentRegisterList() {
        return studentRegisterList;
    }

    public void setStudentRegisterList(ArrayList<String> studentRegisterList) {
        this.studentRegisterList = studentRegisterList;
    }
    
    public void addStudentRegisterList(String Studentname) {
        this.studentRegisterList.add(Studentname);
        updateCurrentStudentsRegister();
    }
    
    public void removeStudentRegisterList(String Studentname) {
        this.studentRegisterList.remove(Studentname);
        updateCurrentStudentsRegister();
    }
    
    @Override
    public String toString() {
        // CourseID, CourseName, Section, Location, MaxStudent
        return getCourseId()+","+getCourseName()+","+getCourseSection()+","+getCourseLocation()+","+getMaxStudentsRegister();
    }
    
    public String toStringFaculty() {
        // CourseID, CourseName, Section, Location, MaxStudent, Faculty  
         return getCourseId()+","+getCourseName()+","+getCourseSection()+","+getCourseLocation()+","+getMaxStudentsRegister()+","+getInstructor();
    }
    
    public String toStringStudent() {
        // CourseID, CourseName, Section, Location, MaxStudent, CurentStudent, Faculty, Students
        StringBuilder sb = new StringBuilder(getCourseId()+","+getCourseName()+","+getCourseSection()+","+getCourseLocation()+","+getMaxStudentsRegister()+","+getCurrentStudentsRegister()+","+getInstructor());
        for(String student : getStudentRegisterList()){
            sb.append(","+student);
        }
        return sb.toString();
    }
    
    
}
