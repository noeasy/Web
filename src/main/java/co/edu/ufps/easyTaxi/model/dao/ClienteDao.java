package co.edu.ufps.easyTaxi.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.ufps.easyTaxi.model.dto.Cliente;

@Stateless
public class ClienteDao {
	
	@PersistenceContext(unitName="primary")
    private EntityManager em;
	
	public List<Cliente> getClientes(){
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		String consulta = "SELECT c FROM Cliente c " +
				  " ORDER BY c.apellido ";

		Query query = em.createQuery(consulta);
		
		clientes = query.getResultList();
		
		return clientes;
	}
	
	public void crearCliente(Cliente c){
		em.persist(c);
	}
	
	public void editarCliente(Cliente c){
		em.merge(c);
	}
	
	public void eliminar(Cliente c){
		em.remove(c);
	}
}
