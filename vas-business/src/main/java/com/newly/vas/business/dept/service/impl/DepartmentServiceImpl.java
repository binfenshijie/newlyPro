package com.newly.vas.business.dept.service.impl;

import com.google.common.collect.Lists;
import com.newly.vas.business.dept.bean.DepartmentBO;
import com.newly.vas.business.dept.bean.DepartmentDO;
import com.newly.vas.business.dept.mapper.DepartmentMapper;
import com.newly.vas.business.dept.service.DepartmentService;
import com.newly.common.service.impl.BaseServiceImpl;
import com.newly.vas.business.dept.bean.DepartmentBO;
import com.newly.vas.business.dept.bean.DepartmentDO;
import com.newly.vas.business.dept.mapper.DepartmentMapper;
import com.newly.vas.business.dept.service.DepartmentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Created by bingo on 2020/4/16 上午11:46
 */
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<DepartmentMapper, DepartmentDO> implements DepartmentService {

    @Autowired
    private DepartmentMapper deptMapper;

    @Override
    public DepartmentMapper getDAO() {
        return this.deptMapper;
    }


    /**
     * 可高亮搜索内容，展开搜索的路径
     * @param parent
     * @param deptBOs
     * @param searchInfo
     * @return
     */
    @Override
    public List<DepartmentBO> buildDeptTree(DepartmentBO parent, List<DepartmentBO> deptBOs, String searchInfo) {
        List<DepartmentBO> deptChildren = Lists.newArrayList();
        Iterator<DepartmentBO> it = deptBOs.iterator();
        while(it.hasNext()){
            DepartmentBO temp = it.next();
            if (temp.getParent()!=null && temp.getChildren()!=null) {
                continue;
            }
            if (!StringUtils.isEmpty(searchInfo)){
                if(temp.getName().contains(searchInfo)){
                    temp.setFind(true);
                }else{
                    temp.setFind(false);
                }
            }
            if(parent.getId().equals(temp.getParentId())){
                temp.setParent(parent);
                if (temp.getFind() != null && true == temp.getFind()){
                    temp.setTreeNodeOpen();
                }
                List<DepartmentBO> childNodes = buildDeptTree(temp,deptBOs,searchInfo);
                temp.setLeaf(false);
                temp.setChildren(childNodes);
                if (childNodes.size() == 0){
                    temp.setLeaf(true);
                    temp.setChildren(null);
                }
                deptChildren.add(temp);
            }

        }
        return deptChildren;
    }

    @Override
    public String getChildrenDept(String deptId) {
        return deptMapper.getChildrenDept(deptId);
    }

    @Override
    public List<DepartmentDO> getByNameOrDeptCode(String search) {
        return deptMapper.getByNameOrDeptCode(search);
    }

}
