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
import javax.swing.JCheckBox;


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
	private JTextField textFieldPressure;

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
		lblTemperaturaOdczuwalna.setBounds(10, 225, 180, 14);
		contentPane.add(lblTemperaturaOdczuwalna);
		
		textFieldTemperature = new JTextField();
		textFieldTemperature.setEditable(false);
		textFieldTemperature.setBounds(240, 127, 86, 20);
		contentPane.add(textFieldTemperature);
		textFieldTemperature.setColumns(10);
		
		textFieldHumidity = new JTextField();
		textFieldHumidity.setEditable(false);
		textFieldHumidity.setBounds(240, 152, 86, 20);
		contentPane.add(textFieldHumidity);
		textFieldHumidity.setColumns(10);
		
		textFieldWindSpeed = new JTextField();
		textFieldWindSpeed.setEditable(false);
		textFieldWindSpeed.setBounds(240, 177, 86, 20);
		contentPane.add(textFieldWindSpeed);
		textFieldWindSpeed.setColumns(10);
		
		textFieldWindChill = new JTextField();
		textFieldWindChill.setEditable(false);
		textFieldWindChill.setBounds(240, 222, 86, 20);
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
		lblTryb.setBounds(200, -1, 131, 39);
		contentPane.add(lblTryb);
		
		JLabel lblCinienie = new JLabel("Ciśnienie");
		lblCinienie.setBounds(10, 200, 86, 14);
		contentPane.add(lblCinienie);
		
		textFieldPressure = new JTextField();
		textFieldPressure.setEditable(false);
		textFieldPressure.setBounds(240, 197, 86, 20);
		contentPane.add(textFieldPressure);
		textFieldPressure.setColumns(10);
		
		
		JLabel lblWasne = new JLabel("Własne");
		lblWasne.setBounds(188, 105, 46, 14);
		contentPane.add(lblWasne);
		
		final JCheckBox checkBoxTemperature = new JCheckBox("");
		checkBoxTemperature.setBounds(198, 126, 21, 23);
		contentPane.add(checkBoxTemperature);
		
		final JCheckBox checkBoxHumidity = new JCheckBox("");
		checkBoxHumidity.setBounds(198, 151, 21, 23);
		contentPane.add(checkBoxHumidity);
		
		final JCheckBox checkBoxWindSpeed = new JCheckBox("");
		checkBoxWindSpeed.setBounds(198, 176, 21, 23);
		contentPane.add(checkBoxWindSpeed);
		
		final JCheckBox checkBoxChillWind = new JCheckBox("");
		checkBoxChillWind.setBounds(198, 221, 21, 23);
		contentPane.add(checkBoxChillWind);
		
		final JCheckBox checkBoxPressure = new JCheckBox("");
		checkBoxPressure.setBounds(198, 200, 21, 23);
		contentPane.add(checkBoxPressure);
		
		String []modes = {"Nazwa","Współrzędne","ID","Własna strona"};
		final JComboBox comboBoxMode = new JComboBox(modes);
		comboBoxMode.setBounds(240, 7, 91, 22);
		contentPane.add(comboBoxMode);
		
		JButton btnUpdate = new JButton("Aktualizuj");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			{
				try {
					String []numbers;
					numbers=textFieldCoords.getText().split(",");
					weather.updateData(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[1]));
					if(comboBoxMode.getSelectedIndex()==0)
						weather.updateData(textFieldCity.getText());
					else if(comboBoxMode.getSelectedIndex()==1)
					{
						
					}
					else if(comboBoxMode.getSelectedIndex()==2)
					{
						weather.updateData(Integer.parseInt(textFieldID.getText()));
					}
					else if(comboBoxMode.getSelectedIndex()==3)
					{
						String websiteAddress;
						String temperatureForm = null;
						String humidityForm = null;
						String pressureForm = null;
						String chillWindForm = null;
						String windSpeedForm = null;
						WebScrapper webScrapper = new WebScrapper();
						
						websiteAddress=JOptionPane.showInputDialog("Podaj adres strony");
						webScrapper.setWebsiteAddress(websiteAddress);
						
						if(checkBoxTemperature.isSelected())
						{
							temperatureForm=JOptionPane.showInputDialog("Podaj linijkę z temperaturą (może kiedyś będzie poradnik jak to zrobić)");
							webScrapper.setTemperatureForm(temperatureForm);
							webScrapper.updateWeatherInfo();
							textFieldTemperature.setText(Double.toString(webScrapper.getWeatherInfo().getTemperature()));
						}
						
						if(checkBoxHumidity.isSelected())
						{
							humidityForm=JOptionPane.showInputDialog("Podaj linijkę z wilgotnością (może kiedyś będzie poradnik jak to zrobić)");
							webScrapper.setHumidityForm(humidityForm);
							webScrapper.updateWeatherInfo();
							textFieldHumidity.setText(Double.toString(webScrapper.getWeatherInfo().getHumidity()));
						}
						if(checkBoxWindSpeed.isSelected())
						{
							windSpeedForm=JOptionPane.showInputDialog("Podaj linijkę z prędkością wiatru (może kiedyś będzie poradnik jak to zrobić)");
							webScrapper.setWindSpeedForm(windSpeedForm);
							webScrapper.updateWeatherInfo();
							textFieldWindSpeed.setText(Double.toString( webScrapper.getWeatherInfo().getWindSpeed()));
						}
						if(checkBoxChillWind.isSelected())
						{
							chillWindForm=JOptionPane.showInputDialog("Podaj linijkę z temperaturą odczuwalną (To narazie nie działa)");
						}
						
						
					}
					if(!checkBoxTemperature.isSelected())
					{
						textFieldTemperature.setText(Double.toString(weather.getWeatherInfo().getTemperature()));
					}
					if(!checkBoxHumidity.isSelected())
					textFieldHumidity.setText(Double.toString(weather.getWeatherInfo().getHumidity()));
					if(!checkBoxWindSpeed.isSelected())
					textFieldWindSpeed.setText(Double.toString(weather.getWeatherInfo().getWindSpeed()));
					if(!checkBoxPressure.isSelected())
					textFieldPressure.setText(Double.toString(weather.getWeatherInfo().getPressure()));
					
					if(!checkBoxChillWind.isSelected())
					{
						if(!checkBoxTemperature.isSelected()&&!checkBoxHumidity.isSelected()&&!checkBoxWindSpeed.isSelected())
						{
							textFieldWindChill.setText(String.format("%.2f",weather.getChillWind()));
						}
						else
						{
							textFieldWindChill.setText(String.format("%.2f",weather.getChillWind(
									Double.parseDouble(textFieldTemperature.getText()), 
									Double.parseDouble(textFieldHumidity.getText()),
									Double.parseDouble(textFieldWindSpeed.getText()))));
						}
					}
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Nie udało się uzyskać informacji o pogodzie");
					e.printStackTrace();
				}
			}
			}
		});
		btnUpdate.setBounds(341, 7, 91, 23);
		contentPane.add(btnUpdate);
	}
}
