import { Component } from '@angular/core';

import { NavController } from 'ionic-angular';

@Component({
  selector: 'fifth-slide',
  templateUrl: 'fifthslide.html'
})
export class FifthSlide {
          private pixelhidden : boolean = false;
          private camerahidden : boolean = true;
  constructor(public navCtrl: NavController) {
    
  }

  pixelshow(){
            this.pixelhidden = false;
            this.camerahidden = true;
  }
  camerashow(){
            this.camerahidden = false;
            this.pixelhidden = true;
  }
}
