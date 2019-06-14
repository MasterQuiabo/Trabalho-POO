package gameStates;

public enum State {
	MENUSTATE, PLAYSTATE, CHARACTERSELECTIONSTATE;
	
	public static State valueOf(int state) {
		if(state == 0)
			return State.MENUSTATE;
		else if(state == 1)
			return State.CHARACTERSELECTIONSTATE;
		else 
			return State.PLAYSTATE;
	}
	
	public static int valueOf(State state) {
		if(state == State.MENUSTATE)
			return 0;
		else if(state == State.CHARACTERSELECTIONSTATE)
			return 1;
		else 
			return 2;
	}
}
