package com.op.paper;

import java.util.List;
import java.util.Optional;

public interface PaperService {

    Paper create(Paper paper);

    List<Paper> findAll();

    Optional<Paper> findById();

    Optional<Paper> updateById(String id,Paper paper);
}
