package autocomplete;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class BinarySearchTest {
    ArrayList<Term> alphaTerms;
    ArrayList<Term> alphaTerms2;
    ArrayList<Term> repeatTerms;

    @BeforeEach
    public void setUp() {
        alphaTerms = new ArrayList<Term>();
        alphaTerms2 = new ArrayList<Term>();
        repeatTerms = new ArrayList<Term>();
        for (int i = 0; i < 26; i++) {
            alphaTerms.add(new Term((char)(i + 97) + "", i));
            alphaTerms2.add(new Term((char)(i + 97) + "eee", i));
            alphaTerms2.add(new Term((char)(i + 97) + "" + (char)(i + 97), i));
        }

        repeatTerms.add(new Term("Dress", 2));

        for(int i = 0; i < 400; i++){
            repeatTerms.add(new Term("Shirt", 4));
        }

        repeatTerms.add(new Term("Shoes", 2));

        for(int i = 0; i < 400; i++){
            repeatTerms.add(new Term("Shawl", 4));
        }

        repeatTerms.add(new Term("Gloves", 0));


        Collections.shuffle(alphaTerms);
        Collections.shuffle(alphaTerms2);
        Collections.shuffle(repeatTerms);
    }

    @Test
    public void testFirstIndexOfReverseWeight() {
        alphaTerms.add(new Term("a", 0));
        alphaTerms.add(new Term("b", 1));
        alphaTerms.sort(Term.byReverseWeightOrder());
        int index = BinarySearchForAll.firstIndexOf(alphaTerms, new Term("a", 0), Term.byReverseWeightOrder());
        assertEquals(alphaTerms.size() - 2, index);
        index = BinarySearchForAll.firstIndexOf(alphaTerms, new Term("a", 100), Term.byReverseWeightOrder());
        assertEquals(index, -1);

        repeatTerms.sort(Term.byReverseWeightOrder());
        index = BinarySearchForAll.firstIndexOf(repeatTerms, new Term("Shawl", 4), Term.byReverseWeightOrder());
        assertEquals(index, 0);
        index = BinarySearchForAll.firstIndexOf(repeatTerms, new Term("Shirt", 4), Term.byReverseWeightOrder());
        assertEquals(index, 0);
        index = BinarySearchForAll.firstIndexOf(repeatTerms, new Term("Hello", 4), Term.byReverseWeightOrder());
        assertEquals(index, 0);
        index = BinarySearchForAll.firstIndexOf(repeatTerms, new Term("Dress", 2), Term.byReverseWeightOrder());
        assertEquals(index, 800);
    }

    @Test
    public void testFirstIndexOfPrefix() {
        alphaTerms2.add(new Term("sup", 0));
        alphaTerms2.add(new Term("neato", 1));
        alphaTerms2.sort(Term.byPrefixOrder(1));
        int index = BinarySearchForAll.firstIndexOf(alphaTerms2, new Term("sup", 0), Term.byPrefixOrder(1));
        assertEquals(37, index);
        alphaTerms2.sort(Term.byPrefixOrder(2));      
        index = BinarySearchForAll.firstIndexOf(alphaTerms2, new Term("sup", 0), Term.byPrefixOrder(2));
        assertEquals(39, index);
        index = BinarySearchForAll.firstIndexOf(alphaTerms2, new Term("neato", 1), Term.byPrefixOrder(2));
        assertEquals(26, index);
        assertEquals(alphaTerms2.get(26).key, "neee");
        assertEquals(alphaTerms2.get(27).key, "neato");
        index = BinarySearchForAll.firstIndexOf(alphaTerms, new Term("AAAAAAA", 100), Term.byPrefixOrder(1));
        assertEquals(index, -1);

        repeatTerms.sort(Term.byPrefixOrder(1));
        index = BinarySearchForAll.firstIndexOf(repeatTerms, new Term("Shawl", 4), Term.byPrefixOrder(1));
        assertEquals(index, 2);
        index = BinarySearchForAll.firstIndexOf(repeatTerms, new Term("Shirt", 4), Term.byPrefixOrder(1));
        assertEquals(index, 2);
        index = BinarySearchForAll.firstIndexOf(repeatTerms, new Term("Hello", 4), Term.byPrefixOrder(1));
        assertEquals(index, -1);
        index = BinarySearchForAll.firstIndexOf(repeatTerms, new Term("Dress", 2), Term.byPrefixOrder(1));
        assertEquals(index, 0);

        repeatTerms.sort(Term.byPrefixOrder(3));
        index = BinarySearchForAll.firstIndexOf(repeatTerms, new Term("Shawl", 4), Term.byPrefixOrder(3));
        assertEquals(index, 2);
        index = BinarySearchForAll.firstIndexOf(repeatTerms, new Term("Shirt", 4), Term.byPrefixOrder(3));
        assertEquals(index, 402);
        index = BinarySearchForAll.firstIndexOf(repeatTerms, new Term("Hello", 4), Term.byPrefixOrder(3));
        assertEquals(index, -1);
        index = BinarySearchForAll.firstIndexOf(repeatTerms, new Term("Dress", 2), Term.byPrefixOrder(3));
        assertEquals(index, 0);
    }

    @Test
    public void testFirstIndexOfDefault() {
        alphaTerms2.add(new Term("sup", 0));
        alphaTerms2.add(new Term("neato", 1));
        Collections.sort(alphaTerms2);
        int index = BinarySearchForAll.firstIndexOf(alphaTerms2, new Term("sup", 0), (Term t1, Term t2) -> {return t1.compareTo(t2);});
        assertEquals(39, index);
        index = BinarySearchForAll.firstIndexOf(alphaTerms2, new Term("neato", 1), (Term t1, Term t2) -> {return t1.compareTo(t2);});
        assertEquals(26, index);
        index = BinarySearchForAll.firstIndexOf(alphaTerms2, new Term("ZZZZ", 1), (Term t1, Term t2) -> {return t1.compareTo(t2);});
        assertEquals(-1, index);

        Collections.sort(repeatTerms);
        index = BinarySearchForAll.firstIndexOf(repeatTerms, new Term("Shawl", 4), (Term t1, Term t2) -> {return t1.compareTo(t2);});
        assertEquals(index, 2);
        index = BinarySearchForAll.firstIndexOf(repeatTerms, new Term("Shirt", 4), (Term t1, Term t2) -> {return t1.compareTo(t2);});
        assertEquals(index, 402);
        index = BinarySearchForAll.firstIndexOf(repeatTerms, new Term("Hello", 4), (Term t1, Term t2) -> {return t1.compareTo(t2);});
        assertEquals(index, -1);
        index = BinarySearchForAll.firstIndexOf(repeatTerms, new Term("Dress", 2), (Term t1, Term t2) -> {return t1.compareTo(t2);});
        assertEquals(index, 0);
    }

    @Test
    public void testLastIndexOfReverseWeight() {
        alphaTerms.add(new Term("a", 0));
        alphaTerms.add(new Term("b", 1));
        alphaTerms.sort(Term.byReverseWeightOrder());
        int index = BinarySearchForAll.lastIndexOf(alphaTerms, new Term("a", 0), Term.byReverseWeightOrder());
        assertEquals(27, index);
        index = BinarySearchForAll.lastIndexOf(alphaTerms, new Term("b", 1), Term.byReverseWeightOrder());
        assertEquals(25, index);

        alphaTerms2.sort(Term.byReverseWeightOrder());
        alphaTerms2.add(new Term("ceee", 2));
        alphaTerms2.add(new Term("ceee", 2));
        index = BinarySearchForAll.lastIndexOf(alphaTerms2, new Term("ceee", 2), Term.byReverseWeightOrder());
        assertEquals(53, index);

        repeatTerms.sort(Term.byReverseWeightOrder());
        index = BinarySearchForAll.lastIndexOf(repeatTerms, new Term("Shawl", 4), Term.byReverseWeightOrder());
        assertEquals(index, 799);
        index = BinarySearchForAll.lastIndexOf(repeatTerms, new Term("Shirt", 4), Term.byReverseWeightOrder());
        assertEquals(index, 799);
        index = BinarySearchForAll.lastIndexOf(repeatTerms, new Term("Hello", 4), Term.byReverseWeightOrder());
        assertEquals(index, 799);
        index = BinarySearchForAll.lastIndexOf(repeatTerms, new Term("Dress", 2), Term.byReverseWeightOrder());
        assertEquals(index, 801);
    }
 
    @Test
    public void testLastIndexOfPrefix() {
        alphaTerms2.add(new Term("sep", 0));
        alphaTerms2.add(new Term("neato", 1));
        alphaTerms2.add(new Term("neato", 1));
        alphaTerms2.sort(Term.byPrefixOrder(1));
        int index = BinarySearchForAll.lastIndexOf(alphaTerms2, new Term("sep", 0), Term.byPrefixOrder(1));
        assertEquals(40, index);
        alphaTerms2.sort(Term.byPrefixOrder(2));      
        index = BinarySearchForAll.lastIndexOf(alphaTerms2, new Term("sep", 0), Term.byPrefixOrder(2));
        assertEquals(39, index);
        index = BinarySearchForAll.lastIndexOf(alphaTerms2, new Term("neato", 1), Term.byPrefixOrder(2));
        assertEquals(28, index);
        assertEquals(alphaTerms2.get(26).key, "neee");
        assertEquals(alphaTerms2.get(28).key, "neato");

        repeatTerms.sort(Term.byPrefixOrder(1));
        index = BinarySearchForAll.lastIndexOf(repeatTerms, new Term("Shawl", 4), Term.byPrefixOrder(1));
        assertEquals(index, 802);
        index = BinarySearchForAll.lastIndexOf(repeatTerms, new Term("Shirt", 4), Term.byPrefixOrder(1));
        assertEquals(index, 802);
        index = BinarySearchForAll.lastIndexOf(repeatTerms, new Term("Hello", 4), Term.byPrefixOrder(1));
        assertEquals(index, -1);
        index = BinarySearchForAll.lastIndexOf(repeatTerms, new Term("Dress", 2), Term.byPrefixOrder(1));
        assertEquals(index, 0);

        repeatTerms.sort(Term.byPrefixOrder(3));
        index = BinarySearchForAll.lastIndexOf(repeatTerms, new Term("Shawl", 4), Term.byPrefixOrder(3));
        assertEquals(index, 401);
        index = BinarySearchForAll.lastIndexOf(repeatTerms, new Term("Shirt", 4), Term.byPrefixOrder(3));
        assertEquals(index, 801);
        index = BinarySearchForAll.lastIndexOf(repeatTerms, new Term("Hello", 4), Term.byPrefixOrder(3));
        assertEquals(index, -1);
        index = BinarySearchForAll.lastIndexOf(repeatTerms, new Term("Dress", 2), Term.byPrefixOrder(3));
        assertEquals(index, 0);
    }

    @Test
    public void testLastIndexOfDefault() {
        alphaTerms2.add(new Term("sup", 0));
        alphaTerms2.add(new Term("sup", 0));
        alphaTerms2.add(new Term("neato", 1));
        alphaTerms2.add(new Term("neato", 1));
        Collections.sort(alphaTerms2);
        int index = BinarySearchForAll.lastIndexOf(alphaTerms2, new Term("sup", 0), (Term t1, Term t2) -> {return t1.compareTo(t2);});
        assertEquals(41, index);
        index = BinarySearchForAll.lastIndexOf(alphaTerms2, new Term("neato", 1), (Term t1, Term t2) -> {return t1.compareTo(t2);});
        assertEquals(27, index);

        Collections.sort(repeatTerms);
        index = BinarySearchForAll.lastIndexOf(repeatTerms, new Term("Shawl", 4), (Term t1, Term t2) -> {return t1.compareTo(t2);});
        assertEquals(index, 401);
        index = BinarySearchForAll.lastIndexOf(repeatTerms, new Term("Shirt", 4), (Term t1, Term t2) -> {return t1.compareTo(t2);});
        assertEquals(index, 801);
        index = BinarySearchForAll.lastIndexOf(repeatTerms, new Term("Hello", 4), (Term t1, Term t2) -> {return t1.compareTo(t2);});
        assertEquals(index, -1);
        index = BinarySearchForAll.lastIndexOf(repeatTerms, new Term("Dress", 2), (Term t1, Term t2) -> {return t1.compareTo(t2);});
        assertEquals(index, 0);
    }
    
}
