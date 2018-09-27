package io.luna.net.rsa;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.function.Consumer;

/**
 * A {@link Consumer} implementation that will generate new RSA keys and store them in the local file system.
 *
 * @author lare96 <http://github.com/lare96>
 */
final class RSAKeyGen implements Consumer<String> {

    /**
     * The private RSA key file.
     */
    private final Path privateFile = Paths.get("rsapriv.toml");

    /**
     * The public RSA key file.
     */
    private final Path publicFile = Paths.get("rsapub.toml");

    @Override
    public void accept(String s) {
        try {
            RSAKeyPair keyPair = RSAKeyPair.newKeyPair();
            keyPair.writeToFile(privateFile, publicFile);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException e) {
            e.printStackTrace();
        } finally {
            RSAKeyGenMain.exit();
        }
    }
}