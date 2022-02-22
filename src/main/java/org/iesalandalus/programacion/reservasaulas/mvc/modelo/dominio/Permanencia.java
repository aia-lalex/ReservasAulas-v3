package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class Permanencia {

	private LocalDate dia;
	static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public Permanencia(LocalDate dia) {
		setDia(dia);
	

	}

	public Permanencia(Permanencia permanencia) {
		if (permanencia == null) {
			throw  new NullPointerException("ERROR: No se puede copiar una permanencia nula.");
		}

		setTramo(permanencia.getTramo());
		setDia(permanencia.getDia());

	}

	public LocalDate getDia() {
		return dia;
	}

	private void setDia(LocalDate dia) {
		if (dia == null) {
			throw new NullPointerException("ERROR: El día de una permanencia no puede ser nulo.");
		}
		this.dia = dia;
	}


	public abstract int getPuntos();
	
	@Override
	public int hashCode() {
		return Objects.hash(dia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Permanencia other = (Permanencia) obj;
		return Objects.equals(dia, other.dia);
	}

	
	@Override
	public String toString() {
		return "dia=" + dia.format(FORMATO_DIA);

	}


}


