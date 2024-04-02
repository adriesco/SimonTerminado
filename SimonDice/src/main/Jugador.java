package main;

public class Jugador {
	/**
	 * Atributos.
	 */
	private int puntuacion;
	private String nombre;

	/**
	 * Constructora para establecer el nombre y la puntutacion
	 * 
	 * @param _nombre
	 */
	public Jugador(String _nombre , int _puntuacion) {
		this.nombre = _nombre;
		this.puntuacion = _puntuacion;
	}

	/**
	 * Metodo que nos da el nombre del jugador.
	 * 
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo que establece la puntuacion.
	 * 
	 * @param _nombre
	 */
	public void setNombre(String _nombre) {
		this.nombre = _nombre;
	}

	/**
	 * Metodo que establece la puntuacion
	 * 
	 * @param _puntos
	 */
	public void setPuntuacion(int _puntos) {
		this.puntuacion = _puntos;
	}

	/**
	 * Metodo que nos devuelve la puntuacion del jugador.
	 * 
	 * @return
	 */
	public int getPuntuacion() {
		return this.puntuacion;
	}
}
