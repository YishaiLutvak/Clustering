import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

//https://stackoverflow.com/questions/1068760/can-i-pass-parameters-by-reference-in-java
public class AgglomerativeClustering <T extends Clusterable<T>> implements Clustering<T>{
	double threshold;
	Set<Set<T>>clusters;

	public AgglomerativeClustering(double threshold) {
		this.threshold = threshold;
	}

	public Set<Set<T>> clusterSet(Set<T> elements) {

		this.clusters = elements.stream().map(item->fromItemToSet(item)).collect(Collectors.toSet());

		while (clusters.size() != 1) {
			ArrayList<Set<T>> myPair = clusters.stream()
					.flatMap(s1->clusters.stream()
							.filter(s2->!s2.equals(s1))
							.map(s2->{
								ArrayList<Set<T>> p = new ArrayList<>();
								p.add(s1);
								p.add(s2);
								return p;
								}))
					.min(Comparator.comparing(p->setsDistance(p.get(0),p.get(1))))
					.orElse(null);

			if(setsDistance(myPair.get(0),myPair.get(1))>this.threshold)
				return clusters;

			clusters.remove(myPair.get(0));
			clusters.remove(myPair.get(1));
			myPair.get(0).addAll(myPair.get(1));
			clusters.add(myPair.get(0));
		}

		return clusters;
	}

	private double setsDistance(Set<T>set1,Set<T>set2)
	{
		return set1.stream().map(point1->
				set2.stream().map(point2->point2.distance(point1)).min(Double::compare).orElse(null)
		).min(Double::compare).orElse(null);
	}

	private Set<T> fromItemToSet(T item)
	{
		HashSet mySet = new HashSet<T>();
		mySet.add(item);
		return mySet;
	}
}