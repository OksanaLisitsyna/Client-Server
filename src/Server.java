import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 8080;
        try (ServerSocket serverSocket = new ServerSocket(port);
             Socket clientSocket = serverSocket.accept();
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            String dialogMark = "Server: "; //отметки для "диалога", для красоты
            String dialogMarkIncom = "Client: ";

            // когда появляется входящее соединение, печатаем "connection accepted"
            // и выводим на экран сообщение от клиента
            System.out.println("New connection accepted");
            System.out.println(dialogMarkIncom + in.readLine());
            // спрашиваем у клиента имя
            String questionAboutName = "Hello! Write your name";
            out.println(questionAboutName);
            System.out.println(dialogMark + questionAboutName);

            // считываем ответ клиента, его имя и спрашиваем у клиента возрастную категорию
            final String userName = in.readLine();
            System.out.println(dialogMarkIncom + userName);
            String questionAboutAge = "Are you child? (yes/no)";
            out.println(questionAboutAge);
            System.out.println(dialogMark + questionAboutAge);

            // читаем ответ клиента и отправляем соответствующее сообщение
            final String resp = in.readLine();
            System.out.println(dialogMarkIncom + resp);
            if (resp.equals("yes")) {
                String answerKid = String.format("Welcome to the kids area, %s! Let's play!", userName);
                out.println(answerKid);
                System.out.println(dialogMark + answerKid);
            } else if (resp.equals("no")) {
                String answerAdult = String.format("Welcome to the adult zone, %s!  Have a good rest, or a good working day!", userName);
                out.println(answerAdult);
                System.out.println(dialogMark + answerAdult);
            } else {
                String answerIncorrect = "Incorrect answer";
                out.println(answerIncorrect);
                System.out.println(dialogMark + answerIncorrect);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}