import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MenuCreator {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.x-kom.pl/");
        String source = getSource(url);
        System.out.println(listOfMenu(source));

        System.out.println();
        System.out.println(getSource(url));
    }

    private static List<String> listOfMenu (String source){
List<String> menuStringList = new ArrayList<>();



        for (int i = 0; i < source.length(); i++) {
            i = source.indexOf("iRTleC\">",i);
            if (i < 0) {
                break;
            }
             menuStringList.add( source.substring(i).split("</div>")[0].substring(8));




        }





        return menuStringList;
    }
    public static String getSource(URL oracle) throws IOException {
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
        return content;
    }
}
