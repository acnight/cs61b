public class OffByN implements CharacterComparator {
    private int numberBetween;
    public OffByN(int N) {
        numberBetween = N;
    }
    @Override
    public boolean equalChars(char x, char y) {
        if (Math.abs(x - y) == numberBetween) {
            return true;
        }
        return false;
    }
}
