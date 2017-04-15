package backEnd;

import javax.sound.sampled.*;

class SoundSender extends UDPSender implements Runnable {

    private final int bufferLength;
    private TargetDataLine line;
    private Thread thread;
    private boolean isStart;

    public SoundSender(String groupAddress, int port, int bufferLength) {
        super(groupAddress, port);
        AudioFormat format = new AudioFormat(8000, 16, 2, true, true);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        try {
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format, line.getBufferSize());
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        this.bufferLength = bufferLength;
        isStart = false;
    }


    public void run() {
        byte[] buffer = new byte[bufferLength];
        while (isStart && !thread.isInterrupted()) {
            line.read(buffer, 0, buffer.length);
            send(buffer);
        }
    }

    public void start() {
        if (thread == null || !thread.isAlive()) {
            thread = new Thread(this);
            line.start();
            thread.start();
            isStart = true;
        }
    }

    public void stop() {
        thread.interrupt();
        line.stop();
        isStart = false;
    }

}
