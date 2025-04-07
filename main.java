public class BigVigenere {
    private int[] key;
    private char[][] alphabet;
    private static String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ñÑ";

    public BigVigenere(String numericKey) {
        setKey(numericKey);
        buildAlphabet();
    }

    private void setKey(String numericKey) {
        key = numericKey.chars().map(Character::getNumericValue).toArray();
    }

    private void buildAlphabet() {
        int n = characters.length();
        alphabet = new char[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                alphabet[i][j] = characters.charAt((i + j) % n);
    }

    public String encrypt(String message) {
        StringBuilder r = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            int row = characters.indexOf(c);
            int col = key[i % key.length];
            r.append(row == -1 ? c : alphabet[row][col]);
        }
        return r.toString();
    }
