import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese la clave numérica inicial:");
        String clave = scanner.nextLine();

        System.out.println("Ingrese el mensaje a cifrar:");
        String mensaje = scanner.nextLine();

        BigVigenere bv = new BigVigenere(clave);
        
        long startTime = System.nanoTime();
        String cifrado = bv.encrypt(mensaje);
        long endTime = System.nanoTime();
        long encryptionTime = endTime - startTime;
        System.out.println("Mensaje cifrado: " + cifrado);
        System.out.println("Tiempo de cifrado: " + encryptionTime + " ns");

        startTime = System.nanoTime();
        String descifrado = bv.decrypt(cifrado);
        endTime = System.nanoTime();
        long decryptionTime = endTime - startTime;
        System.out.println("Mensaje descifrado: " + descifrado);
        System.out.println("Tiempo de descifrado: " + decryptionTime + " ns");

        System.out.println("Ingrese una posición para buscar en el mensaje cifrado (0 a " + (cifrado.length() - 1) + "):");
        int pos = scanner.nextInt();

        if (pos >= 0 && pos < cifrado.length()) {
            char resultadoSearch = bv.search(pos);
            char resultadoOptimal = bv.optimalSearch(pos);
            System.out.println("search(" + pos + "): " + resultadoSearch);
            System.out.println("optimalSearch(" + pos + "): " + resultadoOptimal);
        } else {
            System.out.println("Posición fuera de rango. Debe estar entre 0 y " + (cifrado.length() - 1));
        }

        System.out.println("\nEjecutando pruebas...");
        VigenereCipher.runTests(clave, mensaje);

        System.out.println("\nEvaluando rendimiento...");
        VigenereCipher.evaluatePerformance(mensaje);

        scanner.close();
    }
}
