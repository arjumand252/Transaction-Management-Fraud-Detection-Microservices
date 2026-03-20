package com.fraudapp.stream;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.kafka.streams.StreamsBuilder;
import com.fraudapp.dao.Transaction;

@Configuration
public class FraudStreamProcessor {
    @Bean
    public KStream<String, Transaction> process(StreamsBuilder builder){
        KStream<String, Transaction> stream = builder.stream("transaction-topic");
        
        stream.filter((key, txn) -> txn.getAmount() > 500000)
              .peek((key, txn) -> System.out.println("FRUAD DETECTED: " + txn.getId()))
              .to("fraud-alerts-topic");
        return stream;
    }
}
