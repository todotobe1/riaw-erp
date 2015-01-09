package com.riawworks.riaw.erp.factory.sales;

import com.riawworks.riaw.erp.framework.exception.BeanConversionException;
import com.riawworks.riaw.erp.framework.factory.BeanFactory;
import com.riawworks.riaw.erp.model.bo.DeliveryNote;
import com.riawworks.riaw.erp.model.vo.sales.DeliveryNoteVo;

public interface SalesBeanFactory extends BeanFactory {

	public DeliveryNoteVo createDeliveryNoteVo(DeliveryNote deliveryNote)
			throws BeanConversionException;

}
