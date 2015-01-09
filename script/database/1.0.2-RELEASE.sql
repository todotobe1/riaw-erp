ALTER TABLE `riaw`.`t_cost_allocation_subject`	DROP COLUMN `parent_subject_id`;
ALTER TABLE `riaw`.`t_cost_allocation_record` DROP COLUMN `partner_code`;
ALTER TABLE `riaw`.`t_cost_allocation_record` DROP COLUMN `cost_allocation_subject_code`;

CREATE TABLE `riaw`.`t_cost_allocation_formula_var` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ctime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '记录创建时间',
  `mtime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '记录修改时间',
  `subject_id` INT UNSIGNED NOT NULL COMMENT '科目ID',
  `variable_name` VARCHAR(45) NOT NULL COMMENT '变量名称',
  `variable_value` DOUBLE(10, 2) UNSIGNED NOT NULL COMMENT '变量值',
  `variable_unit` VARCHAR(45) NOT NULL COMMENT '变量单位',
  PRIMARY KEY (`id`))
COMMENT = '成本分摊科目计算公式变量表';

CREATE TABLE `riaw`.`t_cost_allocation_partner` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ctime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '记录创建时间',
  `mtime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '记录修改时间',
  `subject_id` INT UNSIGNED NOT NULL COMMENT '科目ID',
  `partner_id` INT UNSIGNED NOT NULL COMMENT '合伙人ID',
  PRIMARY KEY (`id`))
COMMENT = '合伙人与成本分摊科目关联表';