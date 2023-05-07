package modelo.dao.departamento;

import java.util.List;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBRuntimeException;
import org.neodatis.odb.OID;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.oid.OIDFactory;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import modelo.Departamento;
import modelo.dao.AbstractGenericDao;
import modelo.exceptions.InstanceNotFoundException;
import util.ConnectionFactory;
import util.Utils;

public class DepartamentoNeoDatisDao extends AbstractGenericDao<Departamento> implements IDepartamentoDao {

	//Clase DepartamentoNeoDatisDao.java: Implementa con NeoDatis todas las operaciones CRUD, 
	//findAll y el método public boolean exists(Integer dept)
	
	//variable privada de conexion
	private ODB dataConnection;
	
	//constructor
	public DepartamentoNeoDatisDao() {	
		this.dataConnection = ConnectionFactory.getConnection(); 
	}
	
	
	//METODOS CRUD
	//CREATE
	@Override
	public long create(Departamento entity) {
		OID oid = null;
		long oidlong = -1 ;
		try {

			oid = this.dataConnection.store(entity);
			this.dataConnection.commit();
			System.out.println("Creado un objeto "+
			getEntityClass()+ " con oid: " + oid.getObjectId());

		} catch (Exception ex) {

			System.err.println("Ha ocurrido una excepción: " + ex.getMessage());
			this.dataConnection.rollback();
			oid = null;
		}
		if (oid != null) {
			oidlong = oid.getObjectId();
		}
		return oidlong;
	}



	//READ
	@Override
	public Departamento read(long id) throws InstanceNotFoundException {
		Departamento departamento = null;
		try {
			OID oid = OIDFactory.buildObjectOID(id);
			departamento = (Departamento) this.dataConnection.getObjectFromId(oid);
		} catch (ODBRuntimeException ex) {

			System.err.println("Ha ocurrido una excepción: " + ex.getMessage());
			//Suponiendo que no encuentra dept con el id
			throw new InstanceNotFoundException(id, getEntityClass());
		} catch (Exception ex) {

			System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

		}
		return departamento;
	}

	//UPDATE
	@Override
	public boolean update(Departamento entity) {
		boolean exito = false;
		try {
			this.dataConnection.store(entity);
			this.dataConnection.commit();
			exito = true;
		} catch (Exception ex) {
			System.err.println("Ha ocurrido una excepción: " + ex.getMessage());
			this.dataConnection.rollback();

		}
		return exito;
	}

	//DELETE
	@Override
	public boolean delete(Departamento entity) {
		boolean exito = false;
		try {
			OID oid = this.dataConnection.delete(entity);
			System.out.println("El oid del objeto eliminado es: " + oid.getObjectId());
			this.dataConnection.commit();
			exito = true;
		} catch (Exception ex) {
			System.err.println("Ha ocurrido una excepción: " + ex.getMessage());
			this.dataConnection.rollback();

		}
		return exito;
	}

	
	@Override
	public List<Departamento> findAll() {
		CriteriaQuery query = new CriteriaQuery(Departamento.class);
		IQuery iquery = query.orderByAsc("deptno");
		Objects<Departamento> empleados = dataConnection.getObjects(iquery);
		//uso del metodo toList(Objects<E>) del paquete Utils
		return Utils.toList(empleados);
	}

	@Override
	public boolean exists(Integer dept) {
		CriteriaQuery query = new CriteriaQuery(Departamento.class, 
				Where.equal("deptno", dept));
		Objects<Departamento> empleados = dataConnection.getObjects(query);
		return (empleados.size()==1);
	}
	
	

}
