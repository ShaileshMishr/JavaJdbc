package com.training.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class jdbcDemo4 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Scanner sc =new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/infinite";
        String user="root";
        String password = "india@123";
        Connection con=null;

        try{

            con = DriverManager.getConnection(url,user,password);
            System.out.println("Enter product id you want to search:");
            int id=sc.nextInt();

            String sql = "{call get_products(?) }";
           // String sql = "{call get_price(?, ?) }";

//            PreparedStatement stmt = con.prepareStatement("select * from product where prod_id=?");
//            stmt.setInt(1,id);

            CallableStatement stmt =  con.prepareCall(sql);
            stmt.setDouble(1,100);

            //Statement stmt = con.createStatement();  //Statement is used for static query
            // ResultSet rs = stmt.executeQuery("select * from product where prod_id=?");  // prepared statement is used for dynamic query



            ResultSet rs = stmt.executeQuery();

            List<Product> productList = new ArrayList<Product>();
            while (rs.next()) {
                Product product = new Product();
                product.setProdId(rs.getInt(1));
                product.setProdName(rs.getString(2));
                product.setProdDesc(rs.getString(3));
                product.setPrice(rs.getDouble(4));
                productList.add(product);

            }

//            for(Product pr: productList) {
//                System.out.println(pr.getProdDesc()+"::"+pr.getPrice());
//            }
            for(Product pr: productList) {
                System.out.println(pr.getProdId()+"::"+pr.getProdDesc()+"::"+pr.getProdName()+"::"+pr.getPrice());
            }
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
