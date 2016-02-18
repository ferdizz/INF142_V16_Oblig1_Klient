import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static Scanner input;
    private static Socket clientSocket;
    private static BufferedReader in;
    private static DataOutputStream out;

    public static void main(String[] args) throws Exception {
        menu();
    }

    public static void menu() {

        boolean run = true;
        int valg = 0;

        while (run) {
            try {
                input = new Scanner(System.in);

                clientSocket = new Socket("127.0.0.1", 7001);
                out = new DataOutputStream(clientSocket.getOutputStream());
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println();
            System.out.println("Klient - Meny:");
            System.out.println("--------------");
            System.out.println("1) Be om nummer");
            System.out.println("2) Legg til nummer");
            System.out.println("3) Trekk fra nummer");
            System.out.println("4) Hent historie");

            valg = input.nextInt();

            switch (valg) {
                case 1:
                    getNumber();
                    break;
                case 2:
                    addToNumber();
                    break;
                case 3:
                    subFromNumber();
                    break;
                case 4:
                    getHistory();
                    break;
                case 0:
                    System.out.println("Stopping.");
                    run = false;
                    break;
            }
            try {
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void getNumber() {
        try {
            out.writeBytes("ID 1 GET\n");
            System.out.println("Retur: " + in.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addToNumber() {
        System.out.print("Enter number to add: ");
        int num = input.nextInt();
        try {
            out.writeBytes("ID 1 ADD " + num + "\n");
            System.out.println("Retur: " + in.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void subFromNumber() {
        System.out.print("Enter number to subtract: ");
        int num = input.nextInt();
        try {
            out.writeBytes("ID 1 SUB " + num + "\n");
            System.out.println("Retur: " + in.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getHistory() {
        try {
            out.writeBytes("ID 1 HISTORY\n");
            System.out.println("Retur:\n" + in.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
