package character;

public class EarthWizard extends Wizard{
	
	private int livingArmor;
	
	public EarthWizard()
	{
		super(80, 120, 1.2, 0.8, 1.0);
		livingArmor = 1;
		super.setType(EARTH);
	}
	
	public double elementalStrike()
	{
		double DMG = 12;
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
		double gainMP = 35;
		
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
		double DMG = 30;
		double consumeMP = 80;
		
		if(super.getMP() >= consumeMP)
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
		double HP = 40;
		double consumeMP = 70;
		
		if(super.getMP() >= consumeMP)
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
	
	public void takeDamage(double DMG, int dmgType) {
		DMG=DMG-getLivingArmor();
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

	public int getLivingArmor() {
		return livingArmor;
	}

	public void setLivingArmor(int livinArmor) {
		this.livingArmor = livinArmor;
	}
	
}
