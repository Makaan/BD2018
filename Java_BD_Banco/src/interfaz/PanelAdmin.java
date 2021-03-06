package interfaz;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Banco.ConsultaAdmin;
import quick.dbtable.DBTable;


import java.awt.FlowLayout;
import javax.swing.JTextArea;

public class PanelAdmin extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DBTable tableConsulta;
	private JList<String> listNombreTablas;
	private JList<String> listAtributosTabla;
	
	private ConsultaAdmin consu; 
	private JButton btnBorrar;
	private JTextArea textArea;

	/**
	 * Create the panel.
	 */
	public PanelAdmin() {
		consu = null;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[]{58, 77, 92, 86};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0};
		setLayout(gridBagLayout);
		setBackground(Interfaz.primaryLight);
		
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout());
		panelBotones.setBorder(new TitledBorder(new LineBorder(Interfaz.textColor), "Opciones", TitledBorder.CENTER, TitledBorder.TOP, null, Interfaz.textColor));
		panelBotones.setBackground(Interfaz.primaryLight);
		panelBotones.setForeground(Interfaz.textColor);
		
		btnBorrar = FabBoton.construirBoton("Borrar");
		
		btnBorrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				textArea.setText("");
				
			}
		});
		
		
		textArea = new JTextArea();
		textArea.setText("Poner aqui sentencia SQL");
		textArea.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				textArea.setText("");
				
			}
		});
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 1;
		add(textArea, gbc_textArea);
		
		listNombreTablas = new JList<String>();
		listNombreTablas.setBorder(new TitledBorder(new LineBorder(Interfaz.textColor), "Nombre de las Tablas", TitledBorder.LEADING, TitledBorder.TOP, null, Interfaz.textColor));
		listNombreTablas.setBackground(Interfaz.fondo);
		listNombreTablas.setForeground(Interfaz.textColor);
		GridBagConstraints gbc_listNombreTablas = new GridBagConstraints();
		gbc_listNombreTablas.insets = new Insets(0, 0, 5, 0);
		gbc_listNombreTablas.fill = GridBagConstraints.BOTH;
		gbc_listNombreTablas.gridheight = 2;
		gbc_listNombreTablas.gridwidth = 1;
		gbc_listNombreTablas.weightx = 40;
		gbc_listNombreTablas.weighty = 50;
		gbc_listNombreTablas.gridx = 1;
		gbc_listNombreTablas.gridy = 2;
		
		
		
		
		add(new JScrollPane(listNombreTablas), gbc_listNombreTablas);
		
		listAtributosTabla = new JList<String>();
		listAtributosTabla.setBorder(new TitledBorder(new LineBorder(Interfaz.textColor), "Nombre de los Atributos", TitledBorder.LEADING, TitledBorder.TOP, null, Interfaz.textColor));
		listAtributosTabla.setBackground(Interfaz.fondo);
		listAtributosTabla.setForeground(Interfaz.textColor);
		GridBagConstraints gbc_listAtributosTabla = new GridBagConstraints();
		gbc_listAtributosTabla.fill = GridBagConstraints.BOTH;
		gbc_listAtributosTabla.gridheight = 2;
		gbc_listAtributosTabla.gridwidth = 1;
		gbc_listAtributosTabla.weightx = 40;
		gbc_listAtributosTabla.weighty = 50;
		gbc_listAtributosTabla.gridx = 1;
		gbc_listAtributosTabla.gridy = 0;
		add(new JScrollPane(listAtributosTabla), gbc_listAtributosTabla);
		
		
		listNombreTablas.addListSelectionListener(new ListSelectionListener() {
					
		@Override
		public void valueChanged(ListSelectionEvent e) {
			
			if (consu!=null) {
				
				String nombreTabla = listNombreTablas.getSelectedValue();
				
				consu.nombreAtributosTabla(listAtributosTabla, nombreTabla);
			}
		}
		});
		
		
		GridBagConstraints gbc_panelBotones = new GridBagConstraints();
		gbc_panelBotones.anchor = GridBagConstraints.NORTH;
		gbc_panelBotones.insets = new Insets(0, 0, 5, 5);
		gbc_panelBotones.gridx = 0;
		gbc_panelBotones.gridy = 0;
		gbc_panelBotones.weightx = 150.0;
		gbc_panelBotones.weighty = 6.0;
		gbc_panelBotones.fill = GridBagConstraints.HORIZONTAL;
		add(panelBotones, gbc_panelBotones);
		
			JButton botonConsulta = FabBoton.construirBoton("Realizar Consulta");
			 
			JButton botonLogin = FabBoton.construirBoton("Login Admin");
			botonLogin.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					boolean verificar = false;
					JPasswordField fieldPass = new JPasswordField();
					Object[] message = {
							"Password:", fieldPass
					};
					while (!verificar) {	
						int option = JOptionPane.showConfirmDialog(null, message, "Ingrese Numero y PIN", JOptionPane.OK_CANCEL_OPTION);
						if (option == JOptionPane.OK_OPTION) {	
							String pass  = new String(fieldPass.getPassword());
							if(pass.equals("admin")) {
								
					           verificar = true;
					           consu = new ConsultaAdmin();
				               botonConsulta.setEnabled(true);
				               JOptionPane.showMessageDialog(null, "Login Exitoso","Admin",JOptionPane.PLAIN_MESSAGE,null);
							  
				              consu.mostrarTablas(listNombreTablas);  
				              
							}
							else {
				        	   JOptionPane.showMessageDialog(null, "Contraseña incorrecta, ingresela nuevamente","Error Login", JOptionPane.ERROR_MESSAGE, null);
						       botonConsulta.setEnabled(false);
							}
						}
						else 
							verificar = true;
					}
					
				}
			});
		    panelBotones.add(botonLogin);
			
			botonConsulta.setEnabled(false);
			botonConsulta.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					String consulta = textArea.getText();
					if(!consulta.equals("")) {
						int estadoConsulta = consu.consultaAdmin(tableConsulta, consulta);
						if(estadoConsulta == 2) {
							JOptionPane.showMessageDialog(null, "Consulta exitosa", "Exito", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							 
							 if(estadoConsulta == 1 ) {
								 JOptionPane.showMessageDialog(null, "Sentencia Ejecutada", "Exito", JOptionPane.INFORMATION_MESSAGE);
							 }
							 else {
							 JOptionPane.showMessageDialog(null, "La consulta falló", "Error", JOptionPane.ERROR_MESSAGE);
							 }
							 
						 }
					}
					else {
						JOptionPane.showMessageDialog(null, "No se ingreso una consulta", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
				
					
					
				}
			});
			panelBotones.add(botonConsulta);
			
			panelBotones.add(btnBorrar);
		add(panelBotones, gbc_panelBotones);	
		
		
		tableConsulta =  new DBTable();
		tableConsulta.setEditable(false);
		tableConsulta.setBorder(new TitledBorder(new LineBorder(Interfaz.textColor), "Consulta", TitledBorder.CENTER, TitledBorder.TOP, null, Interfaz.textColor));
		tableConsulta.setBackground(Interfaz.fondo);
		tableConsulta.setForeground(Interfaz.textColor);
		
		GridBagConstraints gbc_tableConsulta = new GridBagConstraints();
		gbc_tableConsulta.insets = new Insets(0, 0, 0, 5);
		gbc_tableConsulta.fill = GridBagConstraints.BOTH;
		gbc_tableConsulta.gridheight = 2;
		gbc_tableConsulta.gridwidth = 1;
		gbc_tableConsulta.weightx = 60;
		gbc_tableConsulta.weighty = 100;
		gbc_tableConsulta.gridx = 0;
		gbc_tableConsulta.gridy = 2;
		add(tableConsulta, gbc_tableConsulta);
		
		
		
		
		
	}

}
