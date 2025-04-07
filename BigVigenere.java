import java.util.Scanner;

public class BigVigenere {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        char[][] matrizAlfabeto = new char[64][64];
        char[] alfabetoMinusculas = {'a','b','c','d','e','f','g','h','i','j','k','l','m',
                                     'n','o','p','q','r','s','t','u','v','w','x','y','z'};

        char[] alfabetoMayusculas = {'A','B','C','D','E','F','G','H','I','J','K','L','M',
                                     'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

        char[] numeros = {'0','1','2','3','4','5','6','7','8','9'};

        int totalCaracteres = alfabetoMinusculas.length + alfabetoMayusculas.length + numeros.length;

        for (int fila = 0; fila < 64; fila++) {
            int numero = fila;
            for (int columna = 0; columna < 64; columna++) {
                int pos = numero % totalCaracteres;
                if (pos < alfabetoMinusculas.length) {
                    matrizAlfabeto[fila][columna] = alfabetoMinusculas[pos];
                } else if (pos < alfabetoMinusculas.length + alfabetoMayusculas.length) {
                    matrizAlfabeto[fila][columna] = alfabetoMayusculas[pos - alfabetoMinusculas.length];
                } else {
                    matrizAlfabeto[fila][columna] = numeros[pos - alfabetoMinusculas.length - alfabetoMayusculas.length];
                }
                numero++;
            }
        }

        System.out.println("¿Cifrar o descifrar?");
        System.out.println("1: cifrar");
        System.out.println("2: descifrar");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion == 1) {
            System.out.println("Escribe el texto a cifrar:");
            String mensaje = scanner.nextLine();
            System.out.println("Clave para cifrar:");
            String clave = scanner.nextLine();

            String mensajeCifrado = cifrarVigenere(mensaje, clave, matrizAlfabeto);
            System.out.println("Mensaje cifrado: " + mensajeCifrado);

        } else if (opcion == 2) {
            System.out.println("Escribe el texto cifrado:");
            String mensajeCifrado = scanner.nextLine();
            System.out.println("Clave para descifrar:");
            String clave = scanner.nextLine();

            String mensajeDescifrado = descifrarVigenere(mensajeCifrado, clave, matrizAlfabeto);
            System.out.println("Mensaje descifrado: " + mensajeDescifrado);

        } else {
            System.out.println("Opción no válida.");
        }

        scanner.close();
    }

    public static String cifrarVigenere(String mensaje, String clave, char[][] matrizAlfabeto) {
        StringBuilder mensajeCifrado = new StringBuilder();
        int longitudClave = clave.length();

        for (int i = 0; i < mensaje.length(); i++) {
            char caracterMensaje = mensaje.charAt(i);
            char caracterClave = clave.charAt(i % longitudClave);

            int fila = obtenerFila(caracterMensaje, matrizAlfabeto);
            int columna = obtenerColumna(caracterClave, matrizAlfabeto);

            if (fila == -1 || columna == -1) {
                mensajeCifrado.append(caracterMensaje);
            } else {
                mensajeCifrado.append(matrizAlfabeto[fila][columna]);
            }
        }

        return mensajeCifrado.toString();
    }

    public static String descifrarVigenere(String mensajeCifrado, String clave, char[][] matrizAlfabeto) {
        StringBuilder mensajeDescifrado = new StringBuilder();
        int longitudClave = clave.length();

        for (int i = 0; i < mensajeCifrado.length(); i++) {
            char caracterMensaje = mensajeCifrado.charAt(i);
            char caracterClave = clave.charAt(i % longitudClave);

            int columna = obtenerColumna(caracterClave, matrizAlfabeto);

            for (int fila = 0; fila < 64; fila++) {
                if (matrizAlfabeto[fila][columna] == caracterMensaje) {
                    mensajeDescifrado.append(matrizAlfabeto[fila][0]);
                    break;
                }
            }
        }

        return mensajeDescifrado.toString();
    }

    public static int obtenerFila(char caracter, char[][] matrizAlfabeto) {
        for (int columna = 0; columna < 64; columna++) {
            if (matrizAlfabeto[0][columna] == caracter) {
                return columna;
            }
        }
        return -1;
    }

    public static int obtenerColumna(char caracter, char[][] matrizAlfabeto) {
        for (int columna = 0; columna < 64; columna++) {
            if (matrizAlfabeto[0][columna] == caracter) {
                return columna;
            }
        }
        return -1;
    }
}
