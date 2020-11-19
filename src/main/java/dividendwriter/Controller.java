package dividendwriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeFilter;

import java.io.IOException;
import java.util.Date;

public class Controller {
    public static final String SBER = "СБЕРБАНК АО 2014";
    public static final String AFK = "АФК «СИСТЕМА» АО 2014";
    public static final String GP = "ГАЗПРОМ АО 2014";
    public static final String SURGUT = "СУРГУТНЕФТЕГАЗ АП 2014";

    public static void main(String[] args) throws IOException {
        System.out.println("test");
        Document doc = Jsoup.connect("https://bcs-express.ru/dividednyj-kalendar?year=2015").get();

        //Document document = Jsoup.parse("https://ru.investing.com/equities/microsoft-corp-technical", "https://ru.investing.com/equities/microsoft-corp-technical", Parser.htmlParser());
        Elements elem = doc.select("div._item"); //<div class="summaryTableLine"><span>Скол. средние:</span><span class="greenFont bold">Покупать</span><span><i class="noBold">Покупать</i> <i id="maBuy">(8)</i></span><span><i class="noBold">Продавать</i> <i id="maSell">(4)</i></span></div>
        String valueSlimeMidl = "";
        String valueTechMidl = "";

        //Сбер, АФК, ГАЗПРОМ и СургутПреф
        for (Element elements : elem) {
            for (int i = 0; i < elements.childNodeSize(); i++) {
                System.out.println(elements.childNode(1).toString());

//                if (!"".equals(elements.childNode(i).toString().trim())) {
//                    Elements elem2 = elements.select("div._title, div._last-day");
//                    for (Element elements2 : elem2) {
////                        if (SBER.equals(elements2.childNode(0).toString().trim().toUpperCase())) {
////                            Elements elem3 = elements.select("div.js-div-table-toggle");
////                        }
//                            System.out.println(elements2.childNode(0).toString());
//                    }
//
//                System.out.println(elements.childNode(i).toString().trim());
//                if (SLIME_MIDL.equals(elements.childNode(i).childNode(0).toString())) {
//                    valueSlimeMidl = elements.childNode(i + 1).childNode(0).toString();
//                }
//
//                if (TECH_IND.equals(elements.childNode(i).childNode(0).toString())) {
//                    valueTechMidl = elements.childNode(i + 1).childNode(0).toString();
//                }
  //              }
//            for (Element elemChild : elements.children()) {
//                if (elemChild.childNodeSize() > 0) {
//                    if (SLIME_MIDL.equals(elemChild.childNode(0)) {
//
//                    }
//
//
//                }
//
//            }
            }
        }
    }
}
