package cn.com.git.udmp.modules.sys.dao;

import cn.com.git.udmp.common.persistence.annotation.MyBatisDao;
import cn.com.git.udmp.modules.sys.entity.BizTime;
import cn.com.git.udmp.modules.sys.entity.BizTimeKey;

@MyBatisDao
public interface BizTimeDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UDMP.T_BUSINESS_TIME
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(BizTimeKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UDMP.T_BUSINESS_TIME
     *
     * @mbggenerated
     */
    int insert(BizTime record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UDMP.T_BUSINESS_TIME
     *
     * @mbggenerated
     */
    int insertSelective(BizTime record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UDMP.T_BUSINESS_TIME
     *
     * @mbggenerated
     */
    BizTime selectByPrimaryKey(BizTimeKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UDMP.T_BUSINESS_TIME
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(BizTime record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UDMP.T_BUSINESS_TIME
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(BizTime record);
}