package com.my.study.file;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ParseXml {
	
	public static JSONObject getFormByReportid(Connection connection,String reportid){
		
		
		return null;
	}
	
	
	private static File getFirstFileByReportid(String reportid){
		String fileName = new String("V_"+reportid+".csv");
		
		
		return null;
	}
	
	
	
	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(ParseXml.class);

	/**
	 * 获取指标集
	 * 
	 * @author : KLP
	 * @param tableMetaInform
	 * @param reportId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static JSONArray getMetaNameArray(File tableMetaInform, String reportId,Connection connection) throws Exception {
		JSONArray cols = new JSONArray();
		SAXReader reader = new SAXReader();
		File dataInform = new File(tableMetaInform.getParent()+"/dataInform.xml");
		String period = parsePeriods(dataInform, reportId).getString(0);
		String year = period.substring(0, 4);
		reader.setValidation(false);
		Document document = reader.read(tableMetaInform);
		List<Node> nodes = document.selectNodes("//tableDescription");
		for (Node node : nodes) {
			String tableId = node.selectSingleNode("tableId").getText().trim();
			if (reportId.equals(tableId)) {
				// result.put("tableId", tableId);
				List<Node> modelNodes = node.selectNodes("modelElements");
				for (Node model : modelNodes) {
					JSONObject result = new JSONObject(16, true);
					String colName = model.selectSingleNode("colName").getText().trim();
					List<Node> metaNodes = model.selectNodes("metaElements");
					StringBuilder metaName = new StringBuilder();
					for (Node metaNode : metaNodes) {
						String meta = metaNode.selectSingleNode("metaName").getText().trim();
						metaName.append(meta);
					}
					if (metaName.equals("代码") || metaName.equals("产品代码") || metaName.equals("序号")) {
						result.put("code", "CODE");
						result.put("name", metaName);
					} else if (colName.equals("DATAINDEX")) {
						result.put("code", "CODE");
						result.put("name", "代码DATAINDEX");
					} else {
						result.put("code", colName);
						result.put("name", metaName);
					}
					result.put("type", "字符");
					result.put("unit", "50");
					addRange(result,year, connection);
					cols.add(result);
				}
			}
		}
		return cols;
	}

	/**
	 * 添加指标范围 range 
	 * @author : KLP
	 * @param result
	 * @param year
	 * @param connection
	 * @throws SQLException
	 */
	public static void addRange(JSONObject result,String year, Connection connection) throws SQLException {
		String sql = "select code,bgq from (select * from wf_zbfz_zq where name='" + result.getString("name") + "' and substr(bgq,0,4)='"
				+ year + "') where rownum=1";
		Map<String, Object> map = new QueryRunner().query(connection, sql, new MapHandler());
		if (null != map) {
			String code = (String) map.get("code");
			String ver = (String) map.get("bgq");
			JSONObject range = new JSONObject();
			range.put("code", code);
			range.put("name", result.getString("name"));
			range.put("ver", ver);
			result.put("range", range);
		}
	}

	/**
	 * 获取报告期集合 认为每个tableIndo.xml文件里边同一个表的报告期是一样的，只需要读取一个文件即可
	 * 
	 * @author : KLP
	 * @return
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public static JSONArray parsePeriods(File dataInform, String reportId) throws DocumentException {
		JSONArray periods = new JSONArray();
		SAXReader reader = new SAXReader();
		String period = "";
		reader.setValidation(false);
		Document document = reader.read(dataInform);
		List<Node> tableNodes = document.selectNodes("//tableDescription");
		for (Node tableNode : tableNodes) {
			String tableId = tableNode.selectSingleNode("tableId").getText().trim();
			if (reportId.equals(tableId)) {
				List<Node> periodNodes = tableNode.selectNodes("period");
				for (Node periodNode : periodNodes) {
					period = periodNode.getText().trim();
					periods.add(period);
				}
			}
		}
		return periods;
	}
	
	public static void main(String[] args) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//201-1  2016  
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.59:1521:PZSERVER","md","md");
		JSONArray metaNameList = getMetaNameArray(
				new File("C:/tt/tt/非年报/企业一套表统计调查制度(2016)/规模以下工业[B1]/520100000000_20170222094817/tableMetaInform.xml"),
				"8600000002015112307320107",conn);
		System.out.println(metaNameList);

		// JSONArray array = parsePeriods(new
		// File("C:/tt/tt/非年报/企业一套表统计调查制度(2015)/重点服务业[F]/520100000000_20170222100909/dataInform.xml"),
		// "8600000002014112707840002");
		// System.out.println(array.toJSONString());
		// System.out.println(array.size());
		
		
	}

}
