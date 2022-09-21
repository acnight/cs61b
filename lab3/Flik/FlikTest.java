import static org.junit.Assert.*;
import org.junit.Test;
public class FlikTest {
    @Test
    public void testIsSameNumber(){
            int a = 128;
            int b = 128;
            boolean c = Flik.isSameNumber(a, b);
            assertEquals(true, c);
    }
        }