package Wizards;

public abstract class Wizard {

	private double HP;
	private double MP;
	private double fireResistance;
	private double iceResistance;
	private double earthResistance;
	
	protected Wizard(double fireResistance, double iceResistance, double earthResistance)
	{
		this.HP = 100;
		this.MP = 100;
		this.fireResistance = fireResistance;
		this.earthResistance = earthResistance;
		this.iceResistance = iceResistance;
	}
	
	protected void loseHP(double DMG)
	{
		this.HP = this.HP - DMG;
	}
	
	protected void loseMP(double consumeMP)
	{
		this.MP = this.MP - consumeMP;
	}
	
	protected void passiveRegeneration()
	{
		this.MP = this.MP + 20.0;
	}
	
	protected double getHP()
	{
		return this.HP;
	}
	
	protected double getMP()
	{
		return this.MP;
	}
	
	protected double getFireResistance()
	{
		return this.fireResistance;
	}
	
	protected double getEarthResistance()
	{
		return this.earthResistance;
	}
	
	protected double getIceResistance()
	{
		return this.iceResistance;
	}
		
}
