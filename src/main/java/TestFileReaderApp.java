import org.testng.Assert;
import org.testng.annotations.Test;

public class TestFileReaderApp {

    @Test
    public void testFileReadApp() throws Exception{

        FileReaderApp fileReaderApp = new FileReaderApp();
        String result = fileReaderApp.testMain();

        Assert.assertEquals("Users: 10 | Marcin Nowak 29 333667888", result);
    }
}
