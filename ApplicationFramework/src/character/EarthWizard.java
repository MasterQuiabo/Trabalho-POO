package character;

public class EarthWizard extends Wizard{

	public EarthWizard()
	{
		super(80, 120, 1.2, 0.8, 1.0);
	}
	
	public double elementalStrike()
	{
		double DMG = 5;
		double consumeMP = 30;
		
		if(super.getMP() >= 30)
		{
			super.loseMP(consumeMP);
			return DMG;
		}
		else
			return -1;

	}
	
	public void elementalWisdom()
	{
		double gainMP = 30;
		
		if((super.getMP() + 30) <= super.getMaxMP())
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
		double DMG = 20;
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
	
	public int intenseHealing()
	{
		double HP = 20;
		double consumeMP = 30;
		
		if(super.getMP() >= 30)
		{
			super.loseMP(consumeMP);
			if((super.getHP() + 20) <= super.getMaxHP())
			{
				super.gainHP(HP);
			}
			else
				super.gainHP(super.getMaxHP() - super.getHP());	
		}
		else
			return -1;
		
		return 0;
		
	}
	
	
}
