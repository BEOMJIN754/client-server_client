import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SignUp {
	ServerIF server;

	public void signUp(ServerIF server) throws IOException, NullDataException {
		this.server = server;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.print("사용하고자 하는 ID를 입력하세요. (8자리숫자): ");
			String userId = reader.readLine().trim();
			if (!isValidId(userId)) { System.out.println("ID는 8자리 숫자여야 합니다.");continue;}
			System.out.println("사용하고자 하는 비밀번호를 입력하세요. (6~20자 사이의 문자열): ");
			String userPw = reader.readLine().trim();
			if(!isValidPassword(userPw)) {System.out.println("비밀번호는 6~20자의 문자열이어야 합니다. (숫자 포함 가능)");continue;}
			
			if(server.signUp(userId,userPw)) {System.out.println("회원가입에 성공하셨습니다.");break;}
			else System.out.println("회원가입에 실패하셨습니다.");
		}
		
	}

	private boolean isValidId(String userId) {
		return userId.matches("^[0-9]{8}$");
	}

	private boolean isValidPassword(String userPw) {
		return userPw.matches("^[a-zA-Z0-9]{6,20}$");
	}

}
