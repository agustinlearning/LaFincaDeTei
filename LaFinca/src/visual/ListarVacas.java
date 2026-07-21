package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logica.Vaca;
import logica.GestionFinca;

public class ListarVacas extends JDialog {
	
	private JPanel contentPane = new JPanel();
	private DefaultTableModel model;
	private JTable table;
	private Object[] row;
	private Vaca selected = null;
	private JButton btnModificar;
	private JButton btnEliminar;
	//private JLabel lblContenedorImagen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListarVacas dialog = new ListarVacas();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ListarVacas() {
		
		setBounds(100, 100, 630, 319);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		String[] headers = {"Nombre","Raza","Foto","Embarazada","Fecha Aproximada de parto"};
		model = new DefaultTableModel() {
		    @Override
		    public Class<?> getColumnClass(int column) {
		        if (column == 2) {
		            return ImageIcon.class; // Esto le dice al JTable que renderice la imagen
		        }
		        return Object.class;
		    }
		};

		model.setColumnIdentifiers(headers);
		table = new JTable(model);

		table.setRowHeight(110);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				if(index >= 0)
				{
					btnEliminar.setEnabled(true);
					btnModificar.setEnabled(true);
					selected = GestionFinca.getInstancia().buscarVacaPorId(table.getValueAt(index, 0).toString());
				}
			}
		});
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		btnModificar = new JButton("Modificar");
		btnModificar.setEnabled(false);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selected!= null)
				{
					AgregarVaca updVaca = new AgregarVaca();
					updVaca.setModal(true);
					updVaca.setVisible(true);
				}
			}
		});
		btnModificar.setActionCommand("OK");
		panel.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selected!=null)
				{
					int select = JOptionPane.showConfirmDialog(null, "Estas seguro que deseas eliminar el vaca de codigo: " +selected.getId(), "Eliminar Vaca", JOptionPane.WARNING_MESSAGE);
					if(select == JOptionPane.OK_OPTION)
					{
						GestionFinca.getInstancia().eliminarVacaPorIndex(selected);
						btnEliminar.setEnabled(false);
						btnModificar.setEnabled(false);
						loadVacas();
						
					}
				}
			}
		});
		btnEliminar.setEnabled(false);
		btnEliminar.setActionCommand("OK");
		panel.add(btnEliminar);
		
		JButton button_1 = new JButton("Cancelar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setActionCommand("Cancel");
		panel.add(button_1);
		
		
		loadVacas();
	}

	private void loadVacas() {
	    model.setRowCount(0);
	    row = new Object[model.getColumnCount()];
	    
	    for(Vaca vaca : GestionFinca.getInstancia().getLasVacas()) {
	        row[0] = vaca.getNombre();
	        row[1] = vaca.getRaza();
	        row[2] = buscarImagen("img" + vaca.getId());
	        
	        if(vaca.getUltimoEmbarazo() != null) {
	            row[3] = vaca.getUltimoEmbarazo().getFinalizado().toString();
	            LocalDate temp = vaca.getUltimoEmbarazo().getFechaEmbarazo();
	            row[4] = temp.plusMonths(9).toString();
	        } else {
	            row[3] = "N/A"; 
	            row[4] = "N/A";
	        }
	        
	        model.addRow(row);
	    }
	}
	
	private ImageIcon buscarImagen(String nombre) {
	    Path rutaDirectorio = Paths.get("imagenes_app");
	    Optional<Path> resultado1 = buscarArchivo(rutaDirectorio, nombre + ".jpg");
	    Optional<Path> resultado2 = buscarArchivo(rutaDirectorio, nombre + ".png");
	    
	    if(resultado1.isPresent()) {
	        ImageIcon imagenIcono = new ImageIcon(resultado1.get().toAbsolutePath().toString());
	        java.awt.Image imagenEscalada = imagenIcono.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
	        return new ImageIcon(imagenEscalada);
	    }
	    
	    if(resultado2.isPresent()) {
	        ImageIcon imagenIcono = new ImageIcon(resultado2.get().toAbsolutePath().toString());
	        java.awt.Image imagenEscalada = imagenIcono.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
	        return new ImageIcon(imagenEscalada);
	    }
	    return null; 
	}
	
	private static Optional<Path> buscarArchivo(Path directorioBase, String nombreBuscado) {
        try (Stream<Path> paths = Files.find(directorioBase, Integer.MAX_VALUE,
                (path, attrs) -> attrs.isRegularFile() && path.getFileName().toString().equals(nombreBuscado))) {
            return paths.findFirst();
        } catch (IOException e) {
            System.err.println("Error al buscar el archivo: " + e.getMessage());
        }
        return Optional.empty();
    }
	
}
