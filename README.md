# java-concurrency
Project to demonstrate Executor Service

ExecutorService is a JDK API that simplifies running tasks in asynchronous mode. Generally speaking, ExecutorService automatically provides a pool of threads and an API for assigning tasks to it.

The easiest way to create ExecutorService is to use one of the factory methods of the Executors class.
ExecutorService executor = Executors.newFixedThreadPool(10);
ExecutorService can execute Runnable and Callable tasks.

The execute() method is void and doesn't give any possibility to get the result of a task's execution or to check the task's status (is it running):

executorService.execute(runnableTask);
submit() submits a Callable or a Runnable task to an ExecutorService and returns a result of type Future:

submit vs execute -> submit takes callable as input, whereas execute takes runnable as input, thats the reason submit returns Future value.

Future<String> future = executorService.submit(callableTask);

invokeAny() assigns a collection of tasks to an ExecutorService, causing each to run, and returns the result of a successful execution of one task (if there was a successful execution):

String result = executorService.invokeAny(callableTasks);

invokeAll() assigns a collection of tasks to an ExecutorService, causing each to run, and returns the result of all task executions in the form of a list of objects of type Future:

List<Future<String>> futures = executorService.invokeAll(callableTasks);

The submit() and invokeAll() methods return an object or a collection of objects of type Future, which allows us to get the result of a task's execution or to check the task's status (is it running).

The Future interface provides a special blocking method get(), which returns an actual result of the Callable task's execution or null in the case of a Runnable task:

Future<String> future = executorService.submit(callableTask);
String result = null;
try {
    result = future.get();
} catch (InterruptedException | ExecutionException e) {
    e.printStackTrace();
}

Calling the get() method while the task is still running will cause execution to block until the task properly executes and the result is available.

With very long blocking caused by the get() method, an application's performance can degrade. If the resulting data is not crucial, it is possible to avoid such a problem by using timeouts:

String result = future.get(200, TimeUnit.MILLISECONDS);
If the execution period is longer than specified (in this case, 200 milliseconds), a TimeoutException will be thrown.

We can use the isDone() method to check if the assigned task already processed or not.

The Future interface also provides for canceling task execution with the cancel() method and checking the cancellation with the isCancelled() method:

boolean canceled = future.cancel(true);
boolean isCancelled = future.isCancelled();

Reference: https://www.baeldung.com/java-executor-service-tutorial
