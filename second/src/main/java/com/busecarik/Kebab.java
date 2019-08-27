package com.busecarik;

@Food(price = 12.5)
public class Kebab {

    @Time(takes = 12)
    public void prepare() {}

    @Time(takes = 20)
    public void cook() {}

    @Time(takes = 10)
    public void send() {}
}
