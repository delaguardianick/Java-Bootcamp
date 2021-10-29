/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Week1;

/**
 *
 * @author Gordak
 */
public class ThursDog {
    
    // Class variables
    private String name;
    private double weight;
    
    // Default constructor
    public ThursDog() {
    }
    
    // Constructor to create dog object
    // Dog dog1 = new Dog("Pisha", 120.0)
    public ThursDog(String nameIn, double weightIn) {
        this.name = nameIn;
        this.weight = weightIn;
    }
    
    public String getName() {
        return name;
    }
    
    // dog1.setName("Rishy");
    // Private class variable is changed
    public void setName(String name) {
        this.name = name;
    }
    
    public double getWeight() {
        return weight;
    }
    
    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    public void bark() {
        System.out.println("WOOF!");
    }
    
    public void sit() {
        System.out.println("Sitting...");
    }
    
}

