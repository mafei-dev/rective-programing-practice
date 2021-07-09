package com.mafei.section4;

import reactor.core.publisher.Flux;

import java.util.function.Function;

/*
  @Author mafei
*/
public class L08SwitchOnFirst {
    public static void main(String[] args) {

        getPerson()
                .switchOnFirst((signal, personFlux) -> {
                    System.out.println("inside of the switch on.");
                    //if the first age is less than 10, the filler will be applied.
                    if (signal.isOnNext() && signal.get().getAge() > 10) {
                        return personFlux;
                    } else {
                        return applyFilter().apply(personFlux);
                    }
                })
                .subscribe(person -> {
                    System.out.println("person = " + person);
                });
    }

    public static Flux<Person> getPerson() {
        return Flux.range(1, 10)
                .map(integer -> new Person());
    }

    public static Function<Flux<Person>, Flux<Person>> applyFilter() {
        System.out.println("Entered filter.");
        return personFlux -> personFlux
                .filter(person -> person.getAge() > 10)
                .doOnNext(person ->
                        person.setName(person.getName().toUpperCase())
                ).doOnDiscard(Person.class, person -> {
                    System.out.println("Rejected person = " + person);
                });
    }

}
