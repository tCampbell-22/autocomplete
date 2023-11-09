package autocomplete;

import java.util.Comparator;

/**
 * Class that represents a term which consists of a key and an associated weight.
 */
public class Term implements Comparable<Term> {

	protected String key;
	protected long weight;


	/**
	 * Initializes a term with the given key and weight.
	 * 
	 * @param key
	 *            key to be stored
	 * @param weight
	 *            associated weight
	 */
	public Term(String key, long weight) {
		this.key = key;
		this.weight = weight;
	}

	/**
	 * @return comparator ordering elts by descending weight
	 */
	public static Comparator<Term> byReverseWeightOrder() {
		return (Term first, Term second) -> {
			return ((-1) * Long.valueOf(first.weight).compareTo(Long.valueOf(second.weight)));
		};
	}

	/**
	 * @param r
	 *            Number of initial characters to use in comparing keys
	 * @return comparator using lexicographic order, but using only the first r
	 *         letters of each key
	 */
	public static Comparator<Term> byPrefixOrder(int r) {
		return (Term first, Term second) -> {

			if (first.key.length() < r || second.key.length() < r) {

				if (first.key.length() < second.key.length()) {
					return first.key.compareTo(second.key.substring(0, first.key.length()));
				} else {
					return first.key.substring(0, second.key.length()).compareTo(second.key);
				}

			}

			return first.key.substring(0, r).compareTo(second.key.substring(0, r));
		};
	}

	/**
	 * @param that
	 *            Term to be compared
	 * @return -1, 0, or 1 depending on whether the key for THIS is
	 *		   lexicographically smaller, same or larger than THAT
	 */
	public int compareTo(Term that) {
		return this.key.compareTo(that.key);      
	}

	/**
	 * @return a string representation of this term in the following format: the
	 *         weight, followed by 2 tabs, followed by the key.
	 **/
	public String toString() {
		return this.weight + "\t\t" + this.key;   
	}

}
