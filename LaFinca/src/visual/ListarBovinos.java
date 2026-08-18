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
import logica.Becerrito;
import logica.Bovino;
import logica.GestionFinca;
import logica.Toro;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.DropMode;
import javax.swing.BoxLayout;

public class ListarBovinos extends JDialog {
	
	private JPanel contentPane = new JPanel();
	private DefaultTableModel model;
	private JTable table;
	private Object[] row;
	private Bovino selected = null;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JComboBox<String> cbxTipo;
	private JTextPane txtBusqueda;
	//private JLabel lblContenedorImagen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListarBovinos dialog = new ListarBovinos();
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
	public ListarBovinos() {
		
		setBounds(100, 100, 630, 319);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		String[] headers = {"Nombre","Raza","Tipo","Foto","Embarazada","Fecha Aproximada de parto"};
		model = new DefaultTableModel() {
		    @Override
		    public Class<?> getColumnClass(int column) {
		        if (column == 3) {
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
					AgregarBovino updBovino = new AgregarBovino();
					updBovino.setModal(true);
					updBovino.setVisible(true);
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
						//GestionFinca.getInstancia().eliminarVacaPorIndex(selected);
						btnEliminar.setEnabled(false);
						btnModificar.setEnabled(false);
						loadBovinos("");
						
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		cbxTipo = new JComboBox<String>();
		cbxTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected = null;
				btnEliminar.setEnabled(false);
				btnModificar.setEnabled(false);
				loadBovinos("");
			}
		});
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		txtBusqueda = new JTextPane();
		panel_1.add(txtBusqueda);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setSelectedIcon(new ImageIcon(ListarBovinos.class.getResource("/recursos/Lupa.png")));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadBovinos(txtBusqueda.getText());
			}
		});
		btnBuscar.setIcon(new ImageIcon(ListarBovinos.class.getResource("/recursos/Lupa.png")));
		panel_1.add(btnBuscar);
		cbxTipo.setModel(new DefaultComboBoxModel<String>(new String[] {"Vacas", "Toros", "Becerritos"}));
		cbxTipo.setSelectedIndex(0);
		panel_1.add(cbxTipo);
		
		
		loadBovinos("");
	}

	private void loadBovinos(String busqueda) {
	    model.setRowCount(0);
	    row = new Object[model.getColumnCount()];
	    String tipo = (String) cbxTipo.getSelectedItem();
	    if(tipo.equalsIgnoreCase("vacas")){
	    	for(Vaca vaca : GestionFinca.getInstancia().getLasVacas()) {
		        row[0] = vaca.getNombre();
		        row[1] = vaca.getRaza();
		        row[3] = buscarImagen("img" + vaca.getId());
		        row[2] = "Vaca";
		        if(vaca.getUltimoEmbarazo() != null) {
		            row[4] = vaca.getUltimoEmbarazo().getFinalizado().toString();
		            LocalDate temp = vaca.getUltimoEmbarazo().getFechaEmbarazo();
		            row[5] = temp.plusMonths(9).toString();
		        } else {
		            row[4] = "N/A"; 
		            row[5] = "N/A";
		        }
		        if(busqueda.isEmpty())
		        {
		        	model.addRow(row);
		        }else
		        {
		        	if(vaca.getNombre().contains(busqueda))
		        	{
		        		model.addRow(row);
		        	}
		        }
		        
		        
		    }
	    }
	    if(tipo.equalsIgnoreCase("toros")) {
	    	for(Toro toro : GestionFinca.getInstancia().getLosToros()) {
		        row[0] = toro.getNombre();
		        row[1] = toro.getRaza();
		        row[3] = buscarImagen("img" + toro.getId());
		        row[2] = "Toro";
		        row[4] = "N/A"; 
		        row[5] = "N/A";
		        
		        if(busqueda.isEmpty())
		        {
		        	model.addRow(row);
		        }else
		        {
		        	if(toro.getNombre().contains(busqueda))
		        	{
		        		model.addRow(row);
		        	}
		        }
		    }
	    }
	    
	    if(tipo.equalsIgnoreCase("Becerritos")) {
	    	for(Becerrito becerro: GestionFinca.getInstancia().getLosBecerritos()) {
		        row[0] = becerro.getNombre();
		        row[1] = becerro.getRaza();
		        row[3] = buscarImagen("img" + becerro.getId());
		        row[2] = "Becerrito";
		        row[4] = "N/A"; 
		        row[5] = "N/A";
		        
		        if(busqueda.isEmpty())
		        {
		        	model.addRow(row);
		        }else
		        {
		        	if(becerro.getNombre().contains(busqueda))
		        	{
		        		model.addRow(row);
		        	}
		        }
		        
		    }
	    }
	}
	
	private ImageIcon buscarImagen(String nombre) {
	    Path rutaDirectorio = Paths.get("/LaFinca/src/images");
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
