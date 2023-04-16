package org.example;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaTargetClient extends KafkaClient {
    private final KafkaProducer<String, String> producer;

    public KafkaTargetClient(String groupID, String topicName, String bootstrapServers) {
        super(groupID, topicName, bootstrapServers);

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<>(props);
    }

    @Override
    public void handleMessage(ConsumerRecord<String, String> record) {
        if (record.key().equals("SENSOR")) {
            System.out.println(record.value());
            send("Hedef Client tarafından işlendi: " + record.value());
        }
    }

    @Override
    public void send(String value) {
        producer.send(new ProducerRecord<>(topicName, "TARGET", value));
    }

    @Override
    public void close() {
        producer.close();
        consumer.close();
    }
}