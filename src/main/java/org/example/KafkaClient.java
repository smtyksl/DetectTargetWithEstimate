package org.example;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import java.util.Random;


public abstract class KafkaClient {
    protected final String topicName;
    protected final KafkaConsumer<String, String> consumer;

    protected Point coordinate;

    Random rand;

    public KafkaClient(String groupID, String topicName, String bootstrapServers) {
        this.topicName = topicName;
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupID);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(topicName));
        rand = new Random();
    }

    public void run() {
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                handleMessage(record);
            }
        }
    }

    public abstract void handleMessage(ConsumerRecord<String, String> record);
    public abstract void send(String value);
    public abstract void close();

    public void PutPoint (){
        coordinate = new Point();
        int x = rand.nextInt(2001) - 1000;
        int y = rand.nextInt(2001) - 1000;
        coordinate.setX(x);
        coordinate.setY(y);
    }

    public double distance(Point other) {

        int dx = coordinate.getX() - other.getX();
        int dy = coordinate.getY() - other.getY();
        return Math.sqrt(dx*dx + dy*dy);
    }
}