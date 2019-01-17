package nl.hva.ict.ds.util;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class NameReaderTest {
    private String[] firstNames;
    private String[] lastNames;

    @Before
    public void setup() {
        firstNames = new NameReader("/firstnames.txt").getNames();
        lastNames = new NameReader("/lastnames.txt").getNames();
    }

    @Test
    public void shouldReadAllFirstNames() {

        assertEquals(192, firstNames.length);
    }

    @Test
    public void shouldContainKnownFirstNames() {
        Arrays.sort(firstNames);

        assertTrue(Arrays.binarySearch(firstNames, "Jelle") >= 0);
        assertTrue(Arrays.binarySearch(firstNames, "Jonathan") >= 0);
        assertTrue(Arrays.binarySearch(firstNames, "Milou") >= 0);
        assertTrue(Arrays.binarySearch(firstNames, "Daniël") >= 0);
        assertTrue(Arrays.binarySearch(firstNames, "Mohammed") >= 0);
        assertTrue(Arrays.binarySearch(firstNames, "Yara") >= 0);
    }
    @Test
    public void shouldReadAllLastNames() {
        assertEquals(100, lastNames.length);
    }

    @Test
    public void shouldContainKnownLastNames() {
        Arrays.sort(lastNames);

        assertTrue(Arrays.binarySearch(lastNames, "Brouwer") >= 0);
        assertTrue(Arrays.binarySearch(lastNames, "van Leeuwen") >= 0);
        assertTrue(Arrays.binarySearch(lastNames, "van de Pol") >= 0);
        assertTrue(Arrays.binarySearch(lastNames, "Jansen") >= 0);
        assertTrue(Arrays.binarySearch(lastNames, "Scholten") >= 0);
        assertTrue(Arrays.binarySearch(lastNames, "Postma") >= 0);
    }

}
