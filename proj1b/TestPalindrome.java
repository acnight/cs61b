import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("A"));
        assertTrue(palindrome.isPalindrome("noon"));
        assertTrue(palindrome.isPalindrome("versionnoisrev"));
        assertFalse(palindrome.isPalindrome("versiocoisre"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome(" "));
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("versionnoisre"));
        assertFalse(palindrome.isPalindrome("ersionnoisrev"));
        assertTrue(palindrome.isPalindrome("ersionnoisre"));
    }
    @Test
    public void testIsPalindromeOffByOne() {
        OffByOne a = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", a));
        assertTrue(palindrome.isPalindrome("flke", a));
        assertFalse(palindrome.isPalindrome("flqqqke", a));
    }
}

