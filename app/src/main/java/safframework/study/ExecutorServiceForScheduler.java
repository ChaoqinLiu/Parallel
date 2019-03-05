package safframework.study;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceForScheduler {

    public static void main(String[] args){

        int threadNum = Runtime.getRuntime().availableProcessors() + 1;

        ExecutorService executor = Executors.newFixedThreadPool(threadNum);
        final Scheduler scheduler = Schedulers.from(executor);

        Observable.range(1,100)
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        return Observable.just(integer)
                                .subscribeOn(scheduler)
                                .map(new Function<Integer, String>() {
                                    @Override
                                    public String apply(Integer integer) throws Exception {
                                        return integer.toString();
                                    }
                                });
                    }
                }).doFinally(new Action() {
            @Override
            public void run() throws Exception {
                executor.shutdown();
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }
}
