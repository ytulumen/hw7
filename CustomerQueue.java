/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ytulumen.hw7_yasin_tulumen_121044020;

import java.util.ArrayList;

/**
 *
 * @author yasin
 */
public class CustomerQueue{
    //Data Fields
    
    /* The queue of Customers */
    private ArrayList<Customer> cstmrQueue;   

    //Constructor
    /**
     * Creates a new ArrayList
     */
    public CustomerQueue(){
        cstmrQueue = new ArrayList<Customer>();
    }
    /**
     * Adds Customer end of ArrayList
     * @param e added Customer
     * @return true if any error detected
     */
    public boolean add(Customer e) {
        try{
            cstmrQueue.add(cstmrQueue.size(), e);
            return true;
        }
        catch(IndexOutOfBoundsException a){
            a.printStackTrace();
            return false;
        }
    }
    /**
     * Adds Customer end of ArrayList
     * @param e added Customer
     * @return true if any error detected
     */
    public boolean offer(Customer e) {
        return cstmrQueue.add(e);
    }
    /**
     * Retrieves and removes the head of this queue
     * @return returns null if this queue is empty.
     */
    public Customer poll() {
        Customer temp;
        temp = cstmrQueue.get(0);
        cstmrQueue.remove(0);
        return temp;
    }
    /**
     * Retrieves, but does not remove, the head of this queue.
     * @return head of queue
     */
    public Customer element() {
        return cstmrQueue.get(0);
    }
    /**
     * Retrieves, but does not remove, the head of this queue
     * @return returns null if this queue is empty.
     */
    public Customer peek() {
        return cstmrQueue.get(0);
    }
    /**
     * Size of list
     * @return Returns the number of elements in this list 
     */
    public int size() {
        return cstmrQueue.size();
    }
    /**
     * List is empty or not
     * @return returns true if list is empty
     */
    public boolean isEmpty() {
        return cstmrQueue.isEmpty();
    }
    /**
     * Removes a single instance of the specified element from this list
     * @param o be issued element
     * @return returns true if element removed succesfully
     */
    public boolean remove(Object o) {
        return cstmrQueue.remove(o);
    }
}
