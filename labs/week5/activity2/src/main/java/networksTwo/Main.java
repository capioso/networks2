package networksTwo;
import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) {
        String reportPath = "ping_report.txt";
        int totalTests = 4;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(reportPath))) {
            writer.write("Ping Test Report\n");
            writer.write("-----------------------------------------------------\n");

            for (String host : args) {
                writer.write("Pinging " + host + ":\n");

                for (int i = 0; i < totalTests; i++) {
                    try {
                        long startTime = System.currentTimeMillis();
                        InetAddress inet = InetAddress.getByName(host);
                        boolean reachable = inet.isReachable(1000);
                        long endTime = System.currentTimeMillis();
                        long responseTime = endTime - startTime;

                        if (reachable) {
                            writer.write("Test " + (i + 1) + ": Reply from " + host + ", Time: " + responseTime + " ms\n");
                        } else {
                            writer.write("Test " + (i + 1) + ": No reply from " + host + "\n");
                        }
                    } catch (IOException e) {
                        writer.write("Test " + (i + 1) + ": Error - " + e.getMessage() + "\n");
                    }
                }
                writer.write("-----------------------------------------------------\n");
            }
            System.out.println("Ping test completed");
        } catch (IOException e) {
            System.err.println("Error writing report: " + e.getMessage());
        }
    }
}