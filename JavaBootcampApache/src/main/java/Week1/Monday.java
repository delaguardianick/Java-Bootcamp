package Week1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gordak
 */

//Binary conversion
//Hardware can only execute 1 and 0
//Code needs to be compiled first -> compiler
//
//Java:
//JDK JRE JVM
//JVM inside JRE inside JDK
//
//JDK: Develop program with application (WRITE programs)
//    JRE: Allow to run java program (runtine env)
//        JVM: Java virtual machine
//
//Steps to run program:
//1. High level language (java)-> bytecode 
//    using javac
//    gives a .class (bytecode)
//
//2. bytecode -> Binary
//    using interpretor
//        JVM includes: Just in Time (JIT) compiler
//    bytecode is machine-independent, universal
//
//No need to compile each time before running program, java bytecode exectues faster

public class Monday {
    public static void main(String[] args) {
        System.out.println("Helloworld");
    }
    
}
