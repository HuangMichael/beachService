package com.linkbit.beidou.service.line;

import com.linkbit.beidou.dao.line.LineRepository;
import com.linkbit.beidou.domain.line.Line;
import com.linkbit.beidou.service.app.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by huangbin on 2016/3/24.
 * 线路业务类
 */
@Service
public class LineService extends BaseService {

    @Autowired
    LineRepository lineRepository;

    /**
     * 根据状态查询所有启用的线路
     */
    public List<Line> findByStatus(String status) {
        status = StringUtils.isEmpty(status) ? "1" : status;
        return lineRepository.findByStatus(status);
    }

    /**
     * 根据id查询线路
     */
    public Line findById(Long id) {

        Line line = null;

        if (id != null) {
            line = lineRepository.findById(id);
        }
        return line;

    }


    /**
     * @param line
     * @return保存线路信息
     */
    public Line save(Line line) {
        return lineRepository.save(line);
    }

    /**
     * @return 查询所有线
     */
    public List<Line> findLines() {
        return lineRepository.findByType("1");
    }


    /**
     * @return 查询所有段
     */
    public List<Line> findSegs() {

        return lineRepository.findByType("2");
    }


    /**
     * @return 查询所有段
     */
    public List<Line> findAll() {
        return lineRepository.findAll();
    }
}
