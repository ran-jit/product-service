package com.product.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/** author: Ranjith Manickam @ 21 July' 2019 */
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ErrorCode {
    private Integer code;
    private ErrorType type;
    private String message;
}
