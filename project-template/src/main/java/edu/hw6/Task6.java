package edu.hw6;

import java.net.DatagramSocket;
import java.net.ServerSocket;

public class Task6 {
    public static void main(String[] args) {
        for (int port = 0; port <= 49151; port++) {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                serverSocket.close();
                System.out.println("TCP Port " + port + " is available");
            } catch (Exception e) {
                System.out.println("TCP Port " + port + " is not available");
            }

            try {
                DatagramSocket datagramSocket = new DatagramSocket(port);
                datagramSocket.close();
                System.out.println("UDP Port " + port + " is available");
            } catch (Exception e) {
                System.out.println("UDP Port " + port + " is not available");
            }
        }
    }
}
