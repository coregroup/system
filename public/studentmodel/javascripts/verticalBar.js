var MONTHS = [ "Expressões", "Estruturas", "Entrada e Saída de Dados",
				"Tipo de Dados", "Declaração", "Recursividade" ];

var MONTHS = [ 
	
];
		var color = Chart.helpers.color;
		var barChartData = {
			labels : [ "Expressões", "Estruturas", "Entrada e Saída de Dados" ],
			datasets : [
					{
						label : 'Aluno',
						backgroundColor : color(window.chartColors.red).alpha(0.5)
								.rgbString(),
						borderColor : window.chartColors.red,
						borderWidth : 1,
						data : [ 40, 92, 85, 95, 92, 74 ]
					},
					{
						label : 'Turma',
						backgroundColor : color(window.chartColors.blue).alpha(0.5)
								.rgbString(),
						borderColor : window.chartColors.blue,
						borderWidth : 1,
						data : [ 70, 92, 80, 90, 97, 75 ]
					},
					{
						label : 'Conteúdos Ministrados',
						backgroundColor : color(window.chartColors.green)
								.alpha(0.5).rgbString(),
						borderColor : window.chartColors.green,
						borderWidth : 1,
						data : [ 100, 100, 100, 100, 100, 100 ]
					} ]
		};
	
		window.onload = function() {
			var ctx = document.getElementById("canvas").getContext("2d");
			window.myBar = new Chart(ctx, {
				type : 'bar',
				data : barChartData,
				options : {
					responsive : true,
					legend : {
						position : 'right',
					},
					title : {
						display : true,
						text : 'Modelo de Gráfico'
					}
				}
			});
	
		};
	
		document
				.getElementById('addData')
				.addEventListener(
						'click',
						function() {
							if (barChartData.datasets.length > 0) {
								if (barChartData.labels.length < 6) {
									var month = MONTHS[barChartData.labels.length
											% (MONTHS.length)];
									barChartData.labels.push(month);
								} else {
									window
											.alert("Não existe mais nenhum assunto cadastrado");
								}
								window.myBar.update();
							}
						});
	
		document.getElementById('removeData').addEventListener('click', function() {
			barChartData.labels.splice(-1, 1); // remove the label first
	
			window.myBar.update();
		});
	
		Chart.scaleService.updateScaleDefaults('linear', {
			ticks : {
				min : 0,
				max : 100
			}
		});