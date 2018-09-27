package io.luna.net.rsa;

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
        System.out.println("RSAKeyGen v1.0");
        System.out.println("Type (y) to generate new keys.");
        System.out.println("Keep in mind that doing this will overwrite existing keys.");

        TerminalReader terminal = new TerminalReader();
        terminal.awaitNextInputMatching("y", new RSAKeyGen());
    }

    /**
     * Causes the application to gracefully exit.
     */
    static void exit() {
        System.out.println("The application will now exit.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ignored) {
        }
        System.exit(0);
    }
}