import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CrsData implements Serializable {
    private static ArrayList<Course> course = new ArrayList<>();
    private static ArrayList<Course> courseFaculty = new ArrayList<>();
    private static ArrayList<Course> courseStudent = new ArrayList<>();
    private static ArrayList<Admin> admins = new ArrayList<>();
    private static ArrayList<Student> students = new ArrayList<>();
    private static ArrayList<Faculty> facultys = new ArrayList<>();
    
    
    
    public static void addCourse(Course c){
        course.add(c);
    }
    
    public static void addCourseFaculty(Course c){
        courseFaculty.add(c);
    }
    
    public static void addCourseStudent(Course c){
        courseStudent.add(c);
    }
    
    public static void updateCourse(Course courseUpdate){
        updateCourseFaculty(courseUpdate);
        updateCourseStudent(courseUpdate);
    }
    
    public static void updateCourseFaculty(Course courseUpdate){
        for(int i = 0 ; i < courseFaculty.size(); i++){
            if(courseUpdate.getCourseId().equals(courseFaculty.get(i).getCourseId())){
                courseFaculty.get(i).setMaxStudentsRegister(courseUpdate.getMaxStudentsRegister());
                courseFaculty.get(i).setCourseSection(courseUpdate.getCourseSection());
                courseFaculty.get(i).setCourseLocation(courseUpdate.getCourseLocation());
                System.out.println("Successfully update course in courseFacultys");
                return;
            }
        }
    }
    
    public static void updateCourseStudent(Course courseUpdate){
        for(int i = 0 ; i < courseStudent.size(); i++){
            if(courseUpdate.getCourseId().equals(courseStudent.get(i).getCourseId())){
                courseStudent.get(i).setMaxStudentsRegister(courseUpdate.getMaxStudentsRegister());
                courseStudent.get(i).setCourseSection(courseUpdate.getCourseSection());
                courseStudent.get(i).setCourseLocation(courseUpdate.getCourseLocation());
                System.out.println("Successfully update course in courseStudents");
                return;
            }
        }
    }
    
    public static void removeCourse(String courseId){
        for(int i = 0 ; i < course.size(); i++){
            if(courseId.equals(course.get(i).getCourseId())){
                course.remove(i);
                removeCourseFaculty(courseId);
                System.out.println("Successfully remove course from courses");
                return;
            }
        }
    }
    
    public static void removeCourseFaculty(String courseId){
        for(int i = 0 ; i < courseFaculty.size(); i++){
            if(courseId.equals(courseFaculty.get(i).getCourseId())){
                courseFaculty.remove(i);
                removeCourseStudent(courseId);
                System.out.println("Successfully remove course from courseFacultys");
                return;
            }
        }
    }
    
    public static void removeCourseStudent(String courseId){
        for(int i = 0 ; i < courseStudent.size(); i++){
            if(courseId.equals(courseStudent.get(i).getCourseId())){
                courseStudent.remove(i);
                System.out.println("Successfully remove course from courseStudents");
                return;
            }
        }
    }

    public static void setCourse(ArrayList<Course> course) {
        CrsData.course = course;
    }

    public static ArrayList<Course> getCourse() {
        return course;
    }

    public static void setCourseFaculty(ArrayList<Course> courseFaculty) {
        CrsData.courseFaculty = courseFaculty;
    }

    public static ArrayList<Course> getCourseFaculty() {
        return courseFaculty;
    }

    public static void setCourseStudent(ArrayList<Course> courseStudent) {
        CrsData.courseStudent = courseStudent;
    }

    public static ArrayList<Course> getCourseStudent() {
        return courseStudent;
    }

    public static void setAdmins(ArrayList<Admin> admins) {
        CrsData.admins = admins;
    }

    public static ArrayList<Admin> getAdmins() {
        return admins;
    }

    public static void setFacultys(ArrayList<Faculty> facultys) {
        CrsData.facultys = facultys;
    }

    public static ArrayList<Faculty> getFacultys() {
        return facultys;
    }

    public static void setStudents(ArrayList<Student> students) {
        CrsData.students = students;
    }

    public static ArrayList<Student> getStudents() {
        return students;
    }
    
    public static void writeFile(List<String> content, String filePath) throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
        PrintWriter pw = new PrintWriter(bw);
        for(String line : content){
            pw.println(line);        
        }
        pw.flush();
        pw.close();
    }
    
    public static ArrayList<String> getFileText(String filePath) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
        ArrayList<String> result = new ArrayList<>();
        String line;
        while((line=br.readLine())!=null){
            result.add(line);
        }
        br.close();
        return result;
    }


    public static void desequelizeUser() throws IOException{
        ArrayList <Student> studentList = new ArrayList<>();
        ArrayList <Faculty> facultyList = new ArrayList<>();
        ArrayList <Admin> adminList = new ArrayList<>();
        ArrayList <String> fileText = getFileText("User.csv");
        for(String line : fileText){
            List<String> entries = Arrays.asList(line.split(","));
            if("Admin".equals(entries.get(0))){
                adminList.add(new Admin(entries.get(1), entries.get(2), entries.get(3), entries.get(4), entries.get(5)));
            }
            if("Faculty".equals(entries.get(0))){
                facultyList.add(new Faculty(entries.get(1), entries.get(2), entries.get(3), entries.get(4), entries.get(5)));
            }
            if("Student".equals(entries.get(0))){
                studentList.add(new Student(entries.get(1), entries.get(2), entries.get(3), entries.get(4), entries.get(5)));
            }
        }
        setAdmins(adminList);
        setFacultys(facultyList);
        setStudents(studentList);
    }
    
    public static void desequelizeCourse() throws IOException{
        ArrayList <Course> courses = new ArrayList<>();
        ArrayList <String> fileText = getFileText("Course.csv");
        for(String line : fileText){
            List<String> entries = Arrays.asList(line.split(","));
            Course courseInput = new Course(entries.get(0), entries.get(1), Integer.parseInt(entries.get(2)), entries.get(3), Integer.parseInt(entries.get(4)));
            courses.add(courseInput);
        }
        setCourse(courses);
    }
    
    public static void desequelizeCourseFaculty() throws IOException{
        ArrayList <Course> courses = new ArrayList<>();
        ArrayList <String> fileText = getFileText("CourseFaculty.csv");
        for(String line : fileText){
            List<String> entries = Arrays.asList(line.split(","));
            Course courseInput = new Course(new Course(entries.get(0), entries.get(1), Integer.parseInt(entries.get(2)), entries.get(3), Integer.parseInt(entries.get(4))),entries.get(5));
            courses.add(courseInput);
        }
        setCourseFaculty(courses);
    }
    
    public static void desequelizeCourseStudent() throws IOException{
        ArrayList <Course> courses = new ArrayList<>();
        ArrayList <String> fileText = getFileText("CourseStudent.csv");
        for(String line : fileText){
            List<String> entries = Arrays.asList(line.split(","));
            Course courseInput = new Course(new Course(entries.get(0), entries.get(1), Integer.parseInt(entries.get(2)), entries.get(3), Integer.parseInt(entries.get(4))),entries.get(6));
            for(int i = 7; i < entries.size(); i++){
                courseInput.addStudentRegisterList(entries.get(i));
            }
            courses.add(courseInput);
        }
        setCourseStudent(courses);
    }
    
    public static void sequelizeUser() throws IOException {
        ArrayList <String> userList = new ArrayList<>();
        for(Admin user : admins){
            userList.add("Admin,"+user.toString());
        }
        
        for(Faculty user : facultys){
            userList.add("Faculty,"+user.toString());
        }
        
        for(Student user : students){
            userList.add("Student,"+user.toString());
        }
        writeFile(userList, "User.csv");
        System.out.println("Successfully sequelize");
    }
    
    public static void sequelizeCourse() throws IOException {
        ArrayList <String> courseList = new ArrayList<>();
        for(Course c : course){
            courseList.add(c.toString());
        }
        writeFile(courseList, "Course.csv");
        System.out.println("Successfully sequelize course");
    }
    
    public static void sequelizeCourseFaculty() throws IOException {
        ArrayList <String> courseFacultyList = new ArrayList<>();
        for(Course c : courseFaculty){
            courseFacultyList.add(c.toStringFaculty());
        }
        writeFile(courseFacultyList, "CourseFaculty.csv");
        System.out.println("Successfully sequelize course faculty");
    }
    
    public static void sequelizeCourseStudent() throws IOException {
        ArrayList <String> courseStudentList = new ArrayList<>();
        for(Course c : courseStudent){
            courseStudentList.add(c.toStringStudent());
        }
        writeFile(courseStudentList, "CourseStudent.csv");
        System.out.println("Successfully sequelize course student");
    }
   
}
