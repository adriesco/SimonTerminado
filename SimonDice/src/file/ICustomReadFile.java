package file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import main.Jugador;

public interface ICustomReadFile {

	/**
	 * Metodo para cerrar el fichero de lectura
	 * 
	 * @throws IOException
	 */
	public void CloseReadFile() throws IOException;

	/**
	 * Crear array tipo jugador
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */
	ArrayList<Jugador> jugadores() throws FileNotFoundException;
}