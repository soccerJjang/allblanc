<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/commonInclude.jsp" %>

<script src="/resources/adm/vendors/chart.js/dist/Chart.min.js"></script>
<script type="text/javaScript">
<!--
	$(document).ready(function() {
		var lineChart = document.getElementById("line-chart");
	    var lineCtx = lineChart.getContext('2d');
	    lineChart.height = 80;
	    var datapack1 = [65, 59, 80, 81, 56, 55, 40];
	    var datapack2 = [28, 48, 40, 19, 90, 27, 75];
	    var lineConfig = new Chart(lineCtx, {
	        type: 'line',
	        data: {
	        labels: ["16th", "17th", "18th", "19th", "20th", "21th", "22th"],
	        datasets: [{
	            label: 'Series A',
	            backgroundColor: infoInverse,
	            borderColor: info,
	            pointBackgroundColor: info,
	            borderWidth: 2,
	            data: datapack1
	        },
	        {
	            label: 'Series B',
	            backgroundColor: successInverse,
	            borderColor: success,
	            pointBackgroundColor: success,
	            borderWidth: 2,
	            data: datapack2
	            }]
	        },
	        
	        options: {
	            legend: {
	                display: false
	            },
	            scales: {
	              
	              yAxes: [{

	                  stacked: true,
	                   ticks: {
	                      min: 0,
	                      stepSize: 30,
	                  }

	              }]
	          }
	        }
	    });
	});

	<c:if test="${!empty resultMsg}">swal("<spring:message code="${resultMsg}" />");</c:if>
-->
</script>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <div class="widget card">
                <div class="card-block">
                    <h5 class="card-title">Monthly Overview</h5>
                    <div class="row mrg-top-30">
                        <div class="col-md-3 col-sm-6 col-6 border right border-hide-md">
                            <div class="text-center pdd-vertical-10">
                                <h2 class="font-primary no-mrg-top">8%</h2>
                                <p class="no-mrg-btm">APPL</p>
                            </div>
                        </div>
                        <div class="col-md-3 col-sm-6 col-6 border right border-hide-md">
                            <div class="text-center pdd-vertical-10">
                                <h2 class="font-primary no-mrg-top">$1,730</h2>
                                <p class="no-mrg-btm">M.AVG</p>
                            </div>
                        </div>
                        <div class="col-md-3 col-sm-6 col-6 border right border-hide-md">
                            <div class="text-center pdd-vertical-10">
                                <h2 class="font-primary no-mrg-top">77%</h2>
                                <p class="no-mrg-btm">Increment</p>
                            </div>
                        </div>
                        <div class="col-md-3 col-sm-6 col-6">
                            <div class="text-center pdd-vertical-10">
                                <h2 class="font-primary no-mrg-top">18%</h2>
                                <p class="no-mrg-btm">Profit</p>
                            </div>
                        </div>
                    </div>
                    <div class="row mrg-top-35">
                        <div class="col-md-12">
                            <div>
                                <canvas id="line-chart" height="220"></canvas>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
