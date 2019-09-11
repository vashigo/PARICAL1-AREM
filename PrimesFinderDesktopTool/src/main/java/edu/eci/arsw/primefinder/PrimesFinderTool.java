package edu.eci.arsw.primefinder;

import edu.eci.arsw.mouseutils.MouseMovementMonitor;
import java.io.IOException;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class PrimesFinderTool {

	public static void main(String[] args) {
		            
            int maxPrim=1000;
            
            PrimesResultSet prs=new PrimesResultSet("john");
            
            //PrimeFinder.findPrimes(new BigInteger("1"), new BigInteger("10000"), prs);
            //PrimeFinder.findPrimes(new BigInteger("1"), new BigInteger("10"), prs);
            
            try {
                PrimeFinder.findPrimes(new BigInteger("1"), new BigInteger("100"), prs, 4);
                while(PrimeFinder.isRunning()){
                    try {
                        Thread.sleep(10);
                        if (MouseMovementMonitor.getInstance().getTimeSinceLastMouseMovement()>10000){
                            PrimeFinder.inicie();
                            System.out.println("inicio de hilos ");
                        }
                        else{
                            PrimeFinder.pause();
                            System.out.println("a espera de inactividad");
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PrimesFinderTool.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                System.out.println("Prime numbers found:");
                System.out.println(prs.getPrimes());
            } catch (InterruptedException ex) {
                Logger.getLogger(PrimesFinderTool.class.getName()).log(Level.SEVERE, null, ex);
            }

	}
	
}


