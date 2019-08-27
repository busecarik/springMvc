package com.busecarik;

@Food(price = 22.5)
public class Pizza {

    @Time(takes = 10)
    public void prepare() {}

    @Time(takes = 20)
    public void cook() {}

    @Time(takes = 15)
    public void send() {}
}
