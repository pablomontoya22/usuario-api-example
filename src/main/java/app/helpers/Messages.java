package app.helpers;

public enum Messages {
	UPDATED("Updated successfully!"),
	DELETED("Deteled successfully!"),
	ERROR("An error has happened"),
	NOT_FOUND("Record not found"),
	CONFLICT("Duplicated record");

	private final String value;

	Messages (final String value) {
		this.value = value;
	}

	public String val() {
		return value;
	}
}