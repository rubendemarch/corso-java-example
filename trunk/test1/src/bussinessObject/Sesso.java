package bussinessObject;

public enum Sesso {
	M("Maschio"), F("Femmina");
	String label;
	private Sesso(String label){
		this.label=label;
	}
	public String getLabel(){
		return label;
	}
}