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
	        showList(students); // 통합된 출력 함수 호출
	    } else if (sChoice.equals("2")) {
	        ArrayList<Course> courses = server.getAllCourseData();
	        System.out.println("Server' answer:");
	        System.out.println("cId        professor    cName         preRequisite ");
	        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
	        showList(courses); // 통합된 출력 함수 호출
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
	
	public static void showList(ArrayList<?> dataList) {
		String list = "";
		for(int i=0;i<dataList.size();i++) {
			list += dataList.get(i)+"\n";
		}
		System.out.println(list);
	}
}
