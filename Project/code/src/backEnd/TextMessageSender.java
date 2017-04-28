package backEnd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by wtupc96 on 2017/4/16.
 */
public class TextMessageSender {
    private static Socket sendSocket;
    private static BufferedReader bufferedReader;

    private static PrintWriter write;

    public TextMessageSender(String host) throws IOException {
        int index = host.indexOf(':');
        sendSocket = new Socket(host.substring(0, index), Integer.valueOf(host.substring(index + 1)));
        bufferedReader = new BufferedReader(new InputStreamReader(sendSocket.getInputStream()));
        write = new PrintWriter(sendSocket.getOutputStream());
    }

    public void sendMessage(String message) {
        write.println(message);
        write.flush();
    }

    public void releaseResources() {
        try {
            bufferedReader.close();
            write.close();
            sendSocket.close();
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
}
