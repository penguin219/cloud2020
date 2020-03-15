package com.wwy.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data //getter and setter
@AllArgsConstructor //全参构造器
@NoArgsConstructor //空参构造器
public class Payment implements Serializable {
    private Long id;
    private String serial;
}
