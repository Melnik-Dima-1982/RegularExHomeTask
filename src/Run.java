import java.util.Scanner;

public class Run {
    public static void main(String[] args) {
        RegularEx testRegularEx = new RegularEx();
//        testRegularEx.checkIP();
//        testRegularEx.startWithCapitalLetterAndFinishWithPoint();
//        testRegularEx.checkEmail();
        testStringBuilder();
    }

    public static void testStringBuilder (){
        System.out.print ("Enter File`s Path - ");
        String userInput = new Scanner(System.in).nextLine();
//        D:\\Java\\Stormnet\\RegularExHomeTask\\Worlds.txt
        new RegularEx().getLine(userInput);
    }
}
