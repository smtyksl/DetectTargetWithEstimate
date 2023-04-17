package org.example;

public class CenterUnit {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        String groupID = "group1";
        String topicName = "semsor1topic";
        String bootstrapServers = "localhost:9092";

        KafkaClient sensor1 = KafkaClientFactory.createClient(KafkaClientFactory.ClientType.SENSOR, groupID, topicName, bootstrapServers);
        new Thread(() -> sensor1.run()).start();


    }
}