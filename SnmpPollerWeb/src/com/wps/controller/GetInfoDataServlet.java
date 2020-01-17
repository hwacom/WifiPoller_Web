package com.wps.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.service.LoadFileData;
import com.service.impl.LoadFileDataImpl;
import com.wps.DatatableResponse;
import com.wps.vo.InfoDataVO;

/**
 * Servlet implementation class InfoDataServlet
 */
@WebServlet("/servlet/getInfoData.json")
public class GetInfoDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(GetInfoDataServlet.class);
	
	private LoadFileData loadFileData;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetInfoDataServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if (loadFileData == null) {
				loadFileData = new LoadFileDataImpl();
			}
			List<InfoDataVO> infoDataList = loadFileData.loadInfoData();
			
			DatatableResponse data = new DatatableResponse(new Long(infoDataList.size()), infoDataList, new Long(infoDataList.size()), "GetInfoData..");

			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(data);

			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(json);

		} catch (Exception e) {
			log.error(e.toString(), e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
