package character;

public abstract class Wizard {

	private double HP;
	private double MP;
	private double fireResistance;
	private double iceResistance;
	private double earthResistance;
	
	public Wizard(double fireResistance, double iceResistance, double earthResistance)
	{
		this.HP = 100;
		this.MP = 100;
		this.fireResistance = fireResistance;
		this.earthResistance = earthResistance;
		this.iceResistance = iceResistance;
	}
	
	public void loseHP(double DMG)
	{
		this.HP = this.HP - DMG;
	}
	
	public void loseMP(double consumeMP)
	{
		this.MP = this.MP - consumeMP;
	}
	
	public void passiveRegeneration()
	{
		this.MP = this.MP + 1.0;
	}
	
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
		
}
