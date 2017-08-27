package cn.qianying.graduation.dao.mapper;

import java.io.Serializable;  
import java.util.List;  
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.qianying.graduation.domain.Page;  

public interface CommonMapper<T> {  
    /** 
     *  
     * 增加一个实体 
     * @param pojo 
     * @return 影响的行数 0失败，1成功 
     */  
    public int save(T pojo);  
  
    /** 
     *  
     * 通过id删除实体 
     *  
     * @param clazz 
     * @param id 
     * @return 
     */  
    public int deleteById(Class<T> clazz,  
            Serializable id);  
  
    /** 
     *  
     * 通过主键获取实体 
     *  
     * @param clazz 
     * @param id 
     * @return 
     */  
    public T getById(Class<T> clazz, Serializable id);  
  
    /** 
     *  
     * 查询所有实体 
     *  
     * @param clazz 
     * @return 
     */  
    public List<T> listAll(Class<T> clazz);  
      
    /** 
     *  
     * 分页查询 
     *  
     * @param clazz 
     * @param p 
     * @return 
     */  
    public Page<T> pageSelect(Class<T> clazz,Page<T> p,String[]attrs,Object[]values);  
      
      
    /** 
     *  
     * 分页查询时，用来统计总条数 
     *  
     * @param clazz 
     * @param attrs 
     * @param values 
     * @return 
     */  
    public int pageCount(Class<T> clazz,String[]attrs,Object[]values);  
  
    /** 
     *  
     * 统计总条数 
     *  
     * @param clazz 
     * @return 
     */  
    public int countAll(Class<T> clazz);  
  
    /** 
     *  
     * 指定查询使用的命名sql，查询结果封装成map 
     *  
     * @param statment 
     * @param paraMap 
     * @return 
     */  
    List<Map<String,Object>>  selectMap(String statment, Map<String, Object> paraMap);  
  
}