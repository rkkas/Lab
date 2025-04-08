import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese la clave numérica inicial:");
        String clave = scanner.nextLine();

        BigVigenere bv = new BigVigenere(clave);

        System.out.println("Mensaje a cifrar:");
        String mensaje = scanner.nextLine();

        String cifrado = bv.encrypt(mensaje);
        System.out.println("Mensaje cifrado: " + cifrado);

        String descifrado = bv.decrypt(cifrado);
        System.out.println("Mensaje descifrado: " + descifrado);

        // Validar posición de acceso al mensaje cifrado
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

        scanner.close();
    }
}
