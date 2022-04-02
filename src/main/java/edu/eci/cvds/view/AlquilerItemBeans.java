package edu.eci.cvds.view;



import com.google.inject.Inject;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import java.sql.Date;
import java.util.List;

@ManagedBean(name = "AlquilerItem")
@ApplicationScoped
public class AlquilerItemBeans extends BasePageBean {
    @Inject
    private ServiciosAlquiler serviciosAlquiler;
    private Cliente selectedCliente;
    private long costo;

    public void registrarCliente(String nombre,long doc,String telefono, String direccion,String email) throws ExcepcionServiciosAlquiler {
        try{
            serviciosAlquiler.registrarCliente(new Cliente(nombre,doc,telefono,direccion,email));
        } catch (ExcepcionServiciosAlquiler ex) {
            throw new ExcepcionServiciosAlquiler("Error al registrar el Cliente");
        }
    }

    public Cliente getselectedCliente() {
        return selectedCliente;
    }

    public void setselectedCliente(Cliente selectedCliente) {
        this.selectedCliente = selectedCliente;
    }

    public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
        try{
            return serviciosAlquiler.consultarClientes();
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            throw new ExcepcionServiciosAlquiler("Error al consultar clientes");
        }
    }

    public List<ItemRentado> consultarItemsCliente(long idcliente) throws ExcepcionServiciosAlquiler {
        try {
            return serviciosAlquiler.consultarItemsCliente(idcliente);
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            throw new ExcepcionServiciosAlquiler("Error al consultar items del clientes");
        }
    }

    public void registrarAlquiler(int idItem , int numdias) throws ExcepcionServiciosAlquiler {
        Item item = serviciosAlquiler.consultarItem(idItem);
        serviciosAlquiler.registrarAlquilerCliente(new Date(System.currentTimeMillis()),selectedCliente.getDocumento(),item,numdias);
    }

    public long consultarMulta(int iditem) throws ExcepcionServiciosAlquiler {
        try {
            return serviciosAlquiler.consultarMultaAlquiler(iditem, new Date(System.currentTimeMillis()));
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            throw new ExcepcionServiciosAlquiler("Error al consultar multa alquiler");
        }
    }

    public void consultarCosto(int iditem , int numdias) throws ExcepcionServiciosAlquiler {
        try {
            this.costo = serviciosAlquiler.consultarCostoAlquiler(iditem, numdias);
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            throw new ExcepcionServiciosAlquiler("Error al consultar costo alquiler");
        }
    }
    public long getCosto(){
        return costo;
    }
}
