package com.jpract.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * 简易 CSV generate utils.
 *
 * @author fengjiantao.
 * @since 6/1/17.
 */
@Slf4j
public class CSVUtils {

    /**
     * 消费 csv 格式的上传文件, 并转化为 csvRecord 进行 foreach 回调操作, 需要传递 header - 文件流, 并传递回调函数执行
     * record foreach 回调过程.
     *
     * @param header            csv header.
     * @param fileStream        csv file stream.
     * @param csvRecordConsumer record consumer.
     */
    public static void consumeTheCSVFile(String[] header, InputStream fileStream, Consumer<CSVRecord> csvRecordConsumer) {
        Objects.requireNonNull(header, "header should not null.");
        Objects.requireNonNull(fileStream, "file stream should not null.");
        Objects.requireNonNull(csvRecordConsumer, "consumer should not null.");
        CSVFormat format = CSVFormat.DEFAULT.withHeader(header).withSkipHeaderRecord();
        try (Reader in = new InputStreamReader(fileStream)) {
            Iterable<CSVRecord> records = format.parse(in);
            records.forEach(csvRecordConsumer);
        } catch (Exception e) {
            log.error("When import black list error occur!", e);
        }
    }

    public static String generateCSVContent(Map<String, String> headerMap, List<Map<String, String>> data) {
        StringBuilder result = new StringBuilder();
        // BOM code - for windows.
        result.append("\ufeff");
        CSVFormat format = CSVFormat.DEFAULT.withHeader(generateHeaderArray(headerMap));
        try {
            CSVPrinter printer = new CSVPrinter(result, format);
            writeContent(headerMap, data, printer);
            return result.toString();
        } catch (IOException e) {
            log.error("When gen csv content error occur", e);
            return StringUtils.EMPTY;
        }
    }

    private static String[] generateHeaderArray(Map<String, String> headerMap) {
        // 写入文件头部
        List<Map.Entry<String, String>> entryList = Lists.newArrayList(headerMap.entrySet());
        String[] headerArray = new String[entryList.size()];

        for (int i = 0; i < entryList.size(); i++) {
            String header = entryList.get(i).getValue();
            headerArray[i] = header;
        }

        return headerArray;
    }

    private static void writeContent(Map<String, String> headerMap, List<Map<String, String>> data, CSVPrinter printer)
            throws IOException {
        // 写文件内容
        List<Map.Entry<String, String>> entryList = Lists.newArrayList(headerMap.entrySet());
        for (Map<String, String> dataItem : data) {
            List<String> records = Lists.newArrayList();
            for (Map.Entry<String, String> anEntryList : entryList) {
                String cellKey = anEntryList.getKey();
                String cellValue = dataItem.get(cellKey);
                records.add(cellValue);
            }
            printer.printRecord(records);
        }
    }
}
