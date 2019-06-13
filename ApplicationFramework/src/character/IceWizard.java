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
		
		super.loseMP(consumeMP);
		return DMG;

	}
	
	public void elementalWisdom()
	{
		double gainMP = 25;
		
		super.gainMP(gainMP);
	}
	
	public double ultimateStrike()
	{
		double DMG = 20;
		double consumeMP = 60;
		
		super.loseMP(consumeMP);
		return DMG;
	}
	
	// Habilidade única //
	
	public int stunningBlow(){
		double consumeMP = 40;
		int turnsStunned = 1;

		super.loseMP(consumeMP);
		return turnsStunned;
	}
}
