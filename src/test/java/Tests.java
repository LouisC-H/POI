import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Tests {

    @Test
    void fullExample(){
        Assertions.assertEquals(405, Main.runDay13Code("src/main/resources/exampleP1.txt"));
    }

    @Test
    void fullExampleP2(){
        Assertions.assertEquals(400, Main.runDay13CodeP2("src/main/resources/exampleP1.txt"));
    }

}
