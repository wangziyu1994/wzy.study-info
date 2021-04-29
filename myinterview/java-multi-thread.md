#####1.notify() 和 notifyAll() 有什么区别？

notify()随机唤醒一个被wait()的线程.无法确定唤醒的是哪一个被wait()的线程.由JVM控制.

notifyAll()唤醒所有被wait()的线程.



##### 2.Java 如何实现多线程之间的通讯和协作？

1,synchronized代码块里面的wait()  notify() notifyAll()方法

<font color='red'>2.ReentrantLock类加锁的线程的Condition类的await()/signal()/signalAll() 线程间直接的数据交换</font>?

<font color='red'>3.通过管道进行线程间通信：1）字节流；2）字符流同步方法和同步块，哪个是更好的选择？</font>



##### 3.什么是线程同步和线程互斥，有哪几种实现方式？

<font color='red'> 线程同步:通过一定的逻辑来控制线程共同完成一个任务?</font>

线程互斥:  一次只有线程，对共享资源的访问一次只允许一个线程,线程同步的特殊形式.

<font color='red'> lock与unlock方法，替换synchronized，这就是互斥锁的体现。消费者生产者模式就是同步锁的体现。 </font>



##### 4.在监视器(Monitor)内部，是如何做线程同步的？程序应该做哪种级别的同步

<font color='red'>在 java 虚拟机中，每个对象( Object 和 class )通过某种逻辑关联监视器,每个监视器和一个对象引用相关联，为了实现监视器的互斥功能，每个对象都关联着一把锁。</font>

<font color='red'>一旦方法或者代码块被 synchronized 修饰，那么这个部分就放入了监视器的</font>

<font color='red'>监视区域，确保一次只能有一个线程执行该部分的代码，线程在获取锁之前不允许执行该部分的代码</font>

<font color='red'>另外 java 还提供了显式监视器( Lock )和隐式监视器( synchronized )两种锁方案</font>







##### 5.如果你提交任务时，线程池队列已满，这时会发生什么

1，如果是无界队列（近似无穷大）的话,此是继续添加到等待队列

2，如果是有界队列的话，那么此时会开启非核心线程数执行队列的任务，如果达到最大线程数的话，此时执行拒绝策略





##### 6.JDK线程池拒绝策略模式是AbortPolicy 就是抛弃掉此任务



##### 7.SpringMvc/Servlet是单实例，多线程的，非线程安全，Stust是多实例多线程的，每个请求分配一个Action,请求完成后销毁，是线程安全的





##### 8.在 Java 程序中怎么保证多线程的运行安全？

1.对于要运行安全的代码采用Synchronized关键字，或者ReenTrantLock锁来进行同步

2.或者采用Atomic的类来进行原子性操作，进行运算



##### 9.你对线程优先级的理解是什么？

多线程并发执行，每个线程都处于Runnable状态，那么优先级高的线程可以优先获得CPU的执行权



##### 10.线程类的构造方法、静态块是被哪个线程调用的?

构造方法被此线程的创建线程调用

静态块在加载类时执行，只有创建此线程的线程才会加载该类，因此也是创建线程调用的



##### 11.Java 中怎么获取一份线程 dump 文件？你如何在 Java 中获取线程堆栈

1. jps -l 找出java进程的pid

2. top -p -H pid 找到此进程对应的众多线程号

3. jstack  pid 导出了当前java进程的线程栈dump日志  

4. 将top -pH的线程号转化为16进制 根据16进制在dump日志里查询，可以查询到指定线程的日志

	******

1. linux  kill -3 PID 停止进程，并且导出线程dump 日志  window  ctrl+break

##### 11.一个线程发生运行异常，会怎么样?,如何跟踪？

1. 发生异常没有被捕获时，线程会停止运行.

2. Thread.UncaughtExceptionHandler是用于处理未捕获异常造成线程突然中断情况的一个内嵌接口。当一个未捕获异常将造成线程中断的时候，JVM 会使用 Thread.getUncaughtExceptionHandler()来查询线程的信息

	UncaughtExceptionHandler 并将线程和异常作为参数传递给 handler 的 uncaughtException()方法进行处理。

```java 
 Thread t1=new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"执行.......");
            int i=12/0;
            System.out.println(Thread.currentThread().getName()+"异常后面代码.......");
        },"wzythread1");
        t1.setUncaughtExceptionHandler((t,e)->{
            Thread.UncaughtExceptionHandler un=Thread.currentThread().getUncaughtExceptionHandler();
            System.out.println("异常处理器是");
            System.out.println("线程未捕获的异常"+t.getName());
            System.out.println("线程未捕获的异常是");
            //e.printStackTrace();
            System.out.println("处理异常结束");

        });
        t1.start();
```



##### 12.Java 线程数过多会造成什么异常？

1. java线程数如果过多的话，可能造成栈的溢出，因为一个线程数就要开辟一个单独的线程栈 1MB
2. 还有可能导致CPU占用率过高。导致机器所有程序的卡顿。因为线程数过多，CPU需要不断切换，从而导致执行效率变低.

<font color='red'>降低稳定性JVM 在可创建线程的数量上存在一个限制，这个限制值将随着平台的不同而不同，并且承受着多个因素制约，包括 JVM 的启动参数、Thread 构造函数中请求栈的大小，以及底层操作系统对线程的限制等。如果破坏了这些限制，那么可能抛出OutOfMemoryError 异常。</font>



##### 13.Java中垃圾回收有什么目的？什么时候进行垃圾回收？

1. java垃圾回收可以回收没有引用的垃圾对象。释放内存空间。可以避免内存溢出，内存泄漏等问题。
2. 当一个对象没有被引用时，那么此对象就是垃圾对象。
3. 对于分代式垃圾回收器，当edon区满的时候回触发YGC，当S1或者S2满了的时候，将新生代所有存活对象移动至老年代.
4. 当老年代内存达到JVM设定的最大值时，也会出现FGC。
5. 程序员手动调用System.gc()方法时，也会调用.



##### 14.如果对象的引用被置为null，垃圾收集器是否会立即释放对象占用的内存？

1. 不会立即。
2. 而是在下一次触发垃圾回收事件时才会被回收.



##### 15.finalize()方法什么时候被调用？析构函数(finalization)的目的是什么？

1. 在垃圾回收器回收此对象时，该对象的finalize方法会被调用,先执行此方法，再回收此对象占用空间
2. GC本来就是内存回收了，应用还需要在finalization做什么呢？ 答案是大部分时候，什么都不用做(也就是不需要重载)。只有在某些很特殊的情况下，比如你调用了一些native的方法(一般是C写的)，可以要在finaliztion里去调用C的释放函数。



##### 16.重排序数据依赖性为什么代码会重排序?

1. 单线程下，具有数据依赖性的两条指令，并不会发生指令的重排序。
2. 单线程情况下，不改变程序执行的最终结果可以重新排序.
3. 多线程情况下，单线程的不改变程序执行的最终结果，可能影响多线程的执行结果.



##### 17.as-if-serial规则和happens-before规则的区别

<font color='red'>as-if-serial语义给编写单线程程序的程序员创造了一个幻境：单线程程序是按程序的顺序来执行的。happens-before关系给编写正确同步的多线程程序的程序员创造了一个幻境：正确同步的多线程程序是按 happens-before指定的顺序来执行的。</font>

##### 18.synchronized 的作用？

修饰方法，该方法一次只会允许一个线程通过

代码块 块中的内容，一次只会允许一个线程通过

为了多线程环境下控制线程的同步。



#####19.说说自己是怎么使用 synchronized 关键字，在项目中用到了吗

在多线程的情况下，控制线程共享数据的安全时。

1.使用synchronized代码块

2.synchronized修饰方法 

synchronized代码块中使用唯一对象作为锁。



#### 20.synchronized底层实现原理 ？

