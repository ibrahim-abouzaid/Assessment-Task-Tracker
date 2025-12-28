package com.IAbouzaid.Task.Management.setting;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Setter
@Getter
@ConfigurationProperties(prefix = "token")
@Component
public class JWTToken {

    private String secret;
    private Duration time;
}