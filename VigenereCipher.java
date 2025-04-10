import java.util.Random;

public class VigenereCipher {

    public static void runTests(String key, String message) {
        BigVigenere cipher = new BigVigenere(key);
        
        long startTime = System.nanoTime();
        String encryptedMessage = cipher.encrypt(message);
        long endTime = System.nanoTime();
        long encryptionTime = endTime - startTime;

        System.out.println("Mensaje cifrado: " + encryptedMessage);
        System.out.println("Tiempo de cifrado: " + encryptionTime + " ns");

        startTime = System.nanoTime();
        String decryptedMessage = cipher.decrypt(encryptedMessage);
        endTime = System.nanoTime();
        long decryptionTime = endTime - startTime;

        System.out.println("Mensaje descifrado: " + decryptedMessage);
        System.out.println("Tiempo de descifrado: " + decryptionTime + " ns");
    }

    public static void evaluatePerformance(String message) {
        String[] keySizes = {"10", "50", "100", "500", "1000", "5000"};
        
        for (String keySize : keySizes) {
            String key = generateRandomKey(Integer.parseInt(keySize));

            long startTime = System.nanoTime();
            BigVigenere cipher = new BigVigenere(key);
            String encryptedMessage = cipher.encrypt(message);
            long endTime = System.nanoTime();
            long encryptionTime = endTime - startTime;

            startTime = System.nanoTime();
            String decryptedMessage = cipher.decrypt(encryptedMessage);
            endTime = System.nanoTime();
            long decryptionTime = endTime - startTime;

            System.out.println("Clave de tamaño " + keySize);
            System.out.println("Tiempo de cifrado: " + encryptionTime + " ns");
            System.out.println("Tiempo de descifrado: " + decryptionTime + " ns");
        }
    }

    private static String generateRandomKey(int size) {
        StringBuilder key = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            char randomChar = (char) ('a' + random.nextInt(26));
            key.append(randomChar);
        }
        return key.toString();
    }
}
