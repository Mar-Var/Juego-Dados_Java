package co.edu.uptc.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MainWindow extends JFrame {
	
	JLabel lbTile;
	
	JPanel pnBodyProgram;
	
	JPanel pnConfiguration;
	JLabel lbPlayersNumber;
	JComboBox cbPlayersNumber;
	JLabel lbLevel;
	JComboBox cbLevel;
	JButton btnStartGame;
	
	JPanel pnResults;
	JLabel lbShiftPlayer;
	JLabel lbShiftPlayerNumber;
	JLabel lbAdvancedPositions;
	JLabel lbAdvancedPositionsNumber;
	JLabel lbRemainingPositions;
	JLabel lbRemainingPositionsNumber;
	JLabel lsReturns;
	JLabel lsReturnsNumber;

	JPanel pnComboButtons;
	JButton btnConfigurationParameters;
	JButton btnNewGame;
	JButton btnAbout;
	
	JPanel pnGameZone;
	JPanel pnComboImagen;
	JLabel lbIconImage1;
	JLabel lbIconImage2;
	JLabel lbScore;
	JButton btnThrowPlayer;
	
	JPanel pnPares;
	JLabel lbPairsNumbers;
	
	JPanel pnWinner;
	JLabel lbWinnerNumber;
	
	
	

	public MainWindow() {
		super("Rapidos y Furiosos");
		setSize( new Dimension(580,320));// Ajustar tamaño de ventana
		setDefaultCloseOperation(EXIT_ON_CLOSE);// Decir que pasa cuando se cierre la ventana
		//setResizable(false);// para decir que la ventana no se pueda cambiar de tamaño
		//Organizadores de contenido(flowLayout,Borderlayout,GridLayout. GridbagLayout)
		setLayout(new GridBagLayout());
	}
	
	public void begin() {
		createComponents();
		addComponents();
		
	}
	

	private void addComponents() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy=0;
		gbc.gridx=0;
		gbc.anchor=GridBagConstraints.CENTER;
		add(lbTile,gbc);
		
		gbc.gridy=1;
		add(pnBodyProgram,gbc);
		
	}
	private void addComponentsBodyProgram() {
		GridBagConstraints gbc = new GridBagConstraints();
		pnBodyProgram.add(arg0);
	}

	private void createComponents() {
		
		lbTile= new JLabel("Title");
		//---------------------------------------------------------------------------------
		pnBodyProgram= new JPanel();
		pnBodyProgram.setLayout(new GridBagLayout());
		//---------------------------------------------------------------------------------
		pnConfiguration= new JPanel();
		pnConfiguration.setPreferredSize(new Dimension(100,100));
		
		lbPlayersNumber = new JLabel("Número de Jugadores");
		//cbPlayersNumber;
		lbLevel = new JLabel("Nivel");
		cbLevel=new JComboBox<>(new Object[] {"Basico","Medio","alto"});
		btnStartGame= new JButton("Iniciar Juego");
		
		
		pnResults= new JPanel();
		pnResults.setPreferredSize(new Dimension(100,100));
		pnResults.setLayout(new GridBagLayout());
		
		
		lbShiftPlayer= new JLabel("Jugador Turno");
		lbShiftPlayerNumber= new JLabel();
		lbAdvancedPositions= new JLabel("Posiciones Avanzadas");;
		lbAdvancedPositionsNumber=new JLabel();;
		lbRemainingPositions= new JLabel("Posiciones Restantes");;
		lbRemainingPositionsNumber=new JLabel();;
		lsReturns= new JLabel("Retornos");;
		lsReturnsNumber =new JLabel();;
		
		
		pnComboButtons = new JPanel();
		pnComboButtons.setPreferredSize(new Dimension(100,100));
		pnComboButtons.setLayout(new GridBagLayout());
		btnConfigurationParameters=new JButton("Parámetros Configuración");
		btnNewGame=new JButton("Nuevo Juego");
		btnAbout=new JButton("Acerca de...");
		
		pnGameZone= new JPanel();
		pnGameZone.setPreferredSize(new Dimension(100,100));
		pnGameZone.setLayout(new GridBagLayout());
		pnComboImagen = new JPanel();
		pnComboImagen.setLayout(new GridBagLayout());
		lbIconImage1=new JLabel(new ImageIcon("img/."));
		lbIconImage2=new JLabel(new ImageIcon("img/."));;
		lbScore= new JLabel("");
		btnThrowPlayer= new JButton();// Necesario hacer cambios
		btnThrowPlayer.setName("nada");
		
		pnPares = new JPanel();
		pnPares.setPreferredSize(new Dimension(100,50));
		pnPares.setLayout(new GridBagLayout());
		lbPairsNumbers= new JLabel();
		
		
		pnWinner= new JPanel();
		pnWinner.setPreferredSize(new Dimension(100,50));
		lbWinnerNumber= new JLabel();
		
		
		
		
		
		
		
		
		
		
	}
	

}
