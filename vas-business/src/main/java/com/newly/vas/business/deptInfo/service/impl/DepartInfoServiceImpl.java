package com.newly.vas.business.deptInfo.service.impl;

import com.google.common.collect.Lists;
import com.newly.vas.business.deptInfo.bean.DepartInfoBO;
import com.newly.vas.business.deptInfo.bean.DepartInfoDO;
import com.newly.vas.business.deptInfo.bean.DeptParentDTO;
import com.newly.vas.business.deptInfo.bean.UserTree;
import com.newly.vas.business.deptInfo.service.DepartInfoService;
import com.newly.common.service.impl.BaseServiceImpl;

import com.newly.vas.business.deptInfo.bean.DepartInfoBO;
import com.newly.vas.business.deptInfo.bean.DepartInfoDO;
import com.newly.vas.business.deptInfo.bean.DeptParentDTO;
import com.newly.vas.business.deptInfo.bean.UserTree;
import com.newly.vas.business.deptInfo.mapper.DepartInfoMapper;
import com.newly.vas.business.deptInfo.service.DepartInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Created by bingo on 2020/4/16 上午11:46
 */
@Service
public class DepartInfoServiceImpl extends BaseServiceImpl<DepartInfoMapper, DepartInfoDO> implements DepartInfoService {

    @Autowired
    private DepartInfoMapper deptMapper;

    @Override
    public DepartInfoMapper getDAO() {
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
    public List<DepartInfoBO> buildDeptTree(DepartInfoBO parent, List<DepartInfoBO> deptBOs, String searchInfo) {
        List<DepartInfoBO> deptChildren = Lists.newArrayList();
        Iterator<DepartInfoBO> it = deptBOs.iterator();
        while(it.hasNext()){
            DepartInfoBO temp = it.next();
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
                List<DepartInfoBO> childNodes = buildDeptTree(temp,deptBOs,searchInfo);
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
    public List<DepartInfoDO> getByNameOrDeptCode(String search) {
        return deptMapper.getByNameOrDeptCode(search);
    }
    public int getCount(String name){
        return deptMapper.getCount(name);
    }

    public int saveParent(DeptParentDTO dept){
        return deptMapper.saveParent(dept);
    }

    public UserTree getUserTree(String userId){
        return deptMapper.getUserTree(userId);
    }

   public List<DepartInfoDO> getListByMaps(String userCode){
        return deptMapper.getUserCode(userCode);
   }

   public String getDeptId(String userCode){
        return deptMapper.getDeptId(userCode);
   }
}
