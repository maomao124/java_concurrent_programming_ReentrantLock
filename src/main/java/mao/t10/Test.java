package mao.t10;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Project name(项目名称)：java并发编程_ReentrantLock
 * Package(包名): mao.t10
 * Class(类名): Test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/5
 * Time(创建时间)： 17:13
 * Version(版本): 1.0
 * Description(描述)： 条件变量
 */

public class Test
{
    /**
     * 锁
     */
    private static final ReentrantLock LOCK = new ReentrantLock();

    /**
     * 条件
     */
    private static final Condition condition = LOCK.newCondition();

    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(Test.class);


    public static void main(String[] args)
    {
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                log.debug("获取锁");
                LOCK.lock();
                try
                {
                    log.debug("获取到锁");
                    Thread.sleep(2000);
                    log.debug("唤醒");
                    condition.signal();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    log.debug("释放锁");
                    LOCK.unlock();
                }
            }
        }, "t1");

        log.debug("获取锁");
        LOCK.lock();
        log.debug("获取到锁");
        thread.start();
        try
        {
            Thread.sleep(500);
            log.debug("await");
            condition.await();
            log.debug("被唤醒");
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        finally
        {
            log.debug("释放锁");
            LOCK.unlock();
        }
    }
}
