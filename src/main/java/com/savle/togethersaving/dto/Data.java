package com.savle.togethersaving.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Data<T> {

    private T data;

}
