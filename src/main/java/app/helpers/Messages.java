package app.helpers;

public enum Messages {
	UPDATED("Updated successfully!"),
	DELETED("Deteled successfully!"),
	ERROR("An error has happened");

	private final String value;

	Messages (final String value) {
		this.value = value;
	}

	public String val() {
		return value;
	}
}