package com.java.chapter3.java.chapter5;

import java.util.ArrayList;

/**
 * This code will compile with no errors however when we run the program we get an ArrayStoreException
 *
 */
public class Trouble {
    public static void main(String[] args){
        Banana[] basketOfBanana = new Banana[2];
        basketOfBanana[0] = new Banana();

        Object[] basketOfFruits = basketOfBanana; //Trouble
        basketOfFruits[1] = new Apple();

        for(Banana banana:basketOfBanana){
            System.out.println(banana);
        }

        /**
         * Java does not allow the below code
         */

        //Java code
        ArrayList<Integer> list = new ArrayList<Integer>();
        //ArrayList<Object> list2 = list; compilation error

        //But this can be bypassed by
        ArrayList list3 = list;
    }
}
