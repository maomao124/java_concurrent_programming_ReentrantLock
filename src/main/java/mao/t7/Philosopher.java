package mao.t7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project name(项目名称)：java并发编程_ReentrantLock
 * Package(包名): mao.t7
 * Class(类名): Philosopher
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/5
 * Time(创建时间)： 16:42
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Philosopher extends Thread
{
    private static final Logger log = LoggerFactory.getLogger(Philosopher.class);

    /**
     * 左边筷子
     */
    final Chopstick leftChopstick;

    /**
     * 右边筷子
     */
    final Chopstick rightChopstick;

    /**
     * 哲学家
     *
     * @param name           线程名字，也就是哲学家名字
     * @param leftChopstick  左边筷子
     * @param rightChopstick 右边筷子
     */
    public Philosopher(String name, Chopstick leftChopstick, Chopstick rightChopstick)
    {
        super(name);
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
    }

    /**
     * 吃饭
     */
    private void eat()
    {
        log.debug("eating...");
        try
        {
            Thread.sleep(200);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        while (true)
        {
            //尝试获得左手筷子
            if (leftChopstick.tryLock())
            {
                try
                {
                    //尝试获得右手筷子
                    if (rightChopstick.tryLock())
                    {
                        try
                        {
                            eat();
                        }
                        finally
                        {
                            //放下右手筷子
                            rightChopstick.unlock();
                        }
                    }
                }
                finally
                {
                    //放下左手筷子
                    leftChopstick.unlock();
                }
            }
        }
    }
}
