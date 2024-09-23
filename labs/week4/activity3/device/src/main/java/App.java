import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class App {

    private static final int IDEAL_TEMPERATURE = 22;

    public static void main(String[] args) {
        try {
            MqttConnection mqttConnection = new MqttConnection();
            Random random = new Random();

            while (true) {
                int sensorTemperature = random.nextInt(40);

                String state;
                if (sensorTemperature < IDEAL_TEMPERATURE) {
                    state = "heating";
                } else if (sensorTemperature > IDEAL_TEMPERATURE) {
                    state = "cooling";
                } else {
                    state = "idle";
                }

                String message = String.format("Sensor: %d, Ideal: %d, State: %s",
                        sensorTemperature, IDEAL_TEMPERATURE, state);

                mqttConnection.sendMessage("CapiosoConnection", message);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("Problem sleeping: " + e.getMessage());
                }
            }

        } catch (Exception e) {
            System.out.println("Error working with the broker: " + e.getMessage());
        }

    }
}
