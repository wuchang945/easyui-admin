package kafka;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
//

/**
 *   http://mirrors.hust.edu.cn/apache/kafka/0.9.0.0/
 *   http://czj4451.iteye.com/blog/2041096
 *   window 下启动kafka
 bin\windows\zookeeper-server-start.bat config\zookeeper.properties
 bin\windows\kafka-server-start.bat config\server.properties
 */
public class KafkaConsumer {

    private final ConsumerConnector consumer;

    private KafkaConsumer() {
        Properties props = new Properties();
        props.put("zookeeper.connect", "127.0.0.1:2181");
        props.put("group.id", "jd-group");
        props.put("zookeeper.session.timeout.ms", "4000");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "smallest");
        props.put("serializer.class", "kafka.serializer.StringEncoder");

        ConsumerConfig config = new ConsumerConfig(props);

        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(config);
    }

    void consume() {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(KafkaProducer.TOPIC, new Integer(1));

        StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
        StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());

        Map<String, List<KafkaStream<String, String>>> consumerMap =
                consumer.createMessageStreams(topicCountMap,keyDecoder,valueDecoder);
        KafkaStream<String, String> stream = consumerMap.get(KafkaProducer.TOPIC).get(0);
        ConsumerIterator<String, String> it = stream.iterator();
        while (it.hasNext())
        {
            System.out.println(it.next().message());
        }
        System.out.println("finish");


    }

    //http://www.open-open.com/lib/view/open1412991579999.html
    public static void main(String[] args) {
        new KafkaConsumer().consume();
    }
}