//Run a task with the help of callable and store it's result in the Future.
package multithreading;

import java.util.Scanner;
import java.util.concurrent.*;

public class Question7 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the number for cube");
        Integer num=scanner.nextInt();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> result = executorService.submit(new Cube(num));
        System.out.println("The cube of "+num+"="+result.get());
    }

    static class Cube implements Callable<Integer> {
        public Integer num = 0;

        Cube(Integer num) {
            this.num = num;
        }

        @Override
        public Integer call() throws Exception {
            return cube();
        }

        public Integer cube() {
            return (num * num * num);
        }
    }
}