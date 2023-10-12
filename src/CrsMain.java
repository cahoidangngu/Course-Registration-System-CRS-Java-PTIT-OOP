import java.io.IOException;
import java.util.Scanner;


public class CrsMain {
    
    
    public static void main(String[] args) {
        try {
            CrsData.desequelizeUser();
            CrsData.desequelizeCourse();
            CrsData.desequelizeCourseFaculty();
            CrsData.desequelizeCourseStudent();
            loginMain();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public static void loginMain() throws IOException{
                System.out.println("Choose User Below:");
                System.out.println("=========================");
		System.out.print("""
                                 1. Admin
                                 2. Faculty
                                 3. Student
                                 4. Exit
                                 ======================
                                 """);
		
		Scanner input = new Scanner(System.in);
		int choice = Integer.parseInt(input.nextLine());
		System.out.println("=========================");
                System.out.println("Login System");
		//Check if username/password are correct
		if (choice == 1) {
			System.out.println("Enter Admin Username: ");
			String username = input.nextLine();
			
			System.out.println("Enter Admin Password: ");
			String password = input.nextLine();
			
			for(Admin admin : CrsData.getAdmins()){
                            if(username.equals(admin.getUsername())&&password.equals(admin.getPassword())){
                                adminSelection(admin);
                                break;
                            }
                        }
			
		}
		
		if (choice == 2) {
			
				System.out.println("Enter Faculty Username: ");
				String username = input.nextLine();

				System.out.println("Enter Faculty Password: ");
				String password = input.nextLine();

				for(Faculty f : CrsData.getFacultys()){
                                    if(username.equals( f.getUsername())&&password.equals(f.getPassword())){
                                        facultySelection(f);
                                        break;
                                    }
                                }
				
//			}
					
		}
		if (choice == 3) {
                               
				System.out.println("Enter Student Username: ");
				String username = input.nextLine();

				System.out.println("Enter Student Password: ");
				String password = input.nextLine();

				for(Student s : CrsData.getStudents()){
                                    if(username.equals( s.getUsername())&&password.equals(s.getPassword())){
                                        studentSelection(s);
                                        break;
                                    }
                                }
					
		}
		if (choice == 4) {
                    CrsData.sequelizeUser();
                    CrsData.sequelizeCourse();
                    CrsData.sequelizeCourseFaculty();
                    CrsData.sequelizeCourseStudent();
                    System.exit(0);
		}
                System.out.println("Wrong Username or Password :((. Please Login Again!\n");
                loginMain();
	}
	
	
	public static void adminSelection(Admin admin) throws IOException {
		System.out.print("""
                                 COURSE MANAGEMENT:
                                 ======================
                                 1. Create a new course
                                 2. Delete a course
                                 3. Edit a course
                                 4. Display course info
                                 5. Register a student
                                 6. Register a faculty
                                 ======================
                   
                                 """);
		
		System.out.print("""
                                 REPORTS:
                                 ======================
                                 7. View all courses
                                 8. View all full courses
                                 9. Add full courses to file
                                 10. View students in a course
                                 11. View courses for a student
                                 12. Sort courses
                                 13. Exit
                                 ======================
                                 """);
		//Ask user to make selection
		Scanner input = new Scanner(System.in);
		System.out.println("Enter selection: ");
		int systemEntry = input.nextInt();
		
		//Call specified method from Admin class based on selection
		
			if (systemEntry == 1) admin.creatCourse();
			if (systemEntry == 2) admin.deleteCourse();
			if (systemEntry == 3) admin.editCourse();
			if (systemEntry == 4) admin.displayCourseById();
			if (systemEntry == 5) admin.registerStudent();	
			if (systemEntry == 6) admin.registerFaculty();
			if (systemEntry == 7) admin.adminViewAllCourses();
			if (systemEntry == 8) admin.viewFullCourses();
			if (systemEntry == 9) admin.writeToFileFullCourse();
			if (systemEntry == 10) admin.viewRegisteredStudents();
			if (systemEntry == 11) admin.viewAllStudentCourses();
			if (systemEntry == 12) admin.sortCourses();
			if (systemEntry == 13) {
				loginMain(); 
			}
		adminSelection(admin);
	}
	
	public static void studentSelection(Student student) throws IOException {
		System.out.print("""
                                 COURSE MANAGEMENT:
                                 ======================
                                 1. View all courses
                                 2. View all available courses
                                 3. Register for a course
                                 4. Withdraw from a course
                                 5. Your courses
                                 6. Exit
                                 ======================
                                 """);
		//Ask user to make selection
		Scanner input = new Scanner(System.in);
		System.out.println("Enter selection: ");
		int systemEntry = input.nextInt();
		
		//Call specified method from Student class based on selection
		if (systemEntry == 1) student.studentViewAllCourse();
		if (systemEntry == 2) student.viewAvailableCourse();
		if (systemEntry == 3) student.registerToCourse();
		if (systemEntry == 4) student.withdrawFromCourse();
		if (systemEntry == 5) student.viewAllRegisteredCourses();
		if (systemEntry == 6) {
			loginMain();
		}
		studentSelection(student);
	}
        
	public static void facultySelection(Faculty faculty) throws IOException {
		System.out.print("""
                                COURSE MANAGEMENT:
                                ======================
                                 1. View all courses
                                 2. Register for a course
                                 3. Withdraw from a course
                                 4. Teaching courses
                                 5. Exit
                                ======================
                                 """);
		//Ask user to make selection
		Scanner input = new Scanner(System.in);
		System.out.println("Enter selection: ");
		int systemEntry = input.nextInt();
		
		//Call specified method from Student class based on selection
		if (systemEntry == 1) faculty.falcultyViewAllCourses();
		if (systemEntry == 2) faculty.registerToCourse();
		if (systemEntry == 3) faculty.withdrawFromCourse();
		if (systemEntry == 4) faculty.viewAllRegisteredCourses();
		if (systemEntry == 5) {
			loginMain();
		}
		facultySelection(faculty);
	}
    
}
