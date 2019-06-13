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
		
		super.loseMP(consumeMP);
		return DMG;

	}
	
	public void elementalWisdom()
	{
		double gainMP = 30;
		
		super.gainMP(gainMP);
	}
	
	public double ultimateStrike()
	{
		double DMG = 20;
		double consumeMP = 80;
		
		super.loseMP(consumeMP);
		return DMG;
	}
	
	// Habilidade única //
	
	public void intenseHealing()
	{
		double HP = 20;
		double consumeMP = 30;
		
		super.loseMP(consumeMP);
		super.gainHP(HP);
	}
	
	
}
