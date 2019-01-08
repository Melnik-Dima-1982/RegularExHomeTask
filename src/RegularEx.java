import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularEx {

    public void checkIP() {
        System.out.print("Enter IP - adress (required format - xxx.xxx.xxx.xxx): ");
        String userInput = new Scanner(System.in).nextLine();
        Pattern splitPattern = Pattern.compile("\\.");
        String[] partOfIP = splitPattern.split(userInput);
        if (partOfIP.length != 4) {
            System.out.println("IP is not correct");
            return;
        }

        for (String word : partOfIP) {
            int num = -1;
            try {
                num = Integer.parseInt(word);
            } catch (NumberFormatException e) {
                System.out.println("IP is not correct");
                return;
            }

            if (num > 0 && num < 255) {
                continue;
            } else {
                System.out.println("IP is not correct");
                return;
            }
        }
        System.out.println("IP is correct");
    }

    public void startWithCapitalLetterAndFinishWithPoint() {
        System.out.print("Enter sentence: ");
        String userInput = new Scanner(System.in).nextLine();
        Pattern patternStartSent = Pattern.compile("^[A-ZА-Я]");
        Matcher matcherStartSent = patternStartSent.matcher(userInput);
        Pattern patternEndSent = Pattern.compile("\\.$");
        Matcher matcherEndSent = patternEndSent.matcher(userInput);
        boolean start = matcherStartSent.find();
        boolean end = matcherEndSent.find();

        if (start & end) {
            System.out.println("Sentence starts with capital letter and finishes with point");
        } else if (!start & end) {
            System.out.println("Sentence doesn't start with capital letter, finishes with point");
        } else if (start & !end) {
            System.out.println("Sentence starts with capital letter, doesn't finish with point");
        } else {
            System.out.println("Sentence doesn't start with capital letter, doesn't finish with point");
        }
    }

    public void checkEmail() {
        System.out.print("Enter your e-mail (required format - username@mailservice.domen): ");
        String userEmail = new Scanner(System.in).nextLine();
        Pattern patternEmail = Pattern.compile(".*@.*\\..*");
//        Pattern patternEmail = Pattern.compile("[a-zA-Z]*@[a-zA-Z]*\\."); ///[a-z]@[a-z]\.[a-z]
        Matcher matcherEmail = patternEmail.matcher(userEmail);
        System.out.println(matcherEmail.matches());
    }

    public void getLine(String filePath) {

        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<String> wordsFromFile = new ArrayList<>();

        try (FileInputStream fin = new FileInputStream(filePath)) {
            int content;
            String currentString = "";
            while ((content = fin.read()) != -1) {
                if ((char) content != ' ') {
                    currentString += ((char) content);
                }
                if ((char) content == ' ') {
                    wordsFromFile.add(currentString);
                    currentString = "";
                }
            }
            wordsFromFile.add(currentString);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        wordsFromFile.trimToSize();
        String firstString = wordsFromFile.get((int) (Math.random() * wordsFromFile.size()));
        stringBuilder.append(firstString + " ");
        wordsFromFile.remove(firstString);

        char lastCharOfString = firstString.charAt(firstString.length() - 1);
        String stringWithLastChar = String.valueOf(lastCharOfString);
        Iterator iterWordsFromFile = wordsFromFile.listIterator();
        while (wordsFromFile.size() != 0) {

            String currentString = (String) iterWordsFromFile.next();
            char firstCharOfString = currentString.charAt(0);
            String StringWithFirstChar = String.valueOf(firstCharOfString);

            if (StringWithFirstChar.equalsIgnoreCase(stringWithLastChar)) {
                stringBuilder.append(currentString + " ");
                lastCharOfString = currentString.charAt(currentString.length() - 1);
                stringWithLastChar = String.valueOf(lastCharOfString);
                iterWordsFromFile.remove();
                iterWordsFromFile = wordsFromFile.listIterator();
                continue;
            }
        }
        System.out.println(stringBuilder);
    }
}
