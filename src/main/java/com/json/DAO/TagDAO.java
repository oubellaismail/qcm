package com.json.DAO;

import com.json.model.Tag;

public interface TagDAO {
    int insertTag(Tag tag);
    Tag findTag(int id);
    void deleteTag(int id);
}
