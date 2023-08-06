package sandipchitale.jdk.sslbundle;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class NoOpPasswordProcessorConfig {

    @Component
    public static class NoOpPasswordProcessor implements PasswordProcessor {
        @Override
        public char[] process(char[] password) {
            return password;
        }
    }
}
