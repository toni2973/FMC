package nju.software.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface DesignService {

	// ===========================设计验证=================================
	public List<Map<String, Object>> getVerifyDesignList();

	public Map<String, Object> getVerifyDesignDetail(int orderId);

	public boolean verifyDesignSubmit(long taskId, boolean result,
			String comment);

	// ===========================上传版型=================================
	public List<Map<String, Object>> getUploadDesignList();

	public Map<String, Object> getUploadDesignDetail(Integer orderId);

	public void uploadDesignSubmit(int orderId, long taskId, String url,
			Timestamp uploadTime);

	// ===========================修改版型=================================
	public List<Map<String, Object>> getModifyDesignList();

	public Map<String, Object> getModifyDesignDetail(Integer orderId);

	public boolean modifyDesignSubmit(int orderId, long taskId, String url,
			Timestamp uploadTime);

	// ===========================确认版型=================================
	public List<Map<String, Object>> getConfirmDesignList();

	public Map<String, Object> getConfirmDesignDetail(Integer orderId);

	public List<Map<String, Object>> getSearchVerifyDesignList(
			String ordernumber, String customername, String stylename,
			String startdate, String enddate, Integer[] employeeIds);

	public List<Map<String, Object>> getSearchUploadDesignList(
			String ordernumber, String customername, String stylename,
			String startdate, String enddate, Integer[] employeeIds);

	public List<Map<String, Object>> getSearchModifyDesignList(
			String ordernumber, String customername, String stylename,
			String startdate, String enddate, Integer[] employeeIds);

	public List<Map<String, Object>> getSearchConfirmDesignList(
			String ordernumber, String customername, String stylename,
			String startdate, String enddate, Integer[] employeeIds);

	public List<Map<String, Object>> getComputeDesignCostList();

	public List<Map<String, Object>> getSearchComputeDesignCostList(
			String ordernumber, String customername, String stylename,
			String startdate, String enddate, Integer[] employeeIds);

	public Map<String, Object> getComputeDesignCostInfo(Integer orderId);

	public List<Map<String, Object>> getNeedCraftList();

	public boolean produceSampleSubmit(long taskId, boolean result);

	public List<Map<String, Object>> getNeedCraftSampleList();

	public List<Map<String, Object>> getTypeSettingSliceList();

	public Map<String, Object> getNeedCraftSampleDetail(int orderId);

	public Map<String, Object> getNeedCraftProductDetail(int orderId);

	public Map<String, Object> getTypeSettingSliceDetail(int orderId);

	public void uploadCraftFileSubmit(int orderId, String craftFileUrl);

	public void needCraftSampleSubmit(int orderId, long taskId,
			String craftLeader, Timestamp completeTime);

	public void needCraftProductSubmit(int orderId, long taskId,
			String crafsManName, Timestamp crafsProduceDate);

	public void getTypeSettingSliceSubmit(int orderId, long taskId);

	public void computeDesignCostSubmit(int orderId, long taskId,
			boolean result, String comment, short needCraft,
			float stampDutyMoney, float washHangDyeMoney, float laserMoney,
			float embroideryMoney, float crumpleMoney, float openVersionMoney);

	public List<Map<String, Object>> getConfirmCadList();

	public Map<String, Object> getConfirmCadDetail(int orderId);

	public boolean confirmCadSubmit(int orderId, long taskId, String cadurl,
			Timestamp uploadTime);

	public void getTypeSettingSliceSubmit(int orderId, String cadding_side,
			long taskId);

	public boolean produceSampleSubmit(long taskId, boolean result,
			String orderId);

	public void EntryCadData(int orderId, long taskId, String url,
			Timestamp uploadTime, String cadSide, Timestamp completeTime);

	// 获取订单中工艺状态
	String getCraftInfo(Integer orderId);

	public List<Map<String, Object>> getSearchNeedCraftSampleList(
			String ordernumber, String customername, String stylename,
			String startdate, String enddate, Integer[] employeeIds);

	public List<Map<String, Object>> getSearchNeedCraftList(String ordernumber,
			String customername, String stylename, String startdate,
			String enddate, Integer[] employeeIds);

	public List<Map<String, Object>> getSearchTypeSettingSliceList(
			String ordernumber, String customername, String stylename,
			String startdate, String enddate, Integer[] employeeIds);

	public List<Map<String, Object>> getSearchConfirmCadList(
			String ordernumber, String customername, String stylename,
			String startdate, String enddate, Integer[] employeeIds);

}
