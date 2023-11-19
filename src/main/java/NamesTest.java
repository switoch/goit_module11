import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NamesTest {
    public static void main(String[] args) {
        first();
        second();
        third();
        fourth();

    }

    public static void first(){
        List<String> namesList = List.of("atest0", "test1", "test2", "test3", "test4", "test52", "test6", "test7");
        List<Names> names = new ArrayList<>();
        for(int i = 0; i< namesList.size(); i++){
            Names name = new Names(namesList.get(i), i);
            names.add(name);
        }

        List<Names> result = names.stream().
                filter(n -> n.getIndex()%2 != 0)
                .distinct()
                .toList();

        for (Names h : result) {
            System.out.println(h);
        }
    }

    public static void second(){
        List<String> namesList = List.of("atest0", "test1", "test2", "test3", "test4", "test52", "test6", "test7");
        List<Names> names = new ArrayList<>();
        for (String s : namesList) {
            Names name = new Names(s);
            names.add(name);
        }

        names.stream()
                .map(n -> n.getName().toUpperCase())
                .sorted(Comparator.reverseOrder())
                .peek(System.out::println)
                .toList();
    }


    public static void third(){
        String[] arr = {"1, 2, 0", "4, 5"};

        Arrays.stream(arr)
                .map(s -> List.of(s.split(", ")))
                .flatMap(Collection::stream)
                .map(Integer::valueOf)
                .peek(System.out::println)
                .toList();
    }

    public static void fourth(){
        RandomEternal ra = new RandomEternal();
        Stream<Integer> iterate = Stream.iterate(55, (seed) -> ra.withSeed(seed).next());

        List<Integer> collect = iterate
                .peek(System.out::println)
                .limit(10)
                .collect(Collectors.toList());

    }
}
