import { Component,OnInit } from '@angular/core';
import { FileUpload } from '../FielUpload';
import { HttpClient, HttpEventType } from '@angular/common/http';
import { ReadVarExpr } from '@angular/compiler';


@Component({
  selector: 'app-upload-therapy',
  templateUrl: './upload-therapy.component.html',
  styleUrls: ['./upload-therapy.component.css']
})
export class UploadTherapyComponent implements OnInit {

  title = 'Session Upload';

  constructor(private http: HttpClient) { }


  fileUpload :FileUpload = {} as FileUpload;
  fileToUpload: File = null;

  ngOnInit(): void {
  }


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
