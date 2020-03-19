package com.java8.impl;

import com.java8.dto.Persion;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class PersionData {

    public static void main(String[] args) {
       /* List<Persion> persions = new ArrayList<>();
        persions.add(new Persion("小红", 10, "女"));
        persions.add(new Persion("小黑", 12, "女"));
        persions.add(new Persion("小白", 5, "男"));
        persions.add(new Persion("小狼", 9, "男"));
        persions.add(new Persion().setName("小猪").setAge(15).setSex("男"));
        persions.add(new Persion().setName("小猪").setAge(15).setSex("男"));*/

        List<Persion> persions1 = new ArrayList<>();
        persions1.addAll(Data.getPersion());
        // Stream
        Stream<Persion> stream1 = Data.getPersion().stream();
        Stream<Persion> stream2 = persions1.stream();
        Stream stream0 = Stream.of(Data.getPersion());

        // collect
        List<Persion> list = Data.getPersion().stream().collect(Collectors.toList());

        Set<Persion> set = Data.getPersion().stream().collect(Collectors.toSet());

        Map<String, Integer> map0 = Data.getPersion().stream().collect(Collectors.toMap(val -> val.getName(), val -> val.getAge()));
        Map<String, Integer> map1 = Data.getPersion().stream().collect(Collectors.toMap(Persion::getName, Persion::getAge));
        System.out.println(list);
        System.out.println(set);
        // System.out.println(map0);

        // reduce
        Stream<Integer> stream3 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        Optional<Integer> optionalInteger = stream3.reduce((v1, v2) -> v1 + v2);
        Integer result = optionalInteger.get();
        System.out.println(result);
        Persion tem = new Persion();
        Optional<Persion> optionalInteger1 = Data.getPersion().stream().reduce((v1, v2) -> tem.setAge(v1.getAge() + v2.getAge()));
        Integer sumAge = optionalInteger1.get().getAge();
        System.out.println(sumAge);

        // System.out.println(Data.getPersion().stream().collect(Collectors.summingInt(Persion::getAge)));

        // max min
        Persion max = Data.getPersion().stream().max((s1, s2) -> s1.getAge() - s2.getAge()).get();
        Persion min = Data.getPersion().stream().min((s1, s2) -> s1.getAge() - s2.getAge()).get();

        // anyMatch allMatch noneMatch

        boolean anyMatch = Data.getPersion().stream().anyMatch(v -> v.getAge() > 20);
        boolean allMatch = Data.getPersion().stream().allMatch(v -> v.getAge() > 20);
        boolean noneMatch = Data.getPersion().stream().noneMatch(v -> v.getAge() > 20);

        // count
        long count = Data.getPersion().stream().count();

        //foreach
        Data.getPersion().stream().forEach(System.out::println);


        // filter
        Data.getPersion().stream().filter(v -> v.getAge() > 10).forEach(System.out::println);

        // distinct
        Data.getPersion2().stream().distinct().forEach(System.out::println);

        //sorted
        Data.getPersion().stream().sorted().forEach(System.out::println);
        Data.getPersion().stream().sorted((v1, v2) -> v1.getAge() - v2.getAge()).forEach(System.out::println);
        Data.getPersion().stream().sorted(Persion::compareTo).forEach(System.out::println);


        // sorted skip
        Data.getPersion().stream().limit(3).forEach(System.out::println);
        Data.getPersion().stream().skip(3).forEach(System.out::println);

        // map
        stream2.map(v -> v.getAge() > 5 ? v : v.getName()).forEach(System.out::println);


        // 并行流 多线程数据处理
        Data.getPersion().stream().parallel();
        Stream<Persion> persionStream = persions1.parallelStream();
        LongStream.rangeClosed(1L, 10).forEach(System.out::println);


        // findFirst  findAny  区当前处理的的第一个元素
        System.out.println(Data.getPersion().stream().findFirst());
        System.out.println(persionStream.findFirst());
        //System.out.println(persionStream.findAny());

        // 不太好理解
        String[] array = new String[]{"hello", "nihao"};
        String[] array2 = new String[]{"hello", "nihao"};
        Arrays.stream(array).map(v -> v.split("")).forEach(System.out::println);
        Arrays.stream(array2).map(v -> v.split("")).flatMap(Arrays::stream).forEach(System.out::println);

        // maxBy maxBy
        Data.getPersion().stream().collect(Collectors.maxBy((v1, v2) -> v1.getAge() - v2.getAge()));
        System.out.println(Data.getPersion().stream().collect(Collectors.maxBy(Persion::compareTo)));
        System.out.println(Data.getPersion().stream().collect(Collectors.maxBy(Persion::compareTo)));

        // joining
        System.out.println(Data.getPersion().stream().map(Persion::getName).collect(Collectors.joining()));
        System.out.println(Data.getPersion()
                .stream()
                .map(Persion::getName)
                .collect(Collectors.joining("&&")));
        System.out.println(Data.getPersion().stream()
                .map(Persion::getName)
                .collect(Collectors.joining("&&", "[", "]")));

        // summarizingInt summarizingDouble summingLong
        // averagingInt
        System.out.println(Data.getPersion().stream().collect(Collectors.summingInt(Persion::getAge)));
        System.out.println(Data.getPersion().stream().collect(Collectors.averagingInt(Persion::getAge)));

        // summarizingInt
        System.out.println(Data.getPersion().stream().collect(Collectors.summarizingInt(Persion::getAge)));

        //=========================

       /*
       1. REPL(Read Eval Print Loop)意为交互式的编程环境。JShell 是 Java 9 新增的一个交互式的编程环境工具。它允许你无需使用类或者方法包装来执行 Java 语句。它与 Python 的解释器类似，可以直接 输入表达式并查看其执行结果

       2. Java 9 多版本兼容 jar 包多版本兼容 JAR 功能能让你创建仅在特定版本的 Java 环境中运行库程序时选择使用的 class 版本。通过 --release 参数指定编译版本

       3. Java 9 List，Set 和 Map 接口中，新的静态工厂方法可以创建这些集合的不可变实例。这些工厂方法可以以更简洁的方式来创建集合
       Map<String, String> map = Map.of("A","Apple","B","Boy","C","Cat");

       4.一个接口中能定义如下几种变量/方法：常量 抽象方法 默认方法 静态方法 私有方法 私有静态方法

       5.Process API 添加了一个名为 ProcessHandle 的接口, 它允许查询进程状态并管理进程

       6. Optional 类的引入 stream() ifPresentOrElse() or()方法
         stream 方法的作用就是将 Optional 转为一个 Stream，如果该 Optional 中包含值，那么就返回包含这个值的 Stream，否则返回一个空的 Stream（Stream.empty()）
         ifPresentOrElse 方法的用途是，如果一个 Optional 包含值，则对其包含的值调用函数 action，即 action.accept(value)，这与 ifPresent 一致；与 ifPresent 方法的区别在于，ifPresentOrElse 还有第二个参数 emptyAction —— 如果 Optional 不包含值，那么 ifPresentOrElse 便会调用 emptyAction
         or 如果值存在，返回 Optional 指定的值，否则返回一个预设的值。

        */
/*
        1.局部变量类型推断
        var list = new ArrayList<String>();
        var stream = list.stream();
        2.统一的垃圾回收接口
        3.并行全垃圾回收器 G1
        大家如果接触过 Java 性能调优工作，应该会知道，调优的最终目标是通过参数设置来达到快速、低延时的内存垃圾回收以提高应用吞吐量，尽可能的避免因内存回收不及时而触发的完整 GC（Full GC 会带来应用出现卡顿）。
        G1 垃圾回收器是 Java 9 中 Hotspot 的默认垃圾回收器，是以一种低延时的垃圾回收器来设计的，旨在避免进行 Full GC，但是当并发收集无法快速回收内存时，会触发垃圾回收器回退进行 Full GC。之前 Java 版本中的 G1 垃圾回收器执行 GC 时采用的是基于单线程标记扫描压缩算法（mark-sweep-compact）。为了最大限度地减少 Full GC 造成的应用停顿的影响，Java 10 中将为 G1 引入多线程并行 GC，同时会使用与年轻代回收和混合回收相同的并行工作线程数量，从而减少了 Full GC 的发生，以带来更好的性能提升、更大的吞吐量。
        Java 10 中将采用并行化 mark-sweep-compact 算法，并使用与年轻代回收和混合回收相同数量的线程。具体并行 GC 线程数量可以通过：-XX：ParallelGCThreads 参数来调节，但这也会影响用于年轻代和混合收集的工作线程数
*/

/*
9
       https://www.ibm.com/developerworks/cn/java/the-new-features-of-Java-9/index.html
10
       https://www.ibm.com/developerworks/cn/java/the-new-features-of-Java-10/index.html
11
       https://www.ibm.com/developerworks/cn/java/the-new-features-of-Java-11/index.html

12
       https://www.ibm.com/developerworks/cn/java/the-new-features-of-Java-12/index.html
13
       https://www.ibm.com/developerworks/cn/java/the-new-features-of-Java-13/index.html
*/


    }


}
