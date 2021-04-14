import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Set;
import java.util.TreeSet;

public class Runner {
    public static void main(String[] args) throws Exception {

        URL oracle = new URL("https://www.otodom.pl/sprzedaz/mieszkanie/sopot/");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));

        String inputLine;
        StringBuilder stringBuilder = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            stringBuilder.append(inputLine);
            stringBuilder.append(System.lineSeparator());
        }
        in.close();
        String content = stringBuilder.toString();

        Set<String> setOfLinks = new TreeSet<>();

        for (int i = 0; i < content.length(); i++) {
            i = content.indexOf("https://www.otodom.pl/pl/oferta/", i);
            if (i < 0) {
                break;
            }
            String substring = content.substring(i);
            String link = substring.split(".html")[0];
            setOfLinks.add(link);
        }
        System.out.println(setOfLinks);
    }
}
