package com.zou.service;

import com.zou.dao.SpitDao;
import com.zou.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import java.util.Date;
import java.util.List;


/**
 * 吐槽 service
 * @author BinPeng
 * @date 2020/5/12 9:37
 */
@Service
@Transactional
public class SpitService {
    @Autowired
    private SpitDao spitDao;
    @Autowired
    private IdWorker idWorker;

    public List<Spit> findAll(){
        return spitDao.findAll();
    }
    public Spit findById(String id){
        return spitDao.findById(id).get();
    }
    public void save(Spit spit){
        //初始化数据
        spit.set_id( idWorker.nextId()+"");
        spit.setPublishtime(new Date());//发布日期  
        spit.setVisits(0);//浏览量         
        spit.setShare(0);//分享数        
        spit.setThumbup(0);//点赞数         
        spit.setComment(0);//回复数       
        spit.setState("1");//状态
        //如果当前添加的吐槽,有父节点那么父节点的吐槽回复数+1
        if (spit.getParentid()!=null && !"".equals(spit.getParentid())){
            Query query=new Query();
            //Criteria.where("_id").is(spit.getParentid() 查询出父类对象 然后更新父级的 comment +1
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update();
            update.inc("comment",1);
            mongoTemplate.updateFirst(query,update,"spit");
        }
        spitDao.save(spit);
    }
    public void update(Spit spit){
        spitDao.save(spit);
    }
    public void delete(String id){
        spitDao.deleteById(id);
    }

    //根据上级ID查询吐槽分页数据
    public Page<Spit> findByParentid(String parentId,int page,int size){
        Pageable pageable=PageRequest.of(page-1,size);
        return spitDao.findByParentid(parentId,pageable);
    }

    @Autowired
    private MongoTemplate mongoTemplate;
    //点赞
    public void updateThumbup(String id){
      /*  Spit spit = spitDao.findById(id).get();
        spit.setThumbup(spit.getThumbup()+1);
        spitDao.save(spit);
        执行效率慢
        */

      //使用原生mongo命令
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update =new Update();
        update.inc("thumbup",1);//点赞+1
        mongoTemplate.updateFirst(query,update,"spit");
    }
}
