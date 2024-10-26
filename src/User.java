import java.io.Serializable;
import java.util.StringTokenizer;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	protected String userId;
	protected String userPw;
	protected String firstName;
	protected String lastName;
	
	public User(String inputString) {
		StringTokenizer stringTokenizer = new StringTokenizer(inputString);
		this.userId = stringTokenizer.nextToken();
		this.userPw = stringTokenizer.nextToken();
		this.firstName = stringTokenizer.nextToken();
		this.lastName = stringTokenizer.nextToken();
        }
	public String getUserId() {
		return userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public boolean match(String userId) {
		return this.userId.equals(userId);
	}
}
