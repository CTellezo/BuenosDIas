package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.daoAlumno;
import modelo.Alumno;
import modelo.Usuario;

import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class vAlumno extends JInternalFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JComboBox cboMunicipio;
	private JComboBox cboCarrera;
	private JComboBox cboGrupo;
	private JLabel lblID;
	private JScrollPane scrollPaneAlumno;
	private JTable tblAlumnos;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnEliminar;
	DefaultTableModel modeloA = new DefaultTableModel();
	daoAlumno dao = new daoAlumno();
	ArrayList<Alumno> lista;
	int fila = -1;
	Alumno alumno =new Alumno();
	private JButton btnBorrar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vAlumno frame = new vAlumno();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void actualizartablaalumnos() {
		while (modeloA.getRowCount() > 0) {
			modeloA.removeRow(0);
		}
		lista = dao.consultaAlumnos();
		for (Alumno u : lista) {
			Object user[] = new Object[5];
			user[0] = u.getId();
			user[1] = u.getNombre();
			user[2] = u.getGrupo();
			user[3] = u.getCarrera();
			user[4] = u.getMunicipio();
			modeloA.addRow(user);
		}
		tblAlumnos.setModel(modeloA);
	}

	public void limpiar() {
		lblID.setText("");
		txtNombre.setText(null);
		cboGrupo.setSelectedItem(null);
		cboCarrera.setSelectedItem(null);
		cboMunicipio.setSelectedItem(null);
	}

	public vAlumno() {
		//setIconImage(Toolkit.getDefaultToolkit().getImage(vAlumno.class.getResource("/img/Java.jpg")));
		setTitle("CRUD ALUMNO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 749, 336);
		//setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblID = new JLabel("1");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblID.setBounds(121, 32, 46, 14);
		contentPane.add(lblID);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(33, 34, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("NOMBRE");
		lblNewLabel_1.setBounds(33, 66, 60, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("GRUPO");
		lblNewLabel_2.setBounds(33, 100, 46, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("CARRERA");
		lblNewLabel_3.setBounds(33, 139, 83, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("MUNICIPIO");
		lblNewLabel_4.setBounds(33, 176, 83, 14);
		contentPane.add(lblNewLabel_4);

		txtNombre = new JTextField();
		txtNombre.setBounds(121, 63, 131, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		cboGrupo = new JComboBox();
		cboGrupo.setModel(new DefaultComboBoxModel(new String[] { "101", "102", "103", "104", "105", "106", "301",
				"302", "303", "304", "305", "306", "501", "502", "503", "504", "505", "506" }));
		cboGrupo.setBounds(121, 96, 131, 22);
		contentPane.add(cboGrupo);

		cboCarrera = new JComboBox();
		cboCarrera.setModel(
				new DefaultComboBoxModel(new String[] { "Programación", "Ventas", "Maquinas y herramientas" }));
		cboCarrera.setBounds(121, 135, 131, 22);
		contentPane.add(cboCarrera);

		cboMunicipio = new JComboBox();
		cboMunicipio.setModel(new DefaultComboBoxModel(new String[] { "Tecamac", "Ecatepec", "Tizayuca" }));
		cboMunicipio.setBounds(121, 172, 131, 22);
		contentPane.add(cboMunicipio);

		scrollPaneAlumno = new JScrollPane();
		scrollPaneAlumno.setBounds(281, 11, 430, 204);
		contentPane.add(scrollPaneAlumno);

		tblAlumnos = new JTable();
		tblAlumnos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila=tblAlumnos.getSelectedRow();
				alumno=lista.get(fila);
				lblID.setText(""+alumno.getId());
				txtNombre.setText(""+alumno.getNombre());
				cboGrupo.setSelectedItem(""+alumno.getGrupo());
				cboCarrera.setSelectedItem(""+alumno.getCarrera());
				cboMunicipio.setSelectedItem(""+alumno.getMunicipio());
			}
		});
		tblAlumnos.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null }, { null, null, null, null, null },
						{ null, null, null, null, null }, },
				new String[] { "New column", "New column", "New column", "New column", "New column" }));
		scrollPaneAlumno.setViewportView(tblAlumnos);
		modeloA.addColumn("ID");
		modeloA.addColumn("NOMBRE");
		modeloA.addColumn("GRUPO");
		modeloA.addColumn("CARRERA");
		modeloA.addColumn("MUNICIPIO");
		tblAlumnos.setModel(modeloA);
		actualizartablaalumnos();

		btnAgregar = new JButton("AGREGAR");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtNombre.getText().equals("") || cboGrupo.getSelectedItem().equals("")
							|| cboCarrera.getSelectedItem().equals("") || cboMunicipio.getSelectedItem().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS");
						return;
					}
					Alumno userA = new Alumno();
					userA.setNombre(txtNombre.getText());
					userA.setGrupo(Integer.parseInt(cboGrupo.getSelectedItem().toString()));
					userA.setCarrera(cboCarrera.getSelectedItem().toString());
					userA.setMunicipio(cboMunicipio.getSelectedItem().toString());
					if (dao.insertarAlumno(userA)) {
						actualizartablaalumnos();
						limpiar();
						JOptionPane.showMessageDialog(null, "SE AGREGO CORRECTAMENTE");
					} else {
						JOptionPane.showMessageDialog(null, "ERROR");
					}
				} catch (Exception ea) {
					JOptionPane.showMessageDialog(null, "ERROR");

				}
			}
		});
		btnAgregar.setBounds(96, 251, 89, 23);
		contentPane.add(btnAgregar);

		btnEditar = new JButton("EDITAR");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtNombre.getText().equals("") || cboGrupo.getSelectedItem().equals("")
							|| cboCarrera.getSelectedItem().equals("") || cboMunicipio.getSelectedItem().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS ");
						return;
					}
					alumno.setNombre(txtNombre.getText());
					alumno.setGrupo(Integer.parseInt(cboGrupo.getSelectedItem().toString()));
					alumno.setCarrera(cboCarrera.getSelectedItem().toString());
					alumno.setMunicipio(cboMunicipio.getSelectedItem().toString());
					if (dao.editarAlumno(alumno)) {
						actualizartablaalumnos();
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
		btnEditar.setBounds(263, 251, 89, 23);
		contentPane.add(btnEditar);

		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					int opcion = JOptionPane.showConfirmDialog(null, "¿ESTA SEGURO DE ELIMINAR ESTE ALUMNO?",
							"ELIMINAR ALUMNO", JOptionPane.YES_NO_OPTION);
					if (opcion == 0) {
						if (dao.EliminarAlumno(lista.get(fila).getId())) {
							actualizartablaalumnos();
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
		btnEliminar.setBounds(437, 251, 89, 23);
		contentPane.add(btnEliminar);
		
		btnBorrar = new JButton("BORRAR");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		btnBorrar.setBounds(592, 251, 89, 23);
		contentPane.add(btnBorrar);
	}
}
