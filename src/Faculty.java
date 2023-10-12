import java.util.Scanner;

public class Faculty extends User implements FacultyInterface{


    public Faculty() {
    }
    
    public Faculty(String firstName, String midName, String lastName, String username, String password) {
        super(username, password, firstName, midName, lastName);
    }
    
    
    @Override
    public void falcultyViewAllCourses() {
        System.out.println("All Courses Of University");
        System.out.println("=========================");
        for(Course c : CrsData.getCourse()){
            System.out.println("Course ID: " + c.getCourseId()+ "\n" 
                                + "Course Name: " + c.getCourseName()+ "\n" 
                                + "Maximum Number Students Register: " + c.getMaxStudentsRegister() + "\n" 
                                + "Course Section: " + c.getCourseSection() + "\n" 
                                + "Location: "+ c.getCourseLocation());
            System.out.println("==========");
        }
        System.out.println();
    }

    @Override
    public void registerToCourse() {
         Scanner sc = new Scanner(System.in);
        System.out.println("Enter Course To Register");
        System.out.println("=========================");
        System.out.print("Enter Course Name: ");
        String courseName = sc.nextLine();
        System.out.print("Enter Course Id: ");
        String courseId =  sc.nextLine();
        System.out.println("==========");

        if(CrsData.getCourseFaculty().stream().anyMatch(cf->courseId.equals(cf.getCourseId()))){
            System.out.println("Course Have Been Registered By Another Faculty\n");
            return;
        }
        
   
        for(Course c : CrsData.getCourse()){
            
            if(courseName.equals(c.getCourseName())&&courseId.equals(c.getCourseId())){
                Course newCourseFaculty = new Course(c, this.getName());
                CrsData.getCourseFaculty().add(newCourseFaculty);
                System.out.println("Successfull Register To Course\n");
                return;
            }
        }
        System.out.println("No Course Found\n");   
    }

    @Override
    public void viewAllRegisteredCourses() {
        System.out.println("All Registered Courses");
        System.out.println("=========================");
        
        if(CrsData.getCourseFaculty().stream().noneMatch(cf->cf.getInstructor().equals(this.getName()))){
            System.out.println("Faculty Have No Register Courses\n");
            return;
        }
        
        for(Course c : CrsData.getCourseFaculty()){
            if(this.getName().equals( c.getInstructor())){
                System.out.println("Course ID: " + c.getCourseId()+ "\n" 
                                + "Course Name: " + c.getCourseName()+ "\n" 
                                + "Maximum Number Students Register: " + c.getMaxStudentsRegister() + "\n" 
                                + "Course Section: " + c.getCourseSection() + "\n" 
                                + "Location: "+ c.getCourseLocation());
                System.out.println("==========");
            }
        }
        System.out.println();
    }

    @Override
    public void withdrawFromCourse() {
         Scanner sc = new Scanner(System.in);
        System.out.println("Enter Course To Withdraw");
        System.out.println("=========================");
        System.out.print("Enter Course Name: ");
        String courseName = sc.nextLine();
        System.out.print("Enter Course Id: ");
        String courseId =  sc.nextLine();
        System.out.println("==========");
        
        if(CrsData.getCourseFaculty().stream().anyMatch(cf->courseName.equals(cf.getCourseName())&&courseId.equals(cf.getCourseId()))){
            CrsData.removeCourseFaculty(courseId);
            System.out.println("Successfull Withdraw From Course\n");
            return;
        }
        
        System.out.println("No Course Found\n");   
    }
    
}
