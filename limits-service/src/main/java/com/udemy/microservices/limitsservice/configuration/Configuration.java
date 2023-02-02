package com.udemy.microservices.limitsservice.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("limits-service")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Configuration
{
  private int minimum;
  private int maximum;
}
