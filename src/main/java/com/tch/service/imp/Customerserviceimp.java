package com.tch.service.imp;

import com.tch.service.Customerservice;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * User: zzj
 * Date: 2017-09-21
 * Time: 14:58
 */
@Service
public class Customerserviceimp implements Customerservice{
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public void saveOrUpdate() {
        Map<String,Object> json=new HashMap<String, Object>();
        json.put("username","zzj");
        json.put("age",12);
        json.put("sex","男");
        IndexResponse response=elasticsearchTemplate.getClient().prepareIndex("ku","biao").setSource(json, XContentType.JSON).get();
       // System.out.println(elasticsearchTemplate.indexExists(Customer.class));
        // Index name
        String _index = response.getIndex();
// Type name
        String _type = response.getType();
// Document ID (generated or not)
        String _id = response.getId();
// Version (if it's the first time you index this document, you will get: 1)
        long _version = response.getVersion();
// status has stored current instance statement.
        RestStatus status = response.status();
        System.out.println(_index+" "+_type+" "+_id+" "+_version);
    }
}
