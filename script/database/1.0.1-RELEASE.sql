-- 记录出货单编辑时的真实单价
alter table riaw.t_delivery_note_detail add column `unit_price` double(10, 2) unsigned not null comment '单价' after `quantity`;

-- 迁移标准单价
update riaw.t_delivery_note_detail t 
set 
    t.unit_price = (select 
            a.unit_price
        from
            riaw.t_product_pricing a
        where
            a.id = t.product_pricing_id)
where
    exists( select 
            1
        from
            riaw.t_product_pricing a
        where
            a.id = t.product_pricing_id);

CREATE TABLE `riaw`.`t_customer` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ctime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '记录创建时间',
  `mtime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '记录修改时间',
  `enabled` CHAR NOT NULL DEFAULT '1' COMMENT '生效状态（0：失效、1：生效）',
  `code` VARCHAR(45) NOT NULL COMMENT '客户编码',
  `name` VARCHAR(45) NOT NULL COMMENT '客户名称',
  `liaison` VARCHAR(45) NOT NULL COMMENT '联系人',
  `telephone` VARCHAR(45) NOT NULL COMMENT '联系电话',
  PRIMARY KEY (`id`))
COMMENT = '客户表';

-- 加长url字段 45 -> 100
alter table riaw.t_menu modify url varchar(100);

CREATE TABLE `riaw`.`t_customized_product_pricing` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ctime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '记录创建时间',
  `mtime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '记录修改时间',
  `product_pricing_id` INT UNSIGNED NOT NULL COMMENT '产品定价ID',
  `customer_id` INT UNSIGNED NOT NULL COMMENT '客户ID',
  `customer_code` VARCHAR(45) NOT NULL COMMENT '客户编码',
  `unit_price` DOUBLE(10, 2) UNSIGNED NOT NULL COMMENT '单价',
  PRIMARY KEY (`id`))
COMMENT = '客户产品定制价表';

-- 新增产品ID和单位ID来替换原来的定价ID
alter table riaw.t_delivery_note_detail add column `product_id` INT UNSIGNED NOT NULL COMMENT '产品ID' after `delivery_note_id`;
alter table riaw.t_delivery_note_detail add column `unit_id` INT UNSIGNED NOT NULL COMMENT '计量单位ID' after `product_id`;
update riaw.t_delivery_note_detail t
      set t.product_id = (select product_id from riaw.t_product_pricing where id = t.product_pricing_id),
           t.unit_id = (select unit_id from riaw.t_product_pricing where id = t.product_pricing_id)
 where exists (select 1 from riaw.t_product_pricing where id = t.product_pricing_id);
 alter table riaw.t_delivery_note_detail drop column `product_pricing_id`; 

-- 合伙人成本分摊
 CREATE TABLE `riaw`.`t_partner` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ctime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '记录创建时间',
  `mtime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '记录修改时间',
  `human_id` INT UNSIGNED NOT NULL COMMENT '系统人员ID',
  `partner_code` VARCHAR(45) NOT NULL COMMENT '合伙人编码',
  PRIMARY KEY (`id`))
COMMENT = '合伙人表';

 CREATE TABLE `riaw`.`t_cost_allocation_record` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ctime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '记录创建时间',
  `mtime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '记录修改时间',
  `period` CHAR(6) NOT NULL COMMENT '记录账期（yyyymm）',
  `partner_id` INT UNSIGNED NOT NULL COMMENT '合伙人ID',
  `partner_code` VARCHAR(45) NOT NULL COMMENT '合伙人编码',
  `cost_allocation_subject_id` INT UNSIGNED NOT NULL COMMENT '成本分摊科目ID',
  `cost_allocation_subject_code` VARCHAR(45) NOT NULL COMMENT '成本分摊科目编码',
  `data` DOUBLE(10, 2) UNSIGNED NULL COMMENT '本账期数据',
  `charge` DOUBLE(10, 2) UNSIGNED NOT NULL COMMENT '本账期费用',
  PRIMARY KEY (`id`))
COMMENT = '成本分摊记录表';

 CREATE TABLE `riaw`.`t_cost_allocation_subject` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ctime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '记录创建时间',
  `mtime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '记录修改时间',
  `subject_name` VARCHAR(45) NOT NULL COMMENT '科目名称',
  `subject_code` VARCHAR(45) NOT NULL COMMENT '科目编码',
  `formula` VARCHAR(45) COMMENT '标准计费公式',
  `charging_type` char(1) NULL COMMENT '计费类型（0：累计计费、1：当前计费）',
  `parent_subject_id` INT UNSIGNED NULL COMMENT '父级科目ID',
  PRIMARY KEY (`id`))
COMMENT = '成本分摊科目表';