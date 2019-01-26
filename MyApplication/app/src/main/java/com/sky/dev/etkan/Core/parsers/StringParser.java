package com.sky.dev.etkan.Core.parsers;

import com.tradinos.network.TradinosParser;

public class StringParser implements TradinosParser<String> {
    @Override
    public String Parse(String text) {
        return text;
    }
}
