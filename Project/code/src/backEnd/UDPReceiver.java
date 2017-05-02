package backEnd;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;


class UDPReceiver {

    private final int port;
    private final int bufferSize;
    //    private MulticastSocket s;
    private DatagramSocket s;
    private InetAddress group;
    private byte[] buffer;
    private DatagramPacket dgp;
    private boolean isClose;

    UDPReceiver(String groupAddress, int port, int bufferSize) {
        try {
            s = new MulticastSocket(port);
            group = InetAddress.getByName(groupAddress);
//            s.joinGroup(group);
            //s.setLoopbackMode(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer = new byte[bufferSize];
        dgp = new DatagramPacket(buffer, bufferSize);
        isClose = false;
        this.port = port;
        this.bufferSize = bufferSize;
    }

    void close() {
        if (s != null && group != null) {
//            try {
//                s.leaveGroup(group);
            s.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            isClose = true;
        }
    }

    void contact() {
        if (isClose) {
            try {
                s = new MulticastSocket(port);
//                s.joinGroup(group);
            } catch (IOException e) {
                e.printStackTrace();
            }
            buffer = new byte[bufferSize];
            dgp = new DatagramPacket(buffer, bufferSize);
            isClose = false;
        }
    }

    byte[] receive() {
        try {
            s.receive(dgp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer;
    }

    protected int getDgpLength() {
        return dgp.getLength();
    }

    protected String getIP() {
        return dgp.getAddress().toString();
    }

}
