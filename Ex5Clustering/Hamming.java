import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Hamming {

    /**
     * compute the Hamming distance between two strands of DNA
     * given by strings s1 and s2
     * @param s1 the first strand of DNA
     * @param s2 the second strand of DNA
     * @return an integer representing the Hamming distance between the two
     *         strands of DNA
     */
    public static int compute(String s1, String s2){

        if(s1.length() != s2.length()){ throw new IllegalArgumentException("DNA strands must be of equal length."); }

        List<String> dna1 = Arrays.asList(s1.split(""));
        List<String> dna2 = Arrays.asList(s2.split(""));

        return (int)zip(dna1, dna2).stream()
                .filter(position -> !position.get(0).equals(position.get(1)))
                .count()
                ;

    }

    /**
     * creates a zipped list containing lists of elements from
     * the same positions of the lists in the arguments
     * @param l1 first list
     * @param l2 second list
     * @return a List<List<T>> containing elements from both lists
     */
    private static <T> List<List<T>> zip(List<T> l1, List<T> l2){

        return IntStream.range(0, l1.size())
                .mapToObj(i -> Arrays.asList(l1.get(i), l2.get(i)))
                .collect(Collectors.toList());

    }
}