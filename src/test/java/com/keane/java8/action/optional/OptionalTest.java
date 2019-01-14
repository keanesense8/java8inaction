package com.keane.java8.action.optional;

import com.sun.tracing.dtrace.ArgsAttributes;
import lombok.*;
import org.junit.Test;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionalTest {

    @Test
    public void optionTest(){

        Optional<Car> car = Optional.empty();
        System.out.println(car.isPresent() ? car.get() : "null");

        Map<String,Car> m = Stream.iterate(0 , i -> i +1 )
                .limit(20)
//                .map(i -> "car" + i )
                .collect(Collectors.toMap(x -> { return "car" + x;}
                , x -> {return x % 5 ==0 ? new Car():new Car("car" + x , new Person("keane" + x));}));
        m.keySet().parallelStream()
                .forEach(
                        x ->
                                System.out.println(
                                Optional.ofNullable(m.get(x))
                        .map(Car::getPerson)
                                .map(y -> x + " " + y.getName())
                        .orElse(x + " other !!!!!!! "))

                );


    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    class Car{
        private String brand;
        private Person person;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    class Person{
        private String name;
    }



}
