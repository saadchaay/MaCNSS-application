package org.cnss.helpers;

import java.util.Objects;
import java.util.Scanner;

public class LoginForm {
    private String credentials;
    private String password;

    Scanner in = new Scanner(System.in);

    public String getCredentials() {
        return credentials;
    }

    public String getPassword() {
        return password;
    }

    public void displayForm(Sessions session){
        System.out.println("\n\t*\t\tAuthentication\t\t*");
        if(Objects.equals(session.getLoggedIn(), "PATIENT"))
            System.out.print("Enter Your Matricule >>  ");
        else System.out.print("Enter Your Email >>  ");
            this.credentials = in.next();
            System.out.print("Enter Your Password >>  ");
            this.password = in.next();
    }
}
