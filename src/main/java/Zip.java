import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Zip {
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second){

        var a = first.iterator();
        var b = second.iterator();

        Stream.Builder<T> result = Stream.builder();
        while(a.hasNext() && b.hasNext()) {
            result.add(a.next()).add(b.next());
        }

        return result.build();
    }


    // CopyPast from StackOverflow (https://stackoverflow.com/questions/17640754/zipping-streams-using-jdk8-with-lambda-java-util-stream-streams-zip).
    // It doesn't work if the first stream is smaller than second
    public static<T> Stream<T> lazyZip(Stream<? extends T> a,
                                         Stream<? extends T> b) {
        Spliterator<? extends T> aSpliterator = a.spliterator();
        Spliterator<? extends T> bSpliterator = b.spliterator();

        // Zipping looses DISTINCT and SORTED characteristics
        int characteristics = aSpliterator.characteristics() & bSpliterator.characteristics() &
                ~(Spliterator.DISTINCT | Spliterator.SORTED);

        long zipSize = ((characteristics & Spliterator.SIZED) != 0)
                ? Math.min(aSpliterator.getExactSizeIfKnown(), bSpliterator.getExactSizeIfKnown())
                : -1;

        Iterator<T> aIterator = Spliterators.iterator(aSpliterator);
        Iterator<T> bIterator = Spliterators.iterator(bSpliterator);

        Iterator<T> cIterator = new Iterator<>() {
            boolean flag = true;
            @Override
            public boolean hasNext() {
                return aIterator.hasNext() && bIterator.hasNext();
            }

            @Override
            public T next() {
                T el = (flag ? aIterator : bIterator).next();
                flag = !flag;
                return el;
            }
        };

         Spliterator<T> split = Spliterators.spliterator(cIterator, zipSize, characteristics);
        return (a.isParallel() || b.isParallel())
                ? StreamSupport.stream(split, true)
                : StreamSupport.stream(split, false);

    }
}
