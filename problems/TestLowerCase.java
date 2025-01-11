import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Locale;

public class TestLowerCase {

    public static void main (String[] argv) throws UnsupportedEncodingException {
        if (null instanceof String) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
        String x = "11";
        switch (x) {
            case "0":
                x = "1";
        }
        System.out.println(x);
    }

    private static Integer extracted(Integer i) {
        for (int ii=0;ii<10;ii++) {
            i++;
        }
        return i;
    }
}
