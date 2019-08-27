package com.busecarik;

@Food(price = 30)
public class Sushi {

    @Time(takes = 30)
    public void prepare() {}

    public void cook() {}

    @Time(takes = 20)
    public void send() {}
}
