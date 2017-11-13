package com.tch.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: zzj
 * @date: 2017/11/09
 * Time: 10:57
 */
@Entity
@Table(name = "T_EL_SEARCH", schema = "TC_TOOLS")
public class TElSearchEntity {
    private long oId; 
    private String oName;
    private String sObjType;
    private String tOwner;
    private String tName;
    private String sdes;
    private Long allCnt = 0L;
    private Date cntTime;
    private Long allLast = 0L;
    private Date lastTime;
    private Date dindate = new Date();
    private Date dupdate;
    private String szlField;
    private String szlValue;
    private String szlMode;
    private String tybz = "启动";
    private String sqlFinish = "0";
    private String serverMacId;
    private String sindexDir;
    private String sIschange = "否";
    private String sStop = "否";
    private TElDbEntity tElDbEntity;


    @Id
    @Column(name = "O_ID")
    public long getoId() {
        return oId;
    }

    public void setoId(long oId) {
        this.oId = oId;
    }

    @ManyToOne(targetEntity = TElDbEntity.class)
    public TElDbEntity gettElDbEntity() {
        return tElDbEntity;
    }

    public void settElDbEntity(TElDbEntity tElDbEntity) {
        this.tElDbEntity = tElDbEntity;
    }

    @Basic
    @Column(name = "O_NAME")
    public String getoName() {
        return oName;
    }

    public void setoName(String oName) {
        this.oName = oName;
    }

    @Basic
    @Column(name = "S_OBJ_TYPE")
    public String getsObjType() {
        return sObjType;
    }

    public void setsObjType(String sObjType) {
        this.sObjType = sObjType;
    }


    @Basic
    @Column(name = "T_OWNER")
    public String gettOwner() {
        return tOwner;
    }

    public void settOwner(String tOwner) {
        this.tOwner = tOwner;
    }

    @Basic
    @Column(name = "T_NAME")
    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    @Basic
    @Column(name = "SDES")
    public String getSdes() {
        return sdes;
    }

    public void setSdes(String sdes) {
        this.sdes = sdes;
    }

    @Basic
    @Column(name = "ALL_CNT")
    public Long getAllCnt() {
        return allCnt;
    }

    public void setAllCnt(Long allCnt) {
        this.allCnt = allCnt;
    }

    @Basic
    @Column(name = "CNT_TIME")
    public Date getCntTime() {
        return cntTime;
    }

    public void setCntTime(Date cntTime) {
        this.cntTime = cntTime;
    }

    @Basic
    @Column(name = "ALL_LAST")
    public Long getAllLast() {
        return allLast;
    }

    public void setAllLast(Long allLast) {
        this.allLast = allLast;
    }

    @Basic
    @Column(name = "LAST_TIME")
    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    @Basic
    @Column(name = "DINDATE")
    public Date getDindate() {
        return dindate;
    }

    public void setDindate(Date dindate) {
        this.dindate = dindate;
    }

    @Basic
    @Column(name = "DUPDATE")
    public Date getDupdate() {
        return dupdate;
    }

    public void setDupdate(Date dupdate) {
        this.dupdate = dupdate;
    }

    @Basic
    @Column(name = "SZL_FIELD")
    public String getSzlField() {
        return szlField;
    }

    public void setSzlField(String szlField) {
        this.szlField = szlField;
    }

    @Basic
    @Column(name = "SZL_VALUE")
    public String getSzlValue() {
        return szlValue;
    }

    public void setSzlValue(String szlValue) {
        this.szlValue = szlValue;
    }

    @Basic
    @Column(name = "SZL_MODE")
    public String getSzlMode() {
        return szlMode;
    }

    public void setSzlMode(String szlMode) {
        this.szlMode = szlMode;
    }

    @Basic
    @Column(name = "TYBZ")
    public String getTybz() {
        return tybz;
    }

    public void setTybz(String tybz) {
        this.tybz = tybz;
    }

    @Basic
    @Column(name = "SQL_FINISH")
    public String getSqlFinish() {
        return sqlFinish;
    }

    public void setSqlFinish(String sqlFinish) {
        this.sqlFinish = sqlFinish;
    }

    @Basic
    @Column(name = "SERVER_MAC_ID")
    public String getServerMacId() {
        return serverMacId;
    }

    public void setServerMacId(String serverMacId) {
        this.serverMacId = serverMacId;
    }

    @Basic
    @Column(name = "SINDEX_DIR")
    public String getSindexDir() {
        return sindexDir;
    }

    public void setSindexDir(String sindexDir) {
        this.sindexDir = sindexDir;
    }

    @Basic
    @Column(name = "S_ISCHANGE")
    public String getsIschange() {
        return sIschange;
    }

    public void setsIschange(String sIschange) {
        this.sIschange = sIschange;
    }

    @Basic
    @Column(name = "S_STOP")
    public String getsStop() {
        return sStop;
    }

    public void setsStop(String sStop) {
        this.sStop = sStop;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TElSearchEntity that = (TElSearchEntity) o;
        if (oId != that.oId) return false;
        if (oName != null ? !oName.equals(that.oName) : that.oName != null) return false;
        if (sObjType != null ? !sObjType.equals(that.sObjType) : that.sObjType != null) return false;
        if (tOwner != null ? !tOwner.equals(that.tOwner) : that.tOwner != null) return false;
        if (tName != null ? !tName.equals(that.tName) : that.tName != null) return false;
        if (sdes != null ? !sdes.equals(that.sdes) : that.sdes != null) return false;
        if (allCnt != null ? !allCnt.equals(that.allCnt) : that.allCnt != null) return false;
        if (cntTime != null ? !cntTime.equals(that.cntTime) : that.cntTime != null) return false;
        if (allLast != null ? !allLast.equals(that.allLast) : that.allLast != null) return false;
        if (lastTime != null ? !lastTime.equals(that.lastTime) : that.lastTime != null) return false;
        if (dindate != null ? !dindate.equals(that.dindate) : that.dindate != null) return false;
        if (dupdate != null ? !dupdate.equals(that.dupdate) : that.dupdate != null) return false;
        if (szlField != null ? !szlField.equals(that.szlField) : that.szlField != null) return false;
        if (szlValue != null ? !szlValue.equals(that.szlValue) : that.szlValue != null) return false;
        if (szlMode != null ? !szlMode.equals(that.szlMode) : that.szlMode != null) return false;
        if (tybz != null ? !tybz.equals(that.tybz) : that.tybz != null) return false;
        if (sqlFinish != null ? !sqlFinish.equals(that.sqlFinish) : that.sqlFinish != null) return false;
        if (serverMacId != null ? !serverMacId.equals(that.serverMacId) : that.serverMacId != null) return false;
        if (sindexDir != null ? !sindexDir.equals(that.sindexDir) : that.sindexDir != null) return false;
        if (sIschange != null ? !sIschange.equals(that.sIschange) : that.sIschange != null) return false;
        if (sStop != null ? !sStop.equals(that.sStop) : that.sStop != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (oId ^ (oId >>> 32));
        result = 31 * result + (oName != null ? oName.hashCode() : 0);
        result = 31 * result + (sObjType != null ? sObjType.hashCode() : 0);
        result = 31 * result + (tOwner != null ? tOwner.hashCode() : 0);
        result = 31 * result + (tName != null ? tName.hashCode() : 0);
        result = 31 * result + (sdes != null ? sdes.hashCode() : 0);
        result = 31 * result + (allCnt != null ? allCnt.hashCode() : 0);
        result = 31 * result + (cntTime != null ? cntTime.hashCode() : 0);
        result = 31 * result + (allLast != null ? allLast.hashCode() : 0);
        result = 31 * result + (lastTime != null ? lastTime.hashCode() : 0);
        result = 31 * result + (dindate != null ? dindate.hashCode() : 0);
        result = 31 * result + (dupdate != null ? dupdate.hashCode() : 0);
        result = 31 * result + (szlField != null ? szlField.hashCode() : 0);
        result = 31 * result + (szlValue != null ? szlValue.hashCode() : 0);
        result = 31 * result + (szlMode != null ? szlMode.hashCode() : 0);
        result = 31 * result + (tybz != null ? tybz.hashCode() : 0);
        result = 31 * result + (sqlFinish != null ? sqlFinish.hashCode() : 0);
        result = 31 * result + (serverMacId != null ? serverMacId.hashCode() : 0);
        result = 31 * result + (sindexDir != null ? sindexDir.hashCode() : 0);
        result = 31 * result + (sIschange != null ? sIschange.hashCode() : 0);
        result = 31 * result + (sStop != null ? sStop.hashCode() : 0);
        return result;
    }
}
