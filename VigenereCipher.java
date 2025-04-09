import java.util.Random;

public class VigenereCipher {

    // Método para realizar la prueba de cifrado y descifrado con la clave y el mensaje proporcionado
    public static void runTests(String key, String message) {
        BigVigenere cipher = new BigVigenere(key);
        
        // Cifrar el mensaje
        long startTime = System.nanoTime();
        String encryptedMessage = cipher.encrypt(message);
        long endTime = System.nanoTime();
        long encryptionTime = endTime - startTime;

        System.out.println("Mensaje cifrado: " + encryptedMessage);
        System.out.println("Tiempo de cifrado: " + encryptionTime + " ns");

        // Descifrar el mensaje
        startTime = System.nanoTime();
        String decryptedMessage = cipher.decrypt(encryptedMessage);
        endTime = System.nanoTime();
        long decryptionTime = endTime - startTime;

        System.out.println("Mensaje descifrado: " + decryptedMessage);
        System.out.println("Tiempo de descifrado: " + decryptionTime + " ns");
    }

    // Método para evaluar el tiempo de ejecución con diferentes tamaños de clave
    public static void evaluatePerformance(String message) {
        String[] keySizes = {"10", "50", "100", "500", "1000", "5000"};
        
        for (String keySize : keySizes) {
            String key = generateRandomKey(Integer.parseInt(keySize));

            // Medir tiempo de cifrado
            long startTime = System.nanoTime();
            BigVigenere cipher = new BigVigenere(key);
            String encryptedMessage = cipher.encrypt(message);
            long endTime = System.nanoTime();
            long encryptionTime = endTime - startTime;

            // Medir tiempo de descifrado
            startTime = System.nanoTime();
            String decryptedMessage = cipher.decrypt(encryptedMessage);
            endTime = System.nanoTime();
            long decryptionTime = endTime - startTime;

            System.out.println("Clave de tamaño " + keySize);
            System.out.println("Tiempo de cifrado: " + encryptionTime + " ns");
            System.out.println("Tiempo de descifrado: " + decryptionTime + " ns");
        }
    }

    // Método para generar una clave aleatoria de un tamaño determinado
    private static String generateRandomKey(int size) {
        StringBuilder key = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            char randomChar = (char) ('a' + random.nextInt(26)); // Letras aleatorias
            key.append(randomChar);
        }
        return key.toString();
    }
}
