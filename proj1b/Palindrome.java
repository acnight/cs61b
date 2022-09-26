public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> a = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            a.addLast(ch);
        }
        return a;
    }
    public boolean isPalindrome(String word) {
        if (word.length() < 2) {
            return true;
        }
        Deque<Character> a = wordToDeque(word);
        int s = a.size();
        for (int i = 0; i < s / 2; i++) {
            if (a.removeFirst() != a.removeLast()) {
                return false;
            }
        }
        return true;
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() < 2) {
            return true;
        }
        Deque<Character> a = wordToDeque(word);
        int s = a.size();
        for (int i = 0; i < s / 2; i++) {
            if (!cc.equalChars(a.removeFirst(), a.removeLast())) {
                return false;
            }
        }
        return true;
    }
}
