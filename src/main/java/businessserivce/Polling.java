package businessserivce;

import api.CryptoAPI;
import entity.CandleStick;
import entity.TimeFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.Timer;

public class Polling {

    private final static Logger logger = LogManager.getLogger(Polling.class);

    public static CandleStick getTrade(String instrument, Date intTime, TimeFrame tF) throws InterruptedException {

        if (instrument == null) {
            logger.error("instrument == null");
        }

        if (intTime == null) {
            logger.error("intTime == null");
        }

        if (tF == null) {
            logger.error("tF == null");
        }

        Timer t = new Timer();
        CryptoAPI trade = new CryptoAPI(instrument, intTime.getTime(), tF, "getTrade");
        t.scheduleAtFixedRate(trade, intTime, 1L);

        while (!trade.getFinishPolling()) {
            Thread.sleep(1000);
        }

        return trade.getcS();
    }
}
