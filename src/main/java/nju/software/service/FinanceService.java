package nju.software.service;

import java.util.List;
import java.util.Map;

import nju.software.dataobject.Money;
import nju.software.model.OrderInfo;

public interface FinanceService {
	
	//===========================样衣金确认=================================
	public List<Map<String,Object>>getConfirmSampleMoneyList(String actorId);
	
	public Map<String,Object> getConfirmSampleMoneyDetail(String actorId,Integer orderId);
	
	public boolean confirmSampleMoneySubmit(String actorId,long taskId,boolean result, Money money);
	
	//===========================定金确认===================================
	public List<Map<String,Object>>getConfirmDepositList(String actorId);
	
	public Map<String,Object> getConfirmDepositDetail(String actorId,Integer orderId);
	
	public boolean confirmDepositSubmit(String actorId,long taskId,boolean result, Money money);
	
	//===========================尾款确认===================================
	public List<Map<String,Object>>getConfirmFinalPaymentList(String actorId);
	
	public Map<String,Object> getConfirmFinalPaymentDetail(String actorId,Integer orderId);
	
	public boolean confirmFinalPaymentSubmit(String actorId,long taskId,boolean result, Money money);
}
