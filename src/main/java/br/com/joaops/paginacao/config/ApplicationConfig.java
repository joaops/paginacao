package br.com.joaops.paginacao.config;

import br.com.joaops.paginacao.Main;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

/**
 *
 * @author João Paulo
 */
@EnableAsync
@Configuration
@EnableScheduling
@ComponentScan(basePackages = "br.com.joaops.paginacao",
        excludeFilters=@ComponentScan.Filter(
                type=FilterType.REGEX,
                pattern={
                    "br.com.joaops.paginacao.model.*"
                }
        )
)
public class ApplicationConfig {
    
    @Bean
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler();
    }
    
}