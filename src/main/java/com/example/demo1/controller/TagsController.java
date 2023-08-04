package com.example.demo1.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo1.dao.Tags;
import com.example.demo1.mapper.TagsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@Slf4j
public class TagsController {

    @Autowired
    private TagsMapper tagsMapper;

    @RequestMapping("run")
    public void run() {
        List<Tags> list = tagsMapper.selectAll();
        // 写法1
        String fileName =  "D:\\桌面\\20230614活动执行所用标签\\"+ "BS_tags_id.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        List<List<String>> data = new ArrayList<>();
        for (Tags tags : list) {
            List<String> listTags = new ArrayList<>();
            listTags.add(tags.getCampaign());
            listTags.add(tags.getSegment());

            String segmentjson = tags.getSegmentjson();
            JSONArray objects = JSONObject.parseArray(segmentjson);
            for (Object object : objects) {
                String tag = object.toString();
                JSONObject jsonObject = JSONObject.parseObject(tag);
                if (jsonObject.get("tableName").equals("ex_h_member_tags")) {
                    JSONObject segmentJson = JSONObject.parseObject(jsonObject.get("segmentJson").toString());
                    JSONObject filter = JSONObject.parseObject(segmentJson.get("filter").toString());
                    JSONArray items = JSONObject.parseArray(filter.get("item").toString());
                    for (Object item : items) {
                        JSONObject itemsJSON = JSONObject.parseObject(JSONObject.toJSONString(item));
                        JSONArray items2 = null;
                        if (JSONObject.toJSONString(item).contains("item")) {
                            items2 = JSONObject.parseArray(itemsJSON.get("item").toString());
                        }
                        if (items2 == null) {
                            JSONObject items1JSON = JSONObject.parseObject(JSONObject.toJSONString(item));
                            JSONArray values = JSONObject.parseArray(items1JSON.get("value").toString());
                            for (Object value : values) {
                                JSONObject valueJSON = JSONObject.parseObject(JSONObject.toJSONString(value));
                                listTags.add( valueJSON.get("id") + ":" + valueJSON.get("name"));
                                listTags.add( valueJSON.get("id").toString());

                            }
                        } else {
                            for (Object item2 : items2) {
                                if (JSONObject.toJSONString(item2).contains("item")) {
                                     String item3 = JSONObject.parseObject(JSONObject.toJSONString(item2)).get("item").toString();
                                    for (Object item33 : JSONArray.parseArray(item3)) {
                                        JSONObject items3JSON = JSONObject.parseObject(JSONObject.toJSONString(item33));
                                        JSONArray values = JSONObject.parseArray(items3JSON.get("value").toString());
                                        for (Object value : values) {
                                            JSONObject valueJSON = JSONObject.parseObject(JSONObject.toJSONString(value));
                                            listTags.add( valueJSON.get("id") + ":" + valueJSON.get("name"));
                                            listTags.add( valueJSON.get("id").toString());

                                        }
                                    }

                                } else {
                                    JSONObject items2JSON = JSONObject.parseObject(JSONObject.toJSONString(item2));
                                    JSONArray values = JSONObject.parseArray(items2JSON.get("value").toString());
                                    for (Object value : values) {
                                        JSONObject valueJSON = JSONObject.parseObject(JSONObject.toJSONString(value));
                                        listTags.add( valueJSON.get("id") + ":" + valueJSON.get("name"));
                                        listTags.add( valueJSON.get("id").toString());
                                    }

                                }
                            }
                        }
                    }

                }

                if (jsonObject.get("tableName").equals("crm_member_tags")) {
                    JSONObject segmentJson = JSONObject.parseObject(jsonObject.get("segmentJson").toString());
                    JSONObject filter = JSONObject.parseObject(segmentJson.get("filter").toString());
                    JSONArray items = JSONObject.parseArray(filter.get("item").toString());
                    for (Object item : items) {
                        JSONObject itemsJSON = JSONObject.parseObject(JSONObject.toJSONString(item));
                        JSONArray items2 = null;

                        if (JSONObject.toJSONString(item).contains("item")) {
                            items2 = JSONObject.parseArray(itemsJSON.get("item").toString());
                        }

                        if (items2 == null) {
                            listTags.add( itemsJSON.get("tagId") + ":" + itemsJSON.get("tagCn"));
                            listTags.add( itemsJSON.get("tagId").toString());
                        } else {
                            for (Object item2 : items2) {
                                if (JSONObject.toJSONString(item2).contains("item")){
                                    JSONObject item23 = JSONObject.parseObject(JSONObject.toJSONString(item2));
                                    Object item3 = item23.get("item");
                                    JSONArray items3 = JSONObject.parseArray(JSONObject.toJSONString(item3));
                                    for (Object o : items3) {
                                        JSONObject items3JSON = JSONObject.parseObject(JSONObject.toJSONString(o));
                                        listTags.add(items3JSON.get("tagId") + ":" + items3JSON.get("tagCn"));
                                        listTags.add( items3JSON.get("tagId").toString());
                                    }
                                }else {
                                    JSONObject items2JSON = JSONObject.parseObject(JSONObject.toJSONString(item2));
                                    listTags.add(items2JSON.get("tagId") + ":" + items2JSON.get("tagCn"));
                                    listTags.add(items2JSON.get("tagId").toString());
                                }

                            }
                        }
                    }
                }
            }
            log.info("listTags:{}",JSONObject.toJSONString(listTags));
            data.add(listTags);

        }
        //调用方法
        EasyExcel.write(fileName).sheet("tags")
                .doWrite(data);

    }
}
