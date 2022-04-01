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
package edu.eci.cvds.samples.services.client;



import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemRentadoMapper;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.TipoItemMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author hcadavid
 */
public class    MyBatisExample {

    /**
     * Método que construye una fábrica de sesiones de MyBatis a partir del
     * archivo de configuración ubicado en src/main/resources
     *
     * @return instancia de SQLSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return sqlSessionFactory;
    }

    /**
     * Programa principal de ejempo de uso de MyBATIS
     * @param args
     * @throws SQLException 
     */
    public static void main(String args[]) throws SQLException, ParseException {
        SqlSessionFactory sessionfact = getSqlSessionFactory();

        SqlSession sqlss = sessionfact.openSession();

        //Crear el mapper y usarlo: 
        ClienteMapper cm=sqlss.getMapper(ClienteMapper.class);
        ItemRentadoMapper ir=sqlss.getMapper(ItemRentadoMapper.class);
        ItemMapper im=sqlss.getMapper(ItemMapper.class);
        TipoItemMapper tm=sqlss.getMapper(TipoItemMapper.class);

        //System.out.println(cm.consultarClientes());
        System.out.println("Consulta por cliente:");
        //System.out.println(cm.consultarCliente(101430));
        //System.out.println("Consulta todos los items:");
        //System.out.println(im.consultarItems());
        //System.out.println(im.consultarItem(1));
        //cm...
        System.out.println("----------------------------");
        cm.agregarItemRentadoACliente(6,2 ,
                new SimpleDateFormat("yyyy/MM/dd").parse("2022/09/28"),
                new SimpleDateFormat("yyyy/MM/dd").parse("2022/10/28"));
        System.out.println(cm.consultarCliente(6));
        System.out.println("Consultar item rentado por id");
        System.out.println(ir.consultarItemRentado(2132577));
        System.out.println(" ");
        System.out.println("Consultar tipo items");
        System.out.println(tm.getTiposItems());
        System.out.println(" ");
        System.out.println("Consultar tipo item por id");
        System.out.println(tm.getTipoItem(2));
        System.out.println(" ");
        System.out.println("Consultar clientes");
        System.out.println(cm.consultarClientes());
        System.out.println(" ");
        System.out.println("Consultar clientes por id");
        System.out.println(cm.consultarCliente(101430));
        System.out.println(" ");
        System.out.println("Consultar items");
        System.out.println(im.consultarItems());
        System.out.println(" ");
        System.out.println("Consultar items por id ");
        System.out.println(im.consultarItem(1));
        System.out.println(" ");

        sqlss.commit();

        sqlss.close();

    }


}
