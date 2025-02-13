package LE_05_02;

import java.util.Scanner;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPAddresses {
    public static void main(String[] args) {


        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter IP Address");
            String ip = sc.nextLine();

            InetAddress inet = InetAddress.getByName(ip);
            byte[] bytes = inet.getAddress();

            StringBuilder decimal = new StringBuilder();
            StringBuilder octal = new StringBuilder();
            StringBuilder hexadecimal = new StringBuilder();
            StringBuilder binary = new StringBuilder();

            for (byte b : bytes) {
                int unsignedByte = b & 0xFF;

                decimal.append(unsignedByte).append(":");
                octal.append(Integer.toOctalString(unsignedByte)).append(".");
                hexadecimal.append(Integer.toHexString(unsignedByte)).append(".");
                binary.append(String.format("%08d", Integer.parseInt(Integer.toBinaryString(unsignedByte)))).append(".");
            }

            decimal.setLength(decimal.length() - 1);
            octal.setLength(octal.length() - 1);
            hexadecimal.setLength(hexadecimal.length() - 1);
            binary.setLength(binary.length() - 1);

            System.out.println("\nResults for IP Address: " + ip);
            System.out.println("Decimal: " + decimal);
            System.out.println("Octal: " + octal);
            System.out.println("Hexadecimal: " + hexadecimal.toString().toUpperCase());
            System.out.println("Binary: " + binary);

        } catch (UnknownHostException e) {
            System.out.println("Invalid IP Address.");
        }
    }
}