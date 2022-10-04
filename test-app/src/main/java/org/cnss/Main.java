package org.cnss;
import org.cnss.entities.*;
import org.cnss.helpers.Database;
import org.cnss.helpers.Sessions;

import java.sql.ResultSet;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Database cn = new Database();
        Scanner in = new Scanner(System.in);

        Sessions s = new Sessions();
        s.menuSession();
        /*List<String> ns = Arrays.asList("Adam","Omar","Aymen","Sofia");
        List<String> square = ns.stream().map(el -> "Hello\t"+el).collect(Collectors.toList());
        System.out.println("enter the name >> ");
        String name = in.nextLine();
        System.out.println("enter the address >> ");
        String address = in.next();
        System.out.println("enter the age >> ");
        int age = in.nextInt();

        // create table in db
        String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) VALUES (1, '"+ name +"', "+ age +", '"+ address +"', 20000.00 );";
        if(cn.execute(sql)){
            System.out.println("add success");
        }*/
        /*String sql = "SELECT * FROM patients";
        ResultSet res = cn.resultSet(sql);

        try {
            while (res.next()){
                System.out.println(res.getString("email"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }*/
//        }

    }


}