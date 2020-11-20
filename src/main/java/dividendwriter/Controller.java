package dividendwriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Controller {

    public static void main(String[] args) throws IOException {

        ArrayList<String> arrayTicker = new ArrayList<>();
        arrayTicker.add("СБЕРБАНК АО");
        arrayTicker.add("АФК «СИСТЕМА» АО");
        arrayTicker.add("ГАЗПРОМ АО");
        arrayTicker.add("СУРГУТНЕФТЕГАЗ АП");

        HashMap<String, String> dataForInsert = new HashMap<>();
        System.out.println("test");
        for (int i = 2010; i < 2021; i++) {

            Document doc = Jsoup.connect("https://bcs-express.ru/dividednyj-kalendar?year="+String.valueOf(i)).get();

            Elements elem = doc.select("div.dividends-table__row, div._item"); //<div class="summaryTableLine"><span>Скол. средние:</span><span class="greenFont bold">Покупать</span><span><i class="noBold">Покупать</i> <i id="maBuy">(8)</i></span><span><i class="noBold">Продавать</i> <i id="maSell">(4)</i></span></div>

            //Сбер, АФК, ГАЗПРОМ и СургутПреф
            for (Element elements : elem) {
                for (int j = 0; j < elements.childNodeSize(); j++) {
                    if (!"".equals(elements.childNode(j).toString().trim())) {
                        Elements elem2 = elements.select("div._title");
                        Element elements2 = elem2.first();
                        String nameFirm = elements2.childNode(0).toString().trim().toUpperCase();
                        String nameFirmWithoutYear = nameFirm.substring(0, nameFirm.length() - 4).trim();
                        String yearPayment = nameFirm.substring(nameFirm.length() - 4, nameFirm.length()).trim();

                        if (arrayTicker.contains(nameFirmWithoutYear)) {

                            try {
                                Elements elem3 = elements.select("div._last-day");
                                Element elements3 = elem3.first();
                                Elements elem4 = elements.select("span.dividends-table__value");
                                Element elements4 = elem4.first();

                                Elements elem5 = elements.select("div._value");
                                Element elements5 = elem5.first();

                                Elements elem6 = elements.select("div._price");
                                Element elements6 = elem6.first();
                                Elements elem61 = elements6.select("span.dividends-table__value");
                                Element elements61 = elem61.first();

                                Elements elem7 = elements.select("div._profit");
                                Element elements7 = elem7.first();
                                Elements elem71 = elements7.select("span.dividends-table__value");
                                Element elements71 = elem71.first();
                                //System.out.println(elements2.childNode(0).toString().trim());

                                String forInsert = elements3.childNode(0).toString().trim() + ";"
                                        + elements4.childNode(0).toString().trim() + ";"
                                        + elements5.childNode(0).toString().trim() + ";"
                                        + elements61.childNode(0).toString().trim() + ";"
                                        + elements71.childNode(0).toString().trim();

                                dataForInsert.put(nameFirmWithoutYear + ";" + yearPayment, forInsert);
                            } catch (Exception e) {
                                System.out.println("Вложенный элемент - не несет данные");
                            }

                            break;
                        }
                    }
                }


            }

        }
        for (Map.Entry<String, String> entry : dataForInsert.entrySet()) {
            System.out.println(entry);
        }
    }
}
