package com.op.paper;

import javax.validation.constraints.NotEmpty;

public class Paper {

    @NotEmpty
    private String type;
    @NotEmpty
    private String size;
    @NotEmpty
    private String count;
    @NotEmpty
    private String color;
}
