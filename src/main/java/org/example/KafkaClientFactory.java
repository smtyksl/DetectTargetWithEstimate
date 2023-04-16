package org.example;

public class KafkaClientFactory {
    public enum ClientType {
        SENSOR,
        TARGET
    }

    public static KafkaClient createClient(ClientType type, String groupID, String topicName, String bootstrapServers) {
        KafkaClient client = null;
        switch (type) {
            case SENSOR:
                client = new KafkaSensorClient(groupID, topicName, bootstrapServers);
                break;
            case TARGET:
                client = new KafkaTargetClient(groupID, topicName, bootstrapServers);
                break;
        }
        return client;
    }
}