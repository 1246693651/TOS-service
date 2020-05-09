package com.hnist.tos.service.impl;

import com.hnist.tos.dao.OrganizationDao;
import com.hnist.tos.entity.Organization;
import com.hnist.tos.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 * @author Pany
 * @date 2020-05-06 19:26
 * @content
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    OrganizationDao  organizationDao;

    /**
     * 添加机构
     * @param organization
     */
    @Override
    public void add(Organization organization) {
        organizationDao.save(organization);
    }

    /**
     * 删除机构
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        organizationDao.deleteById(id);
    }

    /**
     * 更新机构信息
     * @param organization
     */
    @Override
    public void update(Organization organization) {
       Organization mid = organizationDao.findById(organization.getId()).get();
       mid.setName(organization.getName());
       mid.setDescription(organization.getDescription());

    }

    /**
     * 通过ID查到机构
     * @param id
     * @return
     */
    @Override
    public Organization findById(Long id) {
        return organizationDao.findById(id).get();
    }

    /**
     *  查询全部机构列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @Override
    public Page<Organization> findAll(int page, int size, Map map) {
        //查询条件
        Specification<Organization> spec = new Specification<Organization>() {

            /**
             * 动态拼接查询条件
             * @param root
             * @param query
             * @param cb
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Organization> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();

                //根据请求的companyId是否为空构造查询条件
                if (!StringUtils.isEmpty(map.get("companyId"))){
                    list.add(cb.equal(root.get("companyId").as(String.class) , (String)map.get("companyId")));
                }

                //根据请求的部门id构造查询条件
                if (!StringUtils.isEmpty(map.get("departmentId"))){
                    list.add(cb.equal(root.get("departmentId").as(String.class) , (String)map.get("departmentId")));
                }

                //根据请求的hasDept进行判断
                if (!StringUtils.isEmpty(map.get("hasDept"))){
                    if ("0".equals((String) map.get("hasDept")))   {
                        list.add(cb.isNull(root.get("departmentId")));
                    }else{
                        list.add(cb.isNotNull(root.get("departmentId")));
                    }
                }
                return cb.and(list.toArray(new Predicate[list.size()]));
            }
        };
        Pageable pageable = PageRequest.of(page,size);
        //分页
        Page<Organization> organizationPage = organizationDao.findAll(spec, pageable);
        return organizationPage;
    }
}
