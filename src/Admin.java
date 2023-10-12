import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Admin extends User implements AdminInterface{
    
     public Admin() {
    }
    
    
    public Admin(String firstName, String midName, String lastName, String username, String password) {
        super(username, password, firstName, midName, lastName);
    }
    
    @Override
    public void creatCourse(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter New Course");
        System.out.println("=========================");
        System.out.print("Enter Course Id: ");
        String courseId = sc.nextLine();
        System.out.print("Enter Course Name: ");
        String courseName = sc.nextLine();
        System.out.print("Enter Course Max Number Student Register: ");
        int courseMaxNumber = Integer.parseInt( sc.nextLine());
        System.out.print("Enter Course Section: ");
        int courseSection = Integer.parseInt( sc.nextLine());
        System.out.print("Enter Course Location: ");
        String courseLocation = sc.nextLine();
        System.out.println("=========================");

        if(CrsData.getCourse().stream().anyMatch(c->courseId.equals(c.getCourseId()))){
            System.out.println("Course Have Already Existed\n");
            return;
        }
        Course course = new Course(courseId.trim(), courseName.trim(), courseSection, courseLocation.trim(), courseMaxNumber);

        CrsData.getCourse().add(course);
        System.out.println();
    }

    @Override
    public void deleteCourse() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Remove Course");
        System.out.println("=========================");
        System.out.print("Enter Course Id: ");
        String courseId = sc.nextLine();
        System.out.print("Enter Course Name: ");
        String courseName = sc.nextLine();
        System.out.println("=========================");
        
        if(CrsData.getCourse().stream().noneMatch(s->courseId.equals(s.getCourseId())&&courseName.equals(s.getCourseName()))){
            System.out.println("No Course Found\n");
            return;
        }
        
        CrsData.removeCourse(courseId);
        System.out.println();
    }

    @Override
    public void editCourse() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Edit Course");
        System.out.println("=========================");
        System.out.print("Enter Course Id: ");
        String courseId = sc.nextLine();
        System.out.print("Enter Course Name: ");
        String courseName = sc.nextLine();
        System.out.println("=========================");
        
        if(CrsData.getCourse().stream().noneMatch(s->courseId.equals(s.getCourseId())&&courseName.equals(s.getCourseName()))){
            System.out.println("No Course Found\n");
            return;
        }
        
        for(Course c : CrsData.getCourse()){
            if(courseId.equals(c.getCourseId())&&courseName.equals(c.getCourseName())){
                while(true){
                    //Ask user what they want to edit
                    System.out.print("What would you like to edit?\n");
                    System.out.print("1. Max amount of students\n");
                    System.out.print("2. Section Number\n");
                    System.out.print("3. Class location\n");
                    System.out.print("4. Done\n");
                    int choice = Integer.parseInt(sc.nextLine());
                    if (choice == 1) {
                        System.out.print("New max amount of students: ");
                        int newMax = Integer.parseInt(sc.nextLine());
                        c.setMaxStudentsRegister(newMax);
                    }
                    if (choice == 2) {
                        System.out.print("New section number: ");
                        int newSection = Integer.parseInt(sc.nextLine());
                        c.setCourseSection(newSection);
                    }
                    if (choice == 3) {
                        System.out.print("New course location: ");
                        String newLocation = sc.nextLine();
                        c.setCourseLocation(newLocation);
                    }
                    if (choice == 4) {
                        CrsData.updateCourse(c);
                        System.out.println("Successfully update course");
                        break;
                    }
                    System.out.println("=========================");
                }
            }
        }
        System.out.println(); 
    }

    @Override
    public void displayCourseById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Display Course Infor");
        System.out.println("=========================");
        System.out.print("Enter Course Id: ");
        String courseId = sc.nextLine();
        System.out.println("=========================");
        for(Course c : CrsData.getCourse()){
            if(courseId.equals(c.getCourseId())){
                System.out.println("Course ID: " + c.getCourseId()+ "\n" 
                                + "Course Name: " + c.getCourseName()+ "\n" 
                                + "Maximum Number Students Register: " + c.getMaxStudentsRegister() + "\n" 
                                + "Course Section: " + c.getCourseSection() + "\n" 
                                + "Location: "+ c.getCourseLocation()+"\n");
                return;
            } 
        }
        System.out.println("No Course Found\n");
    
    }

    @Override
    public void registerStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Register Student Infor");
        System.out.println("=========================");
        System.out.print("Enter First Name: ");
        String firstName = sc.nextLine();
        System.out.print("Enter Mid Name: ");
        String midName = sc.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = sc.nextLine();
        System.out.print("Enter Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Password: ");
        String passowrd = sc.nextLine();
        System.out.println("=========================");
        
        Student student = new Student(firstName.trim(), midName.trim(), lastName.trim(), username.trim(), passowrd.trim());
        
        if(CrsData.getStudents().stream().noneMatch(s->student.getName().equals(s.getName()))){
                CrsData.getStudents().add(student);
                System.out.println("Successfully Register Student\n");
        }
        
        System.out.println("=========================");
        System.out.print("Register Student To Course ?\n");
        System.out.print("1.Yes\n");
        System.out.print("2.No\n");
        int choice = Integer.parseInt(sc.nextLine());
        System.out.println("==========");
        if(choice==1){
            student.registerToCourse();
        }
        System.out.println();
    }
    
    @Override
    public void registerFaculty() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Register Faculty Infor");
        System.out.println("=========================");
        System.out.print("Enter First Name: ");
        String firstName = sc.nextLine();
        System.out.print("Enter Mid Name: ");
        String midName = sc.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = sc.nextLine();
        System.out.print("Enter Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Password: ");
        String passowrd = sc.nextLine();
        System.out.println("=========================");
        
        Faculty faculty = new Faculty(firstName, midName, lastName, username, passowrd);
        
       if(CrsData.getStudents().stream().noneMatch(s->faculty.getName().equals(s.getName()))){
                CrsData.getFacultys().add(faculty);
                System.out.println("Successfully Register Faculty\n");
        }
        
        System.out.print("Register Faculty To Course ?\n");
        System.out.print("1.Yes\n");
        System.out.print("2.No\n");
        int choice = Integer.parseInt(sc.nextLine());
        System.out.println("==========");
        if(choice==1){
            faculty.registerToCourse();
        }
        System.out.println();
    }

    @Override
    public void adminViewAllCourses() {
        System.out.println("All Course Of University");
        System.out.println("=========================");
        for(Course c : CrsData.getCourse()){
            Course course;
            course = CrsData.getCourseFaculty().stream().filter(cf->cf.getCourseId().equals(c.getCourseId())).findFirst().orElse(c);
            course = CrsData.getCourseStudent().stream().filter(cs->cs.getCourseId().equals(c.getCourseId())).findFirst().orElse(course);
            System.out.println("Course ID: " + course.getCourseId()+ "\n" 
                                + "Course Name: " + course.getCourseName()+ "\n" 
                                + "Maximum Number Students Register: " + course.getMaxStudentsRegister() + "\n" 
                                + "Current Number Students Register: " + course.getCurrentStudentsRegister() + "\n"  
                                + "Course Instructor: " + course.getInstructor() + "\n" 
                                + "Course Section: " + course.getCourseSection() + "\n" 
                                + "Location: "+ course.getCourseLocation());
            if(course.getCurrentStudentsRegister()!=0){
                System.out.println("==========");
                int cnt = 1;
                for(String studentName : course.getStudentRegisterList()){
                    System.out.println(cnt+". "+studentName);
                    cnt++;
                }
            }
            System.out.println("=================\n");
        }
        System.out.println();
    }

    @Override
    public void viewFullCourses() {
        System.out.println("All Full Course Of University");
        System.out.println("=========================");
        if(CrsData.getCourseStudent().stream().noneMatch(s->s.getMaxStudentsRegister()==s.getCurrentStudentsRegister())){
            System.out.println("No Course Full\n");
            return;
        }
         for(Course c : CrsData.getCourseStudent()){
            if(c.getCurrentStudentsRegister()==c.getMaxStudentsRegister()){
                System.out.println("Course ID: " + c.getCourseId()+ "\n" 
                                + "Course Name: " + c.getCourseName()+ "\n" 
                                + "Current Number Students Register: " + c.getCurrentStudentsRegister() + "\n" 
                                + "Maximum Number Students Register: " + c.getMaxStudentsRegister() + "\n" 
                                + "Course Instructor: " + c.getInstructor() + "\n" 
                                + "Course Section: " + c.getCourseSection() + "\n" 
                                + "Location: "+ c.getCourseLocation());
                System.out.println("==========");
            } 
        }
         System.out.println();
    }

    @Override
    public void viewRegisteredStudents() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Display Registered Students Course");
        System.out.println("=========================");
        System.out.print("Enter Course Id: ");
        String courseId = sc.nextLine();
        System.out.println("=========================");
        
         if(CrsData.getCourse().stream().noneMatch(cf->courseId.equals(cf.getCourseId()))){
                System.out.println("No Course Found\n");
                return;
        }
         
         if(CrsData.getCourseFaculty().stream().noneMatch(cf->courseId.equals(cf.getCourseId()))){
                System.out.println("Course Has No Instructor Register\n");
                return;
        }
        
         for(Course c : CrsData.getCourseStudent()){
            if(courseId.equals(c.getCourseId())){
                System.out.println("Course ID: " + c.getCourseId()+ "\n" 
                                + "Course Name: " + c.getCourseName()+ "\n" 
                                + "Maximum Number Students Register: " + c.getMaxStudentsRegister() + "\n" 
                                + "Current Number Students Register: " + c.getCurrentStudentsRegister() + "\n"
                                + "Course Instructor: " + c.getInstructor() + "\n" 
                                + "Course Section: " + c.getCourseSection() + "\n" 
                                + "Location: "+ c.getCourseLocation());
                if(c.getCurrentStudentsRegister()!=0){
                    System.out.println("==========");
                    int cnt = 1;
                    for(String studentName : c.getStudentRegisterList()){
                        System.out.println(cnt+". "+studentName);
                        cnt++;
                    }
                    System.out.println("==========");
                }else{
                    System.out.println("No Student Register");
                }
                System.out.println();
                return;
            } 
        }
         System.out.println("Course Have No Student Register\n");
    }

    @Override
    public void viewAllStudentCourses() {
        Scanner sc = new Scanner(System.in);
        System.out.println("View All Student Courses");
        System.out.println("=========================");
        System.out.print("Enter First Name: ");
        String firstName = sc.nextLine();
        System.out.print("Enter Mid Name: ");
        String midName = sc.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = sc.nextLine();
        System.out.println("=========================");
        String name = firstName.trim()+" "+midName.trim()+" "+lastName.trim();
        
        
        
        if(CrsData.getStudents().stream().noneMatch(s->s.getName().equals(name))){
                System.out.println("No Students Found\n");
                return;
        }
         
        if(CrsData.getCourseStudent().stream().noneMatch(cs->cs.getStudentRegisterList().contains(name))){
                System.out.println("Student Have Not Registered Any Course\n");
                return;
        }
        
        for(Course course : CrsData.getCourseStudent()){
            if(course.getStudentRegisterList().contains(name)){
                    System.out.println("Course ID: " + course.getCourseId()+ "\n" 
                                     + "Course Name: " + course.getCourseName()); 
                    System.out.println("==========");
            }
        }
        System.out.println();
    }

    @Override
    public void sortCourses() {
        ArrayList<Course> newCourseStudent = new ArrayList<>(CrsData.getCourseStudent());
        newCourseStudent.sort((a,b)->b.getCurrentStudentsRegister()-a.getCurrentStudentsRegister());
        CrsData.setCourseStudent(newCourseStudent);
    }

    @Override
    public void writeToFileFullCourse() {
        ArrayList<String> fullCourses = new ArrayList<>();
        for(Course c : CrsData.getCourseStudent()){
            if(c.getCurrentStudentsRegister()==c.getMaxStudentsRegister()){
               fullCourses.add(c.toStringStudent());
            } 
        }
        
        try {
            CrsData.writeFile(fullCourses, "FullCourse.csv");
            System.out.println("All Full Course Have Successfully written");
        } catch (IOException e) {
            e.printStackTrace();
        }
         
    }
    
}
