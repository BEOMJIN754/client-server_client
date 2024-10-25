import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LogIn {
	private UserMain userMain;
	
	public void logInDialog(ServerIF server) throws IOException, NullDataException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("ID를 입력하세요.: ");
		String userId = reader.readLine().trim();
		System.out.println("PW를 입력하세요.: ");
		String userPw = reader.readLine().trim();
		
		if(server.authenticateUser(userId,userPw)) {
			System.out.println("로그인 되었습니다.");
			this.userMain = new UserMain();
			userMain.run(server);}
		else System.out.println("로그인되지 않았습니다.");
	}
}
