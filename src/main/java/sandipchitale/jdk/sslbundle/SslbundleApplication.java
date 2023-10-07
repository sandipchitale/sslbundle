package sandipchitale.jdk.sslbundle;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ssl.SslBundle;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import java.security.KeyStore;

@SpringBootApplication
public class SslbundleApplication {

	@Profile("printCertificates")
	@Bean
	public CommandLineRunner printCertificates(SslBundles sslBundles) {
		return (args) -> {
			SslBundle sslBundle = sslBundles.getBundle(JDKSslBundleRegistrar.JAVA_CACERTS_BUNDLE);
			if (sslBundle != null) {
				KeyStore trustStore = sslBundle.getStores().getTrustStore();
				trustStore.aliases().asIterator().forEachRemaining(alias -> {
					try {
						System.out.println(
								"\n--------------------------------------------------------------------" +
								"\nAlias: " + alias +
								"\nCertificate: " + trustStore.getCertificate(alias));
					} catch (Exception ignore) {
					}
				});
			}
		};
	}

	@Bean
	public CommandLineRunner postmanEchoGet(SslBundles sslBundles, RestTemplateBuilder restTemplateBuilder) {
		return (args) -> {
			SslBundle sslBundle = sslBundles.getBundle(JDKSslBundleRegistrar.JAVA_CACERTS_BUNDLE);
			if (sslBundle != null) {
				RestTemplate restTemplate = restTemplateBuilder.setSslBundle(sslBundle).build();
				System.out.println(restTemplate.getForEntity("https://postman-echo.com/get", String.class).getBody());
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SslbundleApplication.class, args);
	}
}
