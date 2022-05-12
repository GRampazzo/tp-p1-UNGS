package juego;

import java.util.Random;
import java.awt.Color;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {

	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Mikasa mikasa;
	private Tiempo tiempo;
	private Suero suero;
	private int cantObs;
	private Obstaculos [] obs;
	
	public Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Sakura Ikebana Delivery - Grupo 12 - Huayta - Rampazzo -Apellido3 - V0.01", 800, 600);
		this.mikasa = new Mikasa(400, 300, 40, 40,0);
		this.tiempo = new Tiempo(true, 0);
		this.suero = new Suero(0, 0, 0);
		this.cantObs=5;
		this.iniciarObs();
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
		if (tiempo.isInicia()) {
			if (entorno.estaPresionada('w'))
				mikasa.moverAdelante();
		
			if (entorno.estaPresionada('a'))
				mikasa.moverIzquierda();
		
			if (entorno.estaPresionada('d'))
				mikasa.moverDerecha();
			
			if (suero.colision(mikasa.getX(), mikasa.getY(), suero.getX(), suero.getY(), suero.getRadio())) {
				suero.setX(0);
				suero.setY(0);
				suero.setRadio(0);
			}
			
			//apariciones del suero			
			if (tiempo.getContar() == 0.015 || (int) tiempo.getContar() == 19) {
				suero.setX(Math.random() * 800);
				suero.setY(Math.random() * 600);
				suero.setRadio(30);
			}
			
			//dibujar
			mikasa.dibujar(entorno);
			
			if (((int) tiempo.getContar() > 10 && (int) tiempo.getContar() < 18)
			 || ((int) tiempo.getContar() > 20 && (int) tiempo.getContar() < 28)) {
				suero.dibujar(entorno);
			}
		
			for (Obstaculos o: this.obs) {
				o.dibujar(entorno);
			}
			
			//cronometro
			tiempo.setContar(0.015);
			
			//si queres controlar el tiempo en el eclipse:
			//System.out.println(tiempo.getContar());
			
			//marca fin del juego
			if ((int) tiempo.getContar() > 40) {
				this.tiempo = new Tiempo(false, 0);
			}
		} else {
			entorno.cambiarFont("Arial", 32, Color.WHITE);
			entorno.escribirTexto("GAME OVER", 300, 300);
		}
		
	}
	private void iniciarObs() {
		Obstaculos[] lista = new Obstaculos[this.cantObs];
		Random r=new Random();
		int xPiso = 370;
		int yPiso = 500;
		int ancho = 80;
		int alto = 80;
		for (int i = 0; i < this.cantObs; i++) {
			Obstaculos p = new Obstaculos (xPiso, yPiso, ancho, alto);
			lista[i] = p;
			yPiso = yPiso - 100;
			xPiso = r.nextInt(600);
			if (xPiso>=350 && xPiso<450) {
				xPiso=r.nextInt(600);
			}
		}
		this.obs = lista;
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
