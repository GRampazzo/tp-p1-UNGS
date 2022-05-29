package juego;


import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;
import utils.Lista;

public class Mikasa {
	private double x;
	private double y;
	private double radio;
	private double angulo;
	private double distancia;
	boolean convertir = false;
	private Lista<Disparo> disparos;
	private int ultimoDisparo;
	private int sentido = 0;
	private Image image = Herramientas.cargarImagen("mikasa1.png");
	private Image image2 = Herramientas.cargarImagen("mikasa-Titan.png");

	public Mikasa(double x, double y, double radio, double angulo, boolean convertir) {
		this.x = x;
		this.y = y;
		this.radio = radio;
		this.angulo = angulo;
		this.convertir = convertir;
		this.disparos = new Lista<Disparo>();
	}

//	public void dibujar(Entorno e) {
//		e.dibujarCirculo(this.x, this.y, this.radio, Color.ORANGE);
//	}

	public void dibujarSprite(Entorno e) {
		if (this.convertir) {
			e.dibujarImagen(this.image2, x + 5, y, angulo, 0.1);
		} else {
			e.dibujarImagen(image, this.x, this.y, this.angulo, 0.1);
		}
	}

	public void disparar(int contadorTicks) {
		this.disparos.agregarAtras(new Disparo(this.x, this.y, 10, this.angulo));
		this.ultimoDisparo = contadorTicks;
	}

	// movimiento
	public void moverAdelante() {
		this.sentido = 0;
		if (this.x > 20 && ((this.x) < 820) && this.y < 590 && ((this.y) > -30)) {
			this.x += Math.cos(this.angulo) * 2;
			this.y += Math.sin(this.angulo) * 2;
		}
		if (this.x <= 20 && !this.convertir) {
			this.x += 0.5;
		} else if (this.x <= 35 && this.convertir) {
			this.x += 5;
		}
		if ((this.x) >= 785 && !this.convertir) {
			this.x -= 5;
		} else if (this.x >= 755 && this.convertir) {
			this.x -= 5;
		}
		if (this.y >= 580 && !this.convertir) {
			this.y -= 5;
		} else if (this.y >= 575 && this.convertir) {
			this.y -= 5;
		}
		if ((this.y) <= 20 && !this.convertir) {
			this.y += 5;
		} else if (this.y <= 35 && this.convertir) {
			this.y += 5;
		}
	}

	public void moverDerecha() {
		if (this.sentido == 0) {
			this.angulo += 0.08;
		} else {
			this.angulo -= 0.08;
		}

	}

	public void moverIzquierda() {
		if (this.sentido == 0) {
			this.angulo -= 0.08;
		} else {
			this.angulo += 0.08;
		}
	}

	public void moverAtras() {
		this.sentido = 1;
		if (this.x > 15 && ((this.x) < 820) && this.y < 590 && ((this.y) > -30)) {
			this.x -= Math.cos(this.angulo) * 2;
			this.y -= Math.sin(this.angulo) * 2;
		}
		if (this.x <= 15 && !this.convertir) {
			this.x += 5;
		} else if (this.x <= 30 && this.convertir) {
			this.x += 5;
		}
		if ((this.x) >= 785 && !this.convertir) {
			this.x -= 5;
		} else if (this.x >= 755 && this.convertir) {
			this.x -= 5;
		}
		if (this.y >= 580 && !this.convertir) {
			this.y -= 5;
		} else if (this.y >= 575 && this.convertir) {
			this.y -= 5;
		}
		if ((this.y) <= 20 && !this.convertir) {
			this.y += 5;
		} else if (this.y <= 35 && this.convertir) {
			this.y += 5;
		}
	}
	
	public void distancia(double x1, double y1, double x2, double y2) {
		this.distancia = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	}

//	getters and setters
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getRadio() {
		return radio;
	}

	public void setRadio(double radio) {
		this.radio = radio;
	}

	public double getAngulo() {
		return angulo;
	}

	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public boolean isConvertir() {
		return convertir;
	}

	public void setConvertir(boolean convertir) {
		this.convertir = convertir;
	}

	public Image getImage2() {
		return image2;
	}

	public void setImage2(Image image2) {
		this.image2 = image2;
	}

	public int getUltimoDisparo() {
		return this.ultimoDisparo;
	}

	public Lista<Disparo> getDisparos() {
		return this.disparos;
	}

	public void setDisparos(Lista<Disparo> disparos) {
		this.disparos = disparos;
	}
}