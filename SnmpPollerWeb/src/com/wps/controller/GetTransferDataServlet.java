package com.wps.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.service.LoadFileData;
import com.service.impl.LoadFileDataImpl;
import com.wps.DatatableResponse;
import com.wps.vo.TransferDataVO;

/**
 * Servlet implementation class GetTransferDataServlet
 */
@WebServlet("/servlet/getTransferData.json")
public class GetTransferDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LoadFileData loadFileData;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetTransferDataServlet() {
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
			List<TransferDataVO> transferDataList = loadFileData.loadTransferData();

			DatatableResponse data = new DatatableResponse(new Long(transferDataList.size()), transferDataList, new Long(transferDataList.size()), "GetTransferData..");

			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(data);

			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(json);

		} catch (Exception e) {
			e.printStackTrace();
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
