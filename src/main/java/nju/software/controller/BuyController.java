package nju.software.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nju.software.dataobject.Accessory;
import nju.software.dataobject.Fabric;
import nju.software.dataobject.FabricCost;
import nju.software.dataobject.Logistics;
import nju.software.model.OrderInfo;
import nju.software.service.BuyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BuyController {

	// ===========================采购验证=================================
	@RequestMapping(value = "/buy/verifyPurchaseList.do")
	@Transactional(rollbackFor = Exception.class)
	public String verifyPurchaseList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		List<Map<String, Object>> list = buyService.getVerifyPurchaseList();
		model.put("list", list);
		model.addAttribute("taskName", "采购验证");
		model.addAttribute("url", "/buy/verifyPurchaseDetail.do");
		return "/buy/verifyPurchaseList";
	}

	@RequestMapping(value = "/buy/verifyPurchaseDetail.do")
	@Transactional(rollbackFor = Exception.class)
	public String verifyPurchaseDetail(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String orderId = request.getParameter("orderId");
		Map<String, Object> orderInfo = buyService
				.getVerifyPurchaseDetail(Integer.parseInt(orderId));
		model.addAttribute("orderInfo", orderInfo);
		return "/buy/verifyPurchaseDetail";
	}

	@RequestMapping(value = "/buy/verifyPurchaseSubmit.do")
	@Transactional(rollbackFor = Exception.class)
	public String verifyPurchaseSubmit(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Long taskId = Long.parseLong(request.getParameter("taskId"));
		String comment = request.getParameter("suggestion");
		boolean result = Boolean.parseBoolean(request.getParameter("result"));
		buyService.verifyPurchaseSubmit(taskId, result, comment);
		return "forward:/buy/verifyPurchaseList.do";
	}

	// ===========================采购成本核算=================================
	@RequestMapping(value = "/buy/computePurchaseCostList.do")
	@Transactional(rollbackFor = Exception.class)
	public String computePurchaseCostList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		List<Map<String, Object>> list = buyService
				.getComputePurchaseCostList();
		model.addAttribute("list", list);
		model.addAttribute("taskName", "采购成本核算");
		model.addAttribute("url", "/buy/computePurchaseCostDetail.do");
		return "/buy/computePurchaseCostList";
	}

	@RequestMapping(value = "/buy/computePurchaseCostDetail.do")
	@Transactional(rollbackFor = Exception.class)
	public String computePurchaseCostDetail(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String orderId = request.getParameter("orderId");
		Map<String, Object> orderInfo = buyService
				.getComputePurchaseCostDetail(Integer.parseInt(orderId));
		model.addAttribute("orderInfo", orderInfo);
		return "/buy/computePurchaseCostDetail";
	}

	@RequestMapping(value = "/buy/computePurchaseCostSubmit.do")
	@Transactional(rollbackFor = Exception.class)
	public String computePurchaseCostSubmit(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {

	    Integer orderId = Integer.parseInt(request.getParameter("orderId"));
		long taskId = Long.parseLong(request.getParameter("taskId"));
		
		
		// 面料数据
				String fabric_name = request.getParameter("fabric_name");
				String tear_per_meter = request.getParameter("tear_per_meter");
				String cost_per_meter = request.getParameter("cost_per_meter");
				String fabric_names_temp[] = fabric_name.split(",");
				String tear_per_meters_temp[] = tear_per_meter.split(",");
				String cost_per_meters_temp[] = cost_per_meter.split(",");
				
				
				
				System.out.println("================================");
				System.out.println(fabric_name);
				System.out.println("================================");
				
				
//				List<Fabric> fabrics = new ArrayList<Fabric>();
				List<String> fabric_names_list=new ArrayList<String>();
				List<String> cost_per_meters_list=new ArrayList<String>();
				List<String> tear_per_meters_list=new ArrayList<String>();
//				List<FabricCost> fabricCosts=new ArrayList<FabricCost>();
				for (int i = 0; i < fabric_names_temp.length; i++) {
					
					if (fabric_names_temp[i].equals(""))
						continue;
					fabric_names_list.add(fabric_names_temp[i]);
					System.out.println("================================");
					System.out.println(fabric_names_temp[i]);
					System.out.println("================================");
					tear_per_meters_list.add(tear_per_meters_temp[i]);
					cost_per_meters_list.add(cost_per_meters_temp[i]);
				}

				 int size=fabric_names_list.size();  
			        String[] fabric_names = (String[])fabric_names_list.toArray(new String[size]);  
			        String[] tear_per_meters = (String[])tear_per_meters_list.toArray(new String[size]);  
			        String[] cost_per_meters = (String[])cost_per_meters_list.toArray(new String[size]);  
				
				
				// 辅料数据
				String accessory_name = request.getParameter("accessory_name");
				String tear_per_pieces = request.getParameter("tear_per_piece");
				String cost_per_pieces = request.getParameter("cost_per_piece");
				String accessory_names_temp[] = accessory_name.split(",");
				String tear_per_piece_temp[] = tear_per_pieces.split(",");
				String cost_per_piece_temp[] = cost_per_pieces.split(",");

				List<String> accessory_names_list=new ArrayList<String>();
				List<String> tear_per_piece_list=new ArrayList<String>();
				List<String> cost_per_piece_list=new ArrayList<String>();
	          for (int i = 0; i < accessory_names_temp.length; i++) {
					
					if (accessory_names_temp[i].equals(""))
						continue;
					accessory_names_list.add(accessory_names_temp[i]);
					tear_per_piece_list.add(tear_per_piece_temp[i]);
					cost_per_piece_list.add(cost_per_piece_temp[i]);
				}
				
	          int accessory_size=accessory_names_list.size();  
		        String[] accessory_names = (String[])accessory_names_list.toArray(new String[accessory_size]);  
		        String[] tear_per_piece = (String[])tear_per_piece_list.toArray(new String[accessory_size]);  
		        String[] cost_per_piece = (String[])cost_per_piece_list.toArray(new String[accessory_size]);  
				
//		String[] fabric_names = request.getParameterValues("fabricName");
//		String[] tear_per_meters = request.getParameterValues("tear_per_meter");
//		String[] cost_per_meters = request.getParameterValues("cost_per_meter");
		
		
//		String[] fabric_prices = request.getParameterValues("fabric_price");

//		String[] accessory_names = request.getParameterValues("accessoryName");
//		String[] tear_per_piece = request.getParameterValues("tear_per_piece");
//		String[] cost_per_piece = request.getParameterValues("cost_per_piece");
//		String[] accessory_prices = request
//				.getParameterValues("accessory_price");

		buyService.computePurchaseCostSubmit(orderId, taskId, fabric_names,
				tear_per_meters, cost_per_meters,
				accessory_names, tear_per_piece, cost_per_piece
				);

		return "forward:/buy/computePurchaseCostList.do";

	}

	// ========================样衣原料采购===========================
	@RequestMapping(value = "/buy/purchaseSampleMaterialList.do")
	@Transactional(rollbackFor = Exception.class)
	public String purchaseSampleMaterialList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		List<Map<String, Object>> list = buyService
				.getPurchaseSampleMaterialList();
		model.put("list", list);
		model.addAttribute("taskName", "样衣原料采购");
		model.addAttribute("url", "/buy/purchaseSampleMaterialDetail.do");
		return "/buy/purchaseSampleMaterialList";
	}

	@RequestMapping(value = "/buy/purchaseSampleMaterialDetail.do")
	@Transactional(rollbackFor = Exception.class)
	public String purchaseSampleMaterialDetail(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Integer orderId = Integer.parseInt(request.getParameter("orderId"));
		Map<String, Object> orderInfo = buyService
				.getPurchaseSampleMaterialDetail(orderId);
		model.addAttribute("orderInfo", orderInfo);
		return "/buy/purchaseSampleMaterialDetail";
	}

	@RequestMapping(value = "/buy/purchaseSampleMaterialSubmit.do")
	@Transactional(rollbackFor = Exception.class)
	public String purchaseSampleMaterialSubmit(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String taskId = request.getParameter("taskId");
		boolean result = request.getParameter("result").equals("1");
		buyService.purchaseSampleMaterialSubmit(Long.parseLong(taskId), result);
		return "forward:/buy/purchaseSampleMaterialList.do";
	}

	// ========================生产验证===========================
	@RequestMapping(value = "/buy/confirmPurchaseList.do")
	@Transactional(rollbackFor = Exception.class)
	public String confirmPurchaseList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		List<Map<String, Object>> list = buyService.getConfirmPurchaseList();
		model.put("list", list);
		model.addAttribute("taskName", "确认生产原料");
		model.addAttribute("url", "/buy/confirmPurchaseDetail.do");
		return "/buy/confirmPurchaseList";
	}

	@RequestMapping(value = "/buy/confirmPurchaseDetail.do")
	@Transactional(rollbackFor = Exception.class)
	public String confirmPurchaseDetail(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String orderId = request.getParameter("orderId");
		Map<String, Object> orderInfo = buyService
				.getConfirmPurchaseDetail(Integer.parseInt(orderId));
		model.addAttribute("orderInfo", orderInfo);
		return "/buy/confirmPurchaseDetail";
	}

	@RequestMapping(value = "/buy/confirmPurchaseSubmit.do")
	@Transactional(rollbackFor = Exception.class)
	public String confirmPurchaseSubmit(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String taskId = request.getParameter("taskId");
		Boolean result = request.getParameter("result").equals("1");
		buyService.confirmPurchaseSubmit(Long.parseLong(taskId), result);
		return "forward:/buy/confirmPurchaseList.do";
	}

	// ========================生产采购===========================
	@RequestMapping(value = "/buy/purchaseMaterialList.do")
	@Transactional(rollbackFor = Exception.class)
	public String purchaseMaterialList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		List<Map<String, Object>> list = buyService.getPurchaseMaterialList();
		model.put("list", list);
		model.addAttribute("taskName", "生产采购");
		model.addAttribute("url", "/buy/purchaseMaterialDetail.do");
		return "/buy/purchaseMaterialList";
	}

	@RequestMapping(value = "/buy/purchaseMaterialDetail.do")
	@Transactional(rollbackFor = Exception.class)
	public String purchaseMaterialDetail(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String orderId = request.getParameter("orderId");
		Map<String, Object> orderInfo = buyService
				.getPurchaseMaterialDetail(Integer.parseInt(orderId));
		model.addAttribute("orderInfo", orderInfo);
		return "/buy/purchaseMaterialDetail";
	}

	@RequestMapping(value = "/buy/purchaseMaterialSubmit.do")
	@Transactional(rollbackFor = Exception.class)
	public String purchaseMaterialSubmit(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String taskId = request.getParameter("taskId");
		Boolean result = request.getParameter("result").equals("1");
		buyService.purchaseMaterialSubmit(Long.parseLong(taskId), result);
		return "forward:/buy/purchaseMaterialList.do";
	}

	@Autowired
	private BuyService buyService;
}
