package mao.t9;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Project name(项目名称)：java并发编程_ReentrantLock
 * Package(包名): mao.t9
 * Class(类名): Test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/5
 * Time(创建时间)： 17:01
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test
{
    /**
     * 非公平锁
     */
    private static final ReentrantLock lock = new ReentrantLock(true);

    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(Test.class);


    public static void main(String[] args) throws InterruptedException
    {
        lock.lock();
        for (int i = 0; i < 50; i++)
        {
            new Thread(() ->
            {
                lock.lock();
                try
                {
                    log.debug("运行");
                }
                finally
                {
                    lock.unlock();
                }
            }, "t" + i).start();
        }
        //1s之后去争抢锁
        Thread.sleep(1000);
        new Thread(() ->
        {
            log.debug("--------------->开始运行");
            lock.lock();
            try
            {
                log.debug("---------------->运行");
            }
            finally
            {
                lock.unlock();
            }
        }, "强行插入").start();
        lock.unlock();
    }

}
