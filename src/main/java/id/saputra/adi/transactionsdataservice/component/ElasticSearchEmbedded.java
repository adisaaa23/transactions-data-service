package id.saputra.adi.transactionsdataservice.component;

import org.springframework.stereotype.Component;
import pl.allegro.tech.embeddedelasticsearch.EmbeddedElastic;
import pl.allegro.tech.embeddedelasticsearch.PopularProperties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@Component
public class ElasticSearchEmbedded {

    private EmbeddedElastic embeddedElastic;

    @PostConstruct
    public void start() throws IOException, InterruptedException {
        embeddedElastic = EmbeddedElastic.builder()
                .withElasticVersion("5.0.1")
                .withSetting(PopularProperties.HTTP_PORT, 9200)
                .build();
        embeddedElastic.start();
    }

    @PreDestroy
    public void stop() {
        embeddedElastic.stop();
    }

}
