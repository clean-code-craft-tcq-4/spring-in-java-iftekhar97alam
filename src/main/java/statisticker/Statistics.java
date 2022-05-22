package statisticker;

import java.util.Collections;
import java.util.List;

public class Statistics 
{
	public static class Stats{
		float min = Float.NaN;
		float max = Float.NaN;;
		float average = Float.NaN;;
		
		Stats(List<Float> numbers){
			if(!numbers.isEmpty())
			{
				this.min = Collections.min(numbers);
				this.max = Collections.max(numbers);
				this.average = getAverage(numbers);
			}
		}
		
		private Float getAverage(List<Float> numbers){
			Float sum=0.0f;
			for (Float number : numbers) {
		        sum += number;
		    }
			return sum/numbers.size();
		}
	}
    public static Stats getStatistics(List<Float> numbers) {
    	return new Stats(numbers);
    }
}

interface IAlerter{
	public void setAlert(boolean bool);
}

class EmailAlert implements IAlerter{
	boolean emailSent = false;
	@Override
	public void setAlert(boolean bool) {
		this.emailSent = bool;
	}
}

class LEDAlert implements IAlerter{
	boolean ledGlows = false;
	@Override
	public void setAlert(boolean bool) {
		this.ledGlows = bool;
	}
}

class StatsChecker{
	private float max;
	private IAlerter[] alerters;
	StatsChecker(float max, IAlerter[] alerters){
		this.max = max;
		this.alerters = alerters;
	}
	
	public void checkAndAlert(List<Float> numbers){
		for (Float number : numbers) {
	        if(number > max){
	        	for(IAlerter alerter: alerters)
	        		alerter.setAlert(true);
	        	return;
	        }
	    }
	}
}
