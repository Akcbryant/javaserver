package javaserver.helpers;

public class ArgumentParser {

    public static int getPort(String[] args) {
        int port = 5000;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-p")) {
              port = Integer.parseInt(args[i + 1]);
            }
        }

        return port;
    }

    public static String getDirectory(String[] args) {
        String directory = System.getProperty("user.dir");

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-d")) {
                directory = args[i + 1];
          }
        }

        return directory;
    }
}
