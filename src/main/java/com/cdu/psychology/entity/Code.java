package com.cdu.psychology.entity;
import lombok.Getter;
import lombok.Setter;
public class Code {
    @Getter @Setter public int id;
    @Getter @Setter public String code;
    @Getter @Setter public int used; //是否用过，1代表用过，0为默认值
}
