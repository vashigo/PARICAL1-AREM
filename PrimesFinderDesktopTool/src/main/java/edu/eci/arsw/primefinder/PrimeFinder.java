package edu.eci.arsw.primefinder;

import edu.eci.arsw.math.MathUtilities;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class PrimeFinder{
        
        private static final List<PrimeFinderThird> primeThirds = new LinkedList<>();
	public static void findPrimes(BigInteger _a, BigInteger _b, PrimesResultSet prs, int threads) throws InterruptedException{
                
                BigInteger a=_a;
                BigInteger b=_b;
                int numTot = b.intValue()-a.intValue();
                int range = numTot / threads;
                BigInteger ini;
                BigInteger fin;
                for(int i=0; i<threads;i++){
                    if(i==threads-1){
                        ini = BigInteger.valueOf(a.intValue()+range*i);
                        primeThirds.add(new PrimeFinderThird(ini,b, prs));
                        primeThirds.get(i).start();
                    }else{
                        ini = BigInteger.valueOf(a.intValue()+range*i);
                        fin = BigInteger.valueOf(a.intValue()+range*(i+1));
                        primeThirds.add(new PrimeFinderThird(ini,fin, prs));
                        primeThirds.get(i).start();
                    }
                    
                }
                
                for(PrimeFinderThird i:primeThirds){
                    i.join();
                }  
	}
        
        public static void pause(){
            for(PrimeFinderThird i:primeThirds){
                i.pause();
            }
        }
        
        public static void inicie(){
            for(PrimeFinderThird i:primeThirds){
                i.inicie();
            }
        }
	
        public static boolean isRunning(){
            boolean running = false;
            for(PrimeFinderThird i:primeThirds){
                if(i.isRunning()){
                    running = true;
                }
            }
            return running;
        }
	
	
	
	
	
}
