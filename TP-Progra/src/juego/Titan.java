
package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Titan {
	private double x;
	private double y;
	private double radio;
	private double angulo;
	private double distancia;
	private Image image = Herramientas.cargarImagen("titan2.png");
	private boolean impacto = false;

	public Titan(double x, double y, double radio, double angulo) {
		this.x = x;
		this.y = y;
		this.radio = radio;
		this.angulo = angulo;
	}

	public void dibujarSprite(Entorno e) {
		e.dibujarImagen(this.image, this.x, this.y, 0, 0.15);
	}

	public void moverTitan() {
		this.x -= Math.cos(this.angulo) * 0.5;
		this.y -= Math.sin(this.angulo) * 0.5;

		if (this.x <= 35) {
			this.x += 5;
		}
		if (this.x >= 755) {
			this.x -= 5;
		}
		if (this.y >= 530) {
			this.y -= 5;
		}
		if (this.y <= 35) {
			this.y += 5;
		}
	}

	public void direccionTitan(double x, double y) {
		double dx = this.x - x;
		double dy = this.y - y;
		this.angulo = Math.atan2(dy, dx);

	}

	public boolean colisiona(double radio1, double radio2, double dist) {
		return (radio1 + radio2) > dist;
	}

	public void distancia(double x1, double y1, double x2, double y2) {
		this.distancia = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	}

	public void rebotar(Titan t1, Titan t2) {
		if (t1.getX() <= t2.getX() && t1.getY() <= t2.getY()) {
			t1.setX(t1.getX() - 2);
			t1.setY(t1.getY() - 2);
		} else if (t1.getX() >= t2.getX() && t1.getY() >= t2.getY()) {
			t1.setX(t1.getX() + 2);
			t1.setY(t1.getY() + 2);
		} else if (t1.getX() >= t2.getX() && t1.getY() <= t2.getY()) {
			t1.setX(t1.getX() + 2);
			t1.setY(t1.getY() - 2);
		} else if (t1.getX() <= t2.getX() && t1.getY() >= t2.getY()) {
			t1.setX(t1.getX() - 2);
			t1.setY(t1.getY() + 2);
		}
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

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public double getDistancia() {
		return this.distancia;
	}

	public boolean getImpacto() {
		return impacto;
	}

	public void setImpacto(boolean a) {
		this.impacto = a;
	}

}
