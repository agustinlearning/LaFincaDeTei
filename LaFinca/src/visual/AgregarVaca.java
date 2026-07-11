package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.*;
import java.io.File;
import java.time.LocalDate;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JCheckBox;
import javax.swing.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


public class AgregarVaca extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JButton btnSeleccionar;
    private JLabel lblContenedorImagen;
    private JSpinner spnFechaNac;
    private JTextField txtRaza;
    private JComboBox comboBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AgregarVaca dialog = new AgregarVaca();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AgregarVaca() {
		setBounds(100, 100, 720, 420);
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
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		
        lblContenedorImagen = new JLabel("Ninguna imagen seleccionada", SwingConstants.CENTER);
        lblContenedorImagen.setBounds(379, 91, 218, 203);
		contentPanel.add(lblContenedorImagen);
		btnSeleccionar = new JButton("Buscar Imagen...");
		btnSeleccionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear el selector de archivos
                JFileChooser fileChooser = new JFileChooser();
                
                // Filtrar para que solo muestre imágenes
                FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imágenes (JPG, PNG)", "jpg", "jpeg", "png");
                fileChooser.setFileFilter(filtro);

                int resultado = fileChooser.showOpenDialog(null);
                
                if (resultado == JFileChooser.APPROVE_OPTION) {
                    File archivoSeleccionado = fileChooser.getSelectedFile();
                    
                    // Crear el icono y asignarlo al JLabel
                    ImageIcon imagenIcono = new ImageIcon(archivoSeleccionado.getAbsolutePath());
                    
                    // Opcional: Escalar la imagen para que quepa en el JLabel
                    java.awt.Image imagenEscalada = imagenIcono.getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
                    
                    lblContenedorImagen.setIcon(new ImageIcon(imagenEscalada));
                    lblContenedorImagen.setText(""); // Quitar el texto
                    
                }
            }
        });
		btnSeleccionar.setBounds(348, 35, 278, 23);
		contentPanel.add(btnSeleccionar);
		
		JLabel lblFechaDeEmbarazo = new JLabel("Fecha de Nacimiento");
		lblFechaDeEmbarazo.setBounds(51, 109, 146, 20);
		contentPanel.add(lblFechaDeEmbarazo);
		
		spnFechaNac = new JSpinner();
		spnFechaNac.setModel(new LocalDateSpinnerModel(LocalDate.now(), ChronoUnit.DAYS));
		spnFechaNac.setBounds(51, 137, 172, 26);
		contentPanel.add(spnFechaNac);
		
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
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Nacido en finca", "Comprado"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(161, 252, 146, 26);
		contentPanel.add(comboBox);
		
		
	}
}
