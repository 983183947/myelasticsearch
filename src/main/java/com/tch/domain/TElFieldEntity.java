package com.tch.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.support.spring.annotation.FastJsonFilter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: zzj
 * @date: 2017/11/15
 * Time: 10:17
 */
@Entity
@Table(name = "T_EL_FIELD", schema = "TC_TOOLS")
public class TElFieldEntity {
    private long nid;
    private String cnname;
    private String sfield;
    private String stype;
    private String dateformat;
    private String show;
    private Long showOrder;
    private String iskey;
    private String storeType;
    private String indexType;
    private Date dindate;
    private String isfc;
    private String issort;
    private String fl;
    private String memo;
    private String isboost;
    private String scol1;
    private String scol2;
    private String scol3;
    private String addBig;

    @JSONField(serialize =false )
    private TElSearchEntity tElSearchEntity;

    @ManyToOne
    public TElSearchEntity gettElSearchEntity() {
        return tElSearchEntity;
    }

    public void settElSearchEntity(TElSearchEntity tElSearchEntity) {
        this.tElSearchEntity = tElSearchEntity;
    }

    @Id
    @Column(name = "NID", nullable = false, precision = 0)
    public long getNid() {
        return nid;
    }

    public void setNid(long nid) {
        this.nid = nid;
    }

    @Column(name = "CNNAME", nullable = false, length = 100)
    public String getCnname() {
        return cnname;
    }

    public void setCnname(String cnname) {
        this.cnname = cnname;
    }

    @Column(name = "SFIELD", nullable = false, length = 100)
    public String getSfield() {
        return sfield;
    }

    public void setSfield(String sfield) {
        this.sfield = sfield;
    }

    @Column(name = "STYPE", nullable = false, length = 20)
    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    @Column(name = "DATEFORMAT", nullable = true, length = 30)
    public String getDateformat() {
        return dateformat;
    }

    public void setDateformat(String dateformat) {
        this.dateformat = dateformat;
    }

    @Column(name = "SHOW", nullable = true, length = 1)
    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    @Column(name = "SHOW_ORDER", nullable = true, precision = 0)
    public Long getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Long showOrder) {
        this.showOrder = showOrder;
    }

    @Column(name = "ISKEY", nullable = true, length = 4)
    public String getIskey() {
        return iskey;
    }

    public void setIskey(String iskey) {
        this.iskey = iskey;
    }

    @Column(name = "STORE_TYPE", nullable = true, length = 10)
    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    @Column(name = "INDEX_TYPE", nullable = true, length = 10)
    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    @Column(name = "DINDATE", nullable = true)
    public Date getDindate() {
        return dindate;
    }

    public void setDindate(Date dindate) {
        this.dindate = dindate;
    }

    @Column(name = "ISFC", nullable = true, length = 1)
    public String getIsfc() {
        return isfc;
    }

    public void setIsfc(String isfc) {
        this.isfc = isfc;
    }

    @Column(name = "ISSORT", nullable = true, length = 1)
    public String getIssort() {
        return issort;
    }

    public void setIssort(String issort) {
        this.issort = issort;
    }

    @Column(name = "FL", nullable = true, length = 50)
    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    @Column(name = "MEMO", nullable = true, length = 500)
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Column(name = "ISBOOST", nullable = true, length = 1)
    public String getIsboost() {
        return isboost;
    }

    public void setIsboost(String isboost) {
        this.isboost = isboost;
    }

    @Column(name = "SCOL1", nullable = true, length = 500)
    public String getScol1() {
        return scol1;
    }

    public void setScol1(String scol1) {
        this.scol1 = scol1;
    }

    @Column(name = "SCOL2", nullable = true, length = 500)
    public String getScol2() {
        return scol2;
    }

    public void setScol2(String scol2) {
        this.scol2 = scol2;
    }

    @Column(name = "SCOL3", nullable = true, length = 500)
    public String getScol3() {
        return scol3;
    }

    public void setScol3(String scol3) {
        this.scol3 = scol3;
    }

    @Column(name = "ADD_BIG", nullable = true, length = 1)
    public String getAddBig() {
        return addBig;
    }

    public void setAddBig(String addBig) {
        this.addBig = addBig;
    }


}
