package io.luna.net.rsa;

import javafx.application.Application;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The main class that will launch the terminal.
 *
 * @author lare96 <http://github.com/lare96>
 */
public final class RSAKeyGenMain {

    /**
     * The main method, the point of initialization.
     */
    public static void main(String[] args) {
        doKeys();
    }

    /**
     * Attempts to generate and publish new RSA keys.
     */
    private static void doKeys() {
        Path privateFile = Paths.get("rsapriv.toml");
        Path publicFile = Paths.get("rsapub.toml");

        try {
            RSAKeyPair keyPair = RSAKeyPair.newKeyPair();
            keyPair.writeToFile(privateFile, publicFile);
        } catch (Exception e) {
            exit(e);
            return;
        }
        exit();
    }

    /**
     * Causes the application to gracefully exit.
     */
    private static void exit() {
        exit(null);
    }

    /**
     * Causes the application to exit because of an error.
     *
     * @param e The error that caused the exit.
     */
    private static void exit(Exception e) {
        if (e == null) {
            Application.launch(AlertWindow.class);
        } else {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Application.launch(AlertWindow.class, sw.toString());
        }
    }
}