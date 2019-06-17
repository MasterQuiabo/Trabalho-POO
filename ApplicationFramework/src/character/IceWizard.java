package character;

public class IceWizard extends Wizard{

	public IceWizard()
	{
		super(120, 80, 0.8, 1.0, 1.2);
		super.setType(ICE);
	}
	
	public double elementalStrike()
	{
		double DMG = 15;
		double consumeMP = 25;
		
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
		double gainMP = 30;
		
		passiveRegeneration(gainMP);
	}
	
	public double ultimateStrike()
	{
		double DMG = 40;
		double consumeMP = 60;
		
		if(super.getMP() >= consumeMP)
		{
			super.loseMP(consumeMP);
			return DMG;
		}
		else
			return -1;
	}
	
	// Habilidade única //
	
	public int stunningBlow(){
		double consumeMP = 60;
		int turnsStunned = 2;

		if(super.getMP() >= consumeMP)
		{
			super.loseMP(consumeMP);
			return turnsStunned;
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
