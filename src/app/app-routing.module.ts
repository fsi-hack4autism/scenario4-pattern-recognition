import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AnalyzeComponent } from './analyze/analyze.component';
import { ChartsComponent } from './charts/charts.component';
import { LoginComponent } from './login/login.component';
import { UploadTherapyComponent } from './upload-therapy/upload-therapy.component';
import { VisualizeComponent } from './visualize/visualize.component';

const routes: Routes = [
  {path: 'visualize', component: VisualizeComponent},
  {path: 'uploadTherapy', component: UploadTherapyComponent},
  {path: 'login', component: LoginComponent},
  {path: 'analyze', component: AnalyzeComponent}
  
    
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
