package org.example;

public class Target{

    public static void main(String[] args) {
        String groupID = "group1";
        String topicName = "topic1";
        String bootstrapServers = "localhost:9092";
        KafkaClient target = KafkaClientFactory.createClient(KafkaClientFactory.ClientType.TARGET, groupID, topicName, bootstrapServers);
        new Thread(() -> target.run()).start();
    }
}