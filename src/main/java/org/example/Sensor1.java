package org.example;

import com.google.protobuf.InvalidProtocolBufferException;

public class Sensor1 {
    public static void main(String[] args) throws InterruptedException, InvalidProtocolBufferException {
        String groupID = "group1";
        String topicName = "topic1";
        String bootstrapServers = "localhost:9092";

        KafkaClient sensor1 = KafkaClientFactory.createClient(KafkaClientFactory.ClientType.SENSOR, groupID, topicName, bootstrapServers);
        new Thread(() -> sensor1.run()).start();

        var a = (KafkaSensorClient)sensor1;
        while(true) {
            a.PutPoint();
            //sensor1.send(a.getMessage());
            Thread.sleep(100);
          //  a.getMessage();

        }

    }
}
