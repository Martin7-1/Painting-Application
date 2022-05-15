package com.zyinnju.draw;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 坐标点
 *
 * @author Zyi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Point implements Serializable {

	private Integer x;

	private Integer y;
}
