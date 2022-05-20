package com.shark.facedistinguish.mockitotest;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Random;

class MockitoTestTest {

    @Mock
    private Random randomMock;

    @AfterEach
    private void destroy() {

    }

    @Spy
    private MockitoTest mockitoTestSpy;

    @BeforeEach
    void initParams() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * 使用断言方法Assertions.assertEquals()将期望值和实际方法调用返回结果进行比较
     */
    @Test
    void add2() {
        // randomSpy是使用@Spy注解的对象，randomSpy.add(1, 2)会走真实的方法，对两个数字会进行相加
        Assertions.assertEquals(3, mockitoTestSpy.add(1, 2));
    }

    /**
     * 1、 Mockito.mock()方法模拟某个类对象来生成实例对象；
     * 2、 Mockito.when().thenReturn() 打桩方法，when调用某个方法时，thenReturn()此时返回某个值；
     * 3、 Assertions.assertEquals()断言方法，用于判断某个方法的执行结果是否等于期望值
     */
    @Test
    void add() {
        Random random = Mockito.mock(Random.class);
        // 使用mock对象调用方法，不会走真实的方法，调用时返回的是默认值 0
        System.out.println(random.nextInt());

        // 对mock对象方法进行打桩，设置调用方法时返回某个值
        Mockito.when(random.nextInt()).thenReturn(2);
        Assertions.assertEquals(2, random.nextInt());

        // 使用Mock注解生成的mock对象来进行方法调用
        // 注意：使用mock注解的时候需要和MockitoAnnotations.openMocks(this)一起使用
        Mockito.when(randomMock.nextInt()).thenReturn(2);
        Assertions.assertEquals(2, random.nextInt());

    }

    /**
     * Mockito.mock() 与 Mockito.spy() 方法对比
     */
    @Test
    void add3() {
        Random random = Mockito.mock(Random.class);
        // 打印结果：0 没有调用实际的.nextInt()方法
        System.out.println(random.nextInt());

        // spy(new Random())方法可以是对象实例
        Random random2 = Mockito.spy(new Random());
        // 结果是一个随机数，因为使用.spy()方法会走真实的调用
        System.out.println(random2.nextInt());

        // spy(Random.class)方法也可以是类.class对象
        Random random3 = Mockito.spy(Random.class);
        // 结果是一个随机数，因为使用.spy()方法会走真实的调用
        System.out.println(random3.nextInt());
    }

    /**
     * 使用Mockito.verify()对mock对象的方法进行验证
     */
    @Test
    void add1() {

        Random random = Mockito.mock(Random.class);
        random.nextInt();
        // 判断某个方法是否调用
        Mockito.verify(random).nextInt();

        // 验证方法执行次数，能测试通过，因为上面只调用了 1 次
        Mockito.verify(random, Mockito.times(1)).nextInt();
        // 不能测试通过，因为上面只调用了 1 次
//        Mockito.verify(random, Mockito.times(2)).nextInt();
    }

    @Test
    void add4() {
        Mockito.when(mockitoTestSpy.add(1, 1)).thenCallRealMethod();
        Assertions.assertEquals(2, mockitoTestSpy.add(1, 1));
    }

    /**
     * 1、 Mockito.mockStatic(StringUtils.class) 对静态方法进行mock
     * 2、 Assertions.assertEquals()使用该断言判断方法执行结果是否和预期值相等
     * 3、 Assertions.assertTrue()使用该断言判断方法执行结果是否为true
     */
    @Test
    void add5() {
        // 注意静态模拟对象使用完成后需要手动关闭，内部使用的是ThreadLocal<MockingProgress>来进行处理的，同一个线程会公用一个ThreadLocal
        try (MockedStatic<StringUtils> stringUtilsStatic = Mockito.mockStatic(StringUtils.class)) {
            stringUtilsStatic.when(() -> StringUtils.compare("a", "a")).thenReturn(0);
            Assertions.assertEquals(0, StringUtils.compare("a", "a"));
            stringUtilsStatic.when(() -> StringUtils.isBlank(null)).thenReturn(true);
            Assertions.assertTrue(StringUtils.isBlank(null));
        }
    }
}