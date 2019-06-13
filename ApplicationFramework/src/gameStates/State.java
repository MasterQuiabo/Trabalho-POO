package gameStates;

public enum State {
	MENUSTATE, PLAYSTATE;
	
	public static State valueOf(int state) {
		if(state == 0)
			return State.MENUSTATE;
		else
			return State.PLAYSTATE;
	}
	
	public static int valueOf(State state) {
		if(state == State.MENUSTATE)
			return 0;
		else 
			return 1;
	}
}
