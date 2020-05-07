package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import jdk.nashorn.internal.objects.NativeNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;

/**
 * service层
 * @author BinPeng
 * @date 2020/5/6 11:34
 */
@Service
@Transactional
public class LabelService {
    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;//雪花算法生成的id
    public List<Label> findAll(){
        return labelDao.findAll();
    }
    public Label findById(String id){
        return labelDao.findById(id).get();
    }
    public void save(Label label){
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }
    public void update(Label label){
        labelDao.save(label);//可以作为更新
    }
    public void deleteById(String id){
        labelDao.deleteById(id);
    }

    //条件查询
    public List<Label> findSearch(Label label) {
       return labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root 根对象 把条件封装到哪个对象中
             * @param query 封装查询的关键字  例如group  order by
             * @param cb 用来封装条件对象 ,如果直接返回null就是没有条件
             * @return null
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                if (label.getLabelname()!=null && !"".equals(label.getLabelname())){
                    Predicate predicate = cb.like(root.get("labelname").as(String.class),"%"+label.getLabelname()+"%");//where labelname like "%xxx%"
                    list.add(predicate);
                }
                if (label.getState()!=null && !"".equals(label.getState())){
                    Predicate predicate1 = cb.like(root.get("state").as(String.class),"%"+label.getState()+"%");//where state like "%xxx%"
                    list.add(predicate1);
                }
                //实例化一个数组作为最终返回值的条件
                Predicate[] parr = new Predicate[list.size()];
                //list转换为数组
                parr  = list.toArray(parr);
                return cb.and(parr);//and 就是 where labelname like= "%xxx%" and state like="%xxx%"
            }

        });

    }


    //分页
    public Page<Label> findPageQuery(Label label, int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);//因为框架内部是从0页开始的
        return labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root 根对象 把条件封装到哪个对象中
             * @param query 封装查询的关键字  例如group  order by
             * @param cb 用来封装条件对象 ,如果直接返回null就是没有条件
             * @return null
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                if (label.getLabelname()!=null && !"".equals(label.getLabelname())){
                    Predicate predicate = cb.like(root.get("labelname").as(String.class),"%"+label.getLabelname()+"%");//where labelname like "%xxx%"
                    list.add(predicate);
                }
                if (label.getState()!=null && !"".equals(label.getState())){
                    Predicate predicate1 = cb.like(root.get("state").as(String.class),"%"+label.getState()+"%");//where state like "%xxx%"
                    list.add(predicate1);
                }
                //实例化一个数组作为最终返回值的条件
                Predicate[] parr = new Predicate[list.size()];
                //list转换为数组
                parr  = list.toArray(parr);
                return cb.and(parr);//and 就是 where labelname like= "%xxx%" and state like="%xxx%"
            }

        },pageable);
    }
}
