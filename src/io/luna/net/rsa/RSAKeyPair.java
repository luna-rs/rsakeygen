package io.luna.net.rsa;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

/**
 * A model representing a newly generated RSA public and private key pair.
 *
 * @author lare96 <http://github.com/lare96>
 */
final class RSAKeyPair {

    /**
     * Generates a new RSA key pair.
     *
     * @return The newly generated RSA key pair.
     * @throws NoSuchAlgorithmException If the RSA algorithm is unavailable.
     * @throws InvalidKeySpecException  If the key specification is invalid.
     */
    static RSAKeyPair newKeyPair() throws NoSuchAlgorithmException, InvalidKeySpecException {

        // Initialize RSA key generator.
        KeyPairGenerator rsaKeyGen = KeyPairGenerator.getInstance("RSA");
        rsaKeyGen.initialize(1024);

        // Retrieve modulus and exponent values for each key.
        KeyPair keypair = rsaKeyGen.genKeyPair();
        KeyFactory rsaKeyFactory = KeyFactory.getInstance("RSA");
        RSAPrivateKeySpec privSpec = rsaKeyFactory.getKeySpec(keypair.getPrivate(), RSAPrivateKeySpec.class);
        RSAPublicKeySpec pubSpec = rsaKeyFactory.getKeySpec(keypair.getPublic(), RSAPublicKeySpec.class);

        // Store them!
        return new RSAKeyPair(privSpec, pubSpec);
    }

    /**
     * The private key.
     */
    private final RSAPrivateKeySpec privateKey;

    /**
     * The public key.
     */
    private final RSAPublicKeySpec publicKey;

    /**
     * Creates a new {@link RSAKeyPair}.
     *
     * @param privateKey The private key.
     * @param publicKey The public key.
     */
    private RSAKeyPair(RSAPrivateKeySpec privateKey, RSAPublicKeySpec publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    /**
     * Writes the generated keys to the designated files.
     *
     * @param privateFile The file to store the private key.
     * @param publicFile The file to store the public key.
     * @throws IOException If any I/O errors occur while writing to the files.
     */
    void writeToFile(Path privateFile, Path publicFile) throws IOException {
        byte[] privateBytes = getBytes(privateKey.getModulus(), privateKey.getPrivateExponent());
        byte[] publicBytes = getBytes(publicKey.getModulus(), publicKey.getPublicExponent());
        Files.write(privateFile, privateBytes);
        Files.write(publicFile, publicBytes);

        System.out.println("RSA private and public keys successfully generated.");
    }

    /**
     * Returns the bytes detailing the text to write to files.
     *
     * @param mod The modulus to write.
     * @param exp The exponent to write.
     * @return The private bytes.
     */
    private byte[] getBytes(BigInteger mod, BigInteger exp) {
        String writeString = "[key]\n" + "modulus = " + '"' + mod + '"' + '\n' +
                "exponent = " + '"' + exp + '"';
        return writeString.getBytes();
    }
}