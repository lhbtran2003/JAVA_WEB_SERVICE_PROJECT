package com.example.internship.mapper;

import com.example.internship.dto.request.roundcriteria.AddRoundCriteria;
import com.example.internship.dto.request.roundcriteria.UpdateRoundCriteria;
import com.example.internship.entity.RoundCriteria;

public class RoundCriteriaMapper {
    public static RoundCriteria toEntity (AddRoundCriteria request) {
        RoundCriteria roundCriteria = new RoundCriteria();
        roundCriteria.setWeight(request.getWeight());
        return roundCriteria;
    }

    public static RoundCriteria toEntity (RoundCriteria roundCriteriaExisted, UpdateRoundCriteria request) {
        roundCriteriaExisted.setWeight(request.getWeight());
        return roundCriteriaExisted;
    }
}
