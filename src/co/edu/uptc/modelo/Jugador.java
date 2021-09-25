package co.edu.uptc.modelo;

public class Jugador {
	private int idPlayer;
	private int currentScore;
	private int remainingScore;
	private int diceScore[];
	private int returns;
	private int pairs;
	private int pointsLevel;
	
	public Jugador(int idPlayer,int remainingScore) {
		this.pointsLevel=remainingScore;
		this.idPlayer=idPlayer;
		this.currentScore=0;
		this.remainingScore=remainingScore;
		this.returns=0;
		this.pairs=0;
		this.diceScore=null;
	}
	public void addPair() {
		this.pairs+=1;
	}	
	public void addReturn() {
		this.returns+=1;
	}
	
	

	public int getPointsLevel() {
		return pointsLevel;
	}
	public void setPointsLevel(int pointsLevel) {
		this.pointsLevel = pointsLevel;
	}
	public int[] getDiceScore() {
		return diceScore;
	}


	public void setDiceScore(int[] diceScore) {
		this.diceScore = diceScore;
	}


	public int getIdPlayer() {
		return idPlayer;
	}

	public void setIdPlayer(int idPlayer) {
		this.idPlayer = idPlayer;
	}

	public int getCurrentScore() {
		return currentScore;
	}

	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}

	public int getRemainingScore() {
		return remainingScore;
	}

	public void setRemainingScore(int remainingScore) {
		this.remainingScore = remainingScore;
	}

	public int getReturns() {
		return returns;
	}

	public void setReturns(int returns) {
		this.returns = returns;
	}

	public int getPairs() {
		return pairs;
	}

	public void setPairs(int pairs) {
		this.pairs = pairs;
	}

	@Override
	public String toString() {
		return "Jugador [idPlayer=" + idPlayer + ", currentScore=" + currentScore + ", remainingScore=" + remainingScore
				+ ", returns=" + returns + ", pairs=" + pairs + "]";
	}
	
	
	
	

}
