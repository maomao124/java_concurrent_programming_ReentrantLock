package mao.t3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Project name(项目名称)：java并发编程_ReentrantLock
 * Package(包名): mao.t3
 * Class(类名): Test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/5
 * Time(创建时间)： 16:11
 * Version(版本): 1.0
 * Description(描述)： 可打断
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


    public static void main(String[] args)
    {
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                log.debug("启动...");
                try
                {
                    REENTRANT_LOCK.lockInterruptibly();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                    log.debug("等锁的过程中被打断");
                    return;
                }
                try
                {
                    log.debug("获得了锁");
                }
                finally
                {
                    log.debug("释放锁");
                    REENTRANT_LOCK.unlock();
                }
            }
        }, "t1");

        //主线程获取锁
        REENTRANT_LOCK.lock();
        log.debug("main线程获得了锁");
        thread.start();
        try
        {
            Thread.sleep(1000);
            //打断
            log.debug("执行打断");
            thread.interrupt();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        finally
        {
            REENTRANT_LOCK.unlock();
        }
    }
}
