package backEnd;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by wtupc96 on 2017/4/16.
 */
public class TextMessageReceiver {
    private static ServerSocket serverSocket;
    private static Socket getSocket;
    private static BufferedReader bufferedReader;
    private static BufferedWriter bufferedWriter;

    public TextMessageReceiver() {
        try {
            serverSocket = new ServerSocket(10005);
            getSocket = serverSocket.accept();
            bufferedReader = new BufferedReader(new InputStreamReader(getSocket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(getSocket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String receiveMessage() {
        try {
            String message = bufferedReader.readLine();
            return message;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void releaseResources() {
        try {
            bufferedReader.close();
            bufferedWriter.close();
            serverSocket.close();
            getSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
