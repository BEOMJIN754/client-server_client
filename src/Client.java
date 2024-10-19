import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Client {

	public static void main(String[] args) throws IOException {
		ServerIF server;
		Boolean exit = true;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			while (exit) {
				server = menuTui();
				exit = userChoice(server,reader);
			}
		} catch (NotBoundException | NullDataException | RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static boolean userChoice(ServerIF server, BufferedReader reader)
			throws IOException, RemoteException, NullDataException {
		String sChoice = reader.readLine().trim();
		switch (sChoice) {
		case "1":
			getAllStudentsTui(server);
			break;
		case "2":
			getAllcourseTui(server);
			break;
		case "3":
			addStudent(server, reader);
			break;
		case "4":
			deleteStudent(server, reader);
			break;
		case "5":
			addCourse(server, reader);
			break;
		case "6":
			deleteCourse(server, reader);
			break;
		case "7":
			deleteStudent(server, reader);
			break;
		case "x":
			System.out.println("Process Stopped.");
			return false;
		default:
			System.out.println("invalid choice!!!");
		}return true;
	}

	

	private static void deleteCourse(ServerIF server, BufferedReader reader) throws RemoteException, IOException {
		System.out.print("Course ID: ");
		if (server.deleteCourse(reader.readLine().trim()))
			System.out.println("SUCCESS");
		else
			System.out.println("FAIL");
	}

	private static void addCourse(ServerIF server, BufferedReader reader) throws RemoteException, IOException {
		System.out.println("------Course Information------");
		System.out.print("Course ID: ");
		String courseId = reader.readLine().trim();
		System.out.print("Professor : ");
		String professor = reader.readLine().trim();
		System.out.print("Course Name: ");
		String courseName = reader.readLine().trim();
		System.out.print("Course preRequisite: ");
		String pre = reader.readLine().trim();

		if (server.addCourse(courseId + " " + professor + " " + courseName + " " + pre))
			System.out.println("SUCCESS");
		else
			System.out.println("FAIL");
	}

	private static void getAllStudentsTui(ServerIF server) throws RemoteException, NullDataException {
		System.out.println("Server's answer.");
		System.out.println("sId        name    department  completedCoursesList ");
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		showList(server.getAllStudentData()); // 통합된 출력 함수 호출
	}

	private static void getAllcourseTui(ServerIF server) throws RemoteException, NullDataException {
		System.out.println("Server's answer.");
		System.out.println("cId        professor    cName         preRequisite ");
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		showList(server.getAllCourseData()); // 통합된 출력 함수 호출
	}

	private static void addStudent(ServerIF server, BufferedReader reader) throws RemoteException, IOException {
		System.out.println("------Student Information------");
		System.out.print("Student ID: ");
		String studentId = reader.readLine().trim();
		System.out.print("Student Name: ");
		String studentName = reader.readLine().trim();
		System.out.print("Student Department: ");
		String studentDept = reader.readLine().trim();
		System.out.print("Student Completed Course List: ");
		String completedCourse = reader.readLine().trim();

		if (server.addStudent(studentId + " " + studentName + " " + studentDept + " " + completedCourse))
			System.out.println("SUCCESS");
		else
			System.out.println("FAIL");
	}

	private static void deleteStudent(ServerIF server, BufferedReader reader) throws RemoteException, IOException {
		System.out.print("Stident ID: ");
		if (server.deleteStudent(reader.readLine().trim()))
			System.out.println("SUCCESS");
		else
			System.out.println("FAIL");
	}

	private static ServerIF menuTui() throws NotBoundException, MalformedURLException, RemoteException {
		ServerIF server;
		server = (ServerIF) Naming.lookup("Server");
		System.out.println("**********new**********");
		System.out.println("1. List Students");
		System.out.println("2. List Courses");
		System.out.println("3. Add Student");
		System.out.println("4. Delete Student");
		System.out.println("5. Add Course");
		System.out.println("6. Delete Course");
		System.out.println("");
		System.out.println("Press 'x' to exit");
		System.out.println("***********************");
		return server;
	}

	public static void showList(ArrayList<?> dataList) {
		String list = "";
		for (int i = 0; i < dataList.size(); i++) {
			list += dataList.get(i) + "\n";
		}
		System.out.println(list);
	}
}
