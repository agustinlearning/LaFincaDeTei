package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.*;

import logica.GestionFinca;
import logica.Vaca;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import javax.swing.*;

// Nuevas importaciones para LGoodDatePicker
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class AgregarBovino extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JButton btnSeleccionar;
	private JLabel lblContenedorImagen;
	
	// 1. Cambiamos JSpinner por el componente DatePicker
	private DatePicker dpFechaNac;
	
	private JTextField txtRaza;
	private JComboBox<String> cbxProcedencia;
	private File archivoSeleccionado;
	private Path archivoDestino;
	private JComboBox<String> cbxTipo;
	private JLabel lblNumero;
	private JTextField txtNumero;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AgregarBovino dialog = new AgregarBovino();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AgregarBovino() {
		setBounds(100, 100, 810, 387);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre: ");
		lblNewLabel.setBounds(51, 36, 69, 20);
		contentPanel.add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(161, 33, 146, 26);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("A\u00F1adir");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// 2. Extraer la fecha ahora es mucho más limpio y seguro:
						if(cbxTipo.getSelectedIndex() == 0)
						{
							Vaca vaca = Vaca.registrarNueva(
									txtNombre.getText(),
									archivoDestino != null ? archivoDestino.toString() : "",
									dpFechaNac.getDate(), // Obtenemos el LocalDate directamente
									txtRaza.getText(),
									(String) cbxProcedencia.getSelectedItem()
							);
							GestionFinca.getInstancia().agregarVaca(vaca);
							JOptionPane.showMessageDialog(null, "Vaca Registrada", "Informaci\u00F3n", JOptionPane.INFORMATION_MESSAGE);
							clear();
						}
						if(cbxTipo.getSelectedIndex() == 1)
						{
							
						}
						if(cbxTipo.getSelectedIndex() == 2)
						{
							
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		lblContenedorImagen = new JLabel("Elija una imagen", SwingConstants.CENTER);
		lblContenedorImagen.setBounds(561, 91, 172, 173);
		contentPanel.add(lblContenedorImagen);
		
		btnSeleccionar = new JButton("Buscar Imagen...");
		btnSeleccionar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("Im\u00E1genes (JPG, PNG)", "jpg", "jpeg", "png");
				fileChooser.setFileFilter(filtro);

				int resultado = fileChooser.showOpenDialog(null);
				
				if (resultado == JFileChooser.APPROVE_OPTION) {
					File archivoOrigen = fileChooser.getSelectedFile();
					try {
						Path carpetaDestino = Paths.get("/LaFinca/src/images");
						
						if (!Files.exists(carpetaDestino)) {
							Files.createDirectories(carpetaDestino);
						}
						
						String nombreOriginal = archivoOrigen.getName();
						String extension = nombreOriginal.substring(nombreOriginal.lastIndexOf("."));

						GestionFinca.getInstancia();
						String nuevoNombre = "img"+ "B-" + GestionFinca.idBovino + extension;

						archivoDestino = carpetaDestino.resolve(nuevoNombre);
						Files.copy(archivoOrigen.toPath(), archivoDestino, StandardCopyOption.REPLACE_EXISTING);
						
						ImageIcon imagenIcono = new ImageIcon(archivoDestino.toString());
						java.awt.Image imagenEscalada = imagenIcono.getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
						
						lblContenedorImagen.setIcon(new ImageIcon(imagenEscalada));
						lblContenedorImagen.setText(""); 
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		btnSeleccionar.setBounds(532, 35, 226, 23);
		contentPanel.add(btnSeleccionar);
		
		JLabel lblFechaDeEmbarazo = new JLabel("Fecha de Nacimiento");
		lblFechaDeEmbarazo.setBounds(351, 182, 146, 20);
		contentPanel.add(lblFechaDeEmbarazo);
		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setFormatForDatesCommonEra("dd/MM/yyyy"); // Formato latino/europeo
		
		dpFechaNac = new DatePicker(dateSettings);
		dpFechaNac.setDateToToday(); // Por defecto selecciona hoy
		// Se le dio un poco más de ancho (180) para que el botón de calendario quepa perfectamente
		dpFechaNac.setBounds(351, 252, 180, 26); 
		
		contentPanel.add(dpFechaNac);
		
		JLabel lblRaza = new JLabel("Raza: ");
		lblRaza.setBounds(51, 182, 69, 20);
		contentPanel.add(lblRaza);
		
		txtRaza = new JTextField();
		txtRaza.setColumns(10);
		txtRaza.setBounds(161, 179, 146, 26);
		contentPanel.add(txtRaza);
		
		JLabel lblProcedencia = new JLabel("Procedencia: ");
		lblProcedencia.setBounds(51, 255, 95, 20);
		contentPanel.add(lblProcedencia);
		
		cbxProcedencia = new JComboBox<String>();
		cbxProcedencia.setModel(new DefaultComboBoxModel<String>(new String[] {"Nacido en finca", "Comprado"}));
		cbxProcedencia.setToolTipText("");
		cbxProcedencia.setBounds(161, 252, 146, 26);
		contentPanel.add(cbxProcedencia);
		
		cbxTipo = new JComboBox<String>();
		cbxTipo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(cbxTipo.getSelectedIndex()!=2)
				{
					lblNumero.setVisible(false);
					txtNumero.setVisible(false);
				}
				else
				{
					lblNumero.setVisible(true);
					txtNumero.setVisible(true);
				}
				
			}
		});
		cbxTipo.setModel(new DefaultComboBoxModel<String>(new String[] {"Vaca", "Toro", "Becerrito"}));
		cbxTipo.setSelectedIndex(0);
		cbxTipo.setBounds(161, 102, 146, 26);
		contentPanel.add(cbxTipo);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(51, 105, 69, 20);
		contentPanel.add(lblTipo);
		
		lblNumero = new JLabel("Numero");
		lblNumero.setVisible(false);
		lblNumero.setBounds(351, 33, 146, 23);
		contentPanel.add(lblNumero);
		
		
		
		
		txtNumero = new JTextField();
		txtNumero.setBounds(351, 102, 146, 26);
		txtNumero.setVisible(false);
		contentPanel.add(txtNumero);
		txtNumero.setColumns(10);
	}

	private void clear() {
		txtNombre.setText("");
		dpFechaNac.setDateToToday();
		txtRaza.setText("");
		lblContenedorImagen.setText("Imagen no seleccionada");
		lblContenedorImagen.setIcon(null);
	}
}