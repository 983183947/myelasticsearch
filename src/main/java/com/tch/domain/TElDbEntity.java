package com.tch.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: zzj
 * @date: 2017/11/09
 * Time: 10:57
 */
@Entity
@Table(name = "T_EL_DB", schema = "TC_TOOLS")
public class TElDbEntity {
    /**
     * 对象id
     */
    private long nid;
    /**
     * 数据库名称
     */
    private String jdmc;
    /**
     * 系统名称
     */
    private String jdlx;
    /**
     * 所属部门
     */
    private String jdbm;
    /**
     * 单位代码
     */
    private String jddwdm;
    /**
     * 单位名称
     */
    private String jddwmc;
    /**
     * 数据库类型oracle/sqlserver/其他
     */
    private String jdklx;
    /**
     * 节点描述
     */
    private String jdmemo;
    /**
     * 数据库IP
     */
    private String jdkip;
    /**
     * 数据库实例
     */
    private String jdksid;
    /**
     * 数据库端口
     */
    private String jdkport;
    /**
     * 数据库用户
     */
    private String jdkuser;
    /**
     * 数据库密码
     */
    private String jdkpass;
    /**
     * 可以直接使用URL
     */
    private String jdbcUrl;
    /**
     * 连接JNDI
     */
    private String jdjndi;
    /**
     * 创建时间
     */
    private Date jdkcjsj;
    /**
     * 创建人
     */
    private String jdkcjr;
    /**
     * 停用日期
     */
    private Date jdktyrq;
    /**
     * 最后修改人
     */
    private String jdkxgr;
    /**
     * 最后修改日期
     */
    private Date jdkxgrq;
    /**
     * 数据链路
     */
    private String jddbLink;

    @Id
    @Column(name = "NID")
    public long getNid() {
        return nid;
    }

    public void setNid(long nid) {
        this.nid = nid;
    }

    @Column(name = "JDMC")
    public String getJdmc() {
        return jdmc;
    }

    public void setJdmc(String jdmc) {
        this.jdmc = jdmc;
    }


    @Column(name = "JDLX")
    public String getJdlx() {
        return jdlx;
    }

    public void setJdlx(String jdlx) {
        this.jdlx = jdlx;
    }

    @Column(name = "JDBM")
    public String getJdbm() {
        return jdbm;
    }

    public void setJdbm(String jdbm) {
        this.jdbm = jdbm;
    }

    @Column(name = "JDDWDM")
    public String getJddwdm() {
        return jddwdm;
    }

    public void setJddwdm(String jddwdm) {
        this.jddwdm = jddwdm;
    }

    @Column(name = "JDDWMC")
    public String getJddwmc() {
        return jddwmc;
    }

    public void setJddwmc(String jddwmc) {
        this.jddwmc = jddwmc;
    }

    @Column(name = "JDKLX")
    public String getJdklx() {
        return jdklx;
    }

    public void setJdklx(String jdklx) {
        this.jdklx = jdklx;
    }

    @Column(name = "JDMEMO")
    public String getJdmemo() {
        return jdmemo;
    }

    public void setJdmemo(String jdmemo) {
        this.jdmemo = jdmemo;
    }

    @Column(name = "JDKIP")
    public String getJdkip() {
        return jdkip;
    }

    public void setJdkip(String jdkip) {
        this.jdkip = jdkip;
    }

    @Column(name = "JDKSID")
    public String getJdksid() {
        return jdksid;
    }

    public void setJdksid(String jdksid) {
        this.jdksid = jdksid;
    }

    @Column(name = "JDKPORT")
    public String getJdkport() {
        return jdkport;
    }

    public void setJdkport(String jdkport) {
        this.jdkport = jdkport;
    }

    @Column(name = "JDKUSER")
    public String getJdkuser() {
        return jdkuser;
    }

    public void setJdkuser(String jdkuser) {
        this.jdkuser = jdkuser;
    }

    @Column(name = "JDKPASS")
    public String getJdkpass() {
        return jdkpass;
    }

    public void setJdkpass(String jdkpass) {
        this.jdkpass = jdkpass;
    }

    @Column(name = "JDBC_URL")
    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    @Column(name = "JDJNDI")
    public String getJdjndi() {
        return jdjndi;
    }

    public void setJdjndi(String jdjndi) {
        this.jdjndi = jdjndi;
    }

    @Column(name = "JDKCJSJ")
    public Date getJdkcjsj() {
        return jdkcjsj;
    }

    public void setJdkcjsj(Date jdkcjsj) {
        this.jdkcjsj = jdkcjsj;
    }

    @Column(name = "JDKCJR")
    public String getJdkcjr() {
        return jdkcjr;
    }

    public void setJdkcjr(String jdkcjr) {
        this.jdkcjr = jdkcjr;
    }

    @Column(name = "JDKTYRQ")
    public Date getJdktyrq() {
        return jdktyrq;
    }

    public void setJdktyrq(Date jdktyrq) {
        this.jdktyrq = jdktyrq;
    }

    @Column(name = "JDKXGR")
    public String getJdkxgr() {
        return jdkxgr;
    }

    public void setJdkxgr(String jdkxgr) {
        this.jdkxgr = jdkxgr;
    }

    @Column(name = "JDKXGRQ")
    public Date getJdkxgrq() {
        return jdkxgrq;
    }

    public void setJdkxgrq(Date jdkxgrq) {
        this.jdkxgrq = jdkxgrq;
    }

    @Column(name = "JDDB_LINK")
    public String getJddbLink() {
        return jddbLink;
    }

    public void setJddbLink(String jddbLink) {
        this.jddbLink = jddbLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TElDbEntity that = (TElDbEntity) o;

        if (nid != that.nid) return false;
        if (jdmc != null ? !jdmc.equals(that.jdmc) : that.jdmc != null) return false;
        if (jdlx != null ? !jdlx.equals(that.jdlx) : that.jdlx != null) return false;
        if (jdbm != null ? !jdbm.equals(that.jdbm) : that.jdbm != null) return false;
        if (jddwdm != null ? !jddwdm.equals(that.jddwdm) : that.jddwdm != null) return false;
        if (jddwmc != null ? !jddwmc.equals(that.jddwmc) : that.jddwmc != null) return false;
        if (jdklx != null ? !jdklx.equals(that.jdklx) : that.jdklx != null) return false;
        if (jdmemo != null ? !jdmemo.equals(that.jdmemo) : that.jdmemo != null) return false;
        if (jdkip != null ? !jdkip.equals(that.jdkip) : that.jdkip != null) return false;
        if (jdksid != null ? !jdksid.equals(that.jdksid) : that.jdksid != null) return false;
        if (jdkport != null ? !jdkport.equals(that.jdkport) : that.jdkport != null) return false;
        if (jdkuser != null ? !jdkuser.equals(that.jdkuser) : that.jdkuser != null) return false;
        if (jdkpass != null ? !jdkpass.equals(that.jdkpass) : that.jdkpass != null) return false;
        if (jdbcUrl != null ? !jdbcUrl.equals(that.jdbcUrl) : that.jdbcUrl != null) return false;
        if (jdjndi != null ? !jdjndi.equals(that.jdjndi) : that.jdjndi != null) return false;
        if (jdkcjsj != null ? !jdkcjsj.equals(that.jdkcjsj) : that.jdkcjsj != null) return false;
        if (jdkcjr != null ? !jdkcjr.equals(that.jdkcjr) : that.jdkcjr != null) return false;
        if (jdktyrq != null ? !jdktyrq.equals(that.jdktyrq) : that.jdktyrq != null) return false;
        if (jdkxgr != null ? !jdkxgr.equals(that.jdkxgr) : that.jdkxgr != null) return false;
        if (jdkxgrq != null ? !jdkxgrq.equals(that.jdkxgrq) : that.jdkxgrq != null) return false;
        if (jddbLink != null ? !jddbLink.equals(that.jddbLink) : that.jddbLink != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (nid ^ (nid >>> 32));
        result = 31 * result + (jdmc != null ? jdmc.hashCode() : 0);
        result = 31 * result + (jdlx != null ? jdlx.hashCode() : 0);
        result = 31 * result + (jdbm != null ? jdbm.hashCode() : 0);
        result = 31 * result + (jddwdm != null ? jddwdm.hashCode() : 0);
        result = 31 * result + (jddwmc != null ? jddwmc.hashCode() : 0);
        result = 31 * result + (jdklx != null ? jdklx.hashCode() : 0);
        result = 31 * result + (jdmemo != null ? jdmemo.hashCode() : 0);
        result = 31 * result + (jdkip != null ? jdkip.hashCode() : 0);
        result = 31 * result + (jdksid != null ? jdksid.hashCode() : 0);
        result = 31 * result + (jdkport != null ? jdkport.hashCode() : 0);
        result = 31 * result + (jdkuser != null ? jdkuser.hashCode() : 0);
        result = 31 * result + (jdkpass != null ? jdkpass.hashCode() : 0);
        result = 31 * result + (jdbcUrl != null ? jdbcUrl.hashCode() : 0);
        result = 31 * result + (jdjndi != null ? jdjndi.hashCode() : 0);
        result = 31 * result + (jdkcjsj != null ? jdkcjsj.hashCode() : 0);
        result = 31 * result + (jdkcjr != null ? jdkcjr.hashCode() : 0);
        result = 31 * result + (jdktyrq != null ? jdktyrq.hashCode() : 0);
        result = 31 * result + (jdkxgr != null ? jdkxgr.hashCode() : 0);
        result = 31 * result + (jdkxgrq != null ? jdkxgrq.hashCode() : 0);
        result = 31 * result + (jddbLink != null ? jddbLink.hashCode() : 0);
        return result;
    }
}
