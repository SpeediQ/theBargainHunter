import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//1552

public class Runner {
    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();

        String constance = "https://www.x-kom.pl";
        String keyWord1 = "notebook";
        String keyWord2 = "laptop";
        List<Item> listOfItems = new ArrayList<>();

        String myLink = "https://www.x-kom.pl/g-4/c/1590-smartfony-i-telefony.html";

        URL oracle = new URL(myLink+"?page=1&per_page=90");


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
//        System.out.println(content);
        List<String> listOfLinks = new ArrayList<>();

        for (int i = 0; i < content.length(); i++) {
            String value1 = "{\"price\":";
            String value2 = "\",\"price\":";
            i = content.indexOf(value1, i);
            if (i < 0) {
                break;
            }
            String substring = content.substring(i);
            String text = substring.split(value2)[0];


            listOfLinks.add(text);
        }
//        if (listOfLinks.size() > 0) {
//            for (int i = 0; i < listOfLinks.size(); i++) {
//                String item = listOfLinks.get(i);
//            }
//        }


        for (String listOfLink : listOfLinks) {

            Pattern pattern = Pattern.compile("(\\\"price\\\":)(\\d{1,6})(,\\\"oldPrice\\\":)(\\d{1,6}).{1,1000}(\\\"id\\\":\\\")(.{1,500})(\\\",\\\"name\\\":\\\")(.{1,500})");
            Matcher matcher = pattern.matcher(listOfLink);

            while (matcher.find()) {
                listOfItems.add(new Item(matcher.group(8), Integer.parseInt(matcher.group(4)), Integer.parseInt(matcher.group(2)),matcher.group(6)));
            }

        }
        listOfItems.sort(Comparator.comparing(Item::getDifference).reversed());

        for (Item listOfItem : listOfItems) {
            System.out.println(listOfItem);
        }
        long end = System.currentTimeMillis();
        System.out.println();
        System.out.println("Biggest Promotion: "+listOfItems.get(listOfItems.size()-1));



    }
}
