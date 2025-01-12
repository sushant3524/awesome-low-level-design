package kafka;

import java.util.concurrent.atomic.AtomicLong;

public class Subscriber {
    String id;
    AtomicLong offsetConsumed;

    public void consume(Message message) {
// logic
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AtomicLong getOffsetConsumed() {
        return offsetConsumed;
    }

    public void setOffsetConsumed(AtomicLong offsetConsumed) {
        this.offsetConsumed = offsetConsumed;
    }
}
