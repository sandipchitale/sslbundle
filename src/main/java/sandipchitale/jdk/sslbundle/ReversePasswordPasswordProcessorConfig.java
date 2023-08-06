package sandipchitale.jdk.sslbundle;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Configuration
public class ReversePasswordPasswordProcessorConfig {

    @Component
    @Primary
    public static class ReversePasswordPasswordProcessor implements PasswordProcessor {
        @Override
        public char[] process(char[] password) {
            return new StringBuffer(new String(password)).reverse().toString().toCharArray();
        }
    }
}
