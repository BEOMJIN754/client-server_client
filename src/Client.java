import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;

public class Client {
    private SignUp signUp; 
    private LogIn logIn;     
    private ServerIF server;

    public Client() {
        try {
            server = (ServerIF) Naming.lookup("Server");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.signUp = new SignUp();
        this.logIn = new LogIn();
    }

    public static void main(String[] args) throws IOException, NullDataException {
        Client client = new Client();  // Client 인스턴스 생성
        client.mainTui();  // 인스턴스 메서드 호출
    }

    private void mainTui() throws IOException, NullDataException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean exit = true;

        while (exit) {
            System.out.println("****** Main Menu ******");
            System.out.println("1. Sign up");
            System.out.println("2. LogIn");
            System.out.println("Press 'x' to exit");
            System.out.println("**********************");

            String choice = reader.readLine().trim();

            switch (choice) {
                case "1":
                    signUp.signUp(server);
                    break;
                case "2":
                   logIn.logInDialog(server);
                case "x":
                    System.out.println("Program stopped.");
                    exit = false;
                    break;
                default:
                    System.out.println("Invalid input!");
            }
        }
    }
}
