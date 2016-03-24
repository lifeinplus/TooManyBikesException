package com.controller;

import com.exception.TooManyBikesException;

public class BikeOrder {

	private String order = "";

	// ������� ��������� � ����������� ������
	public String getOrder() {
		return order ;
	}

	
	// �������� ������ �� ������������ ������ ���������
	// � ������ ���������� � ������ ��������������
	public void validateOrder(String model, int quantity) throws TooManyBikesException {
		// ���� ������ ���������� �� Giant, � ���������� ������ 10 ����
		if ((model != "Giant") && (quantity < 10)) {
			// ���������� ��������� � ����������� ������
			order = "Your order is valid: Model - " + model + " Quantity - " + quantity;
		} else {
			// �����, ����������� ��������� ���������� �
			// ������� ��� ������������ ��������� �������� 
			throw new TooManyBikesException("Can not ship " + quantity +
					" bikes of the model " + model);
		}
	}

}
