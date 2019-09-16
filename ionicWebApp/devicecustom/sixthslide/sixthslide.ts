import { Component } from '@angular/core';

import { NavController } from 'ionic-angular';

@Component({
  selector: 'sixth-slide',
  templateUrl: 'sixthslide.html'
})
export class SixthSlide {

  constructor(public navCtrl: NavController) {
    
  }
  myYear: String = new Date().toISOString();
  myMonth: String = new Date().toISOString();
}
