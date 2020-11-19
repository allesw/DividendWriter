package dividendwriter;


import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AdapterBD {
    private static volatile AdapterBD instance;
    private static volatile Config config;

    private static Connection connectWithDB = null;
    private static final String SQL_INSERT_HISTORY_QUOTE = "INSERT INTO history_quotes (ticker, per, tradedate, tradetime, openprice, highprice, lowprice, closeprice, vol) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String SQL_INSERT_QUOTE = "INSERT INTO quotes (companyname, sector, idfinam, fullurl, market, currency, ticker) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String SQL_UPDATE_QUOTE = "UPDATE quotes SET companyname = ?, sector = ?, idfinam = ?, fullurl = ?, market = ?, currency = ? WHERE ticker = ?;";
    private static final String SQL_SELECT_TICKER_QUOTE = "SELECT ticker FROM quotes WHERE ticker = ?;";
    private static final String SQL_DELETE_HISTORY_QUOTE = "DELETE FROM history_quotes WHERE ticker = ? and tradedate = ?";
    private static final String SQL_SELECT_HISTORY_QUOTE = "SELECT t1.ticker, t1.idfinam, MAX(COALESCE(tradedate, '2010-01-01')) AS lastdate FROM quotes AS t1 LEFT JOIN history_quotes AS t2 ON t1.ticker = t2.ticker group by t1.ticker, t1.idfinam";

    public static AdapterBD getInstance(Config quoteConfig) {
        AdapterBD localInstance = instance;
        config = quoteConfig;
        if (localInstance == null) {
            synchronized (AdapterBD.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new AdapterBD();
                    connect();
                }
            }
        }
        return localInstance;
    }

    private AdapterBD() {

    }

    private static void connect() {
        try {
            connectWithDB = DriverManager.getConnection(config.getHost(), config.getLogin(), config.getPassword());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean isConnect() {
        try {
            if (connectWithDB.isClosed()) {
                return false;
            } else {
                return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

    }

    /*public boolean insertQuoteHistories(ArrayList<QuoteHistory> histories) {

        //Удаляем старые записи по этой дате и тикеру что бы не дублировать
        //Формируем список для удаления (тикер, дата)
        HashMap<String, QuoteHistory> uniqueValue = new HashMap<>();
        for (QuoteHistory entry : histories) {
            uniqueValue.put(entry.getUniqueId(), entry);
        }

        try {
            PreparedStatement psDelete = connectWithDB.prepareStatement(SQL_DELETE_HISTORY_QUOTE);
            for (Map.Entry entry: uniqueValue.entrySet()) {
                QuoteHistory quoteHistory = (QuoteHistory) entry.getValue();
                psDelete.setString(1,quoteHistory.getTicker());
                psDelete.setDate(2,quoteHistory.getTradedate());
                psDelete.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //Вставляем полученные данные из histories в базу
        try {
            connectWithDB.setAutoCommit(false);
            PreparedStatement psInsert = connectWithDB.prepareStatement(SQL_INSERT_HISTORY_QUOTE);
            for (QuoteHistory quoteHistory : histories) {
                psInsert.setString(1, quoteHistory.getTicker());
                psInsert.setInt(2, quoteHistory.getPer());
                psInsert.setDate(3, quoteHistory.getTradedate());
                psInsert.setTime(4, quoteHistory.getTradetime());
                psInsert.setDouble(5, quoteHistory.getOpenprice());
                psInsert.setDouble(6, quoteHistory.getHighprice());
                psInsert.setDouble(7, quoteHistory.getLowprice());
                psInsert.setDouble(8, quoteHistory.getCloseprice());
                psInsert.setDouble(9, quoteHistory.getVol());
                psInsert.executeUpdate();
            }

            connectWithDB.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void insertQuote(Quote quote) throws SQLException {

        if (quote == null) {
            throw new SQLException("Не передан объект для записи function insertQuote");
        }

        PreparedStatement psSelect = connectWithDB.prepareStatement(SQL_SELECT_TICKER_QUOTE);
        psSelect.setString(1, quote.getTicker());

        ResultSet result = psSelect.executeQuery();

        PreparedStatement psInsertUpdate = null;

        if (result.next()) {
            psInsertUpdate = connectWithDB.prepareStatement(SQL_UPDATE_QUOTE);
        } else {
            psInsertUpdate = connectWithDB.prepareStatement(SQL_INSERT_QUOTE);
        }

        psInsertUpdate.setString(1, quote.getCompanyname());
        psInsertUpdate.setString(2, quote.getSector());
        psInsertUpdate.setString(3, quote.getIdfinam());
        psInsertUpdate.setString(4, quote.getFullurl());
        psInsertUpdate.setString(5, quote.getMarket());
        psInsertUpdate.setString(6, quote.getCurrency());
        psInsertUpdate.setString(7, quote.getTicker());

        psInsertUpdate.executeUpdate();
    }

    public HashMap<Quote, Date> getSelectDataQoutes(ArrayList<String> tickersArray) throws SQLException {

        HashMap<Quote, Date> quoteMap = new HashMap<>();

        PreparedStatement psSelect = connectWithDB.prepareStatement(SQL_SELECT_HISTORY_QUOTE);
        ResultSet result = psSelect.executeQuery();
        while (result.next()) {
            String ticker = result.getString("ticker");
            String idfinam = result.getString("idfinam");
            Date lastDate = result.getDate("lastdate");
            if (tickersArray.size() > 0) {
                if (tickersArray.contains(ticker)) {
                    Quote quote = new Quote(ticker, idfinam);
                    quoteMap.put(quote, lastDate);
                }
            } else {
                Quote quote = new Quote(ticker, idfinam);
                quoteMap.put(quote, lastDate);
            }
        }

        result.close();
        psSelect.close();

        return quoteMap;
    } */
}