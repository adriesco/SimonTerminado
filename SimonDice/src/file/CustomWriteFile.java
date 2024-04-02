package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import main.Jugador;

public class CustomWriteFile extends FileWriter implements ICustomWriteFile {

	/**
	 * Constructora
	 * 
	 * @param file
	 * @throws IOException
	 */
	public CustomWriteFile(String file) throws IOException {
		super(file);

	}

	@Override
	public void CloseWriteFile() throws IOException {
		// TODO Auto-generated method stub
		this.close();
	}

	@Override
	public void WritePlayers(String texto) throws IOException {
		// TODO Auto-generated method stub
		this.write(texto);
	}

}
