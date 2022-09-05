package mao.t2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Project name(项目名称)：java并发编程_ReentrantLock
 * Package(包名): mao.t2
 * Class(类名): Test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/5
 * Time(创建时间)： 16:05
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test
{
    /**
     * 可重入锁
     */
    private static final ReentrantLock REENTRANT_LOCK = new ReentrantLock();

    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(Test.class);

    /**
     * method1
     */
    public static void method1()
    {
        REENTRANT_LOCK.lock();
        try
        {
            log.debug("execute method1");
            method2();
        }
        finally
        {
            REENTRANT_LOCK.unlock();
        }
    }

    /**
     * method2
     */
    public static void method2()
    {
        REENTRANT_LOCK.lock();
        try
        {
            log.debug("execute method2");
            method3();
        }
        finally
        {
            REENTRANT_LOCK.unlock();
        }
    }

    /**
     * method3
     */
    public static void method3()
    {
        REENTRANT_LOCK.lock();
        try
        {
            log.debug("execute method3");
        }
        finally
        {
            REENTRANT_LOCK.unlock();
        }

    }

    public static void main(String[] args)
    {
        method1();
    }
}
