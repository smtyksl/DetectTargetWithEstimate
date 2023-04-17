**Java’da Apache Kafka sunucusu çalıştırmak için şu adımları izleyebilirsiniz:**

1. **Kafka’yı indirin: İlk olarak, Apache Kafka indirmeler sayfasından en son Kafka ikili dosyasını indirin.**
2. **Java’yı yükleyin: Makinenizde Java yüklü olduğundan emin olun. Değilse, Java web sitesinden Java’yı yükleyin.**
3. **ZooKeeper’ı başlatın: Kafka, küme yönetimi için ZooKeeper’ı kullanır, bu nedenle önce ZooKeeper’ı çalıştırarak başlayın. Bunun için **`bin/zookeeper-server-start.sh config/zookeeper.properties` komutunu çalıştırın.
4. **Kafka broker’ını başlatın: ZooKeeper çalışır durumdayken, Kafka broker’ını çalıştırmak için **`bin/kafka-server-start.sh config/server.properties` komutunu çalıştırın.
5. **Bir konu oluşturun: Şimdi, **`bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic yourTopicName` komutunu çalıştırarak bir konu oluşturabilirsiniz.
6. **Mesaj gönderin ve alın: Kafka üreticisini konunuza mesaj göndermek ve Kafka tüketicisini konunuzdan mesaj almak için kullanabilirsiniz.**

**Bu kadar! Artık Java’da çalışan bir Apache Kafka sunucusunuz var.**

# Proje Hakkında Bilgiler(kütüphane, teknoloji)

* Apache Kafka projesi, birçok farklı kütüphaneyi kullanarak geliştirilmiştir. Kafka’nın Java istemcisi yani kafka-clients kütüphanesi projede kullanılmıştır. Ayrıca projenin test aşamasında, JUnit kütüphanesi kullanılmıştır.
* Projenin çalıştırılması için minimum gereksinimlerden biri de JavaFX kütüphanesidir. Bu nedenle projenin çalışması için JavaFX’in kurulu olması gerekir.Ayrıca, Google’ın Protobuf (Protocol Buffer) kütüphanesi de Kafka projesi için kullanılmıştır.
* Projenin pom.xml dosyasına baktığımızda, kullanılan kütüphanelerin sürümleri de belirtilmiştir. Kafka’nın Java istemcisi 2.8.0 sürümü kullanılırken, JUnit 4.13.2 sürümüne yer verilmiştir. JavaFX kütüphanesinin 16 sürümü, Protobuf kütüphanesinin de 3.22.2 sürümleri kullanılmıştır.
* Bu bilgiler doğrultusunda, projenin başarılı bir şekilde derlenip çalıştırılabilmesi için yukarıdaki kütüphanelerin ve sürümlerinin kurulu olduğundan emin olunması gerekmektedir.

java versiyon bilgileri:

openjdk 17.0.6 2023-01-17

OpenJDK Runtime Environment (build 17.0.6+10-Ubuntu-0ubuntu122.04)

OpenJDK 64-Bit Server VM (build 17.0.6+10-Ubuntu-0ubuntu122.04, mixed mode, sharing)

# Protobuf Derlemek için

protoc --proto_path=your-proto --java_out=build/gen src/foo.proto
