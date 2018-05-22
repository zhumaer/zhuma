package com.zm.zhuma.user.model.po;

import com.zm.zhuma.commons.annotations.EnumValue;
import com.zm.zhuma.commons.model.po.BasePO;
import com.zm.zhuma.commons.model.po.BaseSortTreePO;
import com.zm.zhuma.commons.validator.CreateGroup;
import com.zm.zhuma.commons.validator.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @desc 用户PO

 * @author zhumaer
 * @since 6/15/2017 2:48 PM
 */
@ApiModel("用户PO")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Org extends BaseSortTreePO<Long> {

	private static final long serialVersionUID = 2623905517895913619L;

	@ApiModelProperty(value = "主键")
	@Id
	@GeneratedValue(generator = "JDBC")
	private Long id;

	@ApiModelProperty(value = "组织架构名称")
	@NotBlank(groups = CreateGroup.class)
	@Length(min=1, max=64, groups = {CreateGroup.class, UpdateGroup.class})
	private String name;

	@ApiModelProperty(value = "类型")
	@NotBlank(groups = CreateGroup.class)
	@EnumValue(enumClass=TypeEnum.class, enumMethod="isValidName", groups = {CreateGroup.class, UpdateGroup.class})
	private String type;

	/**
	 * 组织架构类型枚举
	 */
	public enum TypeEnum {
		/**公司*/
		COMPANY,
		/**部门*/
		DEPARTMENT,
		/**组别*/
		GROUP;

		public static boolean isValidName(String name) {
			for (TypeEnum typeEnum : TypeEnum.values()) {
				if (typeEnum.name().equals(name)) {
					return true;
				}
			}
			return false;
		}
	}

}
