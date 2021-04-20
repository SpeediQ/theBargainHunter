import java.io.BufferedReader;
import java.io.IOException;
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

        List<Item> listOfItems = new ArrayList<>();

        String myLink = "https://www.x-kom.pl/g-2/c/1530-notebooki-laptopy-17-3.html";

        URL url = new URL(myLink + "?page=" + 1 + "&per_page=90");

        int maxPage = getMaxPage(url);

        for (int i = 1; i <= maxPage; i++) {
            url = new URL(myLink + "?page=" + i + "&per_page=90");
            createListOfItemsFromUrl(listOfItems, url);
        }

        showTheResult(listOfItems);
        System.out.println(listOfItems.size());

        System.out.println(getSource(new URL("https://www.x-kom.pl/")));


        long end = System.currentTimeMillis();

    }

    private static int getMaxPage(URL url) throws IOException {
        String source = getSource(url);
        int i = source.indexOf("ekasrY\">z");
        String substring = source.substring(i);
        String s = source.substring(i).split("</span>")[0].substring(10);
        return Integer.parseInt(s);

    }

    private static void createListOfItemsFromUrl(List<Item> listOfItems, URL oracle) throws IOException {
        String content = getSource(oracle);

        List<String> listOfLinks = getListOfLinks(content);

        addItemsToList(listOfItems, listOfLinks);


    }

    private static void showTheResult(List<Item> listOfItems) {
        for (Item listOfItem : listOfItems) {
            System.out.println(listOfItem);
        }
        System.out.println();
        System.out.println("Biggest Promotion: " + listOfItems.get(listOfItems.size() - 1));
    }

    private static void addItemsToList(List<Item> listOfItems, List<String> listOfLinks) {
        for (String link : listOfLinks) {


            Pattern pattern = Pattern.compile("\"price\":(\\d{1,8}),\"oldPrice\":(\\w{1,10})\\S{1,100}name\":\"(\\w{1,25})\\S{1,500}\"id\":\"(\\d{1,15})\",\"name\":\"(.{1,150})");
            Matcher matcher = pattern.matcher(link);
            int oldPrice = 0;

            while (matcher.find()) {
                if (matcher.group(2).equals("null")) {
                    oldPrice = Integer.parseInt(matcher.group(1));
                } else {
                    oldPrice = Integer.parseInt(matcher.group(2));
                }

                listOfItems.add(new Item(matcher.group(3) + ": " + matcher.group(5), Integer.parseInt(matcher.group(1)), oldPrice, matcher.group(4)));
            }

        }
        listOfItems.sort(Comparator.comparing(Item::getDifference));
    }

    private static List<String> getListOfLinks(String content) {
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
        return listOfLinks;
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
