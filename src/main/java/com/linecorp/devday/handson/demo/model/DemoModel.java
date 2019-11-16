package com.linecorp.devday.handson.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class DemoModel {

    private Map<String, String> args;

}
