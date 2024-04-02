
package main;

import java.util.Scanner;
import java.io.IOException;
import java.util.Random;

public class Engine {
	Scanner scanner = new Scanner(System.in);
	private int ayudas;
	private int puntuacion = 0;

	public enum tColores {
		Rojo, Azul, Verde, Dorado, Blanco, Marron, Naranja
	}

	public enum tModo {
		Facil, Dificil
	}

	public int MAX_COLORES_FACIL = 3; // Modo facil que en la primera secuencia solo imprime 3 colores
	public int MAX_COLORES_DIFICIL = 7; // Modo dificil que en la primera secuencia solo imprime 4 colores

	int MAX_COLORES_SEQ = 15; // array con los colores y el numero de colores que se van a imprimir
	tColores[] secuenciaColores = new tColores[MAX_COLORES_SEQ];

	/**
	 * Recibe un char y te devuelve un color
	 * 
	 * @param _color
	 * @return
	 */
	public tColores charToColor(char _color) {
		char letraColor = Character.toLowerCase(_color);

		switch (letraColor) {
		case 'r':
			return tColores.Rojo;
		case 'v':
			return tColores.Verde;
		case 'a':
			return tColores.Azul;
		case 'd':
			return tColores.Dorado;
		case 'b':
			return tColores.Blanco;
		case 'm':
			return tColores.Marron;
		case 'n':
			return tColores.Naranja;
		default:

		}
		return null;
	}

	/**
	 * Recibe un numero entero y te devuelve un color
	 * 
	 * @param _numero
	 * @return
	 */
	public tColores intToColor(int _numero) {

		switch (_numero) {

		case 0:
			return tColores.Rojo;

		case 1:
			return tColores.Azul;

		case 2:
			return tColores.Verde;

		case 3:
			return tColores.Dorado;

		case 4:
			return tColores.Blanco;

		case 5:
			return tColores.Marron;

		case 6:
			return tColores.Naranja;

		}
		return null;
	}

	/**
	 * metodo para generar un array con numeros aleatorios
	 * 
	 * @param _numero
	 */
	public void generarSecuencia(int _numero) {
		for (int i = 0; i < secuenciaColores.length; i++) {
			secuenciaColores[i] = intToColor((int) (Math.random() * _numero));
		}
	}

	/**
	 * Un metodo con un if que si el color y su numero coinciden da false por lo que
	 * el usuario no ha fallado , de la otra forma si da un true el ususario si ha
	 * fallado
	 * 
	 * @param _index
	 * @param _color
	 * @return
	 */
	public boolean comprobarColor(int _index, tColores _color) {
		return (secuenciaColores[_index] == _color);

	}

	/**
	 * Te muestra la secuencia aleatoria de los 3 primeros colores para que se
	 * memorizen.
	 * 
	 * @param _numero
	 */
	public void mostrarSecuencia(int _numero) {
		for (int i = 0; i < _numero; i++) {
			System.out.print(secuenciaColores[i] + " ");
		}
	}

	/**
	 * Metodo para que te muestre el menu
	 */
	public void menu() {
		Scanner scanner = new Scanner(System.in);
		System.out.println(
				"0- Salir \n1- Jugar en modo fácil \n2- Jugar en modo difícil \n3- Ver 10 mejores jugadores \n4- Ver jugador(es) con la puntuacion mas alta \n5- Encontrar a un jugador");
	}

	/**
	 * Metodo para proporcionar ayudas al usuario
	 * 
	 * @param _index
	 * @return
	 */
	public boolean usarAyuda(int _index) {
		if (ayudas > 0) {
			ayudas--;
			System.out.println("Tu color olvidado es " + secuenciaColores[_index] + " y te quedan " + this.ayudas
					+ " oportunidades");
			return true;
		} else {
			System.out.println("No te quedan mas ayudas");
			return false;
		}
	}

	/**
	 * Metodo que inicia el juego
	 * 
	 * @throws IOException
	 */
	public void start() throws IOException {
		Record record = new Record();
		Scanner scanner = new Scanner(System.in);
		System.out.println("¡Bienvenido a Simon dice!");
		System.out.print("¿Cómo te llamas? ");

		String nombre = scanner.nextLine();
		Jugador jugador = new Jugador(nombre, 0);
		record.introducirJugador(jugador);
		record.cargarRanking();
		System.out.println("Hola " + nombre + ", presiona ENTER ");
		scanner.nextLine();

		int seleccion = 0;

		while (seleccion != 6) {
			menu();
			seleccion = scanner.nextInt();
			switch (seleccion) {
			case 1:
				play(tModo.Facil);
				record.escribirRanking();
				jugador.setPuntuacion(puntuacion);
				break;
			case 2:
				play(tModo.Dificil);
				record.escribirRanking();
				jugador.setPuntuacion(puntuacion);
				break;
			case 3:
				record.ordenarRanking();
				System.out.println("Mostrando ranking...");
				record.showRanking(); // te enseña los 10 mejores o los que haya.
				System.out.println("Ranking mostrado.");
				break;
			case 4:
				record.ordenarRanking();
				System.out.println("Mostrando ranking...");
				record.showBestPlayer(); // te enseña los 10 mejores o los que haya.
				System.out.println("Ranking mostrado.");
				break;
			case 5:
				System.out.println("Ingrese el nombre del jugador a buscar: ");
				String nombreBusca = scanner.next();
				Jugador jugadorEncontrado = record.getJugadorByName(nombreBusca);
				if (jugadorEncontrado != null) {
					System.out.println("Jugador encontrado:");
					System.out.println("Nombre: " + jugadorEncontrado.getNombre());
					System.out.println("Puntuación: " + jugadorEncontrado.getPuntuacion());
				} else {
					System.out.println("El jugador no fue encontrado.");
					menu();
				}
				break;
			default:
				record.escribirRanking();
				System.out.println("Saliendo...");
				System.exit(0);
			}
		}
	}

	/**
	 * Metodo que empieza el juego
	 */
	public void play(tModo modo) throws IOException {

		int numSecuencia;
		int coloresSecuencia;
		boolean error = true;
		puntuacion = 0;
		numSecuencia = 1;
		coloresSecuencia = 3;
		ayudas = 3;

		if (modo == tModo.Facil) {
			generarSecuencia(MAX_COLORES_FACIL);
		} else {
			generarSecuencia(MAX_COLORES_DIFICIL);
		}
		scanner.nextLine();
		while (coloresSecuencia <= MAX_COLORES_SEQ && error) {
			System.out.println("Secuencia" + numSecuencia + ":");
			mostrarSecuencia(coloresSecuencia);
			System.out.println("\n" + "Memoriza la secuencia y pulsa ENTER para continuar... (Enter)");
			scanner.nextLine();
			for (int i = 0; i <= 5; i++) {
				System.out.println();
			}
			int j = 0;
			while (j < coloresSecuencia && error) {
				char caracter = scanner.next().charAt(0);
				scanner.nextLine();
				if ((caracter == 'x' || caracter == 'X') && ayudas >= 1) {
					usarAyuda(j);
					puntuacion -= 8;
					j++;
				} else if ((caracter == 'x' || caracter == 'X') && ayudas < 1) {
					usarAyuda(j);
				} else if (comprobarColor(j, charToColor(caracter))) {
					System.out.println("Correcto!");
					j++;
					puntuacion += 2;
				} else {
					System.out.println("Ha fallado.");
					error = false;
				}
			}
			if (error && coloresSecuencia < MAX_COLORES_SEQ) {
				numSecuencia++;
				coloresSecuencia++;
				puntuacion += 8;
			} else if (error && coloresSecuencia == MAX_COLORES_SEQ) {
				System.out.println("Has ganado");
				puntuacion += 40;
				error = true;
			}
		}

	}
}
