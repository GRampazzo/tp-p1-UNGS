package juego;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {

	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Mikasa mikasa;
	
	public Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Sakura Ikebana Delivery - Grupo 12 - Huayta - Apellido2 -Apellido3 - V0.01", 800, 600);
		this.mikasa = new Mikasa(100, 200, 20, 50);
		// Inicializar lo que haga falta para el juego
		// ...

		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick() {
		// Procesamiento de un instante de tiempo
		// ...
		if (entorno.estaPresionada('w'))
			mikasa.moverArriba();
		
		if (entorno.estaPresionada('s')) 
			mikasa.moverAbajo();
		
		if (entorno.estaPresionada('a'))
			mikasa.moverIzquierda();
		
		if (entorno.estaPresionada('d'))
			mikasa.moverDerecha();
		
		mikasa.dibujar(entorno);
		
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
