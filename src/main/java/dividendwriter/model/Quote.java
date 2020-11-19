package dividendwriter.model;

//import org.apache.logging.log4j.Level;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.SQLException;

public class Quote {

    private String ticker;
    private String companyname;
    private String sector;
    private String idfinam;
    private String fullurl;
    private String market;
    private String currency;
    //public static final Logger logger = LogManager.getLogger(Quote.class);

    public Quote(String ticker, String idfinam) {
        this.ticker = ticker;
        this.idfinam = idfinam;
    }

    public Quote(String ticker, String companyname, String sector, String idfinam, String fullurl, String market, String currency) {
        this.ticker = ticker;
        this.companyname = companyname;
        this.sector = sector;
        this.idfinam = idfinam;
        this.fullurl = fullurl;
        this.market = market;
        this.currency = currency;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getIdfinam() {
        return idfinam;
    }

    public void setIdfinam(String idfinam) {
        this.idfinam = idfinam;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getFullurl() {
        return fullurl;
    }

    public void setFullurl(String fullurl) {
        this.fullurl = fullurl;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}

