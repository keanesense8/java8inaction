package com.keane.java8.action.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;



public class StreamTest {

    @Test
    public void StreamIterateTest(){
        IntStream.rangeClosed(1, 100)
                .filter(x -> x % 3 == 0)
                .boxed();
    }

    @Test
    public void streamMapTest(){
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        list.stream().map(n -> n*n)
                .collect(Collectors.toList());

    }

    @Test
    public void streamFlatMapTest(){
        /**
         * 你可以使用两个map来迭代这两个 表，并生成数对。但这样会返回一个Stream-
         * <Stream<Integer[]>>。你需要让生成的流  化，以得到一个Stream<Integer[]>。这
         *  是flatMap所做的
         */
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<Integer[]> ret = numbers1.stream()
                .flatMap(n1 ->
                        numbers2.stream()
                                .filter(n2 -> (n1 + n2) % 3 == 0)
                                .map(n2 -> new Integer[]{n1 ,n2}))
                //如果不是使用flatmap
                //这段返回 [[1,3],[1,4]] , [[2,3],[2,4]]....
                .collect(Collectors.toList());

        ret.parallelStream().forEach(x -> System.out.println(Arrays.toString(x)));

    }

    @Test
    public void streamTestSum(){
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        int sum = numbers.stream().reduce(0, Integer::sum);
        print(sum+"");

    }

    @Test
    public void streamMaxMinTest(){
        List<Integer> numbers = Arrays.asList(1, 2, 3,11,33);
        numbers.stream().reduce((a,b) -> a > b ? b : a).ifPresent(x -> print(x+""));
    }

    @Test
    public void streamSortTest(){

    }


    public void print(String print){
        System.out.println(print);
    }


}
