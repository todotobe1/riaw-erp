package com.riawworks.riaw.erp.controller.sales;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.riawworks.riaw.erp.enums.SalesView;
import com.riawworks.riaw.erp.factory.sales.SalesBeanFactory;
import com.riawworks.riaw.erp.framework.json.JacksonMapper;
import com.riawworks.riaw.erp.framework.model.vo.AjaxResponseVo;
import com.riawworks.riaw.erp.framework.model.vo.PagingDataVo;
import com.riawworks.riaw.erp.model.bo.DeliveryNote;
import com.riawworks.riaw.erp.model.vo.sales.DeliveryNoteDetailVo;
import com.riawworks.riaw.erp.model.vo.sales.DeliveryNoteVo;
import com.riawworks.riaw.erp.service.sales.DeliveryService;

@Controller
@RequestMapping("/sales/deliverynote")
public class DeliveryNoteController {

	private DeliveryService deliveryService;
	private SalesBeanFactory salesBeanFactory;

	public DeliveryService getDeliveryService() {
		return deliveryService;
	}

	@Resource(name = "deliveryService")
	public void setDeliveryService(DeliveryService deliveryService) {
		this.deliveryService = deliveryService;
	}

	public SalesBeanFactory getSalesBeanFactory() {
		return salesBeanFactory;
	}

	@Resource(name = "salesBeanFactory")
	public void setSalesBeanFactory(SalesBeanFactory salesBeanFactory) {
		this.salesBeanFactory = salesBeanFactory;
	}

	@RequestMapping("/view")
	public ModelAndView view() {
		return new ModelAndView(SalesView.DELIVERYNOTE_LIST);
	}

	@RequestMapping("/additionView")
	public ModelAndView additionView() {
		return new ModelAndView(SalesView.DELIVERYNOTE_ADDITION);
	}

	@RequestMapping("/editView")
	public ModelAndView editView(HttpServletRequest httpServletRequest)
			throws Exception {
		DeliveryNote deliveryNote = getDeliveryService().readDeliveryNote(
				Integer.valueOf(httpServletRequest.getParameter("id")));
		return new ModelAndView(SalesView.DELIVERYNOTE_EDIT).addObject(
				"deliveryNote",
				getSalesBeanFactory().createDeliveryNoteVo(deliveryNote));
	}

	@RequestMapping("/list")
	public PagingDataVo<DeliveryNoteVo> list(HttpServletRequest httpServletRequest)
			throws Exception {
		PagingDataVo<DeliveryNoteVo> pagingData = new PagingDataVo<DeliveryNoteVo>();
		pagingData.setTotal(getDeliveryService().readDeliveryNotesCount());
		String page = httpServletRequest.getParameter("page");
		String rows = httpServletRequest.getParameter("rows");
		List<DeliveryNote> deliveryNotes = getDeliveryService()
				.readDeliveryNotes(Integer.valueOf(page), Integer.valueOf(rows));

		List<DeliveryNoteVo> deliveryNoteVos = new ArrayList<DeliveryNoteVo>();
		for (DeliveryNote deliveryNote : deliveryNotes) {
			deliveryNoteVos.add(getSalesBeanFactory().createDeliveryNoteVo(
					deliveryNote));
		}

		pagingData.setRows(deliveryNoteVos);

		return pagingData;
	}

	@RequestMapping("/add")
	public AjaxResponseVo<Integer> add(HttpServletRequest httpServletRequest) {
		String recordNo = httpServletRequest.getParameter("recordNo");
		String customer = httpServletRequest.getParameter("customer");
		String deliveryVan = httpServletRequest.getParameter("deliveryVan");
		String deliveryDate = httpServletRequest.getParameter("deliveryDate");
		String deliveryMan = httpServletRequest.getParameter("deliveryMan");
		String containerDeliver = httpServletRequest
				.getParameter("containerDeliver");
		String containerTake = httpServletRequest.getParameter("containerTake");
		try {
			return new AjaxResponseVo<Integer>(AjaxResponseVo.SUCCEED, null,
					getDeliveryService().createDeliveryNote(
							recordNo,
							customer,
							deliveryVan,
							deliveryDate,
							deliveryMan,
							containerDeliver,
							containerTake,
							JacksonMapper.getInstance().readValue(
									httpServletRequest
											.getParameter("effectRows"),
									Map.class)));
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResponseVo<Integer>(AjaxResponseVo.FAIL,
					e.getMessage());
		}
	}

	@RequestMapping("/delete")
	public AjaxResponseVo<Object> delete(HttpServletRequest httpServletRequest)
			throws Exception {
		String ids = httpServletRequest.getParameter("deliveryNoteId");
		Integer[] idArr = JacksonMapper.getInstance().readValue(ids,
				Integer[].class);
		for (Integer id : idArr) {
			getDeliveryService().deleteDeliveryNote(id);
		}
		return new AjaxResponseVo<Object>(AjaxResponseVo.SUCCEED);
	}

	@RequestMapping("/detail")
	public List<DeliveryNoteDetailVo> detail(
			HttpServletRequest httpServletRequest) throws Exception {
		String id = httpServletRequest.getParameter("id");
		return getDeliveryService().readDeliveryNoteDetail(Integer.valueOf(id));
	}

	@RequestMapping("/edit")
	public AjaxResponseVo<Object> edit(HttpServletRequest httpServletRequest)
			throws Exception {
		String recordNo = httpServletRequest.getParameter("recordNo");
		String deliveryNoteId = httpServletRequest
				.getParameter("deliveryNoteId");
		String customer = httpServletRequest.getParameter("customer");
		String deliveryVan = httpServletRequest.getParameter("deliveryVan");
		String deliveryDate = httpServletRequest.getParameter("deliveryDate");
		String deliveryMan = httpServletRequest.getParameter("deliveryMan");
		String containerDeliver = httpServletRequest
				.getParameter("containerDeliver");
		String containerTake = httpServletRequest.getParameter("containerTake");
		try {
			getDeliveryService().updateDeliveryNote(
					deliveryNoteId,
					recordNo,
					customer,
					deliveryVan,
					deliveryDate,
					deliveryMan,
					containerDeliver,
					containerTake,
					JacksonMapper.getInstance().readValue(
							httpServletRequest.getParameter("effectRows"),
							Map.class));
			return new AjaxResponseVo<Object>(AjaxResponseVo.SUCCEED);
		} catch (Exception e) {
			return new AjaxResponseVo<Object>(AjaxResponseVo.FAIL,
					e.getMessage());
		}
	}

}
