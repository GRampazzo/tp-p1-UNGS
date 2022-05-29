package utils;

import java.util.function.Function;

public class Lista<T> {
	private Nodo<T> primero;
	private int proximoId;

	public Lista() {
		this.primero = null;
		this.proximoId = 0;
	}

	public void mostrar() {
		Nodo<T> actual = this.primero;
		System.out.print("[");
		while (actual != null) {
			System.out.print(actual.getId() + " ");
			actual = actual.getSiguiente();
		}
		System.out.println("]");
	}

	public void agregarAtras(T n) {
		Nodo<T> nuevo = new Nodo<T>(n, proximoId);
		this.proximoId += 1;
		if (this.primero == null) {
			this.primero = nuevo;
		} else {
			Nodo<T> actual = this.primero;
			while (actual.getSiguiente() != null) {
				actual = actual.getSiguiente();
			}
			actual.setSiguiente(nuevo);
		}
	}

	public void agregarAdelante(T n) {
		Nodo<T> nuevo = new Nodo<T>(n, proximoId);
		this.proximoId += 1;
		if (this.primero == null) {
			this.primero = nuevo;
		} else {
			nuevo.setSiguiente(this.primero);
			this.primero = nuevo;
		}
	}

	public int largo() {
		Nodo<T> actual = this.primero;
		int cant = 0;
		while (actual != null) {
			cant++;
			actual = actual.getSiguiente();
		}
		return cant;
	}

	public void quitarDePosicion(int pos) {
		Nodo<T> actual = this.primero;
		if (this.primero != null && pos == 0) {
			this.primero = this.primero.getSiguiente();
		}
		if (pos >= this.largo())
			return;
		int i = 1;
		while (actual != null && actual.getSiguiente() != null) {
			if (i == pos) {
				actual.setSiguiente(actual.getSiguiente().getSiguiente());
				return;
			}
			actual = actual.getSiguiente();
			i++;
		}
	}

	public void quitarPorId(int id) {
		Nodo<T> actual = this.primero;
		if (this.primero != null && this.primero.getId() == id) {
			this.primero = this.primero.getSiguiente();
		}
		while (actual != null && actual.getSiguiente() != null) {
			if (actual.getSiguiente().getId() == id) {
				actual.setSiguiente(actual.getSiguiente().getSiguiente());
				return;
			}
			actual = actual.getSiguiente();
		}
	}

	public T obtenerDePosicion(int pos) {
		Nodo<T> actual = this.primero;
		int i = 0;
		while (actual != null && actual.getSiguiente() != null) {
			if (i == pos) {
				return actual.getElemento();
			}
			actual = actual.getSiguiente();
			i++;
		}
		return null;
	}

	public T obtenerPorId(int id) {
		Nodo<T> actual = this.primero;
		while (actual != null && actual.getSiguiente() != null) {
			if (actual.getId() == id) {
				return actual.getElemento();
			}
			actual = actual.getSiguiente();
		}
		return null;
	}

	public void forEachNodo(Function<Nodo<T>, Void> callback) {
		Nodo<T> actual = this.primero;
		while (actual != null) {
			callback.apply(actual);
			actual = actual.getSiguiente();
		}
	}

	public void forEachElement(Function<T, Void> callback) {
		Nodo<T> actual = this.primero;
		while (actual != null) {
			callback.apply(actual.getElemento());
			actual = actual.getSiguiente();
		}
	}
//	getters y setters
	public Nodo<T> getPrimero() {
		return primero;
	}

	public void setPrimero(Nodo<T> primero) {
		this.primero = primero;
	}

	public int getProximoId() {
		return proximoId;
	}

	public void setProximoId(int proximoId) {
		this.proximoId = proximoId;
	}
	
}
