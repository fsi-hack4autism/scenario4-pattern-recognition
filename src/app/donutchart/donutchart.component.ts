import { Component, Input, OnInit } from '@angular/core';
import { ChartType } from 'chart.js';
import { MultiDataSet, Label } from 'ng2-charts';

@Component({
  selector: 'app-donutchart',
  templateUrl: './donutchart.component.html',
  styleUrls: ['./donutchart.component.css']
})
export class DonutchartComponent implements OnInit {

 // public doughnutChartLabels: Label[] = ['BenchMark', 'Test'];
  @Input() doughnutChartLabels: Label[];
  
  //public doughnutChartData: MultiDataSet = [
    //[350, 450],
    //[50, 150]
  //];
  @Input()doughnutChartData : MultiDataSet;
  
  public doughnutChartType: ChartType = 'doughnut';


  constructor() { }

  ngOnInit(): void {
  }

}
