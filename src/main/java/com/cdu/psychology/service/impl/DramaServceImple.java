package com.cdu.psychology.service.impl;

import com.cdu.psychology.dao.DramaDao;
import com.cdu.psychology.entity.Show;
import com.cdu.psychology.service.DramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DramaServceImple implements DramaService {

    @Autowired
    private DramaDao dramaDao;
    @Override
    public int putShow(Show show) {
        return dramaDao.putShow(show);
    }
}
