import { Component } from '@angular/core';
import { FileUpload } from './FielUpload';
import { HttpClient } from '@angular/common/http';
import { ReadVarExpr } from '@angular/compiler';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'fsi-hackathon2';

  username: string = '';
  fileUpload :FileUpload = {} as FileUpload;
  fileToUpload: File = null;

  onsubmitclicked() {
    alert(this.fileUpload.therapistId);
    alert(this.fileUpload.recordingLocation);
    
   

  }

  handleFileInput(files: FileList) {
    console.log("in file upload");
    this.fileToUpload = files.item(0);
    
    var myReader:FileReader = new FileReader();

    myReader.onloadend = function(e){
      // you can perform an action with readed data here
      //console.log(myReader.result);
      console.log("process of reading complete");
      return myReader.result;  
    }
    this.fileUpload.fileContent = myReader.readAsText(this.fileToUpload)

    console.log(this.fileUpload.fileContent);

}

}
