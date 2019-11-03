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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
