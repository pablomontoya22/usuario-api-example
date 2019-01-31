package app.helpers;

public enum Messages {

	UPDATED("Updated successfully!"),
	DELETED("Deteled successfully!"),
	ERROR("A error has happened");

	private final String value;

	Messages (String value) {
		this.value = value;
	}

	public String val() {
		return value;
	}

}
