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
		// 시스템 인풋 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 서버를 받아오는데 Naming에 서버 등록을 해줌. 걔를 lookup으로 찾아와야 함
		while (exit) {
			try {
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

				String sChoice = br.readLine().trim();
				if (sChoice.equals("1")) {
					ArrayList<Student> students = server.getAllStudentData();
					System.out.println("Server' answer:");
					System.out.println("sId        name    department  completedCoursesList ");
					System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
					for (Student student : students) {
						System.out.println(student); // Student 객체의 toString() 메서드를 호출해 한 줄씩 출력
					}
				} else if (sChoice.equals("2")) {
					ArrayList<Course> courses = server.getAllCourseData();
					System.out.println("Server' answer:");
					System.out.println("cId  professor       cName           preRequisite ");
					System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
					for (Course course : courses) {
						System.out.println(course); // Course 객체의 toString() 메서드를 호출해 한 줄씩 출력
					}
				} else if (sChoice.equals("x")) {
					System.out.println("Process Stopped.");

					exit = false;
				}
			}

			catch (MalformedURLException e) {
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

}
