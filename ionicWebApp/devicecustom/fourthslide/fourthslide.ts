import { Component } from '@angular/core';

import { NavController } from 'ionic-angular';

@Component({
  selector: 'fourth-slide',
  templateUrl: 'fourthslide.html'
})
export class FourthSlide {
          private performance;
          private efficiencyhidden :boolean = true;
  constructor(public navCtrl: NavController) {
            this.performance = "premium";
  }       
          efficiencyshow(){
                    this.efficiencyhidden = false;
          }
          efficiencyhide(){
                    this.efficiencyhidden = true;
          }
}
