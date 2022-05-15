package com.zyinnju.draw.shape;

import com.zyinnju.draw.AbstractContent;
import com.zyinnju.enums.ShapeType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * 抽象的图形类
 * @author Zyi
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class AbstractShape extends AbstractContent implements Serializable {

	/**
	 * 绘制该图形的粗细
	 */
	protected Integer thickness;
	/**
	 * 图形的类型
	 */
	protected ShapeType shapeType;
}
