/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ytulumen.hw7_yasin_tulumen_121044020;

/**
 * A class to represent a Customer
 * @author yasin
 */
public class Customer {
    //Data Fields
    /* Customer total process time */
    private int processingTime;
    /* Customer arrival time */
    private int arrivalTime;
    /* Customer type */
    private int customerPriority;
    
    //Constructors
    /**
     * Create a new free customer with requariments
     */
    public Customer(){
        this.arrivalTime = 0;
        this.processingTime = 0;
        this.customerPriority = 0;
    }
    /**
     * Create a new customer with requariments
     * @param processingTime  Customer total process time 
     * @param arrivalTime Customer arrival time
     * @param customerPriority Customer type
     */
    public Customer(int processingTime, int arrivalTime, int customerPriority){
        this.arrivalTime=arrivalTime;
        this.processingTime=processingTime;
        this.customerPriority=customerPriority;
    }
    
    //Methods
    /**
     * Return the customer arrival time
     * @return customer arrival time 
     */
    public int getArrivalTime(){
        return this.arrivalTime;
    }
    /**
     * Return the customer process time
     * @return customer process time
     */
    
    public int getProcessingTime(){
        return this.processingTime;
    }
    /**
     * Return the customer priority
     * @return customer priority
     */
    public int getCustomerPriority(){
        return this.customerPriority;
    }
    /**
     * Calculates arrival time as minute and returns it
     * @param hour customer's arrival time's hour part
     * @param minute customer's arrival time's minute part
     * @return converted time in minute type
     */
    private int convertTimetoMin(int hour, int minute){
        return (hour*60 + minute);
    }
    /**
     * Sets the arrivalTime, if format is hh:mm
     * @param arrTimeHour customer's arrival time's hour part
     * @param arrTimeMin customer's arrival time's minute part
     */
    public void setArrivalTime(int arrTimeHour, int arrTimeMin){
        this.arrivalTime=convertTimetoMin(arrTimeHour, arrTimeMin);
    }
    /**
     * Sets the arrivalTime, if format is mm
     * @param arrivalTime customer's arrival time in minute type
     */
    public void setArrivalTime(int arrivalTime){
        this.arrivalTime=arrivalTime;
    }
    /**
     * Sets the processingTime 
     * @param processingTime the value to set
     */
    public void setProcessingTime(int processingTime){
        this.processingTime=processingTime;
    }
    /**
     * Sets the customerPriority
     * @param customerPriority the priority to set
     */
    public void setCustomerPriority(int customerPriority){
        this.customerPriority=customerPriority;
    }
}
