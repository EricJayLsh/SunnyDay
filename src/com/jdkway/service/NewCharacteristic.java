package com.jdkway.service;

import com.jdkway.inter.Example01;
import com.jdkway.inter.Example02;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.UnsupportedEncodingException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Author:shihai.li@hand-chian.com
 * jdk1.8 新特性描述类
 * <p>
 * 描述总结：Lambda 表达式实际上是对函数式接口的实现 方法引用则是简化了Lambda表达式
 * Date:2019/4/4
 **/
public class NewCharacteristic extends ExampleClass implements Example01, Example02 {

    /**
     * description: Lambda 表达式
     *
     * @date: 2019/4/4
     * @author: @shihai.li@hand-china.com
     **/
    public void Lambda01() {

        // 无参数 无返回值
        Runnable t1 = () -> System.out.println("无参数 无返回值表达式");
        t1.run();

        // 有参数无返回值
        Consumer<String> t3 = (String s) -> {
            System.out.println("有参数 " + s + " 无返回值表达式");
        };
        t3.accept("我是参数001");

        // 有参数有返回值
        Function<Integer, Integer> function = (x) -> x - 100;
        System.out.println(function.apply(12));

        BiFunction<Integer, Integer, String> t4 = (x, y) -> "x+y=" + (x + y);
        System.out.println(t4.apply(12, 23));

        // 无参数 有返回值
        Supplier<String> sup = () -> "hello";
        System.out.println(sup.get());

        // bool
        Predicate<String> t5 = (str) -> str.length() > 5;
        System.out.println(t5.test("nihaoya"));

        // 自定义方法函数式接口调用
        Example01 eric01 = (String a) -> "获取名称：" + a;
        eric01.getName("EricJayLsh");

        // lambda 表达式只能引用标记了 final 的外层局部变量
        final int f = 34;
        Example02 eric02 = (int a, int b) -> a + b + f;
        // f = 55;
        System.out.println(NewCharacteristic.operate(23, 45, eric02));

        // 表达式中的变量名 不能和局部变量相同
        String str1 = "456";
        Predicate<String> eric03 = (str) -> str.length() > 5;

        // 传统方法排序
        List<Integer> list1k = new ArrayList<>();
        // 实现Comparator进行排序
        Collections.sort(list1k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        // 此处提示Lambda 表达是可以替换为方法引用
        Collections.sort(list1k, (h1, h2) -> h1.compareTo(h2));
        Collections.sort(list1k, Integer::compare);

    }

    private static int operate(int a, int b, Example02 v) {
        return v.getSum(a, b);
    }


    /**
     * description: 方法引用
     *
     * @date: 2019/4/4
     * @author: @shihai.li@hand-china.com
     **/
    public void Lambda02() {

        // 方法引用- 对象::实例方法
        Consumer<Integer> con = (x) -> System.out.println(x);
        con.accept(100);
        Consumer<Integer> con2 = System.out::println;
        con2.accept(200);

        // 方法引用- 类名::静态方法名(只是将 . 换成了 ：：)
        BiFunction<Integer, Integer, Integer> biFun = (x, y) -> Integer.compare(x, y);
        BiFunction<Integer, Integer, Integer> biFun2 = Integer::compare;
        System.out.println(biFun.apply(100, 200));
        System.out.println(biFun2.apply(700, 200));

        // 方法引用- 类名::实例方法名
        BiFunction<String, String, Boolean> fun1 = (str1, str2) -> str1.equals(str2);
        BiFunction<String, String, Boolean> fun2 = String::equals;
        System.out.println(fun1.apply("hello", "hello"));
        System.out.println(fun2.apply("hello", "world"));

        // 构造方法引用  类名::new
        Supplier<ExampleDto> sup = () -> new ExampleDto();
        System.out.println(sup.get());
        Supplier<ExampleDto> sup2 = ExampleDto::new;
        System.out.println(sup2.get());

        // 构造方法引用  类名::new （带一个参数）
        Function<Integer, ExampleDto> fun3s = (x) -> new ExampleDto(x);
        Function<Integer, ExampleDto> fun3 = ExampleDto::new;
        System.out.println(fun3s.apply(100));
        System.out.println(fun3.apply(100));

        // 数组引用
        Function<Integer, String[]> fun4s = (x) -> new String[x];
        Function<Integer, String[]> fun4x = String[]::new;
        String[] strArrays = fun4s.apply(5);
        String[] strArraysx = fun4x.apply(5);

        // 创建一个空数组
        Function<Integer, String[]> fun4 = String[]::new;
        String[] strArray = fun4.apply(5);
        for (int i = 0; i < strArray.length; i++) {
            strArray[i] = i + "ss";
        }
        Arrays.stream(strArray).forEach(System.out::println);
    }

    /**
     * description: 函数式接口
     *
     * @date: 2019/4/4
     * @author: @shihai.li@hand-china.com
     **/
    public void Lambda03() {
        // 有且仅有一个的抽象方法 (可以包含Object里的public方法)

        /*java.lang.Runnable
        java.util.concurrent.Callable
        java.security.PrivilegedAction
        java.util.Comparator
        java.io.FileFilter
        java.nio.file.PathMatcher
        java.lang.reflect.InvocationHandler
        java.beans.PropertyChangeListener
        java.awt.event.ActionListener
        javax.swing.event.ChangeListener

        JDK 1.8 新增加的函数接口

        java.util.function*/

        /*新增函数式接口
        1 BiConsumer<T, U>代表了一个接受两个输入参数的操作，并且不返回任何结果
        2 BiFunction<T, U, R>代表了一个接受两个输入参数的方法，并且返回一个结果
        3 BinaryOperator<T>代表了一个作用于于两个同类型操作符的操作，并且返回了操作符同类型的结果
        4 BiPredicate<T, U>代表了一个两个参数的boolean值方法
        5 BooleanSupplier代表了boolean值结果的提供方
        6 Consumer<T>代表了接受一个输入参数并且无返回的操作
        7 DoubleBinaryOperator代表了作用于两个double值操作符的操作，并且返回了一个double值的结果。
        8 DoubleConsumer代表一个接受double值参数的操作，并且不返回结果。
        9 DoubleFunction<R>代表接受一个double值参数的方法，并且返回结果
        10 DoublePredicate代表一个拥有double值参数的boolean值方法
        11 DoubleSupplier代表一个double值结构的提供方
        12 DoubleToIntFunction接受一个double类型输入，返回一个int类型结果。
        13 DoubleToLongFunction接受一个double类型输入，返回一个long类型结果
        14 DoubleUnaryOperator接受一个参数同为类型double, 返回值类型也为double 。
        15 Function<T, R>接受一个输入参数，返回一个结果。
        16 IntBinaryOperator接受两个参数同为类型int, 返回值类型也为int 。
        17 IntConsumer接受一个int类型的输入参数，无返回值 。
        18 IntFunction<R>接受一个int类型输入参数，返回一个结果 。
        19 IntPredicate：接受一个int输入参数，返回一个布尔值的结果。
        20 IntSupplier无参数，返回一个int类型结果。
        21 IntToDoubleFunction接受一个int类型输入，返回一个double类型结果 。
        22 IntToLongFunction接受一个int类型输入，返回一个long类型结果。
        23 IntUnaryOperator接受一个参数同为类型int, 返回值类型也为int 。
        24 LongBinaryOperator接受两个参数同为类型long, 返回值类型也为long。
        25 LongConsumer接受一个long类型的输入参数，无返回值。
        26 LongFunction<R>接受一个long类型输入参数，返回一个结果。
        27 LongPredicate R接受一个long输入参数，返回一个布尔值类型结果。
        28 LongSupplier无参数，返回一个结果long类型的值。
        29 LongToDoubleFunction接受一个long类型输入，返回一个double类型结果。
        30 LongToIntFunction接受一个long类型输入，返回一个int类型结果。
        31 LongUnaryOperator接受一个参数同为类型long, 返回值类型也为long。
        32 ObjDoubleConsumer<T>接受一个object类型和一个double类型的输入参数，无返回值。
        33 ObjIntConsumer<T>接受一个object类型和一个int类型的输入参数，无返回值。
        34 ObjLongConsumer<T>接受一个object类型和一个long类型的输入参数，无返回值。
        35 Predicate<T>接受一个输入参数，返回一个布尔值结果。
        36 Supplier<T>无参数，返回一个结果。
        37 ToDoubleBiFunction<T, U>接受两个输入参数，返回一个double类型结果
        38 ToDoubleFunction<T>接受一个输入参数，返回一个double类型结果
        39 ToIntBiFunction<T, U>接受两个输入参数，返回一个int类型结果。
        40 ToIntFunction<T>接受一个输入参数，返回一个int类型结果。
        41 ToLongBiFunction<T, U>接受两个输入参数，返回一个long类型结果。
        42 ToLongFunction<T>接受一个输入参数，返回一个long类型结果。
        43 UnaryOperator<T>接受一个参数为类型T, 返回值类型也为T。
        */
        // 举例 JDK中的接口
        Function<Integer, Integer> function = (x) -> x - 100;
        System.out.println(function.apply(56));

        String[] b = new String[]{"1", "2", "3"};
        List<String> a = Arrays.asList(b);
        // forEach 接收参数为 Consumer （Consumer<T>代表了接受一个输入参数并且无返回的操作）
        a.forEach((v) -> System.out.println(v));
    }

    // 默认方法
    public void Lambda04() {
        // 接口中 defeat 修饰的方法
        Example01 example01 = new NewCharacteristic();
        example01.defaultFunction();
        // 直接用接口名调用该方法
        Example01.staticFunction();

        System.out.println("类中的方法优先于接口中的方法");
        System.out.println(example01.defaultFunction());
    }

    // Stream
    public void Lambda05() {
        /*Stream（流）是一个来自数据源的元素队列并支持聚合操作

        元素是特定类型的对象，形成一个队列。Java中的Stream并不会存储元素，而是按需计算。
        数据源 流的来源。可以是集合，数组，I / O channel，产生器generator 等。
        聚合操作 类似SQL语句一样的操作，比如filter, map, reduce, find, match, sorted等。

        和以前的Collection操作不同，Stream操作还有两个基础的特征：

        Pipelining:
        中间操作都会返回流对象本身。这样多个操作可以串联成一个管道，如同流式风格（fluent style）。这样做可以对操作进行优化，比如延迟执行(laziness) 和短路( short -circuiting)。
        内部迭代：以前对集合遍历都是通过Iterator或者For - Each的方式, 显式的在集合外部进行迭代，这叫做外部迭代。Stream提供了内部迭代的方式，通过访问者模式(Visitor) 实现。*/

        /*集合接口有两个方法来生成流：
        stream() − 为集合创建串行流。
        parallelStream() − 为集合创建并行流。



    Intermediate 中间操作
        map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered
    Terminal 结束操作（终点操作）
        forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
    Short-circuiting
        anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit
        */

        List<ExampleDto> list1 = new ArrayList<>();
        ExampleDto item = new ExampleDto("a", "b", "c", 104, "lsh", 2);
        list1.add(item);
        ExampleDto item1 = new ExampleDto("a", "b", "c", 109, "ert", 4);
        list1.add(item1);


        // 穿行流
        Stream<ExampleDto> stream01 = list1.stream();
        // 并行流
        Stream<ExampleDto> stream02 = list1.parallelStream();

        List<String> list1k = new ArrayList<>();
        list1k.add("t");
        list1k.add("c");
        list1k.add("tb");
        String[] strArray = new String[]{"a", "b", "c"};

        Stream stream = Stream.of("a", "b", "c");

        stream = Stream.of(strArray);

        stream = Stream.of(list1k);

        //stream = Arrays.stream(strArray);

        System.out.println("测试stream");
        List<String> filtereds = list1k.stream().filter(v -> !v.equals("c")).collect(Collectors.toList());
        filtereds.forEach(System.out::println);

        System.out.println("================");

        // 返回 IntStream
        Random random = new Random();
        random.ints().limit(5).forEach(System.out::println);

        random.ints().limit(5).sorted().forEach(System.out::println);

        random.ints().limit(5).sorted().forEachOrdered(System.out::println);
        /*
         *//* forEach
        Stream 提供了新的方法 'forEach' 来迭代流中的每个数据*//*
        Random random1 = new Random();
        random.ints().limit(10).forEach(System.out::println);

        random.ints(10).forEach(System.out::println);*/

        // random.ints().forEach(System.out::println);

        System.out.println("================");
        // map 方法用于映射每个元素到对应的结果
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数
        List<Integer> squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        squaresList.forEach(v -> System.out.println(v));
        // stream 仅仅是在数据源的副本上（视图）操作
        System.out.println("查看数据源是否改变");
        numbers.forEach(v -> System.out.println(v));
        System.out.println("====");


        // filter 方法用于通过设置的条件过滤出元素。
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        // 获取空字符串的数量  注意stream 中 的count方法返回类型为 long
        long count = strings.stream().filter(string -> string.isEmpty()).count();
        long count1 = strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println(count);
        System.out.println(count1);

        System.out.println("====");

        //limit 方法用于获取指定数量的流。
        Random random2 = new Random();
        random.ints().limit(10).forEach(System.out::println);


        //sorted 方法用于对流进行排序。
        Random random3 = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);


        List<String> stringst = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered = stringst.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

        System.out.println("筛选列表: " + filtered);
        String mergedString = stringst.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);

        List<Integer> numberst = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        IntSummaryStatistics stats = numberst.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
        System.out.println("另一种写法：");

        Optional<Integer> max = numberst.stream().max((e1, e2) -> Integer.compare(e1, e2));
        Optional<Integer> max1 = numberst.stream().max(Integer::compare);
        System.out.println("列表中最大的数 : " + numberst.stream().max(Integer::compare).get());
        System.out.println("列表中最小的数 : " + numberst.stream().min(Integer::compare).get());

    }

    /**
     * description: Optional 类
     *
     * @date: 2019/4/4
     * @author: @shihai.li@hand-china.com
     **/
    public void Lambda06() {

        /*1 static <T> Optional<T> empty()返回空的 Optional 实例。
        2 	boolean equals(Object obj)判断其他对象是否等于 Optional。
        3 	Optional<T> filter(Predicate<? super <T> predicate)如果值存在，并且这个值匹配给定的 predicate，返回一个Optional用以描述这个值，否则返回一个空的Optional。
        4 	<U> Optional<U> flatMap(Function<? super T,Optional<U>> mapper)如果值存在，返回基于Optional包含的映射方法的值，否则返回一个空的Optional
        5 	T get()如果在这个Optional中包含这个值，返回值，否则抛出异常：NoSuchElementException
        6 	int hashCode()返回存在值的哈希码，如果值不存在 返回 0。
        7 	void ifPresent(Consumer<? super T> consumer)如果值存在则使用该值调用 consumer , 否则不做任何事情。
        8 	boolean isPresent()如果值存在则方法会返回true，否则返回 false。
        9 	<U>Optional<U> map(Function<? super T,? extends U> mapper)如果有值，则对其执行调用映射函数得到返回值。如果返回值不为 null，则创建包含映射返回值的Optional作为map方法返回值，否则返回空Optional。
        10 	static <T> Optional<T> of(T value)返回一个指定非null值的Optional。
        11 	static <T> Optional<T> ofNullable(T value)如果为非空，返回 Optional 描述的指定值，否则返回空的 Optional。
        12 	T orElse(T other)如果存在该值，返回值， 否则返回 other。
        13 	T orElseGet(Supplier<? extends T> other)如果存在该值，返回值， 否则触发 other，并返回 other 调用的结果。
        14 	<X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier)如果存在该值，返回包含的值，否则抛出由 Supplier 继承的异常
        15 	String toString()返回一个Optional的非空字符串，用来调试*/

        NewCharacteristic java8Tester = new NewCharacteristic();
        Integer value1 = null;
        Integer value2 = new Integer(10);

        // Optional.ofNullable - 允许传递为 null 参数  ofNullable 对null进行了判断
        Optional<Integer> a = Optional.ofNullable(value1);

        // Optional.of - 如果传递的参数是 null，抛出异常 NullPointerException
        Optional<Integer> b = Optional.of(value2);
        System.out.println(java8Tester.sum(a, b));

        // 实际应用
        // 将变量放入Optional中 避免处理空指针问题
        Optional<Integer> t = Optional.ofNullable(value1);
        Integer ts1 = t.orElse(new Integer(0));

        Integer ts2 = Optional.ofNullable(value1).orElse(new Integer(0));
        Integer ts3 = (value1 == null ? 0 : value1);
    }

    private Integer sum(Optional<Integer> a, Optional<Integer> b) {
        // Optional.isPresent - 判断值是否存在
        System.out.println("第一个参数值存在: " + a.isPresent());
        System.out.println("第二个参数值存在: " + b.isPresent());
        // Optional.orElse - 如果值存在，返回它，否则返回默认值
        Integer value1 = a.orElse(new Integer(0));
        // Optional.get - 获取值，值需要存在
        Integer value2 = b.get();
        return value1 + value2;
    }


    /**
     * description: Nashorn, JavaScript 引擎
     *
     * @date: 2019/4/4
     * @author: @shihai.li@hand-china.com
     **/
    public void Lambda07() {

        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");

        String name = "Runoob";
        Integer result = null;
        try {
            nashorn.eval("print('" + name + "')");
            result = (Integer) nashorn.eval("10 + 2");

        } catch (ScriptException e) {
            System.out.println("执行脚本错误: " + e.getMessage());
        }
        System.out.println(result.toString());
    }


    /**
     * description: 新的日期时间 API
     *
     * @date: 2019/4/4
     * @author: @shihai.li@hand-china.com
     **/
    public void Lambda08() {
        // LocalDate / LocalTime / LocalDateTime
        // 比如想把当前日期格式化成yyyy-MM-dd hh:mm:ss的形式:
        LocalDate ld = LocalDate.now();
        LocalTime lt = LocalTime.now();
        LocalDateTime dt = LocalDateTime.now();
        System.out.println(ld);
        System.out.println(lt);
        System.out.println(dt);

        System.out.println("处理格式后：");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        System.out.println(dtf.format(dt));


        // 获取当前的日期时间
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("当前时间: " + currentTime);

        LocalDate date1 = currentTime.toLocalDate();
        System.out.println("当前日期: " + date1);

        Month month = currentTime.getMonth();
        int day = currentTime.getDayOfMonth();
        int seconds = currentTime.getSecond();

        System.out.println("月: " + month + ", 日: " + day + ", 秒: " + seconds);

        LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
        System.out.println("date2: " + date2);

        // 12 december 2014
        LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
        System.out.println("date3: " + date3);

        // 22 小时 15 分钟
        LocalTime date4 = LocalTime.of(22, 15);
        System.out.println("date4: " + date4);

        // 解析字符串
        LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date5: " + date5);


        // 获取当前时间日期
        ZonedDateTime date1s = ZonedDateTime.parse("2019-03-14T10:15:30+05:30[Asia/Shanghai]");
        System.out.println("date1: " + date1s);

        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId: " + id);

        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("当期时区: " + currentZone);

    }

    /**
     * description: Base64
     *
     * @date: 2019/4/4
     * @author: @shihai.li@hand-china.com
     **/
    public void Lambda09() {

       /* 1 static Base64.Decoder getDecoder()返回一个 Base64.Decoder ，解码使用基本型 base64 编码方案。
        2 	static Base64.Encoder getEncoder()返回一个 Base64.Encoder ，编码使用基本型 base64 编码方案。
        3 	static Base64.Decoder getMimeDecoder()返回一个 Base64.Decoder ，解码使用 MIME 型 base64 编码方案。
        4static Base64.Encoder getMimeEncoder()返回一个 Base64.Encoder ，编码使用 MIME 型 base64 编码方案。
        5 	static Base64.Encoder getMimeEncoder(int lineLength, byte[] lineSeparator)返回一个 Base64.Encoder ，编码使用 MIME 型 base64 编码方案，可以通过参数指定每行的长度及行的分隔符。
        6 	static Base64.Decoder getUrlDecoder()返回一个 Base64.Decoder ，解码使用 URL 和文件名安全型 base64 编码方案。
        7 	static Base64.Encoder getUrlEncoder()返回一个 Base64.Encoder ，编码使用 URL 和文件名安全型 base64 编码方案。*/

        try {

            // 使用基本编码
            String base64encodedString = Base64.getEncoder().encodeToString("https://www.baidu.com/".getBytes("utf-8"));
            System.out.println("Base64 编码后的字符串 (基本) :" + base64encodedString);

            // 解码
            byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);

            System.out.println("原始字符串: " + new String(base64decodedBytes, "utf-8"));

            base64encodedString = Base64.getUrlEncoder().encodeToString("https://www.baidu.com/".getBytes("utf-8"));
            System.out.println("Base64 编码URL字符串:" + base64encodedString);

            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < 10; ++i) {
                stringBuilder.append(UUID.randomUUID().toString());
            }
            byte[] mimeBytes = stringBuilder.toString().getBytes("utf-8");
            String mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
            //String mimeEncodedStringS = Base64.getMimeEncoder(1, mimeBytes).encodeToString(mimeBytes);
            System.out.println("Base64 编码字符串 (MIME) :");
            System.out.println(mimeEncodedString);
            //  System.out.println("Base64 编码字符串 (MIME)S :" + mimeEncodedStringS);

        } catch (UnsupportedEncodingException e) {
            System.out.println("Error :" + e.getMessage());
        }
    }


    /**
     * description: 实现接口Example01 即实现抽象方法
     *
     * @date: 2019/4/4
     * @author: @shihai.li@hand-china.com
     **/
    @Override
    public String getName(String v) {
        return null;
    }

    /**
     * description: 实现接口Example02 即实现抽象方法
     *
     * @date: 2019/4/4
     * @author: @shihai.li@hand-china.com
     **/
    @Override
    public int getSum(int a, int b) {
        return 0;
    }
}
