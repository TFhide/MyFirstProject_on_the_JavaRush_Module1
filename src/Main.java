import java.io.*;
import java.nio.file.Files;
import static java.nio.file.StandardOpenOption.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Main {

    public static int key;
    public static String text;

    public static String pathForEncoding = "/home/Desktop/123/Encode1.txt";
    public static String pathAfterDecoding = "/home/Desktop/123/Decode1.txt";
    public static String pathAfterDecodingBruteForce = "/home/Desktop/123/DecodeBrute1.txt";
    public static String pathAfterDecodingStatAnalyze = "/home/Desktop/123/DecodeStatAnalyze1.txt";


    Main(String text) {
        this.text = text;
    }

    public static int getKey() {
        return key;
    }

    public static void setKey(int keyy) {
        key = keyy;
    }

    public static String getText() {
        return text;
    }

    public static Scanner console = new Scanner(System.in);

    public static void setText(String textt) {

        text = textt;
    }

    public static void main(String[] args) {
        startInfo();
        modesInfo();
        startModes();

    }

    public static void startInfo() {
        System.out.println();
        System.out.println("Привет, для шифровки или расшифровки текста, напиши или вставь текст на русском языке: ");
        while (console.hasNext()) {
            String regex = "[^.abcdefghijklmnopqrstuvwxyz]";
            Pattern pattern = Pattern.compile(regex);
            String inputText = console.nextLine();
            Matcher match = pattern.matcher(inputText);
            if (match.find()) {
                setText(inputText);
                break;
            } else {
                System.out.println("Введен текст на другом языке, пожалуйста смените раскладку на русский язык");
                System.out.println("Введи текст повторно на соответствующем языке");
            }
        }
    }

    public static void modesInfo() {
        System.out.println();
        System.out.println("Текст добавлен, теперь выбери необходимое действие в соответствии c нижепредставленным списком:");
        System.out.println("1. шифровка текста");
        System.out.println("2. расшифровка текста с помощью заданного ключа");
        System.out.println("3. расшифровка текста с помощью brute force");
        System.out.println("4. расшифровка с помощью статистического анализа текста");
        System.out.println();
    }

    private static void startModes() {
        System.out.print("Введи цифру от 1 до 4: ");
        while (console.hasNextInt()) {
            int num = console.nextInt();
            if (num == 1) {
                System.out.println("Введи ключ от 1 до 70 для смещения по алфавиту: ");
                while (console.hasNextInt()) {
                    int key = console.nextInt();
                    if (0 >= key || key >= 71) {
                        System.out.println("Задан некорректный ключ");
                        System.out.println("Введи ключ от 1 до 70");
                        System.out.println("Введи ключ от 1 до 70 или для завершения программы введи слово выход: ");
                        String exit = console.nextLine();
                        if (exit.contains("выход")) {
                            break;
                        }
                    } else if (0 < key && key < 71) {
                        setKey(key);
                        System.out.println("ключ изменен");
                        encodeText();
                        System.out.println("шифрование завершено");
                        break;
                    }

                }
                break;
            }
            if (num == 2) {
                System.out.println("Введи ключ от 1 до 70 для смещения по алфавиту: ");
                while (console.hasNextInt()) {
                    int key = console.nextInt();
                    if (0 >= key || key >= 71) {
                        System.out.println("Задан некорректный ключ");
                        System.out.println("Введи ключ от 1 до 70");
                        System.out.println("Введи ключ от 1 до 70 или для завершения программы введи слово выход: ");
                        String exit = console.nextLine();
                        if (exit.contains("выход")) {
                            break;
                        }
                    } else if (0 < key && key < 71) {
                        setKey(key);
                        System.out.println("ключ изменен");
                        decodeText();
                        System.out.println("расшифровка завершена");
                        break;
                    }
                }
                break;
            }
            if (num == 3) {
                decodeBruteForce();
                System.out.println("расшифровка Brute force завершена");
            }
            if (num == 4) {
                decodeStatAnalyze();
                System.out.println("расшифровка с помощью статистического анализа текста завершена");
            }
            if (0 >= num || num > 4) {
                System.out.println("Введено неккоректное значение.");
                System.out.println("Введи цифру от 1 до 4 или для завершения программы введи слово выход: ");
                String exit = console.nextLine();
                if (exit.contains("выход")) {
                    break;
                }
            }
        }
    }

    private static void encodeText() {
        try {
            File file = new File(pathForEncoding);
            FileWriter fw = new FileWriter(file);
            StringBuilder sb = new StringBuilder(pathForEncoding);
            boolean check = true;
            int count = 2;
            while (check) {
                if (file.exists() && file.isFile()) {
                    fw.write(Encode.toEncoding(getText(), getKey()));
                    fw.flush();
                    fw.close();
                } else {
                    sb.replace(pathForEncoding.length() - 5, pathForEncoding.length() - 4, String.valueOf(count));
                    File file2 = new File(sb.toString());
                    FileWriter fwNew = new FileWriter(file2);
                    fwNew.write(Encode.toEncoding(getText(), getKey()));
                    fwNew.flush();
                    fwNew.close();
                }
                check = false;
                count++;
            }

        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    private static void decodeText() {
        try {
            File file = new File(pathAfterDecoding);
            FileWriter fw = new FileWriter(file);
            StringBuilder sb = new StringBuilder(pathAfterDecoding);
            boolean check = true;
            int count = 2;
            while (check) {
                if (file.exists() && file.isFile()) {
                    fw.write(Decode.toDecoding(getText(), getKey()));
                    fw.flush();
                    fw.close();
                } else {
                    sb.replace(pathAfterDecoding.length() - 5, pathAfterDecoding.length() - 4, String.valueOf(count));
                    File file2 = new File(sb.toString());
                    FileWriter fwNew = new FileWriter(file2);
                    fwNew.write(Decode.toDecoding(getText(), getKey()));
                    fwNew.flush();
                    fwNew.close();
                }
                check = false;
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void decodeBruteForce() {
        try {
            File file = new File(pathAfterDecodingBruteForce);
            FileWriter fw = new FileWriter(file);
            StringBuilder sb = new StringBuilder(pathAfterDecodingBruteForce);
            boolean check = true;
            int count = 2;
            while (check) {
                if (file.exists() && file.isFile()) {
                    fw.write(Decode.decodeBrute(getText()));
                    fw.flush();
                    fw.close();
                } else {
                    sb.replace(pathAfterDecodingBruteForce.length() - 5, pathAfterDecodingBruteForce.length() - 4, String.valueOf(count));
                    File file2 = new File(sb.toString());
                    FileWriter fwNew = new FileWriter(file2);
                    fwNew.write(Decode.decodeBrute(getText()));
                    fwNew.flush();
                    fwNew.close();
                }
                check = false;
                count++;
            }
        } catch (IOException ioo) {
            ioo.printStackTrace();
        }
    }

    private static void decodeStatAnalyze() {
        try {
            File file = new File(pathAfterDecodingStatAnalyze);
            FileWriter fw = new FileWriter(file);
            StringBuilder sb = new StringBuilder(pathAfterDecodingStatAnalyze);
            boolean check = true;
            int count = 2;
            while (check) {
                if (file.exists() && file.isFile()) {
                    fw.write(Decode.decodeStatAnalyze(getText()));
                    fw.flush();
                    fw.close();
                    break;
                } else {
                    sb.replace(pathAfterDecodingStatAnalyze.length() - 5, pathAfterDecodingStatAnalyze.length() - 4, String.valueOf(count));
                    File file2 = new File(sb.toString());
                    FileWriter fwNew = new FileWriter(file2);
                    fwNew.write(Decode.decodeStatAnalyze(getText()));
                    fwNew.flush();
                    fwNew.close();
                }
                check = false;
                count++;
            }
        } catch (IOException ioo) {
            ioo.printStackTrace();
        }
    }
}
