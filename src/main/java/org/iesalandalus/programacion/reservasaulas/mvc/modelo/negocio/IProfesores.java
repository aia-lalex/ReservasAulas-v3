package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public interface IProfesores {

	List<Profesor> getProfesores();
	
	public int getNumProfesores();
	
	void insertar(Profesor profesor) throws OperationNotSupportedException;
	
	Profesor buscar(Profesor profesor);
	
	void borrar(Profesor profesor) throws OperationNotSupportedException;
	
	List<String> representar();

	void comenzar();

	void terminar();
	
}
