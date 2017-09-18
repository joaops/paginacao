package br.com.joaops.paginacao.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author João Paulo
 */
@Component
public class TesteTask {
    
    private static final Logger LOGGER = LogManager.getLogger(TesteTask.class);
    
    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
        // A Cada Minuto
        LOGGER.info("Hora: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
    }
    
}