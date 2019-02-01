package app.helpers;

public enum HTTPCodes {

	OK((short) 200),
	NOT_FOUND((short) 404),
	BAD_REQUEST((short) 422),
	INTERNAL_SERVER_ERROR((short) 500);

	private short code;

	HTTPCodes (short code) {
		this.code = code;
	}

	public short val() {
		return code;
	}

}
