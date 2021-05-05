import javafx.util.Pair;

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
			Pair<Set<T>,Set<T>>myPair = clusters.stream()
					.flatMap(s1->clusters.stream()
							.filter(s2->!s2.equals(s1))
							.map(s2->new Pair<Set<T>,Set<T>>(s1,s2)))
					.min(Comparator.comparing(s->setsDistance(s.getKey(),s.getValue())))
					.orElse(null);
			if(setsDistance(myPair.getKey(),myPair.getValue())>this.threshold)
				return clusters;
			clusters.remove(myPair.getKey());
			clusters.remove(myPair.getValue());
			myPair.getKey().addAll(myPair.getValue());
			clusters.add(myPair.getKey());
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