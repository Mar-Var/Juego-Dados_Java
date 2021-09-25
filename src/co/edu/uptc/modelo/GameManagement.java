package co.edu.uptc.modelo;

import java.util.ArrayList;
import java.util.Random;

public class GameManagement {
	private int initialShift;
	private String level;
	private int numberPlayers=2;
	private int currentTurn;
	private int dice01;
	private int dice02;
	private int score[]= {dice01,dice02};
	private Jugador winnerPlayer;
	
	private final int BASIC_LEVEL=30;
	private final int MEDIUM_LEVEL=30;
	private final int HARD_LEVEL=30;
	private boolean inGame=false;
	
	ArrayList<Jugador> jugador;
	// Seccion para inicio del juego
	public GameManagement (int numberPlayers,String level) {
		this.inGame=true;
		this.numberPlayers=numberPlayers;
		this.level=level;
		jugador= new ArrayList<>();
	}
	public boolean addPlayers() {
		
		for (int i = 0; i < this.numberPlayers; i++) {
			jugador.add(new Jugador(i+1, selectLevelScore(this.level)));
		}
		
		return jugador.size()==numberPlayers?true:false;
	}
	
	public int selectLevelScore(String level) {
		if(this.level.equals("Facil")) {
			return BASIC_LEVEL;
		}
		else if(this.level.equals("Medio")) {
			return MEDIUM_LEVEL;
			
		}
		else if(this.level.equals("Dificil")) {
			return HARD_LEVEL;		
		}
		return 0;
	}
	
	public void endGame() {
		jugador.clear();
		this.initialShift=0;
		this.level="";
	}
	public void selectFirstShift() {
		this.initialShift=new Random().nextInt(jugador.size())+1;
		this.currentTurn=this.initialShift;
	}
	
	// Finaliza la seccion del inicio del juego
	
	// Inicia la seccion estando en juego.
	
	// Este metodo controlara absolutamente todo el proceso de los turnos y las reglas de el juego
	public void turningPlayers () {
			if(currentTurn>jugador.size()) {
				this.currentTurn=1;
			}
			this.currentTurn++;
	}
	// lanzamiento de dados
	public int[] throwDice(){
		this.dice01= new Random().nextInt(6)+1;
		this.dice02= new Random().nextInt(6)+1;
		return score;
	}
	// Sumar dados y validar el score
	public boolean addScore(int score[]) {
		jugador.get(currentTurn-1).setDiceScore(score);
		if(score[0]==1 && score[1]==1) {
			return 7-jugador.get(currentTurn-1).getRemainingScore()<0?false:true;
		}
		return score[0]+score[1]-jugador.get(currentTurn-1).getRemainingScore()<0?false:true;
	}
	// A�adir el score
	public boolean addScore() {
		int amountAdd=0;
		if(addScore(throwDice())) {
			if(score[0]==1 && score[1]==1) {
				amountAdd=7;
				jugador.get(currentTurn-1).setCurrentScore(amountAdd);
				jugador.get(currentTurn-1).setRemainingScore(jugador.get(currentTurn-1).getRemainingScore()-amountAdd);;
				return true;
			}
			amountAdd=score[0]+score[1];
			jugador.get(currentTurn-1).setCurrentScore(amountAdd);
			jugador.get(currentTurn-1).setRemainingScore(jugador.get(currentTurn-1).getRemainingScore()-amountAdd);;
			return true;
		}
		return false;
	}
	
	//Metodo que retorna la cantidad de pares
	public int addShowPair() {
		if(score[0]==score[1]) {
			jugador.get(currentTurn-1).addPair();;
			return jugador.get(currentTurn-1).getPairs();
		}
		else {
			jugador.get(currentTurn-1).setPairs(0);
			return jugador.get(currentTurn-1).getPairs();	
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
			findPalyerWhitSameScore(this.score).setRemainingScore(findPalyerWhitSameScore(score).getPointsLevel());;
			findPalyerWhitSameScore(this.score).setCurrentScore(0);
		}
		return true;
	}
	// Este metodo me permite saber si ya se cumplieron tres pares y finaliza 
	public boolean isThreePair() {
		if(jugador.get(currentTurn-1).getPairs()==3) {
			this.winnerPlayer=jugador.get(currentTurn-1);
			this.inGame=false;
			return true;
		}
		return false;
	}
	
	public boolean winByPoints() {
		if(jugador.get(currentTurn-1).getRemainingScore()==0) {
			this.winnerPlayer=jugador.get(currentTurn-1);
			inGame=false;
			return true;
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	public int getInitialShift() {
		return initialShift;
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
