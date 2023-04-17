package org.example;

import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.Kafka.*;
import com.google.protobuf.util.JsonFormat;

import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class KafkaSensorClient extends KafkaClient {
    private final KafkaProducer<String, String> producer;

    public KafkaSensorClient(String groupID, String topicName, String bootstrapServers) {
        super(groupID, topicName, bootstrapServers);
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(props);
    }

    @Override
    public void handleMessage(ConsumerRecord<String, String> record) {
        System.out.printf("Received message key=%s, value=%s, partition=%d, offset=%d\n",
                record.key(), record.value(), record.partition(), record.offset());
        // Do something with the message here, for example write it to a file or store it in a database
    }

    @Override
    public void send(String value) {
        producer.send(new ProducerRecord<>(topicName, "SENSOR", value));
    }

    @Override
    public void close() {
        producer.close();
        consumer.close();
    }

    public String getMessage() throws InvalidProtocolBufferException {
        // Create a new Coordinate object
        Coordinate coordinate = Coordinate.newBuilder()
                .setX(42)
                .setY(123)
                .build();

        // Create a new KafkaMessage object
        KafkaMessage message = KafkaMessage.newBuilder()
                .setClientType(ClientType.SENSOR1)
                .setCoordinate(coordinate)
                .build();

        // Serialize the KafkaMessage to a string
        String kafkaMessageString = JsonFormat.printer().print(message);

        return kafkaMessageString;
    }

}