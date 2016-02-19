package javaserver;

public class App 
{
    public static void main(String[] args) {
        Server server = new Server(5000);
        server.start();
    }
}
