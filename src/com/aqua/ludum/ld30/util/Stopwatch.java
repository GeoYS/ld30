package com.aqua.ludum.ld30.util;

/**
 * Stopwatch to keep track of time in MILLISECONDS.
 * @author GeoYS_2
 *
 */
public class Stopwatch {
    private long start, lastTime = 0;
    private boolean isRunning;
    public Stopwatch(){
        start = System.currentTimeMillis();
        isRunning = false;
    }
    
    /**
     * Time elapsed in millis
     * @return
     */
    public long time(){
        return isRunning ? System.currentTimeMillis() - start + lastTime : lastTime;
    }    
    
    /**
     * Starts the timer.
     */
    public void start(){
    	start = System.currentTimeMillis();
        isRunning = true;
    }
    
    /**
     * Stops the timer.
     */
    public void stop(){
        lastTime = time();
        isRunning = false;
    }
    
    /**
     * Returns whether or not the stopwatch is running.
     * @return
     */
    public boolean isRunning(){
        return isRunning;
    }
    
    /**
     * Stops and resets the stopwatch.
     */
    public void reset(){
    	lastTime = 0;
    	isRunning = false;
    }
    
    /**
     * Restarts the timer. (Same as resetting and starting.)
     */
    public void restart(){
    	lastTime = 0;
        start = System.currentTimeMillis();
        isRunning = true;
    }
}
