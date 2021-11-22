package com.airqualityservice.application;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SidoType {
    seoul("서울시"),
    busan("부산시"),
    ;

    private final String description;

}
