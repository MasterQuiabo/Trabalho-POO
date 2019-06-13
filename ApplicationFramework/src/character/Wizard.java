package character;

public abstract class Wizard {
	private double maxHP;
	private double maxMP;
	private double HP;
	private double MP;
	private double fireResistance;
	private double iceResistance;
	private double earthResistance;
	private int turnsStunned;
	
	public Wizard(double HP, double MP, double fireResistance, double iceResistance, double earthResistance)
	{
		this.maxHP = HP;
		this.maxMP = MP;
		this.HP = HP;
		this.MP = MP;
		this.fireResistance = fireResistance;
		this.earthResistance = earthResistance;
		this.iceResistance = iceResistance;
		this.turnsStunned = 0;
	}
	
	/* Metodos para ver hp e mp maximos.
	 Assim podemos substituir metodos exaustivos que
	 testam o tipo de mago para lidar com hp e mp,
	 alem de permitir que removamos os metodos maxHP e maxMP
	 da classe PlayState*/
	
	public double getMaxHP() {
		return maxHP;
	}
	
	public double getMaxMP() {
		return maxMP;
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
	
	//Agora o passive regeneration que lida com os excessos de MP.
	
	public void passiveRegeneration()
	{
		this.MP = this.MP + 5.0;
		if(this.MP>getMaxMP()) {
			this.MP = getMaxMP();
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
