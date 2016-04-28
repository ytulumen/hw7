/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ytulumen.hw7_yasin_tulumen_121044020;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;
import javax.swing.text.DefaultEditorKit;

/**
 * Simulate the priority queue
 * @author yasin
 */
public class ArrivalSimulator {
    //Data Fields
    /** Queue to read from file */
    private CustomerQueue cstmrQueue =
            new CustomerQueue();
    /** Queue to sorted priority queue*/
    private CustomerQueue otherCstmrQueue =
            new CustomerQueue();
    /** Queue to transition elements */
    private CustomerQueue lastCstmrQueue =
            new CustomerQueue();
    /* maximum log time*/
    private final static int logTime = 1200;
    /**
     * Reads from file and stores datas in cstmrQueue
     * @param filename name of read from file 
     */
    public void readFromFile(String filename){

        BufferedReader br = null;
        int hour, minute;
        try {
            /** Read lines in this string*/
            String sCurrentLine;
            int first, second, counter;
            Character colon=':', ch;
            br = new BufferedReader(new FileReader(filename));
            sCurrentLine = br.readLine();
            /** this loop parses readed line*/
            while ((sCurrentLine = br.readLine()) != null) {
                Customer cstmr = new Customer();
                counter=5;
                /** Reads arrival time*/
                if(Character.isDigit(sCurrentLine.charAt(0))
                        && Character.isDigit(sCurrentLine.charAt(1))
                        && sCurrentLine.charAt(2)==colon
                        && Character.isDigit(sCurrentLine.charAt(3))
                        && Character.isDigit(sCurrentLine.charAt(4))){
                    hour = Character.getNumericValue(sCurrentLine.charAt(0))*10
                            + Character.getNumericValue(sCurrentLine.charAt(1));
                    minute = Character.getNumericValue(sCurrentLine.charAt(3))*10
                            + Character.getNumericValue(sCurrentLine.charAt(4));
                    cstmr.setArrivalTime(hour, minute);
                }
                else
                    throw new IllegalArgumentException();
                /** Jumps all whitespace characters*/
                while(Character.isWhitespace(sCurrentLine.charAt(counter)))
                    ++counter;
                /** Reads Transaction Duration*/
                if(Character.isDigit(sCurrentLine.charAt(counter))
                        && Character.isDigit(sCurrentLine.charAt(counter+1))){
                    minute = Character.getNumericValue(sCurrentLine.charAt(counter))*10
                            + Character.getNumericValue(sCurrentLine.charAt(counter+1));   
                    cstmr.setProcessingTime(minute);
                }
                else
                    throw new IllegalArgumentException();
                counter +=2;
                /** Jumps all whitespace characters*/
                while(Character.isWhitespace(sCurrentLine.charAt(counter))
                        || Character.isLetter(sCurrentLine.charAt(counter)))
                    ++counter;
                if(Character.isDigit(sCurrentLine.charAt(counter)))
                    cstmr.setCustomerPriority(Character.getNumericValue(sCurrentLine.charAt(counter)));
                else
                    throw new IllegalArgumentException();            
            
                cstmrQueue.add(cstmr);
                cstmr=null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e){
            e.printStackTrace();
        } 
        try {
            if (br != null)br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    /**
     * Runs simulator and writes it to file
     */
    public void runSimulator(){
              
        try{
            File file = new File("Output.txt");
            /** creates the file */
            file.createNewFile();
            /** creates a FileWriter Object */
            FileWriter writer = new FileWriter(file); 
            /** Writes the content to the file */
            writer.write("Departure Time    "
                        + "Transact Time    "
                        + "Served Customer Type");
            writer.write(System.getProperty( "line.separator" ));

            while(!cstmrQueue.isEmpty()){
                int hour=0, minute=0, hourC=0; 
                otherCstmrQueue=new CustomerQueue();;
                lastCstmrQueue=new CustomerQueue();;
                if(cstmrQueue.element().getCustomerPriority()==1){
                    otherCstmrQueue.add(cstmrQueue.poll());
                }
                else {
                    otherCstmrQueue.add(cstmrQueue.poll());
                    if((otherCstmrQueue.element().getCustomerPriority() 
                        > cstmrQueue.element().getCustomerPriority())
                        && ((otherCstmrQueue.element().getArrivalTime() 
                        + otherCstmrQueue.element().getProcessingTime())
                        >= (cstmrQueue.element().getArrivalTime() 
                        + cstmrQueue.element().getProcessingTime()))){

                        while((otherCstmrQueue.element().getCustomerPriority() 
                                > cstmrQueue.element().getCustomerPriority())
                                && ((otherCstmrQueue.element().getArrivalTime() 
                                + otherCstmrQueue.element().getProcessingTime())
                                >= (cstmrQueue.element().getArrivalTime() 
                                + cstmrQueue.element().getProcessingTime()) )){

                            otherCstmrQueue.add(cstmrQueue.poll());
                        }            
                        for(int i=0 ; i<otherCstmrQueue.size()-1 ; ++i){
                            lastCstmrQueue.add(otherCstmrQueue.poll());
                        }
                        while(!cstmrQueue.isEmpty()){
                            lastCstmrQueue.add(cstmrQueue.poll());
                        }
                        while(!lastCstmrQueue.isEmpty()){
                            cstmrQueue.add(lastCstmrQueue.poll());
                        }
                    }
                }
                minute = (otherCstmrQueue.element().getArrivalTime())% 60;
                hourC = otherCstmrQueue.element().getArrivalTime();
                while((hourC-=60) >= 60)
                    ++hour;
                ++hour;

                if(otherCstmrQueue.element().getArrivalTime() < logTime){
                    writer.write(hour + ":" + minute + "              ");

                    writer.write(otherCstmrQueue.element().getProcessingTime() 
                            +"                   " );

                    writer.write(otherCstmrQueue.element().getCustomerPriority());

                    writer.write(System.getProperty( "line.separator" ));
                    writer.flush();
                }
            }
            writer.close();
        }catch(IndexOutOfBoundsException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        
        
        
        
    /*    Customer cs2 = null;

        if(cstmrQueue.isEmpty())
            return;
        System.out.println("cuspri" 
                + cstmrQueue.element().getCustomerPriority()
                + cstmrQueue.element().getArrivalTime());
        if(cstmrQueue.element().getCustomerPriority()==1){
            cs2 = cstmrQueue.poll();
            runSimulator();
        }
        else if(cstmrQueue.element().getCustomerPriority()==2){
            cs2 = cstmrQueue.poll();
            while(!cstmrQueue.isEmpty()){
                if((cs2.getCustomerPriority() > cstmrQueue.element().getCustomerPriority())
                        && ((cs2.getArrivalTime() + cs2.getProcessingTime())
                        >= (cstmrQueue.element().getArrivalTime() + cstmrQueue.element().getProcessingTime()) )){
                    runSimulator();
                }
            }
        }
        else if(cstmrQueue.element().getCustomerPriority()==3){
            cs2 = cstmrQueue.poll();
            while(!cstmrQueue.isEmpty()){
                if((cs2.getCustomerPriority() > cstmrQueue.element().getCustomerPriority())
                        && ((cs2.getArrivalTime() + cs2.getProcessingTime())
                        >= (cstmrQueue.element().getArrivalTime() + cstmrQueue.element().getProcessingTime()) )){
                    runSimulator();
                }
            }
        }        
        System.out.println(cs2.getArrivalTime());
*/
            
    }
    public static void main(String[] args) {
        ArrivalSimulator simsim = new ArrivalSimulator();
        simsim.readFromFile("data1.txt");
        simsim.runSimulator();
    }
}
