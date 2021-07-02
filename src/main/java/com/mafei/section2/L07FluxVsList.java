package com.mafei.section2;

import com.mafei.section2.helper.NameGenerator;

import java.util.List;

/*
  @Author mafei
*/
public class L07FluxVsList {

    public static void main(String[] args) {

        //when get the data, everything is blocked,
        List<String> names = NameGenerator.getNames(20);
        System.out.println("names = " + names);


        //with flux [non block] you don't have to patient totally 20 seconds
        NameGenerator.getName(20).subscribe(name -> {
            System.out.println("name = " + name);
        });


    }
}
