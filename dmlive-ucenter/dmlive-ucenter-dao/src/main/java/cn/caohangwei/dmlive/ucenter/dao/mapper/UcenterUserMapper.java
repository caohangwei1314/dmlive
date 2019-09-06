package cn.caohangwei.dmlive.ucenter.dao.mapper;

import cn.caohangwei.dmlive.ucenter.dao.model.UcenterUser;
import cn.caohangwei.dmlive.ucenter.dao.model.UcenterUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UcenterUserMapper {
    long countByExample(UcenterUserExample example);

    int deleteByExample(UcenterUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UcenterUser record);

    int insertSelective(UcenterUser record);

    List<UcenterUser> selectByExample(UcenterUserExample example);

    UcenterUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UcenterUser record, @Param("example") UcenterUserExample example);

    int updateByExample(@Param("record") UcenterUser record, @Param("example") UcenterUserExample example);

    int updateByPrimaryKeySelective(UcenterUser record);

    int updateByPrimaryKey(UcenterUser record);
}