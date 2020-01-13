package com.corbinhughes.jsonlogs.controllers;

import static net.logstash.logback.argument.StructuredArguments.keyValue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;

@RestController
public class LoggingController {
  private final Logger logger = LoggerFactory.getLogger(getClass());

  @RequestMapping("/")
  public String index() {
    return "Hello there";
  }

  @RequestMapping("/warn")
  public String warn() {
    logger.warn("oh no, here's a warning", ImmutableMap.of("foo", "bar"));

    return "logged warning";
  }

  @RequestMapping("/info")
  public String info() {
    logger.info("here's some info", keyValue("name", "Tom"));

    return "logged info";
  }

  @RequestMapping("/sleep")
  public String sleep() throws InterruptedException {
    int sleepMillis = (int) (2000 * Math.random());
    Thread.sleep(sleepMillis);
    return String.format("slept %d ms", sleepMillis);
  }

  @RequestMapping("/exception")
  public String exception() {
    throw new RuntimeException("oh no, an error happened!");
  }
}
