package autocomplete;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class TermTest {
    Term t;
    ArrayList<Term> termsToSort;

    @BeforeEach
    public void setUp() {
        termsToSort = new ArrayList<Term>();
        termsToSort.add(new Term("Ello", 1));
        termsToSort.add(new Term("Hiya!", 6));
        termsToSort.add(new Term("Hey", 3));
        termsToSort.add(new Term("Howdy!", 2));
    }

    @Test 
    public void testConstructor(){
        t = new Term("HI", 4);
        assertEquals(t.key, "HI");
        assertEquals(t.weight, 4);
    }

    @Test 
    public void testReverseWeightComparator(){
        Collections.sort(termsToSort, Term.byReverseWeightOrder());
        assertEquals(termsToSort.get(0).weight, 6);
        assertEquals(termsToSort.get(1).key, "Hey");
    }

    @Test 
    public void testPrefixOrderSmallerThanR(){
        termsToSort.sort(Term.byPrefixOrder(9));
        assertEquals(termsToSort.get(0).key, "Ello");
        assertEquals(termsToSort.get(1).key, "Hey");
    }

    @Test 
    public void testPrefixOrderOfOne(){
        termsToSort.sort(Term.byPrefixOrder(1));
        assertEquals(termsToSort.get(0).key, "Ello");
        assertEquals(termsToSort.get(1).key, "Hiya!");
    }

    @Test
    public void testCompareTo(){
        Term t1 = new Term("Zebra", 100);
        Term t2 = new Term("Apple", 100);
        assertTrue(t1.compareTo(t2) > 0);
    }

}
