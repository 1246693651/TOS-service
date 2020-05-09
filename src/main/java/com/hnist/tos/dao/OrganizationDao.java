package com.hnist.tos.dao;

import com.hnist.tos.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrganizationDao extends JpaRepository<Organization,Long>, JpaSpecificationExecutor<Organization> {
}
