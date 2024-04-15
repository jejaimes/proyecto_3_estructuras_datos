package model.vo;

public class Via {
	private long id;
	private double peso;

	public Via(double pPeso, long pId) {
		id = pId;
		peso = pPeso;
	}

	public long darId() {
		return id;
	}

	public double darDistancia() {
		return peso;
	}
}
