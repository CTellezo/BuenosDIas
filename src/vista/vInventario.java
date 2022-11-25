 package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.management.modelmbean.InvalidTargetObjectTypeException;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.daoCategoria;
import dao.daoComandos;
import dao.daoUsuario;
import modelo.Abastecer;
import modelo.Categoria;
import modelo.Usuario;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Color;

public class vInventario extends JFrame {

	private JPanel contentPane;
	int fila = -1;
	private JTextField txtD;
	private JTable tblinventario;
	private JLabel lblID;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnEditar;
	private JButton btnBorrar;
	private JScrollPane scrollPane;
	daoComandos dao = new daoComandos();
	DefaultTableModel modelo = new DefaultTableModel();
	ArrayList<Abastecer> lista = new ArrayList<Abastecer>();
	Abastecer abastecer;
	private JSlider sldCantidad;
	int producto;
	private JLabel lblP;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vInventario frame = new vInventario();
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
	public void limpiar() {
		txtD.setText("");
	    lblP.setText("");
	}

	public vInventario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(vInventario.class.getResource("/img/Java.jpg")));
		setTitle("UNIDADES X PRODUCTO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 642, 424);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Nirmala UI", Font.BOLD, 19));
		lblNewLabel.setBounds(20, 21, 46, 23);
		contentPane.add(lblNewLabel);
		lblID = new JLabel("1");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblID.setBounds(148, 24, 73, 20);
		contentPane.add(lblID);
		txtD = new JTextField();
		txtD.setBounds(148, 63, 174, 34);
		contentPane.add(txtD);
		txtD.setColumns(10);
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtD.getText().equals("")|| sldCantidad.getValue()<=0) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS ");
						return;
					}
					Abastecer user = new Abastecer();
					user.setDescripcion(txtD.getText());
					user.setCantidad(sldCantidad.getValue());
					System.out.println("Cantidad: "+sldCantidad.getValue());
					if (dao.insertarProducto(user)) {
						actualizarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "SE AGREGO CORRECTAMENTE");
					} else {
						JOptionPane.showMessageDialog(null, "ERROR");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "ERROR");
				}
			}
		});
		btnAgregar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnAgregar.setFont(new Font("Imprint MT Shadow", Font.ITALIC, 17));
		btnAgregar.setBounds(335, 14, 112, 41);
		contentPane.add(btnAgregar);

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtD.getText().equals("") || sldCantidad.getAccessibleContext().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS ");
						return;
					}
					abastecer.setDescripcion(txtD.getText());
					abastecer.setCantidad(sldCantidad.getValue());
					if (dao.editarProducto(abastecer)) {
						actualizarTabla();
						JOptionPane.showMessageDialog(null, "SE ACTUALIZO  CORRECTAMENTE");
					} else {
						JOptionPane.showMessageDialog(null, "ERROR");
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "ERROR");
				}
			}
		});
		btnEditar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnEditar.setFont(new Font("Imprint MT Shadow", Font.ITALIC, 17));
		btnEditar.setBounds(489, 14, 112, 41);
		contentPane.add(btnEditar);
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					int opcion = JOptionPane.showConfirmDialog(null, "¿ESTA SEGURO DE ELIMINAR ESTE PRODUCTO?","ELIMINAR PRODUCTO", JOptionPane.YES_NO_OPTION);
					if (opcion == 0) {
						if (dao.EliminarProducto(lista.get(fila).getId())) {
							actualizarTabla();
							JOptionPane.showMessageDialog(null, "SE ELIMINO CORRECTAMENTE");
						} else {
							JOptionPane.showMessageDialog(null, "ERROR");
						}
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "ERROR");
				}
			}
		});
		btnEliminar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnEliminar.setFont(new Font("Imprint MT Shadow", Font.ITALIC, 17));
		btnEliminar.setBounds(415, 139, 103, 39);
		contentPane.add(btnEliminar);
		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		btnBorrar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnBorrar.setFont(new Font("Imprint MT Shadow", Font.ITALIC, 17));
		btnBorrar.setBounds(415, 70, 103, 41);
		contentPane.add(btnBorrar);
		scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		scrollPane.setBounds(34, 196, 533, 158);
		contentPane.add(scrollPane);
		tblinventario = new JTable();
		tblinventario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = tblinventario.getSelectedRow();
				fila = tblinventario.getSelectedRow();
				abastecer = lista.get(fila);
				lblID.setText("" + lista.get(fila).getId());
				txtD.setText(abastecer.getDescripcion());
				sldCantidad.setValue(abastecer.getCantidad());
			}
		});
		tblinventario.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null }, },
				new String[] { "New column", "New column", "New column", "New column" }));
		scrollPane.setViewportView(tblinventario);
		modelo.addColumn("ID");
		modelo.addColumn("DESCRIPCION");
		modelo.addColumn("CANTIDAD");
		tblinventario.setModel(modelo);
		
		JLabel lblCategoria = new JLabel("Descripcion");
		lblCategoria.setFont(new Font("Nirmala UI", Font.BOLD, 19));
		lblCategoria.setBounds(20, 65, 118, 23);
		contentPane.add(lblCategoria);
		
		sldCantidad =  new JSlider();
		sldCantidad.setPaintTicks(true);
		sldCantidad.setForeground(new Color(139, 69, 19));
		sldCantidad.setMinimum(1);
		sldCantidad.setValue(0);
		sldCantidad.setMinorTickSpacing(5);
		sldCantidad.setMajorTickSpacing(25);
		sldCantidad.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				producto = sldCantidad.getValue();
				lblP.setText(""+ producto);
				//actualizarTabla();
			}
		});
		sldCantidad.setValue(0);
		sldCantidad.setBounds(108, 123, 223, 41);
		contentPane.add(sldCantidad);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setFont(new Font("Nirmala UI", Font.BOLD, 19));
		lblCantidad.setBounds(18, 119, 103, 34);
		contentPane.add(lblCantidad);
		
		lblP = new JLabel("");
		lblP.setFont(new Font("Nirmala UI", Font.BOLD, 19));
		lblP.setBounds(335, 133, 64, 31);
		contentPane.add(lblP);
		actualizarTabla();
	}

	public void actualizarTabla() {
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		lista = dao.fetchAbastecer();
		for (Abastecer u : lista) {
			Object o[] = new Object[4];
			o[0] = u.getId();
			o[1] = u.getDescripcion();
			o[2] = u.getCantidad();
			modelo.addRow(o);
		}
		tblinventario.setModel(modelo);
	}
}
