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
import javax.sound.sampled.Clip;

@SuppressWarnings("unused")
public class Juego extends InterfaceJuego {

	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Mikasa mikasa;
	private Suero suero;
	private Tiempo tiempo;
	private Image imageFondo;
	private Lista<Disparo> disparos;
	private Obstaculos obstaculos;
	private boolean item;
	private Lista<Titan> titanes;
	private int tickUltimoTitan;
	private int puntos;
	private int eliminados;
	private Clip sonido;
	private Clip sonido2;

	public Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Sakura Ikebana Delivery - Grupo 12 - Rampazzo - Huayta - V0.01",
				800, 600);
		this.mikasa = new Mikasa(400, 300, 40, 0, false);
		this.suero = new Suero(0);
		this.tiempo = new Tiempo(true, 0, 60);
		this.disparos = this.mikasa.getDisparos();
		this.imageFondo = Herramientas.cargarImagen("FondoSeis.jpg");
		this.obstaculos = new Obstaculos(5);
		this.obstaculos.generarObs();
		this.item = false;
		this.tickUltimoTitan = -1;
		this.titanes = new Lista<Titan>();
		this.puntos=0;
		this.eliminados=0;
		this.sonido = Herramientas.cargarSonido("musicaJuego2.wav");
		this.sonido2 = Herramientas.cargarSonido("musicaGameOver.wav");

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
			this.sonido.start();
//			dibujar
			entorno.dibujarImagen(this.imageFondo, 400, 400, 0, 1.7);
			this.mikasa.dibujarSprite(entorno);

//			instrucciones
			if (entorno.estaPresionada('w') || entorno.estaPresionada(entorno.TECLA_ARRIBA))
				this.mikasa.moverAdelante();

			if (entorno.estaPresionada('a') || entorno.estaPresionada(entorno.TECLA_IZQUIERDA))
				this.mikasa.moverIzquierda();

			if (entorno.estaPresionada('s') || entorno.estaPresionada(entorno.TECLA_ABAJO))
				this.mikasa.moverAtras();

			if (entorno.estaPresionada('d') || entorno.estaPresionada(entorno.TECLA_DERECHA))
				this.mikasa.moverDerecha();

//			titanes
			if (this.titanes.largo() < 6) {
				crearTitan();
			}
			
			this.titanes.forEachNodo(titan->{
				impactoTitan(titan);
				return null;
			});

			procesarTitan();

//			disparo
			if (this.entorno.estaPresionada(this.entorno.TECLA_ESPACIO)
					&& this.mikasa.getUltimoDisparo() + 50 <= this.tiempo.getContar() && !this.mikasa.isConvertir()) {
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
				ttn.getElemento().distancia(ttn.getElemento().getX(), ttn.getElemento().getY(), this.mikasa.getX(),
						this.mikasa.getY());
				obstaculos.distancia(obs.getX(), obs.getY(), ttn.getElemento().getX(), ttn.getElemento().getY());
				if (obstaculos.colisiona(obs.getRadio(), ttn.getElemento().getRadio(), this.obstaculos.getDistancia())
						&& this.obstaculos.getDistancia() <= 100) {
					titanChocaObs(ttn.getElemento(), obs);
				}
				if (!this.mikasa.isConvertir() && ttn.getElemento().colisiona(ttn.getElemento().getRadio(),
						this.mikasa.getRadio(), ttn.getElemento().getDistancia())
						&& ttn.getElemento().getDistancia() <= 50) {
					this.tiempo.setInicia(false);
				}
				if (this.mikasa.isConvertir() && ttn.getElemento().colisiona(ttn.getElemento().getRadio(),
						this.mikasa.getRadio(), ttn.getElemento().getDistancia())
						&& ttn.getElemento().getDistancia() <= 100) {
					this.mikasaChocaTtn(ttn);
				}

				obs.dibujarSprite(entorno);
				ttn.getElemento().dibujarSprite(entorno);
				return null;
			});
		}
			
			
//			lo mismo pero con mikasa
			for (Obstaculos2 obs : this.obstaculos.getObstaculos2()) {
			this.obstaculos.distancia(obs.getX(), obs.getY(), this.mikasa.getX(), this.mikasa.getY());
			if (!this.mikasa.isConvertir()
					&& this.obstaculos.colisiona(obs.getRadio(), this.mikasa.getRadio(), this.obstaculos.getDistancia())
					&& this.obstaculos.getDistancia() <= 70) {
				mikasaChocaObs(obs);
			} else if (this.mikasa.isConvertir()
					&& this.obstaculos.colisiona(obs.getRadio(), this.mikasa.getRadio(), this.obstaculos.getDistancia())
					&& this.obstaculos.getDistancia() <= 85) {
				this.mikasaChocaObs(obs);
			}
		}
				
			for (Obstaculos2 obs : this.obstaculos.getObstaculos2()) {
				impactoObs(obs);
			}

			this.tiempo.setContar(1);
			this.tiempo.setTimer(0.01111102);

			if ((int) tiempo.getTimer()==0) {
				tiempo.setInicia(false);
			}
			
//			diujar datos
			entorno.cambiarFont("Arial Black", 25, Color.BLACK);
			entorno.escribirTexto("KYOJINES ELIMINADOS: " + this.eliminados, 4, 588);
			entorno.cambiarFont("Arial Black", 25, Color.WHITE);
			entorno.escribirTexto("TIEMPO: " + (int) this.tiempo.getTimer(), 620, 23);
			entorno.escribirTexto("PUNTAJE: " + this.puntos, 4, 23);

		} else {
			entorno.cambiarFont("Arial", 32, Color.WHITE);
			entorno.escribirTexto("GAME OVER", 300, 300);
			entorno.cambiarFont("Arial", 25, Color.WHITE);
			entorno.escribirTexto("PUNTAJE TOTAL: " + this.puntos, 270, 330);
			this.sonido.close();
			this.sonido2.start();
		}
	}

//	metodos 
	public void titanChocaObs(Titan ttn, Obstaculos2 obs) {
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

	public <T> void mikasaChocaTtn(Nodo<T> ttn) {
		this.titanes.forEachNodo(nodoTitan -> {
			if (ttn.getId() == nodoTitan.getId() && this.mikasa.isConvertir()) {
				this.titanes.quitarPorId(nodoTitan.getId());
				this.mikasa.setConvertir(false);
				this.mikasa.setRadio(40);
				this.puntos += 50;
				this.eliminados += 1;
			}
			return null;
		});
	}



	public void mikasaChocaObs(Obstaculos2 obs) {
		if (this.mikasa.getX() <= obs.getX() && this.mikasa.getY() <= obs.getY()) {
			this.mikasa.setX(this.mikasa.getX() - 2);
			this.mikasa.setY(this.mikasa.getY() - 2);
		} else if (this.mikasa.getX() >= obs.getX() && this.mikasa.getY() >= obs.getY()) {
			this.mikasa.setX(this.mikasa.getX() + 2);
			this.mikasa.setY(this.mikasa.getY() + 2);
		} else if (this.mikasa.getX() >= obs.getX() && this.mikasa.getY() <= obs.getY()) {
			this.mikasa.setX(this.mikasa.getX() + 2);
			this.mikasa.setY(this.mikasa.getY() - 2);
		} else if (this.mikasa.getX() <= obs.getX() && this.mikasa.getY() >= obs.getY()) {
			this.mikasa.setX(this.mikasa.getX() - 2);
			this.mikasa.setY(this.mikasa.getY() + 2);
		}
	}

	public void crearSuero() {
		for (Obstaculos2 obs : this.obstaculos.getObstaculos2()) {
			if (this.tiempo.getContar() == 1000) {
				this.suero.setX(Math.random() * (50 - 750) + 750);
				this.suero.setY(Math.random() * (50 - 550) + 550);
				this.suero.setRadio(40);
				if (this.suero.getX() <= obs.getX() && this.suero.getY() <= obs.getY()) {
					this.suero.setX(this.suero.getX() + 40);
					this.suero.setY(this.suero.getY() + 40);
				} else if (this.suero.getX() >= obs.getX() && this.suero.getY() >= obs.getY()) {
					this.suero.setX(this.suero.getX() + 40);
					this.suero.setY(this.suero.getY() + 40);
				} else if (this.suero.getX() >= obs.getX() && this.suero.getY() <= obs.getY()) {
					this.suero.setX(this.suero.getX() + 40);
					this.suero.setY(this.suero.getY() - 40);
				} else if (this.suero.getX() <= obs.getX() && this.suero.getY() >= obs.getY()) {
					this.suero.setX(this.suero.getX() - 40);
					this.suero.setY(this.suero.getY() + 40);
				}
			}
			if (tiempo.getContar() == 3000) {
				this.item = false;
				this.suero.setX(Math.random() * (50 - 750) + 750);
				this.suero.setY(Math.random() * (50 - 550) + 550);
				this.suero.setRadio(40);
				if (this.suero.getX() <= obs.getX() && this.suero.getY() <= obs.getY()) {
					this.suero.setX(this.suero.getX() + 40);
					this.suero.setY(this.suero.getY() + 40);
				} else if (this.suero.getX() >= obs.getX() && this.suero.getY() >= obs.getY()) {
					this.suero.setX(this.suero.getX() + 40);
					this.suero.setY(this.suero.getY() + 40);
				} else if (this.suero.getX() >= obs.getX() && this.suero.getY() <= obs.getY()) {
					this.suero.setX(this.suero.getX() + 40);
					this.suero.setY(this.suero.getY() - 40);
				} else if (this.suero.getX() <= obs.getX() && this.suero.getY() >= obs.getY()) {
					this.suero.setX(this.suero.getX() - 40);
					this.suero.setY(this.suero.getY() + 40);
				}
			}
		}

	}

	public void funcionSuero() {
		if ((this.tiempo.getContar() > 1000 && this.tiempo.getContar() < 2000) && !this.item) {
			this.suero.distancia(this.suero.getX(), this.suero.getY(), this.mikasa.getX(), this.mikasa.getY());
			if (this.suero.getDistancia() <= 60
					&& this.suero.colisiona(mikasa.getRadio(), suero.getRadio(), suero.getDistancia())) {
				this.item = true;
				this.mikasa.setConvertir(true);
				this.mikasa.setRadio(105);
				this.mikasa.dibujarSprite(entorno);
				this.suero.setImage(null);

			} else {
				this.suero.dibujarSprite(entorno);
			}
		} else if ((this.tiempo.getContar() > 3000 && this.tiempo.getContar() < 4000) && !this.item) {
			this.suero.distancia(this.suero.getX(), this.suero.getY(), this.mikasa.getX(), this.mikasa.getY());
			if (this.suero.getDistancia() <= 60
					&& this.suero.colisiona(this.mikasa.getRadio(), this.suero.getRadio(), this.suero.getDistancia())) {
				this.item = true;
				this.suero.setAux(null);
				this.mikasa.setConvertir(true);
				this.mikasa.setRadio(105);
				this.mikasa.dibujarSprite(entorno);

			} else {
				this.suero.dibujarSprite(entorno);
			}
		}
	}

	private void procesarDisparos() {
		this.mikasa.getDisparos().forEachNodo(disparo -> {
			if (disparo.getElemento().getImpacto() == false) {
				disparo.getElemento().dibujarSprite(this.entorno);
				disparo.getElemento().mover();
			}
			if (disparo.getElemento().getImpacto() == true || !disparo.getElemento().estaEnPantalla()) {
				this.disparos.quitarPorId(disparo.getId());
			}
			return null;
		});
	}

	private void crearTitan() {
		if (this.tickUltimoTitan == -1 || this.tickUltimoTitan + 50 < this.tiempo.getContar()) {
			this.titanes.agregarAtras(
					new Titan(Math.random() * (100 - 700) + 700, Math.random() * (50 - 500) + 500, 120, 0));
			this.tickUltimoTitan = (int) this.tiempo.getContar();
		}
	}

	private void procesarTitan() {
		this.titanes.forEachNodo(nodoTitan -> {
			colisionTitanes(nodoTitan);
			if (nodoTitan.getElemento().getImpacto() == false) {
				nodoTitan.getElemento().direccionTitan(this.mikasa.getX(), this.mikasa.getY());
				nodoTitan.getElemento().dibujarSprite(this.entorno);
				nodoTitan.getElemento().moverTitan();
			}
			if (nodoTitan.getElemento().getImpacto() == true) {
				this.titanes.quitarPorId(nodoTitan.getId());
			}
			return null;
		});
	}

	private void impactoTitan(Nodo<Titan> titan) {
		this.disparos.forEachNodo(disparo -> {
			Disparo dis = disparo.getElemento();
			dis.distancia(dis.getX(), dis.getY(), titan.getElemento().getX(), titan.getElemento().getY());
			if (titan.getElemento().colisiona(titan.getElemento().getRadio(), dis.getRadio(), dis.getDistancia())
					&& dis.getDistancia() < 60) {
				dis.setImpacto(true);
				titan.getElemento().setImpacto(true);
				this.puntos += 15;
				this.eliminados += 1;
			}
			return null;
		});
	}

	private void impactoObs(Obstaculos2 obs) {
		this.disparos.forEachNodo(disparo -> {
			Disparo dis = disparo.getElemento();
			dis.distancia(dis.getX(), dis.getY(), obs.getX(), obs.getY());
			if (dis.colisiona(obs.getRadio(), dis.getRadio(), dis.getDistancia()) && dis.getDistancia() < 50) {
				dis.setImpacto(true);
			}
			return null;
		});
	}

	private void colisionTitanes(Nodo<Titan> t2) {
		this.titanes.forEachNodo(titan -> {
			Titan t = titan.getElemento();
			Titan otro = t2.getElemento();
			if (titan.getId() != t2.getId()) {
				t.distancia(t.getX(), t.getY(), otro.getX(), otro.getY());
				if (t.colisiona(t.getRadio(), otro.getRadio(), t.getDistancia()) && t.getDistancia() < 100) {
					t.rebotar(t, otro);
				}
			}
			return null;
		});
	}

	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
