package autocomplete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Autocomplete {
	private List<Term> terms;

	/**
	 * Initializes the data structure from the given array of terms.
	 * 
	 * @param terms
	 *            List of terms that can be searched
	 */
	public Autocomplete(List<Term> terms) {
		this.terms = terms;
		Collections.sort(this.terms);
	}
    
	/**
	 * @param prefix
	 *            String prefix to be matched
	 * @return list of terms that start with the given prefix, in descending
	 *         order of weight
	 */
	public List<Term> allMatches(String prefix) {
		List<Term> matching = new ArrayList<Term>();
		Term prefixTerm = new Term(prefix, 1);
		int first, last;
		first = BinarySearchForAll.firstIndexOf(terms, prefixTerm, Term.byPrefixOrder(prefix.length()));
		last = BinarySearchForAll.lastIndexOf(terms, prefixTerm, Term.byPrefixOrder(prefix.length()));
		
		if (first == -1 || last == -1) {
			return matching;
		}

		if (first > 0 || last < terms.size() - 1) {
			if (first == last) {
				matching.add(terms.get(first));
			} else {
				for(Term term : terms.subList(first, last + 1)) {
					matching.add(term);
				}
			}
		}

		matching.sort(Term.byReverseWeightOrder());

		return matching;
	}

}
