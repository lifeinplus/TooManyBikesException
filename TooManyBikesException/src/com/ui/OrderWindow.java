package com.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.controller.BikeOrder;
import com.exception.TooManyBikesException;

public class OrderWindow extends JFrame {

	private BikeOrder bikeOrder;
	private String selectedModel = "";
	protected int selectedQuantity;

	// Конструктор
	public OrderWindow() {
		// Создаём экземпляр класса BikeOrder
		bikeOrder = new BikeOrder();

		// Создаём экземпляр панели формы
		JPanel windowContent = new JPanel();
		// Задаём менеджер компоновки
		windowContent.setLayout(new GridLayout(3, 2));
		
		// Создаём массив моделей велосипедов
		String[] bikeModels = {"", "Author", "Giant", "GT", "Scott", "Trek"};
		
		// Создаём экземпляры элементов формы
		JLabel labelSelect = new JLabel("Select model:");
		JComboBox comboBoxModel = new JComboBox(bikeModels);
		JLabel labelQuantity = new JLabel("Quantity:");
		final JTextField textFieldQuantity = new JTextField(20);
		JButton buttonOrder = new JButton("Order");
		final JLabel labelStatus = new JLabel("Order status:");
		
		// Обрабатываем выбор элемента выпадающего списка
		comboBoxModel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Получаем информацию о произошедшем событии
				JComboBox cmbItem = (JComboBox) e.getSource();
				// Получаем содержимое элемента выпадающего списка
				selectedModel  = cmbItem.getSelectedItem().toString();
			}
		});
		
		// Обрабатываем нажатие кнопки заказа
		buttonOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Используем конструкцию try-catch, так как
				// метод validateOrder() в своём описании говорит,
				// что может выбрасывать исключение TooManyBikesException
				try {
					if (selectedModel == "") {
						labelStatus.setText("Please choose model!");
					} else {
						// Получаем количество заказываемых велосипедов
						selectedQuantity = Integer.parseInt(textFieldQuantity.getText().toString());
						// Проверяем заказ на соответствие логике программы
						bikeOrder.validateOrder(selectedModel, selectedQuantity);
						// Если кастомное исключение не было выброшено,
						// получаем и устанавливаем в поле статуса
						// сообщение о доступности заказа
						labelStatus.setText(bikeOrder.getOrder());
					}
				// Выбрасывается, если в поле textFieldQuantity
				// присутствуют символы отличные от чисел
				} catch (NumberFormatException nfe) {
					labelStatus.setText("You should provide quantity like integer number!");
				// Обрабатываем кастомное исключение
				} catch (TooManyBikesException tmbe) {
					// Получаем текст, передаваемый конструктору
					// кастомного исключения, и устанавливаем его в поле статуса
					labelStatus.setText(tmbe.getMessage());
				}
			}
		});
		
		// Добавляем элементы на панель
		windowContent.add(labelSelect);
		windowContent.add(comboBoxModel);
		windowContent.add(labelQuantity);
		windowContent.add(textFieldQuantity);
		windowContent.add(buttonOrder);
		windowContent.add(labelStatus);
		
		// Добавляем панель на фрейм
		add(windowContent);
		
		// Устанавливаем заголовок, размеры,
		// видимость и закрытие фрейма
		setTitle("Place your order");
		setSize(600, 150);
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args) {
		// Создаём экземпляр окна заказа
		new OrderWindow();
	}

}
