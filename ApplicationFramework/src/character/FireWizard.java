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
		
		super.loseMP(consumeMP);
		return DMG;

	}
	
	public void elementalWisdom()
	{
		double gainMP = 20;
		
		super.gainMP(gainMP);
	}
	
	public double ultimateStrike()
	{
		double DMG = 25;
		double consumeMP = 80;
		
		super.loseMP(consumeMP);
		return DMG;
	}
	
	// Habilidade única //
	
	public double trueFlames()
	{
		double TrueDMG = 15;
		double consumeMP = 60;
		
		super.loseMP(consumeMP);
		return TrueDMG;
	}

}