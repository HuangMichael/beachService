package com.linkbit.beidou.service.line;

import com.linkbit.beidou.dao.line.StationRepository;
import com.linkbit.beidou.domain.line.Line;
import com.linkbit.beidou.domain.line.Station;
import com.linkbit.beidou.service.app.BaseService;
import com.linkbit.beidou.utils.CommonStatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by huangbin on 2016/3/24.
 * 线路业务类
 */
@Service
public class StationService extends BaseService {

    @Autowired
    StationRepository stationRepository;

    /**
     * 根据id查询站点
     */
    public Station findById(Long id) {
        Station station = null;

        if (id != null) {
            station = stationRepository.findById(id);
        }
        return station;
    }


    /**
     * @param station 车站信息
     * @return 保存车站信息
     */
    public Station save(Station station) {
        return stationRepository.save(station);
    }


    /**
     * 根据状态查询所有的站
     */
    public List<Station> findByStatus(String status) {
        status = StringUtils.isEmpty(status) ? "1" : status;
        return stationRepository.findByStatus(status);
    }

    /**
     * 根据状态查询所有的站
     */
    public List<Station> findStationsByLine(Line line) {
        List<Station> stationList = null;
        if (line != null) {
            stationList = stationRepository.findByLineAndStatus(line, "1");
        }
        return stationList;
    }


    /**
     * 根据状态查询所有的站
     *
     * @return 查询有效的站
     */
    public List<Station> findActiveStation() {
        return stationRepository.findByStatus(CommonStatusType.STATUS_YES);
    }


    /**
     * @param stationName
     * @param pageable    可分页
     * @return 根据站名模糊查询
     */
    public Page<Station> findByStationNameContains(String stationName, Pageable pageable) {
        return stationRepository.findByDescriptionContains(stationName, pageable);
    }


    /**
     * 根据状态查询所有的站
     *
     * @return 查询有效的站
     */
    public boolean delete(Long id) {
        stationRepository.update(id);
        return stationRepository.findByIdAndStatus(id, CommonStatusType.STATUS_YES) == null;
    }

}