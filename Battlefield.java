package Wizards;
import java.util.Scanner;
import java.util.Random;


public class Battlefield {

	private static int wizardTurn = 1;
	
	public static void main(String[] args)
	{
		
		System.out.println("Escolha seu mago");
		System.out.println("1 - Mago de fogo");
		System.out.println("2 - Mago de gelo");
		System.out.println("3 - Mago de terra");

		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		
		if(input == 1)
		{
			FireWizard userWizard = new FireWizard();
			
			Random random = new Random();
			int x = random.nextInt(3);
			
			if(x == 0)
			{
				FireWizard computerWizard = new FireWizard();
			}
			else if(x == 1)
			{
				IceWizard computerWizard = new IceWizard();
			}
			else if(x == 2)
			{
				EarthWizard computerWizard = new EarthWizard();
			}
			
			startShowdown();
		}
		else if(input == 2)
		{
			IceWizard userWizard = new IceWizard();
			
			Random random = new Random();
			int x = random.nextInt(3);
			
			if(x == 0)
			{
				FireWizard computerWizard = new FireWizard();
			}
			else if(x == 1)
			{
				IceWizard computerWizard = new IceWizard();
			}
			else if(x == 2)
			{
				EarthWizard computerWizard = new EarthWizard();
			}
			
			startShowdown();
		}
		if(input == 3)
		{
			EarthWizard userWizard = new EarthWizard();
			
			Random random = new Random();
			int x = random.nextInt(3);
			
			if(x == 0)
			{
				FireWizard computerWizard = new FireWizard();
			}
			else if(x == 1)
			{
				IceWizard computerWizard = new IceWizard();
			}
			else if(x == 2)
			{
				EarthWizard computerWizard = new EarthWizard();
			}
			
			startShowdown();
			
		}
	}
	
	private static void startShowdown()
	{
		
	}

}
