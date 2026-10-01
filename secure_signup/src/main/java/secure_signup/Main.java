package secure_signup;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static Scanner entrada;
    public static void main(String[] args) {
        entrada=new Scanner(System.in);
    }

    public static void Menu(){
        System.out.println("1-Sign Up");
        System.out.println("2-Exit");
    }
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,}$");
    public static void signUp(){

        System.out.println("Name: ");
        String name = entrada.nextLine();
        System.out.println("Last Name: ");
        String lastName=entrada.nextLine();
        System.out.println("Email: ");
        String email=entrada.nextLine();
        System.out.println("Password: ");
        char[] password=entrada.nextLine().toCharArray();
        System.out.println("Repeat your password: ");
        char[] password2=entrada.nextLine().toCharArray();
        try{
            if(name.isEmpty() || lastName.isEmpty()){
                System.out.println("Name or Last Name are brong");
                return;
            }
            if(!EMAIL_PATTERN.matcher(email).matches()){
                System.out.println("The E-Mail is invalid");
                return;
            }
            if(password.length<8){
                System.out.println("The password is too short");
                return;
            }
            if(Arrays.equals(password,password2)){
                System.out.println("The passwords must match");
                return;
            }
        } finally {
            // Limpieza inmediata de AMBOS arrays en la memoria RAM
            Arrays.fill(password, '0');
            Arrays.fill(password2, '0');
        }
    }
}
