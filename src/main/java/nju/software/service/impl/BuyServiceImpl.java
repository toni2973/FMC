package nju.software.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.task.query.TaskSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nju.software.dao.impl.AccessoryCostDAO;
import nju.software.dao.impl.AccessoryDAO;
import nju.software.dao.impl.CraftDAO;
import nju.software.dao.impl.CustomerDAO;
import nju.software.dao.impl.EmployeeDAO;
import nju.software.dao.impl.FabricCostDAO;
import nju.software.dao.impl.FabricDAO;
import nju.software.dao.impl.LogisticsDAO;
import nju.software.dao.impl.OrderDAO;
import nju.software.dao.impl.ProductDAO;
import nju.software.dao.impl.ProduceDAO;
import nju.software.dao.impl.QuoteDAO;
import nju.software.dataobject.Accessory;
import nju.software.dataobject.AccessoryCost;
import nju.software.dataobject.Craft;
import nju.software.dataobject.Customer;
import nju.software.dataobject.Fabric;
import nju.software.dataobject.FabricCost;
import nju.software.dataobject.Order;
import nju.software.dataobject.Produce;
import nju.software.dataobject.Quote;
import nju.software.model.OrderInfo;
import nju.software.service.BuyService;
import nju.software.util.JbpmAPIUtil;

@Service("buyServiceImpl")
public class BuyServiceImpl implements BuyService {

	// ===========================采购验证=================================
	public List<Map<String, Object>> getVerifyPurchaseList() {
		return service.getOrderList(ACTOR_PURCHASE_MANAGER,
				TASK_VERIFY_PURCHASE);
	}

	@Override
	public List<Map<String, Object>> getSearchVerifyPurchaseList(
			String ordernumber, String customername, String stylename,
			String startdate, String enddate, Integer[] employeeIds) {
		return service.getSearchOrderList(ACTOR_PURCHASE_MANAGER, ordernumber, customername, stylename,
				 startdate, enddate, employeeIds,
				TASK_VERIFY_PURCHASE);
	}
	
	@Override
	public Map<String, Object> getVerifyPurchaseDetail(Integer orderId) {
		// TODO Auto-generated method stub
		return service.getBasicOrderModel(ACTOR_PURCHASE_MANAGER,
				TASK_VERIFY_PURCHASE, orderId);
	}

	@Override
	public boolean verifyPurchaseSubmit(long taskId, boolean result,
			String comment) {
		// TODO Auto-generated method stub
		Map<String, Object> data = new HashMap<String, Object>();
		data.put(RESULT_PURCHASE, result);
		data.put(RESULT_PURCHASE_COMMENT, comment);
		try {
			jbpmAPIUtil.completeTask(taskId, data, ACTOR_PURCHASE_MANAGER);
			return true;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	// ===========================采购成本核算=================================
	@Override
	public List<Map<String, Object>> getComputePurchaseCostList() {
		// TODO Auto-generated method stub
		return service.getOrderList(ACTOR_PURCHASE_MANAGER,
				TASK_COMPUTE_PURCHASE_COST);
	}
	
	@Override
	public List<Map<String, Object>> getSearchComputePurchaseCostList(
			String ordernumber, String customername, String stylename,
			String startdate, String enddate, Integer[] employeeIds) {
		return service.getSearchOrderList(ACTOR_PURCHASE_MANAGER, ordernumber,  customername,  stylename,
				 startdate,  enddate, employeeIds,
				TASK_COMPUTE_PURCHASE_COST);
	}
	
	@Override
	public Map<String, Object> getComputePurchaseCostDetail(Integer orderId) {
		// TODO Auto-generated method stub
		return service.getBasicOrderModel(ACTOR_PURCHASE_MANAGER,
				TASK_COMPUTE_PURCHASE_COST, orderId);
	}

	@Override
	public void computePurchaseCostSubmit(int orderId, long taskId,
			boolean result, String comment, String[] fabric_names,
			String[] fabric_amounts, String[] tear_per_meters,
			String[] cost_per_meters, String[] accessory_names,
			String[] accessory_querys, String[] tear_per_piece,
			String[] cost_per_piece) {

		List<Fabric> FabricList = fabricDAO.findByOrderId(orderId);
		List<Accessory> AccessoryList = accessoryDAO.findByOrderId(orderId);

		for (Fabric fabric : FabricList) {
			fabricDAO.delete(fabric);
		}

		for (Accessory accessory : AccessoryList) {
			accessoryDAO.delete(accessory);
		}

		float all_fabric_prices = 0.0f;
		float all_accessory_prices = 0.0f;
		if (fabric_names != null) {
			for (int i = 0; i < fabric_names.length; i++) {
				Fabric fabric = new Fabric();
				fabric.setOrderId(orderId);
				fabric.setFabricName(fabric_names[i]);
				fabric.setFabricAmount(fabric_amounts[i]);

				FabricCost fabricCost = new FabricCost();
				fabricCost.setOrderId(orderId);
				fabricCost.setFabricName(fabric_names[i]);
				fabricCost
						.setTearPerMeter(Float.parseFloat(tear_per_meters[i]));
				fabricCost
						.setCostPerMeter(Float.parseFloat(cost_per_meters[i]));

				// float
				// fabric_price=Float.parseFloat(tear_per_meters[i])*Float.parseFloat(cost_per_meters[i]);

				BigDecimal fabric_price_temp = new BigDecimal(
						(Float.parseFloat(tear_per_meters[i]))
								* (Float.parseFloat(cost_per_meters[i])));
				float fabric_price = fabric_price_temp.setScale(2,
						BigDecimal.ROUND_HALF_UP).floatValue();

				all_fabric_prices += fabric_price;

				fabricCost.setPrice(fabric_price);
				FabricCostDAO.save(fabricCost);
				fabricDAO.save(fabric);
			}
		}
		if (accessory_names != null) {
			for (int i = 0; i < accessory_names.length; i++) {
				Accessory accessory = new Accessory();
				accessory.setOrderId(orderId);
				accessory.setAccessoryName(accessory_names[i]);
				accessory.setAccessoryQuery(accessory_querys[i]);

				AccessoryCost accessoryCost = new AccessoryCost();
				accessoryCost.setOrderId(orderId);
				accessoryCost.setAccessoryName(accessory_names[i]);
				accessoryCost.setTearPerPiece(Float
						.parseFloat(tear_per_piece[i]));
				accessoryCost.setCostPerPiece(Float
						.parseFloat(cost_per_piece[i]));

				float accessory_price = Float.parseFloat(tear_per_piece[i])
						* Float.parseFloat(cost_per_piece[i]);

				all_accessory_prices += accessory_price;

				accessoryCost.setPrice(accessory_price);
				AccessoryCostDAO.save(accessoryCost);
				accessoryDAO.save(accessory);
			}
		}

		Quote quote = quoteDAO.findById(orderId);

		if (quote == null) {
			quote = new Quote();
			quote.setOrderId(orderId);
			quote.setAccessoryCost(all_accessory_prices);
			quote.setFabricCost(all_fabric_prices);
			quoteDAO.save(quote);
		} else {
			quote.setAccessoryCost(all_accessory_prices);
			quote.setFabricCost(all_fabric_prices);

			float singleCost = quote.getCutCost() + quote.getManageCost()
					+ quote.getDesignCost() + quote.getIroningCost()
					+ quote.getNailCost() + quote.getPackageCost()
					+ quote.getSwingCost() + quote.getOtherCost()
					+ all_accessory_prices + all_fabric_prices + quote.getCraftCost();

			quote.setSingleCost(singleCost);
			quoteDAO.attachDirty(quote);
		}

		Map<String, Object> data = new HashMap<String, Object>();
		data.put(RESULT_PURCHASE, result);
		data.put(RESULT_PURCHASE_COMMENT, comment);
		try {
			jbpmAPIUtil.completeTask(taskId, data, ACTOR_PURCHASE_MANAGER);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ===========================采购样衣原料=================================
	@Override
	public List<Map<String, Object>> getPurchaseSampleMaterialList() {
		// TODO Auto-generated method stub
		return service.getOrderList(ACTOR_PURCHASE_MANAGER,
				TASK_PURCHASE_SAMPLE_MATERIAL);
	}

	@Override
	public List<Map<String, Object>> getSearchPurchaseSampleMaterialList(
			String ordernumber, String customername, String stylename,
			String startdate, String enddate, Integer[] employeeIds) {
		return service.getSearchOrderList(ACTOR_PURCHASE_MANAGER, ordernumber,  customername,  stylename,
				 startdate,  enddate,  employeeIds,
				TASK_PURCHASE_SAMPLE_MATERIAL);
	}
	
	@Override
	public Map<String,Object> getPurchaseSampleMaterialDetail(Integer orderId) {
		// TODO Auto-generated method stub
		return service.getBasicOrderModelWithQuote(ACTOR_PURCHASE_MANAGER,
				TASK_PURCHASE_SAMPLE_MATERIAL, orderId);
	}

	@Override
	public boolean purchaseSampleMaterialSubmit(long taskId, boolean result) {
		// TODO Auto-generated method stub
		Map<String, Object> data = new HashMap<String, Object>();
		data.put(RESULT_PURCHASE, result);
		try {
			jbpmAPIUtil.completeTask(taskId, data, ACTOR_PURCHASE_MANAGER);
			
			return true;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean purchaseSampleMaterialSubmit(long taskId, boolean result,
			boolean needcraft,String orderId,String samplepurName,Timestamp samplepurDate,String samplesupplierName) {
		List<Craft>craftList =craftDAO.findByOrderId(Integer.parseInt(orderId));
		Craft craft =craftList.get(0);
		if("0".equals(craft.getNeedCraft().toString())||craft.getNeedCraft()==0){
			craft.setOrderSampleStatus("3");
		}else{
			craft.setOrderSampleStatus("2");
		}
		craftDAO.attachDirty(craft);
		Order order = orderDAO.findById(Integer.parseInt(orderId));
		order.setSamplepurName(samplepurName);
		order.setSamplepurDate(samplepurDate);
		order.setSamplesupplierName(samplesupplierName);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put(RESULT_PURCHASE, result);
		data.put(RESULT_NEED_CRAFT, needcraft);
		try {
			jbpmAPIUtil.completeTask(taskId, data, ACTOR_PURCHASE_MANAGER);
			if(result==false){//如果result的的值为false，即为样衣面料采购失败，流程会异常终止，将orderState设置为1
				order.setOrderState("1");
				
			}
			orderDAO.attachDirty(order);
			return true;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	// ===========================采购确认=================================
	@Override
	public List<Map<String, Object>> getConfirmPurchaseList() {
		// TODO Auto-generated method stub
		return service.getOrderList(ACTOR_PURCHASE_MANAGER,
				TASK_CONFIRM_PURCHASE);
	}

	@Override
	public List<Map<String, Object>> getSearchConfirmPurchaseList(
			String ordernumber, String customername, String stylename,
			String startdate, String enddate, Integer[] employeeIds) {
		return service.getSearchOrderList(ACTOR_PURCHASE_MANAGER, ordernumber,  customername,  stylename,
				 startdate,  enddate,  employeeIds,
				TASK_CONFIRM_PURCHASE);
	}
	
	@Override
	public Map<String, Object> getConfirmPurchaseDetail(Integer orderId) {
		// TODO Auto-generated method stub
		return service.getBasicOrderModelWithQuote(ACTOR_PURCHASE_MANAGER,
				TASK_CONFIRM_PURCHASE, orderId);
	}

	@Override
	public boolean confirmPurchaseSubmit(long taskId, boolean result) {
		// TODO Auto-generated method stub
		Map<String, Object> data = new HashMap<String, Object>();
		data.put(RESULT_PURCHASE, result);
		try {
			jbpmAPIUtil.completeTask(taskId, data, ACTOR_PURCHASE_MANAGER);
			return true;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean confirmPurchaseSubmit(long taskId, boolean result,String orderId) {
		// TODO Auto-generated method stub
		Order order = orderDAO.findById(Integer.parseInt(orderId));
		Map<String, Object> data = new HashMap<String, Object>();
		data.put(RESULT_PURCHASE, result);
		try {
			jbpmAPIUtil.completeTask(taskId, data, ACTOR_PURCHASE_MANAGER);
			if(result==false){//如果result的的值为false，即为大货面料采购失败，流程会异常终止，将orderState设置为1
				order.setOrderState("1");
				orderDAO.attachDirty(order);
			}
			return true;
		} catch (InterruptedException e) {
 			e.printStackTrace();
			return false;
		}
	}
    
	// ========================毛衣原料采购==================================
	@Override
	public List<Map<String, Object>> purchaseSweaterMaterialList() {
		return service.getOrderList(ACTOR_PURCHASE_MANAGER,
				TASK_BUY_SWEATER_MATERIAL);
	}
	
	// ========================毛衣原料采购搜索==================================
	@Override
	public List<Map<String, Object>> getSearchPurchaseSweaterMaterialList(
			String ordernumber, String customername, String stylename,
			String startdate, String enddate, Integer[] employeeIds) {
		return service.getSearchOrderList(ACTOR_PURCHASE_MANAGER, ordernumber,  customername,  stylename,
				 startdate,  enddate,  employeeIds,
				 TASK_BUY_SWEATER_MATERIAL);
	}
	
	// ======================毛衣原料采购详情===================================
	@Override
	public Map<String, Object> getPurchaseSweaterMaterialDetail(int orderId) {
		return service.getBasicOrderModelWithQuote(ACTOR_PURCHASE_MANAGER,
				TASK_BUY_SWEATER_MATERIAL, orderId);
	}
	
	@Override
	public boolean purchaseSweaterMaterialSubmit(long taskId, String orderId,
			String total_price, String weight, String type,
			String purchase_time, String supplier, String head,
			boolean buySweaterMaterialResult){
		try {
			Map<String, Object> data = new HashMap<String, Object>();
			Order order = orderDAO.findById(Integer.parseInt(orderId));
			order.setBuySweaterMaterialResult(buySweaterMaterialResult);
			order.setWool_weight(weight);
			order.setTotal_price(total_price);
			order.setWool_type(type);
			order.setPurchase_director(head);
			order.setPurchase_time(purchase_time);
			order.setSupplier(supplier);
			orderDAO.merge(order);
			data.put("sweaterMaterial", buySweaterMaterialResult);
			jbpmAPIUtil.completeTask(taskId, data, ACTOR_PURCHASE_MANAGER);
			return true;
		} catch (InterruptedException e) {
 			e.printStackTrace();
			return false;
		}
	}
	
	// ========================大货原料采购=================================
	@Override
	public List<Map<String, Object>> getPurchaseMaterialList() {
		// TODO Auto-generated method stub
		return service.getOrderList(ACTOR_PURCHASE_MANAGER,
				TASK_PURCHASE_MATERIAL);
	}

	@Override
	public List<Map<String, Object>> getSearchPurchaseMaterialList(
			String ordernumber, String customername, String stylename,
			String startdate, String enddate, Integer[] employeeIds) {
		return service.getSearchOrderList(ACTOR_PURCHASE_MANAGER, ordernumber,  customername,  stylename,
				 startdate,  enddate,  employeeIds,
				TASK_PURCHASE_MATERIAL);
	}
	
	@Override
	public Map<String, Object> getPurchaseMaterialDetail(Integer orderId) {
		// TODO Auto-generated method stub
		return service.getBasicOrderModelWithQuote(ACTOR_PURCHASE_MANAGER,
				TASK_PURCHASE_MATERIAL, orderId);
	}

	@Override
	public boolean purchaseMaterialSubmit(long taskId, boolean result) {
		// TODO Auto-generated method stub
		Map<String, Object> data = new HashMap<String, Object>();
		data.put(RESULT_PURCHASE, result);
		try {
			jbpmAPIUtil.completeTask(taskId, data, ACTOR_PURCHASE_MANAGER);
			return true;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean purchaseMaterialSubmit(long taskId, boolean result,String orderId,String masspurName,Timestamp masspurDate,String masssupplierName) {
		// TODO Auto-generated method stub
		Order order = orderDAO.findById(Integer.parseInt(orderId));
		order.setMasspurName(masspurName);
		order.setMasspurDate(masspurDate);
		order.setMasssupplierName(masssupplierName);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put(RESULT_PURCHASE, result);
		if(result==false){
		if(order.getIsHaoDuoYi().equals(1)){			
			data.put(RESULT_IS_HAODUOYI,true );
		}else{
			data.put(RESULT_IS_HAODUOYI,false );
		}
		}
		try {
			jbpmAPIUtil.completeTask(taskId, data, ACTOR_PURCHASE_MANAGER);
			orderDAO.attachDirty(order);
			return true;
		} catch (InterruptedException e) {
 			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public Map<String, Object> getPrintProcurementOrderDetail(Integer orderId,List<Produce> productList) {
		// TODO Auto-generated method stub
		Map<String,Object>model=new HashMap<String,Object>();
		Order order = orderDAO.findById(orderId);
		model.put("order", order);
		model.put("customer",customerDAO.findById(order.getCustomerId()));
		model.put("employee", employeeDAO.findById(order.getEmployeeId()));
		model.put("logistics", logisticsDAO.findById(orderId));
		model.put("fabrics", FabricCostDAO.findByOrderId(orderId));
		model.put("accessorys", accessoryDAO.findByOrderId(orderId));
		model.put("orderId", service.getOrderId(order));
		Produce produce = new Produce();
		produce.setOid(orderId);
		produce.setType(Produce.TYPE_SAMPLE_PRODUCE);
		model.put("sample", ProduceDAO.findByExample(produce));
		if(productList != null){
			model.put("produce", productList);
		}else{
			produce.setType(Produce.TYPE_PRODUCE);
			model.put("produce", ProduceDAO.findByExample(produce));
		}
		return model;

	}

	@Autowired
	private ServiceUtil service;
	@Autowired
	private OrderDAO orderDAO;
	@Autowired
	private JbpmAPIUtil jbpmAPIUtil;
	@Autowired
	private LogisticsDAO logisticsDAO;
	@Autowired
	private FabricDAO fabricDAO;
	@Autowired
	private QuoteDAO quoteDAO;
	@Autowired
	private AccessoryDAO accessoryDAO;
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private AccessoryCostDAO AccessoryCostDAO;
	@Autowired
	private FabricCostDAO FabricCostDAO;
	@Autowired
	private ProduceDAO ProduceDAO;
	@Autowired
	private CraftDAO craftDAO;
	@Autowired
	private CustomerDAO customerDAO;
	@Autowired
	private EmployeeDAO employeeDAO;
	

	public final static String ACTOR_PURCHASE_MANAGER = "purchaseManager";
	public final static String TASK_VERIFY_PURCHASE = "verifyPurchase";
	public final static String TASK_COMPUTE_PURCHASE_COST = "computePurchaseCost";
	public final static String TASK_PURCHASE_SAMPLE_MATERIAL = "purchaseSampleMaterial";
	public final static String TASK_CONFIRM_PURCHASE = "confirmPurchase";
	public final static String TASK_PURCHASE_MATERIAL = "purchaseMaterial";
	public final static String TASK_BUY_SWEATER_MATERIAL ="buySweaterMaterial";        
	public final static String RESULT_PURCHASE = "purchase";
	public final static String RESULT_PURCHASE_COMMENT = "purchaseComment";
    public final static String RESULT_NEED_CRAFT = "needCraft";
    public final static String RESULT_IS_HAODUOYI = "isHaoDuoYi";
    public final static String RESULT_PURCHASE_SWEATER_MATERIAL ="sweaterMaterial";








}
