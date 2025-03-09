import java.util.Base64;

interface EncryptionStrategy {
    String encrypt(String text);
}

class EncryptionService {

    private EncryptionStrategy strat;

    public void setEncryptionStrategy(EncryptionStrategy method) {
        this.strat = method;
    }

    public String encrypt(String text) {
        return strat.encrypt(text);
    }

    public static void main(String[] args) {
        
    }
}

class CaesarCipherEncryption implements EncryptionStrategy {

    private final int shift;

    public CaesarCipherEncryption(int shift) {
        this.shift = shift;
    }

    @Override
    public String encrypt(String text) {
        StringBuilder result = new StringBuilder();
        int shift = this.shift;
            for (char ch : text.toCharArray()) {
                if (Character.isLetter(ch)) {
                    char base = Character.isLowerCase(ch) ? 'a' : 'A';
                    ch = (char) ((ch - base + shift) % 26 + base);
                }
                result.append(ch);
            }
        return result.toString();
    }
}

class Base64Encryption implements EncryptionStrategy {

    @Override
    public String encrypt(String text) {
        return Base64.getEncoder().encodeToString(text.getBytes());
    }
}

class XOREncryption implements EncryptionStrategy {

    private char key;
    public XOREncryption(char k) {
        this.key = k;
    }

    @Override
    public String encrypt(String text) {
        StringBuilder result = new StringBuilder();
        char key = this.key;
        for (char ch : text.toCharArray()) {
            result.append((char) (ch ^ key));
        }
        return result.toString();
    }
}

class ReverseStringEncryption implements EncryptionStrategy {

    @Override
    public String encrypt(String text) {
        
        String newstring = "";
        char ch;

        for (int i = 0; i < text.length(); i++) {
            ch = text.charAt(i);
            newstring = ch + newstring; 
        }

        return newstring;
    }
}

class DuplicateCharacterEncryption implements EncryptionStrategy {

    @Override
    public String encrypt(String text) {
        String newstring = "";

        for (int i = 0; i < text.length(); i++) {
            newstring += text.charAt(i);
            newstring += text.charAt(i);
        }
        return newstring;
    }
}
