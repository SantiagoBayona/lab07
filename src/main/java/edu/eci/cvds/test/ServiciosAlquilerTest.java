package edu.eci.cvds.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import static org.junit.Assert.fail;

public class ServiciosAlquilerTest {

    @Inject
    private SqlSession sqlSession;

    ServiciosAlquiler serviciosAlquiler;

    public ServiciosAlquilerTest() {
        serviciosAlquiler = ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
    }

    @Test
    public void DeberiaConsultarElCostoDelAlquiler() throws ExcepcionServiciosAlquiler {
        try{
            Item it = new Item(new TipoItem(40, "item bonito" ),800,
                    "estufa", "bueno", new SimpleDateFormat("yyyy/MM/dd").parse("2020/04/01"),
                    40,"Cualquiera","99");
            serviciosAlquiler.registrarItem(it);
            System.out.println(serviciosAlquiler.consultarCostoAlquiler(800,30));
            Assert.assertEquals(40*30,serviciosAlquiler.consultarCostoAlquiler(800,30));
        } catch (ExcepcionServiciosAlquiler | ParseException excepcionServiciosAlquiler) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void NoDeberiaConsultarTipoItem() throws ExcepcionServiciosAlquiler {
        try{
            serviciosAlquiler.consultarTipoItem(32);
            Assert.assertTrue(false);
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void DeberiaRetornarValorMultaItem() throws ExcepcionServiciosAlquiler {
        try {
            serviciosAlquiler.registrarItem(new Item(new TipoItem(1, "item bonito" ),1,
                    "Televisor", "bueno", new SimpleDateFormat("yyyy/MM/dd").parse("2022/04/01"),
                    99,"Internet","99"));
            serviciosAlquiler.valorMultaRetrasoxDia(1);
            Assert.assertEquals(serviciosAlquiler.consultarItem(1).getTarifaxDia(),99);
        } catch (ExcepcionServiciosAlquiler | ParseException excepcionServiciosAlquiler) {
            fail();
        }
    }

    @Test
    public void DeberiaConsultarCliente() throws ExcepcionServiciosAlquiler {
        try{
            Assert.assertEquals(serviciosAlquiler.consultarCliente(1).getDocumento(),1);
        }catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            Assert.assertTrue(false);
        }
    }
    @Test
    public void DeberiaLanzarExcepcionSiNoExisteElCliente() throws ExcepcionServiciosAlquiler {
        try{
            serviciosAlquiler.consultarCliente(11112);
        }catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            Assert.assertTrue(true);
        }
    }
}