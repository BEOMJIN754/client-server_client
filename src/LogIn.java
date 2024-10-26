import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;

public class LogIn {
	private UserMain userMain;
	
	public void logInDialog(ServerIF server) throws IOException, NullDataException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("ID를 입력하세요.: ");
		String userId = reader.readLine().trim();
		System.out.println("PW를 입력하세요.: ");
		String userPw = reader.readLine().trim();
		try {
            String sessionId = server.authenticateUser(userId, userPw);
            if (sessionId != null) {
                System.out.println("로그인 성공.");
                this.userMain = new UserMain();
                userMain.run(server,sessionId);
            } else {
                System.out.println("로그인 실패. 잘못된 자격 증명입니다.");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
