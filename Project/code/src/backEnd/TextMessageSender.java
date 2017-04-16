package backEnd;

import java.io.*;
import java.net.Socket;

/**
 * Created by wtupc96 on 2017/4/16.
 */
public class TextMessageSender {
    private static Socket sendSocket;
    private static BufferedReader bufferedReader;
    private static BufferedWriter bufferedWriter;

    public TextMessageSender(String host) {
        try {
            int index = host.indexOf(':');
            sendSocket = new Socket(host.substring(0, index), Integer.valueOf(host.substring(index + 1)));
            bufferedReader = new BufferedReader(new InputStreamReader(sendSocket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(sendSocket.getOutputStream()));
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

    public void releaseResources() {
        try {
            bufferedReader.close();
            bufferedWriter.close();
            sendSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
