import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIClient {

    public static void main(String[] args) {
        // Replace with the actual API URL you want to call
        String apiUrl = "https://www.deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                System.out.println("API Response:\n" + response.toString());
            } else {
                System.out.println("API request failed with response code: " + responseCode);
            }
            connection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
