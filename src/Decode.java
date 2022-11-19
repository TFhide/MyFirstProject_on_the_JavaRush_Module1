import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Decode {

    public static void main(String[] args) {
        System.out.println(decodeStatAnalyze("!?.ъэА,ш,Чьэ-ш"));
    }

    public static String toDecoding(String encodeText, int key) {
        StringBuilder decodeText = new StringBuilder();
        final int len = encodeText.length();
        key %= 71;

        for (int i = 0; i < len; i++) {
            int position = Encode.ALPHABET.indexOf(encodeText.charAt(i));
            if (Encode.isCapitalLetter(encodeText.charAt(i))) {
                int keyVal = (position - key) % 71;
                if (keyVal < 0) {
                    keyVal = Encode.ALPHABET.length() + keyVal;
                }
                char replaceVal = Encode.ALPHABET.charAt(keyVal);
                decodeText.append(replaceVal);
            } else if (Encode.isSmallLatter(encodeText.charAt(i))) {
                int keyVal = (position - key) % 71;
                if (keyVal < 0) {
                    keyVal = Encode.ALPHABET.length() + keyVal;
                }
                char replaceVal = Encode.ALPHABET.charAt(keyVal);
                decodeText.append(replaceVal);
            } else {
                decodeText.append(encodeText.charAt(i));
            }
        }
        return decodeText.toString();
    }

    public static String decodeBrute(String encodeText) {
        String[] listOfTheAnswers = new String[71];
        String str = "";
        for (int i = 0; i < 71; i++) {
            listOfTheAnswers[i] = toDecoding(encodeText, i + 1);
            String regex = "(\\W|^)(\sна\s|\sоб\s|\sбез\s|\sмежду\s|\sпод\s|\sв\s|\sпо\s|\sвокруг\s|\sо\s|\sпро\s|" +
                    "\sдо\s|\sс\s|\sдля\s|\sоколо\s|\sиз-за\s|\sза\s|\sот\s|\sиз-под\s|\sк\s|\sперед\s|" +
                    "\sкак\s|\sкогда\s|\sгде\s|\sкакой\s|\sтот\s|\sэтот\s|\sкоторый\s|\sили\s|\sя\s|\sон\s|\sона\s)|\sгод\s|\sты\s|\sвы\s(\\W|$)";
            Pattern pattern = Pattern.compile(regex);
            Matcher match = pattern.matcher(listOfTheAnswers[i]);
            while (match.find()) {
                str = listOfTheAnswers[i];
            }
        }
        return str;
    }

    public static String decodeStatAnalyze(String encodeText) {
        String listOfTheAnswers = "";

        StringBuilder sb = new StringBuilder();


        for (int i = 0; i < 71; i++) {
            float countA = 0;
            float countO = 0;
            float countE = 0;
            float countS = 0;
            listOfTheAnswers = toDecoding(encodeText, i);
            if (listOfTheAnswers != null) {
                for (int j = 0; j < listOfTheAnswers.length(); j++) {
                    if (listOfTheAnswers.charAt(j) == 'а' || listOfTheAnswers.charAt(j) == 'А') {
                        countA += 1;
                    } else if (listOfTheAnswers.charAt(j) == 'о' || listOfTheAnswers.charAt(j) == 'О') {
                        countO += 1;
                    } else if (listOfTheAnswers.charAt(j) == 'е' || listOfTheAnswers.charAt(j) == 'Е') {
                        countE += 1;
                    } else if (listOfTheAnswers.charAt(j) == ' ') {
                        countS += 1;
                    }
                }
                System.out.println(i + " " + (countS * 100) / encodeText.length());
                if (encodeText.length() < 10) {
                    if ((countA * 100) / encodeText.length() >= 1 && 1 <= (countO * 100) / encodeText.length() && 1 <= (countE * 100) / encodeText.length() && 1 <= (countS * 100) / encodeText.length())
                        sb.append(listOfTheAnswers);
                }
                if ((countA * 100) / encodeText.length() > 4 && 4 < (countO * 100) / encodeText.length() && 4 < (countE * 100) / encodeText.length() && 8 < (countS * 100) / encodeText.length())
                    sb.append(listOfTheAnswers);
            }
        }
        return sb.toString();
    }
}


