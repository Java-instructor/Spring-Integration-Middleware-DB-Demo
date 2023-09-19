package com.java.instructor.dbpoller.utils;

public class QueryUtils {

	/*
	 * public static void main(String[] args) { QueryDetails qDetails = new
	 * QueryDetails(); qDetails.setOriginalSQL("");
	 * 
	 * getParamMapFromQuery(qDetails); replaceSQLAsParam(qDetails);
	 * System.out.println(qDetails.getKeyValueMap());
	 * System.out.println(qDetails.getUpdatedSQL()); }
	 * 
	 * public static QueryDetails getQueryDetails(String sql) { QueryDetails
	 * qDetails = new QueryDetails(); qDetails.setOriginalSQL(sql);
	 * getParamMapFromQuery(qDetails); replaceSQLAsParam(qDetails);
	 * 
	 * return qDetails; }
	 * 
	 * public static void getParamMapFromQuery(QueryDetails qDetails) { String query
	 * = qDetails.getOriginalSQL(); Map<String, String> map = new HashMap<String,
	 * String>(); List<String> values = new ArrayList(); String[] splits =
	 * query.trim().split(" "); List<String> listStr =
	 * removeSpace(Arrays.asList(splits)); for (int i = 0; i < listStr.size(); i++)
	 * { String s1 = listStr.get(i); s1 = s1.trim(); if (s1.length() > 0 &&
	 * s1.equals("=") && listStr.get(i + 1).contains("'")) { String key; String
	 * value; if (listStr.get(i - 1).contains(".")) { key = listStr.get(i -
	 * 1).substring(listStr.get(i - 1).indexOf(".") + 1);
	 * 
	 * } else { key = listStr.get(i - 1);
	 * 
	 * } int findex = listStr.get(i + 1).indexOf("'"); int lindex = listStr.get(i +
	 * 1).lastIndexOf("'"); value = listStr.get(i + 1).substring(findex + 1,
	 * lindex); map.put(key, value); values.add(value); } }
	 * 
	 * String[] paramstr = Arrays.copyOf(values.toArray(), values.size(),
	 * String[].class); qDetails.setParams(paramstr); qDetails.setKeyValueMap(map);
	 * }
	 * 
	 * public static List<String> removeSpace(List<String> lst) { List<String>
	 * listStr = new ArrayList<String>(); for (String s : lst) { if
	 * (s.trim().length() > 0) { listStr.add(s.trim()); } } return listStr; }
	 * 
	 * public static void replaceSQLAsParam(QueryDetails qDetails) { String sql =
	 * qDetails.getOriginalSQL(); Set<String> keys = qDetails.keyValueMap.keySet();
	 * for (String key : keys) { String v = qDetails.keyValueMap.get(key); sql =
	 * sql.replaceAll("'" + v + "'", "?");
	 * 
	 * } qDetails.setUpdatedSQL(sql); }
	 * 
	 * } class QueryDetails {
	 * 
	 * String[] params; String originalSQL; String updatedSQL; Map<String, String>
	 * keyValueMap = new HashMap<String, String>();
	 * 
	 * public String[] getParams() { return params; }
	 * 
	 * public void setParams(String[] params) { this.params = params; }
	 * 
	 * public String getOriginalSQL() { return originalSQL; }
	 * 
	 * public void setOriginalSQL(String originalSQL) { this.originalSQL =
	 * originalSQL; }
	 * 
	 * public String getUpdatedSQL() { return updatedSQL; }
	 * 
	 * public void setUpdatedSQL(String updatedSQL) { this.updatedSQL = updatedSQL;
	 * }
	 * 
	 * public Map<String, String> getKeyValueMap() { return keyValueMap; }
	 * 
	 * public void setKeyValueMap(Map<String, String> keyValueMap) {
	 * this.keyValueMap = keyValueMap; }
	 */
}
