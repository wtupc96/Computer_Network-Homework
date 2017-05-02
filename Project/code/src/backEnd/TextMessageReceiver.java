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
            serverSocket = new ServerSocket(9527);
            System.out.println("TCP 9527");
            getSocket = serverSocket.accept();
            bufferedReader = new BufferedReader(new InputStreamReader(getSocket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(getSocket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String receiveMessage() {
        try {
            return bufferedReader.readLine();
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

    public void sendMessage(String message) {
        try {
            bufferedWriter.write(message);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
