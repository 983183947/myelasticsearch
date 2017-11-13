package com.tch.dao;

import com.tch.domain.TElSearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author: zzj
 * @date: 2017/11/09
 * Time: 13:33
 */
public interface SearchRepository extends JpaRepository<TElSearchEntity,Long>,JpaSpecificationExecutor<TElSearchEntity> {
    List<TElSearchEntity> getTElSearchEntitiesBySqlFinishAndSStop(String sqlFinish,String sstop);

}
