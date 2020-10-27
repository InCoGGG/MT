package com.example.myapplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class RandomNumberGenerator {

    private ArrayList<Integer> numbers = new ArrayList<>();


    RandomNumberGenerator(int howManyNumbers, String maxNumber){
        generateNumbers(howManyNumbers, maxNumber);
    }


    private void generateNumbers(int howManyNumbers, String maxNumber){
        int min = 0;
        int max = Integer.parseInt(maxNumber);

        for (int i=0; i < howManyNumbers; i++){
            int rand = new Random().nextInt(max - min + 1) + min  ;
            numbers.add(rand);
        }
    }

    //We will again randomise the position of all the numbers and then give to activity
    ArrayList<Integer> getListOfNumber(){
        ArrayList<Integer> a = new ArrayList<>();
        a.addAll(numbers);
        a.addAll(numbers);
        Collections.shuffle(a);

        return a;
    }



}
