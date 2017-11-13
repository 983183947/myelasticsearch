import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.print.DocFlavor;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: zzj
 * Date: 2017-09-26
 * Time: 16:53
 */
public class Test {
    public static void main(String[] args) throws SQLException {
        DruidDataSource druidDataSource=new DruidDataSource();
        druidDataSource.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:xe");
        druidDataSource.setUsername("tc_tools");
        druidDataSource.setPassword("c153");
        ///druidDataSource.setDriverClassName("");
        QueryRunner queryRunner =new QueryRunner(druidDataSource);
        List<HashMap> a = (List)queryRunner.query("select * from T_LU_CONFIG t", new MapListHandler());
        System.out.println(a.get(0).get("svalue"));
    }
}
