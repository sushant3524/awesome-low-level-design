
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Temp {
    public static void main (String[] argv) {
        String country = "italy";
        String urlString = "https://jsonmock.hackerrank.com/api/countries?name=" + country;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.addRequestProperty("Content-Type", "application/json");

            if (connection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                responseBuilder.append(line);
            }

            connection.disconnect();
            String toString = responseBuilder.toString();
            if (toString.contains("capital")) {
                String[] capitals = toString.split("capital\":\"");
                String capital = capitals[1];
                String ans = "";
                for (int i=0;i<capital.length();i++) {
                    if (capital.charAt(i) =='"') {
//                        return ans;
                    }
                    ans += capital.charAt(i);
                }
            } else {
//                return "-1";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
