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
	private int type;
	protected final static int FIRE = 0;
	protected final static int ICE = 1;
	protected final static int EARTH = 2;
	
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
	 alem de permitir que removamos o metodo maxMP
	 da classe PlayState*/
	
	public double getMaxHP() {
		return maxHP;
	}
	
	public double getMaxMP() {
		return maxMP;
	}
	
	// Habilidades genéricas //
	
	public abstract void takeDamage(double DMG, int dmgType);
	
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
		this.HP = (int)(this.HP - DMG);
	}
	
	public void loseMP(double consumeMP)
	{
		this.MP = (int)(this.MP - consumeMP);
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
	
	public void passiveRegeneration(double mp)
	{
		this.MP = this.MP + mp;
		if(this.MP>getMaxMP()) {
			this.MP = getMaxMP();
		}
			
	}
	
	// ---------------------------------------- //
	
	// Gets //
	
	// ---------------------------------------- //
	
	public double getHP()
	{
		return (int)this.HP;
	}

	public double getMP()
	{
		return (int)this.MP;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	// ---------------------------------------- //
		
}
