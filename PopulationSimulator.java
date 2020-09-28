//Deprecated

package defaultpackage;

public class PopulationSimulator {
	
	//returns false if SAMPLE is below zero
	 public static boolean returnPositive(int sample) {
	        boolean flag;
	        if(sample >= 0)
	            flag = true;
	        else
	            flag = false;
	        return flag;
	    }
	 	
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


