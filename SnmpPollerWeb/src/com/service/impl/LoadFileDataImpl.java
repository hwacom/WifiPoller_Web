package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.service.LoadFileData;
import com.utils.FileUtils;
import com.wps.Env;
import com.wps.vo.InfoDataVO;
import com.wps.vo.TransferDataVO;

public class LoadFileDataImpl implements LoadFileData {

	@Override
	public List<InfoDataVO> loadInfoData() throws Exception {
		List<InfoDataVO> retList = new ArrayList<InfoDataVO>();

		try {
			List<String> oriData = FileUtils.loadFile(FileUtils.getNewestFile());
			//			oriData.forEach(line -> System.out.println(line));

			if (oriData != null && !oriData.isEmpty()) {
				oriData.forEach(line -> {
					String[] values = line.split(Env.FILE_CONTENT_SEPARATOR_SYMBOL);
					if(values.length > 10 && values[5] != null && !values[5].isEmpty()) {
						InfoDataVO vo = new InfoDataVO(values);
						if(vo.getApMacAddr().split("\\:").length > 5 && vo.getUserMacAddr().split("\\:").length > 5) {
							retList.add(vo);
						}
					}
				});
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return retList;
	}

	@Override
	public List<TransferDataVO> loadTransferData() throws Exception {
		List<TransferDataVO> retList = new ArrayList<TransferDataVO>();

		try {
			List<String> oriData = FileUtils.loadFile(FileUtils.getNewestFile());
			//			oriData.forEach(line -> System.out.println(line));

			oriData.forEach(line -> {
				String[] values = line.split(Env.FILE_CONTENT_SEPARATOR_SYMBOL);
				retList.add(new TransferDataVO(values));
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

		return retList;
	}

}
