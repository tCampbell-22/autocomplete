package autocomplete;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class AutocompleteTest {
    Autocomplete a;
    ArrayList<Term> terms;

    @BeforeEach
    public void setUp(){
        terms = new ArrayList<Term>();
        for (int i = 0; i < 26; i++) {
            terms.add(new Term("Shoe" + (char)(i + 97), i * 15));
        }

        for (int i = 0; i < 26; i++) {
            terms.add(new Term("Hat" + (char)(i + 97), i * 20));
        }

        for (int i = 0; i < 26; i++) {
            terms.add(new Term("Handbag" + (char)(i + 97), i * 19));
        }

        Collections.sort(terms);
        
        a = new Autocomplete(terms);
    }

    @Test
    public void testConstructor() {
        assertNotNull(a);
    }

    @Test
    public void testAllMatches() {
        List<Term> matches = a.allMatches("Ha");
        assertEquals(matches.get(0).key, "Hatz");
        assertTrue(matches.get(0).weight > matches.get(1).weight);
    }
}