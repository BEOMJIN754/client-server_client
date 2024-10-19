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
				userChoice(server,exit,reader);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullDataException e) {
				
			}
		}
	}
	private static void userChoice(ServerIF server, Boolean exit, BufferedReader reader)
	        throws IOException, RemoteException, NullDataException {
	    String sChoice = reader.readLine().trim();
	    switch(sChoice) {
	    case "1":
	        System.out.println("Server' answer:");
	        System.out.println("sId        name    department  completedCoursesList ");
	        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
	        showList(server.getAllStudentData()); // 통합된 출력 함수 호출
	    	break;
	    case "2":
	        System.out.println("Server' answer:");
	        System.out.println("cId        professor    cName         preRequisite ");
	        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
	        showList(server.getAllCourseData()); // 통합된 출력 함수 호출
	        break;
	    case "3":
	    	addStudent(server, reader);
	    	break;
	    case "4":
			deleteStudent(server, reader);
			break;
	    case "x":
	        System.out.println("Process Stopped.");
	        exit = false;
	    default:
	    	System.out.println("invalid choice!!!");
	    }
	}
	
	private static void addStudent(ServerIF server, BufferedReader reader) throws RemoteException, IOException{
		System.out.println("------Student Information------");
		System.out.println("Student ID: "); String studentId = reader.readLine().trim();
		System.out.println("Student Name: "); String studentName = reader.readLine().trim();
		System.out.println("Student Department: "); String studentDept = reader.readLine().trim();
		System.out.println("Student Completed Course List: "); String completedCourse = reader.readLine().trim();
		
		if(server.addStudent(studentId+" "+studentName+" "+studentDept+" "+completedCourse))System.out.println("SUCCESS");
		else System.out.println("FAIL");
		}
	
	private static void deleteStudent(ServerIF server, BufferedReader reader) throws RemoteException, IOException {
		System.out.print("Stident ID: ");
	    if(server.deleteStudent(reader.readLine().trim()))System.out.println("SUCCESS");
	    else System.out.println("FAIL");
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
		System.out.println("ㅣ          3. Add Student              ㅣ");
		System.out.println("ㅣ                                      ㅣ");
		System.out.println("ㅣ          4. Delete Student           ㅣ");
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
