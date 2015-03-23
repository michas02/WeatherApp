import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ComboBoxEditor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JComboBox;


public class Gui extends JFrame {
	private WeatherApp weather;

	private JPanel contentPane;
	private JTextField textFieldCity;
	private JTextField textFieldTemperature;
	private JTextField textFieldHumidity;
	private JTextField textFieldWindSpeed;
	private JTextField textFieldWindChill;
	private JTextField textFieldCoords;
	private JTextField textFieldID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Gui() {
		weather = new WeatherApp();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMiasto = new JLabel("Miasto");
		lblMiasto.setBounds(10, 11, 46, 14);
		contentPane.add(lblMiasto);
		
		textFieldCity = new JTextField();
		textFieldCity.setText("Wroclaw\t");
		textFieldCity.setBounds(104, 8, 86, 20);
		contentPane.add(textFieldCity);
		textFieldCity.setColumns(10);
		
		JLabel lblTemperatura = new JLabel("Temperatura");
		lblTemperatura.setBounds(10, 130, 180, 14);
		contentPane.add(lblTemperatura);
		
		JLabel lblNewLabel = new JLabel("Wilgotno\u015B\u0107");
		lblNewLabel.setBounds(10, 155, 180, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPrdkoWiatru = new JLabel("Pr\u0119dko\u015B\u0107 wiatru");
		lblPrdkoWiatru.setBounds(10, 180, 180, 14);
		contentPane.add(lblPrdkoWiatru);
		
		JLabel lblTemperaturaOdczuwalna = new JLabel("Temperatura odczuwalna");
		lblTemperaturaOdczuwalna.setBounds(10, 205, 180, 14);
		contentPane.add(lblTemperaturaOdczuwalna);
		
		textFieldTemperature = new JTextField();
		textFieldTemperature.setEditable(false);
		textFieldTemperature.setBounds(200, 127, 86, 20);
		contentPane.add(textFieldTemperature);
		textFieldTemperature.setColumns(10);
		
		textFieldHumidity = new JTextField();
		textFieldHumidity.setEditable(false);
		textFieldHumidity.setBounds(200, 152, 86, 20);
		contentPane.add(textFieldHumidity);
		textFieldHumidity.setColumns(10);
		
		textFieldWindSpeed = new JTextField();
		textFieldWindSpeed.setEditable(false);
		textFieldWindSpeed.setBounds(200, 177, 86, 20);
		contentPane.add(textFieldWindSpeed);
		textFieldWindSpeed.setColumns(10);
		
		textFieldWindChill = new JTextField();
		textFieldWindChill.setEditable(false);
		textFieldWindChill.setBounds(200, 202, 86, 20);
		contentPane.add(textFieldWindChill);
		textFieldWindChill.setColumns(10);
		
		
		
		
		JLabel lblWsprzdne = new JLabel("Wsp\u00F3\u0142rz\u0119dne");
		lblWsprzdne.setBounds(10, 36, 86, 14);
		contentPane.add(lblWsprzdne);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(10, 61, 46, 14);
		contentPane.add(lblId);
		
		textFieldCoords = new JTextField();
		textFieldCoords.setText("51.1,17.03");
		textFieldCoords.setBounds(104, 33, 86, 20);
		contentPane.add(textFieldCoords);
		textFieldCoords.setColumns(10);
		
		textFieldID = new JTextField();
		textFieldID.setText("3081368");
		textFieldID.setBounds(104, 58, 86, 20);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		JLabel lblTryb = new JLabel("Tryb");
		lblTryb.setBounds(200, 11, 46, 14);
		contentPane.add(lblTryb);
		
		String []modes = {"Nazwa","Wspó³rzêdne","ID"};
		final JComboBox comboBoxMode = new JComboBox(modes);
		comboBoxMode.setBounds(240, 7, 91, 22);
		contentPane.add(comboBoxMode);
		
		JButton btnUpdate = new JButton("Aktualizuj");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			{
				try {
					if(comboBoxMode.getSelectedIndex()==0)
						weather.updateData(textFieldCity.getText());
					else if(comboBoxMode.getSelectedIndex()==1)
					{
						String []numbers;
						numbers=textFieldCoords.getText().split(",");
						weather.updateData(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[1]));
					}
					else if(comboBoxMode.getSelectedIndex()==2)
						weather.updateData(Integer.parseInt(textFieldID.getText()));
					textFieldTemperature.setText(String.format("%.2f",weather.getWeatherInfo().getTemperature()));
					textFieldHumidity.setText(Double.toString(weather.getWeatherInfo().getHumidity()));
					textFieldWindSpeed.setText(Double.toString(weather.getWeatherInfo().getWindSpeed()));
					textFieldWindChill.setText(String.format("%.2f",weather.getChillWind()));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Nie uda³o siê uzyskaæ informacji o pogodzie");
					e.printStackTrace();
				}
			}
			}
		});
		btnUpdate.setBounds(341, 7, 91, 23);
		contentPane.add(btnUpdate);
	}
}
