package com.service;

import java.util.List;

import com.wps.vo.InfoDataVO;
import com.wps.vo.TransferDataVO;

public interface LoadFileData {

	public List<InfoDataVO> loadInfoData() throws Exception;

	public List<TransferDataVO> loadTransferData() throws Exception;
}
