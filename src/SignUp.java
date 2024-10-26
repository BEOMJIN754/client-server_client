import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SignUp {
	ServerIF server;

	public void signUp(ServerIF server) throws IOException, NullDataException {
		this.server = server;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.print("Please enter the ID you want to use (8 digits): ");
			String userId = reader.readLine().trim();
			if (!isValidId(userId)) { System.out.println("The ID must be 8 digits.");continue;}
			System.out.println("Please enter the password you want to use (6-20 characters): ");
			String userPw = reader.readLine().trim();
			if(!isValidPassword(userPw)) {System.out.println("The password must be 6-20 characters. (may include numbers)");continue;}
			System.out.println("Enter your first name (only Alphabet): ");
			String firstName = reader.readLine().trim();
			if (!isAlphabetic(firstName)) {
			    System.out.println("First name must contain letters only.");continue;}
			System.out.print("Enter your last name (only Alphabet): ");
			String lastName = reader.readLine().trim();
			if (!isAlphabetic(lastName)) {
			    System.out.println("Last name must contain letters only.");continue;}
			
			if(server.signUp(userId,userPw,firstName,lastName)) {System.out.println("You have successfully signed up.");break;}
			else System.out.println("Sign-up failed.");
		}
		
	}

	private boolean isValidId(String userId) {
		return userId.matches("^[0-9]{8}$");
	}

	private boolean isValidPassword(String userPw) {
		return userPw.matches("^[a-zA-Z0-9]{6,20}$");
	}
	public static boolean isAlphabetic(String input) {
	    return input.matches("[a-zA-Z]+"); // 알파벳만 포함하는 경우 true 반환
	}


}
