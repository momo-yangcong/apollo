package com.ctrip.framework.apollo.doc.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor()
public class CommonResult<T> {
    int code;
    String msg;
    T data;
    public CommonResult(T data){
        this(200,null,data);
    }

}
