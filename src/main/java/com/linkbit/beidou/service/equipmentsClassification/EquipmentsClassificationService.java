package com.linkbit.beidou.service.equipmentsClassification;

import com.linkbit.beidou.dao.equipments.EquipmentsClassificationRepository;
import com.linkbit.beidou.domain.equipments.EquipmentsClassification;
import com.linkbit.beidou.domain.outsourcingUnit.OutsourcingUnit;
import com.linkbit.beidou.service.app.BaseService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangbin on 2016/3/24.
 */
@Service
public class EquipmentsClassificationService extends BaseService {

    @Autowired
    @Getter
    EquipmentsClassificationRepository equipmentsClassificationRepository;

    /**
     * 根据上级设备种类生成子类编号
     */
    public String getCodeByParent(EquipmentsClassification equipmentClassification) {
        String code = "";
        if (equipmentClassification != null) {
            code += equipmentClassification.getClassId();
            int childrenSize = equipmentClassification.getClassificationList().size();
            for (int i = 1; i < 3 - (childrenSize + "").length(); i++) {
                code += "0";
            }
            code += (childrenSize + 1);
        } else {
            code = "001";
        }
        return code;
    }

    /**
     * 新建设备分类
     */
    public EquipmentsClassification create(Long parentId) {
        EquipmentsClassification parent = equipmentsClassificationRepository.findById(parentId);
        EquipmentsClassification newObj = new EquipmentsClassification();
        newObj.setClassId(this.getCodeByParent(parent));
        newObj.setParent(parent);
        return newObj;

    }


    /**
     * 新建设备分类
     */
    public List<EquipmentsClassification> findAll() {
        List<EquipmentsClassification> equipmentsClassificationList = equipmentsClassificationRepository.findAll();
        return equipmentsClassificationList;
    }

    /**
     * 保存设备分类
     */
    public EquipmentsClassification save(EquipmentsClassification equipmentsClassification) {
        return equipmentsClassificationRepository.save(equipmentsClassification);
    }

    /**
     * 根据编号查询种类
     */

    public EquipmentsClassification findById(Long id) {
        return equipmentsClassificationRepository.findById(id);
    }

    /**
     * 删除设备种类
     */

    public void delete(EquipmentsClassification equipmentsClassification) {
        equipmentsClassificationRepository.delete(equipmentsClassification);
    }


    public List<Long> getUnitsByEqClassId(Long cid) {
        EquipmentsClassification equipmentsClassification;
        List<Long> idList = new ArrayList<Long>();
        if (cid != null) {
            equipmentsClassification = equipmentsClassificationRepository.findById(cid);
            List<OutsourcingUnit> unitList = equipmentsClassification.getUnitSet();
            for (OutsourcingUnit unit : unitList) {
                idList.add(unit.getId());
            }

            if (idList.isEmpty()) {
                idList.add(0l);
            }
        }
        return idList;
    }
}
