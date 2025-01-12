package kafka;

import java.util.Date;

public class Message {
    String data;
    Date timeStamp;

    public Message(String data) {
        this.data = data;
        this.timeStamp = new Date();
    }
}
