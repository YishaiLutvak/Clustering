import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class TwoDPoint implements Clusterable<TwoDPoint>{
	double x;
	double y;
	public TwoDPoint(String str){
		List<String> myList = new ArrayList<String>(Arrays.asList(str.split(",")));
		this.x = Double.parseDouble(myList.get(0));
		this.y = Double.parseDouble(myList.get(1));
	}
	public TwoDPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public double distance(TwoDPoint other) {
		double dif_x = this.x - other.x;
		double dif_y = this.y - other.y;
		double square_x = dif_x * dif_x;
		double square_y = dif_y * dif_y;
		return Math.sqrt(square_x + square_y);
	}

	public static Set<TwoDPoint> readClusterableSet(String path) throws IOException{
		return Files.lines(Paths.get(path))
				.map(l-> new TwoDPoint(l))
				.collect(Collectors.toSet());
	}

	@Override
	public String toString() {
		return x + "," + y;
	}

	@Override
	public boolean equals(Object other) {
		TwoDPoint otherP = (TwoDPoint) other;
		return (otherP.x == x && otherP.y == y);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}
