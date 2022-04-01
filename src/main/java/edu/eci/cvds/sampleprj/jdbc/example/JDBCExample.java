/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.cvds.sampleprj.jdbc.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hcadavid
 */
public class JDBCExample {

    public static void main(String args[]){
        try {
            String url="jdbc:mysql://desarrollo.is.escuelaing.edu.co:3306/bdprueba";
            String driver="com.mysql.jdbc.Driver";
            String user="bdprueba";
            String pwd="prueba2019";

            Class.forName(driver);
            Connection con=DriverManager.getConnection(url,user,pwd);
            con.setAutoCommit(false);


            System.out.println("Valor total pedido 1:"+valorTotalPedido(con, 1));

            List<String> prodsPedido=nombresProductosPedido(con, 1);


            System.out.println("Productos del pedido 1:");
            System.out.println("-----------------------");
            for (String nomprod:prodsPedido){
                System.out.println(nomprod);
            }
            System.out.println("-----------------------");


            int suCodigoECI=2165814;
            registrarNuevoProducto(con, suCodigoECI, "arepa rik", 3500);
            con.commit();


            con.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(JDBCExample.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    /**
     * Agregar un nuevo producto con los parámetros dados
     * @param con la conexión JDBC
     * @param codigo
     * @param nombre
     * @param precio
     * @throws SQLException
     */
    public static void registrarNuevoProducto(Connection con, int codigo, String nombre,int precio) throws SQLException{
        String consultString =
                "INSERT INTO ORD_PRODUCTOS VALUES(?, ?, ?);";
        try (PreparedStatement consultTable = con.prepareStatement(consultString)) {
            consultTable.setInt(1, codigo);
            consultTable.setString(2, nombre);
            consultTable.setInt(3, precio);
            consultTable.execute();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        con.commit();

    }

    /**
     * Consultar los nombres de los productos asociados a un pedido
     * @param con la conexión JDBC
     * @param codigoPedido el código del pedido
     * @return
     */
    public static List<String> nombresProductosPedido(Connection con, int codigoPedido){
        List<String> np=new LinkedList<>();
        String consultString =
                "SELECT b.nombre FROM ORD_DETALLE_PEDIDO a " +
                        "INNER JOIN ORD_PRODUCTOS b ON b.codigo = a.producto_fk " +
                        "WHERE a.pedido_fk = ?;";
        try (PreparedStatement consultTable = con.prepareStatement(consultString)){
            consultTable.setInt(1, codigoPedido);
            ResultSet res = consultTable.executeQuery();
            while(res.next()) {
                String name = res.getString("nombre");
                np.add(name);
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return np;
    }


    /**
     * Calcular el costo total de un pedido
     * @param con
     * @param codigoPedido código del pedido cuyo total se calculará
     * @return el costo total del pedido (suma de: cantidades*precios)
     */
    public static int valorTotalPedido(Connection con, int codigoPedido){
        String consultString =
                "SELECT SUM(precio*b.cantidad) AS PRECIO FROM  ORD_PRODUCTOS c " +
                        "INNER JOIN ORD_DETALLE_PEDIDO b ON b.producto_fk = c.codigo " +
                        "WHERE b.pedido_fk = ?;";
        int precio = 0;
        try (PreparedStatement consultTable = con.prepareStatement(consultString)){
            consultTable.setInt(1, codigoPedido);
            ResultSet res = consultTable.executeQuery();
            while(res.next()) {
                String name = res.getString("PRECIO");
                precio = Integer.parseInt(name);
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return precio;
    }

    /**
     * SELECT SUM(precio*b.cantidad) AS PRECIO FROM  ORD_PRODUCTOS c
     * INNER JOIN ORD_DETALLE_PEDIDO b ON b.producto_fk = c.codigo
     * WHERE b.pedido_fk = 2;
     *
     *
     * SELECT b.nombre FROM ORD_DETALLE_PEDIDO a
     * INNER JOIN ORD_PRODUCTOS b ON b.codigo = a.producto_fk
     * WHERE a.pedido_fk = 1;
     *
     * INSERT INTO ORD_PRODUCTOS VALUES(2162343,"aRePa RiKa",49900);
     */
}