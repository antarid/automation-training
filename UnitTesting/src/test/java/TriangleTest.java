import org.junit.Test;
import org.junit.Assert;

public class TriangleTest {
    @Test
    public void justTrianlge() {
        Assert.assertEquals(Triangle.checkIfExists(10, 11, 12), true);
    }

    @Test
    public void rightTriangle() {
        Assert.assertEquals(Triangle.checkIfExists(3, 4, 5), true);
    }

    @Test
    public void triangleWithAllSidesEqualLength() {
        Assert.assertEquals(Triangle.checkIfExists(3, 3, 3), true);
    }

    @Test
    public void triangleWithTwoSidesEqualLength() {
        Assert.assertEquals(Triangle.checkIfExists(3, 3, 5), true);
    }

    @Test
    public void notExistingTrianlge() {
        Assert.assertEquals(Triangle.checkIfExists(25, 5, 10), false);
    }

    @Test
    public void greaterOrEqual() { // a + b should be strictly greater than c
        Assert.assertEquals(Triangle.checkIfExists(10, 20, 10), false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeSide() {
        Triangle.checkIfExists(-10, 5, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void zeroSide() {
        Triangle.checkIfExists(0, 5, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void tooBigSum() {
        Triangle.checkIfExists(Integer.MAX_VALUE, 10, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void tooBigSum() {
        Triangle.checkIfExists(Integer.MAX_VALUE, 10, 10);
    }

}