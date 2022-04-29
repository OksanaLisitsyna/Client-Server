import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String host = "netology.homework";
        int port = 8080;

        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String dialogMark = "Client: "; //отметки для "диалога", для красоты
            String dialogMarkIncom = "Server: ";

            //client инициирует общение
            String welcomeMessage = "Hello";
            out.println(welcomeMessage);
            System.out.println(dialogMark + welcomeMessage);

            //читаем ответ от сервера, выводим это сообщение на экран
            // отвечаем серверу, отправляем свое имя
            System.out.println(dialogMarkIncom + in.readLine());
            out.println(host);
            System.out.println(dialogMark + host);

            // читаем сообщение от сервера и отвечаем ему yes
            System.out.println(dialogMarkIncom + in.readLine());
            String answerAboutAge = "yes";
            out.println(answerAboutAge);
            System.out.println(dialogMark + answerAboutAge);

            //выводим на экран ответ сервера
            System.out.println(dialogMarkIncom + in.readLine());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}