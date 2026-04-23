import java.util.Scanner;
public class PasswordValidator{
    //
    static boolean lengthCheck=false;
    static boolean haveCapital=false;
    static boolean haveNumber=false;

    static void runPolicyCheck(String passwordToCheck){
        //now lets reset the flags to false before we check the new password.
        lengthCheck=false;
        haveCapital=false;
        haveNumber=false;

        //first lets check the length
        if(passwordToCheck.length()>=8){
            lengthCheck=true; 
        }
        //now we can check for capital letters and numbers in one loop.
        //i is the position of the character we are checking in the password.
        for(int i=0;i<passwordToCheck.length();i++){
            char currentChar=passwordToCheck.charAt(i);
            //lets check the capital letters first.
            if(Character.isUpperCase(currentChar)){
                haveCapital=true;
            }
            //lets check the digits now.
            if(Character.isDigit(currentChar)){
                haveNumber=true;
            }
        }
    }

    static String rejectionMessage(){
        String message="";
        if(!lengthCheck){
            message+="Password must be at least 8 characters long.\n";
        }
        if(!haveCapital){
            message+="Password must contain at least one uppercase letter.\n";
        }
        if(!haveNumber){
            message+="Password must contain at least one digit.\n";
        }
        return message;
    }
    public static void main(String[] args){
        Scanner passwordInput=new Scanner(System.in);
        String currentAttempt="";
        int noofAttempts=0;
        boolean accessGranted=false;
        System.out.println("Welcome to the password validator!");
        System.out.println("Your password must satisfy the following rules:");
        System.out.println("1. At least 8 characters long.");
        System.out.println("2. Contains at least one uppercase letter.");   
        System.out.println("3. Contains at least one digit.");
        System.out.println();
        while(!accessGranted){
            System.out.print("please enter a password: ");
            currentAttempt=passwordInput.nextLine();
            noofAttempts++;
            runPolicyCheck(currentAttempt);
            if(lengthCheck && haveCapital && haveNumber){
                accessGranted=true;
            }else{
                String complaint=rejectionMessage();
                System.out.println();
                System.out.println("Password rejected for the following reasons:");
                System.out.println(complaint);
                System.out.println("Please fix the issues and try again.");
                System.out.println();
            }
        }
        System.out.println();
        System.out.println("Password accepted on attempt #"+noofAttempts+".");
        System.out.println("Your account is now secured. Welcome aboard.");
        passwordInput.close();
    }
}