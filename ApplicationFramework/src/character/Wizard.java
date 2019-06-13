package character;

public abstract class Wizard {

	private double HP;
	private double MP;
	private double fireResistance;
	private double iceResistance;
	private double earthResistance;
	private int turnsStunned;
	
	public Wizard(double HP, double MP, double fireResistance, double iceResistance, double earthResistance)
	{
		this.HP = HP;
		this.MP = MP;
		this.fireResistance = fireResistance;
		this.earthResistance = earthResistance;
		this.iceResistance = iceResistance;
		this.turnsStunned = 0;
	}
	
	// Habilidades genéricas //
	
	public abstract double elementalStrike();
	
	public abstract void elementalWisdom();
	
	public abstract double ultimateStrike();
	
	public void stun(int turnsStunned)
	{
		this.turnsStunned = this.turnsStunned + turnsStunned;
	}
	
	public void updateStun()
	{
		this.turnsStunned--;
	}

	// Métodos genéricos //
	
	// ---------------------------------------- //
	
	// Manipulação de HP/MP //
	
	public void loseHP(double DMG)
	{
		this.HP = this.HP - DMG;
	}
	
	public void loseMP(double consumeMP)
	{
		this.MP = this.MP - consumeMP;
	}
	
	public void gainMP(double MP)
	{
		this.MP = this.MP + MP;
	}
	
	public void gainHP(double HP)
	{
		this.HP = this.HP + HP;
	}
	
	public void passiveRegeneration()
	{
		this.MP = this.MP + 5.0;
	}
	
	public void passiveRegeneration(int overFlowType)
	{
		if(overFlowType == 1)
		{
			this.MP = 100;
		}
		else if(overFlowType == 2)
		{
			this.MP = 80;
		}
		else if(overFlowType == 3)
		{
			this.MP = 120;
		}
	}
	
	// ---------------------------------------- //
	
	// Gets //
	
	// ---------------------------------------- //
	
	public double getHP()
	{
		return this.HP;
	}
	
	public double getMP()
	{
		return this.MP;
	}
	
	public double getFireResistance()
	{
		return this.fireResistance;
	}
	
	public double getEarthResistance()
	{
		return this.earthResistance;
	}
	
	public double getIceResistance()
	{
		return this.iceResistance;
	}
	
	public int getTurnsStunned()
	{
		return this.turnsStunned;
	}
	
	// ---------------------------------------- //
		
}
