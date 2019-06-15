package character;

public class FireWizard extends Wizard{

	public FireWizard()
	{
		super(100, 100, 1.0, 0.8, 1.2);
		super.setType(FIRE);
	}
	
	public double elementalStrike()
	{
		double DMG = 20;
		double consumeMP = 30;
		
		if(super.getMP() >= consumeMP)
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
		
		if((super.getMP() + gainMP) <= super.getMaxMP())
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
		double DMG = 35;
		double consumeMP = 75;
		
		if(super.getMP() >= consumeMP)
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
		double TrueDMG = 40;
		double consumeMP = 80;
		
		if(super.getMP() >= consumeMP)
		{
			super.loseMP(consumeMP);
			return TrueDMG;
		}
		else
			return -1;
		
	}
	
	public void takeDamage(double DMG, int dmgType) {
		
		switch(dmgType) {
			case FIRE:
				DMG*=super.getFireResistance();
				break;
			case ICE:
				DMG*=super.getIceResistance();
				break;
			case EARTH:
				DMG*=super.getEarthResistance();
				break;
		}
		
		super.loseHP(DMG);
	}

}