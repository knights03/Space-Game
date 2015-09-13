package cargo;

public enum CargoType {
	UIRON("Iron (U)"),
	IRON("Iron"),
	UTRITONITE("Tritonite (U)"),
	TRITONITE("Tritonite");
	
	private String name;
	
	private CargoType(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
