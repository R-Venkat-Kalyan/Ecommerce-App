package com.ecom.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "build")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuildInfo {

    private String id;
    private String version;
    private String name;
}
