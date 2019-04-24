package com.study.mapper;

import com.study.orm.Attachment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentMapper {
    boolean insert(Attachment attachment);

    List<Attachment> fetchByEmailId(Integer emailId);
}
