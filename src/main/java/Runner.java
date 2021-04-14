import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Set;
import java.util.TreeSet;

public class Runner {
    public static void main(String[] args) throws Exception {
        String constance = "https://www.x-kom.pl";
        String keyWord1 = "notebook";
        String keyWord2 = "laptop";

        URL oracle = new URL("https://www.x-kom.pl/g-2/c/2158-laptopy-2-w-1.html");



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
        System.out.println(content);
        Set<String> setOfLinks = new TreeSet<>();

        for (int i = 0; i < content.length(); i++) {
            i = content.indexOf("/p/", i);
            if (i < 0) {
                break;
            }
            String substring = content.substring(i);
            String link = substring.split(".html")[0];
            if (link.contains(keyWord1)||link.contains(keyWord2))
            setOfLinks.add(constance+link);
        }
        System.out.println(setOfLinks);
    }
}
