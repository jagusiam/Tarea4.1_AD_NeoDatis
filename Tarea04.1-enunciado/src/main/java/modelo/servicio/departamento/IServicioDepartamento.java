package modelo.servicio.departamento;

import java.util.List;

import modelo.Departamento;
import modelo.exceptions.DuplicateInstanceException;

public interface IServicioDepartamento {
	
	//Crea Interfaz IServicioDepartamento.java con los métodos: 
	//CRUD, findAll(), exists()
	
		//El método create deberá comprobar antes de crear si ya existe un departamento con el mismo deptno. Si existe, lanza la excepción DuplicateInstanceException. 
		//En caso contrario intenta crear el departamento.
		public long create(Departamento dept)throws DuplicateInstanceException; 
	
		public boolean delete(Departamento dept);
		
		public boolean update(Departamento dept);
    
		public List<Departamento> findAll();
    
		public boolean exists(Integer deptno);

}
