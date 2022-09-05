package mao.t11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Project name(项目名称)：java并发编程_ReentrantLock
 * Package(包名): mao.t11
 * Class(类名): Test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/5
 * Time(创建时间)： 17:24
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test
{

    private static final ReentrantLock lock = new ReentrantLock();

    private static final Condition waitCigaretteQueue = lock.newCondition();

    private static final Condition waitbreakfastQueue = lock.newCondition();

    private static boolean hasCigrette = false;

    private static boolean hasBreakfast = false;

    private static final Logger log = LoggerFactory.getLogger(Test.class);


    private static void sendCigarette()
    {
        lock.lock();
        try
        {
            log.debug("送烟来了");
            hasCigrette = true;
            waitCigaretteQueue.signal();
        }
        finally
        {
            lock.unlock();
        }
    }

    private static void sendBreakfast()
    {
        lock.lock();
        try
        {
            log.debug("送早餐来了");
            hasBreakfast = true;
            waitbreakfastQueue.signal();
        }
        finally
        {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    lock.lock();
                    while (!hasCigrette)
                    {
                        try
                        {
                            log.debug("开始等待它的烟");
                            waitCigaretteQueue.await();
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    log.debug("等到了它的烟");
                }
                finally
                {
                    lock.unlock();
                }
            }
        }, "t1").start();

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    lock.lock();
                    while (!hasBreakfast)
                    {
                        try
                        {
                            log.debug("开始等待它的早餐");
                            waitbreakfastQueue.await();
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    log.debug("等到了它的早餐");
                }
                finally
                {
                    lock.unlock();
                }
            }
        }, "t2").start();

        Thread.sleep(1000);
        sendBreakfast();
        Thread.sleep(1000);
        sendCigarette();
    }

}
