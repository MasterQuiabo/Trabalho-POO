package character;

public class IceWizard extends Wizard{

	public IceWizard()
	{
		super(120, 80, 0.8, 1.0, 1.2);
	}
	
	public double elementalStrike()
	{
		double DMG = 5;
		double consumeMP = 25;
		
		if(super.getMP() >= 25)
		{
			super.loseMP(consumeMP);
			return DMG;
		}
		else
			return -1;

	}
	
	public void elementalWisdom()
	{
		double gainMP = 25;
		
		if((super.getMP() + 25) <= super.getMaxMP())
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
		double consumeMP = 60;
		
		if(super.getMP() >= 60)
		{
			super.loseMP(consumeMP);
			return DMG;
		}
		else
			return -1;
	}
	
	// Habilidade única //
	
	public int stunningBlow(){
		double consumeMP = 40;
		int turnsStunned = 1;

		if(super.getMP() >= 40)
		{
			super.loseMP(consumeMP);
			return turnsStunned;
		}
		else
			return -1;
		
	}
}
