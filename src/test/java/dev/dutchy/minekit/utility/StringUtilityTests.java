package dev.dutchy.minekit.utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for the {@link StringUtility} class.
 */
public class StringUtilityTests
{

    @Test
    void testBasicWrap()
    {
        String input = "This is a simple test";
        String expected = "This is a\nsimple\ntest";
        assertEquals(expected, StringUtility.wrap(input, 10));
    }

    @Test
    void testLongWord()
    {
        String input = "This is a supercalifragilisticexpialidocious test";
        String expected = "This is a\nsupercalifragilisticexpialidocious\ntest";
        assertEquals(expected, StringUtility.wrap(input, 10));
    }

    @Test
    void testPunctuation()
    {
        String input = "Hello, world!";
        String expected = "Hello,\nworld!";
        assertEquals(expected, StringUtility.wrap(input, 8));
    }

    @Test
    void testMultipleWhitespace()
    {
        String input = "This    is a \t test";
        String expected = "This is a\ntest";
        assertEquals(expected, StringUtility.wrap(input, 10));
    }

    @Test
    void testUniformity()
    {
        String input = "This is a simple test to check the uniformity of the lines.";
        String expected = "This is a\nsimple test\nto check\nthe\nuniformity\nof the\nlines.";

        assertEquals(expected, StringUtility.wrap(input, 12));
    }

    @Test
    void testInvalidMaxLength()
    {
        assertThrows(IllegalArgumentException.class, () -> StringUtility.wrap("This is a test", -1));
    }

}
