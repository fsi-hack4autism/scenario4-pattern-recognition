import { Component } from '@angular/core';
import { FileUpload } from './FielUpload';
import { HttpClient, HttpEventType } from '@angular/common/http';
import { ReadVarExpr } from '@angular/compiler';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Session Upload';

  constructor(private http: HttpClient) { }


  fileUpload :FileUpload = {} as FileUpload;
  fileToUpload: File = null;

  onsubmitclicked() {
    alert(this.fileUpload.therapistId);
    alert(this.fileUpload.recordingLocation);

    this.fileUpload.fileContent = this.fileToUpload;
    
 
    this
    .http
    .post("URL",this.fileUpload,{reportProgress: true, observe: 'events'})
    .subscribe(event =>{
      if (event.type === HttpEventType.UploadProgress){

      }
      else if(event.type === HttpEventType.Response){
        alert("recording uploaded successfully");
      }
    });
    

 
}


  handleFileInput(files: FileList) {
    console.log("in file");
    this.fileToUpload = files.item(0);
        
}

}
