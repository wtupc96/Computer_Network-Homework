package backEnd;

public class Chat {
    private final SoundReceiver soundReceiver;
    private final SoundSender soundSender;

    public Chat(String targetPeer) {
        int indexOfPort = targetPeer.indexOf(':');

        // SoundSender第一个参数是目的地的IP，第二个参数是目的地端口，第三个参数是缓存大小
        soundSender = new SoundSender(targetPeer.substring(0, indexOfPort), 9999, 1024);

        // SoundReceiver第一个参数是本机接收IP，第二个参数是本机接收端口，第三个参数是缓存大小
        soundReceiver = new SoundReceiver("localhost", 10000, 1024);
    }

    public void soundStart() {
        soundReceiver.start();
        soundSender.start();
    }

    public void soundStop() {
        soundReceiver.stop();
        soundSender.stop();
    }
}
