import java.util.List;
import java.util.stream.Collectors;

public class ZipTest {
    public static void main(String[] args) {
        List<String> list1 = List.of("atest0", "test1", "test3", "test5", "test7", "test9", "test11", "test12");
        List<String> list2 = List.of("atest1", "test2", "test4", "test6", "test8");

        List<String> result = Zip.zip(list1.stream(), list2.stream())
                .collect(Collectors.toList());
        for(String s: result){
            System.out.println(s);
        }

    }
}
