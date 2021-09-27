package co.edu.uptc.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import co.edu.uptc.modelo.GameManagement;

public class HandlingEvents implements ActionListener {
	
 	static final String ABOUT_US="Acerca de...";
 	static final String CONFIGURATION_PARAMETERS="Parámetros Configuración";
 	static final String NEW_GAME="Nuevo Juego";
 	static final String START_GAME= "Iniciar Juego";
 	static final String THROW_DICE="Jugador numero";
 	private MainWindow mainWindows;
 	private GameManagement gameManagement;
	
	public HandlingEvents(MainWindow mainWindows) {
		this.mainWindows=mainWindows;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		// TODO Auto-generated method stub
		switch (actionEvent.getActionCommand()) {
		case START_GAME:
			gameManagement= new GameManagement(mainWindows
					.getCbPlayersNumber()
					.getSelectedIndex()+1, mainWindows.getCbLevel()
					.getSelectedItem()
					.toString());
			mainWindows.getBtnStartGame().setEnabled(false);
			mainWindows.getCbPlayersNumber().setEnabled(false);
			mainWindows.getCbLevel().setEnabled(false);
			mainWindows.getBtnConfigurationParameters().setEnabled(false);
			mainWindows.getBtnAbout().setEnabled(true);
			break;
		case CONFIGURATION_PARAMETERS:
			String maxPlayers=JOptionPane.showInputDialog(null,"Ingrese la cantidad maxima de jugadores");
			boolean state=false;
			while (maxPlayers.isEmpty() || maxPlayers==null) {
				JOptionPane.showMessageDialog(null, "Debe ingresar un numero por lo menos ");
				maxPlayers=JOptionPane.showInputDialog(null,"Ingrese la cantidad maxima de jugadores");
			}
			while(state) {
				try {
					
					int maxplayers=(int) Double.parseDouble(maxPlayers);;
					gameManagement.setMaxNumberPlayers(maxplayers);
					gameManagement.setNumberPlayers(maxplayers);
					mainWindows.getCbPlayersNumber().removeAllItems();
					for (int i = 0; i < maxplayers; i++) {
						mainWindows.getCbPlayersNumber().addItem(i+1);
					}
					state=true;
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,"Lo ingresado no es un numero.Ingrese un numero valido" );
					maxPlayers=JOptionPane.showInputDialog(null,"Ingrese la cantidad maxima de jugadores");
					state=false;
				}
			}
			break;
		case NEW_GAME:
			gameManagement.endGame();
			mainWindows.getLbAdvancedPositionsNumber().setText("99");
			mainWindows.getLbPairsNumbers().setText("XXX-XX");
			mainWindows.getLbRemainingPositionsNumber().setText("99");
			mainWindows.getLbReturnsNumber().setText("99");
			mainWindows.getLbShiftPlayerNumber().setText("99");
			mainWindows.getLbWinnerNumber().setText("XXXXX-XXXXXXX");
			mainWindows.getCbLevel().setSelectedIndex(0);
			mainWindows.getCbPlayersNumber().setSelectedIndex(0);
			mainWindows.getCbPlayersNumber().addItem(new Object[] {1,2});
			
			mainWindows.getBtnStartGame().setEnabled(true);
			mainWindows.getCbPlayersNumber().setEnabled(true);
			mainWindows.getCbLevel().setEnabled(true);
			mainWindows.getBtnConfigurationParameters().setEnabled(true);
			mainWindows.getBtnAbout().setEnabled(true);
			
			break;
		case ABOUT_US:
			String mensaje="";
			JOptionPane.showMessageDialog(null, mensaje);
			break;
		case THROW_DICE:

			break;

		default:
			break;
		}
	}

}
