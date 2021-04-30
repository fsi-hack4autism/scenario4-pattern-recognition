import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ChartsModule } from 'ng2-charts';
import { ChartsComponent } from './charts/charts.component';
import { UploadTherapyComponent } from './upload-therapy/upload-therapy.component';
import { LoginComponent } from './login/login.component';
import { VisualizeComponent } from './visualize/visualize.component';
import { AnalyzeComponent } from './analyze/analyze.component';
import { DonutchartComponent } from './donutchart/donutchart.component';
import { LinechartComponent } from './linechart/linechart.component';
import { ScatterplotComponent } from './scatterplot/scatterplot.component'; 

@NgModule({
  declarations: [
    AppComponent,
    ChartsComponent,
    UploadTherapyComponent,
    LoginComponent,
    VisualizeComponent,
    AnalyzeComponent,
    DonutchartComponent,
    LinechartComponent,
    ScatterplotComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    ChartsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
