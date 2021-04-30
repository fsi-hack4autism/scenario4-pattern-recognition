import { Component, OnInit } from '@angular/core';
import { MultiDataSet, Label } from 'ng2-charts';
import { ChartDataSets, ChartOptions, ChartType } from 'chart.js';

@Component({
  selector: 'app-visualize',
  templateUrl: './visualize.component.html',
  styleUrls: ['./visualize.component.css']
})
export class VisualizeComponent implements OnInit {

  constructor() { }
  
  public doughnutChartLabels1: Label[] = ['Overall BenchMark', 'Overall Test'];

 public doughnutChartData1: MultiDataSet = [
    [350, 450],
    [50, 150]
  ];
 
  public doughnutChartLabels2: Label[] = ['First BenchMark', 'First Test'];

  public doughnutChartData2: MultiDataSet = [
     [350, 450],
     [50, 150]
   ];

   public doughnutChartLabels3: Label[] = ['Second BenchMark', 'Second Test'];

   public doughnutChartData3: MultiDataSet = [
      [350, 450],
      [50, 150]
    ];

    public lineChartData: ChartDataSets[] = [
      { data: [65, 59, 80, 81, 56, 55, 40], label: 'BenchMark' },
      { data: [28, 48, 40, 19, 86, 27, 90], label: 'Test' }
    ];

  ngOnInit(): void {
  }

}
