import com.hivemq.client.mqtt.mqtt3.Mqtt3AsyncClient;

import java.nio.charset.StandardCharsets;

public class MqttConnection {
    private Mqtt3AsyncClient client;

    public MqttConnection() {
        MqttConfig mqttConfig = new MqttConfig();
        mqttConfig.connect().thenAccept(client -> {
            this.client = client;
            System.out.println("Ready to send messages");
        });
    }

    public void sendMessage(String topic, String message) {
        if (client != null) {
            client.publishWith()
                    .topic(topic)
                    .payload(message.getBytes(StandardCharsets.UTF_8))
                    .send()
                    .whenComplete((pubAck, pubThrowable) -> {
                        if (pubThrowable != null) {
                            System.out.println("Error publishing: " + pubThrowable.getMessage());
                        } else {
                            System.out.println("Temperature published: " + message);
                        }
                    });
        }
    }
}
