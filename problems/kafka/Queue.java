package kafka;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class Queue {
    Map<String, TopicHandler> topicHandlers;

    public Queue() {
        this.topicHandlers = new HashMap<>();
    }

    public Topic createTopic(String topicName) {
        Topic topic = new Topic(topicName);
        TopicHandler topicHandler = new TopicHandler(topic);
        topicHandlers.put(topic.getId(), topicHandler);
        System.out.println("Created topic with name " + topicName);
        return topic;
    }

    public void subscribe(Subscriber subscriber, Topic topic) {
        topic.addSubscriber(subscriber);
    }

    public void publish(Topic topic, Message message) {
        topicHandlers.get(topic.getId()).publish(message);
    }
    public void resetOffset(Topic topic, Subscriber subscriber, long newOffset) {
        Subscriber subscriber1 = topic.getSubscribers().get(subscriber.getId());
        subscriber1.setOffsetConsumed(new AtomicLong(newOffset));
        topicHandlers.get(topic.getId()).startSubscriberWorker(subscriber1);
    }
}
