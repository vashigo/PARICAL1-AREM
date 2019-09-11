/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.primefinder;

import edu.eci.arsw.math.MathUtilities;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2101770
 */
public class PrimeFinderThird extends Thread {
    
    private final BigInteger a;
    private final BigInteger b;
    private final PrimesResultSet prs;
    private volatile boolean paused = false;
    private volatile boolean running = true;
    private final Object pauseLock = new Object();

    public PrimeFinderThird(BigInteger _a, BigInteger _b, PrimesResultSet _prs) {
        this.a = _a;
        this.b = _b;
        this.prs = _prs;
    }   
    
    @Override
    public void run() {
        MathUtilities mt=new MathUtilities();
                
        int itCount=0;

        BigInteger i=a;
            
        while (i.compareTo(b)<=0){
            synchronized(pauseLock){                
                if(paused){
                    try {
                        pauseLock.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PrimeFinderThird.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            itCount++;
            if (mt.isPrime(i)){
                prs.addPrime(i);
            }

            i=i.add(BigInteger.ONE);
        }
        running = false;
        }    

    
    public void pause(){
        paused=true;
    }
    
    public void inicie(){
        synchronized(pauseLock){
            paused=false;
            pauseLock.notifyAll();
        }
    }

    public boolean isRunning() {
        return running;
    }
    
}
