package co.edu.ufps.easyTaxi.controlador;

import java.text.MessageFormat;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import co.edu.ufps.easyTaxi.model.dao.ClienteDao;
import co.edu.ufps.easyTaxi.model.dto.Cliente;


@ManagedBean
@RequestScoped
public class ClienteControl {

	private List<Cliente> clientes;
	private Cliente cliente;
	
	@EJB
	private ClienteDao clienteDao;

	
	public ClienteControl() {
		
		super();
		cliente = new Cliente();
	}

	public List<Cliente> getClientes() {
	
		return clientes;
	}
	public void mostrar(){
		this.clientes = clienteDao.getClientes();
	}

	public void setClientes(List<Cliente> clientes) {	
		this.clientes = clienteDao.getClientes();
		this.clientes = clientes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String guardarCliente(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			
			clienteDao.crearCliente(cliente);
			
			context.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "El cliente ha sido registrado", null));
			cliente = new Cliente();
		} catch (Exception e) {
			context.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar el cliente", ""));
		}
		
		return "index.xhtml";
	}
	
}
