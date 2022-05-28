package juego;

public class Titan {
	public Titan2[] titan2;
	private double distancia;

	public Titan(int tamanio) {
		this.titan2 = new Titan2[tamanio];
	}

	public void generarTitan() {
		for (int i = 0; i < titan2.length; i++) {
			if (this.titan2[i] == null) {
				this.titan2[i] = new Titan2(Math.random() * (100 - 700) + 700, Math.random() * (50 - 500) + 500, 120, 0);
			}
		}
	}

	public boolean colisiona(double radio1, double radio2, double dist) {
		return (radio1 + radio2) > dist;
	}

	public void distancia(double x1, double y1, double x2, double y2) {
		this.distancia = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	}

//	getters and setters
	public Titan2[] getTitan2() {
		return titan2;
	}

	public void setTitan2(Titan2[] titan2) {
		this.titan2 = titan2;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}
}