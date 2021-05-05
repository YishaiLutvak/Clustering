import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BitArray implements Clusterable<BitArray>{
	private ArrayList<Boolean> bits;

	public BitArray(String str){
		//https://beginnersbook.com/2015/05/java-string-to-arraylist-conversion/
		//https://stackoverflow.com/questions/52876063/how-to-convert-a-string-arraylist-to-a-boolean-arraylist
		List<String> myList = new ArrayList<String>(Arrays.asList(str.split(",")));
		List<Boolean> booleanList = myList.stream()
				.map(s -> Boolean.parseBoolean(s))
				.collect(Collectors.toList());
		this.bits = (ArrayList<Boolean>)booleanList;
	}
	public BitArray(boolean[] bits) {
		//https://www.geeksforgeeks.org/conversion-of-array-to-arraylist-in-java/
		//https://stackoverflow.com/questions/5134466/how-to-cast-arraylist-from-list
		List booleanList = Arrays.asList(bits);
		this.bits = (ArrayList<Boolean>)booleanList;
	}

	@Override
	public double distance(BitArray other) {
		//https://exercism.io/tracks/java/exercises/hamming/solutions/66876c2c483046f2987f970085e907fc#solution-comment-57787
		if(this.bits.size() != other.bits.size()){
			throw new IllegalArgumentException("DNA strands must be of equal length."); }
		return (int)IntStream.range(0, this.bits.size())
				.mapToObj(i -> Arrays.asList(this.bits.get(i), other.bits.get(i)))
				.collect(Collectors.toList()).stream()
				.filter(position -> !position.get(0).equals(position.get(1)))
				.count();
		/*return IntStream.range(0,bits.size())
				.filter(i -> !this.bits.get(i).equals(other.bits.get(i))).count();*/
	}

	public static Set<BitArray> readClusterableSet(String path) throws IOException {
		//https://www.geeksforgeeks.org/stream-max-method-java-examples/
		//https://howtodoinjava.com/java8/java-stream-filter-example/
		int max_size = Files.lines(Paths.get(path))
				.map(l-> new BitArray(l).bits.size()).max(Integer::compare).get();
		return Files.lines(Paths.get(path))
				.map(l-> new BitArray(l))
				.filter(b -> (b.bits.size()==max_size))
				.collect(Collectors.toSet());
	}

	@Override
	public String toString() {
		return bits.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BitArray bitArray = (BitArray) o;
		return bits.equals(bitArray.bits);
	}

	@Override
	public int hashCode() {
		return Objects.hash(bits);
	}
}
