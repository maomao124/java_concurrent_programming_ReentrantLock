package mao.t7;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Project name(项目名称)：java并发编程_ReentrantLock
 * Package(包名): mao.t7
 * Class(类名): Chopstick
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/5
 * Time(创建时间)： 16:42
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Chopstick extends ReentrantLock
{
    /**
     * 筷子名字
     */
    String name;

    /**
     * 筷子
     *
     * @param name 名字
     */
    public Chopstick(String name)
    {
        this.name = name;
    }

    /**
     * 字符串
     *
     * @return {@link String}
     */
    @Override
    public String toString()
    {
        return "筷子" + name;
    }
}
