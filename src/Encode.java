import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Encode {

    public static final String ALPHABET = "АБВГДЕЖЗИКЛИНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдежзиклмнопрстуфхцчшщъыьэюя.,-«»:!? ";

    public static boolean isCapitalLetter(char letter) {
        return ('А' <= letter && letter <= 'Я' || letter == ' ' || letter == '.' || letter == ',' || letter == '-' || letter == ':' || letter == '!' || letter == '?' || letter == '«' || letter == '»');

    }

    public static boolean isSmallLatter(char letter) {
        return ('а' <= letter && letter <= 'я' || letter == ' ' || letter == '.' || letter == ',' || letter == '-' || letter == ':' || letter == '!' || letter == '?' || letter == '«' || letter == '»');

    }

    public static String toEncoding(String textForEncrypt, int key) {
        StringBuilder encodeText = new StringBuilder();
        final int len = textForEncrypt.length();
        key %= 71;
        {
            for (int i = 0; i < len; i++) {
                int position = ALPHABET.indexOf(textForEncrypt.charAt(i));
                if (isCapitalLetter(textForEncrypt.charAt(i))) {
                    int keyVal = (position + key) % 71;
                    char replaceVal = ALPHABET.charAt(keyVal);
                    encodeText.append(replaceVal);
                } else if (isSmallLatter(textForEncrypt.charAt(i))) {
                    int keyVal = (position + key) % 71;
                    char replaceVal = ALPHABET.charAt(keyVal);
                    encodeText.append(replaceVal);
                } else {
                    encodeText.append(textForEncrypt.charAt(i));
                }
            }
            return encodeText.toString();
        }
    }
}



