package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

public class Aula {
private String nombre;

public Aula(String nombre) {
	setNombre(nombre);
}

public Aula(Aula aula) {
	if (aula == null) {
	throw new NullPointerException("El aula no puede ser nula");
	}
	setNombre(aula.getNombre());
	}

public String getNombre() {
	return nombre;
}

private void setNombre(String nombre) {
	this.nombre = nombre;
}

}

