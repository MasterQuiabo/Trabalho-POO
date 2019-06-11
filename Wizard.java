package Wizards;

public abstract class Wizard {

	protected double HP;
	protected double MP;
	protected double fireResistance;
	protected double iceResistance;
	protected double earthResistance;
	
	protected Wizard()
	{
		this.HP = 100;
		this.MP = 100;
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
		
}
