package com.frame.web.module.sys.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.frame.util.Dumper;
import com.frame.web.module.sys.entity.RegionDO;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.regexp.internal.recompile;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegionDaoTest {
	
	@Autowired
	private RegionDao regionDao;
	
	@Test
	public void testCityList () {
		List<Node> nodes = new ArrayList<Node>();
		String startId = "2";
		pass("", startId, nodes);
		Dumper.dump(nodes);
		
	}
	
	public void pass(String parentPath, String id, List<Node> nodes) {
		RegionDO newRegionDo = regionDao.selectById(id);
		if (null != newRegionDo) {
			String thisPath = newRegionDo.getRegionName();
			String thisId = newRegionDo.getRegionId();
			Node node = new Node();
			node.setValue(thisId);
			node.setLabel(thisPath);
			nodes.add(node);
			List<RegionDO> regionDO2List = regionDao.selectByPid(id);
			if (CollectionUtils.isNotEmpty(regionDO2List)) {
				for (RegionDO regionDO2 : regionDO2List) {
					Node node2 = new Node();
					node2.setValue(regionDO2.getRegionId());
					node2.setLabel(thisPath + "/" + regionDO2.getRegionName());
					nodes.add(node2);
					List<RegionDO> regionDO3List = regionDao.selectByPid(regionDO2.getRegionId());
					if (CollectionUtils.isNotEmpty(regionDO3List)) {
						for (RegionDO regionDO3 : regionDO3List) {
							Node node3 = new Node();
							node3.setValue(regionDO3.getRegionId());
							node3.setLabel(thisPath + "/" + regionDO2.getRegionName() + "/" + regionDO3.getRegionName());
							nodes.add(node3);
						}
					}
				}
			}
		}
	}
	
	
//	@Test
	public void testDeleteById() {
		String id = "2";
		Tree aTree = new Tree();
		aTree.setChildren(new ArrayList<Tree>());
		next(id, aTree);
		Dumper.dump(aTree);
	}
	
	
	public void next(String id, Tree tree) {
		List<RegionDO> regionDOList = regionDao.selectByPid(id);
		if (CollectionUtils.isNotEmpty(regionDOList)) {
			Tree childrenTree = new Tree();
			RegionDO newRegionDo = regionDao.selectById(id);
			childrenTree.setLabel(newRegionDo.getRegionName());
			childrenTree.setValue(id);
			childrenTree.setChildren(new ArrayList<Tree>());
			tree.getChildren().add(childrenTree);
			for (RegionDO regionDO : regionDOList) {
				next(regionDO.getRegionId(), childrenTree);
			}
		} else {
			RegionDO regionDO = regionDao.selectById(id);
			Tree newTree = new Tree();
			newTree.setLabel(regionDO.getRegionName());
			newTree.setValue(regionDO.getRegionId());
			List<Tree> childrenTree = tree.getChildren();
			childrenTree.add(newTree);
			tree.setChildren(childrenTree);
		}
	}

}
