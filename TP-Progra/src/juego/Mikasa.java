package juego;

import java.awt.Color;

import entorno.Entorno;

public class Mikasa {
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private double angulo;

	public Mikasa(double x, double y, double ancho, double alto, double angulo) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.angulo = angulo;
	}

	public void dibujar(Entorno e) {
		e.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, this.angulo, Color.ORANGE);
	}

	// movimiento
	public void moverAdelante() {
		if (this.x > 20 && ((this.x + this.ancho) < 800) && this.y < 590 && ((this.y - this.alto) > 0)) {
			this.x += Math.cos(this.angulo) * 1.5;
			this.y += Math.sin(this.angulo) * 1.5;
		}
		if(this.x <= 20){
			this.x = this.x+10;
		}
		if ((this.x + this.ancho) >= 800){	
			this.x= this.x-10;
		}
		if (this.y>=580) {
			this.y =this.y-10; 
		}
		if ((this.y-this.alto) <= 0){
			this.y= this.y+10;
		}
	}


	public void moverDerecha() {
		this.angulo += 0.08;
	}

	public void moverIzquierda() {
		this.angulo -= 0.08;
	}
	
}
