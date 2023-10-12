import java.util.Scanner;

public class Student extends User implements StudentInterface{


    public Student() {
    }
    
    
    public Student(String firstName, String midName, String lastName, String username, String password) {
        super(username, password, firstName, midName, lastName);
    }

    
    @Override
    public void studentViewAllCourse() {
        System.out.println("All Courses Of University");
        System.out.println("=========================");
        for(Course course : CrsData.getCourseFaculty()){
            System.out.println("Course ID: " + course.getCourseId()+ "\n" 
                                + "Course Name: " + course.getCourseName()+ "\n" 
                                + "Maximum Number Students Register: " + course.getMaxStudentsRegister() + "\n" 
                                + "Course Instructor: " + course.getInstructor() + "\n" 
                                + "Course Section: " + course.getCourseSection() + "\n" 
                                + "Location: "+ course.getCourseLocation());
            System.out.println("==========");
        }
    }

    @Override
    public void viewAvailableCourse() {
        System.out.println("Available Courses Of University");
        System.out.println("=========================");
        for(Course c : CrsData.getCourseFaculty()){
            Course course = CrsData.getCourseStudent().stream().filter(cs->c.getCourseId().equals(cs.getCourseId())).findFirst().orElse(c);
            if(course.getCurrentStudentsRegister()<course.getMaxStudentsRegister()){
                    System.out.println("Course ID: " + course.getCourseId()+ "\n" 
                                + "Course Name " + course.getCourseName()+ "\n" 
                                + "Current Number Students Register: " + course.getCurrentStudentsRegister()+ "\n" 
                                + "Maximum Number Students Register: " + course.getMaxStudentsRegister() + "\n" 
                                + "Course Instructor: " + course.getInstructor() + "\n" 
                                + "Course Section: " + course.getCourseSection() + "\n" 
                                + "Location: "+ course.getCourseLocation());
                    System.out.println("==========");
            }
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
        System.out.print("Enter Course ID: ");
        String courseId = sc.nextLine();
        System.out.println("==========");

        
        for(Course c : CrsData.getCourseFaculty()){
            if(courseId.equals(c.getCourseId())&&courseName.equals(c.getCourseName())){
                boolean check = false;
                if(CrsData.getCourseStudent().stream().noneMatch(course->c.getCourseId().equals(course.getCourseId()))){
                    Course newCourseStudent = new Course(c, c.getInstructor());
                    newCourseStudent.addStudentRegisterList(this.getName());
                    CrsData.addCourseStudent(newCourseStudent);
                    check = true;
                }else{
                    for(Course course : CrsData.getCourseStudent()){
                        if(course.getCourseId().equals(c.getCourseId())&&course.getCurrentStudentsRegister() < course.getMaxStudentsRegister()){
                            if(course.getStudentRegisterList().contains(this.getName())){
                                System.out.println("Student Has Already Registered This Course\n");
                                return;
                            }
                            course.addStudentRegisterList(this.getName());
                            check = true;
                            break;
                        }
                    }
                }
                if(!check)System.out.println("Number Of Student Register Are Full\n");
                else System.out.println("Sucessfully register student to course\n");
                return;
            }
        }
        System.out.println("No Course Found\n");   
    }

    @Override
    public void viewAllRegisteredCourses() {
        System.out.println("All Registered Courses");
        System.out.println("=========================");

        if(CrsData.getCourseStudent().stream().noneMatch(c->c.getStudentRegisterList().contains(this.getName()))){
            System.out.println("Student Have No Register Courses\n");
            return;
        }
        
        for(Course c : CrsData.getCourseStudent()){
            if(!c.getStudentRegisterList().contains(this.getName()))continue;
            System.out.println("Course ID: " + c.getCourseId()+ "\n" 
                                + "Course Name: " + c.getCourseName()+ "\n" 
                                + "Current Number Students Register: " + c.getCurrentStudentsRegister() + "\n" 
                                + "Maximum Number Students Register: " + c.getMaxStudentsRegister() + "\n" 
                                + "Course Instructor: " + c.getInstructor() + "\n" 
                                + "Course Section: " + c.getCourseSection() + "\n" 
                                + "Location: "+ c.getCourseLocation());
            System.out.println("==========");
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
        System.out.print("Enter Course ID: ");
        String courseId = sc.nextLine();
        System.out.println("==========");
        
        for(Course course : CrsData.getCourseStudent()){
            if(courseId.equals(course.getCourseId())&&courseName.equals(course.getCourseName())){
                course.removeStudentRegisterList(this.getName());
                if(course.getCurrentStudentsRegister()==0)CrsData.removeCourseStudent(courseId);
                System.out.println("Sucessfully withdraw student from course\n");
                return;
            }
        }
        
        System.out.println("No Course Found\n");   
    }    
    
}
