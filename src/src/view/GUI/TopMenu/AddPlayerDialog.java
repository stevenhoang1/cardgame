package view.GUI.TopMenu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.AddPlayerActionListener;
import view.GUI.MainFrame;

@SuppressWarnings("serial")
public class AddPlayerDialog extends JDialog {
	private JTextField textFieldName = new JTextField(20);

	public JTextField getTextFieldName() {
		return textFieldName;
	}

	public JTextField getInitBet() {
		return initBet;
	}

	private JTextField initBet = new JTextField(20);
	private JButton btnOk = new JButton("ok");

	public void closeDialog() {
		this.dispose();
	}

	public AddPlayerDialog(MainFrame frame) {

		super(frame, "Add a new player");
		
		this.setModal(true);
		this.setBounds(100, 100, 300, 200);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel buttons = new JPanel(new FlowLayout());
		JPanel text = new JPanel(new GridLayout(2, 2, 10, 30));
		add(new JLabel("Add a new player"), BorderLayout.NORTH);
		buttons.add(btnOk);
		text.add(new JLabel("Name:"));
		text.add(textFieldName);
		text.add(new JLabel("Initial Bet:"));
		text.add(initBet);
		add(buttons, BorderLayout.SOUTH);
		add(text);
		btnOk.addActionListener(new AddPlayerActionListener(frame, this));
		
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
