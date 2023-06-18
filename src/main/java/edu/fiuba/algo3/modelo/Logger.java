package edu.fiuba.algo3.modelo;

public class Logger {

    private static final Logger singleInstance = new Logger();

    private static boolean loggerEnable = true;

    private Logger() {
    }

    public static Logger get() {
        return singleInstance;
    }

    public void toggle(boolean bool){
        loggerEnable = bool;
    }

    public void log(String loggedMessage) {
        if (loggerEnable)
            System.out.println(loggedMessage);
    }
}