package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import javax.swing.JButton;

public class vPrincipal extends JFrame {

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vPrincipal frame = new vPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public vPrincipal() {
		setTitle("SISTEMAS POS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 491);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("CRUDS");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("USUARIOS");
		mnNewMenu.add(mntmNewMenuItem);
		getContentPane().setLayout(null);
		
		JDesktopPane desktopPane_1 = new JDesktopPane();
		desktopPane_1.setBounds(0, 34, 687, 385);
		getContentPane().add(desktopPane_1);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 687, 34);
		getContentPane().add(toolBar);
		
		JButton btnNewButton = new JButton("crud usuarios");
		toolBar.add(btnNewButton);
	}
}
