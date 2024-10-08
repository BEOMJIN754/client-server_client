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
		while (exit) {
			try {
				server = menuTui();
				exit = userChoice(server, exit, reader);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private static Boolean userChoice(ServerIF server, Boolean exit, BufferedReader reader)
	        throws IOException, RemoteException {
	    String sChoice = reader.readLine().trim();
	    if (sChoice.equals("1")) {
	        ArrayList<Student> students = server.getAllStudentData();
	        System.out.println("Server' answer:");
	        System.out.println("sId        name    department  completedCoursesList ");
	        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
	        printInformationList(students); // 통합된 출력 함수 호출
	    } else if (sChoice.equals("2")) {
	        ArrayList<Course> courses = server.getAllCourseData();
	        System.out.println("Server' answer:");
	        System.out.println("cId        professor    cName         preRequisite ");
	        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
	        printInformationList(courses); // 통합된 출력 함수 호출
	    } else if (sChoice.equals("x")) {
	        System.out.println("Process Stopped.");
	        exit = false;
	    }
	    return exit;
	}


	private static ServerIF menuTui() throws NotBoundException, MalformedURLException, RemoteException {
		ServerIF server;
		server = (ServerIF) Naming.lookup("Server");
		System.out.println();
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡnewㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		System.out.println("ㅣ                                      ㅣ");
		System.out.println("ㅣ          1. List Students            ㅣ");
		System.out.println("ㅣ                                      ㅣ");
		System.out.println("ㅣ          2. List Courses             ㅣ");
		System.out.println("ㅣ                                      ㅣ");
		System.out.println("ㅣ          Press 'x' to exit           ㅣ");
		System.out.println("ㅣ                                      ㅣ");
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡchooseㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		return server;
	}
	
	public static void printInformationList(ArrayList<? extends Information> list) {
	    for (Information info : list) {
	        if (info instanceof Student) {
	            Student student = (Student) info;
	            // 한 줄에 여러 필드를 연결해서 출력
	            System.out.println(student.getDepartment() + " " 
	                               + student.getId() + " "
	                               + student.getName() + " "
	                               + student.getCompletedCourses().toString());
	            System.out.println(); // 한 학생 정보가 끝난 후 빈 줄로 구분
	        } else if (info instanceof Course) {
	            Course course = (Course) info;
	            // 한 줄에 여러 필드를 연결해서 출력
	            System.out.println(course.getId() + " " 
	                               + course.getcName() + " " 
	                               + course.getName() + " "
	                               + course.getPreRequisite());
	            System.out.println(); // 한 강좌 정보가 끝난 후 빈 줄로 구분
	        }
	    }
	}



}
