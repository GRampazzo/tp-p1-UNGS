package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Iterator;
import java.util.Random;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
import utils.Tiempo;
import utils.Lista;
import utils.Nodo;

public class Juego extends InterfaceJuego {

	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Mikasa mikasa;
	private Suero suero;
	private Tiempo tiempo;
	private Image imageFondo;
	private Image mikasaImagen;
	private Titan titan;
	private Obstaculos obstaculos;
	private Disparo [] disparos;

	public Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Sakura Ikebana Delivery - Grupo N - Apellido1 - Apellido2 -Apellido3 - V0.01",
				800, 600);
		this.mikasa = new Mikasa(400, 300, 40,0);
		this.suero = new Suero(0, 0, 0);
		this.tiempo = new Tiempo(true, 0);
		this.titan = new Titan(50,50,80,0);
		this.disparos = new Disparo[300];
		this.imageFondo = Herramientas.cargarImagen("FondoSeis.jpg");
		this.mikasaImagen= Herramientas.cargarImagen("Mikasa.png");
		this.obstaculos = new Obstaculos(6);
		this.obstaculos.generarObs();
		

		// Inicializar lo que haga falta para el juego
		// ...

		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el m�todo tick() ser� ejecutado en cada instante y por lo
	 * tanto es el m�todo m�s importante de esta clase. Aqu� se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */
	public void tick() {
		// Procesamiento de un instante de tiempo
		// ...

		if (tiempo.isInicia()) {

//			instrucciones
			if (entorno.estaPresionada('w'))
				mikasa.moverAdelante();

			if (entorno.estaPresionada('a'))
				mikasa.moverIzquierda();

			if (entorno.estaPresionada('s'))
				mikasa.moverAtras();

			if (entorno.estaPresionada('d'))
				mikasa.moverDerecha();
			
			for(Disparo d: disparos) {
				if (d!=null) {
					d.setX(mikasa.getX());
					d.setY(mikasa.getY());
					d.setAngulo(mikasa.getAngulo());
				}
			}
			
			if (this.entorno.estaPresionada(this.entorno.TECLA_ESPACIO) && this.mikasa.getUltimoDisparo() + 1 <= this.tiempo.getContar()){
				this.mikasa.disparar((int)this.tiempo.getContar());
			}
			
			
			if (suero.colision(mikasa.getX(), mikasa.getY(), suero.getX(), suero.getY(), suero.getRadio())) {
				suero.setX(0);
				suero.setY(0);
				suero.setRadio(0);
			}

			if (tiempo.getContar() == 0.015 || (int) tiempo.getContar() == 19) {
				suero.setX(Math.random() * 800);
				suero.setY(Math.random() * 600);
				suero.setRadio(30);
			}
			
			for (Obstaculos2 obs : this.obstaculos.getObstaculos2()){
				if(obstaculos.colisiona(obs.getRadio(), suero.getRadio(), obstaculos.getDistancia())){
					suero.setX(Math.random() * 800);
					suero.setY(Math.random() * 600);
					suero.setRadio(30);		
				}
			}
			
			
//			if (titan.colisiona(mikasa.getRadio(), titan.getRadio(), mikasa.getDistancia())
//					&& mikasa.getDistancia() <= 68) {
//				if (mikasa.getX() <= titan.getX() && mikasa.getY() <= titan.getY()) {
//					mikasa.setX(mikasa.getX() - 2);
//					mikasa.setY(mikasa.getY() - 2);
//				} else if (mikasa.getX() >= titan.getX() && mikasa.getY() >= titan.getY()) {
//					mikasa.setX(mikasa.getX() + 2);
//					mikasa.setY(mikasa.getY() + 2);
//				} else if (mikasa.getX() >= titan.getX() && mikasa.getY() <= titan.getY()) {
//					mikasa.setX(mikasa.getX() + 2);
//					mikasa.setY(mikasa.getY() - 2);
//				} else if (mikasa.getX() <= titan.getX() && mikasa.getY() >= titan.getY()) {
//					mikasa.setX(mikasa.getX() - 2);
//					mikasa.setY(mikasa.getY() + 2);
//				}
//			}

//			dibujar
			entorno.dibujarImagen(this.imageFondo, 400, 400, 0, 1.7);
			entorno.dibujarImagen(mikasaImagen, mikasa.getX(),mikasa.getY(), mikasa.getAngulo(),0.65);
			titan.dibujar(entorno);
			titan.moverTitan();
			titan.direccionTitan(mikasa.getX(), mikasa.getY());
			procesarDisparos();
			
			
			
//			por cada obstaculo chequea la distancia entre el titan y el obs, si el metodo colisiona es true,
//			y la distancia entre los circulos es menor a 100, resta o suma en sus respectivos X e Y. del titan
			for (Obstaculos2 obs : this.obstaculos.getObstaculos2()){
				obstaculos.distancia(obs.getX(), obs.getY(), titan.getX(), titan.getY());
				if(obstaculos.colisiona(obs.getRadio(), titan.getRadio(), obstaculos.getDistancia())
				&& obstaculos.getDistancia() <= 70) {
					if (titan.getX() <= obs.getX() && titan.getY() <= obs.getY()) {
						titan.setX(titan.getX() - 2);
						titan.setY(titan.getY() - 2);
					} else if (titan.getX() >= obs.getX() && titan.getY() >= obs.getY()) {
						titan.setX(titan.getX() + 2);
						titan.setY(titan.getY() + 2);
					} else if (titan.getX() >= obs.getX() && titan.getY() <= obs.getY()) {
						titan.setX(titan.getX() + 2);
						titan.setY(titan.getY() - 2);
					} else if (titan.getX() <= obs.getX() && titan.getY() >= obs.getY()) {
						titan.setX(titan.getX() - 2);
						titan.setY(titan.getY() + 2);
					}
				}
				obs.dibujar(entorno);
			}
			
//			lo mismo pero con mikasa
			for (Obstaculos2 obs : this.obstaculos.getObstaculos2()){
				obstaculos.distancia(obs.getX(), obs.getY(), mikasa.getX(), mikasa.getY());
				if(obstaculos.colisiona(obs.getRadio(), mikasa.getRadio(), obstaculos.getDistancia())
				&& obstaculos.getDistancia() <= 50) {
					if (mikasa.getX() <= obs.getX() && mikasa.getY() <= obs.getY()) {
						mikasa.setX(mikasa.getX() - 2);
						mikasa.setY(mikasa.getY() - 2);
					} else if (mikasa.getX() >= obs.getX() && mikasa.getY() >= obs.getY()) {
						mikasa.setX(mikasa.getX() + 2);
						mikasa.setY(mikasa.getY() + 2);
					} else if (mikasa.getX() >= obs.getX() && mikasa.getY() <= obs.getY()) {
						mikasa.setX(mikasa.getX() + 2);
						mikasa.setY(mikasa.getY() - 2);
					} else if (mikasa.getX() <= obs.getX() && mikasa.getY() >= obs.getY()) {
						mikasa.setX(mikasa.getX() - 2);
						mikasa.setY(mikasa.getY() + 2);
					}
				}
				obs.dibujar(entorno);
			}


			if (((int) tiempo.getContar() > 10 && (int) tiempo.getContar() < 18)
					|| ((int) tiempo.getContar() > 20 && (int) tiempo.getContar() < 28)) {
				suero.dibujar(entorno);
			}

//			controla el fin del juego
//			if ((int) tiempo.getContar() > 40) {
//				this.tiempo = new Tiempo(false, 0);
//			}

			tiempo.setContar(0.015);

//			imprime el tiempo
//			System.out.println(tiempo.getContar());
			//genera obstaculos

		} else {
			entorno.cambiarFont("Arial", 32, Color.WHITE);
			entorno.escribirTexto("GAME OVER", 300, 300);
		}
	}
	
	private void moverDisparo(Nodo<Disparo> nodoDisparo) {
		nodoDisparo.getElemento().mover();
		nodoDisparo.getElemento().dibujar(this.entorno);
		if (!nodoDisparo.getElemento().estaEnPantalla()) {
			this.mikasa.getDisparos().quitarPorId(nodoDisparo.getId());
		}
	}
	
	private void procesarDisparos() {
		this.mikasa.getDisparos().forEachNodo(disparo -> {
			this.moverDisparo(disparo);
			return null;
		});
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}