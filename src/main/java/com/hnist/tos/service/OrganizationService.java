package com.hnist.tos.service;

import com.hnist.tos.entity.Organization;

import org.springframework.data.domain.Page;
import java.util.Map;

/**
 * @author Pany
 * @date 2020-05-06 19:24
 * @content
 */

public interface OrganizationService {
    void add(Organization organization);

    void deleteById(Long id);

    void update(Organization organization);

    Organization findById(Long id);

    Page<Organization> findAll(int page, int size, Map map);
}
