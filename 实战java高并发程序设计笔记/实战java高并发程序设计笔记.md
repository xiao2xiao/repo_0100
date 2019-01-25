# 第一章 #

## 1.3 并发级别 ##

- 阻塞、无饥饿、无障碍、无锁、无等待

## 1.5 JMM ##

- JMM的3个特性：
- 原子性   
	yield：
	1. Yield是一个静态的原生(native)方法
	2. Yield告诉当前正在执行的线程把运行机会交给线程池中拥有相同优先级的线程
	3. Yield不能保证使得当前正在运行的线程迅速转换到可运行的状态
	4. 它仅能使一个线程从运行状态转到可运行状态，而不是等待或阻塞状态     
	
- 可见性

- 有序性	
	1. 指令重排：就是计算机组成原理里面的指令重排


----------


# 第二章 #

### 2.2.3 线程中断 ###

- 方法的区别：   
	1. public void interrupt()		       //中断线程，设置中断标志位   
	2. public boolean isInterrupted() 	   //判断线程是否中断    
	3. public static boolean interrupted()//判断线程是否中断，并清除当前中断状态      
	
- 两种方式：
	1. 定义一个标记标量；   
	      
		    if (flag) {
	    		System.out.println("thread is interrupter");
	    		break;  
	    	}
	2. 如果在循环体中，出现了类似wait()或者sleep()这样的操作，则只能通过中断来识别了。      
	 
		    if (Thread.currentThread().isInterrupted()) {
	    	   System.out.println("thread is interrupter");
	    	   break;
	    	}
## 2.3 volatile ##

- volatile能保证数据的可见性和有序性，但是不能保证原子性。
## 2.7 synchronized ##

- 关键字synchronized的作用是实现线程间的同步。他的工作是对同步的代码加锁，使得每一次，只能有一个线程进入同步块，从而保证线程间的安全性。    
- 关键字synchronized可以有多种用法，如下：
	1. 指定加锁对象：对给定对象加锁，进入同步代码前要获得给定对象的锁。
	2. 直接作用于实例方法：相对于对当前实例加锁，进入同步代码前要获得当前实例的锁。
	3. 直接作用于静态方法：相当于对当前类加锁，进入同步代码前要获得当前类的锁。     

# 第3章 JDK 并发包 #

### 3.1.7 线程阻塞工具类：LockSupport ###
- 在实际应用中，可以对ThreadPoolExecutor进行扩展来实现对线程池运行状态的跟踪，输出一些有用的调试信息，以帮助系统故障诊断。  
          
	    public static void main(String[] args) throws Exception {
    		ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS,
    				new LinkedBlockingQueue<Runnable>(10)) {
    			@Override
    			protected void beforeExecute(Thread t, Runnable r) {
    				System.out.println("准备执行：" + ((MyTask) r).getName());
    			}
    			@Override
    			protected void afterExecute(Runnable r, Throwable t) {
    				System.out.println("执行完成：" + ((MyTask) r).getName());
    			}
    			@Override
    			protected void terminated() {
    				System.out.println("线程退出！");
    			}
    		};
    		for (int i = 0; i < 5; i++) {
    			MyTask myTask = new MyTask("Thread-name-" + i);
    			// 使用submit报错
    			// executorService.submit(myTask);
    			executorService.execute(myTask);
    			Thread.sleep(10);
    		}
    		executorService.shutdown();
    	}
- 捕获线程池中哪一个线程抛出的异常和堆栈信息，可以自己扩展ThreadPoolExecutor类，如下：    

	    public static class TraceThreadPoolExecutor extends ThreadPoolExecutor {
    		public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
    				BlockingQueue<Runnable> workQueue) {
    			super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    		}
    		@Override
    		public void execute(Runnable task) {
    			// TODO Auto-generated method stub
    			super.execute(wrap(task, clientTrace(), Thread.currentThread().getName()));
    		}
    		@Override
    		public Future<?> submit(Runnable task) {
    			// TODO Auto-generated method stub
    			return super.submit(wrap(task, clientTrace(), Thread.currentThread().getName()));
    		}
    		private Exception clientTrace() {
    			return new Exception("Client stack trace");
    		}
    		private Runnable wrap(final Runnable task, final Exception clientStack, String clientThreadName) {
    			return new Runnable() {
    				@Override
    				public void run() {
    					try {
    						task.run();
    					} catch (Exception e) {
    						clientStack.printStackTrace();
    						throw e;
    					}
    				}
    			};
    		}
    	}

- forkjoin框架（线程窃取）    
 
		public class ForkJoinCountTaskDemo {
	    	public static class CountTask extends RecursiveTask<Long> {
	    		private static final long serialVersionUID = 1L;
	    		private static final int THRESHOLD = 10000;
	    		private long start;
	    		private long end;
	    		public CountTask(long start, long end) {
	    			super();
	    			this.start = start;
	    			this.end = end;
	    		}
	    		@Override
	    		protected Long compute() {
	    			long sum = 0;
	    			boolean canCompute = (end - start) < THRESHOLD;
	    			if (canCompute) {
	    				for (long i = start; i <= end; i++) {
	    					sum += i;
	    				}
	    			} else {
	    				long step = (start + end) / 100;
	    				ArrayList<CountTask> subTasks = new ArrayList<>();
	    				long pos = start;
	    				for (int i = 0; i < 100; i++) {
	    					long lastOne = pos + step;
	    					if (lastOne > end) {
	    						lastOne = end;
	    					}
	    					CountTask subTask = new CountTask(pos, lastOne);
	    					pos += step + 1;
	    					subTasks.add(subTask);
	    					subTask.fork();
	    				}
	    				for (CountTask t : subTasks) {
	    					sum += t.join();
	    				}
	    			}
	    			return sum;
	    		}
	    	}
	    
	    	public static void main(String[] args) {
	    		ForkJoinPool forkJoinPool = new ForkJoinPool();
	    		CountTask task = new CountTask(0, 200000L);
	    		ForkJoinTask<Long> result = forkJoinPool.submit(task);
	    		try {
	    			long res = result.get();
	    			System.out.println("sum = " + res);
	    		} catch (InterruptedException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		} catch (ExecutionException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	    	}
	    }

### 3.3.1 超好用的工具类：并发集合简介

- concurrentHashMap、CopyOnWriteArrayList、ConcurrentLinkedQuene（高并发环境中性能最好的队列）、BlockingQuene、ConcurrentSkipListMap      
- BlockingQuene：实现方式（链表、数组），适合于数据共享的通道。      