package Wizards;

public class FireWizard extends Wizard{

	public FireWizard()
	{
		super();
		super.fireResistance = 1.0;
		super.earthResistance = 1.2;
		super.iceResistance = 0.8;
	}
	
	public double Elemental_Strike()
	{
		double DMG = 10;
		double consumeMP = 10;
		
		super.loseMP(consumeMP);
		return DMG;

	}
}
