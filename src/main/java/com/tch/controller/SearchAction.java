package com.tch.controller;

import com.tch.common.Base.BaseControl;
import com.tch.domain.document.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: zzj
 * @date: 2017/11/17
 * Time: 11:25
 */
@Controller
public class SearchAction extends BaseControl {
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @RequestMapping("/doSearch")
    public void saveCustomers() {

    }
}
