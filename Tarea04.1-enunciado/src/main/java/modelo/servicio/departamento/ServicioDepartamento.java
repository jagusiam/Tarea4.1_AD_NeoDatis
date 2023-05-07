package modelo.servicio.departamento;

import java.util.List;

import modelo.Departamento;
import modelo.dao.departamento.DepartamentoNeoDatisDao;
import modelo.dao.departamento.IDepartamentoDao;
import modelo.exceptions.DuplicateInstanceException;

public class ServicioDepartamento implements IServicioDepartamento {
	
	//Clase ServicioDepartamento.java con la implementación 
	//de la interfaz del punto anterior.
	
	//variable de tipo IDepartamentoDao
	//declaracion con la interfaz
	private IDepartamentoDao departamentoDao;
	
	//Constructor que instancia departamentoDao
	public ServicioDepartamento() {
		//instanciacion con la clase especifica
		departamentoDao = new DepartamentoNeoDatisDao();
	}
	

	//Implementacion de métodos
	@Override
	public long create(Departamento dept) throws DuplicateInstanceException {
		return departamentoDao.create(dept);
	}


	@Override
	public boolean delete(Departamento dept) {
		return departamentoDao.delete(dept);				
	}

	@Override
	public boolean update(Departamento dept) {
		return departamentoDao.update(dept);
	}

	@Override
	public List<Departamento> findAll() {
		return departamentoDao.findAll();
	}

	@Override
	public boolean exists(Integer deptno) {
		return departamentoDao.exists(deptno);

	}
	

}
