import java.util.Scanner;

public class BigVigenere {
    private int[] key;
    private char[][] matrizAlfabeto;
    private String mensajeCifradoGlobal = "";

    public BigVigenere(String clave) {
        this.key = new int[clave.length()];
        for (int i = 0; i < clave.length(); i++) {
            key[i] = (int) clave.charAt(i);
        }

        char[] alfabetoMinusculas = {'a','b','c','d','e','f','g','h','i','j','k','l','m',
                                     'n','o','p','q','r','s','t','u','v','w','x','y','z'};

        char[] alfabetoMayusculas = {'A','B','C','D','E','F','G','H','I','J','K','L','M',
                                     'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

        char[] numeros = {'0','1','2','3','4','5','6','7','8','9'};

        int totalCaracteres = alfabetoMinusculas.length + alfabetoMayusculas.length + numeros.length;

        matrizAlfabeto = new char[64][64];

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
    }

    public String encrypt(String mensaje) {
        StringBuilder mensajeCifrado = new StringBuilder();
        int longitudClave = key.length;
        for (int i = 0; i < mensaje.length(); i++) {
            char caracterMensaje = mensaje.charAt(i);
            char caracterClave = (char) key[i % longitudClave];

            int fila = obtenerFila(caracterMensaje);
            int columna = obtenerColumna(caracterClave);

            if (fila == -1 || columna == -1) {
                mensajeCifrado.append(caracterMensaje);
            } else {
                mensajeCifrado.append(matrizAlfabeto[fila][columna]);
            }
        }

        mensajeCifradoGlobal = mensajeCifrado.toString();
        return mensajeCifradoGlobal;
    }

    public String decrypt(String mensajeCifrado) {
        StringBuilder mensajeDescifrado = new StringBuilder();
        int longitudClave = key.length;
    
        for (int i = 0; i < mensajeCifrado.length(); i++) {
            char caracterMensaje = mensajeCifrado.charAt(i);
            char caracterClave = (char) key[i % longitudClave];
    
            int columna = obtenerColumna(caracterClave);
    
            for (int fila = 0; fila < 64; fila++) {
                if (matrizAlfabeto[fila][columna] == caracterMensaje) {
                    mensajeDescifrado.append(matrizAlfabeto[fila][0]);
                    break;
                }
            }
        }
    
        return mensajeDescifrado.toString();
    }

    private int obtenerFila(char caracter) {
        for (int columna = 0; columna < 64; columna++) {
            if (matrizAlfabeto[0][columna] == caracter) {
                return columna;
            }
        }
        return -1;
    }

    private int obtenerColumna(char caracter) {
        for (int columna = 0; columna < 64; columna++) {
            if (matrizAlfabeto[0][columna] == caracter) {
                return columna;
            }
        }
        return -1;
    }

    public char search(int position) {
        if (position >= 0 && position < mensajeCifradoGlobal.length()) {
            return mensajeCifradoGlobal.charAt(position);
        }
        return '?';
    }

    public char optimalSearch(int position) {
        return search(position);
    }

    public void reEncrypt() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Ingrese el mensaje cifrado:");
        String mensajeCifrado = scanner.nextLine();
        
        System.out.println("Ingrese la clave actual para descifrar el mensaje:");
        String claveActual = scanner.nextLine();
        BigVigenere cipherDescifrado = new BigVigenere(claveActual);
        String mensajeDescifrado = cipherDescifrado.decrypt(mensajeCifrado);

        System.out.println("Mensaje descifrado con la clave actual: " + mensajeDescifrado);

        System.out.println("Ingrese la nueva clave para cifrar el mensaje:");
        String nuevaClave = scanner.nextLine();

        BigVigenere cipherCifrado = new BigVigenere(nuevaClave);
        String nuevoMensajeCifrado = cipherCifrado.encrypt(mensajeDescifrado);

        System.out.println("Nuevo mensaje cifrado con la nueva clave: " + nuevoMensajeCifrado);
    }
}
