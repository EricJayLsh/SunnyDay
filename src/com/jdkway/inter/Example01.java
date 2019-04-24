package com.jdkway.inter;

/**
 * description: 自定义 函数式接口（配合Lambda表达式使用）
 * <p>
 * 仅仅只包含一个抽象方法的接口，每一个该类型的lambda表达式都会被匹配到这个抽象方法。jdk1.8提供了一个 @FunctionalInterface注解来检测函数式接口
 *
 * @FunctionalInterface 注解仅仅是为了检测改接口是否为函数式接口 不加这个注解也可以是函数式接口
 * <p>
 * Consumer <T>：消费型接口，有参无返回值
 * Supplier <T>：供给型接口，无参有返回值
 * Function <T,R>:函数式接口，有参有返回值
 * Predicate<T>:断言型接口，有参有返回值，返回值是boolean类型
 * @date: 2019/4/4
 * @author: @shihai.li@hand-china.com
 **/
@FunctionalInterface
public interface Example01 {

    /**
     * description: 一个参数的抽象方法
     *
     * @date: 2019/4/4
     * @author: @shihai.li@hand-china.com
     **/
    String getName(String v);

    /**
     * description: 无参数的接口默认方法
     *
     * @date: 2019/4/4
     * @author: @shihai.li@hand-china.com
     **/
    default String defaultFunction() {
        return "这是一个接口中定义的 默认方法 通过关键词 default 来声明 说明接口中可以定义实例方法了";
    }

    /**
     * description: 无参数的接口静态方法
     *
     * @date: 2019/4/4
     * @author: @shihai.li@hand-china.com
     **/
    static void staticFunction() {
        System.out.println("这是一个接口中定义的 静态方法 通过关键词 static 来声明 说明接口中可以定义实例方法了");
    }

}
