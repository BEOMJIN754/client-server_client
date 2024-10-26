import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class UserMain {

	public void run(ServerIF server, String sessionId) throws IOException {
		boolean exit = true;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			while (exit) {
				this.showMenuTui();
				exit = userChoice(server, reader, sessionId);
			}
		} catch (NotBoundException | NullDataException | RemoteException | WrongInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showMenuTui() {
		System.out.println("**********new**********");
		System.out.println("1. List Students");
		System.out.println("2. Add Student");
		System.out.println("3. Delete Student");
		System.out.println("4. List Courses");
		System.out.println("5. Add Course");
		System.out.println("6. Delete Course");
		System.out.println("7. Course Registration");
		System.out.println("8. List Registration");
		System.out.println("9. Delete Registration");
		System.out.println("");
		System.out.println("Press 'x' to exit");
		System.out.println("***********************");
	}

	private static boolean userChoice(ServerIF server, BufferedReader reader, String sessionId)
			throws IOException, RemoteException, NullDataException, WrongInputException, NotBoundException {
		String sChoice = reader.readLine().trim();
		switch (sChoice) {
		case "1":
			getAllStudentsTui(server,sessionId);
			break;
		case "2":
			addStudent(server, reader,sessionId);
			break;
		case "3":
			deleteStudent(server, reader,sessionId);
			break;
		case "4":
			getAllcourseTui(server,sessionId);
			break;
		case "5":
			addCourse(server, reader,sessionId);
			break;
		case "6":
			deleteCourse(server, reader,sessionId);
			break;
		case "7":
			registerCourse(server, reader,sessionId);
			break;
		case "8":
			getAllRegistrationTui(server, reader,sessionId);
			break;
		case "9":
			deleteRegistration(server,reader,sessionId);
			break;
		case "10":
		    findStudentById(server, reader, sessionId);
		    break;
		case "11":
		    findCourseById(server, reader, sessionId);
		    break;

		case "x":
			if(server.logoutUser(sessionId)) {
				System.out.println("정상적으로 로그아웃 되었습니다.");
			return false;
			}
			else {System.out.println("로그아웃에 실패하였습니다.");return true;}
		default:
			System.out.println("invalid choice!!!");
		}
		return true;
	}
	
	private static void getAllStudentsTui(ServerIF server, String sessionId) throws RemoteException, NullDataException {
		System.out.println("Server's answer.");
		System.out.println("sId        name    department  completedCoursesList ");
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		showList(server.getAllStudentData(sessionId));}
	
	private static void addStudent(ServerIF server, BufferedReader reader, String sessionId) throws RemoteException, IOException {
		System.out.println("------Student Information------");
		System.out.print("Student ID: ");String studentId = reader.readLine().trim();
		System.out.print("Student Name: ");String studentName = reader.readLine().trim();
		System.out.print("Student Department: ");String studentDept = reader.readLine().trim();
		System.out.print("Student Completed Course List: ");String completedCourse = reader.readLine().trim();

		if (server.addStudent(studentId + " " + studentName + " " + studentDept + " " + completedCourse,sessionId))System.out.println("SUCCESS");
		else System.out.println("FAIL");}
	
	private static void deleteStudent(ServerIF server, BufferedReader reader, String sessionId) throws RemoteException, IOException {
		System.out.print("Student ID: ");
		if (server.deleteStudent(reader.readLine().trim(),sessionId))System.out.println("SUCCESS");
		else System.out.println("FAIL");}
	
	private static void findStudentById(ServerIF server, BufferedReader reader, String sessionId) throws IOException, RemoteException, NullDataException {
	    System.out.print("Enter the Student ID: ");
	    String studentId = reader.readLine().trim();
	    
	    Student student = server.findStudentById(studentId, sessionId);
	    if (student != null) {
	    	System.out.println("Student Information: ");
	    	System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
	        System.out.println(student);
	    } else {
	        System.out.println("No student found with the provided ID.");
	    }
	}

	
	private static void getAllcourseTui(ServerIF server, String sessionId) throws RemoteException, NullDataException {
		System.out.println("Server's answer.");
		System.out.println("cId        professor    cName         preRequisite ");
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		showList(server.getAllCourseData(sessionId));}
	
	private static void addCourse(ServerIF server, BufferedReader reader, String sessionId) throws RemoteException, IOException {
		System.out.println("------Course Information------");
		System.out.print("Course ID: ");String courseId = reader.readLine().trim();
		System.out.print("Professor : ");String professor = reader.readLine().trim();
		System.out.print("Course Name: ");String courseName = reader.readLine().trim();
		System.out.print("Course preRequisite: ");String pre = reader.readLine().trim();

		if (server.addCourse(courseId + " " + professor + " " + courseName + " " + pre,sessionId))System.out.println("SUCCESS");
		else System.out.println("FAIL");}
	
	private static void deleteCourse(ServerIF server, BufferedReader reader, String sessionId) throws RemoteException, IOException {
		System.out.print("Course ID: ");
		if (server.deleteCourse(reader.readLine().trim(),sessionId))System.out.println("SUCCESS");
		else System.out.println("FAIL");
	}
	
	private static void findCourseById(ServerIF server, BufferedReader reader, String sessionId) throws IOException, RemoteException, NullDataException {
	    System.out.print("Enter the Course ID: ");
	    String courseId = reader.readLine().trim();
	    
	    Course course = server.findCourseById(courseId, sessionId);
	    if (course != null) {
	    	System.out.println("Course Information: ");
	    	System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
	        System.out.println(course);
	    } else {
	        System.out.println("No course found with the provided ID.");
	    }
	}

	
	private static void getAllRegistrationTui(ServerIF server, BufferedReader reader, String sessionId) throws RemoteException, NullDataException {
		System.out.println("Server's answer.");
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		showList(server.getAllRegistrationData(sessionId));
	}

	private static void registerCourse(ServerIF server, BufferedReader reader, String sessionId) throws IOException, WrongInputException {
		System.out.println("ㅡㅡㅡㅡㅡㅡRegistration Informationㅡㅡㅡㅡㅡㅡ");
		System.out.print("Student ID: ");String studentId = reader.readLine().trim();
		System.out.print("Course ID: ");String courseId = reader.readLine().trim();
		
		if(server.registerCourse(studentId,courseId,sessionId))System.out.println("SUCCESS");
		else System.out.println("FAIL");
	}
	
	private static void deleteRegistration(ServerIF server, BufferedReader reader, String sessionId) throws IOException {
	    System.out.print("Enter the Student ID: ");
	    String studentId = reader.readLine().trim();
	    
	    System.out.print("Enter the Course ID to delete: ");
	    String courseId = reader.readLine().trim();
	    
	    if (server.deleteRegistration(studentId, courseId, sessionId)) {
	        System.out.println("Registration deleted successfully.");
	    } else {
	        System.out.println("Failed to delete registration. Check if the Student ID and Course ID are correct.");
	    }
	}

	public static void showList(ArrayList<?> dataList) {
		String list = "";
		for (int i = 0; i < dataList.size(); i++) {
			list += dataList.get(i) + "\n";
		}
		System.out.println(list);
	}
}
