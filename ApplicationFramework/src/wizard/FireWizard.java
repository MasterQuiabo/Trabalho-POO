package wizard;

public class FireWizard extends Wizard{

	public FireWizard()
	{
		super(1.0, 1.2, 0.8);
	}
	
	public double elementalStrike()
	{
		double DMG = 10;
		double consumeMP = 10;
		
		super.loseMP(consumeMP);
		return DMG;

	}
	
}
