package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/persona";
        String user ="root";
    String password ="123456789";


        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            Statement stat =conn.createStatement();
            ResultSet resultado = stat.executeQuery("SELECT * FROM persona");
            while (resultado.next()){
                System.out.println(resultado.getString("nombre"));
                System.out.println(" ");
                System.out.println(resultado.getString("apellido"));
            }
            resultado.close();

            String insertQuery = "INSERT INTO persona(nombre,apellido) VALUE ('camilo','Fresita')";
            int rowInsted = stat.executeUpdate(insertQuery);
            if (rowInsted > 0){
                System.out.println("inserción exitosa");
            }

            String UpdateQuery = "UPDATE persona SET nombre ='Elias' WHERE idpersona = 2";
            int rowupdate = stat.executeUpdate(UpdateQuery);
            if (rowupdate > 0){
                System.out.println("Actualización exitosa");
            }

            String deleteQuery = "DELETE FROM persona WHERE idpersona = 3";
            int rowdelete = stat.executeUpdate(deleteQuery);
            if (rowdelete > 0){
                System.out.println("Eliminación exitosa");
            }

            stat.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}