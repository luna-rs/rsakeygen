package io.luna.net.rsa;

import java.util.Scanner;
import java.util.function.Consumer;

/**
 * A model internally backed by a {@link Scanner} that will read input from the terminal.
 *
 * @author lare96 <http://github.com/lare96>
 */
final class TerminalReader {

    /**
     * The scanner for reading input.
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Awaits the next input. Will block indefinitely.
     *
     * @return The input.
     */
    private String awaitNextInput() {
        return scanner.next();
    }

    /**
     * Awaits the next input matching {@code expected}, then executes {@code onMatching} once the input
     * matches. Will block indefinitely.
     *
     * @param expected The input to await.
     * @param onMatching The action to execute when matching.
     */
    void awaitNextInputMatching(String expected, Consumer<String> onMatching) {
        for (; ; ) {
            if (awaitNextInput().equals(expected)) {
                onMatching.accept(expected);
                break;
            }
        }
    }
}