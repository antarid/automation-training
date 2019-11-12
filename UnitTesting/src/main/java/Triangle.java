public class Triangle {
    public static boolean checkIfExists(int a, int b, int c){
        if (a <= 0 || b <= 0 || c <= 0)
            throw new IllegalArgumentException();
        int aAndB = a + b;
        int aAndC = a + c;
        int bAndC = b + c;
        if(aAndB <= 0 || aAndC <= 0 || bAndC <= 0)
            throw new IllegalArgumentException();

        return aAndB > c && aAndC > b && bAndC > a;
    }
}
