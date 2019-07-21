package com.product.entry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/** author: Ranjith Manickam @ 21 July' 2019 */
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Response<ENTRY> implements Serializable {
    private Boolean status;
    private ENTRY data;
}
