package com.mafei.section1;

import com.mafei.utils.SubscriberUtil;
import reactor.core.publisher.Mono;

/*
  @Author mafei
*/
public class L05MonoFormSupplier {
    public static void main(String[] args) {

        //no body subscribed but the method is invoked
        //if you have data already, you can use this method to create a mono
        Mono.just(getName());

        //no body subscribed but the method is not invoked
        //if you haven't data already, you can use this method to create a mono without any execution
        //if you want to do only when subscribed,use this method.
        Mono.fromSupplier(() -> getName());


    }

    private static String getName() {
        System.out.println("generating the name..");
        return SubscriberUtil.FAKER.name().firstName();

    }
}
