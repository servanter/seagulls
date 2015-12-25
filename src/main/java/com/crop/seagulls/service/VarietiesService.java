package com.crop.seagulls.service;

import java.util.List;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.Varieties;

public interface VarietiesService {

    public List<Varieties> findAll();

    public Paging<Varieties> findList(Varieties varieties);

    public Response findById(Integer id);

    public Response modify(Varieties varieties);

    public Response remove(Long id);

    public Response publish(Varieties varieties);

    public Response add(Varieties varieties, Long start, Long end);
    
    public Response refresh();
}
