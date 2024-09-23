import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.mqtt3.Mqtt3AsyncClient;

import java.util.concurrent.CompletableFuture;

public class MqttConfig {
    private final Mqtt3AsyncClient client;

    public MqttConfig() {
        client = MqttClient.builder()
                .useMqttVersion3()
                .serverHost("broker.hivemq.com")
                .serverPort(1883)
                .buildAsync();
    }

    public CompletableFuture<Mqtt3AsyncClient> connect() {
        CompletableFuture<Mqtt3AsyncClient> future = new CompletableFuture<>();
        if (client != null) {
            client.connect().whenComplete((connAck, throwable) -> {
                if (throwable != null) {
                    System.out.println("Error connecting to the broker: " + throwable.getMessage());
                    future.completeExceptionally(throwable);
                } else {
                    System.out.println("Successfully connected to the MQTT broker.");
                    future.complete(client);
                }
            });
        } else {
            future.completeExceptionally(new Exception("Client is null"));
        }
        return future;
    }
}
