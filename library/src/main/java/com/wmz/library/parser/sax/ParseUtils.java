package com.wmz.library.parser.sax;

import com.wmz.library.utils.StringUtils;

import java.io.InputStream;

/**
 * 解析工具类
 * Created by wmz on 19/6/16.
 */
public class ParseUtils {


    /**
     * 解析字符串xml
     *
     * @param content
     * @param baseParserHandler
     */
    public static void parse(String content, BaseParserHandler baseParserHandler) {
        InputStream is = StringUtils.stringConvertToInputStream(content);
        parse(is, baseParserHandler);
    }

    /**
     * 解析数据流xml
     *
     * @param is
     * @param baseParserHandler
     */
    public static void parse(InputStream is, BaseParserHandler baseParserHandler) {
        BaseParser mBaseParser = new BaseParser(is, baseParserHandler);
        mBaseParser.parse();
    }

}
