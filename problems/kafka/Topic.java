package kafka;

import java.util.*;

public class Topic {
    String id;
    String topicName;
    Map<String, Subscriber> subscribers;
    List<Message> messages;

    public Topic(String topicName) {
        this.id = UUID.randomUUID().toString();
        this.topicName = topicName;
        this.subscribers = new HashMap<>();
        this.messages = new ArrayList<>();
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public void addSubscriber(Subscriber subscriber) {
        subscribers.put(subscriber.getId(), subscriber);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Map<String, Subscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Map<String, Subscriber> subscribers) {
        this.subscribers = subscribers;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
