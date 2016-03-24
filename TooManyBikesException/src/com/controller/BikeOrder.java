package com.controller;

import com.exception.TooManyBikesException;

public class BikeOrder {

	private String order = "";

	// Возврат сообщения о доступности заказа
	public String getOrder() {
		return order ;
	}

	
	// Проверка заказа на соответствие логике программы
	// и выброс исключения в случае несоответствия
	public void validateOrder(String model, int quantity) throws TooManyBikesException {
		// Если модель велосипеда не Giant, а количество меньше 10 штук
		if ((model != "Giant") && (quantity < 10)) {
			// Геренируем сообщение о доступности заказа
			order = "Your order is valid: Model - " + model + " Quantity - " + quantity;
		} else {
			// Иначе, выбрасываем кастомное исключение и
			// передаём его конструктору текстовый параметр 
			throw new TooManyBikesException("Can not ship " + quantity +
					" bikes of the model " + model);
		}
	}

}
