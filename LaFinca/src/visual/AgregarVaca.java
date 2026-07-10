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


public class AgregarVaca extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JButton btnSeleccionar;
    private JLabel lblContenedorImagen;
    private LocalDate fecha;
    private JSpinner spnFechaEmbarazo;
    private JSpinner spnFechaParto;
    private JSpinner spnAproximada;
    private JCheckBox chxEmbarazada;
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
		setBounds(100, 100, 720, 527);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre: ");
		lblNewLabel.setBounds(51, 56, 69, 20);
		contentPanel.add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(135, 53, 146, 26);
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
        lblContenedorImagen.setBounds(340, 92, 218, 151);
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
		btnSeleccionar.setBounds(313, 53, 278, 23);
		contentPanel.add(btnSeleccionar);
		
		JLabel lblFechaDeEmbarazo = new JLabel("Fecha de embarazo");
		lblFechaDeEmbarazo.setBounds(51, 138, 146, 20);
		contentPanel.add(lblFechaDeEmbarazo);
		
		spnFechaEmbarazo = new JSpinner();
		spnFechaEmbarazo.setEnabled(false);
		spnFechaEmbarazo.setModel(new SpinnerDateModel(new Date(1783569600000L), null, null, Calendar.DAY_OF_MONTH));
		spnFechaEmbarazo.setBounds(51, 166, 172, 26);
		contentPanel.add(spnFechaEmbarazo);
		
		JLabel label = new JLabel("Fecha de parto");
		label.setBounds(51, 203, 146, 20);
		contentPanel.add(label);
		
		spnFechaParto = new JSpinner();
		spnFechaParto.setModel(new SpinnerDateModel(new Date(1783656000000L), null, null, Calendar.DAY_OF_MONTH));
		spnFechaParto.setBounds(51, 231, 172, 26);
		contentPanel.add(spnFechaParto);
		
		chxEmbarazada = new JCheckBox("");
		chxEmbarazada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chxEmbarazada.isSelected())
				{
					spnFechaEmbarazo.setEnabled(true);
				}
				else {
					spnFechaEmbarazo.setEnabled(true);
				}
			}
		});
		chxEmbarazada.setBounds(194, 97, 29, 29);
		contentPanel.add(chxEmbarazada);
		
		JLabel lblNewLabel_1 = new JLabel("\u00BFEmbarazada?");
		lblNewLabel_1.setBounds(51, 102, 102, 20);
		contentPanel.add(lblNewLabel_1);
		
		JLabel label_1 = new JLabel("Fecha Aproximada de Parto");
		label_1.setBounds(51, 273, 218, 20);
		contentPanel.add(label_1);
		
		spnAproximada = new JSpinner();
		spnAproximada.setModel(new SpinnerDateModel(new Date(1783656000000L), new Date(1783656000000L), null, Calendar.DAY_OF_MONTH));
		spnAproximada.setBounds(51, 309, 172, 26);
		contentPanel.add(spnAproximada);
		
	}
}
