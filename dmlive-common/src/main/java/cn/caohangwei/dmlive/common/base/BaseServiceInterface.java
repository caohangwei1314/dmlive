package cn.caohangwei.dmlive.common.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * BaseService interface.
 * @author PinuoC
 */
public interface BaseServiceInterface<Record, Example> {

    /**
     * Select count by example.
     * @param example
     * @return
     */
    int countByExample(Example example);

    /**
     * Delete by example.
     * @param example
     * @return
     */
    int deleteByExample(Example example);

    /**
     * Delete by primary key.
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert record.
     * @param record
     * @return
     */
    int insert(Record record);

    /**
     * insert selective record.
     * @param record
     * @return
     */
    int insertSelective(Record record);

    /**
     * Select by example with blobs.
     * @param example
     * @return
     */
    List<Record> selectByExampleWithBLOBs(Example example);

    /**
     * Select by example.
     * @param example
     * @return
     */
    List<Record> selectByExample(Example example);

    /**
     * Select by primary key.
     * @param id
     * @return
     */
    Record selectByPrimaryKey(Integer id);

    /**
     * Update by example selective.
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(@Param("record") Record record, @Param("example") Example example);

    /**
     * Update by example with blobs.
     * @param record
     * @param example
     * @return
     */
    int updateByExampleWithBLOBs(@Param("record") Record record,@Param("example") Example example);

    /**
     * Update by example.
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") Record record,@Param("example") Example example);

    /**
     * Update by primary key selective.
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(@Param("record") Record record);

    /**
     * Update by primary key with blobs.
     * @param record
     * @return
     */
    int updateByPrimaryKeyWithBLOBs(@Param("reocrd") Record record);

    /**
     * Update by primary key.
     * @param record
     * @return
     */
    int updateByPrimaryKey(@Param("record") Record record);

    void initMapper();

}
