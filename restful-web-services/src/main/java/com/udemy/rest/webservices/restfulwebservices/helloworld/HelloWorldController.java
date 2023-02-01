package com.udemy.rest.webservices.restfulwebservices.helloworld;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

// rest api
@RestController
public class HelloWorldController
{
  private MessageSource messageSource;

  public HelloWorldController(MessageSource messageSource)
  {
    this.messageSource = messageSource;
  }

  // /hello-world

  //"Hello World"

  @GetMapping("/hello-world")
  public String helloWorld()
  {
    return "Hello World";
  }

  @GetMapping("/hello-world-bean")
  public HelloWorldBean helloWorldBean()
  {
    return new HelloWorldBean("Hello World");
  }

  @GetMapping("/hello-world-internationalized")
  public String helloWorldInternationalized()
  {
    Locale locale = LocaleContextHolder.getLocale();
    return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
  }

  @GetMapping("/hello-world/path-variable/{name}")
  public HelloWorldBean helloWorldPathVariable(@PathVariable String name)
  {
    return new HelloWorldBean(String.format("Hello World, %s", name));
  }

}
