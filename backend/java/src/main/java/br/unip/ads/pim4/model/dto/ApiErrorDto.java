package br.unip.ads.pim4.model.dto;

public class ApiErrorDto {
	
	private String message;

	public ApiErrorDto() {
		// REST
	}

	public ApiErrorDto(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
