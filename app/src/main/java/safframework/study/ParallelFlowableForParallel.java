package safframework.study;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.schedulers.Schedulers;

public class ParallelFlowableForParallel {

    public static void main(String[] args){

        ParallelFlowable parallelFlowable = Flowable.range(1,100).parallel();

        parallelFlowable.runOn(Schedulers.io())
                .map(new Function<Integer,Object>() {
                    @Override
                    public Object apply(@NonNull Integer integer) throws Exception {
                        return integer.toString();
                    }
                }).sequential()
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        System.out.println(s);
                    }
                });
    }
}
