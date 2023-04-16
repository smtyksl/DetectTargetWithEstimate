package org.example;

public class Sensor1 {
    public static void main(String[] args) {
        String groupID = "group1";
        String topicName = "topic1";
        String bootstrapServers = "localhost:9092";

        KafkaClient sensor1 = KafkaClientFactory.createClient(KafkaClientFactory.ClientType.SENSOR, groupID, topicName, bootstrapServers);
        new Thread(() -> sensor1.run()).start();


        sensor1.send("Sensör 1 verisi");
    }
}
