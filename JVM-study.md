1. Class文件结构<br/>
1.1 魔数 表明Class文件类型<br>
1.2 版本号 主版本号 次版本号<br>
1.3 常量池长度
1.4 常量池 [相关资料连接](https://www.jianshu.com/p/68520593b999 "")
1.5 access_flag
1.6 interface
1.7 method 里面有code 里面具体的内容指向常量池 例如一些机器指令  
```java 
aload_0//等等
```

2. 常量池<br/> 
2.1 tag 表明是什么数据类型的  
2.2 cp_info里面具体的结构 里面有utf-8(存储所有字符串) info method-info 字符串二进制存储等等一堆数值
3. Class文件加载机制<br/>
3.1 四种类加载器 BootClassLoader ExtClassLoader AppClassLoader CustomClassLoader<br/>
3.1.1 BootClassLoader 负责加载jre-home lib下的核心Class  
3.1.2 ExtClassLoader 负责加载 jre-home lib ext下的Class
3.1.3 AppClassLoader 负责加载ClassPath下的Class  
3.1.4 CustomClassLoader 自定义加载指定路径下的Class  
3.2 Class的加载机制双亲委派机制    
```java
//Launcher 先初始化一个ExtClassLoader 
 var1 = Launcher.ExtClassLoader.getExtClassLoader();
   return new Launcher.ExtClassLoader(var0);
//调用ClassLoader的构造器 将parent初始化为null;
    super(getExtURLs(var1), (ClassLoader)null, Launcher.factory);
        this.parent = parent;
//Launcher 接着会初始化一个AppClassLoader 重点也让ClassLoader的所有loader变为AppClassLoader
  this.loader = Launcher.AppClassLoader.getAppClassLoader(var1);
//这里的var0就是上面的ExtClassLoader,因此AppClassLoader的pareent就是ExtClassLoader
  return new Launcher.AppClassLoader(var1x, var0);
//自定义ClassLoader会继承ClassLoader 如果不指定parent的话，会默认使用AppClassLoader
 protected ClassLoader() {
        this(checkCreateClassLoader(), getSystemClassLoader());
    }
 public static ClassLoader getSystemClassLoader() {
        initSystemClassLoader();
        if (scl == null) {
            return null;
        }
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            checkClassLoaderPermission(scl, Reflection.getCallerClass());
        }
        return scl;
    }
initSystemClassLoader();//方法里，这里的l就是Laucher 而Laucher的ClassLoader在初始化AppClassLoader时就已经设置了为App
  scl = l.getClassLoader();




//AppClassLoader重写了ClassLoader的loadClass()方法
 Class var5 = this.findLoadedClass(var1);//ClassLoader父类的方法 查找该层加载器缓存是否存在该类
 this.resolveClass(var5);//var5不为null直接返回
 return super.loadClass(var1, var2);//var5为null,/继续调用ClassLoader的loadClass()方法
//下面的方法来自ClassLoader 是双亲委派模式加载的核心
 Class<?> c = findLoadedClass(name);//再一次缓存中查找
//这里的parent指代该层类加载器的上层加载器,最顶层的的为BootStrapClassLoader由C++实现JAVA中为Null
  if (parent != null) {
//继续调用ClassLoader的loadClass()方法（递归）注意这里会catch(ClassNotFoundException)所以上层类加载器未加载到类，不会抛出异常.
                        c = parent.loadClass(name, false);
                    } else {
//上级类加载器为null 从BootStrapClassLoader 缓存中查找
                        c = findBootstrapClassOrNull(name);
                    }
//如果查到最顶层还未查到的话，则从最顶层即BootStrapClassStrap开始，依次调用findClass方法
   c = findClass(name);
     return defineClass(name, res);
//可以从指定文件,读取Class文件到内存
 protected final Class<?> defineClass(String name,
                                         byte[] b, int off, int len,
                                         CodeSource cs)
    {
        return defineClass(name, b, off, len, getProtectionDomain(cs));
    }
//注意上面的findClass()方法一旦加载到Class则立即返回Class对象，不在向下继续寻找，如果最下层依然找不到，则抛出ClassNotFound异常.
```
***

4.  JVM的执行模式
    4.1 纯编译模式 -Xcomp   JVM启动过程中将所有Class文件用JIT解释成机器码
    4.2 纯解释模式 -Xint     JVM运行过程中如果发现有需要调用某Class，再用JIT进行解释
    4.3 混合模式   -Xmixed     对某些Class启动过程中解释，对某些Class用到时再用JIT解释
    -XX:CompileThreshold = 10000   
    
    ****
    
5.  JVM的硬件基础知识
    5.1  CPU的伪共享问题  
      5.1.1 CPU读取内存中的数据时,是按固定大小读的，每次读64byte  

      5.1.2 当主内存的某一块部分被多个CPU同时读取,或写入时，CPU会通过缓存一致性协议，保持数据的同步性。  
      5.1.3 使用缓存行对齐,将数据存储在主内存不同的块中.可以提高效率.    

    

    5.2 CPU对指令的重排序问题  
      5.2.1  如果两条指令没有依赖关系,前一条指令如果耗时较长，那么CPU可能等待前一条指令，选择继续执行第二条指令

    

    5.3 CPU的内存屏障   
     5.3.1 lfence 读屏障,在此屏障之前的读操作必须完成,才会让其他CPU读操作通过  

     5.3.2 sfence 写屏障,在此屏障之前的写操作必须完成，才会让其他CPU写操作通过  
     5.3.3 mfence 读写屏障,在此屏障之前的读写操作必须完成,才会让其他CPU读写操作通过   

    5.4 JVM的内存屏障  

     5.4.1 loadloadBarrier

     5.4.2 loadstoreBarrier 

     5.4.3 storestoreBarrier 

     5.4.4 storeloadBarrier   

    

    5.5 votaile的实现细节

      5.5.1 字节码层面:

      5.5.2 JVM层面:读写屏障
    
    > StoreStoreBarrier
    >
    > volatile 写操作
>
    > StoreLoadBarrier

    > LoadLoadBarrier
    >
    > volatile 读操作
>
    > LoadStoreBarrier
>
    > 

      5.5.3 OS/硬件层面  window:lock指令/CPU MESI协议


​    

​       5.6 使用JavaAgent测试Object的大小



​           5.6.1 观察虚拟机配置 java-XX:+PrintCommandLineFlags    -version



​           5.6.2 普通对象 

​           a,对象头:8byte   

​            a1. markword 原始数据

​            a2. ClassPointer指针 -XX:+UseCompressedClassPointers 为4字节 不开启为8字节    

​             b,实例数据:基本数据

​             引用数据（ -XX:+UseCompressedOops 为4字节 不开启为8字节 Oops Ordinary Object Pointers ）

​           c,padding 补齐8的倍数

​          



​          5.6.3 数组对象  

​           a,对象头:8byte   

​             a1. markword 原始数据

​             a2. ClassPointer指针 -XX:+UseCompressedClassPointers 为4字节 不开启为8字节    

​             a3.数组长度

​             b,实例数据:基本数据

​             引用数据（ -XX:+UseCompressedOops 为4字节 不开启为8字节 Oops Ordinary Object Pointers ）

​           c,padding 补齐8的倍数 

​       5.6.4 Hotspot  64位压缩原则

1. 4G以下，直接砍掉高32位
2. 4G - 32G，默认开启内存压缩 ClassPointers Oops
3. 32G，压缩无效，使用64位 内存并不是越大越好

​       

​     5.7 什么是JavaAgent

   在JAVA进程运行前对Class进行拦截的一个Java进程



​     5.8 对象头markword的组成

[markword组成]:(https://www.cnblogs.com/paddix/p/5405678.html "markword""

无锁态         hashCode()    0     01  

偏向锁         线程ID             1     01        

注:如果调用过hashCode方法则不能进入偏向锁，已经进入偏向锁的，调用hashCode（）方法会撤销偏向锁，升级为轻量级锁，重量级锁.(因为存贮hashCode的地方会被线程ID覆盖，导致两次调用hashCode不一致,轻量级锁会将hashCode存贮在线程栈中保留指针指向)

https://blog.csdn.net/Saintyyu/article/details/108295657

轻量级锁     当前线程的栈帧存贮markword的指针                         无   00

重量级锁      当前线程的栈帧存贮markword的指针                         无    01





# 6.JAVA RunTime DataSpace  

6.1指令集分类 寄存器和栈   JAVA基于栈 

6.2JVM  PC 程序计数器 记录指令的位置



6.3 JVM RunTime DataSpace  分类

6.3.1 JVM Stack

  6.3.1.1 method space :

 1.8之前 perim space :指定固定大小，GC不清理  1.8之后 metaspace 不指定，默认物理内存最大，GC 清理

  

  6.3.1.2 Stack Frame 栈帧：

  a,本地变量表

  b,操作数栈

  c,动态链接 DynmicLinking 

  d, return address 方法返回值存放的地方  





6.3.2 JVM Heap

6.3.3 Directory Heap 直接访问硬件主内存 比如NIO模型

6.3.3 C++ native method Stack

 

6.3.4  int i=8;  i=i++;/int i=8;  i=++i;的栈内存操作指令

int i=8;

 bipush  8：  byte 8压到操作数栈当中去

 istore_1： 将操作数栈顶的值弹出到本地变量表索引1的位置



i=i++;

iload_1： 将本地变量表索引位置1的变量再次压到操作数栈顶

 iinc 1 by 1： 将本地变量表索引位置1的变量 加1

istore_1： 将操作数栈顶的值再次弹出到本地变量表索引1的位置



++i;

iinc1 by 1： 将本地变量表索引位置1的变量 加1 变为9

iload_1 ：将本地变量表索引位置1的变量再次压到操作数栈顶的值  此时操作数栈顶的值为9

istore_1： 将操作数栈顶的值再次弹出到本地变量表索引1的位置



i=i+1;

iload_1：将本地变量表索引1位置的值压入操作数栈顶
iconst_1：将1压到操作数栈顶
iadd  ：将操作数栈的值弹出相加，在压入操作数栈
istore_1 将操作数栈顶的值弹出到本地变量表1的位置



6.3.5 JVM常用字节码指令

注意：JVM中 int 类型数值，根据 取值范围将 入栈的 **字节码指令** 就分为4类：

取值 **-1~5** 采用 `iconst` 指令；

取值 **-128~127** 采用 `bipush` 指令；

取值 **-32768~32767** 采用 `sipush`指令；

取值 **-2147483648~2147483647** 采用 `ldc` 指令。



a,store

b,load

c,pop

d,mul

e,sub

f,invoke

invokeStatic  静态方法

invokeVirtual 实例方法

invokeInterface

invokeSpecial  可以直接定位，不需要多态的方法 private 方法 ， 构造方法 

invokeDynamic  JVM最难的指令 lambda表达式或者反射或者其他动态语言scala kotlin，或者CGLib ASM，动态产生的class，会用到的指令 







#7. GC

##### 7.1 引用计数法

无法解决对象的循环依赖问题 对象被引用就加1

##### 7.2 根可达算法：

规定rootObject: 

1. 方法栈中的本地变量表的变量。

2. 运行常量池中的变量。

3. 本地native方法的变量

4. metaspace中的静态变量





##### 7.3回收垃圾对象的算法

1. 标记清除法 将有用对象，垃圾对象标记区分。删除垃圾对象。

2. 复制法  将有用对象标记。复制到新内存块。删除旧内存块.

3. 标记整理法 将有用对象，垃圾对象标记区分。删除垃圾对象。将有用对象整理放至到一边.





##### 7.4 新生代  edon 存放新生对象区域， survior1 ,survior2   8:1:1

1. 当触发MinorGC时,会将edon有用的对象复制到s1当中去,清空edon区。

2. 当再次触发MinorGC时,会将edon有用的对象和s1有用的对象一起复制到s2当中去，清空edon区 s1. 如此循环交换往复。

3. 当复制过程中s1或者s2满时,将所有有用对象放置老生代。





##### 7.5 老生代 存放来自于新生代的对象 在触发FGC事件时 会回收自身的无用对象

将有用对象，垃圾对象标记区分。删除垃圾对象。将有用对象整理放至到一边.





### 7.6 几种常见的JAVA垃圾回收器



####Serial(JDK1.3.1以前):单线程清理垃圾对象，清理时发生stop the world 即所有的工作线程停止。清理过后在执行工作线程。

1. Serial-正对新生代  使用复制法 
2. SerialNo针对老年代  使用标记整理法 或者标记清除法(CMS的后背预案)
3. 在用户的桌面应用场景中，可用内存一般不大（几十M至一两百M），可以在较短时间内完成垃圾收集（几十MS至一百多MS）,只要不频繁发生，这是可以接受的 
4. 使用-XX:+UseSerialGC  显式使用





####Parallel:多线程并行处理垃圾对象，清理时发生stop the world ,清理过后在执行工作线程。

##### 1.ParallelOld（JDK1.6之后才出现） 针对老年代

1.  JDK1.6及之后用来代替老年代的Serial Old收集器；特别是在Server模式，多CPU的情况下；这样在注重吞吐量以及CPU资源敏感的场景，就有了Parallel Scavenge加Parallel Old收集器的"给力"应用组合；

2. -XX:+UseParallelOldGC  老年代使用ParallelOldGC垃圾回收器

	

#####2.ParallelNew ()针对新生代

1.    在Server模式下，ParNew收集器是一个非常重要的收集器，因为除Serial外，目前只有它能与CMS收集器配合工作， 但在单个CPU环境中，不会比Serail收集器有更好的效果，因为存在线程交互开销。
2. -XX:+UseParNewGC 新生代会强制使用ParallelNew回收垃圾
3. 指定使用CMS后会默认使用ParallelNew作为新生代垃圾回收器
4. -XX:ParallelGCThreads 指定ParallelNew开始回收垃圾线程的数量，默认与CPU数量相同



##### 3.Parallel Scavenge 针对新生代

1.   高吞吐量为目标，即减少垃圾收集时间，让用户代码获得更长的运行时间；

	   当应用程序运行在具有多个CPU上，对暂停时间没有特别高的要求时，即程序主要在后台进行计算，而不需要与用户进行太多交互；例如，那些执行批量处理、订单处理、工资支付、科学计算的应用程序；



2. -XX:MaxGCPauseMillis 设定发生stw的最大时间
3. -XX:GCTimeRatio 设置垃圾回收的时间占总时间的比例 1/(1+参数值)
4. -XX:+UseAdptiveSizePolicy  JVM自适应调节策略



#### Concurrent(CMS JDK1.5出现)  :指用户线程与垃圾收集线程同时执行（但不一定是并行的，可能会交替执行）

1. 采用标记清除算法，所以容易产生内存碎片

2.   与用户交互较多的场景,希望系统停顿时间最短，注重服务的响应速度；以给用户带来较好的体验；如常见WEB、B/S系统的服务器上的应用

3. -XX:+UseConcMarkSweepGC 指定使用CMS 老生代收集器

4.  过程   

	4.1 初始标记 stop the world  多线程标记root 对象 

	4.2 并发标记 不发生stop the world 应用程序继续运行  标记处所有存活对象 垃圾对象

	4.3 重新标记  stop  the world 标记处并发标记过程当中后续运行过程产生的垃圾对象

	4.4 并发清除 回收所有标记的垃圾对象  

5. 缺点

	5.1 内存碎片 

	5.2 工作线程 GC线程同时运行 占用CPU资源 

	5.3 产生浮动垃圾  并发清除时，工作线程产生的新的垃圾 所以需要预留空间   -XX:CMSInitiatingOccupancyFraction  指定预留空间比例

	5.4 当预留空间无法满足程序需要时就会报  Concurrent Mode Failure 

	此时自动调用SerialOld清理垃圾。可能会产生较长的stop the world

	

	

	###  7.7排查GC问题,优化JVM

	  -Xms 最小内存
	
	-Xmx 最大内存
	
	-Xmn 年轻代大小
	
	
	
	-XX:+PrintGCDetails
	
	-XX:+PrintGCTimeStamps
	
	-XX:+PrintGCCauses
	
	
	
	-XX:+PrintFlagsInitial  默认参数值
	
	-XX:+PrintFlagsFinal 最终参数值
	
	
	
	
	
	
	
	 -Xloggc:/opt/xxx/logs/xxx-xxx-gc-%t.log  指定日志文件位置名称
	
	-XX:+UseGCLogFileRotation  开始日志循环记录 
	
	-XX:NumberOfGCLogFiles=5  设定GC日志的总量
	
	-XX:GCLogFileSize=20M  设定单个GC日志大小
	
	
	
	
	
	







​        

 



















​     

  

​      

​    









​    



