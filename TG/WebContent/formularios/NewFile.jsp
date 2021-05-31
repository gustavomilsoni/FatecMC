<%@page import="org.jfree.data.xy.XYSeriesCollection"%>
<%@page import="org.jfree.data.xy.XYDataset"%>
<%@page import="org.jfree.data.xy.XYSeries"%>
<%@page import="org.jfree.chart.plot.PlotOrientation"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="java.io.*"%>
<%@page import="org.jfree.chart.*"%>
<%@page import="org.jfree.data.general.*"%>
<%@page import="org.jfree.data.category.DefaultCategoryDataset"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
XYSeries data = new XYSeries("Average Weight");

data.add(500,600);
data.add(40,700);
data.add(400,800);

XYDataset xyDataset = new XYSeriesCollection(data);

JFreeChart grafico = ChartFactory.createXYLineChart("teste","data","true", xyDataset, PlotOrientation.VERTICAL,true,true,false);
response.setContentType("image/JPEG");
OutputStream sa = response.getOutputStream();
ChartUtilities.writeChartAsJPEG(sa, grafico, 500, 500);





//File lineChart = new File( "LineChart.jpeg" ); 
//ChartUtilities.saveChartAsJPEG(lineChart ,lineChartObject, width ,height);

%>
</body>
</html>