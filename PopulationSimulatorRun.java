package defaultpackage;

/*
 * Creator: Nate Alberti
 * Date: 9/20
 * Description: Creates a farm and asks the user questions
 * about population size, growth, time, etc. It provides
 * information based on what the user gives it, using
 * mathematical models.
 */

import java.util.Scanner;

public class PopulationSimulatorRun {
	public static void main(String args[]) {
		int capacity = 0;
		int initialPop = 0;
		double growthRate = 0;
        boolean loopQuestions = false;
        Scanner scan = new Scanner(System.in);
        System.out.println("---Bunny farm simulator---");
        System.out.println("\n-New farm-");
        
        //Asking for carrying capacity
        System.out.println("Carrying capacity:");
        capacity = scan.nextInt();
        while(!(PopulationSimulatorRun.returnPositive(capacity))) {
        	System.out.println("Please enter a positive number!");
        	capacity = scan.nextInt();
        }
        
        do {
        //Asking for the initial population
        System.out.println("Initial population: ");
        initialPop = scan.nextInt();
        while(!(PopulationSimulatorRun.returnPositive(initialPop))) {
        	System.out.println("Please enter a positive number!");
        	initialPop = scan.nextInt();
        }
        
        //Calculating for the growth rate
        System.out.println("Births per month: ");
        int births = scan.nextInt();
        while(!(PopulationSimulatorRun.returnPositive(births)) && births != 0) {
        	System.out.println("Please enter a positive/nonzero number!");
        	births = scan.nextInt();
        }
        System.out.println("Deaths per month: ");
        int deaths = scan.nextInt();
        while(!(PopulationSimulatorRun.returnPositive(deaths))) {
        	System.out.println("Please enter a positive number!");
        	deaths = scan.nextInt();
        }
        int newBunnies = births - deaths;
        growthRate = (double) newBunnies/initialPop;
        
        
        //Asking the question
        System.out.println("\n\nSelect a question:");
        System.out.println("\t(1) How many bunnies will I have after x months?");
        System.out.println("\t(2) How many months will I have to wait to get x bunnies?");
        int userIN = scan.nextInt();
        //Verifying the user inputs either a 1 or a 2
        while(!(userIN == 1 || userIN == 2)) {
        	System.out.println("You screwed up the intput!\nONLY enter a 1 or a 2: ");
        	userIN = scan.nextInt();
        }
        
        //Switch to cruise through the options
        switch(userIN) {
        //How many bunnies will remain?
        case 1:
        	System.out.println("Number of months: ");
        	int months = scan.nextInt();
        	while(!(PopulationSimulator.returnPositive(months))) {
            	System.out.println("Please enter a positive number!");
            	months = scan.nextInt();
            }
        	int bunniesRemaining = PopulationSimulatorRun.bunniesRemaining(initialPop, growthRate, capacity, months);
        	System.out.println("\tAt a growth rate of " + growthRate*100 + "%, after " + months + 
        			" months there will be " + bunniesRemaining + " bunnies in the farm");
        	if(bunniesRemaining > capacity)
        		System.out.println("\tWarning: this is above the carrying capacity!");
        	break;
        //How many months to have x bunnies?
        case 2:
        	System.out.println("Population goal: ");
        	int goal = scan.nextInt();
        	int numOfMonths = PopulationSimulatorRun.monthsUntilPop(initialPop, growthRate, capacity, goal);
        	System.out.println("\tAt a growth rate of " + growthRate*100 + "%, it will take " + numOfMonths + 
        			" months to reach " + goal + " number of bunnies on your farm");
        	if(goal > capacity)
        		System.out.println("\tWarning: this is above the carrying capacity!");
        	break;
        //Wrong input, defaults
        default:
        	System.out.println("How'd you get here?");
        	break;
        }
        
        //Asking if the user wants to ask another question
        loopQuestions = false;
        System.out.println("Would you like to answer another question regarding this farm? (y/n): ");
        String userLoopQuestions = scan.next();
        if(!(userLoopQuestions.equalsIgnoreCase("y") || userLoopQuestions.equalsIgnoreCase("n"))) {
        	System.out.println("Please only enter a Y or a N: ");
        	userLoopQuestions = scan.nextLine();
        }
        else if(userLoopQuestions.equalsIgnoreCase("y"))
        	loopQuestions = true;
        } while(loopQuestions);
        
        System.out.println("Program complete.");
        scan.close();
    }
	
	//***METHODS***//
	
		//returns the number of bunnies remaining after x of months
		public static int bunniesRemaining(int initialPop, double growthRate, int capacity, int months){
		        int remaining = initialPop;
		        double decimalPop = (double) initialPop/capacity;
		        for(int i = 0; i < months; i++) {
		            decimalPop += growthRate*decimalPop;
		        }
		        double exact = (double) initialPop*decimalPop;
		        remaining = (int) (Math.round(exact));
		        return remaining;
		    } 
	
	
		//returns false if SAMPLE is below zero
		public static boolean returnPositive(int sample) {
		        boolean flag;
		        if(sample >= 0)
		            flag = true;
		        else
		            flag = false;
		        return flag;
		}
		
		
		//returns the number of months until a specified population is reached ("goal")
		public static int monthsUntilPop(int initialPop, double growthRate, int capacity, int goal) {
			 double decimalPop = (double) initialPop/capacity;
			 double decimalGoal = (double) goal/capacity;
			 int months = 0;
			 while(decimalPop <= decimalGoal) {
				 decimalPop += growthRate*decimalPop;
				 months++;
			 }
			 return months;
		}
}
