package com.riawworks.riaw.erp.factory.sales.impl;

import java.text.SimpleDateFormat;

import org.apache.commons.beanutils.BeanUtils;

import com.riawworks.riaw.erp.factory.sales.SalesBeanFactory;
import com.riawworks.riaw.erp.framework.exception.BeanConversionException;
import com.riawworks.riaw.erp.framework.factory.impl.BeanFactoryImpl;
import com.riawworks.riaw.erp.model.bo.DeliveryNote;
import com.riawworks.riaw.erp.model.vo.sales.DeliveryNoteVo;

public class SalesBeanFactoryImpl extends BeanFactoryImpl implements
		SalesBeanFactory {

	@Override
	public DeliveryNoteVo createDeliveryNoteVo(DeliveryNote deliveryNote)
			throws BeanConversionException {
		try {
			DeliveryNoteVo deliveryNoteVo = new DeliveryNoteVo();
			BeanUtils.copyProperties(deliveryNoteVo, deliveryNote);
			deliveryNoteVo.setDeliveryDate(new SimpleDateFormat("yyyy-MM-dd")
					.format(deliveryNote.getDeliveryDate()));
			return deliveryNoteVo;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeanConversionException(e.getMessage(), e);
		}
	}

}
