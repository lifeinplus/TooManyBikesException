package com.exception;

public class TooManyBikesException extends Exception {
	
	// Получаем текстовый параметр
	public TooManyBikesException(String msgText) {
		// Передаём полученный параметр
		// конструктору супер-класса
		super(msgText);
	}
	
}
