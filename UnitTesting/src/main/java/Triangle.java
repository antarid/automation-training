public class Triangle {
    public static boolean checkIfExists(int a, int b, int c){
        if (a <= 0 || b <= 0 || c <= 0)
            throw new IllegalArgumentException();
        return (a + b) > c && (a + c) > b && (b + c) > a;
    }
}
