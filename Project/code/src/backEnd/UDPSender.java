package backEnd;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class UDPSender {

    private final int port;
    private final DatagramPacket dgp;
    //    private MulticastSocket s;
    private DatagramSocket s;
    private InetAddress group;

    UDPSender(String groupAddress, int port) {
        this.port = port;
        System.out.println(port);
        try {
//            s = new MulticastSocket();
            s = new DatagramSocket();
            group = InetAddress.getByName(groupAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dgp = new DatagramPacket(new byte[0], 0, group, port);
    }

    public void close() {
        if (s != null) {
            s.close();
        }
    }

    void send(byte[] buffer) {
        dgp.setData(buffer);
        dgp.setLength(buffer.length);
        try {
            s.send(dgp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
