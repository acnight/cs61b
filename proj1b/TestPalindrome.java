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
        assertTrue(Palindrome.isPalindrome("A"));
        assertTrue(Palindrome.isPalindrome("noon"));
        assertTrue(Palindrome.isPalindrome("versionnoisrev"));
        assertFalse(Palindrome.isPalindrome("versiocoisre"));
        assertTrue(Palindrome.isPalindrome(""));
        assertTrue(Palindrome.isPalindrome(" "));
        assertFalse(Palindrome.isPalindrome("cat"));
        assertFalse(Palindrome.isPalindrome("versionnoisre"));
        assertFalse(Palindrome.isPalindrome("ersionnoisrev"));
        assertTrue(Palindrome.isPalindrome("ersionnoisre"));
    }
    @Test
    public void testIsPalindromeOffByOne() {
        OffByOne a = new OffByOne();
        assertTrue(Palindrome.isPalindrome("flake", a));
        assertTrue(Palindrome.isPalindrome("flke", a));
        assertFalse(Palindrome.isPalindrome("flqqqke", a));
    }
}

