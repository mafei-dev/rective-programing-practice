package com.mafei.section1;

import java.util.stream.Stream;

/*
  @Author mafei
*/
public class TestStream {
    public static void main(String[] args) {
        //creating a stream pipeline
        Stream<Integer> integerStream = Stream.of(1).map(integer -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return integer * 2;
        });

        integerStream.forEach(integer -> {
            System.out.println("integer = " + integer);
        });
        //or
        //integerStream.forEach(System.out::println);
    }
}
