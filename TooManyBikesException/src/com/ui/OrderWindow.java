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

	// �����������
	public OrderWindow() {
		// ������ ��������� ������ BikeOrder
		bikeOrder = new BikeOrder();

		// ������ ��������� ������ �����
		JPanel windowContent = new JPanel();
		// ����� �������� ����������
		windowContent.setLayout(new GridLayout(3, 2));
		
		// ������ ������ ������� �����������
		String[] bikeModels = {"", "Author", "Giant", "GT", "Scott", "Trek"};
		
		// ������ ���������� ��������� �����
		JLabel labelSelect = new JLabel("Select model:");
		JComboBox comboBoxModel = new JComboBox(bikeModels);
		JLabel labelQuantity = new JLabel("Quantity:");
		final JTextField textFieldQuantity = new JTextField(20);
		JButton buttonOrder = new JButton("Order");
		final JLabel labelStatus = new JLabel("Order status:");
		
		// ������������ ����� �������� ����������� ������
		comboBoxModel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// �������� ���������� � ������������ �������
				JComboBox cmbItem = (JComboBox) e.getSource();
				// �������� ���������� �������� ����������� ������
				selectedModel  = cmbItem.getSelectedItem().toString();
			}
		});
		
		// ������������ ������� ������ ������
		buttonOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ���������� ����������� try-catch, ��� ���
				// ����� validateOrder() � ���� �������� �������,
				// ��� ����� ����������� ���������� TooManyBikesException
				try {
					if (selectedModel == "") {
						labelStatus.setText("Please choose model!");
					} else {
						// �������� ���������� ������������ �����������
						selectedQuantity = Integer.parseInt(textFieldQuantity.getText().toString());
						// ��������� ����� �� ������������ ������ ���������
						bikeOrder.validateOrder(selectedModel, selectedQuantity);
						// ���� ��������� ���������� �� ���� ���������,
						// �������� � ������������� � ���� �������
						// ��������� � ����������� ������
						labelStatus.setText(bikeOrder.getOrder());
					}
				// �������������, ���� � ���� textFieldQuantity
				// ������������ ������� �������� �� �����
				} catch (NumberFormatException nfe) {
					labelStatus.setText("You should provide quantity like integer number!");
				// ������������ ��������� ����������
				} catch (TooManyBikesException tmbe) {
					// �������� �����, ������������ ������������
					// ���������� ����������, � ������������� ��� � ���� �������
					labelStatus.setText(tmbe.getMessage());
				}
			}
		});
		
		// ��������� �������� �� ������
		windowContent.add(labelSelect);
		windowContent.add(comboBoxModel);
		windowContent.add(labelQuantity);
		windowContent.add(textFieldQuantity);
		windowContent.add(buttonOrder);
		windowContent.add(labelStatus);
		
		// ��������� ������ �� �����
		add(windowContent);
		
		// ������������� ���������, �������,
		// ��������� � �������� ������
		setTitle("Place your order");
		setSize(600, 150);
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args) {
		// ������ ��������� ���� ������
		new OrderWindow();
	}

}
