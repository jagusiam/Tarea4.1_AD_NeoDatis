package modelo.dao.departamento;

import modelo.Departamento;
import modelo.dao.IGenericDao;

public interface IDepartamentoDao extends IGenericDao<Departamento> {

	//Debe contar con el método public boolean exists(Integer dept); 
	//Devolverá true o false en función de si ya existe un departamento con el mismo deptno
	
	public boolean exists(Integer dept); 
	
}
