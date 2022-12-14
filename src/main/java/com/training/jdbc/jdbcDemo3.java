package com.training.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class jdbcDemo3 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Scanner sc =new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/infinite";
        String user="root";
        String password = "india@123";
        Connection con=null;

        try{

            con = DriverManager.getConnection(url,user,password);

            System.out.println("Enter details for inserting .....");
            System.out.println("Enter product id :");
            int id1=sc.nextInt();
            System.out.println("Enter product name :");
            String name = sc.next();
            System.out.println("Enter product category :");
            String cat = sc.next();
            System.out.println("Enter product price :");
            double price = sc.nextDouble();

            // for inserting a value
            PreparedStatement stmt = con.prepareStatement("insert into product(prod_id,prod_name,prod_desc,price)"+ "values(?,?,?,?)");
            stmt.setInt(1,id1);
            stmt.setString(2,name);
            stmt.setString(3,cat);
            stmt.setDouble(4,price);

            stmt.executeUpdate();
            System.out.println("Data Added successfully.....");



        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            try{
                con.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}


