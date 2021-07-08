package com.mafei.section4;

import com.mafei.utils.SubscriberUtil;
import reactor.core.publisher.Flux;

import java.util.function.Function;

/*
  @Author mafei
*/

class Person {
    private String name;
    private int age;

    public Person() {
        this.name = SubscriberUtil.FAKER.name().firstName();
        this.age = SubscriberUtil.FAKER.random().nextInt(1, 30);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class L07Transform {


    public static void main(String[] args) {

        getPerson()
                .transform(applyFilter())
                .subscribe(person -> {
                    System.out.println("person = " + person);
                });


    }

    public static Flux<Person> getPerson() {
        return Flux.range(1, 10)
                .map(integer -> new Person());
    }

    public static Function<Flux<Person>, Flux<Person>> applyFilter() {
        return personFlux -> personFlux
                .filter(person -> person.getAge() > 10)
                .doOnNext(person ->
                        person.setName(person.getName().toUpperCase())
                ).doOnDiscard(Person.class, person -> {
                    System.out.println("Rejected person = " + person);
                });

    }

}
