package co.edu.uptc.modelo;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class GameManagement {
	private int initialShift;
	private String level;
	private int numberPlayers=2;
	private int currentTurn;
	private int score[]= new int[2];
	private Jugador winnerPlayer;
	
	private final int BASIC_LEVEL=20;
	private final int MEDIUM_LEVEL=30;
	private final int HARD_LEVEL=50;
	private boolean inGame;
	private ImageIcon imgDado1;
	private ImageIcon imgDado2;
	private ImageIcon imgDado3;
	private ImageIcon imgDado4;
	private ImageIcon imgDado5;
	private ImageIcon imgDado6;
	private ImageIcon gifDado1;
	private ImageIcon gifDado2;
	ArrayList<ImageIcon> arrimgs;
	private int MaxNumberPlayers=2;
	ArrayList<Jugador> jugador;
	
	// Seccion para inicio del juego
	
	public GameManagement () {
		this.inGame=true;
		this.currentTurn=0;
		addDadeImgs();

		
	}
	public void gameInit(int numberPlayers,String level) {
		this.inGame=true;
		jugador= new ArrayList<>();
		this.numberPlayers=numberPlayers;
		this.level=level;
		this.numberPlayers=numberPlayers;
		System.out.println(jugador.size()+"asdasd");
		
		addPlayers();
		
		this.currentTurn=(int)(Math.random()*(jugador.size())+1);
		
	}
	
	public void addDadeImgs() {
		arrimgs= new ArrayList<>();
		arrimgs.add(new ImageIcon(new ImageIcon("img/dado1.jpg").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		arrimgs.add(new ImageIcon(new ImageIcon("img/dado2.jpg").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		arrimgs.add(new ImageIcon(new ImageIcon("img/dado3.jpg").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		arrimgs.add(new ImageIcon(new ImageIcon("img/dado4.jpg").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		arrimgs.add(new ImageIcon(new ImageIcon("img/dado5.jpg").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		arrimgs.add(new ImageIcon(new ImageIcon("img/dado6.jpg").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		gifDado1=new ImageIcon(new ImageIcon("img/gifdado.gif").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		gifDado2=new ImageIcon(new ImageIcon("img/gifdado2.gif").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		
	}
	
	public boolean addPlayers() {
		
		for (int i = 0; i < this.numberPlayers; i++) {
			jugador.add(new Jugador(i+1, selectLevelScore(this.level)));
		}
		
		return jugador.size()==numberPlayers?true:false;
	}
	
	public int selectLevelScore(String level) {
		if(this.level.equals("Básico")) {
			return BASIC_LEVEL;
		}
		else if(this.level.equals("Medio")) {
			return MEDIUM_LEVEL;
			
		}
		else if(this.level.equals("Alto")) {
			return HARD_LEVEL;		
		}
		return 0;
	}
	
	public void endGame() {
		this.jugador.clear();
		this.initialShift=0;
		this.level="";
		this.currentTurn=0;
	}
	// Finaliza la seccion del inicio del juego
	
	// Inicia la seccion estando en juego.
	
	// Este metodo ejecuta el funionamiento completo del juego
	public void addImgDateGUI(JLabel img1,JLabel img2) {
		System.out.println("addIGgDiceGUI");
		for (int i = 0; i < 6 ; i++) {
			if(score[0]==i+1) img1.setIcon(arrimgs.get(i));
			if(score[1]==i+1) img2.setIcon(arrimgs.get(i));
		}

	}
	public boolean playGame(JLabel lbplayershift,JLabel lbadvancesPositions
			,JLabel lbRemainingPositions,JLabel lbScore,JLabel lbpairNumbers
			,JLabel lbWinnerNumber,JButton btnShiftPlayer,JLabel lbImage1
			,JLabel lbImage2,JLabel lbReturns, JButton btnConfigure, JButton btnStartGame) {

		
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("PlayGame");
		throwDice();
		System.out.println(inGame);
		System.out.println(addScore(score));
		if (addScore(score) && inGame) {
			returnPlayer();
			addPair();
			addScore();
			System.out.println("Muerte");
			if(winByPoints(lbWinnerNumber,btnShiftPlayer,btnConfigure,btnStartGame)) {
				addImgDateGUI(lbImage1, lbImage2);
		    	lbplayershift.setText(String.valueOf(currentTurn));
		    	System.out.println(currentTurn);
		    	System.out.println(jugador.get(currentTurn-1).getIdPlayer());
		    	lbadvancesPositions.setText(String.valueOf(jugador.get(currentTurn-1).getCurrentScore()));
		    	lbRemainingPositions.setText(String.valueOf(jugador.get(currentTurn-1).getRemainingScore()));
		    	lbScore.setText(String.valueOf(score[0]+score[1]));
		    	lbReturns.setText(String.valueOf(jugador.get(currentTurn-1).getReturns()));
		    	lbpairNumbers.setText(String.valueOf(jugador.get(currentTurn-1).getPairs()));
		    	lbWinnerNumber.setText(String.valueOf(winnerPlayer==null
		    			?"XXXXXX-XX":jugador.get(currentTurn-1).getIdPlayer()));
				btnShiftPlayer.setEnabled(false);
				btnConfigure.setEnabled(false);
				btnStartGame.setEnabled(false);
				return false;
			}

			btnShiftPlayer.setEnabled(false);
			if(inGame) {
				waitToContinueNextShift(lbplayershift, lbadvancesPositions, lbRemainingPositions
						, lbScore, lbpairNumbers, lbWinnerNumber,btnShiftPlayer,lbImage1,lbImage2,lbReturns);
			//	turningPlayers();
			//	btnShiftPlayer.setLabel("Jugador Numero "+currentTurn);

			}				btnShiftPlayer.setEnabled(true);
			return true;
		}
		return false;
	}


	public void waitToContinueNextShift(JLabel lbplayershift,JLabel lbadvancesPositions
			,JLabel lbRemainingPositions,JLabel lbScore,JLabel lbpairNumbers,JLabel lbWinnerNumber
			,JButton btnThrowPlayer,JLabel lbImage1,JLabel lbImage2,JLabel lbResturns){
		
		System.out.println("StarTimer");
       
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int tic=1;
            @Override
            public void run() {       
                if(tic==1){
           
            			addImgDateGUI(lbImage1, lbImage2);
                    	lbplayershift.setText(String.valueOf(currentTurn));
                    	System.out.println(currentTurn);
                    	System.out.println(jugador.get(currentTurn-1).getIdPlayer());
                    	lbadvancesPositions.setText(String.valueOf(jugador.get(currentTurn-1).getCurrentScore()));
                    	lbRemainingPositions.setText(String.valueOf(jugador.get(currentTurn-1).getRemainingScore()));
                    	lbScore.setText(String.valueOf(score[0]+score[1]));
                    	lbResturns.setText(String.valueOf(jugador.get(currentTurn-1).getReturns()));
                    	lbpairNumbers.setText(String.valueOf(jugador.get(currentTurn-1).getPairs()));
                    	lbWinnerNumber.setText(String.valueOf(winnerPlayer==null
                    			?"XXXXXX-XX":jugador.get(currentTurn-1).getIdPlayer()));
                    	btnThrowPlayer.setEnabled(false);
                	/*else {
                    	lbplayershift.setText(String.valueOf(currentTurn));
                    	lbadvancesPositions.setText(String.valueOf(jugador.get(currentTurn-1).getCurrentScore()));
                    	lbRemainingPositions.setText(String.valueOf(jugador.get(currentTurn-1).getRemainingScore()));
                    	lbScore.setText(String.valueOf(score[0]+score[1]));
                    	lbpairNumbers.setText(String.valueOf(jugador.get(currentTurn-1).getPairs()));
                    	lbWinnerNumber.setText(String.valueOf(winnerPlayer==null
                    			?jugador.get(currentTurn-1).getIdPlayer():"XXXXXX-XX"));
                	}*/
                }else{
                	btnThrowPlayer.setEnabled(true);
        			turningPlayers();
        			btnThrowPlayer.setLabel("Jugador Numero "+currentTurn);
    				lbImage1.setIcon(gifDado1);
    				lbImage2.setIcon(gifDado2);
                    timer.cancel();
                }
              tic--;
            }
            
        };
        timer.schedule(task,0,3000);  
		System.out.println("EndTimer");
      }
	
	// Forma ciclica de los turnos
	public void turningPlayers () {
		System.out.println("TurningPlayers");
		
			if(score[0]==score[1]) {
			}else {
				this.currentTurn++;
			}

			if(currentTurn>jugador.size()) {
				this.currentTurn=1;
			}
			
	}
	
	
	// lanzamiento de dados
	public int[] throwDice(){
		this.score[0]= new Random().nextInt(6)+1;
		this.score[1]= new Random().nextInt(6)+1;
		return score;
	}
	public boolean isPair() {
		System.out.println("Ispair--------------------------------");
		return score[0]==score[1]?true:false;
		
	}
	
	// Sumar dados y validar el score
	public boolean addScore(int score[]) {
		jugador.get(currentTurn-1).setDiceScore(score);
		if(score[0]==1 && score[1]==1) {
			return jugador.get(currentTurn-1).getRemainingScore()-7<0?false:true;
		}else {
			return true;
			//return jugador.get(currentTurn-1).getRemainingScore()-score[0]-score[1]<0?false:true;
		}
		
	}
	
	// Añadir el score
	public boolean addScore() {
		System.out.println("addScoreDefinitve");
		int amountAdd=0;
		if(addScore(score)) {
			if(score[0]==1 && score[1]==1) {
				amountAdd=7;
				jugador.get(currentTurn-1).setCurrentScore(amountAdd+jugador.get(currentTurn-1).getCurrentScore());
				jugador.get(currentTurn-1).setRemainingScore(jugador.get(currentTurn-1).getRemainingScore()-amountAdd);;
				return true;
			}else {
				amountAdd=score[0]+score[1];
				jugador.get(currentTurn-1).setCurrentScore(amountAdd+jugador.get(currentTurn-1).getCurrentScore());
				jugador.get(currentTurn-1).setRemainingScore(jugador.get(currentTurn-1).getRemainingScore()-amountAdd);;
				return true;
			}

		}
		return false;
	}
	
	//Metodo que retorna la cantidad de pares
	public boolean addPair() {

		if(score[0]==score[1]) {
			jugador.get(currentTurn-1).addPair();;
			System.out.println("AddPair");
			return true;
		}
		else {
			jugador.get(currentTurn-1).setPairs(0);
			System.out.println("NotAddPair");
			return false;
		}	 
	}
	
	// Este metodo es para buscar si hay jugadores que tienen el mismo escore
	public Jugador findPalyerWhitSameScore(int score[]) {
		int suma = score[0]+score[1];
		return jugador.stream().filter((jugadorAux)->jugadorAux.getCurrentScore()==suma).findFirst().map(jugardorAux->jugardorAux).orElse(null);
	}
	// Regresar a cero el marcador de jugadores de la misma posicion
	
	public boolean returnPlayer() {
		
		while(findPalyerWhitSameScore(this.score)!=null) {
			findPalyerWhitSameScore(this.score).setRemainingScore(findPalyerWhitSameScore(score).getPointsLevel());;// Restaura la cantidad de puntos que le hacen falta
			findPalyerWhitSameScore(this.score).addReturn();// Añade un retorno 
			findPalyerWhitSameScore(this.score).setCurrentScore(0);// Restaura los puntos a cero
		}
		return true;
	}
	// Este metodo me permite saber si ya se cumplieron tres pares y finaliza 
	public boolean isThreePair() {
		System.out.println("Threepair");
		
		if(jugador.get(currentTurn-1).getPairs()==3) {
			this.winnerPlayer=jugador.get(currentTurn-1);
			this.inGame=false;
			return true;
		}
		return false;
	}
	
	public boolean winByPoints(JLabel lbWinnerNumber,JButton lbthrowPlayer, JButton lbConfiguration,JButton lbStartGame) {
		System.out.println("WinByPoints");
		if(jugador.get(currentTurn-1).getRemainingScore()<=0) {
			this.winnerPlayer=jugador.get(currentTurn-1);
			System.out.println("Ya hay un ganador"+winnerPlayer.getCurrentScore()+"   "+winnerPlayer.getIdPlayer());
			System.out.println();
			this.inGame=false;
			return true;
		}
		else {
			this.inGame=true;
			return false;
		}

	}
	
	
	public int getInitialShift() {
		return initialShift;
	}
	public int getCurrentTurn() {
		return currentTurn;
	}
	public void setCurrentTurn(int currentTurn) {
		this.currentTurn = currentTurn;
	}
	public int[] getScore() {
		return score;
	}
	public void setScore(int[] score) {
		this.score = score;
	}
	public Jugador getWinnerPlayer() {
		return winnerPlayer;
	}
	public void setWinnerPlayer(Jugador winnerPlayer) {
		this.winnerPlayer = winnerPlayer;
	}
	public int getMaxNumberPlayers() {
		return MaxNumberPlayers;
	}
	public void setMaxNumberPlayers(int maxNumberPlayers) {
		MaxNumberPlayers = maxNumberPlayers;
	}
	public void setInitialShift(int initialShift) {
		this.initialShift = initialShift;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getNumberPlayers() {
		return numberPlayers;
	}
	public void setNumberPlayers(int numberPlayers) {
		this.numberPlayers = numberPlayers;
	}
	public ArrayList<Jugador> getJugador() {
		return jugador;
	}
	public void setJugador(ArrayList<Jugador> jugador) {
		this.jugador = jugador;
	}
	public int getBASIC_LEVEL() {
		return BASIC_LEVEL;
	}
	public int getMEDIUM_LEVEL() {
		return MEDIUM_LEVEL;
	}
	public int getHARD_LEVEL() {
		return HARD_LEVEL;
	}
	
	

}
