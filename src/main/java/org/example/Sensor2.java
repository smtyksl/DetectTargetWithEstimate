package org.example;

public class Sensor2 {
    public static void main(String[] args) {
        String groupID = "group1";
        String topicName = "topic1";
        String bootstrapServers = "localhost:9092";

        KafkaClient sensor2 = KafkaClientFactory.createClient(KafkaClientFactory.ClientType.SENSOR, groupID, topicName, bootstrapServers);
        new Thread(() -> sensor2.run()).start();


        sensor2.send("Sensör 1 verisi");
    }
}
