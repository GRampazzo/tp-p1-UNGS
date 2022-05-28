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

@SuppressWarnings("unused")
public class Juego extends InterfaceJuego {

	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Mikasa mikasa;
	private Suero suero;
	private Tiempo tiempo;
	private Image imageFondo;
	private Disparo[] disparos;
	private Obstaculos obstaculos;
	private boolean item;
	private Lista<Titan2> titanes;
	private int tickUltimoTitan;

	public Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Sakura Ikebana Delivery - Grupo N - Apellido1 - Apellido2 -Apellido3 - V0.01",
				800, 600);
		this.mikasa = new Mikasa(400, 300, 40, 0, false);
		this.suero = new Suero(0);
		this.tiempo = new Tiempo(true, 0, 60);
		this.disparos = new Disparo[300];
		this.imageFondo = Herramientas.cargarImagen("FondoSeis.jpg");
		this.obstaculos = new Obstaculos(5);
		this.obstaculos.generarObs();
		this.item = false;
		this.tickUltimoTitan = -1;
		this.titanes = new Lista<Titan2>();

		// Inicializar lo que haga falta para el juego
		// ...

		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */
	public void tick() {
		// Procesamiento de un instante de tiempo
		// ...

		if (tiempo.isInicia()) {

//			dibujar
			entorno.dibujarImagen(this.imageFondo, 400, 400, 0, 1.7);
			mikasa.dibujarSprite(entorno);
//			for (Titan2 ttn : this.titan.getTitan2()) {
//				ttn.moverTitan();
//				ttn.direccionTitan(mikasa.getX(), mikasa.getY());
//			}

//			instrucciones
			if (entorno.estaPresionada('w') || entorno.estaPresionada(entorno.TECLA_ARRIBA))
				mikasa.moverAdelante();

			if (entorno.estaPresionada('a') || entorno.estaPresionada(entorno.TECLA_IZQUIERDA))
				mikasa.moverIzquierda();

			if (entorno.estaPresionada('s') || entorno.estaPresionada(entorno.TECLA_ABAJO))
				mikasa.moverAtras();

			if (entorno.estaPresionada('d') || entorno.estaPresionada(entorno.TECLA_DERECHA))
				mikasa.moverDerecha();

//			titanes
			if (titanes.largo() < 6) {
				crearTitan();
			}

			this.titanes.forEachNodo(titan -> {
				procesarTitan();
				return null;
			});

//			disparo
			for (Disparo d : disparos) {
				if (d != null) {
					d.setX(mikasa.getX());
					d.setY(mikasa.getY());
					d.setAngulo(mikasa.getAngulo());
				}
			}
			if (this.entorno.estaPresionada(this.entorno.TECLA_ESPACIO)
					&& this.mikasa.getUltimoDisparo() + 90 <= this.tiempo.getContar() && !mikasa.convertir) {
				this.mikasa.disparar((int) this.tiempo.getContar());
			}
			procesarDisparos();

//			suero
			crearSuero();
			funcionSuero();

//			obstaculos
//			por cada obstaculo chequea la distancia entre el titan y el obs, si el metodo colisiona es true,
//			y la distancia entre los circulos es menor a 100, resta o suma en sus respectivos X e Y. del titan
			for (Obstaculos2 obs : this.obstaculos.getObstaculos2()) {
				this.titanes.forEachNodo(ttn -> {
					obstaculos.distancia(obs.getX(), obs.getY(), ttn.getElemento().getX(), ttn.getElemento().getY());
					if (obstaculos.colisiona(obs.getRadio(), ttn.getElemento().getRadio(), obstaculos.getDistancia())
							&& obstaculos.getDistancia() <= 70) {
						titanChocaObs(ttn.getElemento(), obs);
					}
					obs.dibujarSprite(entorno);
					ttn.getElemento().dibujar2(entorno);
					return null;
				});
//					obs.dibujar2(entorno);
			}

//			lo mismo pero con mikasa
			for (Obstaculos2 obs : this.obstaculos.getObstaculos2()) {
				obstaculos.distancia(obs.getX(), obs.getY(), mikasa.getX(), mikasa.getY());
				if (!mikasa.convertir
						&& obstaculos.colisiona(obs.getRadio(), mikasa.getRadio(), obstaculos.getDistancia())
						&& obstaculos.getDistancia() <= 70) {
					mikasaChocaObs(obs);
				} else if (mikasa.convertir
						&& obstaculos.colisiona(obs.getRadio(), mikasa.getRadio(), obstaculos.getDistancia())
						&& obstaculos.getDistancia() <= 85) {
					mikasaChocaObs(obs);
				}
			}

//			controla el fin del juego
//			if ((int) tiempo.getContar() > 40) {
//				this.tiempo = new Tiempo(false, 0);
//			}

//			tiempo.setContar(0.015);
			tiempo.setContar(1);
			tiempo.setTimer(0.0111101);

//			diujar datos
			entorno.cambiarFont("Arial Black", 25, Color.BLACK);
			entorno.escribirTexto("KYOJINES ELIMINADOS: " + 0, 4, 588);
			entorno.cambiarFont("Arial Black", 25, Color.WHITE);
			entorno.escribirTexto("TIEMPO: " + (int) tiempo.getTimer(), 620, 23);
			entorno.escribirTexto("PUNTAJE: " + 0, 4, 23);

		} else {
			entorno.cambiarFont("Arial", 32, Color.WHITE);
			entorno.escribirTexto("GAME OVER", 300, 300);
		}
	}

//	metodos 
	public void titanChocaObs(Titan2 ttn, Obstaculos2 obs) {
		if (ttn.getX() <= obs.getX() && ttn.getY() <= obs.getY()) {
			ttn.setX(ttn.getX() - 2);
			ttn.setY(ttn.getY() - 2);
		} else if (ttn.getX() >= obs.getX() && ttn.getY() >= obs.getY()) {
			ttn.setX(ttn.getX() + 2);
			ttn.setY(ttn.getY() + 2);
		} else if (ttn.getX() >= obs.getX() && ttn.getY() <= obs.getY()) {
			ttn.setX(ttn.getX() + 2);
			ttn.setY(ttn.getY() - 2);
		} else if (ttn.getX() <= obs.getX() && ttn.getY() >= obs.getY()) {
			ttn.setX(ttn.getX() - 2);
			ttn.setY(ttn.getY() + 2);
		}
	}

	public void mikasaChocaObs(Obstaculos2 obs) {
		if (mikasa.getX() <= obs.getX() && mikasa.getY() <= obs.getY()) {
			mikasa.setX(mikasa.getX() - 2);
			mikasa.setY(mikasa.getY() - 2);
			// mikasa.setConvertir(false); esto iria cuando choque con un titan (aca no,
			// obvio)
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

	public void crearSuero() {
		for (Obstaculos2 obs : this.obstaculos.getObstaculos2()) {
			if (tiempo.getContar() == 600/* 0.015 */) {
				suero.setX(Math.random() * (50 - 750) + 750);
				suero.setY(Math.random() * (50 - 550) + 550);
				suero.setRadio(40);
//				suero.distancia2(suero.getX(), suero.getY(), obs.getX(), obs.getY());
//				if (suero.colisiona(obs.getRadio(), suero.getRadio(), suero.getDistancia2())) {
				if (suero.getX() <= obs.getX() && suero.getY() <= obs.getY()) {
					suero.setX(suero.getX() + 40);
					suero.setY(suero.getY() + 40);
				} else if (suero.getX() >= obs.getX() && suero.getY() >= obs.getY()) {
					suero.setX(suero.getX() + 40);
					suero.setY(suero.getY() + 40);
				} else if (suero.getX() >= obs.getX() && suero.getY() <= obs.getY()) {
					suero.setX(suero.getX() + 40);
					suero.setY(suero.getY() - 40);
				} else if (suero.getX() <= obs.getX() && suero.getY() >= obs.getY()) {
					suero.setX(suero.getX() - 40);
					suero.setY(suero.getY() + 40);
				}
			}
			if (tiempo.getContar() == 2000) {
				this.item = false;
				suero.setX(Math.random() * (50 - 750) + 750);
				suero.setY(Math.random() * (50 - 550) + 550);
				suero.setRadio(40);
				if (suero.getX() <= obs.getX() && suero.getY() <= obs.getY()) {
					suero.setX(suero.getX() + 40);
					suero.setY(suero.getY() + 40);
				} else if (suero.getX() >= obs.getX() && suero.getY() >= obs.getY()) {
					suero.setX(suero.getX() + 40);
					suero.setY(suero.getY() + 40);
				} else if (suero.getX() >= obs.getX() && suero.getY() <= obs.getY()) {
					suero.setX(suero.getX() + 40);
					suero.setY(suero.getY() - 40);
				} else if (suero.getX() <= obs.getX() && suero.getY() >= obs.getY()) {
					suero.setX(suero.getX() - 40);
					suero.setY(suero.getY() + 40);
				}
			}
		}

	}

	public void funcionSuero() {
		if ((tiempo.getContar() > 600 && tiempo.getContar() < 1500) && !item) {
			suero.distancia(suero.getX(), suero.getY(), mikasa.getX(), mikasa.getY());
			if (suero.getDistancia() <= 60
					&& suero.colisiona(mikasa.getRadio(), suero.getRadio(), suero.getDistancia())) {
				this.item = true;
				mikasa.setConvertir(true);
				mikasa.setRadio(105);
				mikasa.dibujarSprite(entorno);
				suero.setImage(null);

			} else {
				suero.dibujarSprite(entorno);
			}
		} else if ((tiempo.getContar() > 2000 && tiempo.getContar() < 4500) && !item) {
			suero.distancia(suero.getX(), suero.getY(), mikasa.getX(), mikasa.getY());
			if (suero.getDistancia() <= 60
					&& suero.colisiona(mikasa.getRadio(), suero.getRadio(), suero.getDistancia())) {
				this.item = true;
				suero.setAux(null);
				mikasa.setConvertir(true);
				mikasa.setRadio(105);
				mikasa.dibujarSprite(entorno);

			} else {
				suero.dibujarSprite(entorno);
			}
		}
	}

	private void moverDisparo(Nodo<Disparo> nodoDisparo) {
		nodoDisparo.getElemento().mover();
		nodoDisparo.getElemento().dibujarSprite(this.entorno);
//		nodoDisparo.getElemento().dibujar(entorno);
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

	private void crearTitan() {
		if (this.tickUltimoTitan == -1 || this.tickUltimoTitan + 250 < tiempo.getContar()) {
			this.titanes.agregarAtras(
					new Titan2(Math.random() * (100 - 700) + 700, Math.random() * (50 - 500) + 500, 120, 0));
			this.tickUltimoTitan = (int) tiempo.getContar();
		}
	}

	private void procesarTitan() {
		this.titanes.forEachNodo(nodoTitan -> {
			nodoTitan.getElemento().direccionTitan(this.mikasa.getX(), this.mikasa.getY());
			nodoTitan.getElemento().dibujar2(this.entorno);
			nodoTitan.getElemento().moverTitan();

			return null;
		});
	}

	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}