package com.op.paper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaperServicelmpl implements PaperService {

    @Autowired
    private PaperRepository paperRepository;

    @Override
    public Paper create(Paper paper){
        return paperRepository.save(paper);
    }

    @Override
    public List<Paper> findAll(){
        return paperRepository.findAll();
    }

    @Override
    public Optional<Paper> findById() {
        return Optional.empty();
    }

    public Optional<Paper> updateById(String id,Paper paper){
        Optional<Paper> optional = paperRepository.findById(id);
        if(optional.isPresent()){
            Paper existedPaper = optional.get();

            existedPaper.setColor(paper.getColor());
            existedPaper.setSize(paper.getSize());
            existedPaper.setCount(paper.getCount());
            existedPaper.setType(paper.getColor());
        }
        return optional;
    }
}
