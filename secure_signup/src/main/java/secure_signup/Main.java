package secure_signup;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.mindrot.jbcrypt.BCrypt;

public class Main {
    private static Scanner entrada;
    public static void main(String[] args) throws SQLException {
        entrada = new Scanner(System.in);
        int opcion = 0;
        while (opcion != 2){
            Menu();
            System.out.println("Dime opcion");
            opcion=entrada.nextInt();
            switch(opcion){
                case 1:
                    signUp();
                    break;
            }
        }
    }
    public static void Menu(){
        System.out.println("1-Sign Up");
        System.out.println("2-Exit");
    }
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,}$");
    public static void signUp() throws SQLException {
        entrada.nextLine();
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
        String passwordHash="";
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
            if(!Arrays.equals(password,password2)){
                System.out.println("The passwords must match");
                return;
            }
            passwordHash=BCrypt.hashpw(new String(password), BCrypt.gensalt());
            User user=new User(name,lastName,email,passwordHash);
        } finally {
            // We immediately clear the RAM so that passwords are not stored in plain text.
            Arrays.fill(password, '0');
            Arrays.fill(password2, '0');
        }
        if(BBDD.addUsers(name,lastName,email,passwordHash)) System.out.println("User added");
        else System.out.println("The user was not added.");

    }
}
