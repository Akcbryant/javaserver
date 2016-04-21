package javaserver.helpers;

public class ArgumentParser {

    protected static final String PORT_FLAG = "-p";
    protected static final String DIRECTORY_FLAG = "-d";

    protected static final String DEFAULT_PORT = "5000";
    protected static final String DEFAULT_DIRECTORY = System.getProperty("user.dir");

    public static String getPort(String[] args) {
        return getArgument(args, PORT_FLAG, DEFAULT_PORT);
    }

    public static String getDirectory(String[] args) {
        return getArgument(args, DIRECTORY_FLAG, DEFAULT_DIRECTORY);
    }

    private static String getArgument(String[] args, String flag, String defaultValue) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(flag)) {
                return args[i + 1];
            }
        }
        return defaultValue;
    }
}
