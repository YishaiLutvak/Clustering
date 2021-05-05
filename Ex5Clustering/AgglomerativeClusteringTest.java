import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

// A simple validation class for agglomerative clustering.
public class AgglomerativeClusteringTest {
	private static final String POINTS_PATH = System.getProperty("user.dir") + "\\Ex5Clustering\\points.txt";
	//private static final String POINTS_PATH = "C:\\Users\\User\\Desktop\\ex5\\Ex5Clustering\\points.txt";
	private static final String BITARRAYS_PATH = System.getProperty("user.dir") + "\\Ex5Clustering\\bitArrays.txt";
	//private static final String BITARRAYS_PATH = "C:\\Users\\User\\Desktop\\ex5\\Ex5Clustering\\bitArrays.txt";


	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter class name:");
		String className = scanner.nextLine();
		System.out.println("Enter Threshold:");
		double threshold = scanner.nextDouble();
		scanner.nextLine();

		if (className.equals("TwoDPoint")) {
			Clustering<TwoDPoint> twoDPointClustering = new AgglomerativeClustering(threshold);
			Set<TwoDPoint> points = TwoDPoint.readClusterableSet(POINTS_PATH);
			Set<Set<TwoDPoint>> pointsClusterSet = twoDPointClustering.clusterSet(points);
			System.out.println("The number of 2d point clusters is: " + pointsClusterSet.size());
		}
		else {
			AgglomerativeClustering<BitArray> bitArrayClustering = new AgglomerativeClustering(threshold);
			Set<BitArray> bitArrays = BitArray.readClusterableSet(BITARRAYS_PATH);
			Set<Set<BitArray>> bitArrayClusterSet = bitArrayClustering.clusterSet(bitArrays);
			System.out.println("The number of bitArray clusters is: " + bitArrayClusterSet.size());
		}
	}
}

