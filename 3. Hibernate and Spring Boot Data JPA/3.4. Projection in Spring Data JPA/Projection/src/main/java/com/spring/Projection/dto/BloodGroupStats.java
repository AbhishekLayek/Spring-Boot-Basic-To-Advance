package com.spring.Projection.dto;

import com.spring.Projection.entities.type.BloodGroupType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BloodGroupStats {
	private BloodGroupType bloodGroup;
	private Long count;
}
