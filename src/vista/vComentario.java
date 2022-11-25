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
import dao.daoComentario;
import dao.daoUsuario;
import modelo.Comentario;
import modelo.Usuario;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class vComentario extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtTexto;
	private JTable tblUsuarios;
	private JLabel lblID;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnEditar;
	private JButton btnBorrar;
	private JScrollPane scrollPane;
	daoComentario dao = new daoComentario();
	DefaultTableModel modelo = new DefaultTableModel();
	ArrayList<Comentario> lista = new ArrayList<Comentario>();
	Comentario comentario;
	int fila = -1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vComentario frame = new vComentario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void limpiar() {
		txtUsuario.setText("");
		txtTexto.setText("");
		lblID.setText("");
	}

	public vComentario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(vUsuario.class.getResource("/img/Java.jpg")));
		setTitle("CRUD COMENTARIO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 566, 448);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Nirmala UI", Font.BOLD, 19));
		lblNewLabel.setBounds(25, 32, 46, 23);
		contentPane.add(lblNewLabel);
		lblID = new JLabel("1");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblID.setBounds(164, 40, 46, 14);
		contentPane.add(lblID);
		JLabel lblNewLabel_1 = new JLabel("USUARIO");
		lblNewLabel_1.setFont(new Font("Nirmala UI", Font.BOLD, 19));
		lblNewLabel_1.setBounds(25, 66, 86, 21);
		contentPane.add(lblNewLabel_1);
		txtUsuario = new JTextField();
		txtUsuario.setBounds(164, 67, 169, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		JLabel lblNewLabel_1_1 = new JLabel("TEXTO");
		lblNewLabel_1_1.setFont(new Font("Nirmala UI", Font.BOLD, 19));
		lblNewLabel_1_1.setBounds(25, 100, 129, 23);
		contentPane.add(lblNewLabel_1_1);
		txtTexto = new JTextField();
		txtTexto.setColumns(10);
		txtTexto.setBounds(164, 97, 169, 20);
		contentPane.add(txtTexto);
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtUsuario.getText().equals("") || txtTexto.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS ");
						return;
					}
					Comentario user = new Comentario();
					user.setUsuario(txtUsuario.getText());
					user.setTexto(txtTexto.getText());
					if (dao.insertarComentario(user)) {
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
		btnAgregar.setBounds(22, 187, 106, 23);
		contentPane.add(btnAgregar);

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtUsuario.getText().equals("") || txtTexto.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS ");
						return;
					}
					comentario.setUsuario(txtUsuario.getText());
					comentario.setTexto(txtTexto.getText());
					if (dao.editarComentario(comentario)) {
						actualizarTabla();
						limpiar();
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
		btnEditar.setBounds(283, 187, 89, 23);
		contentPane.add(btnEditar);
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					int opcion = JOptionPane.showConfirmDialog(null, "¿ESTA SEGURO DE ELIMINAR ESTE COMENTARIO?",
							"ELIMINAR COMENTARIO", JOptionPane.YES_NO_OPTION);
					if (opcion == 0) {
						if (dao.EliminarComentario(lista.get(fila).getId())) {
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
		btnEliminar.setBounds(159, 187, 103, 23);
		contentPane.add(btnEliminar);
		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblID.setText("");
				txtTexto.setText(null);
				txtUsuario.setText(null);
				limpiar();
			}
		});
		btnBorrar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnBorrar.setFont(new Font("Imprint MT Shadow", Font.ITALIC, 17));
		btnBorrar.setBounds(404, 187, 89, 23);
		contentPane.add(btnBorrar);
		scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		scrollPane.setBounds(22, 240, 503, 158);
		contentPane.add(scrollPane);
		tblUsuarios = new JTable();
		tblUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = tblUsuarios.getSelectedRow();
				fila = tblUsuarios.getSelectedRow();
				comentario = lista.get(fila);
				lblID.setText("" + lista.get(fila).getId());
				txtUsuario.setText(comentario.getUsuario());
				txtTexto.setText(comentario.getTexto());
			}
		});
		tblUsuarios.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null }, },
				new String[] { "New column", "New column", "New column", "New column" }));
		scrollPane.setViewportView(tblUsuarios);
		modelo.addColumn("ID");
		modelo.addColumn("USUARIO");
		modelo.addColumn("TEXTO");
		tblUsuarios.setModel(modelo);
		actualizarTabla();
	}

	public void actualizarTabla() {
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		lista = dao.fetchComentarios();
		for (Comentario u : lista) {
			Object o[] = new Object[3];
			o[0] = u.getId();
			o[1] = u.getUsuario();
			o[2] = u.getTexto();
			modelo.addRow(o);
		}
		tblUsuarios.setModel(modelo);
	}
}