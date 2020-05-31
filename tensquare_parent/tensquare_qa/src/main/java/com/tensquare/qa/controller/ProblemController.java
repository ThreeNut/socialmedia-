package com.tensquare.qa.controller;
import java.util.List;
import java.util.Map;

import com.tensquare.qa.client.BaseClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.tensquare.qa.pojo.Problem;
import com.tensquare.qa.service.ProblemService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin //解决跨域问题
@RequestMapping("/problem")
public class ProblemController {

	@Autowired
	private ProblemService problemService;
	
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private BaseClient baseClient;


	//调用远程服务(本问答模块调用 base模块)
	//调用远程模块服务 http://localhost:9003/problem/label/2
	@GetMapping(value = "/label/{id}")//注意路径
	public Result findLableById(@PathVariable String id){
	  Result result = baseClient.findById(id);
	  return result;
	}
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",problemService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",problemService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<Problem> pageList = problemService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Problem>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",problemService.findSearch(searchMap));
    }
	
	/**
	 * 增加  http://localhost:9003/problem
	 * @param problem
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Problem problem  ){
		String token = (String) request.getAttribute("claims_user");
		if (token==null || "".equals(token)){
			return new Result(false,StatusCode.ERROR,"没有user权限,权限不足!");
		}
		problemService.add(problem);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param problem
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Problem problem, @PathVariable String id ){
		problem.setId(id);
		problemService.update(problem);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		problemService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}

	/**
	 * 最新问答列表
	 * 测试  http://localhost:9003/problem/newlist/1/1/2
	 * @return
	 */
	@GetMapping(value = "/newlist/{label}/{page}/{size}")
	public Result newlist(@PathVariable String label,@PathVariable int page,@PathVariable int size){
		Page<Problem> newlist = problemService.newlist(label, page, size);
		return new Result(true, StatusCode.OK,"ok",newlist);
	}

	/**
	 * 热门问答列表
	 * 测试 http://localhost:9003/problem/hotlist/1/1/2
	 * @param label
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping(value = "/hotlist/{label}/{page}/{size}")
	public Result hotlist(@PathVariable String label,@PathVariable int page,@PathVariable int size){
		Page<Problem> hotlist = problemService.newlist(label, page, size);
		return new Result(true, StatusCode.OK,"ok",hotlist);
	}

	/**
	 * 等待回答列表
	 * @param label
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping(value = "/waitlist/{label}/{page}/{size}")
	public Result waitlist(@PathVariable String label,@PathVariable int page,@PathVariable int size){
		Page<Problem> waitlist = problemService.waitlist(label, page, size);
		return new Result(true, StatusCode.OK,"ok",waitlist);
	}
}

