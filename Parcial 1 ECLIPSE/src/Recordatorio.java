
public class Recordatorio {
	
	private float x;
	private float y;
	private String recordatorio;
	private String importancia;
	
	public Recordatorio(float x, float y, String recordatorio, String importancia) {
		this.x = x;
		this.y = y;
		this.recordatorio = recordatorio;
		this.importancia = importancia;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public String getRecordatorio() {
		return recordatorio;
	}

	public void setRecordatorio(String recordatorio) {
		this.recordatorio = recordatorio;
	}

	public String getImportancia() {
		return importancia;
	}

	public void setImportancia(String importancia) {
		this.importancia = importancia;
	}
	
	
}