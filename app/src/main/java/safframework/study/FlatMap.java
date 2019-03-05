package safframework.study;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class FlatMap {

    public static void main(String[] args){

        Observable.range(1,100)
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        return Observable.just(integer)
                                .subscribeOn(Schedulers.computation())  //指定调度器为computation
                                .map(new Function<Integer, String>() {
                                    @Override
                                    public String apply(Integer integer) throws Exception {
                                        return integer.toString();
                                    }
                                });
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }
}
