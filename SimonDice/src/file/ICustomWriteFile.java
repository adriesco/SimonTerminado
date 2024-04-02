package file;

import java.io.IOException;

public interface ICustomWriteFile {

	/**
	 * Metodo que cierra el fichero de escritura
	 * 
	 * @throws IOException
	 */
	public void CloseWriteFile() throws IOException;

	/**
	 * Metodo pra escribir en fichero
	 * 
	 * @param chain
	 * @throws IOException
	 */
	public void WritePlayers(String chain) throws IOException;
}