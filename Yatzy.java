import java.util.Arrays;
import java.util.stream.IntStream;

public class Yatzy {

    private int[] dice;


    public Yatzy(int d1, int d2, int d3, int d4, int d5) {

        dice = new int[]{d1, d2, d3, d4, d5};

    }

    //use short cercuiting
    public static int yatzy(int... dice) {
        int firstElement = dice[0];
        final boolean isYatzy = Arrays.stream(dice)
                .allMatch(value -> firstElement == value);
        return isYatzy ? 50 : 0;

    }


    public static int chance(int d1, int d2, int d3, int d4, int d5) {
        return Arrays.stream(new int[]{d1, d2, d3, d4, d5})
                .sum();

    }


    public static int ones(int d1, int d2, int d3, int d4, int d5) {
        return getSum(1, d1, d2, d3, d4, d5);

    }

    public static int twos(int d1, int d2, int d3, int d4, int d5) {

        return getSum(2, d1, d2, d3, d4, d5);
    }

    public static int threes(int d1, int d2, int d3, int d4, int d5) {
        return getSum(3, d1, d2, d3, d4, d5);
    }


    public int fours() {
        return getSum(4, dice);

    }

    public int fives() {

        return getSum(5, dice);
    }

    public int sixes() {

        return getSum(6, dice);
    }

    public static int score_pair(int d1, int d2, int d3, int d4, int d5) {


        return 2 * Arrays.stream(getNOfAKind(2, d1, d2, d3, d4, d5))
                .max()
                .orElse(0);
    }

    //return the sum of all dice that validate condition
    private static int getSum(int valueToCheck, int... dices) {
        return Arrays.stream(dices)
                .filter(value -> value == valueToCheck)
                .sum();

    }
   //fill table with all duplicated values or not
    private static int[] getCounts(int d1, int d2, int d3, int d4, int d5) {
        int[] counts = new int[6];
        Arrays.stream(new int[]{d1, d2, d3, d4, d5})
                .forEach(d -> counts[d - 1]++);
        return counts;
    }
    // return table with number of kind
    private static int[] getNOfAKind(int n, int d1, int d2, int d3, int d4, int d5) {

        int[] counts = getCounts(d1, d2, d3, d4, d5);
        return IntStream.range(0, 6)
                .filter(i -> counts[i] >= n)
                .map(i -> i + 1)
                .toArray();

    }

    public static int two_pair(int d1, int d2, int d3, int d4, int d5) {
        int[] pairs = getNOfAKind(2, d1, d2, d3, d4, d5);
        if (pairs.length < 2) {
            return 0;
        }
        return Arrays.stream(pairs)
                .map(value -> 2 * value)
                .sum();
    }


    public static int four_of_a_kind(int d1, int d2, int d3, int d4, int d5) {
        int[] pairs = getNOfAKind(4, d1, d2, d3, d4, d5);
        return pairs.length > 0 ? 4 * pairs[0] : 0;
    }

    public static int three_of_a_kind(int d1, int d2, int d3, int d4, int d5) {
        int[] pairs = getNOfAKind(3, d1, d2, d3, d4, d5);
        return pairs.length > 0 ? 3 * pairs[0] : 0;

    }

    public static int smallStraight(int d1, int d2, int d3, int d4, int d5) {
        int[] tallies = getCounts(d1, d2, d3, d4, d5);
        return IntStream.range(0, 5)
                .filter(i -> tallies[i] == 1)
                .toArray().length == 5 ? 15 : 0;

    }

    public static int largeStraight(int d1, int d2, int d3, int d4, int d5) {
        int[] tallies = getCounts(d1, d2, d3, d4, d5);
        return IntStream.range(1, 6)
                .filter(i -> tallies[i] == 1)
                .toArray().length == 5 ? 20 : 0;
    }

    public static int fullHouse(int d1, int d2, int d3, int d4, int d5) {
        int[] kindThree = getNOfAKind(3, d1, d2, d3, d4, d5);
        int[] pairs = getNOfAKind(2, d1, d2, d3, d4, d5);

        if (kindThree.length != 1 || pairs.length != 2) {
            return 0;
        }
        return 3 * kindThree[0] + 2 * (kindThree[0] == pairs[0] ? pairs[1] : pairs[0]);
    }
}
