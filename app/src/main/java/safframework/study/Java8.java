package safframework.study;

import java.util.ArrayList;
import java.util.List;

public class Java8 {

    public static void main(String[] args){

        List<Integer> result = new ArrayList();
        for (Integer i=1;i<=100;i++){
            result.add(i);
        }

        result.parallelStream()
                .map(new java.util.function.Function<Integer, String>(){
                    @Override

                    public String apply(Integer integer){
                        return integer.toString();
                    }
                }).forEach(new java.util.function.Consumer<String>() {
                    @Override
                    public void accept(String s){
                        System.out.println("s=" + s + ";Current Thread Name" + Thread.currentThread().getName());
                    }
        });

    }
}
