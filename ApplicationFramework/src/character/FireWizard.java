package character;

public class FireWizard extends Wizard{

	public FireWizard()
	{
		super(100, 100, 1.0, 0.8, 1.2);
	}
	
	public double elementalStrike()
	{
		double DMG = 7.5;
		double consumeMP = 35;
		
		if(super.getMP() >= 35)
		{
			super.loseMP(consumeMP);
			return DMG;
		}
		else
			return -1;
	}
	
	public void elementalWisdom()
	{
		double gainMP = 20;
		
		if((super.getMP() + 20) <= super.getMaxMP())
		{
			super.gainMP(gainMP);
		}
		else
		{
			super.gainMP(super.getMaxMP() - super.getMP());
		}
		
	}
	
	public double ultimateStrike()
	{
		double DMG = 25;
		double consumeMP = 80;
		
		if(super.getMP() >= 80)
		{
			super.loseMP(consumeMP);
			return DMG;
		}
		else
			return -1;
		
	}
	
	// Habilidade única //
	
	public double trueFlames()
	{
		double TrueDMG = 15;
		double consumeMP = 60;
		
		if(super.getMP() >= 60)
		{
			super.loseMP(consumeMP);
			return TrueDMG;
		}
		else
			return -1;
		
	}

}